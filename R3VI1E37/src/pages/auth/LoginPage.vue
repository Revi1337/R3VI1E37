<template>
  <div class="flex justify-center items-center fullscreen">
    <q-form @submit.prevent="userLoginRequest">
      <q-card
        dark
        flat
        class="border-light-green text-light-green-12 q-pa-lg column justify-between"
        :style="{ minWidth: '500px', minHeight: '500px', border: `1px solid aqua` }"
      >
        <q-card-section>
          <div class="text-h6 text-weight-bold">Sign In To Your Account</div>
        </q-card-section>

        <q-separator dark inset />

        <q-card-section>
          <q-input
            autofocus
            dark
            :spellcheck="false"
            filled
            v-model="userLoginData.email"
            color="light-green-12"
            label="EMAIL"
            label-color="light-green-12"
            type="text"
            class="q-mb-md"
          />

          <q-input
            dark
            :spellcheck="false"
            filled
            v-model="userLoginData.password"
            color="light-green-12"
            label="PASS"
            label-color="light-green-12"
            :type="isPwd ? 'password' : 'text'"
            class="text-light-green-12"
          >
            <template #append>
              <q-icon
                color="light-green-12"
                :name="isPwd ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="isPwd = !isPwd"
              />
            </template>
          </q-input>

          <div class="row justify-between items-center">
            <q-checkbox keep-color v-model="teal" label="Remember Me" color="light-green-12" />
            <router-link to="/forgot" class="underline text-white"> Forgot Your Password? </router-link>
          </div>
        </q-card-section>

        <q-card-section class="q-gutter-y-lg">
          <div class="row">
            <q-btn type="submit" class="col" :ripple="false" outline label="login" color="light-green-12" />
          </div>

          <q-separator dark inset />

          <div class="row">
            <q-btn class="col" :ripple="false" outline color="green-12" href="/api/oauth2/google">
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="fa-brands fa-google" class="col-1" />
                <div class="col">sign in google</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn class="col" :ripple="false" outline color="green-12" href="/api/oauth2/github">
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="fa-brands fa-github" class="col-1" />
                <div class="col">sign in github</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn class="col" :ripple="false" outline color="green-12" href="/api/oauth2/facebook">
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="fa-brands fa-facebook" class="col-1" />
                <div class="col">sign in facebook</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn class="col" :ripple="false" outline color="green-12" href="/api/oauth2/naver">
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="img:/naver.svg" class="col-1" />
                <div class="col">sign in naver</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn class="col" :ripple="false" outline color="green-12" href="/api/oauth2/kakao">
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="img:/kakaotalk.svg" class="col-1" />
                <div class="col">sign in kakao</div>
              </div>
            </q-btn>
          </div>

          <div class="row items-center justify-between">
            <span class="text-white">Don't have an account?</span>
            <q-btn
              class="q-px-xl"
              :ripple="false"
              outline
              label="sign up"
              color="white"
              :to="{ name: 'Join' }"
            />
          </div>
        </q-card-section>
      </q-card>
    </q-form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { userLogin } from 'src/api/auth';
import { useAuthStore } from 'stores/auth-store';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';

// routing
const router = useRouter();

// use authStore
const { accessToken, nickname, isAuthenticated, roles } = storeToRefs(useAuthStore());
const setPositiveAuthInfo = (nick, token, boolean, role) => {
  nickname.value = nick;
  accessToken.value = token;
  isAuthenticated.value = boolean;
  // roles.value = 'ROLE_ADMIN';
  roles.value = role;
};
const setNegativeAuthInfo = () => {
  nickname.value = null;
  accessToken.value = null;
  isAuthenticated.value = false;
  roles.value = null;
};

// hide password
const isPwd = ref(true);

// User Login
const userLoginData = ref({
  email: 'FUCK5@com',
  password: 'asdf'
});
const userLoginRequest = async () => {
  try {
    const jsonData = {
      email: userLoginData.value.email,
      password: userLoginData.value.password
    };
    const { status, headers, request, data } = await userLogin(jsonData);
    const authHeader = headers['authorization'];

    setPositiveAuthInfo(data.nickname, authHeader.split('Bearer ')[1], true, data.roles); //TODO: authStore 로 빼줄까생각해보자

    router.push({ name: 'Index' });
  } catch (error) {
    console.log('failed');
    setNegativeAuthInfo(); //TODO: authStore 로 빼줄까생각해보자
  }
};
</script>

<style lang="scss" scoped>
.text-white {
  color: $font-color !important;
}

.underline {
  position: relative;
  text-decoration: none;
}
.underline:before {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 0;
  height: 2px;
  background-color: $font-color;
  transition: width 0.3s ease-in-out;
}
.underline:hover:before {
  width: 100%;
}
.login-modal {
  width: 400px;
  height: 150px;
}
</style>
