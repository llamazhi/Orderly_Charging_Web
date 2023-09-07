package com.ruoyi.oc.admin.station.types.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电桩类型对象 oc_charging_station_type
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_charging_station_type")
public class OcChargingStationType extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 充电桩种类ID
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Long typeId;
    /**
     * 充电站ID
     */
    private Long csId;
    /**
     * 制造商
     */
    private String manufacturer;
    /**
     * 所属公司
     */
    private String company;

}
