package com.ruoyi.oc.admin.transformer.records.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 变压器记录视图对象 oc_transformer_records
 *
 * @author zhiwei
 * @date 2023-08-29
 */
@Data
@ExcelIgnoreUnannotated
public class OcTransformerRecordsVo {

    private static final long serialVersionUID = 1L;

    /**
     * 变压器记录id
     */
    @ExcelProperty(value = "变压器记录id")
    private Long recordId;

    /**
     * 小区ID
     */
    @ExcelProperty(value = "小区ID")
    private Long rcId;

    /**
     * 现在时刻
     */
    @ExcelProperty(value = "现在时刻")
    private Date currTime;

    /**
     * 现在充电负荷
     */
    @ExcelProperty(value = "现在充电负荷")
    private Long currChargingLoad;

    /**
     * 变压器ID
     */
    @ExcelProperty(value = "变压器ID")
    private Long transformerId;

    /**
     * 现在日常负荷
     */
    @ExcelProperty(value = "现在日常负荷")
    private Long currDailyLoad;


}
