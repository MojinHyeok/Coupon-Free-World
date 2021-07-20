<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="userID">userID: </label>
        <input id="userID" type="text" v-model="userID" />
      </div>
      <div>
        <label for="userName">userName:</label>
        <input id="userName" type="text" v-model="userName" />
      </div>
      <div>
        <label for="password">password: </label>
        <input id="password" type="text" v-model="password" />
      </div>
      <div>
        <label for="email">email: </label>
        <input id="email" type="text" v-model="email" />
      </div>
      <div>
        <label for="alias">alias: </label>
        <input id="alias" type="text" v-model="alias" />
      </div>
      <div>
        <label for="profilePath">profilePath: </label>
        <input id="profilePath" type="text" v-model="profilePath" />
      </div>
      <div>
        <label for="Bio">Bio: </label>
        <textarea id="Bio" type="text" rows="3" v-model="Bio" />
      </div>
      <button type="submit">회원가입</button>
    </form>
  </div>
</template>

<script>
import { registerUser } from '@/api/auth'

export default {
  data() {
    return {
      userID: '',
      userName: '',
      password: '',
      email: '',
      alias: '',
      profilePath: '',
      Bio: '',
    }
  },
  methods: {
    // submit
    async submitForm() {
      const userData = {
        userID: this.userID,
        userName: this.userName,
        password: this.password,
        email: this.email,
        alias: this.alias,
        profilePath: this.profilePath,
        Bio: this.Bio,
      }
      console.log('요청보낼 데이터', userData)
      const { data } = await registerUser(userData)
      console.log('응답받은 데이터', data.username)
    },
  },
}
</script>

<style></style>
