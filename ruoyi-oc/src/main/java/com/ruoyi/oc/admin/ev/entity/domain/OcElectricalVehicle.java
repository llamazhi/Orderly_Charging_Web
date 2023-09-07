package com.ruoyi.oc.admin.ev.entity.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新能源车对象 oc_electrical_vehicle
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_electrical_vehicle")
public class OcElectricalVehicle extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * EVID
     */
    @TableId(value = "ev_id", type = IdType.AUTO)
    private Long evId;
    /**
     * EV类型ID
     */
    private Long typeId;
    /**
     * 用户ID
     */
    private Long userId;

}
