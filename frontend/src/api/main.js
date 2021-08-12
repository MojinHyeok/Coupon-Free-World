import { instance } from './index'

function updateUid(data) {
  return instance.post('coupon/update/AFKArenaUID', data)
}

export { updateUid }
