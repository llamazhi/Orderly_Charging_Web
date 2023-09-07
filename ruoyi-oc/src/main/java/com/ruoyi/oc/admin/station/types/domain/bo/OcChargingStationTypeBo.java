package com.ruoyi.oc.admin.station.types.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电桩类型业务对象 oc_charging_station_type
 *
 * @author zhiwei
 * @date 2023-08-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcChargingStationTypeBo extends BaseEntity {

    /**
     * 充电桩种类ID
     */
    @NotNull(message = "充电桩种类ID不能为空", groups = { EditGroup.class })
    private Long typeId;

    /**
     * 充电站ID
     */
    @NotNull(message = "充电站ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long csId;

    /**
     * 制造商
     */
    @NotBlank(message = "制造商不能为空", groups = { AddGroup.class, EditGroup.class })
    private String manufacturer;

    /**
     * 所属公司
     */
    @NotBlank(message = "所属公司不能为空", groups = { AddGroup.class, EditGroup.class })
    private String company;


}
