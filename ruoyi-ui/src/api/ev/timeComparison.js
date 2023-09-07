import request from '@/utils/request'

// 查询Time Comparison列表
export function listTimeComparison(query) {
  return request({
    url: '/ev/timeComparison/list',
    method: 'get',
    params: query
  })
}

// 查询Time Comparison详细
export function getTimeComparison(id) {
  return request({
    url: '/ev/timeComparison/' + id,
    method: 'get'
  })
}

// 新增Time Comparison
export function addTimeComparison(data) {
  return request({
    url: '/ev/timeComparison',
    method: 'post',
    data: data
  })
}

// 修改Time Comparison
export function updateTimeComparison(data) {
  return request({
    url: '/ev/timeComparison',
    method: 'put',
    data: data
  })
}

// 删除Time Comparison
export function delTimeComparison(id) {
  return request({
    url: '/ev/timeComparison/' + id,
    method: 'delete'
  })
}
