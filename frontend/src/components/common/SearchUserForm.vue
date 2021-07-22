<template>
  <div>
    <input type="text" placeholder="userID" v-model="searchData" />
  </div>
</template>

<script>
import { searchUser } from '@/api/auth'
import { UsernameValid } from '@/utils/validation'

export default {
  data() {
    return {
      searchData: '',
      results: [],
      logMessage: '',
    }
  },
  methods: {
    async search() {
      try {
        if (!UsernameValid(this.searchData) || this.searchData.trim() === '') {
          this.results = []
          this.logMessage = '해당 유저가 없습니다.'
          return
        }
        const { data } = await searchUser(this.searchData)
        this.results = data
        this.logMessage = ''
      } catch (error) {
        this.results = []
        this.logMessage = '해당 유저가 없습니다.'
      }
    },
    afterFocus(id) {
      const el = document.querySelector(`#${id}`)
      el.addEventListener('blur', () => {
        this[`${id}`] = ''
        this.results = []
        this.logMessage = ''
      })
    },
  },
}
</script>

<style></style>
