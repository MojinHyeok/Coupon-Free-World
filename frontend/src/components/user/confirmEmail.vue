<template>
  <div>
    <div>
      <label for="email">Email : </label>
      <input id="email" v-model="email" />
      <p v-if="!isEmailValid && email">
        이메일 형식이 아닙니다.
      </p>
      <p v-if="!isEmailLenValid">
        email 길이 초과하였습니다.
      </p>
      <button :disabled="!isEmailLenValid || !isEmailLenValid" @click="submit">
        코드전송
      </button>
    </div>
    <div>
      <label for="code">인증코드 : </label>
      <input id="code" v-model="code" />
      <button @click="move">확인</button>
    </div>
  </div>
</template>
<script>
import { confirmEmail } from '@/api/auth'
import { validateEmail } from '@/utils/validation'
export default {
  data() {
    return {
      email: '',
      code: '',
      data: '',
    }
  },
  computed: {
    isEmailLenValid() {
      return this.email.length <= 30
    },
    isEmailValid() {
      return validateEmail(this.email)
    },
  },
  methods: {
    async submit() {
      try {
        const userData = this.email
        const temp = await confirmEmail(userData)
        this.data = temp.data
        console.log(temp)
        if (this.data == 1) alert('이미존재하는 Email입니다')
        else {
          alert('이메일로 코드번호를 전송하였습니다.!!1')
        }
      } catch (error) {
        alert(error)
      }
    },
    move() {
      console.log(this.data + ' ' + this.code)
      if (this.data == this.code && this.data != null) {
        alert('인증에 성공하였습니다.! 회원가입페이지로 이동합니다!')
        this.$router.push('/account/signup?email=' + this.email)
      }
    },
  },
}
</script>

<style></style>
