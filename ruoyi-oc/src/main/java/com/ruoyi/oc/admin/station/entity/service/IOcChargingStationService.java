package com.ruoyi.oc.admin.station.entity.service;

import com.ruoyi.oc.admin.station.entity.domain.OcChargingStation;
import com.ruoyi.oc.admin.station.entity.domain.vo.OcChargingStationVo;
import com.ruoyi.oc.admin.station.entity.domain.bo.OcChargingStationBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 充电桩Service接口
 *
 * @author zhiwei
 * @date 2023-08-23
 */
public interface IOcChargingStationService {

    /**
     * 查询充电桩
     */
    OcChargingStationVo queryById(Long csId);

    /**
     * 查询充电桩列表
     */
    TableDataInfo<OcChargingStationVo> queryPageList(OcChargingStationBo bo, PageQuery pageQuery);

    /**
     * 查询充电桩列表
     */
    List<OcChargingStationVo> queryList(OcChargingStationBo bo);

    /**
     * 新增充电桩
     */
    Boolean insertByBo(OcChargingStationBo bo);

    /**
     * 修改充电桩
     */
    Boolean updateByBo(OcChargingStationBo bo);

    /**
     * 校验并批量删除充电桩信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
