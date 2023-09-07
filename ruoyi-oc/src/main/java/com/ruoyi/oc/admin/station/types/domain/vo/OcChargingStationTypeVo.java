package com.ruoyi.oc.admin.station.types.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 充电桩类型视图对象 oc_charging_station_type
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@ExcelIgnoreUnannotated
public class OcChargingStationTypeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 充电桩种类ID
     */
    @ExcelProperty(value = "充电桩种类ID")
    private Long typeId;

    /**
     * 充电站ID
     */
    @ExcelProperty(value = "充电站ID")
    private Long csId;

    /**
     * 制造商
     */
    @ExcelProperty(value = "制造商")
    private String manufacturer;

    /**
     * 所属公司
     */
    @ExcelProperty(value = "所属公司")
    private String company;


}
