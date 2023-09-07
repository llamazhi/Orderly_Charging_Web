package com.ruoyi.oc.admin.ev.params.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;
import com.ruoyi.oc.client.records.mapper.OcChargingRecordsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.admin.ev.params.domain.bo.OcElectricalVehicleParametersBo;
import com.ruoyi.oc.admin.ev.params.domain.vo.OcElectricalVehicleParametersVo;
import com.ruoyi.oc.admin.ev.params.domain.OcElectricalVehicleParameters;
import com.ruoyi.oc.admin.ev.params.mapper.OcElectricalVehicleParametersMapper;
import com.ruoyi.oc.admin.ev.params.service.IOcElectricalVehicleParametersService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 新能源车参数Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@RequiredArgsConstructor
@Service
public class OcElectricalVehicleParametersServiceImpl extends ServiceImpl<OcElectricalVehicleParametersMapper,
    OcElectricalVehicleParameters> implements IOcElectricalVehicleParametersService {

    private final OcElectricalVehicleParametersMapper baseMapper;

    /**
     * 查询新能源车参数
     */
    @Override
    public OcElectricalVehicleParametersVo queryById(Long typeId){
        return baseMapper.selectVoById(typeId);
    }

    /**
     * 查询新能源车参数列表
     */
    @Override
    public TableDataInfo<OcElectricalVehicleParametersVo> queryPageList(OcElectricalVehicleParametersBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcElectricalVehicleParameters> lqw = buildQueryWrapper(bo);
        Page<OcElectricalVehicleParametersVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询新能源车参数列表
     */
    @Override
    public List<OcElectricalVehicleParametersVo> queryList(OcElectricalVehicleParametersBo bo) {
        LambdaQueryWrapper<OcElectricalVehicleParameters> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcElectricalVehicleParameters> buildQueryWrapper(OcElectricalVehicleParametersBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcElectricalVehicleParameters> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMaxSoc() != null, OcElectricalVehicleParameters::getMaxSoc, bo.getMaxSoc());
        lqw.eq(bo.getNormalChargingRate() != null, OcElectricalVehicleParameters::getNormalChargingRate, bo.getNormalChargingRate());
        lqw.eq(bo.getFastChargingRate() != null, OcElectricalVehicleParameters::getFastChargingRate, bo.getFastChargingRate());
        return lqw;
    }

    /**
     * 新增新能源车参数
     */
    @Override
    public Boolean insertByBo(OcElectricalVehicleParametersBo bo) {
        OcElectricalVehicleParameters add = BeanUtil.toBean(bo, OcElectricalVehicleParameters.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTypeId(add.getTypeId());
        }
        return flag;
    }

    /**
     * 修改新能源车参数
     */
    @Override
    public Boolean updateByBo(OcElectricalVehicleParametersBo bo) {
        OcElectricalVehicleParameters update = BeanUtil.toBean(bo, OcElectricalVehicleParameters.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcElectricalVehicleParameters entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除新能源车参数
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
