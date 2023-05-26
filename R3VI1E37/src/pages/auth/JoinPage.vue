<template>
  <div class="fullscreen row no-wrap">
    <div class="col-5 row justify-end items-center bg-grey-10">
      <div class="invite q-gutter-y-xl">
        <div class="flex flex-center">
          <transition appear enter-active-class="animated fadeInDownBig">
            <svg
              width="135.806"
              height="21.007"
              viewBox="0 0 135.806 21.007"
              xmlns="http://www.w3.org/2000/svg"
              class="cursor-pointer"
              @click="$router.push({ name: 'Intro' })"
            >
              <defs>
                <linearGradient
                  id="r3vi1e37"
                  x1="0%"
                  y1="0%"
                  x2="100%"
                  y2="100%"
                >
                  <stop offset="0%" stop-color="#42B883" />
                  <stop offset="100%" stop-color="#1987bd" />
                </linearGradient>
              </defs>
              <path
                d="M 20.405 15.909 L 20.405 14.649 L 23.35 14.649 L 23.35 15.762 A 4.232 4.232 0 0 0 23.394 16.396 Q 23.502 17.11 23.877 17.506 A 1.562 1.562 0 0 0 24.461 17.884 Q 24.925 18.062 25.605 18.062 L 31.655 18.062 A 4.085 4.085 0 0 0 32.276 18.019 Q 32.592 17.97 32.842 17.867 A 1.541 1.541 0 0 0 33.384 17.506 Q 33.773 17.095 33.875 16.341 A 4.326 4.326 0 0 0 33.911 15.762 L 33.911 14.136 A 4.328 4.328 0 0 0 33.869 13.513 Q 33.763 12.783 33.384 12.393 Q 32.999 11.998 32.257 11.891 A 4.227 4.227 0 0 0 31.655 11.851 L 25.444 11.851 L 25.444 8.922 L 31.567 8.922 A 3.618 3.618 0 0 0 32.136 8.857 Q 32.8 8.723 33.142 8.328 A 1.69 1.69 0 0 0 33.45 7.784 Q 33.539 7.535 33.581 7.227 A 4.404 4.404 0 0 0 33.618 6.636 L 33.618 5.26 A 4.294 4.294 0 0 0 33.576 4.635 Q 33.529 4.314 33.428 4.057 A 1.661 1.661 0 0 0 33.083 3.502 A 1.597 1.597 0 0 0 32.491 3.121 Q 32.233 3.023 31.911 2.979 A 4.116 4.116 0 0 0 31.362 2.945 L 25.898 2.945 A 3.993 3.993 0 0 0 25.283 2.989 Q 24.564 3.102 24.185 3.502 A 1.681 1.681 0 0 0 23.833 4.086 Q 23.74 4.341 23.696 4.656 A 4.389 4.389 0 0 0 23.657 5.26 L 23.657 6.373 L 20.713 6.373 L 20.713 5.113 A 7.38 7.38 0 0 1 20.966 3.09 Q 21.614 0.821 23.923 0.219 A 7.49 7.49 0 0 1 25.811 0.001 L 31.45 0.001 A 8.479 8.479 0 0 1 32.872 0.112 Q 33.631 0.241 34.23 0.522 A 3.692 3.692 0 0 1 35.295 1.261 A 3.891 3.891 0 0 1 36.194 2.734 Q 36.41 3.345 36.494 4.094 A 9.146 9.146 0 0 1 36.548 5.113 L 36.548 5.494 A 8.3 8.3 0 0 1 36.461 6.73 Q 36.316 7.695 35.925 8.416 Q 35.303 9.566 34.116 10.123 Q 35.449 10.518 36.152 11.705 A 4.557 4.557 0 0 1 36.633 12.89 Q 36.772 13.427 36.824 14.06 A 9.666 9.666 0 0 1 36.855 14.854 L 36.855 15.909 A 7.339 7.339 0 0 1 36.603 17.926 Q 35.72 21.007 31.758 21.007 L 25.518 21.007 A 7.38 7.38 0 0 1 23.494 20.754 Q 21.226 20.106 20.623 17.797 A 7.49 7.49 0 0 1 20.405 15.909 Z M 100.151 15.909 L 100.151 14.649 L 103.096 14.649 L 103.096 15.762 A 4.232 4.232 0 0 0 103.14 16.396 Q 103.248 17.11 103.623 17.506 A 1.562 1.562 0 0 0 104.207 17.884 Q 104.671 18.062 105.352 18.062 L 111.401 18.062 A 4.085 4.085 0 0 0 112.022 18.019 Q 112.338 17.97 112.588 17.867 A 1.541 1.541 0 0 0 113.13 17.506 Q 113.519 17.095 113.621 16.341 A 4.326 4.326 0 0 0 113.657 15.762 L 113.657 14.136 A 4.328 4.328 0 0 0 113.616 13.513 Q 113.509 12.783 113.13 12.393 Q 112.745 11.998 112.003 11.891 A 4.227 4.227 0 0 0 111.401 11.851 L 105.19 11.851 L 105.19 8.922 L 111.313 8.922 A 3.618 3.618 0 0 0 111.882 8.857 Q 112.546 8.723 112.888 8.328 A 1.69 1.69 0 0 0 113.196 7.784 Q 113.286 7.535 113.327 7.227 A 4.404 4.404 0 0 0 113.364 6.636 L 113.364 5.26 A 4.294 4.294 0 0 0 113.322 4.635 Q 113.275 4.314 113.175 4.057 A 1.661 1.661 0 0 0 112.83 3.502 A 1.597 1.597 0 0 0 112.237 3.121 Q 111.979 3.023 111.657 2.979 A 4.116 4.116 0 0 0 111.108 2.945 L 105.645 2.945 A 3.993 3.993 0 0 0 105.029 2.989 Q 104.31 3.102 103.931 3.502 A 1.681 1.681 0 0 0 103.58 4.086 Q 103.486 4.341 103.442 4.656 A 4.389 4.389 0 0 0 103.403 5.26 L 103.403 6.373 L 100.459 6.373 L 100.459 5.113 A 7.38 7.38 0 0 1 100.712 3.09 Q 101.36 0.821 103.669 0.219 A 7.49 7.49 0 0 1 105.557 0.001 L 111.196 0.001 A 8.479 8.479 0 0 1 112.618 0.112 Q 113.377 0.241 113.976 0.522 A 3.692 3.692 0 0 1 115.042 1.261 A 3.891 3.891 0 0 1 115.94 2.734 Q 116.156 3.345 116.24 4.094 A 9.146 9.146 0 0 1 116.294 5.113 L 116.294 5.494 A 8.3 8.3 0 0 1 116.207 6.73 Q 116.062 7.695 115.671 8.416 Q 115.049 9.566 113.862 10.123 Q 115.195 10.518 115.898 11.705 A 4.557 4.557 0 0 1 116.379 12.89 Q 116.518 13.427 116.57 14.06 A 9.666 9.666 0 0 1 116.602 14.854 L 116.602 15.909 A 7.339 7.339 0 0 1 116.349 17.926 Q 115.467 21.007 111.504 21.007 L 105.264 21.007 A 7.38 7.38 0 0 1 103.241 20.754 Q 100.972 20.106 100.369 17.797 A 7.49 7.49 0 0 1 100.151 15.909 Z M 81.548 21.007 L 81.548 0.001 L 96.855 0.001 L 96.855 3.004 L 84.551 3.004 L 84.551 9.01 L 95.054 9.01 L 95.054 12.012 L 84.551 12.012 L 84.551 18.004 L 96.855 18.004 L 96.855 21.007 L 81.548 21.007 Z M 50.962 21.007 L 47.651 21.007 L 39.551 0.001 L 42.773 0.001 L 49.307 17.286 L 55.854 0.001 L 59.063 0.001 L 50.962 21.007 Z M 16.597 5.113 L 16.597 7.501 Q 16.597 11.895 12.847 12.511 L 16.802 21.007 L 13.477 21.007 L 9.58 12.613 L 3.003 12.613 L 3.003 21.007 L 0 21.007 L 0 0.001 L 11.499 0.001 Q 15.544 0.001 16.379 3.22 A 7.553 7.553 0 0 1 16.597 5.113 Z M 125.391 21.007 L 121.919 21.007 L 131.338 3.004 L 119.604 3.004 L 119.604 0.001 L 135.806 0.001 L 135.806 0.909 L 125.391 21.007 Z M 69.243 0.001 L 75.85 0.001 L 75.85 21.007 L 72.847 21.007 L 72.847 3.004 L 69.243 3.004 L 69.243 0.001 Z M 62.651 0.001 L 65.654 0.001 L 65.654 21.007 L 62.651 21.007 L 62.651 0.001 Z M 3.003 3.004 L 3.003 9.61 L 11.338 9.61 A 4.319 4.319 0 0 0 11.952 9.57 Q 12.672 9.466 13.053 9.096 A 1.313 1.313 0 0 0 13.066 9.083 Q 13.594 8.555 13.594 7.354 L 13.594 5.26 A 4.319 4.319 0 0 0 13.553 4.645 Q 13.449 3.925 13.08 3.545 A 1.313 1.313 0 0 0 13.066 3.531 Q 12.539 3.004 11.338 3.004 L 3.003 3.004 Z"
                fill="url(#r3vi1e37)"
              />
            </svg>
          </transition>
        </div>

        <transition appear enter-active-class="animated fadeInLeft">
          <div class="q-gutter-y-xl">
            <div class="row no-wrap">
              <q-avatar
                color="dark"
                font-size="1rem"
                size="1.515rem"
                text-color="light-green-12"
                class="q-mr-md"
              >
                <q-icon name="check" />
              </q-avatar>
              <div class="invite-message">
                <p class="text-weight-bold">Join The Community</p>
                <p
                  class="text-blue-grey-4"
                  :style="{ maxWidth: '300px', lineHeight: '1.4' }"
                >
                  A massively growing community of cyber security enthusiasts.
                </p>
              </div>
            </div>
            <div class="row no-wrap">
              <q-avatar
                color="dark"
                font-size="1rem"
                size="1.515rem"
                text-color="light-green-12"
                class="q-mr-md"
              >
                <q-icon name="check" />
              </q-avatar>
              <div class="invite-message">
                <p class="text-weight-bold">Train Like A Pro</p>
                <p
                  class="text-blue-grey-4"
                  :style="{ maxWidth: '300px', lineHeight: '1.4' }"
                >
                  Over 300 virtual hacking labs. Training that is hands-on,
                  self-paced, gamified.
                </p>
              </div>
            </div>
            <div class="row no-wrap">
              <q-avatar
                color="dark"
                font-size="1rem"
                size="1.515rem"
                text-color="light-green-12"
                class="q-mr-md"
              >
                <q-icon name="check" />
              </q-avatar>
              <div class="invite-message">
                <p class="text-weight-bold">Land Your Dream Job</p>
                <p
                  class="text-blue-grey-4"
                  :style="{ maxWidth: '300px', lineHeight: '1.4' }"
                >
                  Advance your skills and join our Careers section to find your
                  next dream job.
                </p>
              </div>
            </div>

            <div class="column no-wrap items-center">
              <p class="text-blue-grey-4">
                Have an account?&nbsp;&nbsp;
                <span
                  class="cursor-pointer sign-in text-light-green-12"
                  @click="$router.push({ name: 'Login' })"
                >
                  Sign In
                </span>
              </p>
              <p class="q-gutter-x-xl">
                <span class="text-change cursor-pointer">Contact</span>
                <span class="text-change cursor-pointer">Terms of Service</span>
                <span class="text-change cursor-pointer">Privacy Policy</span>
              </p>
              <p
                class="text-blue-grey-4 footer-content cursor-pointer"
                @click="$router.push({ name: 'Intro' })"
              >
                Copyright (&COPY;) 2023 R3VI1E37
              </p>
            </div>
          </div>
        </transition>
      </div>
    </div>

    <div class="col row items-center">
      <transition appear enter-active-class="animated fadeInRight">
        <q-form class="login-form" @submit="joinUserRequest">
          <q-card
            dark
            flat
            class="bg-grey-10 q-pa-lg"
            :style="{ minWidth: '400px' }"
          >
            <q-card-section>
              <p class="text-h6 q-mb-none">Create Your Account.</p>
              <p class="text-h6 text-light-green-12">
                Join Thousands Of Hackers
              </p>
            </q-card-section>

            <q-card-section class="q-gutter-y-md">
              <q-input
                no-error-icon
                filled
                hint=""
                :spellcheck="false"
                dense
                dark
                color="light-green-12"
                label-color="light-green-12"
                outlined
                v-model="registerRequest.nickname"
                label="Nickname"
                :rules="[
                  nickname =>
                    nicknameValidMethod(nickname) || 'Nickname is required'
                ]"
                lazy-rules
              >
                <template #append>
                  <q-icon
                    color="light-green-12"
                    :name="nicknameValidStatus ? 'check' : ''"
                  />
                </template>
              </q-input>

              <div
                class="row justify-center items-center no-wrap q-gutter-x-md"
                :style="{ width: '400px' }"
              >
                <q-input
                  no-error-icon
                  filled
                  hint=""
                  type="email"
                  :spellcheck="false"
                  dense
                  dark
                  color="light-green-12"
                  label-color="light-green-12"
                  outlined
                  v-model="registerRequest.email"
                  label="Email"
                  :rules="[
                    email => emailValidMethod(email) || 'Email is required'
                  ]"
                  lazy-rules
                  class="col-7"
                >
                  <template #append>
                    <q-icon
                      color="light-green-12"
                      :name="emailValidStatus ? 'check' : ''"
                    />
                  </template>
                  <template #after>
                    <q-btn
                      :ripple="false"
                      outline
                      padding="6px"
                      label="send"
                      :disable="!emailValidStatus"
                      color="light-green-12"
                      @click="console.log('send')"
                    />
                  </template>
                </q-input>

                <q-input
                  no-error-icon
                  filled
                  hint=""
                  :spellcheck="false"
                  dense
                  dark
                  color="light-green-12"
                  label-color="light-green-12"
                  outlined
                  label="Code"
                  class="col"
                  maxlength="6"
                >
                  <template #after>
                    <q-btn
                      :ripple="false"
                      outline
                      label="verify"
                      color="light-green-12"
                      padding="6px"
                      @click="console.log('verify')"
                    />
                  </template>
                </q-input>
              </div>

              <q-input
                no-error-icon
                filled
                hint=""
                :spellcheck="false"
                :type="isPwd ? 'password' : 'text'"
                dense
                dark
                color="light-green-12"
                label-color="light-green-12"
                outlined
                v-model="registerRequest.password"
                label="Password"
                :rules="[
                  password =>
                    passwordValidMethod(password) || 'Password is required'
                ]"
                lazy-rules
              >
                <template #append>
                  <q-icon
                    color="light-green-12"
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class="cursor-pointer"
                    @click="isPwd = !isPwd"
                  />
                </template>
                <!-- <template #after>
                  <q-icon
                    color="light-green-12"
                    :name="passwordValidStatus ? 'check' : ''"
                  />
                </template> -->
              </q-input>

              <q-input
                no-error-icon
                filled
                hint=""
                :spellcheck="false"
                dense
                dark
                color="light-green-12"
                label-color="light-green-12"
                type="password"
                outlined
                v-model="confirmPassword"
                label="Confirm Password"
                :rules="[
                  password =>
                    confirmPasswordMethod(password) || 'Password Incorrect'
                ]"
                lazy-rules
              >
                <template #append>
                  <q-icon
                    color="light-green-12"
                    :name="confirmPasswordStatus ? 'check' : ''"
                  />
                </template>
              </q-input>

              <q-input
                no-error-icon
                filled
                hint=""
                :spellcheck="false"
                dense
                dark
                color="light-green-12"
                label-color="light-green-12"
                outlined
                v-model="registerRequest.phone"
                label="Phone"
                :rules="[
                  phone => phoneValidMethod(phone) || 'Phone is required'
                ]"
                lazy-rules
              >
                <template #append>
                  <q-icon
                    color="light-green-12"
                    :name="phoneValidStatus ? 'check' : ''"
                  />
                </template>
              </q-input>

              <q-radio
                dense
                dark
                color="light-green-12"
                v-model="registerRequest.gender"
                checked-icon="task_alt"
                unchecked-icon="panorama_fish_eye"
                val="MALE"
                label="Male"
                class="q-mr-lg"
              />

              <q-radio
                dense
                dark
                color="light-green-12"
                v-model="registerRequest.gender"
                checked-icon="task_alt"
                unchecked-icon="panorama_fish_eye"
                val="FEMALE"
                label="Female"
              />
            </q-card-section>

            <q-card-section>
              <q-btn
                :ripple="false"
                :disable="!successValidate"
                outline
                label="Join"
                type="submit"
                color="light-green-12"
                :style="{ width: '100%' }"
              />
            </q-card-section>
          </q-card>
        </q-form>
      </transition>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { joinUser } from 'src/api/auth';

