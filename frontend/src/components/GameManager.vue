<template>
  <div>
    <button @click="openPage">게임 관리</button>
    <div v-if="isOpenPage" class="modal">
      <div class="dialog">
        <button @click="openPage">모달창끄기</button>
        <hr />
        <p>게임</p>
        <div>
          <button @click="openUid">+AFK아레나</button>
          <div v-if="isOpenUid">
            <form @submit.prevent="submitForm">
              <label for="uid">uid:</label>
              <input type="text" v-model="uid" />
              <button type="submit">등록</button>
            </form>
          </div>
        </div>
        <hr />
      </div>
    </div>
  </div>
</template>

<script>
import { updateUid } from '@/api/main'
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
      uid: this.userData.afkarenaUID,
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
      console.log(data)
      let response = await updateUid(data)
      console.log(response)
      this.isOpenUid = !this.isOpenUid
      // 현재 부모 data를 자식에서 바꿔서 문제가 발생 emit을 보내야함
      this.$emit(
        'changeUserData',
        await this.$store.dispatch('getGameUid', getUserFromCookie()),
      )
    },
  },
  created() {
    this.uid = this.userData.afkarenaUID
  },
}
</script>

<style scoped src="./css/user/default.css"></style>
<style scoped src="./css/user/modal.css"></style>
<style scoped></style>
