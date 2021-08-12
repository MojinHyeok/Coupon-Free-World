<template>
  <div>
    <!-- 게임관리 버튼(컴포넌트) -->
    <GameManager
      :userData="userData"
      @changeUserData="changeUserData"
    ></GameManager>
    <!-- 게임이미지 -->
    <p>afk아레나 게임이미지 넣기</p>
    <!-- 모든쿠폰등록(흠...) -->
    <button @click="openPage">모든 쿠폰등록</button>
    <div v-if="isOpenPage" class="modal">
      <div class="dialog">
        <button @click="openPage">이거 클릭하면 꺼짐</button>
        <form @submit.prevent="submitForm">
          <label for="authentication">인증번호:</label>
          <input id="authentication" type="text" v-model="authenValue" />
          <button type="submit">등록</button>
        </form>
        <p v-if="logMsg">{{ logMsg }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { getUserFromCookie } from '@/utils/cookies.js'
import { registCoupon } from '@/api/main'
import GameManager from '@/components/GameManager.vue'
export default {
  components: {
    GameManager,
  },
  data() {
    return {
      userData: {},
      isOpenPage: false,
      authenValue: '',
      logMsg: '',
    }
  },
  methods: {
    changeUserData(tempUserData) {
      this.userData = tempUserData
    },
    openPage() {
      this.isOpenPage = !this.isOpenPage
    },
    async submitForm() {
      const data = {
        userID: getUserFromCookie(),
        verifyCode: this.authenValue,
      }
      console.log(data)
      try {
        let response = await registCoupon(data)
        console.log(response)
      } catch (error) {
        this.logMsg = '인증번호를 정확히 입력해주세요.'
      }
    },
  },
  async created() {
    this.userData = await this.$store.dispatch(
      'getGameUid',
      getUserFromCookie(),
    )
    console.log(this.userData)
  },
}
</script>

<style scoped src="../components/css/user/default.css"></style>
<style scoped src="../components/css/user/modal.css"></style>
<style scoped></style>
