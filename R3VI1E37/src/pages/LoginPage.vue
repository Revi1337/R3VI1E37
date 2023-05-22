<template>
  <div>
    <q-form @submit.prevent="userLoginRequest">
      <q-card
        dark
        class="border-light-green bg-grey-10 text-light-green-12 q-pa-lg column justify-between"
        :style="{ minWidth: '500px', minHeight: '500px' }"
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
            <q-checkbox
              keep-color
              v-model="teal"
              label="Remember Me"
              color="light-green-12"
            />
            <router-link to="/forgot" class="underline text-white">
              Forgot Your Password?
            </router-link>
          </div>
        </q-card-section>

        <q-card-section class="q-gutter-y-lg">
          <div class="row">
            <q-btn
              type="submit"
              class="col"
              :ripple="false"
              outline
              label="login"
              color="light-green-12"
            />
          </div>

          <q-separator dark inset />

          <div class="row">
            <q-btn
              class="col"
              :ripple="false"
              outline
              color="green-12"
              href="/api/oauth2/google"
            >
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="fa-brands fa-google" class="col-1" />
                <div class="col">sign in google</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn
              class="col"
              :ripple="false"
              outline
              color="green-12"
              href="/api/oauth2/github"
            >
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="fa-brands fa-github" class="col-1" />
                <div class="col">sign in github</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn
              class="col"
              :ripple="false"
              outline
              color="green-12"
              href="/api/oauth2/facebook"
            >
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon
                  size="16px"
                  name="fa-brands fa-facebook"
                  class="col-1"
                />
                <div class="col">sign in facebook</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn
              class="col"
              :ripple="false"
              outline
              color="green-12"
              href="/api/oauth2/naver"
            >
              <div class="row items-center" :style="{ minWidth: '200px' }">
                <q-icon size="16px" name="img:/naver.svg" class="col-1" />
                <div class="col">sign in naver</div>
              </div>
            </q-btn>
          </div>

          <div class="row">
            <q-btn
              class="col"
              :ripple="false"
              outline
              color="green-12"
              href="/api/oauth2/kakao"
            >
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
              to="/register"
            />
          </div>
        </q-card-section>
      </q-card>
    </q-form>
  </div>

  <div>
    <Particles
      id="tsparticles"
      :particlesInit="particlesInit"
      :particlesLoaded="particlesLoaded"
      :options="{
        // 여기 시작
        particles: {
          color: {
            value: '#FF0000',
            animation: {
              enable: false,
              speed: 1
            }
          },
          move: {
            attract: {
              enable: true,
              distance: 100,
              rotate: {
                x: 2000,
                y: 2000
              }
            },
            direction: 'none',
            enable: true,
            outModes: {
              // split, out, none, destroy, bounceVertical, bounceHorizontal, bounce
              default: 'split'
            },
            path: {
              clamp: false,
              enable: true,
              delay: {
                value: 0
              },
              generator: 'polygonPathGenerator',
              options: {
                sides: 6,
                turnSteps: 50, // 요놈 크기 30
                angle: 30
              }
            },
            random: false,
            speed: 1.7, // 요놈이랑 맞춰서 사이즈를 조절
            straight: true,
            trail: {
              fillColor: '#000',
              length: 50,
              enable: true
            }
          },
          number: {
            density: {
              enable: true,
              area: 800
            },
            value: 0
          },
          opacity: {
            value: 1
          },
          shape: {
            type: 'circle'
          },
          size: {
            value: 1
          }
        }, // 여까지가 particles 끝
        background: {
          color: $dark
        },
        fullScreen: {
          zIndex: -1
        },
        emitters: {
          direction: 'none',
          rate: {
            quantity: 2,
            delay: 1.5
          },
          size: {
            width: 70,
            height: 70
          },
          position: {
            x: 50,
            y: 50
          }
        },
        retina_detect: true
      }"
    />
  </div>
</template>

<script setup>
import { loadFull } from 'tsparticles';
import { loadPolygonPath } from 'tsparticles-path-polygon';
import { ref } from 'vue';
import { userLogin } from 'src/api/posts';

// tsParticles
const particlesInit = async engine => {
  loadPolygonPath(engine);
  await loadFull(engine);
};
const particlesLoaded = async container => {
  console.log('Particles container loaded', container);
};

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
    const { headers } = await userLogin(jsonData);
    const authHeader = headers['authorization'];
    console.log(authHeader);
  } catch (error) {
    console.error(error);
  }
};
</script>

<style lang="scss" scoped>
.text-white {
  color: $font-color !important;
}
.border-light-green {
  border: 1px solid $light-green-12;
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
</style>

<style lang="scss" scoped></style>
