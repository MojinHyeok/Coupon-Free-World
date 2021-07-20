import Vue from 'vue'
import Vuex from 'vuex'
import {
  getAuthFromCookie,
  getUserFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
  // saveAuthToCookie,
  // saveUserToCookie,
} from '@/utils/cookies'
Vue.use(Vuex)

import { loginUser } from '@/api/auth'

export default new Vuex.Store({
  state: {
    userID: getUserFromCookie() || '',
    token: getAuthFromCookie() || '',
  },
  mutations: {
    setUserid(state, userID) {
      state.userID = userID
    },
    setToken(state, token) {
      state.token = token
    },
    clearUserid(state) {
      state.userID = ''
    },
    clearToken(state) {
      state.token = ''
    },
  },
  actions: {
    async LOGIN({ commit }, userData) {
      const { data } = await loginUser(userData)
      if (data.message === 'fail') {
        return data
      }
      // state에 저장
      commit('setToken', data['access-token'])
      commit('setUserid', userData.userID)
      // 쿠키에 넣기
      saveAuthToCookie(data['access-token'])
      saveUserToCookie(userData.userID)
      return data
    },
  },
})
