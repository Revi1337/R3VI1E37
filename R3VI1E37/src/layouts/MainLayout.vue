<template>
  <div
    class="window-width window-height column justify-between no-wrap hide-scrollbar"
  >
    <header
      class="col-auto row justify-center no-wrap q-gutter-sm"
      :style="{ minHeight: '90px' }"
    >
      <div class="col-8 row justify-between items-center no-wrap">
        <div class="col-auto row no-wrap items-center" @click="goHome">
          <q-avatar square rounded size="lg" class="q-mr-sm">
            <q-img alt="fingerPrint" src="~assets/icon-green.svg" />
          </q-avatar>
          <span class="logo text-h4 text-weight-bold">R3VI1E37</span>
        </div>

        <div class="col-shrink text-right">
          <q-tabs outside-arrows dense v-model="tab" align="justify">
            <q-route-tab
              :ripple="false"
              name="web"
              label="WEB"
              :to="{ name: 'Web' }"
              exact
            />
            <q-route-tab
              :ripple="false"
              name="hacking"
              label="HACKING"
              :to="{ name: 'Hacking' }"
              exact
            />
            <q-route-tab
              :ripple="false"
              name="pentest"
              label="PENTEST"
              :to="{ name: 'Pentest' }"
              exact
            />
            <q-route-tab
              :ripple="false"
              name="cs"
              label="Computer Science"
              :to="{ name: 'CS' }"
              exact
            />
            <q-route-tab
              name="cheet_sheet"
              label="Cheet Sheet"
              :to="{ name: 'Cheet' }"
              exact
            />
          </q-tabs>
        </div>

        <div class="col-auto text-right q-gutter-x-md row flex-center no-wrap">
          <q-btn
            flat
            round
            dense
            icon="fa-brands fa-github"
            target="_blank"
            href="https://github.com/Revi1337"
          />
          <q-btn flat round dense icon="fa-brands fa-twitter" />
          <div>
            <q-btn
              flat
              round
              dense
              size="md"
              icon="fa-regular fa-pen-to-square"
              :to="{ name: 'Post' }"
            />
          </div>

          <q-btn rounded outline>
            <q-avatar size="sm">
              <img
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZHnYW3u0Y2pKCoepqCtCchGi89SaRO4_oZWyg7ial3BmDSAqGElB_LMIqmWEIiTUCpLs&usqp=CAU"
              />
            </q-avatar>
            <span class="q-mx-sm">revi1337</span>
          </q-btn>

          <q-btn
            flat
            round
            dense
            icon="fa-solid fa-right-to-bracket"
            :to="{ name: 'Login' }"
          />
        </div>
      </div>
    </header>

    <main class="col">
      <div class="row justify-center q-mt-xl">
        <div class="col-8">
          <router-view />
        </div>
      </div>
    </main>

    <!-- <div class="col-auto relative">
      <footer-component />
    </div> -->
  </div>
</template>

<script setup>
import FooterComponent from 'src/components/FooterComponent.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const tab = ref('');
const router = useRouter();

const goHome = () => {
  router.push({ name: 'Index' });
};
</script>

<style lang="scss">
header {
  min-height: 100px;
  max-height: 100px;
}
.logo {
  font-size: 22px;
  cursor: pointer;
}
</style>
