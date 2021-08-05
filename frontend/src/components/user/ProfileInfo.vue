<template>
  <div>
    <div v-if="userID.length">
      <b-container class="bv-example-row">
        <b-row class="justify-content-md-center">
          <b-col class="imgbox" cols="4">
            <div v-if="profilePath == ''">
              <img
                src="../../assets/profileDefault.jpg"
                style="width:30vw;height:30vw;  "
              />
            </div>
            <div v-else>
              <img
                :src="profilePath"
                style="border: 1px solid #333;width: 20vw; height: 20vw;border-radius: 50%;  margin:auto;"
              />
            </div>
          </b-col>
          <b-col class="top" cols="8">
            <b-row>
              <b-col cols="3"> 좋아요<br />{{ this.userID }}</b-col>
              <b-col cols="3" @click="move"
                >팔로잉<br />
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
          <button @click="moveProfile" v-if="tempuserID == userID">
            회원정보수정
          </button>
          <button
            v-else-if="this.findrequestFollow"
            @click="canclerequestFollow"
          >
            팔로우 요청 취소
          </button>
          <button v-else-if="this.requestFollow" @click="requestFollow2">
            팔로우요청
          </button>
          <button v-else @click="cancleFollow">팔로우 취소</button>
        </b-row>
      </b-container>
    </div>
    <div v-else>
      <h1>일치하는 ID가 존재하지 않습니다.!</h1>
    </div>
    <div v-if="photos == ''">
      <h1>피드가 존재하지 않습니다.</h1>
    </div>
    <div v-else>
      <profile-feed v-for="feed in photos" :key="feed.id" :feed="feed" />
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { getUserFromCookie } from '@/utils/cookies.js'
import {
  fetchUser,
  findFollower,
  findFollowing,
  requestFollow,
  cancleFollow,
  findrequestFollowOne,
  canclerequestFollow,
} from '@/api/auth'

import { getFeedList } from '@/api/feed.js'
import ProfileFeed from './ProfileFeed.vue'
export default {
  components: {
    ProfileFeed,
  },
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
      profilePath: '',
      follower: [],
      following: [],
      followerCnt: '',
      followingCnt: '',
      tempuserID: '', // 이 사용자가 마이페이지로 들어왔다면 회원정보 수정을 해주기 위해 로그인유저 ID이다.
      requestFollow: true, // 팔로우요청 할지..
      findrequestFollow: false,
      photos: [],
      // eslint-disable-next-line prettier/prettier
      };
  },
  methods: {
    ...mapActions(['getUser']),
    move() {
      this.$router.push('/user/followList?userID=' + this.userID)
    },
    async requestFollow2() {
      const tempData = {
        targetID: this.$route.params.userID,
        sourceID: getUserFromCookie(),
      }
      const data = await requestFollow(tempData)
      if (data.data == 'fail') alert('이미 팔로우 요청하셨습니다')
      else alert('팔로우 요청을 성공적으로 하셨습니다.!')
      this.findrequestFollow = true
    },
    async detectParams() {
      const tempData = {
        targetID: this.$route.params.userID,
        sourceID: getUserFromCookie(),
      }
      const res3 = await findrequestFollowOne(tempData)
      console.log(res3.data)
      if (res3.data != '') {
        this.findrequestFollow = true
      }
      const userData = {
        userID: this.$route.params.userID,
      }
      const { data } = await fetchUser(userData)

      this.userID = data.userInfo.userID
      this.userName = data.userInfo.userName
      this.password = data.userInfo.password
      this.email = data.userInfo.email
      this.alias = data.userInfo.alias
      this.profilePath = data.userInfo.profilePath
      this.bio = data.userInfo.bio
      const res = await findFollower(userData)
      const res2 = await findFollowing(userData)

      this.follower = res.data
      this.following = res2.data
      this.followerCnt = res.data.length
      this.followingCnt = res2.data.length
      const temp = getUserFromCookie()
      this.tempuserID = temp
      for (var i = 0; i < this.followerCnt; i++) {
        if (res.data[i] == temp) {
          this.requestFollow = false
        }
      }
    },
    async canclerequestFollow() {
      const tempData = {
        targetID: this.$route.params.userID,
        sourceID: getUserFromCookie(),
      }
      const Data = await canclerequestFollow(tempData)
      if (Data.data == 'success') alert('팔로우를 요청취소하였습니다.!!')
      else {
        alert('에러발생')
      }
      this.findrequestFollow = false
      this.requestFollow = true
    },
    moveProfile() {
      this.$router.push('/account/edit')
    },
    async cancleFollow() {
      const tempData = {
        targetID: this.$route.params.userID,
        sourceID: getUserFromCookie(),
      }
      const Data = await cancleFollow(tempData)
      if (Data.data == 'success') alert('팔로우를 취소하였습니다.!!')
      else {
        alert('에러발생')
      }
    },
    async getFeedList() {
      const temp = this.$route.params.userID
      var res = await getFeedList(temp)
      this.photos = res.data
      console.log(this.photos)
    },
  },
  created() {
    this.detectParams(this.$route.params.userID)
    this.getFeedList()
  },
  watch: {
    $route(to, from) {
      if (to.path !== from.path) {
        this.detectParams(this.$route.params.userID)
      }
    },
  },
}
</script>

<style scoped src="../css/user/default.css"></style>
<style scoped src="../css/user/ProfileInfo.css"></style>
<style></style>
