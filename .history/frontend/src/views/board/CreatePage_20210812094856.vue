<template>
  <div>
    <h1>글쓰기 페이지</h1>
  </div>
  <CreateForm />
</template>

<script>
import CreateForm from '@/components/board/CreateForm.vue'
import BoardAppbar from '@/components/board/BoardAppbar.vue'
export default {
  components: CreateForm,
  BoardAppbar,
}
</script>
