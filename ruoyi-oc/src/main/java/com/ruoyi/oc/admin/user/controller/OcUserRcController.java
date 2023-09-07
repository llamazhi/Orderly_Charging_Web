package com.ruoyi.oc.admin.user.controller;

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
import com.ruoyi.oc.admin.user.domain.vo.OcUserRcVo;
import com.ruoyi.oc.admin.user.domain.bo.OcUserRcBo;
import com.ruoyi.oc.admin.user.service.IOcUserRcService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小区用户
 *
 * @author zhiwei
 * @date 2023-08-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/userRc")
public class OcUserRcController extends BaseController {

    private final IOcUserRcService iOcUserRcService;

    /**
     * 查询小区用户列表
     */
    @SaCheckPermission("users:userRc:list")
    @GetMapping("/list")
    public TableDataInfo<OcUserRcVo> list(OcUserRcBo bo, PageQuery pageQuery) {
        return iOcUserRcService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出小区用户列表
     */
    @SaCheckPermission("users:userRc:export")
    @Log(title = "小区用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OcUserRcBo bo, HttpServletResponse response) {
        List<OcUserRcVo> list = iOcUserRcService.queryList(bo);
        ExcelUtil.exportExcel(list, "小区用户", OcUserRcVo.class, response);
    }

    /**
     * 获取小区用户详细信息
     *
     * @param userId 主键
     */
    @SaCheckPermission("users:userRc:query")
    @GetMapping("/{userId}")
    public R<OcUserRcVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long userId) {
        return R.ok(iOcUserRcService.queryById(userId));
    }

    /**
     * 新增小区用户
     */
    @SaCheckPermission("users:userRc:add")
    @Log(title = "小区用户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OcUserRcBo bo) {
        return toAjax(iOcUserRcService.insertByBo(bo));
    }

    /**
     * 修改小区用户
     */
    @SaCheckPermission("users:userRc:edit")
    @Log(title = "小区用户", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OcUserRcBo bo) {
        return toAjax(iOcUserRcService.updateByBo(bo));
    }

    /**
     * 删除小区用户
     *
     * @param userIds 主键串
     */
    @SaCheckPermission("users:userRc:remove")
    @Log(title = "小区用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] userIds) {
        return toAjax(iOcUserRcService.deleteWithValidByIds(Arrays.asList(userIds), true));
    }
}
