package com.ruoyi.oc.admin.rc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.admin.rc.domain.bo.OcResidentialCommunityBo;
import com.ruoyi.oc.admin.rc.domain.vo.OcResidentialCommunityVo;
import com.ruoyi.oc.admin.rc.domain.OcResidentialCommunity;
import com.ruoyi.oc.admin.rc.mapper.OcResidentialCommunityMapper;
import com.ruoyi.oc.admin.rc.service.IOcResidentialCommunityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 小区Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@RequiredArgsConstructor
@Service
public class OcResidentialCommunityServiceImpl implements IOcResidentialCommunityService {

    private final OcResidentialCommunityMapper baseMapper;

    /**
     * 查询小区
     */
    @Override
    public OcResidentialCommunityVo queryById(Long rcId){
        return baseMapper.selectVoById(rcId);
    }

    /**
     * 查询小区列表
     */
    @Override
    public TableDataInfo<OcResidentialCommunityVo> queryPageList(OcResidentialCommunityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcResidentialCommunity> lqw = buildQueryWrapper(bo);
        Page<OcResidentialCommunityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询小区列表
     */
    @Override
    public List<OcResidentialCommunityVo> queryList(OcResidentialCommunityBo bo) {
        LambdaQueryWrapper<OcResidentialCommunity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcResidentialCommunity> buildQueryWrapper(OcResidentialCommunityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcResidentialCommunity> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getRcName()), OcResidentialCommunity::getRcName, bo.getRcName());
        return lqw;
    }

    /**
     * 新增小区
     */
    @Override
    public Boolean insertByBo(OcResidentialCommunityBo bo) {
        OcResidentialCommunity add = BeanUtil.toBean(bo, OcResidentialCommunity.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRcId(add.getRcId());
        }
        return flag;
    }

    /**
     * 修改小区
     */
    @Override
    public Boolean updateByBo(OcResidentialCommunityBo bo) {
        OcResidentialCommunity update = BeanUtil.toBean(bo, OcResidentialCommunity.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcResidentialCommunity entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除小区
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
