package com.ruoyi.oc.admin.ev.entity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.oc.admin.ev.entity.domain.OcElectricalVehicle;
import com.ruoyi.oc.admin.ev.entity.domain.vo.OcElectricalVehicleVo;
import com.ruoyi.oc.admin.ev.entity.domain.bo.OcElectricalVehicleBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 新能源车Service接口
 *
 * @author zhiwei
 * @date 2023-08-23
 */
public interface IOcElectricalVehicleService extends IService<OcElectricalVehicle> {

    /**
     * 查询新能源车
     */
    OcElectricalVehicleVo queryById(Long evId);

    /**
     * 查询新能源车列表
     */
    TableDataInfo<OcElectricalVehicleVo> queryPageList(OcElectricalVehicleBo bo, PageQuery pageQuery);

    /**
     * 查询新能源车列表
     */
    List<OcElectricalVehicleVo> queryList(OcElectricalVehicleBo bo);

    /**
     * 新增新能源车
     */
    Boolean insertByBo(OcElectricalVehicleBo bo);

    /**
     * 修改新能源车
     */
    Boolean updateByBo(OcElectricalVehicleBo bo);

    /**
     * 校验并批量删除新能源车信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
