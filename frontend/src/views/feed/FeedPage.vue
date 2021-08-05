<template>
  <div style="margin-top: 15%;">
    <button @click="moveCreate">피드작성 +</button>
    <div class="box-button">
      <button>
        <span>
          <i class="fab fa-buromobelexperte fa-2x"></i>
        </span>
      </button>
      <button></button>
    </div>
    <div class="box">
      <!-- 모든 피드들 뿌려주기 -->
      <FeedListItem
        v-for="feedItem in feedItems"
        :key="feedItem.id"
        :feedItem="feedItem"
      ></FeedListItem>
      <!-- 피드 작성 button -->
    </div>
  </div>
</template>

<script>
import FeedListItem from '@/components/feed/FeedListItem.vue'
import { fetchFeeds } from '@/api/feed.js'
export default {
  components: {
    FeedListItem,
  },
  data() {
    return {
      feedItems: [],
    }
  },
  methods: {
    // feeds 불러오기
    async fetchData() {
      const response = await fetchFeeds()
      this.feedItems = response.data
    },
    moveCreate() {
      this.$router.push('/feed/create')
    },
  },
  created() {
    this.fetchData()
  },
}
</script>

<style scoped src="../../components/css/user/default.css"></style>
<style scoped src="../../components/css/feed/feedList.css"></style>
<style></style>
