package com.ruoyi.oc.admin.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.oc.admin.user.domain.OcUserRc;
import com.ruoyi.oc.admin.user.domain.vo.OcUserRcVo;
import com.ruoyi.oc.admin.user.domain.bo.OcUserRcBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;

import java.util.Collection;
import java.util.List;

/**
 * 小区用户Service接口
 *
 * @author zhiwei
 * @date 2023-08-30
 */
public interface IOcUserRcService extends IService<OcUserRc> {

    /**
     * 查询小区用户
     */
    OcUserRcVo queryById(Long userId);

    /**
     * 查询小区用户列表
     */
    TableDataInfo<OcUserRcVo> queryPageList(OcUserRcBo bo, PageQuery pageQuery);

    /**
     * 查询小区用户列表
     */
    List<OcUserRcVo> queryList(OcUserRcBo bo);

    /**
     * 新增小区用户
     */
    Boolean insertByBo(OcUserRcBo bo);

    /**
     * 修改小区用户
     */
    Boolean updateByBo(OcUserRcBo bo);

    /**
     * 校验并批量删除小区用户信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
