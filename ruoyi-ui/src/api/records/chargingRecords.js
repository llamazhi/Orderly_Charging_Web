import request from '@/utils/request'

// 查询充电记录列表
export function listChargingRecords(query) {
  return request({
    url: '/records/chargingRecords/list',
    method: 'get',
    params: query
  })
}

// 查询充电记录详细
export function getChargingRecords(recordId) {
  return request({
    url: '/records/chargingRecords/' + recordId,
    method: 'get'
  })
}

// 新增充电记录
export function addChargingRecords(data) {
  return request({
    url: '/records/chargingRecords',
    method: 'post',
    data: data
  })
}

// 修改充电记录
export function updateChargingRecords(data) {
  return request({
    url: '/records/chargingRecords',
    method: 'put',
    data: data
  })
}

// 删除充电记录
export function delChargingRecords(recordId) {
  return request({
    url: '/records/chargingRecords/' + recordId,
    method: 'delete'
  })
}
