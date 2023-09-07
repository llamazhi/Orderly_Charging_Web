package com.ruoyi.oc.client.records.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;
import com.ruoyi.oc.client.records.domain.vo.OcChargingRecordsVo;
import com.ruoyi.oc.client.records.domain.bo.OcChargingRecordsBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 充电记录Service接口
 *
 * @author zhiwei
 * @date 2023-08-30
 */
public interface IOcChargingRecordsService extends IService<OcChargingRecords> {

    /**
     * 查询充电记录
     */
    OcChargingRecordsVo queryById(Long recordId);

    /**
     * 查询充电记录列表
     */
    TableDataInfo<OcChargingRecordsVo> queryPageList(OcChargingRecordsBo bo, PageQuery pageQuery);

    /**
     * 查询充电记录列表
     */
    List<OcChargingRecordsVo> queryList(OcChargingRecordsBo bo);

    /**
     * 新增充电记录
     */
    Boolean insertByBo(OcChargingRecordsBo bo);

    /**
     * 修改充电记录
     */
    Boolean updateByBo(OcChargingRecordsBo bo);

    /**
     * 校验并批量删除充电记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    String optimize(Long evId);

    String optimize();
}
