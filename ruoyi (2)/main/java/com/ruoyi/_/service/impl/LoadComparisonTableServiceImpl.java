package com.ruoyi..service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi..domain.bo.LoadComparisonTableBo;
import com.ruoyi..domain.vo.LoadComparisonTableVo;
import com.ruoyi..domain.LoadComparisonTable;
import com.ruoyi..mapper.LoadComparisonTableMapper;
import com.ruoyi..service.ILoadComparisonTableService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author zhiwei
 * @date 2023-08-17
 */
@RequiredArgsConstructor
@Service
public class LoadComparisonTableServiceImpl implements ILoadComparisonTableService {

    private final LoadComparisonTableMapper baseMapper;

    /**
     * 查询【请填写功能名称】
     */
    @Override
    public LoadComparisonTableVo queryById(String uid){
        return baseMapper.selectVoById(uid);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @Override
    public TableDataInfo<LoadComparisonTableVo> queryPageList(LoadComparisonTableBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<LoadComparisonTable> lqw = buildQueryWrapper(bo);
        Page<LoadComparisonTableVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @Override
    public List<LoadComparisonTableVo> queryList(LoadComparisonTableBo bo) {
        LambdaQueryWrapper<LoadComparisonTable> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<LoadComparisonTable> buildQueryWrapper(LoadComparisonTableBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<LoadComparisonTable> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getUid()), LoadComparisonTable::getUid, bo.getUid());
        lqw.eq(bo.getTime() != null, LoadComparisonTable::getTime, bo.getTime());
        lqw.eq(bo.getOldLoad() != null, LoadComparisonTable::getOldLoad, bo.getOldLoad());
        lqw.eq(bo.getNewLoad() != null, LoadComparisonTable::getNewLoad, bo.getNewLoad());
        return lqw;
    }

    /**
     * 新增【请填写功能名称】
     */
    @Override
    public Boolean insertByBo(LoadComparisonTableBo bo) {
        LoadComparisonTable add = BeanUtil.toBean(bo, LoadComparisonTable.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUid(add.getUid());
        }
        return flag;
    }

    /**
     * 修改【请填写功能名称】
     */
    @Override
    public Boolean updateByBo(LoadComparisonTableBo bo) {
        LoadComparisonTable update = BeanUtil.toBean(bo, LoadComparisonTable.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(LoadComparisonTable entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除【请填写功能名称】
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
