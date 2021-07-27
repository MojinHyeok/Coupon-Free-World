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
    <div v-if="isActive">
      <ul>
        <p>현재 검색</p>
        <li
          @click="saveCache(result)"
          v-for="result in results"
          :key="result.id"
        >
          {{ result }}
        </li>
        <p>최근 검색</p>
        <li v-for="currentResult in currentResults" :key="currentResult.id">
          <span @click="moveProfile(currentResult)">
            {{ currentResult }}
          </span>
          <button @click="deleteCache(currentResult)">삭제</button>
        </li>
        <li v-if="logMessage">
          {{ logMessage }}
        </li>
      </ul>
    </div>
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
      currentResults: [],
      isActive: false,
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
      this.isActive = !this.isActive
      this[`${id}`] = ''
      this.results = []
      this.logMessage = ''
      this.resultsDisplay = !this.resultsDisplay
      this.getCache()
    },
    saveCache(result) {
      // 캐시추가
      const cacheName = this.$store.state.userID
      caches.open(cacheName).then(cache => {
        cache.add(result)
      })
      this.getCache()
      this.moveProfile(result)
    },
    moveProfile(result) {
      this.isActive = false
      this.$router.push(`/user/profile/${result}`).catch(() => {})
    },
    getCache() {
      // 캐시가져오기
      const cacheName = this.$store.state.userID
      caches.open(cacheName).then(cache => {
        cache.matchAll().then(responses => {
          for (let response of responses) {
            const findIndex = response['url'].lastIndexOf('/') + 1
            const temp = response['url'].substring(findIndex)
            if (!this.currentResults.includes(temp)) {
              this.currentResults.push(temp)
            }
          }
        })
      })
    },
    // 캐시 삭제
    deleteCache(currentResult) {
      const cacheName = this.$store.state.userID
      caches.open(cacheName).then(cache => {
        cache.matchAll().then(responses => {
          for (let response of responses) {
            if (response['url'].includes(currentResult)) {
              cache.delete(response['url'])
              const findIndex = response['url'].lastIndexOf('/') + 1
              const temp = response['url'].substring(findIndex)
              this.currentResults.splice(this.currentResults.indexOf(temp), 1)
            }
          }
        })
      })
    },
  },
}
</script>

<style></style>
