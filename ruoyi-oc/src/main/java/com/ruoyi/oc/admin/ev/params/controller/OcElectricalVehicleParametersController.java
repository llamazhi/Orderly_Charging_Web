package com.ruoyi.oc.admin.ev.params.controller;

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
import com.ruoyi.oc.admin.ev.params.domain.vo.OcElectricalVehicleParametersVo;
import com.ruoyi.oc.admin.ev.params.domain.bo.OcElectricalVehicleParametersBo;
import com.ruoyi.oc.admin.ev.params.service.IOcElectricalVehicleParametersService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新能源车参数
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/evParams/electricalVehicleParameters")
public class OcElectricalVehicleParametersController extends BaseController {

    private final IOcElectricalVehicleParametersService iOcElectricalVehicleParametersService;

    /**
     * 查询新能源车参数列表
     */
    @SaCheckPermission("evParams:electricalVehicleParameters:list")
    @GetMapping("/list")
    public TableDataInfo<OcElectricalVehicleParametersVo> list(OcElectricalVehicleParametersBo bo, PageQuery pageQuery) {
        return iOcElectricalVehicleParametersService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出新能源车参数列表
     */
    @SaCheckPermission("evParams:electricalVehicleParameters:export")
    @Log(title = "新能源车参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcElectricalVehicleParametersBo bo, HttpServletResponse response) {
        List<OcElectricalVehicleParametersVo> list = iOcElectricalVehicleParametersService.queryList(bo);
        ExcelUtil.exportExcel(list, "新能源车参数", OcElectricalVehicleParametersVo.class, response);
    }

    /**
     * 获取新能源车参数详细信息
     *
     * @param typeId 主键
     */
    @SaCheckPermission("evParams:electricalVehicleParameters:query")
    @GetMapping("/{typeId}")
    public R<OcElectricalVehicleParametersVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long typeId) {
        return R.ok(iOcElectricalVehicleParametersService.queryById(typeId));
    }

    /**
     * 新增新能源车参数
     */
    @SaCheckPermission("evParams:electricalVehicleParameters:add")
    @Log(title = "新能源车参数", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcElectricalVehicleParametersBo bo) {
        return toAjax(iOcElectricalVehicleParametersService.insertByBo(bo));
    }

    /**
     * 修改新能源车参数
     */
    @SaCheckPermission("evParams:electricalVehicleParameters:edit")
    @Log(title = "新能源车参数", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcElectricalVehicleParametersBo bo) {
        return toAjax(iOcElectricalVehicleParametersService.updateByBo(bo));
    }

    /**
     * 删除新能源车参数
     *
     * @param typeIds 主键串
     */
    @SaCheckPermission("evParams:electricalVehicleParameters:remove")
    @Log(title = "新能源车参数", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] typeIds) {
        return toAjax(iOcElectricalVehicleParametersService.deleteWithValidByIds(Arrays.asList(typeIds), true));
    }
}
