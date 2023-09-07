package com.ruoyi.oc.controller;

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
import com.ruoyi.oc.domain.vo.EvTimeComparisonTableVo;
import com.ruoyi.oc.domain.bo.EvTimeComparisonTableBo;
import com.ruoyi.oc.service.IEvTimeComparisonTableService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * Time Comparison
 *
 * @author zhiwei
 * @date 2023-08-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ev/timeComparison")
public class EvTimeComparisonTableController extends BaseController {

    private final IEvTimeComparisonTableService iEvTimeComparisonTableService;

    /**
     * 查询Time Comparison列表
     */
    @SaCheckPermission("ev:timeComparison:list")
    @GetMapping("/list")
    public TableDataInfo<EvTimeComparisonTableVo> list(EvTimeComparisonTableBo bo, PageQuery pageQuery) {
        return iEvTimeComparisonTableService.queryPageList(bo, pageQuery);

    }

    /**
     * 导出Time Comparison列表
     */
    @SaCheckPermission("ev:timeComparison:export")
    @Log(title = "Time Comparison", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EvTimeComparisonTableBo bo, HttpServletResponse response) {
        List<EvTimeComparisonTableVo> list = iEvTimeComparisonTableService.queryList(bo);
        ExcelUtil.exportExcel(list, "Time Comparison", EvTimeComparisonTableVo.class, response);
    }

    /**
     * 获取Time Comparison详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ev:timeComparison:query")
    @GetMapping("/{id}")
    public R<EvTimeComparisonTableVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iEvTimeComparisonTableService.queryById(id));
    }

    /**
     * 新增Time Comparison
     */
    @SaCheckPermission("ev:timeComparison:add")
    @Log(title = "Time Comparison", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EvTimeComparisonTableBo bo) {
        return toAjax(iEvTimeComparisonTableService.insertByBo(bo));
    }

    /**
     * 修改Time Comparison
     */
    @SaCheckPermission("ev:timeComparison:edit")
    @Log(title = "Time Comparison", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EvTimeComparisonTableBo bo) {
        return toAjax(iEvTimeComparisonTableService.updateByBo(bo));
    }

    /**
     * 删除Time Comparison
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ev:timeComparison:remove")
    @Log(title = "Time Comparison", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iEvTimeComparisonTableService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

    /**
     * 查询Time Comparison列表
     */
    @SaCheckPermission("ev:timeComparison:calculate")
    @GetMapping("/calculate")
    public R<Void> calculate() {
        return R.ok();
    }

}
