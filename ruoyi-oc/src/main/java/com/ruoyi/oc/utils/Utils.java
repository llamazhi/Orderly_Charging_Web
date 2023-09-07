package com.ruoyi.oc.utils;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.oc.admin.ev.entity.domain.OcElectricalVehicle;
import com.ruoyi.oc.admin.ev.params.domain.OcElectricalVehicleParameters;
import com.ruoyi.oc.admin.transformer.records.domain.OcTransformerRecords;
import com.ruoyi.oc.admin.user.domain.OcUserRc;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;
import com.ruoyi.oc.utils.optimizer.ChargingStrategy;
import com.ruoyi.oc.utils.optimizer.EVData;
import org.apache.commons.math3.util.Precision;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.*;

// 此类包含用于此项目的各种helper function
public class Utils {
    private static final Logger logger = LogManager.getLogger(Utils.class);
    static InputStream stream = null;
    static SqlSessionFactoryBuilder builder = null;
    static SqlSessionFactory factory= null;
    static SqlSession sqlSession= null;
    public Utils() {
    }

    // 将输入数字限制在三位小数
    public String limitToThreeDecimal(double n) {
        return String.valueOf(Precision.round(n, 3));
    }


    // 返回：离输入数字最近的区间
    public double convertToProperTime(double t) {
        double interval = 0.25;
        return Math.round(t / interval) * interval;
    }

    // 返回：如果输入的时刻大于24小时，将其转为第二天的时刻
    public double convertTimeToNextDay(double currTime) {
        return (currTime > 24) ? (currTime - 24) : currTime;
    }

    // 检查输入的时刻是否在车辆返回与离开时刻内
    public boolean timeIsInRange(double currTime, double returnTime, double leaveTime) {
        // 当两个时刻在同一天
        if (leaveTime > returnTime) {
            return (currTime >= returnTime && currTime <= leaveTime);
        } else { // 第二个时刻于第二天
            return  ((currTime >= returnTime && currTime < 24) || (currTime >= 0 && currTime <= leaveTime));
        }
    }

    // 将输入的24小时制时间值转换为 "HH:mm:ss" 的时间格式
    public Time convertHoursToExactTime(double timeValue) {
        // Extracting hours, minutes, and seconds
        int hours = (int) timeValue;
        int minutes = (int) ((timeValue - hours) * 60);
        int seconds = (int) (((timeValue - hours) * 60 - minutes) * 60);
        String hourStr = String.valueOf(hours);
        String minStr = String.valueOf(minutes);
        String secStr = String.valueOf(seconds);

        // Creating a Time object
        return Time.valueOf(hourStr + ":" + minStr + ":" + secStr);
    }

    public void startSession() throws IOException {
        logger.info("Start loading data to database ... ");
        //1.加载核心配置文件的字节输入流
        stream = Resources.getResourceAsStream("mybatis.xml");
        //2.创建SqlSessionFactory的构建对象--框架使用的是构建者模式
        builder=new SqlSessionFactoryBuilder();
        //3.通过构建对象加载配置文件的输入流获取SqlSessionFactory
        factory=builder.build(stream);
        //4.通过工厂对象获取SqlSession对象----执行JDBC操作的
        sqlSession=factory.openSession();
    }

    public void closeSession() {
        sqlSession.commit();
        sqlSession.close();
    }

    public List<double[]> initializeSimulatedDailyLoad() {
        List<double[]> res = new ArrayList<>();

        for (double i = 0; i <= 15; i += 0.25) {
            double power = 120 * Math.cos(0.47 * i) + 640;
            res.add(new double[]{i, power});
        }

        for (double i = 15.25; i < 24; i += 0.25) {
            double power = 80 * Math.sin(0.385 * i) + 766;
            res.add(new double[]{i, power});
        }
        return res;
    }

    // 生成一天24小时的变压器记录
    public List<OcTransformerRecords> generateTransformerRecords(List<double[]> timeToDailyLoadList, Long tId, Long rcId) {
        List<OcTransformerRecords> res = new ArrayList<>();
        for (double[] timeToDailyLoad : timeToDailyLoadList) {
            OcTransformerRecords tr = new OcTransformerRecords();
            tr.setRcId(rcId);
            tr.setTransformerId(tId);
            int[] hms = this.momentToTime(timeToDailyLoad[0]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hms[0]);
            calendar.set(Calendar.MINUTE, hms[1]);
            calendar.set(Calendar.SECOND, hms[2]);
            tr.setCurrTime(calendar.getTime());
            tr.setCurrDailyLoad((long)timeToDailyLoad[1]);
            tr.setCurrChargingLoad(0L);
            res.add(tr);
        }
        return res;
    }

