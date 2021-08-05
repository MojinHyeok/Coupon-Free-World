<template>
  <div style="margin-top: 15%;">
    <div>
      <div>
        <button
          @click="allFeed"
          :class="{ active: boxCheck, 'no-active': boxCheckTwo }"
          class="menu-button"
        >
          <span>
            <i class="fab fa-buromobelexperte fa-2x"></i>
          </span>
        </button>
        <button
          @click="followFeed"
          :class="{ active: boxCheckTwo, 'no-active': boxCheck }"
          class="menu-button"
        >
          <span>
            <i class="far fa-comment fa-2x"></i>
          </span>
        </button>
        <div class="div-create-button">
          <span @click="moveCreate" class="create-button">
            <i class="far fa-plus-square fa-2x"></i>
          </span>
        </div>
      </div>
    </div>
    <div v-if="boxCheck" class="box">
      <!-- 모든 피드들 뿌려주기 -->
      <FeedListItem
        v-for="feedItem in feedItems"
        :key="feedItem.id"
        :feedItem="feedItem"
      ></FeedListItem>
    </div>
    <!-- 팔로우한 유저 피드 불러오기 -->
    <div v-if="boxCheckTwo" @click="followFeed">
      <p>(공사중)팔로우한 유저 피드 불러오기</p>
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
      boxCheck: true,
      boxCheckTwo: false,
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
    allFeed() {
      this.boxCheck = true
      this.boxCheckTwo = false
    },
    followFeed() {
      this.boxCheck = false
      this.boxCheckTwo = true
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
