<template>
  <div>
    <div>userID: {{ feedItem.userID }}</div>
    <div>feedID: {{ feedItem.feedID }}</div>
    <div>내용: {{ feedItem.content }}</div>
    <div>날짜: {{ feedItem.date }}</div>
    <div>좋아요: {{ feedItem.likeCnt }}</div>
    <div>사진1url: {{ feedItem.photoPath1 }}</div>
    <div>사진2url: {{ feedItem.photoPath2 }}</div>
    <!-- 좋아요 버튼 -->
    <button v-if="isLike" @click="incLike">like</button>
    <button v-else @click="decLick">unlike</button>
    <!-- 피드 삭제 버튼 -->
    <button v-if="isUserValid" @click="feedDelete">삭제</button>
  </div>
</template>
<script>
// <i class="far fa-heart"></i
// >            <i class="fas fa-heart"></i
// >
import { deleteFeed, isUserLike, likeFeed, unlikeFeed } from '@/api/feed.js'
export default {
  props: {
    feedItem: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      isLike: true,
    }
  },
  methods: {
    async feedDelete() {
      await deleteFeed(this.$store.state.userID, this.feedItem.feedID)
      this.$router.push('/feed')
    },
    async incLike() {
      const data = new FormData()
      data.append('userID', this.$store.state.userID)
      data.append('feedID', this.feedItem.feedID)
      await likeFeed(data)
      this.isLike = false
      console.log('incLike작동')
    },
    async decLike() {
      const data = new FormData()
      data.append('userID', this.$store.state.userID)
      data.append('feedID', this.feedItem.feedID)
      await unlikeFeed(data)
      this.isLike = true
      console.log('decLike작동')
    },
  },
  computed: {
    isUserValid() {
      return this.$store.state.userID === this.feedItem.userID
    },
  },
  async created() {
    const data = new FormData()
    data.append('userID', this.$store.state.userID)
    data.append('feedID', this.feedItem.feedID)

    const response = await isUserLike(data)
    console.log('isUserLike =>', response)
    this.isLike = response
  },
}
</script>

<style></style>
