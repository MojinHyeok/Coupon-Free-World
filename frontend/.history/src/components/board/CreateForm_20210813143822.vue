<template>
  <div>
    <fieldset class="p-4">
      <select class="form-select" aria-label="Default select example">
        <option selected>카테고리 선택</option>
        <option value="1">일반</option>
        <option value="2">AFK 아레나</option>
        <option value="3">쿠키런 킹덤</option>
      </select>
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
      boardData: {
        title: '',
        content: '',
        writer: getUserFromCookie(),
        category: this.value,
      },
    }
  },

  methods: {
    write() {
      if (this.title === '' || this.content === '') {
        console.log('한 글자도 입력하지 않음')
      } else {
        console.log(this.boardData)
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
