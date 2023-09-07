import request from '@/utils/request'

// 查询充电桩列表
export function listChargingStation(query) {
  return request({
    url: '/station/chargingStation/list',
    method: 'get',
    params: query
  })
}

// 查询充电桩详细
export function getChargingStation(csId) {
  return request({
    url: '/station/chargingStation/' + csId,
    method: 'get'
  })
}

// 新增充电桩
export function addChargingStation(data) {
  return request({
    url: '/station/chargingStation',
    method: 'post',
    data: data
  })
}

// 修改充电桩
export function updateChargingStation(data) {
  return request({
    url: '/station/chargingStation',
    method: 'put',
    data: data
  })
}

// 删除充电桩
export function delChargingStation(csId) {
  return request({
    url: '/station/chargingStation/' + csId,
    method: 'delete'
  })
}
