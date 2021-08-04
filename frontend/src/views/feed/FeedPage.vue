<template>
  <div>
    <h1>피드's page</h1>
    <!-- 모든 피드들 뿌려주기 -->
    <FeedListItem
      v-for="feedItem in feedItems"
      :key="feedItem.id"
      :feedItem="feedItem"
    ></FeedListItem>
    <!-- 피드 작성 button -->
    <button @click="moveCreate">피드작성 +</button>
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
      console.log('fetchData: ', response.data)
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

<style></style>
