package com.ruoyi.oc.admin.station.types.service;

import com.ruoyi.oc.admin.station.types.domain.OcChargingStationType;
import com.ruoyi.oc.admin.station.types.domain.vo.OcChargingStationTypeVo;
import com.ruoyi.oc.admin.station.types.domain.bo.OcChargingStationTypeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 充电桩类型Service接口
 *
 * @author zhiwei
 * @date 2023-08-23
 */
public interface IOcChargingStationTypeService {

    /**
     * 查询充电桩类型
     */
    OcChargingStationTypeVo queryById(Long typeId);

    /**
     * 查询充电桩类型列表
     */
    TableDataInfo<OcChargingStationTypeVo> queryPageList(OcChargingStationTypeBo bo, PageQuery pageQuery);

    /**
     * 查询充电桩类型列表
     */
    List<OcChargingStationTypeVo> queryList(OcChargingStationTypeBo bo);

    /**
     * 新增充电桩类型
     */
    Boolean insertByBo(OcChargingStationTypeBo bo);

    /**
     * 修改充电桩类型
     */
    Boolean updateByBo(OcChargingStationTypeBo bo);

    /**
     * 校验并批量删除充电桩类型信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
