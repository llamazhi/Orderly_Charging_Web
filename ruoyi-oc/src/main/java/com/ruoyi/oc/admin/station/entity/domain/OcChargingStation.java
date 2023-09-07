package com.ruoyi.oc.admin.station.entity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电桩对象 oc_charging_station
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_charging_station")
public class OcChargingStation extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 充电桩ID
     */
    @TableId(value = "cs_id", type = IdType.AUTO)
    private Long csId;
    /**
     * 变压器ID
     */
    private Long transformerId;
    /**
     * 小区ID
     */
    private Long rcId;
    /**
     * 充电桩类型ID
     */
    private Long typeId;
    /**
     * 过期时间
     */
    private Long expiringDate;

}
