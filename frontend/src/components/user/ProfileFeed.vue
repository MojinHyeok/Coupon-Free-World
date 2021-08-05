<template>
  <div @click="move()">
    <img :src="photoPath" alt="" />
  </div>
</template>

<script>
export default {
  props: {
    feed: Object,
  },
  data() {
    return {
      photoPath: '',
    }
  },
  created() {
    var photo = this.feed.photoPath.split('|')
    this.photoPath = photo[0]
  },
  methods: {
    move() {
      this.$router.push('/feed/' + this.feed.feedID)
    },
  },
}
</script>

<style>
img {
  width: 33%;
}
</style>
