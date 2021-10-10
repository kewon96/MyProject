<template>
  <div class="main">
    <div class="btn sign-in"><router-link :to="loginParam">Sign in</router-link></div>
    <div class="btn sign-up"><router-link :to="registerParam">Sign up</router-link></div>
  </div>
</template>

<script setup lang="ts">
interface param {
  name: String,
  param: Object
}

const loginParam: param = {
  name: 'Login',
  param: {}
}

const registerParam: param = {
  name: 'Register',
  param: {}
}

</script>

<style lang="scss" scoped>
.main {
  display: flex;
  justify-content: flex-start;
  gap: 15px;

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
  }

  .sign-up {
    border: 1px solid #000;
    border-radius: 3px;
  }
}
</style>