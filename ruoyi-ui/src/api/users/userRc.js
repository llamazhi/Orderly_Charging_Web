import request from '@/utils/request'

// 查询小区用户列表
export function listUserRc(query) {
  return request({
    url: '/users/userRc/list',
    method: 'get',
    params: query
  })
}

// 查询小区用户详细
export function getUserRc(userId) {
  return request({
    url: '/users/userRc/' + userId,
    method: 'get'
  })
}

// 新增小区用户
export function addUserRc(data) {
  return request({
    url: '/users/userRc',
    method: 'post',
    data: data
  })
}

// 修改小区用户
export function updateUserRc(data) {
  return request({
    url: '/users/userRc',
    method: 'put',
    data: data
  })
}

// 删除小区用户
export function delUserRc(userId) {
  return request({
    url: '/users/userRc/' + userId,
    method: 'delete'
  })
}
