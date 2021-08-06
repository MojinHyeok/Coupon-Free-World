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

import { loginUser, fetchUser } from '@/api/auth'

export default new Vuex.Store({
  state: {
    userID: getUserFromCookie() || '',
    token: getAuthFromCookie() || '',
    user: {},
    nowCommentList: [],
  },
  getters: {
    user(state) {
      console.log('asdas')
      console.log(state.user)
      return state.user
    },
    getCommentList(state) {
      return state.nowCommentList
    },
    userID(state) {
      return state.userID
    },
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
    setUser(state, payload) {
      console.log('여기는 setuser')
      console.log(payload)
      state.user = payload
    },
    CREATE_COMMENT(state, newComment) {
      state.nowCommentList.push(newComment)
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
    // async getUser({ commit }, userID) {
    //   const { data } = await fetchUser(userID)

    //   commit('setUser', data.userInfo)
    // },
    async getUser(context, payload) {
      const { data } = await fetchUser(payload)
      context.commit('setUser', data.userInfo)
    },
  },
})
