// 회원 가입
import { instance } from './index'

// 회원가입 API
function registerUser(userData) {
  return instance.post('user/join', userData)
}

// 로그인 API
function loginUser(userData) {
  return instance.post('login/confirm', userData)
}

// 회원정보가져오기 API
function fetchUser(userID) {
  return instance.post('user/info', userID)
}

// 회원정보수정 API
function editUser(userData) {
  return instance.post('user/modify', userData)
}

export { registerUser, loginUser, fetchUser, editUser }
