package com.ruoyi.oc.domain;

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
 * Time Comparison对象 ev_time_comparison_table
 *
 * @author zhiwei
 * @date 2023-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ev_time_comparison_table")
public class EvTimeComparisonTable extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 表单ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 时间ID
     */
    private String uid;
    /**
     * 车辆ID
     */
    private String evid;
    /**
     * 优化前起始充电时间
     */
    private Date oldStartTime;
    /**
     * 优化前结束充电时间
     */
    private Date oldEndTime;
    /**
     * 优化后起始充电时间
     */
    private Date newStartTime;
    /**
     * 优化后结束充电时间
     */
    private Date newEndTime;
    /**
     * 车辆离开时间
     */
    private Date leavingTime;

}
