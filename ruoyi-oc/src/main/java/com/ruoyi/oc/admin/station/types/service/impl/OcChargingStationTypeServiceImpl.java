package com.ruoyi.oc.admin.station.types.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.admin.station.types.domain.bo.OcChargingStationTypeBo;
import com.ruoyi.oc.admin.station.types.domain.vo.OcChargingStationTypeVo;
import com.ruoyi.oc.admin.station.types.domain.OcChargingStationType;
import com.ruoyi.oc.admin.station.types.mapper.OcChargingStationTypeMapper;
import com.ruoyi.oc.admin.station.types.service.IOcChargingStationTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 充电桩类型Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@RequiredArgsConstructor
@Service
public class OcChargingStationTypeServiceImpl implements IOcChargingStationTypeService {

    private final OcChargingStationTypeMapper baseMapper;

    /**
     * 查询充电桩类型
     */
    @Override
    public OcChargingStationTypeVo queryById(Long typeId){
        return baseMapper.selectVoById(typeId);
    }

    /**
     * 查询充电桩类型列表
     */
    @Override
    public TableDataInfo<OcChargingStationTypeVo> queryPageList(OcChargingStationTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcChargingStationType> lqw = buildQueryWrapper(bo);
        Page<OcChargingStationTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询充电桩类型列表
     */
    @Override
    public List<OcChargingStationTypeVo> queryList(OcChargingStationTypeBo bo) {
        LambdaQueryWrapper<OcChargingStationType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcChargingStationType> buildQueryWrapper(OcChargingStationTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcChargingStationType> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCsId() != null, OcChargingStationType::getCsId, bo.getCsId());
        lqw.eq(StringUtils.isNotBlank(bo.getManufacturer()), OcChargingStationType::getManufacturer, bo.getManufacturer());
        lqw.eq(StringUtils.isNotBlank(bo.getCompany()), OcChargingStationType::getCompany, bo.getCompany());
        return lqw;
    }

    /**
     * 新增充电桩类型
     */
    @Override
    public Boolean insertByBo(OcChargingStationTypeBo bo) {
        OcChargingStationType add = BeanUtil.toBean(bo, OcChargingStationType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTypeId(add.getTypeId());
        }
        return flag;
    }

    /**
     * 修改充电桩类型
     */
    @Override
    public Boolean updateByBo(OcChargingStationTypeBo bo) {
        OcChargingStationType update = BeanUtil.toBean(bo, OcChargingStationType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcChargingStationType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除充电桩类型
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
