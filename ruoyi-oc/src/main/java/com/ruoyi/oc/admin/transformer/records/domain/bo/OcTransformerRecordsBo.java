package com.ruoyi.oc.admin.transformer.records.domain.bo;

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
 * 变压器记录业务对象 oc_transformer_records
 *
 * @author zhiwei
 * @date 2023-08-29
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OcTransformerRecordsBo extends BaseEntity {

    /**
     * 变压器记录id
     */
    @NotNull(message = "变压器记录id不能为空", groups = { EditGroup.class })
    private Long recordId;

    /**
     * 小区ID
     */
    @NotNull(message = "小区ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rcId;

    /**
     * 现在时刻
     */
    @NotNull(message = "现在时刻不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date currTime;

    /**
     * 现在充电负荷
     */
    @NotNull(message = "现在充电负荷不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long currChargingLoad;

    /**
     * 变压器ID
     */
    @NotNull(message = "变压器ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long transformerId;

    /**
     * 现在日常负荷
     */
    @NotNull(message = "现在日常负荷不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long currDailyLoad;


}
