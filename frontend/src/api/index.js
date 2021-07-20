import axios from 'axios'
// import { setInterceptors } from './common/interceptors'

// 토큰값 없을 때
function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    headers: {
      'Content-type': 'application/json',
    },
  })
}

export const instance = createInstance()
