<template>
  <p class="text-h5 text-weight-bold">Recent Post</p>
  <q-carousel
    :style="{ height: 'auto', width: 'auto' }"
    class="carousel"
    dark
    swipeable
    animated
    v-model="slide"
    :autoplay="autoplay"
    ref="carousel"
    infinite
    transition-prev="slide-right"
    transition-next="slide-left"
  >
    <template v-for="(post, index) in posts" :key="index">
      <q-carousel-slide :name="index + 1">
        <div>
          <PostComponent
            :id="post.id"
            :title="post.title"
            :content="post.content"
            :hashtag="post.hashtag"
            :created-at="post.createdAt"
            :created-by="post.createdBy"
            :category="post.category"
            :shadow="false"
            :border="false"
            v-slot="{ svgLink, timeToRead, content }"
          >
            <div class="row items-center justify-center">
              <div class="col-3 row items-center justify-center">
                <q-avatar size="13rem" square>
                  <q-img :src="svgLink" />
                </q-avatar>
              </div>

              <div class="col-7 row items-center justify-center q-ml-xl">
                <div class="q-ma-lg q-gutter-y-md">
                  <div>
                    <q-avatar size="md">
                      <q-img
                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZHnYW3u0Y2pKCoepqCtCchGi89SaRO4_oZWyg7ial3BmDSAqGElB_LMIqmWEIiTUCpLs&usqp=CAU"
                      />
                    </q-avatar>
                    <span class="q-ml-sm">{{ post.createdBy }}</span>
                  </div>

                  <div class="text-h4 text-bold">{{ post.title }}</div>

                  <div class="text-caption">
                    {{ content }}
                  </div>

                  <q-item-label class="q-mb-md">
                    <span class="text-overline text-red">{{ post.category.toUpperCase() }}</span>
                    <span class="text-bold">&nbsp;·&nbsp;</span>
                    <span class="text-caption">{{ timeToRead }}</span>
                  </q-item-label>
                </div>
              </div>
            </div>
          </PostComponent>
        </div>
      </q-carousel-slide>
    </template>
  </q-carousel>
</template>

<script setup>
import { ref } from 'vue';
import { findAllPost } from 'src/api/posts';
import PostComponent from 'src/components/PostComponent.vue';
import { onMounted } from 'vue';

// request Recent post
const posts = ref([]);
const postRequestParam = ref({
  page: 1,
  size: 4,
  category: 'writeup'
});
const findRecentPostRequest = async param => {
  try {
    const { data } = await findAllPost(param);
    posts.value = data;
  } catch (error) {
    console.error(error);
  }
};

// quarsal
const slide = ref(1);
const autoplay = ref(3000);

onMounted(() => {
  findRecentPostRequest(postRequestParam.value); // TODO: 프롭스로 넘기든 뭐든 해야한다.
});
</script>

<style lang="scss" scoped>
.carousel {
  color: $font-color;
}
</style>
