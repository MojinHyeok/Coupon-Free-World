import { instance } from './index'

function fetchMsg(data) {
  return instance.get('/readAlarm', data)
}

export { fetchMsg }
