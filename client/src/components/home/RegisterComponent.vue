<template>
  <div class="content">
    <label>Email : </label>
    <input type="email" @model="content.email">
  </div>
  <div class="content">
    <label>Password : </label>
    <input type="password" @model="content.password">
  </div>
  <div class="content">
    <label>Username : </label>
    <input type="text" @model="content.username">
  </div>

  <button @click="createMember">SignUp</button>
  <div class="btn sign-in"><router-link :to="loginParam">Sign in</router-link></div>

</template>

<script setup lang="ts">
import {getCurrentInstance, ref} from "vue";
  import http from "../../util/http";

  const vm = getCurrentInstance();

  interface Content {
    email: String,
    password: String,
    username: String,
  }

  const content = ref<Content>({
    email: '',
    password: '',
    username: ''
  })

  const loginParam : Object = {
    name: 'Login',
    param: {}
  }

  async function createMember() {
    const response = await http.post('/regist', content)
    console.log(response)

    alert('회원가입이 끝났습니다!');
    vm.appContext.config.globalProperties.$router.push('Login');
    // click Event에 $route를 넣는 방식도 됨
  }
</script>

<style lang="scss" scoped>
  a {
    color: #000;
    text-decoration: none;

    &:hover {
      color: #565252;
    }
  }

  .btn {
    width: 5em;
    text-align: center;
    vertical-align: middle;
    border: 1px solid #000;
    border-radius: 3px;
  }

  input[type="submit"] {
    background: #fff;
    font-size: 1rem;
    border-radius: 3px;
    border: 1px solid #000;
    width: 7rem;
    margin-bottom: 10px;
  }
</style>