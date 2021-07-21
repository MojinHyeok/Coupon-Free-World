<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="userID">userID: </label>
        <input
          id="userID"
          type="text"
          autocomplete="off"
          v-model="userID"
          disabled
        />
        <p v-if="!isUserIdLenValid">
          userID 길이 초과하였습니다.
        </p>
        <p v-if="!isUserIdValid">
          영어, 숫자 조합만 가능합니다.
        </p>
      </div>
      <div>
        <label for="userName">userName:</label>
        <input
          id="userName"
          type="text"
          autocomplete="off"
          v-model="userName"
        />
        <p v-if="!isUsernameLenValid">
          userName 길이 초과하였습니다.
        </p>
        <p v-if="!isUsernameValid">
          특수문자 사용할 수 없습니다.
        </p>
      </div>
      <label>비밀번호 변경 </label>
      <input
        type="checkbox"
        autocomplete="off"
        id="passwordCheckbox"
        @click="passwordActive"
      />
      <div>
        <label for="passwordCurrent">passwordCurrent: </label>
        <input
          id="passwordCurrent"
          type="text"
          autocomplete="off"
          v-model="passwordCurrent"
          :disabled="!isPwAcitve"
          @keyup="isBlankVaild(passwordCurrent, 'passwordCurrent')"
        />
        <p v-if="!isPasswordLenValid">
          password 길이 초과하였습니다.
        </p>
        <p v-if="logMessage">
          {{ logMessage }}
        </p>
        <p v-if="isPwAcitve && passwordCurrentBlank">
          {{ logBlankpasswordCurrent }}
        </p>
      </div>
      <div>
        <label for="passwordChange">passwordChange: </label>
        <input
          id="passwordChange"
          type="text"
          autocomplete="off"
          v-model="passwordChange"
          :disabled="!isPwAcitve"
          @keyup="isBlankVaild(passwordChange, 'passwordChange')"
        />
        <p v-if="logMessage">
          {{ logMessage }}
        </p>
        <p v-if="isPwAcitve && passwordChangeBlank">
          {{ logBlankpasswordChange }}
        </p>
      </div>
      <div>
        <label for="passwordChangeConfirm">passwordChangeConfirm: </label>
        <input
          id="passwordChangeConfirm"
          type="text"
          autocomplete="off"
          v-model="passwordChangeConfirm"
          :disabled="!isPwAcitve"
        />
        <p v-if="passwordChangeConfirm && !isPasswordConfirmValid">
          비밀번호가 옳바르지 않습니다.
        </p>
      </div>

      <div>
        <label for="email">email: </label>
        <input
          id="email"
          type="text"
          autocomplete="off"
          v-model="email"
          disabled
        />
        <p v-if="!isEmailValid && email">
          이메일 형식이 아닙니다.
        </p>
        <p v-if="!isEmailLenValid">
          email 길이 초과하였습니다.
        </p>
      </div>
      <div>
        <label for="alias">alias: </label>
        <input id="alias" type="text" autocomplete="off" v-model="alias" />
        <p v-if="!isAliasLenValid">
          alias 길이 초과하였습니다.
        </p>
        <p v-if="!isAliasValid">
          특수문자,공백 사용할 수 없습니다.
        </p>
      </div>
      <div>
        <label for="profilePath">profilePath: </label>
        <input
          id="profilePath"
          type="text"
          autocomplete="off"
          v-model="profilePath"
        />
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
        <p v-if="!isBioLenValid">
          30자 이내로 작성해주세요.
        </p>
        <p v-if="!bioBlank">
          공백 사용할 수 없습니다.
        </p>
      </div>
      <button
        type="submit"
        :disabled="
          !userID ||
            !userName ||
            !email ||
            !isEmailValid ||
            !alias ||
            !profilePath ||
            !bio ||
            !isBioLenValid ||
            !isUserIdLenValid ||
            !isUsernameLenValid ||
            !isEmailLenValid ||
            !isAliasLenValid ||
            !isAliasValid ||
            !isUserIdValid ||
            !isUsernameValid ||
            (isPwAcitve &&
              (!passwordCurrent ||
                !passwordChange ||
                !passwordChangeConfirm ||
                !isPasswordConfirmValid))
        "
      >
        수정
      </button>
    </form>
    <button @click="deleteAccount">회원탈퇴</button>
  </div>
