import request from '@/utils/request'

export function getTestData() {
  return request({
    url: 'api/test',
    method: 'get'
  })
}
