package com.ruoyi.oc.admin.ev.params.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 新能源车参数视图对象 oc_electrical_vehicle_parameters
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@ExcelIgnoreUnannotated
public class OcElectricalVehicleParametersVo {

    private static final long serialVersionUID = 1L;

    /**
     * EV类型ID
     */
    @ExcelProperty(value = "EV类型ID")
    private Long typeId;

    /**
     * 最大电容量
     */
    @ExcelProperty(value = "最大电容量")
    private Long maxSoc;

    /**
     * 慢充速率
     */
    @ExcelProperty(value = "慢充速率")
    private Long normalChargingRate;

    /**
     * 快充速率
     */
    @ExcelProperty(value = "快充速率")
    private Long fastChargingRate;


}
