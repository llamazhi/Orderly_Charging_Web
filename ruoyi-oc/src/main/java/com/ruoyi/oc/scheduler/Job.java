package com.ruoyi.oc.scheduler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.oc.admin.ev.entity.domain.OcElectricalVehicle;
import com.ruoyi.oc.admin.ev.entity.mapper.OcElectricalVehicleMapper;
import com.ruoyi.oc.admin.ev.entity.service.IOcElectricalVehicleService;
import com.ruoyi.oc.admin.ev.params.domain.OcElectricalVehicleParameters;
import com.ruoyi.oc.admin.ev.params.service.IOcElectricalVehicleParametersService;
import com.ruoyi.oc.admin.transformer.records.domain.OcTransformerRecords;
import com.ruoyi.oc.admin.transformer.records.service.IOcTransformerRecordsService;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;
import com.ruoyi.oc.client.records.service.IOcChargingRecordsService;
import com.ruoyi.oc.utils.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class Job {
    private static final Utils utils = new Utils();
    @Resource
    private IOcChargingRecordsService ocChargingRecordsService;
    @Resource
    private IOcElectricalVehicleService ocElectricalVehicleService;
    @Resource
    private IOcTransformerRecordsService ocTransformerRecordsService;

    /**
     * 每5分钟插入一条新的 ChargingRecords 数据
     * */
//    @Async
//    @Scheduled(cron="0/5 * * * * ?")
//    public void test() {
//        System.out.println("sdfasdfasfasdf");
//    }

    /**
     * 每5分钟插入一条新的 ChargingRecords 数据
     *
     * prepare
     * 1.生成100個用戶 1-100
     * 2.給這100個用戶生成ev，1  - 1   1 - 多
     * 3.插入5条已有车辆数据
     * process
     * 1. 5分鐘執行一次
     * 2. 首先得查出來當前已經生成的數據、和所有的ev，找到沒有生成
     * 3. 隨機生成兩三個或一個隨便，充电记录，就当是用户来充电了嘛
     * 4. 加一个 new_charging_start_time 字段
     *    查出此字段是否为空，若为空，则还未优化
     * 5. 检查是否现在为适合生成数据的时段：假设 9 - 17 为空跑时段
     * 6. 优化后，更新时段内充电负荷
     *
     * 假设一个小区有 100 名用户，每名用户有一辆电动汽车
     * */
    @Async
    @Scheduled(cron="0 */5 * ? * *")
    public void insertChargingRecords() {
        System.out.println("Data generation is working ... ");
        Random random = new Random();
        Long csId = 1L;
        Long tId = 2L;

        // 随机生成本次要插入的数据量
        int dataSize = 1 + random.nextInt(3);
        // 取得目前电动车总量
        List<OcElectricalVehicle> evList = ocElectricalVehicleService.list();
        int evNums = evList.size();
        int isFinished = 0; // 开始充电时默认未完成充电

        // 根据已有的电动汽车查找已有的 charging_records，没有的话生成
        List<OcChargingRecords> crList = ocChargingRecordsService.list();
        List<Long> crEvIdList = new ArrayList<>();
        for (OcChargingRecords currCr : crList) {
            crEvIdList.add(currCr.getEvId());
        }

        List<OcChargingRecords> newCrList = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            // 随机从 ev 库里生成一个 evId
            // 随机生成其他参数
            // 如果 charging_records 里已有此 id, 重新生成
            Long newEVId = (long) (random.nextInt(evNums) + 1);
            while (crEvIdList.contains(newEVId)) {
                newEVId = (long) (random.nextInt(evNums) + 1);
            }
            Long remainingSOC = (long) (10 + random.nextDouble() * (90 - 10 + 1));
            int ifUsingFastCharging = random.nextInt(5) < 4 ? 1 : 0; // 假设 4/5 的人选择快充
            int ifUsingOrderlyCharging = random.nextInt(5) < 4 ? 1 : 0; // 假设 4/5 的人选择有序充电
            // 生成车辆开始充电时间
            Calendar startCalendar = utils.generateRandomCalendarWithHours(17, 22);
            // 生成车辆预期离开时间, 天数增加一天
            Calendar endCalendar = utils.generateRandomCalendarWithHours(5, 8);
            endCalendar.add(Calendar.DAY_OF_MONTH, 1);

            // 生成 CR 实体
            OcChargingRecords newCr = utils.generateChargingRecords(newEVId, csId, newEVId, tId, ifUsingFastCharging,
                ifUsingOrderlyCharging, startCalendar.getTime(), endCalendar.getTime(), isFinished, remainingSOC);
            newCrList.add(newCr);
        }
        ocChargingRecordsService.saveBatch(newCrList);
    }

    @Async
    @Scheduled(cron="0 0 0 ? * *")
    public void insertTransformerRecords() {
        System.out.println("Adding new load data ... ");
        List<double[]> dailyLoad = utils.initializeSimulatedDailyLoad();
        List<OcTransformerRecords> trList = utils.generateTransformerRecords(dailyLoad, 1L, 1L);
        ocTransformerRecordsService.saveBatch(trList);
    }

}
