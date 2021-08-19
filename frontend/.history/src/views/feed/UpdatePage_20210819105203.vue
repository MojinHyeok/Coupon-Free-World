<template>
  <div>
    <h1>수정페이지</h1>
    <FeedUpdateForm :feedItem="feedItem"></FeedUpdateForm>
  </div>
</template>

<script>
import FeedUpdateForm from '@/components/feed/FeedUpdateForm.vue'
import { fetchFeed } from '@/api/feed.js'
export default {
  components: {
    FeedUpdateForm,
  },
  data() {
    return {
      feedItem: [],
    }
  },
  methods: {
    async fetchData(id) {
      try {
        const response = await fetchFeed(id)
        this.feedItem = response
      } catch (error) {
        console.log(error)
      }
    },
  },
  created() {
    const id = this.$route.params.id
    this.fetchData(id)
  },
}
</script>

<style></style>
