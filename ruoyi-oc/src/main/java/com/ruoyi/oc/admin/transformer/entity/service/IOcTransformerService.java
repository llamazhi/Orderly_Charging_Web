package com.ruoyi.oc.admin.transformer.entity.service;

import com.ruoyi.oc.admin.transformer.entity.domain.OcTransformer;
import com.ruoyi.oc.admin.transformer.entity.domain.vo.OcTransformerVo;
import com.ruoyi.oc.admin.transformer.entity.domain.bo.OcTransformerBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 变压器Service接口
 *
 * @author zhiwei
 * @date 2023-08-23
 */
public interface IOcTransformerService {

    /**
     * 查询变压器
     */
    OcTransformerVo queryById(Long transformerId);

    /**
     * 查询变压器列表
     */
    TableDataInfo<OcTransformerVo> queryPageList(OcTransformerBo bo, PageQuery pageQuery);

    /**
     * 查询变压器列表
     */
    List<OcTransformerVo> queryList(OcTransformerBo bo);

    /**
     * 新增变压器
     */
    Boolean insertByBo(OcTransformerBo bo);

    /**
     * 修改变压器
     */
    Boolean updateByBo(OcTransformerBo bo);

    /**
     * 校验并批量删除变压器信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
