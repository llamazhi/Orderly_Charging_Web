package com.ruoyi..service;

import com.ruoyi..domain.LoadComparisonTable;
import com.ruoyi..domain.vo.LoadComparisonTableVo;
import com.ruoyi..domain.bo.LoadComparisonTableBo;
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
public interface ILoadComparisonTableService {

    /**
     * 查询【请填写功能名称】
     */
    LoadComparisonTableVo queryById(String uid);

    /**
     * 查询【请填写功能名称】列表
     */
    TableDataInfo<LoadComparisonTableVo> queryPageList(LoadComparisonTableBo bo, PageQuery pageQuery);

    /**
     * 查询【请填写功能名称】列表
     */
    List<LoadComparisonTableVo> queryList(LoadComparisonTableBo bo);

    /**
     * 新增【请填写功能名称】
     */
    Boolean insertByBo(LoadComparisonTableBo bo);

    /**
     * 修改【请填写功能名称】
     */
    Boolean updateByBo(LoadComparisonTableBo bo);

    /**
     * 校验并批量删除【请填写功能名称】信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
