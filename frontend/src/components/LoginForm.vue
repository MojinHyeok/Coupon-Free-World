<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="userID">userID: </label>
        <input id="userID" type="text" v-model="userID" />
      </div>
      <div>
        <label for="password">password: </label>
        <input id="password" type="text" v-model="password" />
      </div>
      <button>로그인</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userID: '',
      password: '',
    }
  },
  methods: {
    async submitForm() {
      try {
        const userData = {
          userID: this.userID,
          password: this.password,
        }
        console.log('userData 요청', userData)
        await this.$store.dispatch('LOGIN', userData)
      } catch (error) {
        console.log('로그인에 대한 error', error)
      }
    },
  },
}
</script>

<style></style>
