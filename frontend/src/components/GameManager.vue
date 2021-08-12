<template>
  <div>
    <button @click="openPage">게임 관리</button>
    <div v-if="isOpenPage" class="modal">
      <div class="dialog">
        <button @click="openPage">모달창끄기</button>
        <hr />
        <p>등록한 게임</p>
        <p v-if="isRegist">AFK아레나</p>
        <hr />
        <p>등록가능한 게임</p>
        <div v-if="!isRegist">
          <button @click="openUid">+ AFK아레나</button>
          <div v-if="isOpenUid">
            <form @submit.prevent="submitForm">
              <label for="uid">uid:</label>
              <input type="text" v-model="uid" />
              <button type="submit">등록</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { updateUid } from '@/api/main'
import { getUserFromCookie } from '@/utils/cookies.js'
export default {
  data() {
    return {
      isOpenPage: false,
      isOpenUid: false,
      uid: '',
      isRegist: null,
    }
  },
  methods: {
    openPage() {
      this.isOpenPage = !this.isOpenPage
    },
    openUid() {
      this.isOpenUid = !this.isOpenUid
    },
    async submitForm() {
      const data = {
        afkarenaUID: this.uid,
        userID: getUserFromCookie(),
      }
      let response = await updateUid(data)
      console.log(response)
      this.isOpenUid = !this.isOpenUid
      this.isRegist = true
    },
  },
}
</script>

<style scoped src="./css/user/default.css"></style>
<style scoped src="./css/user/modal.css"></style>
<style scoped></style>
