<template>
  <div>
    <label for="searchData" @click="offResult('searchData')">❤ </label>
    <input
      type="search"
      id="searchData"
      placeholder="userID"
      v-model="searchData"
      @keyup="search"
    />
    <ul v-if="resultsDisplay">
      <li
        @click="moveProfile(result)"
        v-for="result in results"
        :key="result.id"
      >
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
      // display
      resultsDisplay: false,
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
        this.resultsDisplay = true
        this.logMessage = ''
      } catch (error) {
        this.results = []
        this.logMessage = '해당 유저가 없습니다.'
        this.resultsDisplay = true
      }
    },
    offResult(id) {
      this[`${id}`] = ''
      this.logMessage = ''
      this.resultsDisplay = !this.resultsDisplay
    },
    moveProfile(result) {
      this.$router.push(`/user/profile/${result}`).catch(() => {})
    },
  },
}
</script>

<style></style>
