import request from '@/utils/request'

// 查询小区列表
export function listResidentialCommunity(query) {
  return request({
    url: '/rc/residentialCommunity/list',
    method: 'get',
    params: query
  })
}

// 查询小区详细
export function getResidentialCommunity(rcId) {
  return request({
    url: '/rc/residentialCommunity/' + rcId,
    method: 'get'
  })
}

// 新增小区
export function addResidentialCommunity(data) {
  return request({
    url: '/rc/residentialCommunity',
    method: 'post',
    data: data
  })
}

// 修改小区
export function updateResidentialCommunity(data) {
  return request({
    url: '/rc/residentialCommunity',
    method: 'put',
    data: data
  })
}

// 删除小区
export function delResidentialCommunity(rcId) {
  return request({
    url: '/rc/residentialCommunity/' + rcId,
    method: 'delete'
  })
}
