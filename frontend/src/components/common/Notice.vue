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
      client.connect({}, function() {
        console.log('Connected stompTest!!')
        const msg = {
          targetID: getUserFromCookie(),
        }
        // 연결되면 보내
        client.send('/TTT', JSON.stringify(msg), {})
        // 구독중...
        // client.subscribe(`/topic/${getUserFromCookie()}`, function(event) {
        //   console.log('나한테 요청한 user들어와', event.body)
        // })
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
