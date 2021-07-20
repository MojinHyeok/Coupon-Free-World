// 회원 가입
import { instance } from './index'

// 회원가입 API
function registerUser(userData) {
  return instance.post('account/signup', userData)
}

// 로그인 API
function loginUser(userData) {
  console.log('여기?')
  return instance.post('login/confirm', userData)
}

export { registerUser, loginUser }
