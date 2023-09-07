import request from '@/utils/request'

// 查询变压器列表
export function listTransformer(query) {
  return request({
    url: '/transformer/transformer/list',
    method: 'get',
    params: query
  })
}

// 查询变压器详细
export function getTransformer(transformerId) {
  return request({
    url: '/transformer/transformer/' + transformerId,
    method: 'get'
  })
}

// 新增变压器
export function addTransformer(data) {
  return request({
    url: '/transformer/transformer',
    method: 'post',
    data: data
  })
}

// 修改变压器
export function updateTransformer(data) {
  return request({
    url: '/transformer/transformer',
    method: 'put',
    data: data
  })
}

// 删除变压器
export function delTransformer(transformerId) {
  return request({
    url: '/transformer/transformer/' + transformerId,
    method: 'delete'
  })
}
