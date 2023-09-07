package com.ruoyi.oc.admin.station.entity.controller;

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
import com.ruoyi.oc.admin.station.entity.domain.vo.OcChargingStationVo;
import com.ruoyi.oc.admin.station.entity.domain.bo.OcChargingStationBo;
import com.ruoyi.oc.admin.station.entity.service.IOcChargingStationService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电桩
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/station/chargingStation")
public class OcChargingStationController extends BaseController {

    private final IOcChargingStationService iOcChargingStationService;

    /**
     * 查询充电桩列表
     */
    @SaCheckPermission("station:chargingStation:list")
    @GetMapping("/list")
    public TableDataInfo<OcChargingStationVo> list(OcChargingStationBo bo, PageQuery pageQuery) {
        return iOcChargingStationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出充电桩列表
     */
    @SaCheckPermission("station:chargingStation:export")
    @Log(title = "充电桩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcChargingStationBo bo, HttpServletResponse response) {
        List<OcChargingStationVo> list = iOcChargingStationService.queryList(bo);
        ExcelUtil.exportExcel(list, "充电桩", OcChargingStationVo.class, response);
    }

    /**
     * 获取充电桩详细信息
     *
     * @param csId 主键
     */
    @SaCheckPermission("station:chargingStation:query")
    @GetMapping("/{csId}")
    public R<OcChargingStationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long csId) {
        return R.ok(iOcChargingStationService.queryById(csId));
    }

    /**
     * 新增充电桩
     */
    @SaCheckPermission("station:chargingStation:add")
    @Log(title = "充电桩", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcChargingStationBo bo) {
        return toAjax(iOcChargingStationService.insertByBo(bo));
    }

    /**
     * 修改充电桩
     */
    @SaCheckPermission("station:chargingStation:edit")
    @Log(title = "充电桩", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcChargingStationBo bo) {
        return toAjax(iOcChargingStationService.updateByBo(bo));
    }

    /**
     * 删除充电桩
     *
     * @param csIds 主键串
     */
    @SaCheckPermission("station:chargingStation:remove")
    @Log(title = "充电桩", businessType = BusinessType.DELETE)
    @DeleteMapping("/{csIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] csIds) {
        return toAjax(iOcChargingStationService.deleteWithValidByIds(Arrays.asList(csIds), true));
    }
}
