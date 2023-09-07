package com.ruoyi.oc.client.records.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 充电记录视图对象 oc_charging_records
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@Data
@ExcelIgnoreUnannotated
public class OcChargingRecordsVo {

    private static final long serialVersionUID = 1L;

    /**
     * 充电记录id
     */
    @ExcelProperty(value = "充电记录id")
    private Long recordId;

    /**
     * 车辆ID
     */
    @ExcelProperty(value = "车辆ID")
    private Long evId;

    /**
     * 小区ID
     */
    @ExcelProperty(value = "小区ID")
    private Long csId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 变压器ID
     */
    @ExcelProperty(value = "变压器ID")
    private Long tId;

    /**
     * 是否使用快充
     */
    @ExcelProperty(value = "是否使用快充")
    private Integer ifUsingFastCharging;

    /**
     * 剩余电量
     */
    @ExcelProperty(value = "剩余电量")
    private Long remainingSoc;

    /**
     * 预期充电结束时间
     */
    @ExcelProperty(value = "预期充电结束时间")
    private Date expectedChargingEndTime;

    /**
     * 起始充电时间
     */
    @ExcelProperty(value = "起始充电时间")
    private Date chargingStartTime;

    /**
     * 是否使用有序充电
     */
    @ExcelProperty(value = "是否使用有序充电")
    private Integer ifUsingOrderlyCharging;


}
