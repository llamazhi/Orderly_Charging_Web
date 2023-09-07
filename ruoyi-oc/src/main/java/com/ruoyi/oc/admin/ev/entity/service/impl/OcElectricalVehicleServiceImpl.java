package com.ruoyi.oc.admin.ev.entity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.admin.ev.entity.domain.bo.OcElectricalVehicleBo;
import com.ruoyi.oc.admin.ev.entity.domain.vo.OcElectricalVehicleVo;
import com.ruoyi.oc.admin.ev.entity.domain.OcElectricalVehicle;
import com.ruoyi.oc.admin.ev.entity.mapper.OcElectricalVehicleMapper;
import com.ruoyi.oc.admin.ev.entity.service.IOcElectricalVehicleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 新能源车Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@RequiredArgsConstructor
@Service
public class OcElectricalVehicleServiceImpl extends ServiceImpl<OcElectricalVehicleMapper, OcElectricalVehicle>
    implements IOcElectricalVehicleService {

    private final OcElectricalVehicleMapper baseMapper;

    /**
     * 查询新能源车
     */
    @Override
    public OcElectricalVehicleVo queryById(Long evId){
        return baseMapper.selectVoById(evId);
    }

    /**
     * 查询新能源车列表
     */
    @Override
    public TableDataInfo<OcElectricalVehicleVo> queryPageList(OcElectricalVehicleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcElectricalVehicle> lqw = buildQueryWrapper(bo);
        Page<OcElectricalVehicleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询新能源车列表
     */
    @Override
    public List<OcElectricalVehicleVo> queryList(OcElectricalVehicleBo bo) {
        LambdaQueryWrapper<OcElectricalVehicle> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcElectricalVehicle> buildQueryWrapper(OcElectricalVehicleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcElectricalVehicle> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTypeId() != null, OcElectricalVehicle::getTypeId, bo.getTypeId());
        lqw.eq(bo.getUserId() != null, OcElectricalVehicle::getUserId, bo.getUserId());
        return lqw;
    }

    /**
     * 新增新能源车
     */
    @Override
    public Boolean insertByBo(OcElectricalVehicleBo bo) {
        OcElectricalVehicle add = BeanUtil.toBean(bo, OcElectricalVehicle.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setEvId(add.getEvId());
        }
        return flag;
    }

    /**
     * 修改新能源车
     */
    @Override
    public Boolean updateByBo(OcElectricalVehicleBo bo) {
        OcElectricalVehicle update = BeanUtil.toBean(bo, OcElectricalVehicle.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcElectricalVehicle entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除新能源车
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
