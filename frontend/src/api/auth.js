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
//이메일인증하기
function confirmEmail(userData) {
  return instance.get(`/user/mail/${userData}`, userData)
}
//팔로우 취소하기
function cancleFollow(userData) {
  return instance.post('/social/cancleFollow', userData)
}
//팔로우 요청중인지 확인하기
function findrequestFollowOne(userData) {
  return instance.post('/social/findrequestFollowOne', userData)
}
//팔로우 요청 취소하기
function canclerequestFollow(userData) {
  return instance.post('/social/canclerequestFollow', userData)
}
// 검색 요청 API
function createSearch(sourceID, targetID) {
  return instance.get(`record/insert/${sourceID}/${targetID}`)
}
// 검색했던 값 가져오기 API
function fetchSearch(sourceID) {
  return instance.get(`record/fetchRecord/${sourceID}`)
}
// 특정 검색값 삭제
function deleteSearch(sourceID, targetID) {
  return instance.get(`record/deleteRecord/${sourceID}/${targetID}`)
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
  confirmEmail,
  cancleFollow,
  findrequestFollowOne,
  canclerequestFollow,
  createSearch,
  fetchSearch,
  deleteSearch,
}
