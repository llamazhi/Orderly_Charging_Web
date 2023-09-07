package com.ruoyi.oc.domain.bo;

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
 * Time Comparison业务对象 ev_time_comparison_table
 *
 * @author zhiwei
 * @date 2023-08-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EvTimeComparisonTableBo extends BaseEntity {

    /**
     * 表单ID
     */
    @NotNull(message = "表单ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 时间ID
     */
    @NotBlank(message = "时间ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String uid;

    /**
     * 车辆ID
     */
    @NotBlank(message = "车辆ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String evid;

    /**
     * 优化前起始充电时间
     */
    @NotNull(message = "优化前起始充电时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date oldStartTime;

    /**
     * 优化前结束充电时间
     */
    @NotNull(message = "优化前结束充电时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date oldEndTime;

    /**
     * 优化后起始充电时间
     */
    @NotNull(message = "优化后起始充电时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date newStartTime;

    /**
     * 优化后结束充电时间
     */
    @NotNull(message = "优化后结束充电时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date newEndTime;

    /**
     * 车辆离开时间
     */
    @NotNull(message = "车辆离开时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date leavingTime;


}
