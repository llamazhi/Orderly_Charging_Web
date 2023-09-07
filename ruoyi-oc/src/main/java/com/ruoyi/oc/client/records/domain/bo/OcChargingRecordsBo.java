package com.ruoyi.oc.client.records.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充电记录业务对象 oc_charging_records
 *
 * @author zhiwei
 * @date 2023-08-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcChargingRecordsBo extends BaseEntity {

    /**
     * 充电记录id
     */
    @NotNull(message = "充电记录id不能为空", groups = { EditGroup.class })
    private Long recordId;

    /**
     * 车辆ID
     */
    @NotNull(message = "车辆ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long evId;

    /**
     * 小区ID
     */
    @NotNull(message = "小区ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long csId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 变压器ID
     */
    @NotNull(message = "变压器ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tId;

    /**
     * 是否使用快充
     */
    @NotNull(message = "是否使用快充不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer ifUsingFastCharging;

    /**
     * 剩余电量
     */
    @NotNull(message = "剩余电量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long remainingSoc;

    /**
     * 预期充电结束时间
     */
    @NotNull(message = "预期充电结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expectedChargingEndTime;

    /**
     * 起始充电时间
     */
    @NotNull(message = "起始充电时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date chargingStartTime;

    /**
     * 是否使用有序充电
     */
    @NotNull(message = "是否使用有序充电不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer ifUsingOrderlyCharging;


}
