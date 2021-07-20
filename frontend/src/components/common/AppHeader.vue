<template>
  <header>
    <router-link to="/account/signup">signup</router-link> |
    <router-link to="/account/login">login</router-link> |
    <a href="javascript:;" @click="logoutUser">logout</a>
  </header>
</template>

<script>
import { deleteCookie } from '@/utils/cookies'
export default {
  methods: {
    logoutUser() {
      this.$store.commit('clearUserid')
      this.$store.commit('clearToken')
      deleteCookie('one_auth')
      deleteCookie('one_user')
      this.$router.push('/account/login')
    },
  },
}
</script>

<style></style>
