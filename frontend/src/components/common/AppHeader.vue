<template>
  <header>
    <router-link to="/board/list">List</router-link> |
    <router-link to="/account/signup">signup</router-link> |
    <router-link to="/account/login">login</router-link> |
    <a href="javascript:;" @click="logoutUser">logout</a> |
    <router-link to="/account/edit">userEdit</router-link>
    <SearchUserForm></SearchUserForm>
  </header>
</template>

<script>
import { deleteCookie } from '@/utils/cookies'
import SearchUserForm from '@/components/common/SearchUserForm.vue'
export default {
  components: {
    SearchUserForm,
  },
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
