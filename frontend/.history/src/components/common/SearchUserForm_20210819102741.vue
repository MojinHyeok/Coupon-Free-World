<template>
  <div>
    <label for="searchData" @click="onResult()">
      <span v-show="clickCheck" style="color:white;">
        <i class="fas fa-search fa-lg"></i>
      </span>
    </label>
    <div v-if="!clickCheck" class="modal">
      <div class="dialog">
        <div class="box">
          <div class="container-nowrap">
            <div v-if="isActive">
              <input
                type="text"
                id="searchData"
                autocomplete="off"
                placeholder="userID"
                v-model="searchData"
                @keyup="search"
                style="border-color:#ffa061; color: #ffa061;"
              />
            </div>
            <label for="searchData" @click="offResult()">
              <span v-show="!clickCheck">
                <i class="fas fa-times-circle fa-lg" style="color:#ffa061;"></i>
              </span>
            </label>
          </div>
          <div v-if="isActive">
            <hr style="margin: 0.5rem 0" />
            <ul>
              <li
                @click="moveProfile(result)"
                v-for="result in results"
                :key="result.id"
              >
                {{ result }}
              </li>
              <div v-if="!searchData">
                <p style="font-weight:900; font-size:0.8rem;">최근 검색</p>
                <hr style="margin: 0.3rem 0;" />
                <li
                  class="list"
                  v-for="currentResult in currentResults"
                  :key="currentResult.id"
                >
                  <div>
                    <span @click="moveProfile(currentResult.targetID)">
                      {{ currentResult.targetID }}
                    </span>
                    <button @click="deleteUser(currentResult)">
                      <i class="fas fa-user-times"></i>
                    </button>
                  </div>
                  <hr style="margin: 0.3rem 0;" />
                </li>
              </div>
              <li v-if="logMessage && searchData">
                {{ logMessage }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { searchUser, createSearch, fetchSearch, deleteSearch } from '@/api/auth'
import { UsernameValid } from '@/utils/validation'
import { getUserFromCookie } from '@/utils/cookies.js'

export default {
  data() {
    return {
      searchData: '',
      results: [],
      logMessage: '',
      currentResults: [],
      isActive: false,
      clickCheck: true,
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
    // 검색아이콘 누를때
    async onResult() {
      this.searchData = ''
      this.results = []
      this.clickCheck = !this.clickCheck
      this.isActive = !this.isActive
      let { data } = await fetchSearch(getUserFromCookie())
      console.log('검색 아이콘 클릭시', data)
      this.currentResults = data.reverse()
    },
    // x 아이콘 누를때
    offResult() {
      this.clickCheck = !this.clickCheck
      this.isActive = !this.isActive
      this.searchData = ''
      this.results = []
      this.logMessage = ''
    },
    // 검색된 값 클릭시
    async moveProfile(result) {
      this.clickCheck = true
      this.isActive = false
      await createSearch(getUserFromCookie(), result)
      this.$router.push(`/user/profile/${result}`).catch(() => {})
    },
    // 삭제할때
    async deleteUser(searchData) {
      await deleteSearch(getUserFromCookie(), searchData.targetID)
      let { data } = await fetchSearch(getUserFromCookie())
      console.log('삭제했을때', data)
      this.currentResults = data
    },
  },
}
</script>

<style scoped src="../css/user/default.css"></style>
<style scoped src="../css/user/search.css"></style>
<style scoped></style>
