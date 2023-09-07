package com.ruoyi.oc.admin.rc.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 小区视图对象 oc_residential_community
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@ExcelIgnoreUnannotated
public class OcResidentialCommunityVo {

    private static final long serialVersionUID = 1L;

    /**
     * 小区ID
     */
    @ExcelProperty(value = "小区ID")
    private Long rcId;

    /**
     * 小区名称
     */
    @ExcelProperty(value = "小区名称")
    private String rcName;


}
