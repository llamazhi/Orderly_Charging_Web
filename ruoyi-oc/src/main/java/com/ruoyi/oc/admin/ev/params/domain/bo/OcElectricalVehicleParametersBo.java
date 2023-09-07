package com.ruoyi.oc.admin.ev.params.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新能源车参数业务对象 oc_electrical_vehicle_parameters
 *
 * @author zhiwei
 * @date 2023-08-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcElectricalVehicleParametersBo extends BaseEntity {

    /**
     * EV类型ID
     */
    @NotNull(message = "EV类型ID不能为空", groups = { EditGroup.class })
    private Long typeId;

    /**
     * 最大电容量
     */
    @NotNull(message = "最大电容量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long maxSoc;

    /**
     * 慢充速率
     */
    @NotNull(message = "慢充速率不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long normalChargingRate;

    /**
     * 快充速率
     */
    @NotNull(message = "快充速率不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fastChargingRate;


}
