package com.ruoyi.oc.admin.station.entity.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 充电桩视图对象 oc_charging_station
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@ExcelIgnoreUnannotated
public class OcChargingStationVo {

    private static final long serialVersionUID = 1L;

    /**
     * 充电桩ID
     */
    @ExcelProperty(value = "充电桩ID")
    private Long csId;

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
     * 充电桩类型ID
     */
    @ExcelProperty(value = "充电桩类型ID")
    private Long typeId;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Long expiringDate;


}
