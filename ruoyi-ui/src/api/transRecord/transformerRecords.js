import request from '@/utils/request'

// 查询变压器记录列表
export function listTransformerRecords(query) {
  return request({
    url: '/transRecord/transformerRecords/list',
    method: 'get',
    params: query
  })
}

// 查询变压器记录详细
export function getTransformerRecords(recordId) {
  return request({
    url: '/transRecord/transformerRecords/' + recordId,
    method: 'get'
  })
}

// 新增变压器记录
export function addTransformerRecords(data) {
  return request({
    url: '/transRecord/transformerRecords',
    method: 'post',
    data: data
  })
}

// 修改变压器记录
export function updateTransformerRecords(data) {
  return request({
    url: '/transRecord/transformerRecords',
    method: 'put',
    data: data
  })
}

// 删除变压器记录
export function delTransformerRecords(recordId) {
  return request({
    url: '/transRecord/transformerRecords/' + recordId,
    method: 'delete'
  })
}
