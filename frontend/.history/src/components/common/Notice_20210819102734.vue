<template>
  <div>
    <div @click="openModal">
      <span style="color:white; padding-left: 0.6rem;">
        <i class="far fa-bell fa-lg"></i>
      </span>
    </div>
    <div class="modal" v-if="isModal">
      <div class="dialog follow-list-parent">
        <div v-if="msgs.length == 0">
          새로운 요청이 없습니다.
        </div>
        <div v-else>
          <div v-for="msg in msgs" :key="msg.id" @click="readMsg(msg)">
            <div>{{ msg.sourceID }}님이 팔로우합니다.</div>
          </div>
        </div>
        <div class="d-flex justify-content-between pt-3">
          <span @click="goFollowdetail">더 보기</span>
          <span @click="openModal">닫기</span>
        </div>
      </div>
    </div>
    <div class="alarm-modal">
      <div class="alarm-dialog">
        <p><i class="far fa-bell fa-lg"></i> {{ msg }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { getUserFromCookie } from '@/utils/cookies.js'
import { fetchMsg } from '@/api/notice.js'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
export default {
  data() {
    return {
      msg: [],
      msgs: [],
      isModal: false,
    }
  },
  methods: {
    goFollowdetail() {
      this.$router.push('/user/requestFollowList')
      this.isModal = false
    },
    connect() {
      const serverURL = 'http://i5c101.p.ssafy.io/api/stompTest'
      var sock = new SockJS(serverURL)
      var client = Stomp.over(sock)
      this.$store.dispatch('getClient', client)
      console.log(client)
      this.isStomp = true
      this.socket = client
      client.connect({}, () => {
        console.log('Connected stompTest!!')
        const msg = {
          targetID: getUserFromCookie(),
        }
        client.subscribe(`/topic/alarm/${getUserFromCookie()}`, event => {
          this.msgs = JSON.parse(event.body).reverse()
        })
        client.subscribe(`/topic/${getUserFromCookie()}`, event => {
          this.msg = event.body
          document.querySelector('.alarm-modal').style.display = 'block'
          setTimeout(() => {
            document.querySelector('.alarm-modal').style.display = 'none'
          }, 3000)
        })
        client.send('/AlarmCnt', JSON.stringify(msg), {})
      })
    },
    async readMsg(msgData) {
      console.log(msgData.targetID, msgData.sourceID)
      const data = new FormData()
      data.append('targetID', msgData.targetID)
      data.append('sourceID', msgData.sourceID)
      console.log('fetchMsg', data)
      await fetchMsg(data)
      const msg = {
        targetID: getUserFromCookie(),
      }
      let sending = this.$store.getters.client
      sending.send('/AlarmCnt', JSON.stringify(msg), {})
    },
    openModal() {
      this.isModal = !this.isModal
    },
  },
  created() {
    this.connect()
  },
}
</script>

<style scoped></style>
<style scoped src="../css/user/default.css"></style>
<style scoped src="../css/user/modal.css"></style>
