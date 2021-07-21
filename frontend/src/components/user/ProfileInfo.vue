<template>
  <b-container class="bv-example-row">
    <b-row class="justify-content-md-center">
      <b-col col>사진</b-col>
      <b-col cols="10">
        <b-row>
          <b-col cols="3"> 좋아요<br />{{ this.userID }}</b-col>
          <b-col cols="3">팔로워</b-col>
          <b-col cols="3">팔로잉</b-col>
        </b-row>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { getUserFromCookie } from '@/utils/cookies.js'
import { fetchUser } from '@/api/auth'
export default {
  computes: {
    ...mapGetters(['user']),
  },
  data() {
    return {
      userID: '',
      userName: '',
      password: '',
      email: '',
      alias: '',
      likeCnt: '',
      // eslint-disable-next-line prettier/prettier
      };
  },
  methods: {
    ...mapActions(['getUser']),
  },
  async created() {
    const userData = {
      userID: getUserFromCookie(),
    }
    const { data } = await fetchUser(userData)
    this.userID = data.userInfo.userID
    this.userName = data.userInfo.userName
    this.password = data.userInfo.password
    this.email = data.userInfo.email
    this.alias = data.userInfo.alias
    this.profilePath = data.userInfo.profilePath
    this.bio = data.userInfo.bio
  },
}
</script>

<style></style>
