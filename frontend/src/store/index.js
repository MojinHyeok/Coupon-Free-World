import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import { loginUser } from '@/api/auth'

export default new Vuex.Store({
  actions: {
    async LOGIN({ commit }, userData) {
      const response = await loginUser(userData)
      console.log('userData에 대한 응답: ', response, commit)
    },
  },
})
