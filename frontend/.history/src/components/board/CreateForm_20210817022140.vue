<template>
  <div>
    <fieldset class="p-4">
      <div>
        <b-form-select
          class="form-control"
          v-model="selected"
          :options="options"
        ></b-form-select>
        <br />
      </div>
      <div class="mb-3">
        <input
          v-model="boardData.title"
          type="text"
          class="form-control"
          id="title"
          placeholder="제목을 입력해 주세요"
        />
      </div>
      <div class="mb-3">
        <textarea
          v-model="boardData.content"
          class="form-control"
          id="content"
          style="resize: none;"
          rows="10"
        ></textarea>
      </div>
      <div class="d-flex justify-content-end">
        <button @click="write" type="submit" class="btn btn-light">
          등 록
        </button>
      </div>
    </fieldset>
  </div>
</template>

<script>
import { createBoard } from '@/api/boards'
import { getUserFromCookie } from '@/utils/cookies.js'

export default {
  data() {
    return {
      selected: null,
      options: [
        { value: null, text: '선택' },
        { value: '일반', text: '일반' },
        { value: 'AFK 아레나', text: 'AFK 아레나' },
        { value: '쿠키런 킹덤', text: '쿠키런 킹덤' },
      ],
      boardData: {
        title: '',
        content: '',
        writer: getUserFromCookie(),
        category: '일반',
      },
    }
  },

  methods: {
    write() {
      if (this.title === '' || this.content === '') {
        console.log('한 글자도 입력하지 않음')
      } else {
        if ((this.selected = '')) {
          this.selected = '일반'
        }
        this.boardData.category = this.selected
        createBoard(this.boardData)
          .then(() => {
            this.$router.push('/board')
          })
          .catch(() => {
            console.error('글 작성 실패')
          })
      }
    },
  },
}
</script>
