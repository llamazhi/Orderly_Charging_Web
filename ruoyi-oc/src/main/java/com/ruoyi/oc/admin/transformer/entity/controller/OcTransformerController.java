package com.ruoyi.oc.admin.transformer.entity.controller;

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
import com.ruoyi.oc.admin.transformer.entity.domain.vo.OcTransformerVo;
import com.ruoyi.oc.admin.transformer.entity.domain.bo.OcTransformerBo;
import com.ruoyi.oc.admin.transformer.entity.service.IOcTransformerService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 变压器
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/transformer/transformer")
public class OcTransformerController extends BaseController {

    private final IOcTransformerService iOcTransformerService;

    /**
     * 查询变压器列表
     */
    @SaCheckPermission("transformer:transformer:list")
    @GetMapping("/list")
    public TableDataInfo<OcTransformerVo> list(OcTransformerBo bo, PageQuery pageQuery) {
        return iOcTransformerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出变压器列表
     */
    @SaCheckPermission("transformer:transformer:export")
    @Log(title = "变压器", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcTransformerBo bo, HttpServletResponse response) {
        List<OcTransformerVo> list = iOcTransformerService.queryList(bo);
        ExcelUtil.exportExcel(list, "变压器", OcTransformerVo.class, response);
    }

    /**
     * 获取变压器详细信息
     *
     * @param transformerId 主键
     */
    @SaCheckPermission("transformer:transformer:query")
    @GetMapping("/{transformerId}")
    public R<OcTransformerVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long transformerId) {
        return R.ok(iOcTransformerService.queryById(transformerId));
    }

    /**
     * 新增变压器
     */
    @SaCheckPermission("transformer:transformer:add")
    @Log(title = "变压器", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcTransformerBo bo) {
        return toAjax(iOcTransformerService.insertByBo(bo));
    }

    /**
     * 修改变压器
     */
    @SaCheckPermission("transformer:transformer:edit")
    @Log(title = "变压器", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcTransformerBo bo) {
        return toAjax(iOcTransformerService.updateByBo(bo));
    }

    /**
     * 删除变压器
     *
     * @param transformerIds 主键串
     */
    @SaCheckPermission("transformer:transformer:remove")
    @Log(title = "变压器", businessType = BusinessType.DELETE)
    @DeleteMapping("/{transformerIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] transformerIds) {
        return toAjax(iOcTransformerService.deleteWithValidByIds(Arrays.asList(transformerIds), true));
    }
}
