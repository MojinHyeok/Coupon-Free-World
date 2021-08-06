<template>
  <div class="search-bar">
    <input type="text" @keyup.enter="onKeywordEnter" />
    <button>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="16"
        height="16"
        fill="currentColor"
        class="bi bi-search"
        viewBox="0 0 16 16"
      >
        <path
          d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
        />
      </svg>
    </button>
  </div>
</template>

<script>
import { searchBoard } from '@/api/boards'

export default {
  name: 'SearchBar',
  methods: {
    async onKeywordEnter(event) {
      const keyword = event.target.value
      const res = await searchBoard('title', keyword, 10, 0)
      const boardListTotal = res.data
      const boardTotal = boardListTotal.length
      this.$emit('on-keyword-enter', boardListTotal, boardTotal)
    },
  },
}
</script>

<style>
button {
  appearance: auto;
  -webkit-writing-mode: horizontal-tb !important;
  text-rendering: auto;
  color: -internal-light-dark(black, white);
  letter-spacing: normal;
  word-spacing: normal;
  text-transform: none;
  text-shadow: none;
  display: inline-block;
  text-align: center;
  align-items: flex-start;
  background-color: -internal-light-dark(rgb(239, 239, 239), rgb(59, 59, 59));
  box-sizing: border-box;
}
</style>
