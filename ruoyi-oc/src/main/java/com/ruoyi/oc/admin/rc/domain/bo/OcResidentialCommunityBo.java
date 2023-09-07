package com.ruoyi.oc.admin.rc.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小区业务对象 oc_residential_community
 *
 * @author zhiwei
 * @date 2023-08-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcResidentialCommunityBo extends BaseEntity {

    /**
     * 小区ID
     */
    @NotNull(message = "小区ID不能为空", groups = { EditGroup.class })
    private Long rcId;

    /**
     * 小区名称
     */
    @NotBlank(message = "小区名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String rcName;


}
