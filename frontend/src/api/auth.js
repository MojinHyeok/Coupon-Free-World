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
// 회원찾기 API
function searchUser(userData) {
  console.log(userData)
  return instance.get(`/user/list/${userData}`)
}
//팔로워찾기
function findFollower(userData) {
  return instance.post('/social/follower', userData)
}
//팔로잉찾기
function findFollowing(userData) {
  return instance.post('/social/following', userData)
}
//팔로우요청 명단 가져오기
function findrequestFollow(userData) {
  return instance.post('/social/findrequestFollow', userData)
}
//팔로우 요청하기
function requestFollow(userData) {
  return instance.post('/social/requestFollow', userData)
}
//팔로우 요청수락하기
function allowFollow(userData) {
  return instance.post('/social/allowFollow', userData)
}

export {
  registerUser,
  loginUser,
  fetchUser,
  editUser,
  deleteUser,
  searchUser,
  findFollower,
  findFollowing,
  findrequestFollow,
  requestFollow,
  allowFollow,
}
