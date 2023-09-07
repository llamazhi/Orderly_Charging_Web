package com.ruoyi..service;

import com.ruoyi..domain.EvTimeComparisonTable;
import com.ruoyi..domain.vo.EvTimeComparisonTableVo;
import com.ruoyi..domain.bo.EvTimeComparisonTableBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author zhiwei
 * @date 2023-08-17
 */
public interface IEvTimeComparisonTableService {

    /**
     * 查询【请填写功能名称】
     */
    EvTimeComparisonTableVo queryById(String uid);

    /**
     * 查询【请填写功能名称】列表
     */
    TableDataInfo<EvTimeComparisonTableVo> queryPageList(EvTimeComparisonTableBo bo, PageQuery pageQuery);

    /**
     * 查询【请填写功能名称】列表
     */
    List<EvTimeComparisonTableVo> queryList(EvTimeComparisonTableBo bo);

    /**
     * 新增【请填写功能名称】
     */
    Boolean insertByBo(EvTimeComparisonTableBo bo);

    /**
     * 修改【请填写功能名称】
     */
    Boolean updateByBo(EvTimeComparisonTableBo bo);

    /**
     * 校验并批量删除【请填写功能名称】信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
