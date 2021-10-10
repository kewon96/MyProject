<template>
  <div class="content">
    <label>Email : </label>
    <input type="email">
  </div>
  <div class="content">
    <label>Password : </label>
    <input type="password">
  </div>
  <div class="content">
    <label>Username : </label>
    <input type="text">
  </div>

  <div class="btn sign-in"><router-link :to="loginParam">Sign in</router-link></div>

</template>

<script setup lang="ts">
  const loginParam = {
    name: 'Login',
    param: {}
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
</style>