<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="userID">userID: </label>
        <input
          id="userID"
          type="text"
          v-model="userID"
          @focus="afterFocus('userID')"
        />
        <p v-if="userIDCheck && !userID">
          userID가 누락되었습니다.
        </p>
      </div>
      <div>
        <label for="password">password: </label>
        <input
          id="password"
          type="text"
          v-model="password"
          @focus="afterFocus('password')"
        />
        <p v-if="passwordCheck && !password">
          password가 누락되었습니다.
        </p>
      </div>
      <p>{{ logMessage }}</p>
      <button :disabled="!userID || !password">로그인</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userID: '',
      password: '',
      // log
      logMessage: '',
      // check
      userIDCheck: false,
      passwordCheck: false,
    }
  },
  methods: {
    async submitForm() {
      const userData = {
        userID: this.userID,
        password: this.password,
      }
      const res = await this.$store.dispatch('LOGIN', userData)
      if (res.message === 'fail') {
        this.logMessage = '정보를 정확히 입력해주세요'
      } else {
        this.$router.push('/main')
      }
    },
    afterFocus(id) {
      const el = document.querySelector(`#${id}`)
      el.addEventListener('blur', () => {
        // 아무것도 안쓰고 나갔어?
        if (!this[`${id}`]) {
          this[`${id}Check`] = true
        }
      })
    },
  },
}
</script>

<style></style>
