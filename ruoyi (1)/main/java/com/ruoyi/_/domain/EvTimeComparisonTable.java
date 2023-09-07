package com.ruoyi..domain;

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
 * 【请填写功能名称】对象 ev_time_comparison_table
 *
 * @author zhiwei
 * @date 2023-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ev_time_comparison_table")
public class EvTimeComparisonTable extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    private String uid;
    /**
     * 
     */
    private String evid;
    /**
     * 
     */
    private Date oldStartTime;
    /**
     * 
     */
    private Date oldEndTime;
    /**
     * 
     */
    private Date newStartTime;
    /**
     * 
     */
    private Date newEndTime;
    /**
     * 
     */
    private Date leavingTime;

}
