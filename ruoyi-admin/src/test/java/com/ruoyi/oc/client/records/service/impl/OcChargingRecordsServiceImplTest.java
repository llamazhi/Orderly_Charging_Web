package com.ruoyi.oc.client.records.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.oc.admin.ev.entity.domain.OcElectricalVehicle;
import com.ruoyi.oc.admin.ev.entity.service.IOcElectricalVehicleService;
import com.ruoyi.oc.admin.user.domain.OcUserRc;
import com.ruoyi.oc.admin.user.service.IOcUserRcService;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;
import com.ruoyi.oc.client.records.service.IOcChargingRecordsService;
import com.ruoyi.oc.utils.Utils;
import com.ruoyi.oc.admin.transformer.records.domain.OcTransformerRecords;
import com.ruoyi.oc.admin.transformer.records.service.IOcTransformerRecordsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OcChargingRecordsServiceImplTest {

    @Resource
    private IOcTransformerRecordsService ocTransformerRecordsService;
    @Resource
    private IOcChargingRecordsService ocChargingRecordsService;
    @Resource
    private IOcUserRcService ocUserRcService;
    @Resource
    private IOcElectricalVehicleService ocElectricalVehicleService;

    private final static Utils utils = new Utils();

//    /**
//     * 造tr的记录
//     */
//    @Test
//    public void testTransformerDataGenerator() {
//        System.out.println("Start inserting data ... ");
//        // 生成数据
//        List<double[]> timeToDailyLoad = utils.initializeSimulatedDailyLoad();
//        List<OcTransformerRecords> loadsData = utils.generateTransformerRecords(timeToDailyLoad, 2L, 2L);
//        ocTransformerRecordsService.saveBatch(loadsData);
//        System.out.println("Insertion done");
//    }

//    /**
//     * 每5分钟插入一条新的 ChargingRecords 数据
//     * */
//    @Test
////    @Async
////    @Scheduled(cron="*/5 * * * *")
//    public void insertChargingRecords() {
//        Long evId = 2L;
//        Long csId = 1L;
//        Long userId = 1L;
//        Long tId = 1L;
//        int ifUsingFastCharging = 1;
//        int ifUsingOrderlyCharging = 1;
//        int isFinished = 1;
//        Long remainingSOC = 70L;
//
//        // 生成车辆开始充电时间
//        Calendar startCalendar = utils.generateRandomCalendarWithHours(18, 22);
//        // 生成车辆预期离开时间, 天数增加一天
//        Calendar endCalendar = utils.generateRandomCalendarWithHours(5, 8);
//        endCalendar.add(Calendar.DAY_OF_MONTH, 1);
//
//        // 生成 CR 实体
//        OcChargingRecords newCr = utils.generateChargingRecords(evId, csId, userId, tId, ifUsingFastCharging,
//            ifUsingOrderlyCharging, startCalendar.getTime(), endCalendar.getTime(), isFinished, remainingSOC);
//
//        ocChargingRecordsService.save(newCr);
//    }

//    @Test
//    public void insertUserRC() {
//        Long rcId = 1L;
//        Long tId = 1L;
//
//        int n = 100;
//        List<OcUserRc> urcList = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            urcList.add(utils.generateOcUserRc(rcId, tId));
//        }
//        ocUserRcService.saveBatch(urcList);
//    }

//    /**
//     * 假设每辆车只有一个用户
//     * */
//    @Test
//    public void insertEV() {
//        int n = 100;
//        List<OcElectricalVehicle> evList = new ArrayList<>();
//        for (int i = 1; i <= n; i++) {
//            evList.add(utils.generateOcEV((long)i));
//        }
//        ocElectricalVehicleService.saveBatch(evList);
//    }
//
//    @Test
//    public void insertChargingRecords() {
//        Random random = new Random();
//        Long csId = 1L;
//        Long tId = 2L;
//
//        // 随机生成本次要插入的数据量
//        int dataSize = 1 + random.nextInt(3);
//        // 取得目前电动车总量
//        List<OcElectricalVehicle> evList = ocElectricalVehicleService.list();
//        int evNums = evList.size();
//        int isFinished = 0; // 开始充电时默认未完成充电
//
//        // 根据已有的电动汽车查找已有的 charging_records，没有的话生成
//        List<OcChargingRecords> crList = ocChargingRecordsService.list();
//        List<Long> crEvIdList = new ArrayList<>();
//        for (OcChargingRecords currCr : crList) {
//            crEvIdList.add(currCr.getEvId());
//        }
//
//        List<OcChargingRecords> newCrList = new ArrayList<>();
//        for (int i = 0; i < dataSize; i++) {
//            // 随机从 ev 库里生成一个 evId
//            // 随机生成其他参数
//            // 如果 charging_records 里已有此 id, 重新生成
//            Long newEVId = (long) (random.nextInt(evNums) + 1);
//            while (crEvIdList.contains(newEVId)) {
//                newEVId = (long) (random.nextInt(evNums) + 1);
//            }
//            Long remainingSOC = (long) (10 + random.nextDouble() * (90 - 10 + 1));
//            int ifUsingFastCharging = random.nextInt(5) < 4 ? 1 : 0; // 假设 4/5 的人选择快充
//            int ifUsingOrderlyCharging = random.nextInt(5) < 4 ? 1 : 0; // 假设 4/5 的人选择有序充电
//            // 生成车辆开始充电时间
//            Calendar startCalendar = utils.generateRandomCalendarWithHours(18, 22);
//            // 生成车辆预期离开时间, 天数增加一天
//            Calendar endCalendar = utils.generateRandomCalendarWithHours(5, 8);
//            endCalendar.add(Calendar.DAY_OF_MONTH, 1);
//
//            // 生成 CR 实体
//            OcChargingRecords newCr = utils.generateChargingRecords(newEVId, csId, newEVId, tId, ifUsingFastCharging,
//                ifUsingOrderlyCharging, startCalendar.getTime(), endCalendar.getTime(), isFinished, remainingSOC);
//            newCrList.add(newCr);
//        }
//        ocChargingRecordsService.saveBatch(newCrList);
//    }

//    @Test
//    public void insertTransformerRecords() {
//        System.out.println("Adding new load data ... ");
//        List<double[]> dailyLoad = utils.initializeSimulatedDailyLoad();
//        List<OcTransformerRecords> trList = utils.generateTransformerRecords(dailyLoad, 1L, 1L);
//        ocTransformerRecordsService.saveBatch(trList);
//    }

//    @Test
//    public void testTrToday() {
//        QueryWrapper<OcTransformerRecords> trToday = new QueryWrapper<>();
//        trToday.apply("DATE(curr_time) = {0}", LocalDate.now());
//        List<OcTransformerRecords> trList =  ocTransformerRecordsService.list(trToday);
//        System.out.println("curr tr list size: " + trList.size());
//    }

}
