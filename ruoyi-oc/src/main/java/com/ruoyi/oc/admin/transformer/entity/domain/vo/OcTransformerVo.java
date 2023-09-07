package com.ruoyi.oc.admin.transformer.entity.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 变压器视图对象 oc_transformer
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@ExcelIgnoreUnannotated
public class OcTransformerVo {

    private static final long serialVersionUID = 1L;

    /**
     * 变压器ID
     */
    @ExcelProperty(value = "变压器ID")
    private Long transformerId;

    /**
     * 小区ID
     */
    @ExcelProperty(value = "小区ID")
    private Long rcId;

    /**
     * 变压器名称
     */
    @ExcelProperty(value = "变压器名称")
    private String tName;

    /**
     * 变压器最大负荷
     */
    @ExcelProperty(value = "变压器最大负荷")
    private Long tMax;


}
