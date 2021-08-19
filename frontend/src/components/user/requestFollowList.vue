<template>
  <div>
    <li
      class="d-flex"
      v-for="(v, index) in requestFollowList"
      v-bind:key="index"
    >
      <span class="col-8">{{ v }}님이 팔로우 요청을 하였습니다.</span>
      <button class="btn-primary col-3" @click="allow(v)">
        수락하기
      </button>
    </li>
  </div>
</template>

<script>
import { getUserFromCookie } from '@/utils/cookies.js'
import { findrequestFollow, allowFollow } from '@/api/auth'
export default {
  data() {
    return {
      requestFollowList: [],
    }
  },
  async created() {
    await this.test()
  },
  methods: {
    async test() {
      const userData = {
        targetID: getUserFromCookie(),
      }
      const res = await findrequestFollow(userData)
      this.requestFollowList = res.data
    },
    async allow(v) {
      const temp = {
        sourceID: v,
        targetID: getUserFromCookie(),
      }
      await allowFollow(temp)
      await this.test()
    },
  },
}
</script>

<style scoped src="../css/user/userFollowList.css"></style>
