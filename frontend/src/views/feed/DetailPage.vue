<template>
  <div>
    <!-- 댓글 뺀 상세 피드 컴포넌트 -->
    <FeedDetail
      :feedItem="feedItem"
      :isLike="isLike"
      :photos="photos"
      :date="date"
    ></FeedDetail>
    <!-- 댓글 상세 피드 -->
    <!-- <FeedComment :feedItem="feedItem"></FeedComment> -->
  </div>
</template>

<script>
import { fetchFeed, isUserLike } from '@/api/feed.js'
import FeedDetail from '@/components/feed/FeedDetail'
// import FeedComment from '@/components/feed/FeedComment'
export default {
  components: {
    FeedDetail,
    // FeedComment,
  },
  data() {
    return {
      feedItem: {},
      isLike: true,
      photos: [],
      date: '',
    }
  },
  async created() {
    // 해당 피드에 대한 상세 정보 불러오기
    const id = this.$route.params.id
    console.log(id)

    const response = await fetchFeed(id)
    console.log('특정 피드를 불러온다.', response.data)
    this.feedItem = response.data

    const userID = this.$store.state.userID
    const { data } = await isUserLike(userID)
    console.log('해당유저가 좋아요 눌렀는지', data)
    for (var i = 0; i < data.length; i++) {
      if (this.feedItem.feedID == data[i]['feedID']) {
        this.isLike = false
        break
      }
    }
    this.photos = this.feedItem.photoPath.split('|')
    this.date = this.feedItem.date
      .substring(5, 7)
      .concat('월', this.feedItem.date.substring(8, 10), '일')
  },
}
</script>

<style></style>
