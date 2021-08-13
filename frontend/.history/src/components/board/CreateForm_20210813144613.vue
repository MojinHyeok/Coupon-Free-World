<template>
  <div>
    <fieldset class="p-4">
      <div>
        <b-form-select v-model="selected" :options="options"></b-form-select>
        <b-form-select
          v-model="selected"
          :options="options"
          size="sm"
          class="mt-3"
        ></b-form-select>
        <div class="mt-3">
          Selected: <strong>{{ selected }}</strong>
        </div>
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
        { value: null, text: 'Please select an option' },
        { value: 'a', text: 'This is First option' },
        { value: 'b', text: 'Selected Option' },
        { value: { C: '3PO' }, text: 'This is an option with object value' },
        { value: 'd', text: 'This one is disabled', disabled: true },
      ],
      boardData: {
        title: '',
        content: '',
        writer: getUserFromCookie(),
        category: 1,
      },
    }
  },

  methods: {
    write() {
      if (this.title === '' || this.content === '') {
        console.log('한 글자도 입력하지 않음')
      } else {
        console.log(this.boardData)
        console.log(this.value)
        createBoard(this.boardData)
          .then(() => {
            this.$router.push('/board')
          })
          .catch(() => {
            console.error('글 작성 실패')
          })
      }
    },
    select() {
      this.category = this.value
      console.log(this.category)
    },
  },
}
</script>
