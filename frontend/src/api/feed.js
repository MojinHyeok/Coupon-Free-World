import { instance } from './index'

// 피드 데이터 목록을 조회하는 API
function fetchFeeds() {
  return instance.post('/feed/mainFeedList')
}

// 특정 피드를 조회하는 API
function fetchFeed(id) {
  return instance.get(`/feed/select/${id}`)
}

// 피드 작성 API
function createFeed(data) {
  return instance.post('feed/write', data)
}

// 피드 수정 API
function updateFeed(data) {
  return instance.put('feed/update', data)
}

// 피드 삭제 API
function deleteFeed(userID, feedID) {
  return instance.delete(`feed/delete/${userID}/${feedID}`)
}

// 피드 좋아요 증가 API
function likeFeed(data) {
  return instance.post('feed/incLikeCnt', data)
}

// 피드 좋아요 감소 API
function unlikeFeed(data) {
  return instance.post('feed/decLikeCnt', data)
}

export {
  fetchFeeds,
  fetchFeed,
  createFeed,
  updateFeed,
  deleteFeed,
  likeFeed,
  unlikeFeed,
}
