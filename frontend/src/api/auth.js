// 회원 가입
import { instance } from './index'

// 회원가입 API
function registerUser(userData) {
  return instance.post('/account/signup', userData)
}

export { registerUser }
