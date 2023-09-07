package com.ruoyi.oc.admin.ev.params.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.oc.admin.ev.params.domain.OcElectricalVehicleParameters;
import com.ruoyi.oc.admin.ev.params.domain.vo.OcElectricalVehicleParametersVo;
import com.ruoyi.oc.admin.ev.params.domain.bo.OcElectricalVehicleParametersBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.oc.client.records.domain.OcChargingRecords;

import java.util.Collection;
import java.util.List;

/**
 * 新能源车参数Service接口
 *
 * @author zhiwei
 * @date 2023-08-23
 */
public interface IOcElectricalVehicleParametersService extends IService<OcElectricalVehicleParameters> {

    /**
     * 查询新能源车参数
     */
    OcElectricalVehicleParametersVo queryById(Long typeId);

    /**
     * 查询新能源车参数列表
     */
    TableDataInfo<OcElectricalVehicleParametersVo> queryPageList(OcElectricalVehicleParametersBo bo, PageQuery pageQuery);

    /**
     * 查询新能源车参数列表
     */
    List<OcElectricalVehicleParametersVo> queryList(OcElectricalVehicleParametersBo bo);

    /**
     * 新增新能源车参数
     */
    Boolean insertByBo(OcElectricalVehicleParametersBo bo);

    /**
     * 修改新能源车参数
     */
    Boolean updateByBo(OcElectricalVehicleParametersBo bo);

    /**
     * 校验并批量删除新能源车参数信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
