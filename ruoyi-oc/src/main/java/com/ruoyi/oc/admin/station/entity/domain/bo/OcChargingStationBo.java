package com.ruoyi.oc.admin.station.entity.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电桩业务对象 oc_charging_station
 *
 * @author zhiwei
 * @date 2023-08-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcChargingStationBo extends BaseEntity {

    /**
     * 充电桩ID
     */
    @NotNull(message = "充电桩ID不能为空", groups = { EditGroup.class })
    private Long csId;

    /**
     * 变压器ID
     */
    @NotNull(message = "变压器ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long transformerId;

    /**
     * 小区ID
     */
    @NotNull(message = "小区ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rcId;

    /**
     * 充电桩类型ID
     */
    @NotNull(message = "充电桩类型ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long typeId;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long expiringDate;


}
