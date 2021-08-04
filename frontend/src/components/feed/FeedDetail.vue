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
import { deleteFeed } from '@/api/feed.js'
export default {
  props: {
    feedItem: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {}
  },
  methods: {
    feedDelete() {
      deleteFeed(this.$store.state.userID, this.feedItem.feedID)
    },
    incLike() {},
    decLike() {},
  },
  computed: {
    isUserValid() {
      return this.$store.state.userID === this.feedItem.userID
    },
  },
}
</script>

<style></style>
