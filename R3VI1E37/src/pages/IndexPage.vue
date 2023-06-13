<template>
  <div class="q-gutter-y-lg">
    <div>
      <p class="text-h5 text-weight-bold">Recent Post</p>
      <q-card flat dark>
        <q-card-section horizontal>
          <q-img
            class="col-5 q-mr-lg"
            img-class="rounding"
            src="https://picsum.photos/500/300"
            spinner-color="white"
          />

          <q-card-section class="q-ml-xl q-px-md q-gutter-y-md row items-center">
            <q-item-label class="col-12">
              <q-avatar size="md">
                <q-img
                  src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZHnYW3u0Y2pKCoepqCtCchGi89SaRO4_oZWyg7ial3BmDSAqGElB_LMIqmWEIiTUCpLs&usqp=CAU"
                />
              </q-avatar>
              <span class="q-ml-sm">Revi1337</span>
              <span class="text-bold">&nbsp;·&nbsp;</span>
              <span class="text-caption">12 min ago</span>
            </q-item-label>

            <div class="text-h4 text-bold q-mt-sm q-mb-xs col-12">
              How to Penetrate Active Directory Environment
            </div>

            <div class="text-caption col-12">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do asdfa sfd eiusmod tempor
              incididunt ut labore et dolore magna aliqua.
            </div>

            <q-item-label class="col-12">
              <span class="text-overline text-red">Pentest</span>
              <span class="text-bold">&nbsp;·&nbsp;</span>
              <span class="text-caption">3 min read</span>
            </q-item-label>
          </q-card-section>
        </q-card-section>
      </q-card>
    </div>

    <div>
      <p class="text-h5 text-weight-bold">Announcement</p>
      <!-- <div>
        <p v-for="({ id, info }, index) in announcementResponses" :key="index">{{ id }}, {{ info }}</p>
      </div> -->
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss">
.rounding {
  box-sizing: border-box;
  border-radius: 10px;
}
</style>
