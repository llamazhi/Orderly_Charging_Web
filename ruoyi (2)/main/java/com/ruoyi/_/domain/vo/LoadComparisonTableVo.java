package com.ruoyi..domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 【请填写功能名称】视图对象 load_comparison_table
 *
 * @author zhiwei
 * @date 2023-08-17
 */
@Data
@ExcelIgnoreUnannotated
public class LoadComparisonTableVo {

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
    private Long time;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long oldLoad;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long newLoad;


}
