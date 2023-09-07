package com.ruoyi.oc.admin.transformer.records.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 变压器记录对象 oc_transformer_records
 *
 * @author zhiwei
 * @date 2023-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_transformer_records")
public class OcTransformerRecords extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 变压器记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;
    /**
     * 小区ID
     */
    private Long rcId;
    /**
     * 现在时刻
     */
    private Date currTime;
    /**
     * 现在充电负荷
     */
    private Long currChargingLoad;
    /**
     * 变压器ID
     */
    private Long transformerId;
    /**
     * 现在日常负荷
     */
    private Long currDailyLoad;

}
