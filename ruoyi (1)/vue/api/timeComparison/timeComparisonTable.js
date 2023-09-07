import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listTimeComparisonTable(query) {
  return request({
    url: '//timeComparisonTable/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getTimeComparisonTable(uid) {
  return request({
    url: '//timeComparisonTable/' + uid,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addTimeComparisonTable(data) {
  return request({
    url: '//timeComparisonTable',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateTimeComparisonTable(data) {
  return request({
    url: '//timeComparisonTable',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delTimeComparisonTable(uid) {
  return request({
    url: '//timeComparisonTable/' + uid,
    method: 'delete'
  })
}
