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
    <!-- 피드 수정버튼 -->
    <button @click="moveUpdate">수정</button>
    <!-- 피드 삭제 버튼 -->
    <button @click="feedDelete">삭제</button>
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
    moveUpdate() {
      this.$router.push(`/feed/${this.feedItem.feedID}/update`)
    },
    feedDelete() {
      deleteFeed(this.$store.state.userID, this.feedItem.feedID)
    },
  },
}
</script>

<style></style>
