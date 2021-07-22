<template>
  <div v-if="userID.length">
    <b-container class="bv-example-row">
      <b-row class="justify-content-md-center">
        <b-col col>사진</b-col>
        <b-col cols="10">
          <b-row>
            <b-col cols="3"> 좋아요<br />{{ this.userID }}</b-col>
            <b-col cols="3" @click="move"
              >팔로워<br />
              {{ this.followerCnt }}</b-col
            >
            <b-col cols="3" @click="move"
              >팔로잉<br />
              {{ this.followingCnt }}</b-col
            >
          </b-row>
        </b-col>
      </b-row>
      <b-row>
        <button v-if="tempuserID == userID">회원정보수정</button>
        <button v-else-if="this.requestFollow">팔로우요청</button>
        <button v-else>팔로우 취소</button>
      </b-row>
    </b-container>
  </div>
  <div v-else>
    <h1>일치하는 ID가 존재하지 않습니다.!</h1>
  </div>
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
      follower: [],
      following: [],
      followerCnt: '',
      followingCnt: '',
      tempuserID: '', // 이 사용자가 마이페이지로 들어왔다면 회원정보 수정을 해주기 위해 로그인유저 ID이다.
      requestFollow: true, // 팔로우요청 할지..

      // eslint-disable-next-line prettier/prettier
      };
  },
  methods: {
    ...mapActions(['getUser']),
    move() {
      this.$router.push('/user/followList?userID=' + this.userID)
    },
  },
  async created() {
    const userData = {
      userID: this.$route.query.userID,
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
    this.follower = res.data
    this.following = res2.data
    this.followerCnt = res.data.length
    this.followingCnt = res2.data.length
    const temp = getUserFromCookie()
    this.tempuserID = temp
    for (var i = 0; i < this.followerCnt; i++) {
      if (res.data[i] == temp) this.requestFollow = false
    }
    console.log(this.requestFollow)
  },
}
</script>

<style></style>
