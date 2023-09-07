import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listComparisonTable(query) {
  return request({
    url: '//comparisonTable/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getComparisonTable(uid) {
  return request({
    url: '//comparisonTable/' + uid,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addComparisonTable(data) {
  return request({
    url: '//comparisonTable',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateComparisonTable(data) {
  return request({
    url: '//comparisonTable',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delComparisonTable(uid) {
  return request({
    url: '//comparisonTable/' + uid,
    method: 'delete'
  })
}
