package com.ruoyi.oc.admin.transformer.entity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.admin.transformer.entity.domain.bo.OcTransformerBo;
import com.ruoyi.oc.admin.transformer.entity.domain.vo.OcTransformerVo;
import com.ruoyi.oc.admin.transformer.entity.domain.OcTransformer;
import com.ruoyi.oc.admin.transformer.entity.mapper.OcTransformerMapper;
import com.ruoyi.oc.admin.transformer.entity.service.IOcTransformerService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 变压器Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@RequiredArgsConstructor
@Service
public class OcTransformerServiceImpl implements IOcTransformerService {

    private final OcTransformerMapper baseMapper;

    /**
     * 查询变压器
     */
    @Override
    public OcTransformerVo queryById(Long transformerId){
        return baseMapper.selectVoById(transformerId);
    }

    /**
     * 查询变压器列表
     */
    @Override
    public TableDataInfo<OcTransformerVo> queryPageList(OcTransformerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcTransformer> lqw = buildQueryWrapper(bo);
        Page<OcTransformerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询变压器列表
     */
    @Override
    public List<OcTransformerVo> queryList(OcTransformerBo bo) {
        LambdaQueryWrapper<OcTransformer> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcTransformer> buildQueryWrapper(OcTransformerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcTransformer> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getRcId() != null, OcTransformer::getRcId, bo.getRcId());
        lqw.like(StringUtils.isNotBlank(bo.getTName()), OcTransformer::getTName, bo.getTName());
        lqw.eq(bo.getTMax() != null, OcTransformer::getTMax, bo.getTMax());
        return lqw;
    }

    /**
     * 新增变压器
     */
    @Override
    public Boolean insertByBo(OcTransformerBo bo) {
        OcTransformer add = BeanUtil.toBean(bo, OcTransformer.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTransformerId(add.getTransformerId());
        }
        return flag;
    }

    /**
     * 修改变压器
     */
    @Override
    public Boolean updateByBo(OcTransformerBo bo) {
        OcTransformer update = BeanUtil.toBean(bo, OcTransformer.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcTransformer entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除变压器
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
