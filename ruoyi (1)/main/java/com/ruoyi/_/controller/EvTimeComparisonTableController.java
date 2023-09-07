package com.ruoyi..controller;

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
import com.ruoyi..domain.vo.EvTimeComparisonTableVo;
import com.ruoyi..domain.bo.EvTimeComparisonTableBo;
import com.ruoyi..service.IEvTimeComparisonTableService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】
 *
 * @author zhiwei
 * @date 2023-08-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("//timeComparisonTable")
public class EvTimeComparisonTableController extends BaseController {

    private final IEvTimeComparisonTableService iEvTimeComparisonTableService;

    /**
     * 查询【请填写功能名称】列表
     */
    @SaCheckPermission(":timeComparisonTable:list")
    @GetMapping("/list")
    public TableDataInfo<EvTimeComparisonTableVo> list(EvTimeComparisonTableBo bo, PageQuery pageQuery) {
        return iEvTimeComparisonTableService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @SaCheckPermission(":timeComparisonTable:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EvTimeComparisonTableBo bo, HttpServletResponse response) {
        List<EvTimeComparisonTableVo> list = iEvTimeComparisonTableService.queryList(bo);
        ExcelUtil.exportExcel(list, "【请填写功能名称】", EvTimeComparisonTableVo.class, response);
    }

    /**
     * 获取【请填写功能名称】详细信息
     *
     * @param uid 主键
     */
    @SaCheckPermission(":timeComparisonTable:query")
    @GetMapping("/{uid}")
    public R<EvTimeComparisonTableVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String uid) {
        return R.ok(iEvTimeComparisonTableService.queryById(uid));
    }

    /**
     * 新增【请填写功能名称】
     */
    @SaCheckPermission(":timeComparisonTable:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EvTimeComparisonTableBo bo) {
        return toAjax(iEvTimeComparisonTableService.insertByBo(bo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @SaCheckPermission(":timeComparisonTable:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EvTimeComparisonTableBo bo) {
        return toAjax(iEvTimeComparisonTableService.updateByBo(bo));
    }

    /**
     * 删除【请填写功能名称】
     *
     * @param uids 主键串
     */
    @SaCheckPermission(":timeComparisonTable:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] uids) {
        return toAjax(iEvTimeComparisonTableService.deleteWithValidByIds(Arrays.asList(uids), true));
    }
}