// JsonObject
const registerRequest = ref({
  nickname: 'revi1337',
  email: 'FUCK5@com',
  password: 'asdf',
  gender: 'MALE',
  phone: '010-3551-1998'
});

// Join Request
const joinUserRequest = async () => {
  try {
    const jsonData = {
      nickname: registerRequest.value.nickname,
      email: registerRequest.value.email,
      password: registerRequest.value.password,
      gender: registerRequest.value.gender,
      phone: registerRequest.value.phone
    };
    await joinUser(jsonData);
  } catch (error) {
    console.error(error);
  }
};

// Validator
const isPwd = ref(true);

const confirmPassword = ref('');
const confirmPasswordStatus = ref(false);
const confirmPasswordMethod = password => {
  if (
    confirmPassword.value.length === 0 ||
    registerRequest.value.password !== password
  ) {
    confirmPasswordStatus.value = false;
    return false;
  }
  confirmPasswordStatus.value = true;
  return true;
};

const nicknameValidStatus = ref(false);
const nicknameValidMethod = nickname => {
  if (!nickname) {
    nicknameValidStatus.value = false;
    return false;
  }
  nicknameValidStatus.value = true;
  return true;
};

const emailValidStatus = ref(false);
const emailValidMethod = email => {
  if (!email) {
    emailValidStatus.value = false;
    return false;
  }
  emailValidStatus.value = true;
  return true;
};

const passwordValidStatus = ref(false);
const passwordValidMethod = password => {
  if (!password) {
    passwordValidStatus.value = false;
    return false;
  }
  passwordValidStatus.value = true;
  return true;
};

const phoneValidStatus = ref(false);
const phoneValidMethod = phone => {
  if (!phone) {
    phoneValidStatus.value = false;
    return false;
  }
  phoneValidStatus.value = true;
  return true;
};

const successValidate = computed(
  () =>
    nicknameValidStatus.value &&
    emailValidStatus.value &&
    passwordValidStatus.value &&
    confirmPasswordStatus.value &&
    phoneValidStatus.value
);
</script>

<style lang="scss" scoped>
.login-form {
  margin-left: 17%;
}
.invite {
  margin-right: 6rem;
}
.invite-message p {
  margin-bottom: 8px;
}
.sign-in {
  position: relative;
  text-decoration: none;
}
.sign-in:before {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 0;
  height: 2px;
  background-color: $light-green-12;
  transition: width 0.3s ease-in-out;
}
.sign-in:hover:before {
  width: 100%;
  cursor: pointer;
}
.text-change:hover {
  color: $light-green-12;
}
</style>
