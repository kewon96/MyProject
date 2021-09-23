<template>
  <h1>Login</h1>
  <div>
    <div><label>ID</label></div>
    <input type="text" v-model="account.id">
  </div>
  <div>
    <div><label>Password</label></div>
    <input type="password" v-model="account.password">
  </div>

  <button @click="doLogin">Login</button>

</template>

<script setup lang="ts">
  import http from "../../../util/http";
  import { ref } from "vue";

  const account = ref({
    id: '',
    password: ''
  })

  async function doLogin() {
    const response = await http.post('/login', account)
    console.log(response)
  }
</script>

<style scoped>
  div {
    display: flex;
    margin: 5px 0 0 5px;
  }

  div > div {
    width: 300px;
    justify-content: flex-end;
    margin-right: 5px;
  }
</style>