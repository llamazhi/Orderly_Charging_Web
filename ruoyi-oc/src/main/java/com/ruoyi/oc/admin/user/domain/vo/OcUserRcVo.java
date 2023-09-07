package com.ruoyi.oc.admin.user.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 小区用户视图对象 oc_user_rc
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@Data
@ExcelIgnoreUnannotated
public class OcUserRcVo {

    private static final long serialVersionUID = 1L;

    /**
     * 小区居民ID
     */
    @ExcelProperty(value = "小区居民ID")
    private Long userId;

    /**
     * 小区ID
     */
    @ExcelProperty(value = "小区ID")
    private Long rcId;

    /**
     * 变压器ID
     */
    @ExcelProperty(value = "变压器ID")
    private Long trId;


}
