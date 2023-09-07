package com.ruoyi.oc.admin.transformer.entity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 变压器对象 oc_transformer
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_transformer")
public class OcTransformer extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 变压器ID
     */
    @TableId(value = "transformer_id", type = IdType.AUTO)
    private Long transformerId;
    /**
     * 小区ID
     */
    private Long rcId;
    /**
     * 变压器名称
     */
    private String tName;
    /**
     * 变压器最大负荷
     */
    private Long tMax;

}
