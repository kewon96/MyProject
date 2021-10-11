<template>
  <div class="center">
    <h1>Login</h1>
    <form method="post" action="doLogin">
      <div class="txt-field">
        <input type="text" v-model="account.id" required>
        <span></span>
        <label>Username</label>
      </div>
      <div class="txt-field">
        <input type="password" v-model="account.password" required>
        <span></span>
        <label>Password</label>
      </div>

      <div class="pass">Forgot Password?</div>
      <input type="submit" value="Login">
      <div class="signup-link">
        Not a member? <a href="#">Signup</a>
      </div>
    </form>
  </div>

</template>

<script setup lang="ts">
import http from "../../util/http";
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

<style lang="scss" scoped>
.center {
  $color-main: #2691d9;

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 450px;
  background: #fff;
  border-radius: 10px;
  border: 1px solid $color-main;

  h1 {
    text-align: center;
    padding: 0 0 20px 0;
    border-bottom: 1px solid silver;
  }

  form {
    padding: 0 40px;
    box-sizing: border-box;

    .txt-field {
      position: relative;
      border-bottom: 1px solid #adadad;
      margin: 30px 0;

      input {
        width: 100%;
        padding: 0 5px;
        height: 40px;
        font-size: 16px;
        border: none;
        background: none;
        outline: none;

        &:focus,
        &:valid {
          ~label {
            top: -5px;
            color: $color-main;
          }

          ~span:before {
            width: 100%;
          }
        }
      } /* input End */

      label {
        position: absolute;
        top: 50%;
        left: 5px;
        color: #adadad;
        transform: translateY(-50%);
        font-size: 16px;
        pointer-events: none;
        transition: .5s;
      }

      span::before {
        content: '';
        position: absolute;
        top: 40px;
        left: 0;
        width: 0;
        height: 2px;
        background: $color-main;
        transition: .5s;
      }
    } /* .txt-field End */

    input[type="submit"] {
      width: 100%;
      height: 50px;
      border: 1px solid;
      background: $color-main;
      border-radius: 25px;
      font-size: 18px;
      color: #e9f4fb;
      font-weight: 700;
      cursor: pointer;
      outline: none;

      &:hover {
        border-color: $color-main;
        transition: .5s;
      }
    }

    .pass {
      margin: -5px 0 20px 5px;
      color: #a6a6a6;
      cursor: pointer;

      &hover {
        text-decoration: underline;
      }
    }

    .signup-link {
      margin: 30px 0;
      text-align: center;
      font-size: 16px;
      color: #666;

      a {
        color: $color-main;
        text-decoration: none;

        &hover {
          text-decoration: underline;
        }
      }
    }
  } /* form End */

} /* .center End */

</style>