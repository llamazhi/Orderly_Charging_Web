package com.ruoyi..domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 load_comparison_table
 *
 * @author zhiwei
 * @date 2023-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("load_comparison_table")
public class LoadComparisonTable extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    private String uid;
    /**
     * 
     */
    private Long time;
    /**
     * 
     */
    private Long oldLoad;
    /**
     * 
     */
    private Long newLoad;

}
