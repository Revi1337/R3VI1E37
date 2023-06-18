<template>
  <div>
    <p class="text-h5 text-weight-bold">Recent Post</p>
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
      <div class="row">
        <div class="col-auto row items-center">
          <q-avatar size="15rem" square>
            <q-img :src="svgLink" />
          </q-avatar>
        </div>

        <div class="col row items-center q-ml-xl">
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
</template>

<script setup>
import { findPostById } from 'src/api/posts';
import PostComponent from 'src/components/PostComponent.vue';
import { ref } from 'vue';

// Initialize
const post = ref({
  id: '',
  title: '',
  content: '',
  hashtag: '',
  createdAt: '',
  createdBy: '',
  category: ''
});

const showElement = ref(false);

const findPostByIdRequest = async id => {
  try {
    const { data } = await findPostById(id);
    post.value = { ...data };
  } catch (error) {
    console.error(error);
  }
};
findPostByIdRequest(28);
</script>
