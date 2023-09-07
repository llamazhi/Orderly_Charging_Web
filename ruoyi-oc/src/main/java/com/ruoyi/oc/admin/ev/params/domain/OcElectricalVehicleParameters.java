package com.ruoyi.oc.admin.ev.params.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新能源车参数对象 oc_electrical_vehicle_parameters
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_electrical_vehicle_parameters")
public class OcElectricalVehicleParameters extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * EV类型ID
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Long typeId;
    /**
     * 最大电容量
     */
    private Long maxSoc;
    /**
     * 慢充速率
     */
    private Long normalChargingRate;
    /**
     * 快充速率
     */
    private Long fastChargingRate;

}
