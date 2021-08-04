<template>
  <div @click="moveDetail">
    <div>userID: {{ feedItem.userID }}</div>
    <div>feedID: {{ feedItem.feedID }}</div>
    <div>내용: {{ feedItem.content }}</div>
    <div>날짜: {{ feedItem.date }}</div>
    <div>좋아요: {{ feedItem.likeCnt }}</div>
    <div>사진1url: {{ feedItem.photoPath1 }}</div>
    <div>사진2url: {{ feedItem.photoPath2 }}</div>
    <hr />
  </div>
</template>

<script>
export default {
  props: {
    feedItem: {
      type: Object,
      required: true,
    },
  },
  methods: {
    // 해당 게시글 클릭시 상세페이지 이동
    moveDetail() {
      this.$router.push(`/feed/${this.feedItem.feedID}`)
    },
    // 유저 클릭시 유저Profile 이동
    moveProfile() {},
    // 좋아요 클릭시
    clickLike() {},
  },
}
</script>

<style></style>
