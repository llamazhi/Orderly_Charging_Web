package com.ruoyi.oc.admin.ev.entity.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新能源车业务对象 oc_electrical_vehicle
 *
 * @author zhiwei
 * @date 2023-08-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcElectricalVehicleBo extends BaseEntity {

    /**
     * EVID
     */
    @NotNull(message = "EVID不能为空", groups = { EditGroup.class })
    private Long evId;

    /**
     * EV类型ID
     */
    @NotNull(message = "EV类型ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long typeId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;


}
