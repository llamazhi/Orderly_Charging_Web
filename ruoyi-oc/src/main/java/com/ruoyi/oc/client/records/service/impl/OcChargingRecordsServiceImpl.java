package com.ruoyi.oc.client.records.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.oc.utils.Utils;
import com.ruoyi.oc.admin.ev.entity.domain.OcElectricalVehicle;
import com.ruoyi.oc.admin.ev.entity.mapper.OcElectricalVehicleMapper;
import com.ruoyi.oc.admin.ev.params.domain.OcElectricalVehicleParameters;
import com.ruoyi.oc.admin.ev.params.mapper.OcElectricalVehicleParametersMapper;
import com.ruoyi.oc.admin.transformer.records.domain.OcTransformerRecords;
import com.ruoyi.oc.admin.transformer.records.service.IOcTransformerRecordsService;
import com.ruoyi.oc.utils.optimizer.EVData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.client.records.domain.bo.OcChargingRecordsBo;
import com.ruoyi.oc.client.records.domain.vo.OcChargingRecordsVo;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;
import com.ruoyi.oc.client.records.mapper.OcChargingRecordsMapper;
import com.ruoyi.oc.client.records.service.IOcChargingRecordsService;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 充电记录Service业务层处理
 *
 * spring
 *
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@RequiredArgsConstructor
@Service
public class OcChargingRecordsServiceImpl extends ServiceImpl<OcChargingRecordsMapper, OcChargingRecords>
    implements IOcChargingRecordsService {

    private final OcChargingRecordsMapper baseMapper;
    private final OcElectricalVehicleMapper evMapper;
    private final OcElectricalVehicleParametersMapper evpMapper;
    @Resource
    private IOcTransformerRecordsService ocTransformerRecordsService;

    private static final int TIME_NUMS = 96;
    private static final int MAX_EVAL = 100000;
    private static final Utils utils = new Utils();

    /**
     * 查询充电记录
     */
    @Override
    public OcChargingRecordsVo queryById(Long recordId){
        return baseMapper.selectVoById(recordId);
    }

    /**
     * 查询充电记录列表
     */
    @Override
    public TableDataInfo<OcChargingRecordsVo> queryPageList(OcChargingRecordsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcChargingRecords> lqw = buildQueryWrapper(bo);
        Page<OcChargingRecordsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询充电记录列表
     */
    @Override
    public List<OcChargingRecordsVo> queryList(OcChargingRecordsBo bo) {
        LambdaQueryWrapper<OcChargingRecords> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcChargingRecords> buildQueryWrapper(OcChargingRecordsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcChargingRecords> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getEvId() != null, OcChargingRecords::getEvId, bo.getEvId());
        lqw.eq(bo.getCsId() != null, OcChargingRecords::getCsId, bo.getCsId());
        lqw.eq(bo.getUserId() != null, OcChargingRecords::getUserId, bo.getUserId());
        lqw.eq(bo.getTId() != null, OcChargingRecords::getTId, bo.getTId());
        lqw.eq(bo.getIfUsingFastCharging() != null, OcChargingRecords::getIfUsingFastCharging, bo.getIfUsingFastCharging());
        lqw.eq(bo.getRemainingSoc() != null, OcChargingRecords::getRemainingSoc, bo.getRemainingSoc());
        lqw.eq(bo.getExpectedChargingEndTime() != null, OcChargingRecords::getExpectedChargingEndTime, bo.getExpectedChargingEndTime());
        lqw.eq(bo.getChargingStartTime() != null, OcChargingRecords::getChargingStartTime, bo.getChargingStartTime());
        lqw.eq(bo.getIfUsingOrderlyCharging() != null, OcChargingRecords::getIfUsingOrderlyCharging, bo.getIfUsingOrderlyCharging());
        return lqw;
    }

    /**
     * 新增充电记录
     */
    @Override
    public Boolean insertByBo(OcChargingRecordsBo bo) {
        OcChargingRecords add = BeanUtil.toBean(bo, OcChargingRecords.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRecordId(add.getRecordId());
        }
        return flag;
    }

    /**
     * 修改充电记录
     */
    @Override
    public Boolean updateByBo(OcChargingRecordsBo bo) {
        OcChargingRecords update = BeanUtil.toBean(bo, OcChargingRecords.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcChargingRecords entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除充电记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 演示用接口
     */
    public String optimize() {
        // 取得目前所有的 需要优化的 charging_records 数据
        // 筛选出所有还未得到优化解的数据
        QueryWrapper<OcChargingRecords> ocAndNotOptimized = new QueryWrapper<>();
        ocAndNotOptimized.eq("if_using_orderly_charging", 1).isNull("new_charging_start_time");
        List<OcChargingRecords> crList = this.list(ocAndNotOptimized);
        optimizeNewEVs(crList);

        StringBuilder ans = new StringBuilder();
        ans.append("本次优化车辆信息如下： \n");
        for (OcChargingRecords cr : crList) {
            ans.append("{ ").
                append("车辆编号：").append(cr.getEvId()).append("\n").
                append("预计离开时间: ").append(cr.getExpectedLeavingTime().toString()).append("\n").
                append("优化前充电开始时间: ").append(cr.getChargingStartTime().toString()).append("\n").
                append("优化后充电开始时间: ").append(cr.getNewChargingStartTime().toString()).append("\n").
                append("预计充电结束时间: ").append(cr.getExpectedChargingEndTime().toString()).
                append("}").append("\n");
        }
        return ans.toString();
    }

    /**
     * 用户用接口
     */
    // 优化充电顺序
    public String optimize(Long evId) {
        // 首先查询出不需要优化充电的车辆，直接计算他们的预期充电结束时间
        List<OcChargingRecords> nonOptimizedList = setNonOptimizedData(evId);
        if (nonOptimizedList.size() != 0) return "车辆将于 " +
            nonOptimizedList.get(0).getChargingStartTime().toString() + " 开始充电";
        else {
            // 查找输入 evId 是否已被优化过；如有，输出优化后时间
            QueryWrapper<OcChargingRecords> optimizedWithEvId = new QueryWrapper<>();
            optimizedWithEvId.eq("ev_id", evId).isNotNull("new_charging_start_time");
            List<OcChargingRecords> ans = this.list(optimizedWithEvId);
            if (ans.size() != 0) return "车辆经过优化后将于 " +
                ans.get(0).getNewChargingStartTime().toString() + " 开始充电";

            // 取得目前所有的 需要优化的 charging_records 数据
            // 筛选出所有还未得到优化解的数据
            QueryWrapper<OcChargingRecords> ocAndNotOptimized = new QueryWrapper<>();
            ocAndNotOptimized.eq("if_using_orderly_charging", 1).isNull("new_charging_start_time");
            List<OcChargingRecords> crList = this.list(ocAndNotOptimized);
            optimizeNewEVs(crList);

            QueryWrapper<OcChargingRecords> ansWrapper = new QueryWrapper<>();
            ansWrapper.eq("ev_id", evId);
            Date ansDate = this.getOne(ansWrapper).getNewChargingStartTime();
            return "车辆经过优化后将于 " + ansDate.toString() + " 开始充电";
        }
    }

    private void optimizeNewEVs(List<OcChargingRecords> crList) {
        // 给选中的充电记录建立新的EVData
        // params: oldStartTime, leavingTime, chargingTime
        // to calculate chargingTime, need maxSOC & remainingSOC
        List<EVData> evDataList = new ArrayList<>();
        for (OcChargingRecords cr : crList) {
            EVData evData = new EVData();

            // 查询充电记录
            // 取得本次充电起始和结束时间
            Date chargingStartTime = cr.getChargingStartTime();
            Date expectedLeavingTime = cr.getExpectedLeavingTime();
            double[] chargingParams = calculateChargingParams(cr);
            double chargingPower = chargingParams[0];
            double chargingTime = chargingParams[1];

            // 注意：现阶段关于时间计算都是使用double类型的值
            // 以下将 Date 转为形式为24小时 double 值
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(chargingStartTime);
            Calendar expectedLeavingCalendar = Calendar.getInstance();
            expectedLeavingCalendar.setTime(expectedLeavingTime);

            double chargingStartTimeVal = utils.dateToDouble(startCalendar);
            double expectedLeavingTimeVal = utils.dateToDouble(expectedLeavingCalendar);

            // set values of EVData
            evData.setChargingTime(chargingTime);
            evData.setChargingStartTime(chargingStartTimeVal);
            evData.setLeavingTime(expectedLeavingTimeVal);
            evData.setChargingPower(chargingPower);
            evDataList.add(evData);
        }

        // 需要取得模拟用电负荷数据，之后再转换成List<double[]> ([time, load]) 形式
        QueryWrapper<OcTransformerRecords> trToday = new QueryWrapper<>();
        trToday.apply("DATE(curr_time) = {0}", LocalDate.now());
        List<OcTransformerRecords> trList =  ocTransformerRecordsService.list(trToday);
//        System.out.println("curr tr list size: " + trList.size());

        List<double[]> timeToDailyLoad = new ArrayList<>();
        for (OcTransformerRecords tr : trList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tr.getCurrTime());
            double time = utils.dateToDouble(calendar);
            double dailyLoad = (double) tr.getCurrDailyLoad();
            double chargingLoad = (double) tr.getCurrChargingLoad();
            timeToDailyLoad.add(new double[]{time, dailyLoad + chargingLoad});
        }

        // 有序优化
        // 算法所得解为一个 double[] (新的充电开始时间分布) BigDecimal
        double[] newStartTime = utils.updateOptimizedSolution(timeToDailyLoad, evDataList, evDataList.size(),
            TIME_NUMS, MAX_EVAL);

        for (int i = 0; i < evDataList.size(); i++) {
            int[] hms = utils.momentToTime(newStartTime[i]);
            Calendar startCalendar = Calendar.getInstance();
            setCalendarTime(startCalendar, hms);

            // 如果得到新的时间小于旧的时间，则默认得到第二天时刻，给 Calendar 加一天
            if (newStartTime[i] < evDataList.get(i).getChargingStartTime()) {
                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            // 假设离开时间为第二天
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.add(Calendar.DAY_OF_MONTH, 1);
            double endTime = newStartTime[i] + evDataList.get(i).getChargingTime();
            endTime = utils.convertTimeToNextDay(endTime);
            int[] endHms = utils.momentToTime(endTime);
            setCalendarTime(endCalendar, endHms);
            crList.get(i).setNewChargingStartTime(startCalendar.getTime());
            crList.get(i).setExpectedChargingEndTime(endCalendar.getTime());
        }

        // 更新 charging_records 数据库
        updateBatchById(crList);

        // 更新 transformer_records 数据库
        updateTransformRecords(trList, evDataList, newStartTime);
    }

    private List<OcChargingRecords> setNonOptimizedData(Long evId) {
        // 查找所有不需要优化的 evId
        QueryWrapper<OcChargingRecords> nonOptimized = new QueryWrapper<>();
        nonOptimized.eq("ev_id", evId).eq("if_using_orderly_charging", 0);
        List<OcChargingRecords> crList = this.list(nonOptimized);

        double[] startTimeArr = new double[crList.size()];
        double[] chargingTimeArr = new double[crList.size()];
        double[] chargingPowerArr = new double[crList.size()];
        for (int i = 0; i < crList.size(); i++) {
            OcChargingRecords cr = crList.get(i);
            Date chargingStartTime = cr.getChargingStartTime();
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(chargingStartTime);

            double[] chargingParams = calculateChargingParams(cr);
            double chargingPower = chargingParams[0];
            chargingPowerArr[i] = chargingPower;
            double chargingTime = chargingParams[1];
            chargingTimeArr[i] = chargingTime;
            double startTime = utils.dateToDouble(startCalendar);
            startTimeArr[i] = startTime;
            double expectedChargingEndTime = utils.dateToDouble(startCalendar) + chargingTime;
            expectedChargingEndTime = utils.convertTimeToNextDay(expectedChargingEndTime);
            int[] hms = utils.momentToTime(expectedChargingEndTime);
            setCalendarTime(startCalendar, hms);

            if (expectedChargingEndTime < startTime) {
                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            cr.setExpectedChargingEndTime(startCalendar.getTime());
        }
        this.updateBatchById(crList);

        QueryWrapper<OcTransformerRecords> trToday = new QueryWrapper<>();
        trToday.apply("DATE(curr_time) = {0}", LocalDate.now());
        List<OcTransformerRecords> trList =  ocTransformerRecordsService.list(trToday);
        for (double t = 0; t < 24; t += 0.25) {
            double olderPower = trList.get((int)(t * 4)).getCurrChargingLoad();
            double currPower = 0;
            for (int i = 0; i < crList.size(); i++) {
                double endTime = startTimeArr[i] + chargingTimeArr[i];
                endTime = utils.convertTimeToNextDay(endTime);
                if (utils.timeIsInRange(t, startTimeArr[i], endTime)) {
                    currPower += chargingPowerArr[i];
                }
            }
            trList.get((int)(t * 4)).setCurrChargingLoad((long)(currPower + olderPower));
        }
        ocTransformerRecordsService.updateBatchById(trList);
        return crList;
    }

    private double[] calculateChargingParams(OcChargingRecords cr) {
        Integer ifUseFastCharging = cr.getIfUsingFastCharging();
        Long EVId = cr.getEvId();
        OcElectricalVehicle EV = evMapper.selectById(EVId);
        Long EVTypeId = EV.getTypeId();
        OcElectricalVehicleParameters EVParams = evpMapper.selectById(EVTypeId);
        Long maxSOC = EVParams.getMaxSoc();
        Long remainingSOC = cr.getRemainingSoc(); // 假设是以%形式
        return utils.calculateChargingParams(EVParams, ifUseFastCharging, maxSOC, remainingSOC);
    }

    private void setCalendarTime(Calendar oldCalendar, int[] hms) {
        oldCalendar.set(Calendar.HOUR_OF_DAY, hms[0]);
        oldCalendar.set(Calendar.MINUTE, hms[1]);
        oldCalendar.set(Calendar.SECOND, hms[2]);
    }

    // 更新 transformer_records 数据库
    private void updateTransformRecords(List<OcTransformerRecords> trList, List<EVData> evDataList, double[] startTime) {
        for (double t = 0; t < 24; t += 0.25) {
            double olderPower = trList.get((int)(t * 4)).getCurrChargingLoad();
            double currPower = 0;
            for (int i = 0; i < evDataList.size(); i++) {
                double endTime = startTime[i] + evDataList.get(i).getChargingTime();
                endTime = utils.convertTimeToNextDay(endTime);
                if (utils.timeIsInRange(t, startTime[i], endTime)) {
                    currPower += evDataList.get(i).getChargingPower();
                }
            }
            trList.get((int)(t * 4)).setCurrChargingLoad((long)(currPower + olderPower));
        }
        ocTransformerRecordsService.updateBatchById(trList);
    }
}
