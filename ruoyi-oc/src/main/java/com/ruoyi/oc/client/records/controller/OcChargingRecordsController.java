package com.ruoyi.oc.client.records.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.oc.client.records.service.impl.OcChargingRecordsServiceImpl;
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
import com.ruoyi.oc.client.records.domain.vo.OcChargingRecordsVo;
import com.ruoyi.oc.client.records.domain.bo.OcChargingRecordsBo;
import com.ruoyi.oc.client.records.service.IOcChargingRecordsService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充电记录
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/records/chargingRecords")
public class OcChargingRecordsController extends BaseController {

    private final IOcChargingRecordsService iOcChargingRecordsService;


//    /**
//     * 查询充电记录列表
//     */
//    @SaCheckPermission("charging:chargingRecords:list")
//    @GetMapping("/optimize")
//    public String optimize(Long evId) {
//        return iOcChargingRecordsService.optimize(evId);
//    }

    @SaCheckPermission("charging:chargingRecords:list")
    @GetMapping("/optimize")
    public String optimize(@RequestParam(required = false) Long evId) {
        if (evId == null) return  iOcChargingRecordsService.optimize();
        else return iOcChargingRecordsService.optimize(evId);
    }

    /**
     * 查询充电记录列表
     */
    @SaCheckPermission("records:chargingRecords:list")
    @GetMapping("/list")
    public TableDataInfo<OcChargingRecordsVo> list(OcChargingRecordsBo bo, PageQuery pageQuery) {
        return iOcChargingRecordsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出充电记录列表
     */
    @SaCheckPermission("records:chargingRecords:export")
    @Log(title = "充电记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcChargingRecordsBo bo, HttpServletResponse response) {
        List<OcChargingRecordsVo> list = iOcChargingRecordsService.queryList(bo);
        ExcelUtil.exportExcel(list, "充电记录", OcChargingRecordsVo.class, response);
    }

    /**
     * 获取充电记录详细信息
     *
     * @param recordId 主键
     */
    @SaCheckPermission("records:chargingRecords:query")
    @GetMapping("/{recordId}")
    public R<OcChargingRecordsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long recordId) {
        return R.ok(iOcChargingRecordsService.queryById(recordId));
    }

    /**
     * 新增充电记录
     */
    @SaCheckPermission("records:chargingRecords:add")
    @Log(title = "充电记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcChargingRecordsBo bo) {
        return toAjax(iOcChargingRecordsService.insertByBo(bo));
    }

    /**
     * 修改充电记录
     */
    @SaCheckPermission("records:chargingRecords:edit")
    @Log(title = "充电记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcChargingRecordsBo bo) {
        return toAjax(iOcChargingRecordsService.updateByBo(bo));
    }

    /**
     * 删除充电记录
     *
     * @param recordIds 主键串
     */
    @SaCheckPermission("records:chargingRecords:remove")
    @Log(title = "充电记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] recordIds) {
        return toAjax(iOcChargingRecordsService.deleteWithValidByIds(Arrays.asList(recordIds), true));
    }
}
