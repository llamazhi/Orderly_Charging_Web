package com.ruoyi.oc.admin.transformer.records.controller;

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
import com.ruoyi.oc.admin.transformer.records.domain.vo.OcTransformerRecordsVo;
import com.ruoyi.oc.admin.transformer.records.domain.bo.OcTransformerRecordsBo;
import com.ruoyi.oc.admin.transformer.records.service.IOcTransformerRecordsService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 变压器记录
 *
 * @author zhiwei
 * @date 2023-08-29
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/transRecord/transformerRecords")
public class OcTransformerRecordsController extends BaseController {

    private final IOcTransformerRecordsService iOcTransformerRecordsService;

    /**
     * 查询变压器记录列表
     */
    @SaCheckPermission("transRecord:transformerRecords:list")
    @GetMapping("/list")
    public TableDataInfo<OcTransformerRecordsVo> list(OcTransformerRecordsBo bo, PageQuery pageQuery) {
        return iOcTransformerRecordsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出变压器记录列表
     */
    @SaCheckPermission("transRecord:transformerRecords:export")
    @Log(title = "变压器记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcTransformerRecordsBo bo, HttpServletResponse response) {
        List<OcTransformerRecordsVo> list = iOcTransformerRecordsService.queryList(bo);
        ExcelUtil.exportExcel(list, "变压器记录", OcTransformerRecordsVo.class, response);
    }

    /**
     * 获取变压器记录详细信息
     *
     * @param recordId 主键
     */
    @SaCheckPermission("transRecord:transformerRecords:query")
    @GetMapping("/{recordId}")
    public R<OcTransformerRecordsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long recordId) {
        return R.ok(iOcTransformerRecordsService.queryById(recordId));
    }

    /**
     * 新增变压器记录
     */
    @SaCheckPermission("transRecord:transformerRecords:add")
    @Log(title = "变压器记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcTransformerRecordsBo bo) {
        return toAjax(iOcTransformerRecordsService.insertByBo(bo));
    }

    /**
     * 修改变压器记录
     */
    @SaCheckPermission("transRecord:transformerRecords:edit")
    @Log(title = "变压器记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcTransformerRecordsBo bo) {
        return toAjax(iOcTransformerRecordsService.updateByBo(bo));
    }

    /**
     * 删除变压器记录
     *
     * @param recordIds 主键串
     */
    @SaCheckPermission("transRecord:transformerRecords:remove")
    @Log(title = "变压器记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] recordIds) {
        return toAjax(iOcTransformerRecordsService.deleteWithValidByIds(Arrays.asList(recordIds), true));
    }
}
