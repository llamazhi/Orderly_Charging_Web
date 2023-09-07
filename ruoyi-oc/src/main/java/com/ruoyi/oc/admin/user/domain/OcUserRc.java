package com.ruoyi.oc.admin.user.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小区用户对象 oc_user_rc
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_user_rc")
public class OcUserRc extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 小区居民ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 小区ID
     */
    private Long rcId;
    /**
     * 变压器ID
     */
    private Long trId;

}
