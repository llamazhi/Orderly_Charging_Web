package com.ruoyi.oc.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * Time Comparison视图对象 ev_time_comparison_table
 *
 * @author zhiwei
 * @date 2023-08-18
 */
@Data
@ExcelIgnoreUnannotated
public class EvTimeComparisonTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * 表单ID
     */
    @ExcelProperty(value = "表单ID")
    private Long id;

    /**
     * 时间ID
     */
    @ExcelProperty(value = "时间ID")
    private String uid;

    /**
     * 车辆ID
     */
    @ExcelProperty(value = "车辆ID")
    private String evid;

    /**
     * 优化前起始充电时间
     */
    @ExcelProperty(value = "优化前起始充电时间")
    private Date oldStartTime;

    /**
     * 优化前结束充电时间
     */
    @ExcelProperty(value = "优化前结束充电时间")
    private Date oldEndTime;

    /**
     * 优化后起始充电时间
     */
    @ExcelProperty(value = "优化后起始充电时间")
    private Date newStartTime;

    /**
     * 优化后结束充电时间
     */
    @ExcelProperty(value = "优化后结束充电时间")
    private Date newEndTime;

    /**
     * 车辆离开时间
     */
    @ExcelProperty(value = "车辆离开时间")
    private Date leavingTime;


}
