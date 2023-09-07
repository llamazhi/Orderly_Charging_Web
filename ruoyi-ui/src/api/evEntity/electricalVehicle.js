import request from '@/utils/request'

// 查询新能源车列表
export function listElectricalVehicle(query) {
  return request({
    url: '/evEntity/electricalVehicle/list',
    method: 'get',
    params: query
  })
}

// 查询新能源车详细
export function getElectricalVehicle(evId) {
  return request({
    url: '/evEntity/electricalVehicle/' + evId,
    method: 'get'
  })
}

// 新增新能源车
export function addElectricalVehicle(data) {
  return request({
    url: '/evEntity/electricalVehicle',
    method: 'post',
    data: data
  })
}

// 修改新能源车
export function updateElectricalVehicle(data) {
  return request({
    url: '/evEntity/electricalVehicle',
    method: 'put',
    data: data
  })
}

// 删除新能源车
export function delElectricalVehicle(evId) {
  return request({
    url: '/evEntity/electricalVehicle/' + evId,
    method: 'delete'
  })
}
