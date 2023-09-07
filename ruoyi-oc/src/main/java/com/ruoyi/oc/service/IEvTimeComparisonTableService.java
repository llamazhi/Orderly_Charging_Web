package com.ruoyi.oc.service;

import com.ruoyi.oc.domain.EvTimeComparisonTable;
import com.ruoyi.oc.domain.vo.EvTimeComparisonTableVo;
import com.ruoyi.oc.domain.bo.EvTimeComparisonTableBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * Time ComparisonService接口
 *
 * @author zhiwei
 * @date 2023-08-18
 */
public interface IEvTimeComparisonTableService {

    /**
     * 查询Time Comparison
     */
    EvTimeComparisonTableVo queryById(Long id);

    /**
     * 查询Time Comparison列表
     */
    TableDataInfo<EvTimeComparisonTableVo> queryPageList(EvTimeComparisonTableBo bo, PageQuery pageQuery);

    /**
     * 查询Time Comparison列表
     */
    List<EvTimeComparisonTableVo> queryList(EvTimeComparisonTableBo bo);

    /**
     * 新增Time Comparison
     */
    Boolean insertByBo(EvTimeComparisonTableBo bo);

    /**
     * 修改Time Comparison
     */
    Boolean updateByBo(EvTimeComparisonTableBo bo);

    /**
     * 校验并批量删除Time Comparison信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
