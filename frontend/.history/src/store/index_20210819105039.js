import Vue from 'vue'
import Vuex from 'vuex'
import {
  getAuthFromCookie,
  getUserFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
} from '@/utils/cookies'
import { getUserUid } from '@/api/main'
Vue.use(Vuex)

import { loginUser, fetchUser } from '@/api/auth'

export default new Vuex.Store({
  state: {
    userID: getUserFromCookie() || '',
    token: getAuthFromCookie() || '',
    user: {},
    nowCommentList: [],
    client: null,
  },
  getters: {
    user(state) {
      return state.user
    },
    getCommentList(state) {
      return state.nowCommentList
    },
    userID(state) {
      return state.userID
    },
    client(state) {
      return state.client
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
      state.user = payload
    },
    CREATE_COMMENT(state, newComment) {
      state.nowCommentList.push(newComment)
    },
    setClient(state, payload) {
      state.client = payload
    },
  },
  actions: {
    async LOGIN({ commit }, userData) {
      const { data } = await loginUser(userData)
      if (data.message === 'fail') {
        return data
      }
      commit('setToken', data['access-token'])
      commit('setUserid', userData.userID)
      saveAuthToCookie(data['access-token'])
      saveUserToCookie(userData.userID)
      return data
    },
    async getGameUid(context, userData) {
      const { data } = await getUserUid(userData)
      return data
    },
    async getUser(context, payload) {
      const { data } = await fetchUser(payload)
      context.commit('setUser', data.userInfo)
    },
    getClient(context, payload) {
      context.commit('setClient', payload)
    },
  },
})
