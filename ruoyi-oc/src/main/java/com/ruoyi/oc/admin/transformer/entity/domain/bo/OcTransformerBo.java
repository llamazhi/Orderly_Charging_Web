package com.ruoyi.oc.admin.transformer.entity.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 变压器业务对象 oc_transformer
 *
 * @author zhiwei
 * @date 2023-08-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcTransformerBo extends BaseEntity {

    /**
     * 变压器ID
     */
    @NotNull(message = "变压器ID不能为空", groups = { EditGroup.class })
    private Long transformerId;

    /**
     * 小区ID
     */
    @NotNull(message = "小区ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rcId;

    /**
     * 变压器名称
     */
    @NotBlank(message = "变压器名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tName;

    /**
     * 变压器最大负荷
     */
    @NotNull(message = "变压器最大负荷不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tMax;


}
