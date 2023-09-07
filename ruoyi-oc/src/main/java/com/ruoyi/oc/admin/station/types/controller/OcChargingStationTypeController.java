package com.ruoyi.oc.admin.station.types.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.oc.admin.station.types.domain.vo.OcChargingStationTypeVo;
import com.ruoyi.oc.admin.station.types.domain.bo.OcChargingStationTypeBo;
import com.ruoyi.oc.admin.station.types.service.IOcChargingStationTypeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电桩类型
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/stationType/chargingStationType")
public class OcChargingStationTypeController extends BaseController {

    private final IOcChargingStationTypeService iOcChargingStationTypeService;

    /**
     * 查询充电桩类型列表
     */
    @SaCheckPermission("stationType:chargingStationType:list")
    @GetMapping("/list")
    public TableDataInfo<OcChargingStationTypeVo> list(OcChargingStationTypeBo bo, PageQuery pageQuery) {
        return iOcChargingStationTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出充电桩类型列表
     */
    @SaCheckPermission("stationType:chargingStationType:export")
    @Log(title = "充电桩类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcChargingStationTypeBo bo, HttpServletResponse response) {
        List<OcChargingStationTypeVo> list = iOcChargingStationTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "充电桩类型", OcChargingStationTypeVo.class, response);
    }

    /**
     * 获取充电桩类型详细信息
     *
     * @param typeId 主键
     */
    @SaCheckPermission("stationType:chargingStationType:query")
    @GetMapping("/{typeId}")
    public R<OcChargingStationTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long typeId) {
        return R.ok(iOcChargingStationTypeService.queryById(typeId));
    }

    /**
     * 新增充电桩类型
     */
    @SaCheckPermission("stationType:chargingStationType:add")
    @Log(title = "充电桩类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcChargingStationTypeBo bo) {
        return toAjax(iOcChargingStationTypeService.insertByBo(bo));
    }

    /**
     * 修改充电桩类型
     */
    @SaCheckPermission("stationType:chargingStationType:edit")
    @Log(title = "充电桩类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcChargingStationTypeBo bo) {
        return toAjax(iOcChargingStationTypeService.updateByBo(bo));
    }

    /**
     * 删除充电桩类型
     *
     * @param typeIds 主键串
     */
    @SaCheckPermission("stationType:chargingStationType:remove")
    @Log(title = "充电桩类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] typeIds) {
        return toAjax(iOcChargingStationTypeService.deleteWithValidByIds(Arrays.asList(typeIds), true));
    }
}