</template>

<script>
import {
  getUserFromCookie,
  deleteCookie,
  saveUserToCookie,
} from '@/utils/cookies'
import {
  validateEmail,
  UserIdValid,
  UsernameValid,
  BlankValid,
} from '@/utils/validation'
import { fetchUser, editUser, deleteUser } from '@/api/auth'

export default {
  data() {
    return {
      userID: '',
      userName: '',
      password: '',
      passwordCurrent: '',
      passwordChange: '',
      passwordChangeConfirm: '',
      email: '',
      alias: '',
      profilePath: '',
      bio: '',
      // checkbox
      isPwAcitve: false,
      // log
      logMessage: '',
      logBlankpasswordCurrent: '',
      logBlankpasswordChange: '',
      // bool
      passwordCurrentBlank: false,
      passwordChangeBlank: false,
      bioBlank: true,
    }
  },
  computed: {
    // 길이
    isUserIdLenValid() {
      return this.userID.length <= 18
    },
    isUsernameLenValid() {
      return this.userName.length <= 20
    },
    isPasswordLenValid() {
      return this.passwordChange.length <= 18
    },
    isEmailLenValid() {
      return this.email.length <= 30
    },
    isAliasLenValid() {
      return this.alias.length <= 16
    },
    isAliasValid() {
      return UsernameValid(this.alias)
    },
    // 영어, 숫자 허용만 가능
    isUserIdValid() {
      return UserIdValid(this.userID)
    },
    // 한글, 영어, 숫자 허용
    isUsernameValid() {
      return UsernameValid(this.userName)
    },
    isPasswordConfirmValid() {
      return this.passwordChange === this.passwordChangeConfirm
    },
    isEmailValid() {
      return validateEmail(this.email)
    },
    isBioLenValid() {
      return this.bio.length <= 30
    },
  },
  async created() {
    const userData = {
      userID: getUserFromCookie(),
    }
    const { data } = await fetchUser(userData)
    this.userID = data.userInfo.userID
    this.userName = data.userInfo.userName
    this.password = data.userInfo.password
    this.email = data.userInfo.email
    this.alias = data.userInfo.alias
    this.profilePath = data.userInfo.profilePath
    this.bio = data.userInfo.bio
  },
  methods: {
    passwordActive() {
      this.isPwAcitve = !this.isPwAcitve
      this.passwordCurrent = ''
      this.passwordChange = ''
      this.passwordChangeConfirm = ''
      this.passwordCurrentBlank = false
    },
    async submitForm() {
      try {
        if (this.bio.trim() === '') {
          this.logBlankbio = '공백 사용할 수 없습니다.'
          this.bioBlank = false
          return
        }
        if (this.passwordActive) {
          this.password = this.passwordChange
          if (this.password !== this.passwordCurrent) {
            this.logMessage = '암호가 정확하지 않습니다.'
            return
          }
        }
        const userData = {
          userID: this.userID,
          userName: this.userName,
          password: this.password,
          email: this.email,
          alias: this.alias,
          profilePath: this.profilePath,
          bio: this.bio.trim(),
        }
        await editUser(userData)
        // 쿠키 삭제후 생성, state도 바꿔야함
        deleteCookie('one_user')
        saveUserToCookie(this.userName)
        // state 값 바꿈
        this.$store.commit('setUserid', this.userName)
        // 일단 main으로
        this.$router.push('/main')
      } catch (error) {
        console.log(error)
      }
    },
    isBlankVaild(data, id) {
      if (BlankValid(this[`${id}`])) {
        this[`${id}Blank`] = true
        this[`logBlank${id}`] = '공백 사용할 수 없습니다.'
        this[`${id}`] = ''
      } else {
        this[`${id}Blank`] = false
      }
    },
    async deleteAccount() {
      const userData = {
        userID: this.userID,
      }
      await deleteUser(userData)
      deleteCookie('one_user')
      deleteCookie('one_auth')
      this.$store.commit('clearUserid')
      this.$store.commit('clearToken')
      this.$router.push('/account/login')
    },
  },
}
</script>

<style></style>
