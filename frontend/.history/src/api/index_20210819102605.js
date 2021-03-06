import axios from 'axios'
import store from '../store'

function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    headers: {
      'Content-type': 'application/json',
      Authrozation: store.state.token,
    },
  })
}

export const instance = createInstance()
