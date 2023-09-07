package com.ruoyi.oc.admin.transformer.records.service.impl;

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
import com.ruoyi.oc.admin.transformer.records.domain.bo.OcTransformerRecordsBo;
import com.ruoyi.oc.admin.transformer.records.domain.vo.OcTransformerRecordsVo;
import com.ruoyi.oc.admin.transformer.records.domain.OcTransformerRecords;
import com.ruoyi.oc.admin.transformer.records.mapper.OcTransformerRecordsMapper;
import com.ruoyi.oc.admin.transformer.records.service.IOcTransformerRecordsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 变压器记录Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-29
 */
@RequiredArgsConstructor
@Service
public class OcTransformerRecordsServiceImpl extends ServiceImpl<OcTransformerRecordsMapper, OcTransformerRecords>
    implements IOcTransformerRecordsService {

    private final OcTransformerRecordsMapper baseMapper;

    /**
     * 查询变压器记录
     */
    @Override
    public OcTransformerRecordsVo queryById(Long recordId){
        return baseMapper.selectVoById(recordId);
    }

    /**
     * 查询变压器记录列表
     */
    @Override
    public TableDataInfo<OcTransformerRecordsVo> queryPageList(OcTransformerRecordsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcTransformerRecords> lqw = buildQueryWrapper(bo);
        Page<OcTransformerRecordsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询变压器记录列表
     */
    @Override
    public List<OcTransformerRecordsVo> queryList(OcTransformerRecordsBo bo) {
        LambdaQueryWrapper<OcTransformerRecords> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcTransformerRecords> buildQueryWrapper(OcTransformerRecordsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcTransformerRecords> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getRcId() != null, OcTransformerRecords::getRcId, bo.getRcId());
        lqw.eq(bo.getCurrTime() != null, OcTransformerRecords::getCurrTime, bo.getCurrTime());
        lqw.eq(bo.getCurrChargingLoad() != null, OcTransformerRecords::getCurrChargingLoad, bo.getCurrChargingLoad());
        lqw.eq(bo.getTransformerId() != null, OcTransformerRecords::getTransformerId, bo.getTransformerId());
        lqw.eq(bo.getCurrDailyLoad() != null, OcTransformerRecords::getCurrDailyLoad, bo.getCurrDailyLoad());
        return lqw;
    }

    /**
     * 新增变压器记录
     */
    @Override
    public Boolean insertByBo(OcTransformerRecordsBo bo) {
        OcTransformerRecords add = BeanUtil.toBean(bo, OcTransformerRecords.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRecordId(add.getRecordId());
        }
        return flag;
    }

    /**
     * 修改变压器记录
     */
    @Override
    public Boolean updateByBo(OcTransformerRecordsBo bo) {
        OcTransformerRecords update = BeanUtil.toBean(bo, OcTransformerRecords.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcTransformerRecords entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除变压器记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
