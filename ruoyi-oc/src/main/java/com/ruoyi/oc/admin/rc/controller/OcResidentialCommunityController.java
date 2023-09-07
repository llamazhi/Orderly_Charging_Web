package com.ruoyi.oc.admin.rc.controller;

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
import com.ruoyi.oc.admin.rc.domain.vo.OcResidentialCommunityVo;
import com.ruoyi.oc.admin.rc.domain.bo.OcResidentialCommunityBo;
import com.ruoyi.oc.admin.rc.service.IOcResidentialCommunityService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小区
 *
 * @author zhiwei
 * @date 2023-08-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/rc/residentialCommunity")
public class OcResidentialCommunityController extends BaseController {

    private final IOcResidentialCommunityService iOcResidentialCommunityService;

    /**
     * 查询小区列表
     */
    @SaCheckPermission("rc:residentialCommunity:list")
    @GetMapping("/list")
    public TableDataInfo<OcResidentialCommunityVo> list(OcResidentialCommunityBo bo, PageQuery pageQuery) {
        return iOcResidentialCommunityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出小区列表
     */
    @SaCheckPermission("rc:residentialCommunity:export")
    @Log(title = "小区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcResidentialCommunityBo bo, HttpServletResponse response) {
        List<OcResidentialCommunityVo> list = iOcResidentialCommunityService.queryList(bo);
        ExcelUtil.exportExcel(list, "小区", OcResidentialCommunityVo.class, response);
    }

    /**
     * 获取小区详细信息
     *
     * @param rcId 主键
     */
    @SaCheckPermission("rc:residentialCommunity:query")
    @GetMapping("/{rcId}")
    public R<OcResidentialCommunityVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long rcId) {
        return R.ok(iOcResidentialCommunityService.queryById(rcId));
    }

    /**
     * 新增小区
     */
    @SaCheckPermission("rc:residentialCommunity:add")
    @Log(title = "小区", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcResidentialCommunityBo bo) {
        return toAjax(iOcResidentialCommunityService.insertByBo(bo));
    }

    /**
     * 修改小区
     */
    @SaCheckPermission("rc:residentialCommunity:edit")
    @Log(title = "小区", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcResidentialCommunityBo bo) {
        return toAjax(iOcResidentialCommunityService.updateByBo(bo));
    }

    /**
     * 删除小区
     *
     * @param rcIds 主键串
     */
    @SaCheckPermission("rc:residentialCommunity:remove")
    @Log(title = "小区", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rcIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] rcIds) {
        return toAjax(iOcResidentialCommunityService.deleteWithValidByIds(Arrays.asList(rcIds), true));
    }
}
