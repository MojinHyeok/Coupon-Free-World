<template>
  <div>
    <MainAppbar />
    <!-- 게임관리 버튼(컴포넌트) -->
    <div class="box">
      <GameManager
        :userData="userData"
        @changeUserData="changeUserData"
        class="item"
        style="margin-bottom:3%;"
      ></GameManager>
      <!-- 게임이미지 -->
      <img
        src="../assets/afkarena.png"
        alt=""
        style="width:100%;margin-bottom:2%;"
      />
      <!-- 모든쿠폰등록(흠...) -->
      <div class="regist-button" style="margin-bottom: 2em;">
        <button @click="openPage('afkarena')">자동등록</button>
      </div>
      <img
        src="../assets/cookieRun.png"
        alt=""
        style="width:100%;margin-bottom:2%;"
      />
      <!-- 모든쿠폰등록(흠...) -->
      <div class="regist-button">
        <button @click="openPage('cookie')">자동등록</button>
      </div>

      <div v-if="isOpenPage && isAfk" class="modal">
        <div class="dialog">
          <button
            style="width: 100%; display: flex; justify-content: flex-end;"
            @click="openPage('afkarena')"
          >
            <i class="fas fa-times-circle fa-lg" style="color:#ffa061;"></i>
          </button>
          <div class="btn-fm-box" v-if="isAfk">
            <div class="afk-plus">
              <button style="color: #fff;">AFK아레나</button>
            </div>
            <form @submit.prevent="submitForm" class="form-box">
              <input
                id="authentication"
                type="text"
                v-model="authenValue"
                placeholder="인증번호"
              />
              <div class="form-box-div" style="justify-content: space-between;">
                <p>프로필->시스템->인증번호</p>
                <button type="submit">등록</button>
              </div>
            </form>
            <p
              v-if="logMsg"
              style="color: blue; font-weight: bold; font-size: 0.9rem;"
            >
              {{ logMsg }}
            </p>
          </div>
        </div>
      </div>
      <div v-if="isOpenPage && isCookie" class="modal">
        <div class="dialog">
          <button
            style="width: 100%; display: flex; justify-content: flex-end;"
            @click="openPage('cookie')"
          >
            <i class="fas fa-times-circle fa-lg" style="color:#ffa061;"></i>
          </button>
          <div class="btn-fm-box" v-if="isCookie">
            <div class="afk-plus">
              <button style="color: #fff;">쿠키런 킹덤</button>
            </div>
            <form
              @submit.prevent="submitFormTwo"
              class="form-box"
              style="width: 100%; margin-top: 1em;"
            >
              <div class="form-box-div" style="justify-content: space-between;">
                <p>해당게임은 인증번호없이 등록가능</p>
                <button type="submit" style="width:30%; padding: 0.2em 0;">
                  등록
                </button>
              </div>
            </form>
            <p
              v-if="logMsgTwo"
              style="color: blue; font-weight: bold; font-size: 0.9rem;"
            >
              {{ logMsgTwo }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MainAppbar from '@/components/MainAppbar.vue'
import { getUserFromCookie } from '@/utils/cookies.js'
import { registCoupon, registCouponTwo } from '@/api/main'
import GameManager from '@/components/GameManager.vue'
export default {
  components: {
    GameManager,
    MainAppbar,
  },
  data() {
    return {
      userData: {},
      isOpenPage: false,
      authenValue: '',
      logMsg: '',
      logMsgTwo: '',
      isAfk: false,
      isCookie: false,
    }
  },
  methods: {
    changeUserData(tempUserData) {
      this.userData = tempUserData
    },
    openPage(gameName) {
      this.isOpenPage = !this.isOpenPage
      if (gameName == 'cookie') {
        this.isCookie = !this.isCookie
      } else if (gameName == 'afkarena') {
        this.isAfk = !this.isAfk
      }
    },
    async submitForm() {
      const data = {
        userID: getUserFromCookie(),
        verifyCode: this.authenValue,
      }
      console.log(data)
      try {
        await registCoupon(data)
        this.logMsg = '[등록완료]우편을 확인해보세요.'
      } catch (error) {
        this.logMsg = '인증번호가 틀렸습니다.'
      }
    },
    async submitFormTwo() {
      const data = {
        userID: this.userData.cookierunUID,
      }
      console.log(data)
      try {
        await registCouponTwo(data)
        this.logMsgTwo = '[등록완료]우편을 확인해보세요.'
      } catch (error) {
        this.logMsgTwo = '설정에서 DevID를 확인해주세요.'
      }
    },
  },
  async created() {
    this.userData = await this.$store.dispatch(
      'getGameUid',
      getUserFromCookie(),
    )
    console.log('userData결과: ', this.userData)
  },
}
</script>

<style scoped src="../components/css/user/default.css"></style>
<style scoped src="../components/css/main/mainbox.css"></style>
<style scoped></style>
