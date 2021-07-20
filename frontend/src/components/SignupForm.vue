<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="userID">userID: </label>
        <input id="userID" type="text" v-model="userID" />
      </div>
      <div>
        <label for="userName">userName:</label>
        <input id="userName" type="text" v-model="userName" />
      </div>
      <div>
        <label for="password">password: </label>
        <input id="password" type="text" v-model="password" />
      </div>
      <div>
        <label for="email">email: </label>
        <input id="email" type="text" v-model="email" />
      </div>
      <div>
        <label for="alias">alias: </label>
        <input id="alias" type="text" v-model="alias" />
      </div>
      <div class="input-group">
        <label for="profilePath">profilePath: </label>
        <input
          type="text"
          class="form-control"
          placeholder="Input Image URL or  Drag & Drop or Select"
          v-model="filename"
          @dragover.prevent
          @dragenter.prevent
          @drop.prevent="onDrop"
        />
        <div class="input-group-append">
          <span class="input-group-text" @click="onClickFile"
            ><i class="fa fa-paperclip"> </i
          ></span>
          <button class="btn btn-outline-info" @click="onClickUpload">
            Upload
          </button>
        </div>
        <input
          type="file"
          class="file-input"
          accept="image/*"
          ref="fileInput"
          @change="onFileChange"
        />
      </div>
      <!-- IMG보이는지 TEST -->
      <div v-show="imageSrc" class="upload-image">
        <img :src="imageSrc" />
      </div>
      <div>
        <label for="Bio">Bio: </label>
        <textarea id="Bio" type="text" rows="3" v-model="Bio" />
      </div>
      <button type="submit">회원가입</button>
    </form>
  </div>
</template>

<script>
import { registerUser } from '@/api/auth'

export default {
  data() {
    return {
      userID: '',
      userName: '',
      password: '',
      email: '',
      alias: '',
      profilePath: '',
      Bio: '',
      // img
      filename: '',
      imageSrc: '',
    }
  },
  methods: {
    // submit
    async submitForm() {
      const userData = {
        userID: this.userID,
        userName: this.userName,
        password: this.password,
        email: this.email,
        alias: this.alias,
        profilePath: this.profilePath,
        Bio: this.Bio,
      }
      console.log('요청보낼 데이터', userData)
      const { data } = await registerUser(userData)
      console.log('응답받은 데이터', data.username)
    },
  },

  // img
  onDrop(event) {
    this.inputImageFile(event.dataTransfer.files)
  },
  onClickFile() {
    this.$refs.fileInput.click()
  },
  onFileChange(event) {
    this.inputImageFile(event.target.files)
  },
  inputImageFile(files) {
    if (files.length) {
      let file = files[0]
      if (!/^image\//.test(file.type)) {
        alert('이미지 파일만 등록이 가능합니다')
        return false
      }
      this.filename = file.name
      this.preview(file)
    }
  },
  onClickUpload() {
    this.preview(this.filename)
  },
  preview(file) {
    if (typeof file === 'string') {
      this.imageSrc = file
    } else {
      let vm = this
      let reader = new FileReader()
      reader.onload = () => {
        vm.imageSrc = reader.result
      }
      reader.readAsDataURL(file)
    }
  },
}
</script>

<style></style>
