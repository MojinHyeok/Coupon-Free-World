<template>
  <div>
    <div class="container">
      <div class="manager">
        <button @click="openPage">
          <i class="fas fa-cog fa-lg"></i>
        </button>
      </div>
      <div v-if="isOpenPage" class="modal">
        <div class="dialog">
          <button
            @click="openPage"
            style="width: 100%; display: flex; justify-content: flex-end;"
          >
            <i class="fas fa-times-circle fa-lg" style="color:#ffa061;"></i>
          </button>
          <div class="btn-fm-box">
            <div class="afk-plus">
              <button @click="openUid" style="color: #fff;">+AFK아레나</button>
            </div>
            <div v-if="isOpenUid">
              <form @submit.prevent="submitForm" class="form-box">
                <input type="text" v-model="uid" placeholder="UID" />
                <div
                  class="form-box-div"
                  style="justify-content: space-between;"
                >
                  <p>프로필->정보->ID</p>
                  <button type="submit">등록</button>
                </div>
              </form>
            </div>
            <div class="afk-plus">
              <button @click="openDevID" style="color: #fff;">
                +쿠키런킹덤
              </button>
            </div>
            <div v-if="isOpenDevID">
              <form @submit.prevent="submitFormTwo" class="form-box">
                <input type="text" v-model="devid" placeholder="DevID" />
                <div
                  class="form-box-div"
                  style="justify-content: space-between;"
                >
                  <p>환경설정->정보->유저정보</p>
                  <button type="submit">등록</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { updateUid, updateDevID } from '@/api/main'
import { getUserFromCookie } from '@/utils/cookies.js'
export default {
  props: {
    userData: {
      type: [Object, String],
      required: true,
    },
  },
  data() {
    return {
      isOpenPage: false,
      isOpenUid: false,
      isOpenDevID: false,
      uid: '',
      devid: '',
    }
  },
  methods: {
    openPage() {
      this.isOpenPage = !this.isOpenPage
    },
    openUid() {
      this.uid = this.userData.afkarenaUID
      this.isOpenUid = !this.isOpenUid
    },
    openDevID() {
      this.devid = this.userData.cookierunUID
      this.isOpenDevID = !this.isOpenDevID
    },
    async submitForm() {
      const data = {
        afkarenaUID: this.uid,
        userID: getUserFromCookie(),
      }
      await updateUid(data)
      this.isOpenUid = !this.isOpenUid
      this.$emit(
        'changeUserData',
        await this.$store.dispatch('getGameUid', getUserFromCookie()),
      )
      setTimeout(() => (this.isOpenPage = !this.isOpenPage), 500)
    },
    async submitFormTwo() {
      const data = {
        cookierunUID: this.devid,
        userID: getUserFromCookie(),
      }
      await updateDevID(data)
      this.isOpenDevID = !this.isOpenDevID
      this.$emit(
        'changeUserData',
        await this.$store.dispatch('getGameUid', getUserFromCookie()),
      )
      setTimeout(() => (this.isOpenPage = !this.isOpenPage), 500)
    },
  },
}
</script>

<style scoped src="./css/user/default.css"></style>
<style scoped src="./css/main/manager.css"></style>
<style scoped></style>
