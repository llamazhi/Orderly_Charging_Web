package com.ruoyi.oc.client.records.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电记录对象 oc_charging_records
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_charging_records")
public class OcChargingRecords extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 充电记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;
    /**
     * 车辆ID
     */
    private Long evId;
    /**
     * 小区ID
     */
    private Long csId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 变压器ID
     */
    private Long tId;
    /**
     * 是否使用快充
     */
    private Integer ifUsingFastCharging;
    /**
     * 剩余电量
     */
    private Long remainingSoc;
    /**
     * 预期充电结束时间
     */
    private Date expectedChargingEndTime;
    /**
     * 起始充电时间
     */
    private Date chargingStartTime;
    /**
     * 是否使用有序充电
     */
    private Integer ifUsingOrderlyCharging;

    /**
     * 是否完成充电
     */
    private Integer isFinished;

    /**
     * 优化后起始充电时间
     */
    private Date newChargingStartTime;

    /**
     *  预期车辆离开时间
     */
    private Date expectedLeavingTime;

}
