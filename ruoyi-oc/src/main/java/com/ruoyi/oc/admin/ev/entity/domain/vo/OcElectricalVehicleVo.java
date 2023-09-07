package com.ruoyi.oc.admin.ev.entity.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 新能源车视图对象 oc_electrical_vehicle
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Data
@ExcelIgnoreUnannotated
public class OcElectricalVehicleVo {

    private static final long serialVersionUID = 1L;

    /**
     * EVID
     */
    @ExcelProperty(value = "EVID")
    private Long evId;

    /**
     * EV类型ID
     */
    @ExcelProperty(value = "EV类型ID")
    private Long typeId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;


}
