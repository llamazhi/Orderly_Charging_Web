package com.ruoyi.oc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.oc.domain.bo.EvTimeComparisonTableBo;
import com.ruoyi.oc.domain.vo.EvTimeComparisonTableVo;
import com.ruoyi.oc.domain.EvTimeComparisonTable;
import com.ruoyi.oc.mapper.EvTimeComparisonTableMapper;
import com.ruoyi.oc.service.IEvTimeComparisonTableService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * Time ComparisonService业务层处理
 *
 * @author zhiwei
 * @date 2023-08-18
 */
@RequiredArgsConstructor
@Service
public class EvTimeComparisonTableServiceImpl implements IEvTimeComparisonTableService {

    private final EvTimeComparisonTableMapper baseMapper;

    /**
     * 查询Time Comparison
     */
    @Override
    public EvTimeComparisonTableVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询Time Comparison列表
     */
    @Override
    public TableDataInfo<EvTimeComparisonTableVo> queryPageList(EvTimeComparisonTableBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EvTimeComparisonTable> lqw = buildQueryWrapper(bo);
        Page<EvTimeComparisonTableVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询Time Comparison列表
     */
    @Override
    public List<EvTimeComparisonTableVo> queryList(EvTimeComparisonTableBo bo) {
        LambdaQueryWrapper<EvTimeComparisonTable> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EvTimeComparisonTable> buildQueryWrapper(EvTimeComparisonTableBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EvTimeComparisonTable> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getUid()), EvTimeComparisonTable::getUid, bo.getUid());
        lqw.eq(StringUtils.isNotBlank(bo.getEvid()), EvTimeComparisonTable::getEvid, bo.getEvid());
        lqw.eq(bo.getOldStartTime() != null, EvTimeComparisonTable::getOldStartTime, bo.getOldStartTime());
        lqw.eq(bo.getOldEndTime() != null, EvTimeComparisonTable::getOldEndTime, bo.getOldEndTime());
        lqw.eq(bo.getNewStartTime() != null, EvTimeComparisonTable::getNewStartTime, bo.getNewStartTime());
        lqw.eq(bo.getNewEndTime() != null, EvTimeComparisonTable::getNewEndTime, bo.getNewEndTime());
        lqw.eq(bo.getLeavingTime() != null, EvTimeComparisonTable::getLeavingTime, bo.getLeavingTime());
        return lqw;
    }

    /**
     * 新增Time Comparison
     */
    @Override
    public Boolean insertByBo(EvTimeComparisonTableBo bo) {
        EvTimeComparisonTable add = BeanUtil.toBean(bo, EvTimeComparisonTable.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Time Comparison
     */
    @Override
    public Boolean updateByBo(EvTimeComparisonTableBo bo) {
        EvTimeComparisonTable update = BeanUtil.toBean(bo, EvTimeComparisonTable.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EvTimeComparisonTable entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除Time Comparison
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
