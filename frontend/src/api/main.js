import { instance } from './index'

function updateUid(data) {
  return instance.post('coupon/update/AFKArenaUID', data)
}
function getUserUid(data) {
  return instance.get(`coupon/getGameUID/${data}`)
}
function registCoupon(data) {
  return instance.post('coupon/regist/AFKArena', data)
}

export { updateUid, getUserUid, registCoupon }
