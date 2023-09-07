package com.ruoyi.oc.admin.rc.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小区对象 oc_residential_community
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_residential_community")
public class OcResidentialCommunity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 小区ID
     */
    @TableId(value = "rc_id", type = IdType.AUTO)
    private Long rcId;
    /**
     * 小区名称
     */
    private String rcName;

}