    // 将 Calendar 形式的时间转换为适合优化计算的 24 小时制的时刻
    public double dateToDouble(Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) / 60.0 +
            calendar.get(Calendar.SECOND) / 3600.0;
    }


    // 计算车辆现在的充电功率和所需充电时间 (h)
    public double[] calculateChargingParams(OcElectricalVehicleParameters evParams, int ifUseFastCharging,
                                      Long maxSOC, Long remainingSOC) {
        double chargingPower = ifUseFastCharging == 1 ? evParams.getFastChargingRate()
            : evParams.getNormalChargingRate();
        double percentage = (double) remainingSOC / 100;
        double loss = (double) maxSOC * (1 - percentage);
        double time = loss / chargingPower;
        return new double[]{chargingPower, time};
    }

    public double[] updateOptimizedSolution(List<double[]> timeToDailyLoad, List<EVData> evDataList,
                                            int EVNum, int timeNums, int evalTimes) {
        NondominatedPopulation result = new Executor()
            .withProblemClass(ChargingStrategy.class, timeToDailyLoad,
                evDataList, EVNum, timeNums)
            .withAlgorithm("OMOPSO")
            .withMaxEvaluations(evalTimes)
            .distributeOnAllCores()
            .run();

        // 所得solutions是优化过的电动汽车开始充电时间分布
        Solution solution = result.get(0);
        return EncodingUtils.getReal(solution);
    }

    // 将一个 24 小时制的 double value 转换为一个[hr, min, s]的int[]
    public int[] momentToTime(double timeValue) {
        int hour = (int) timeValue;
        int minutes = (int) ((timeValue - hour) * 60);
        int seconds = (int) (((timeValue - hour) * 60 - minutes) * 60);
        return new int[]{hour, minutes, seconds};
    }

    /**
     * 模拟在某一个变压器管理区域内有新车辆进行充电
     * */
    public OcChargingRecords generateChargingRecords(Long evId, Long csId, Long userId, Long tId,
                                                     int if_using_fast_charging, int if_using_orderly_charging,
                                                     Date charging_start_time, Date expected_leaving_time,
                                                     int is_finished, Long remainingSOC) {

        // need evId, csId, userId, tId, if_using_fast_charging, if_using_orderly_charging, charging_start_time,
        // expected_charging_end_time, is_finished, remaining SOC
        OcChargingRecords cr = new OcChargingRecords();
        cr.setEvId(evId);
        cr.setCsId(csId);
        cr.setUserId(userId);
        cr.setTId(tId);
        cr.setIfUsingFastCharging(if_using_fast_charging);
        cr.setIfUsingOrderlyCharging(if_using_orderly_charging);
        cr.setChargingStartTime(charging_start_time);
        cr.setExpectedLeavingTime(expected_leaving_time);
        cr.setIsFinished(is_finished);
        cr.setRemainingSoc(remainingSOC);
        return cr;
    }

    public Calendar generateRandomCalendarWithHours(int startHour, int endHour) {
        Calendar calendar = Calendar.getInstance();

        Random random = new Random();
        int hour = random.nextInt((endHour - startHour) + 1) + startHour;
        int min = random.nextInt(61);
        int seconds = random.nextInt(61);
        calendar.set(Calendar.HOUR_OF_DAY, hour); // Set the start hour
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    /**
     * 生成用户记录
     * */
    public OcUserRc generateOcUserRc(Long rc_id, Long tr_id) {
        OcUserRc urc = new OcUserRc();
        urc.setRcId(rc_id);
        urc.setTrId(tr_id);
        return urc;
    }

    public OcElectricalVehicle generateOcEV(Long userId) {
        Random random = new Random();
        int typeId = random.nextInt(5) + 1;
        OcElectricalVehicle ev = new OcElectricalVehicle();
        ev.setTypeId((long)typeId);
        ev.setUserId(userId);
        return ev;
    }


//    // 计算新的每个时刻对应的充电负荷
//    List<String[]> temp = new ArrayList<>();
//        for (double time = 0; time < 24; time += 0.25) {
//        double currPower = 0;
//        for (int j = 0; j < this.EVDatabase.size(); j++) {
//            EVData ev = this.EVDatabase.get(j);
//            double newEndTime = newStartTime[j] + ev.getChargingTime();
//            newEndTime = this.utils.convertTimeToNextDay(newEndTime);
//            if (this.utils.timeIsInRange(time, newStartTime[j], newEndTime)) {
//                currPower += ev.getChargingPower();
//            }
//        }
//        temp.add(new String[]{String.valueOf(time), String.valueOf(currPower)});
//    }
//        this.setTimeToChargingLoad(temp);
}
