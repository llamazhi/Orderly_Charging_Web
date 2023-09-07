package com.ruoyi.oc.admin.user.service.impl;

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
import com.ruoyi.oc.admin.user.domain.bo.OcUserRcBo;
import com.ruoyi.oc.admin.user.domain.vo.OcUserRcVo;
import com.ruoyi.oc.admin.user.domain.OcUserRc;
import com.ruoyi.oc.admin.user.mapper.OcUserRcMapper;
import com.ruoyi.oc.admin.user.service.IOcUserRcService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 小区用户Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@RequiredArgsConstructor
@Service
public class OcUserRcServiceImpl extends ServiceImpl<OcUserRcMapper, OcUserRc>
            implements IOcUserRcService {

    private final OcUserRcMapper baseMapper;

    /**
     * 查询小区用户
     */
    @Override
    public OcUserRcVo queryById(Long userId){
        return baseMapper.selectVoById(userId);
    }

    /**
     * 查询小区用户列表
     */
    @Override
    public TableDataInfo<OcUserRcVo> queryPageList(OcUserRcBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OcUserRc> lqw = buildQueryWrapper(bo);
        Page<OcUserRcVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询小区用户列表
     */
    @Override
    public List<OcUserRcVo> queryList(OcUserRcBo bo) {
        LambdaQueryWrapper<OcUserRc> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OcUserRc> buildQueryWrapper(OcUserRcBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OcUserRc> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getRcId() != null, OcUserRc::getRcId, bo.getRcId());
        lqw.eq(bo.getTrId() != null, OcUserRc::getTrId, bo.getTrId());
        return lqw;
    }

    /**
     * 新增小区用户
     */
    @Override
    public Boolean insertByBo(OcUserRcBo bo) {
        OcUserRc add = BeanUtil.toBean(bo, OcUserRc.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改小区用户
     */
    @Override
    public Boolean updateByBo(OcUserRcBo bo) {
        OcUserRc update = BeanUtil.toBean(bo, OcUserRc.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(OcUserRc entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除小区用户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
