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
        <label for="passwordConfirm">passwordConfirm: </label>
        <input id="passwordConfirm" type="text" v-model="passwordConfirm" />
      </div>
      <p v-if="passwordConfirm && !isPasswordValid">
        비밀번호가 옳바르지 않습니다.
      </p>
      <div>
        <label for="email">email: </label>
        <input id="email" type="text" v-model="email" />
        <p v-if="!isEmailValid && email">
          이메일 형식이 아닙니다.
        </p>
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
        <label for="bio">bio: </label>
        <textarea
          id="bio"
          type="text"
          placeholder="30자 이내로 작성"
          rows="3"
          v-model="bio"
        />
        <p v-if="!isBioValid">
          30자 이내로 작성해주세요.
        </p>
      </div>

      <button
        type="submit"
        :disabled="
          !userID ||
            !userName ||
            !password ||
            !passwordConfirm ||
            !isPasswordValid ||
            !email ||
            !isEmailValid ||
            !alias ||
            !profilePath ||
            !bio ||
            !isBioValid
        "
      >
        회원가입
      </button>
    </form>
  </div>
</template>

<script>
import { registerUser } from '@/api/auth'
import { validateEmail } from '@/utils/validation'

export default {
  data() {
    return {
      userID: '',
      userName: '',
      password: '',
      passwordConfirm: '',
      email: '',
      alias: '',
      profilePath: '',
      bio: '',
    }
  },
  computed: {
    isPasswordValid() {
      return this.password === this.passwordConfirm
    },
    isEmailValid() {
      return validateEmail(this.email)
    },
    isBioValid() {
      return this.bio.length <= 30
    },
  },
  methods: {
    async submitForm() {
      const userData = {
        userID: this.userID,
        userName: this.userName,
        password: this.password,
        email: this.email,
        alias: this.alias,
        profilePath: this.profilePath,
        bio: this.bio,
      }
      console.log('요청보낼 데이터', userData)
      const response = await registerUser(userData)
      console.log('응답받은 데이터', response)
    },
  },
}
</script>

<style></style>
