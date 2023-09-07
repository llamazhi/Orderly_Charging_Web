package com.ruoyi.oc.admin.station.entity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.admin.station.entity.domain.bo.OcChargingStationBo;
import com.ruoyi.oc.admin.station.entity.domain.vo.OcChargingStationVo;
import com.ruoyi.oc.admin.station.entity.domain.OcChargingStation;
import com.ruoyi.oc.admin.station.entity.mapper.OcChargingStationMapper;
import com.ruoyi.oc.admin.station.entity.service.IOcChargingStationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 充电桩Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@RequiredArgsConstructor
@Service
public class OcChargingStationServiceImpl implements IOcChargingStationService {

    private final OcChargingStationMapper baseMapper;

    /**
     * 查询充电桩
     */
    @Override
    public OcChargingStationVo queryById(Long csId){
        return baseMapper.selectVoById(csId);
    }

    /**
     * 查询充电桩列表
     */
    @Override
    public TableDataInfo<OcChargingStationVo> queryPageList(OcChargingStationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcChargingStation> lqw = buildQueryWrapper(bo);
        Page<OcChargingStationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询充电桩列表
     */
    @Override
    public List<OcChargingStationVo> queryList(OcChargingStationBo bo) {
        LambdaQueryWrapper<OcChargingStation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcChargingStation> buildQueryWrapper(OcChargingStationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcChargingStation> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTransformerId() != null, OcChargingStation::getTransformerId, bo.getTransformerId());
        lqw.eq(bo.getRcId() != null, OcChargingStation::getRcId, bo.getRcId());
        lqw.eq(bo.getTypeId() != null, OcChargingStation::getTypeId, bo.getTypeId());
        lqw.eq(bo.getExpiringDate() != null, OcChargingStation::getExpiringDate, bo.getExpiringDate());
        return lqw;
    }

    /**
     * 新增充电桩
     */
    @Override
    public Boolean insertByBo(OcChargingStationBo bo) {
        OcChargingStation add = BeanUtil.toBean(bo, OcChargingStation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCsId(add.getCsId());
        }
        return flag;
    }

    /**
     * 修改充电桩
     */
    @Override
    public Boolean updateByBo(OcChargingStationBo bo) {
        OcChargingStation update = BeanUtil.toBean(bo, OcChargingStation.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcChargingStation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除充电桩
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
