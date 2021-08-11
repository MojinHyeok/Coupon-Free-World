<template>
  <div>
    알림 공사중
  </div>
</template>

<script>
import { getUserFromCookie } from '@/utils/cookies.js'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
export default {
  data() {
    return {
      msg: null,
    }
  },
  methods: {
    connect() {
      const serverURL = 'http://localhost:8078/stompTest'
      var sock = new SockJS(serverURL)
      var client = Stomp.over(sock)
      this.$store.dispatch('getClient', client)
      console.log(client)
      this.isStomp = true
      this.socket = client
      // 연결해
      client.connect({}, function() {
        console.log('Connected stompTest!!')
        const msg = {
          targetID: getUserFromCookie(),
        }
        // 이런식으로 구독할거야
        client.subscribe(`/topic/alarm/${getUserFromCookie()}`, function(
          event,
        ) {
          console.log('들어와라라라라', event.body)
        })
        client.send('/AlarmCnt', JSON.stringify(msg), {})
      })
    },
  },
  created() {
    this.connect()
  },
  computed: {
    checkServerMsg() {
      return this.$store.getters.getServerMsg
    },
  },
  watch: {
    checkServerMsg(val) {
      this.msg = val
      console.log('notice.vue', this.msg)
    },
  },
}
</script>

<style></style>
