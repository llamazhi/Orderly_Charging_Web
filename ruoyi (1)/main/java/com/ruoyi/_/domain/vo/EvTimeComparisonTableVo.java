package com.ruoyi..domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 【请填写功能名称】视图对象 ev_time_comparison_table
 *
 * @author zhiwei
 * @date 2023-08-17
 */
@Data
@ExcelIgnoreUnannotated
public class EvTimeComparisonTableVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private String uid;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private String evid;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Date oldStartTime;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Date oldEndTime;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Date newStartTime;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Date newEndTime;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Date leavingTime;


}
