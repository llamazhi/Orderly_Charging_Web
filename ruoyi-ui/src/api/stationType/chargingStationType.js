import request from '@/utils/request'

// 查询充电桩类型列表
export function listChargingStationType(query) {
  return request({
    url: '/stationType/chargingStationType/list',
    method: 'get',
    params: query
  })
}

// 查询充电桩类型详细
export function getChargingStationType(typeId) {
  return request({
    url: '/stationType/chargingStationType/' + typeId,
    method: 'get'
  })
}

// 新增充电桩类型
export function addChargingStationType(data) {
  return request({
    url: '/stationType/chargingStationType',
    method: 'post',
    data: data
  })
}

// 修改充电桩类型
export function updateChargingStationType(data) {
  return request({
    url: '/stationType/chargingStationType',
    method: 'put',
    data: data
  })
}

// 删除充电桩类型
export function delChargingStationType(typeId) {
  return request({
    url: '/stationType/chargingStationType/' + typeId,
    method: 'delete'
  })
}
