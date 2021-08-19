<template>
  <div @click="moveDetail">
    <div style=" margin-bottom: 2vw;">
      <img
        :src="photos[0]"
        style="width: 29vw; height: 30vw; padding: 0 1vw;"
      />
    </div>
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
  data() {
    return {
      photos: [],
    }
  },
  methods: {
    moveDetail() {
      this.$router.push(`/feed/${this.feedItem.feedID}`)
    },
    moveProfile() {},
    clickLike() {},
  },
  created() {
    this.photos = this.feedItem.photoPath.split('|')
  },
}
</script>
<style scoped></style>
