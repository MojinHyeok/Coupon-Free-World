<template>
  <div class="search-bar">
    <input type="text" @keyup.enter="onKeywordEnter" />
    <button>
      <i class="bi bi-search" style="height: 100%;"></i>
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
