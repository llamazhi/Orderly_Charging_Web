package com.ruoyi.oc.admin.rc.service;

import com.ruoyi.oc.admin.rc.domain.OcResidentialCommunity;
import com.ruoyi.oc.admin.rc.domain.vo.OcResidentialCommunityVo;
import com.ruoyi.oc.admin.rc.domain.bo.OcResidentialCommunityBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 小区Service接口
 *
 * @author zhiwei
 * @date 2023-08-23
 */
public interface IOcResidentialCommunityService {

    /**
     * 查询小区
     */
    OcResidentialCommunityVo queryById(Long rcId);

    /**
     * 查询小区列表
     */
    TableDataInfo<OcResidentialCommunityVo> queryPageList(OcResidentialCommunityBo bo, PageQuery pageQuery);

    /**
     * 查询小区列表
     */
    List<OcResidentialCommunityVo> queryList(OcResidentialCommunityBo bo);

    /**
     * 新增小区
     */
    Boolean insertByBo(OcResidentialCommunityBo bo);

    /**
     * 修改小区
     */
    Boolean updateByBo(OcResidentialCommunityBo bo);

    /**
     * 校验并批量删除小区信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
