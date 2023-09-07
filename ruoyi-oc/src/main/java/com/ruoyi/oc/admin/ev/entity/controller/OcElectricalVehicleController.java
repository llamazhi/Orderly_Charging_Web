package com.ruoyi.oc.admin.ev.entity.controller;

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
import com.ruoyi.oc.admin.ev.entity.domain.vo.OcElectricalVehicleVo;
import com.ruoyi.oc.admin.ev.entity.domain.bo.OcElectricalVehicleBo;
import com.ruoyi.oc.admin.ev.entity.service.IOcElectricalVehicleService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新能源车
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/evEntity/electricalVehicle")
public class OcElectricalVehicleController extends BaseController {

    private final IOcElectricalVehicleService iOcElectricalVehicleService;

    /**
     * 查询新能源车列表
     */
    @SaCheckPermission("evEntity:electricalVehicle:list")
    @GetMapping("/list")
    public TableDataInfo<OcElectricalVehicleVo> list(OcElectricalVehicleBo bo, PageQuery pageQuery) {
        return iOcElectricalVehicleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出新能源车列表
     */
    @SaCheckPermission("evEntity:electricalVehicle:export")
    @Log(title = "新能源车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcElectricalVehicleBo bo, HttpServletResponse response) {
        List<OcElectricalVehicleVo> list = iOcElectricalVehicleService.queryList(bo);
        ExcelUtil.exportExcel(list, "新能源车", OcElectricalVehicleVo.class, response);
    }

    /**
     * 获取新能源车详细信息
     *
     * @param evId 主键
     */
    @SaCheckPermission("evEntity:electricalVehicle:query")
    @GetMapping("/{evId}")
    public R<OcElectricalVehicleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long evId) {
        return R.ok(iOcElectricalVehicleService.queryById(evId));
    }

    /**
     * 新增新能源车
     */
    @SaCheckPermission("evEntity:electricalVehicle:add")
    @Log(title = "新能源车", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcElectricalVehicleBo bo) {
        return toAjax(iOcElectricalVehicleService.insertByBo(bo));
    }

    /**
     * 修改新能源车
     */
    @SaCheckPermission("evEntity:electricalVehicle:edit")
    @Log(title = "新能源车", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcElectricalVehicleBo bo) {
        return toAjax(iOcElectricalVehicleService.updateByBo(bo));
    }

    /**
     * 删除新能源车
     *
     * @param evIds 主键串
     */
    @SaCheckPermission("evEntity:electricalVehicle:remove")
    @Log(title = "新能源车", businessType = BusinessType.DELETE)
    @DeleteMapping("/{evIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] evIds) {
        return toAjax(iOcElectricalVehicleService.deleteWithValidByIds(Arrays.asList(evIds), true));
    }
}
