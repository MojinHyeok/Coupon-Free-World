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

// 회원탈퇴 API
function deleteUser(userData) {
  return instance.post('user/delete', userData)
}

// 회원검색 API
function searchUser(userData) {
  return instance.get(`user/list/${userData}`)
}

export { registerUser, loginUser, fetchUser, editUser, deleteUser, searchUser }
