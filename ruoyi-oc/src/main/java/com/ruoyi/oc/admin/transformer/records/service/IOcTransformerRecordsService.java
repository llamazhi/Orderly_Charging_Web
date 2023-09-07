package com.ruoyi.oc.admin.transformer.records.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.oc.admin.transformer.records.domain.OcTransformerRecords;
import com.ruoyi.oc.admin.transformer.records.domain.vo.OcTransformerRecordsVo;
import com.ruoyi.oc.admin.transformer.records.domain.bo.OcTransformerRecordsBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 变压器记录Service接口
 *
 * @author zhiwei
 * @date 2023-08-29
 */
public interface IOcTransformerRecordsService extends IService<OcTransformerRecords> {

    /**
     * 查询变压器记录
     */
    OcTransformerRecordsVo queryById(Long recordId);

    /**
     * 查询变压器记录列表
     */
    TableDataInfo<OcTransformerRecordsVo> queryPageList(OcTransformerRecordsBo bo, PageQuery pageQuery);

    /**
     * 查询变压器记录列表
     */
    List<OcTransformerRecordsVo> queryList(OcTransformerRecordsBo bo);

    /**
     * 新增变压器记录
     */
    Boolean insertByBo(OcTransformerRecordsBo bo);

    /**
     * 修改变压器记录
     */
    Boolean updateByBo(OcTransformerRecordsBo bo);

    /**
     * 校验并批量删除变压器记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
