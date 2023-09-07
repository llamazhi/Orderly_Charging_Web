package com.ruoyi.oc.admin.user.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小区用户业务对象 oc_user_rc
 *
 * @author zhiwei
 * @date 2023-08-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcUserRcBo extends BaseEntity {

    /**
     * 小区居民ID
     */
    @NotNull(message = "小区居民ID不能为空", groups = { EditGroup.class })
    private Long userId;

    /**
     * 小区ID
     */
    @NotNull(message = "小区ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rcId;

    /**
     * 变压器ID
     */
    @NotNull(message = "变压器ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long trId;


}
