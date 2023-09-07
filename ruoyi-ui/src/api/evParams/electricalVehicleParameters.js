import request from '@/utils/request'

// 查询新能源车参数列表
export function listElectricalVehicleParameters(query) {
  return request({
    url: '/evParams/electricalVehicleParameters/list',
    method: 'get',
    params: query
  })
}

// 查询新能源车参数详细
export function getElectricalVehicleParameters(typeId) {
  return request({
    url: '/evParams/electricalVehicleParameters/' + typeId,
    method: 'get'
  })
}

// 新增新能源车参数
export function addElectricalVehicleParameters(data) {
  return request({
    url: '/evParams/electricalVehicleParameters',
    method: 'post',
    data: data
  })
}

// 修改新能源车参数
export function updateElectricalVehicleParameters(data) {
  return request({
    url: '/evParams/electricalVehicleParameters',
    method: 'put',
    data: data
  })
}

// 删除新能源车参数
export function delElectricalVehicleParameters(typeId) {
  return request({
    url: '/evParams/electricalVehicleParameters/' + typeId,
    method: 'delete'
  })
}
