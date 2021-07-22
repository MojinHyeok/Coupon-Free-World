<template>
  <div>
    <input
      type="text"
      id="searchData"
      placeholder="userID"
      v-model="searchData"
      @keyup="search"
      @focus="afterFocus('searchData')"
    />
    <ul>
      <li v-for="result in results" :key="result.id">
        {{ result }}
      </li>
      <li v-if="logMessage">
        {{ logMessage }}
      </li>
    </ul>
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
        console.error = function() {}
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
