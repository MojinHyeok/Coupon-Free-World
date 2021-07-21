<template>
  <b-container class="bv-example-row">
    <b-row class="justify-content-md-center">
      <b-col col>사진</b-col>
      <b-col cols="10">
        <b-row>
          <b-col cols="3"> 좋아요<br />{{ this.userID }}</b-col>
          <b-col cols="3"
            >팔로워<br />
            {{ this.FollowerCnt }}</b-col
          >
          <b-col cols="3"
            >팔로잉<br />
            {{ this.FollowingCnt }}</b-col
          >
        </b-row>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { getUserFromCookie } from '@/utils/cookies.js'
import { fetchUser, findFollower, findFollowing } from '@/api/auth'
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
      Follower: [],
      Following: [],
      FollowerCnt: '',
      FollowingCnt: '',
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
    console.log(data)
    this.userID = data.userInfo.userID
    this.userName = data.userInfo.userName
    this.password = data.userInfo.password
    this.email = data.userInfo.email
    this.alias = data.userInfo.alias
    this.profilePath = data.userInfo.profilePath
    this.bio = data.userInfo.bio
    const res = await findFollower(userData)
    const res2 = await findFollowing(userData)
    console.log(res2)
    this.Follower = res.data
    this.Following = res2.data
    this.FollowerCnt = res.data.length
    this.FollowingCnt = res2.data.length
  },
}
</script>

<style></style>
