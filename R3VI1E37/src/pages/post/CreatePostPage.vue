<template>
  <div class="q-pt-xl row no-wrap q-col-gutter-xl" :style="{ height: '800px' }">
    <div class="col-6 column justify-between">
      <div class="row no-wrap">
        <q-select
          spellcheck="false"
          class="col-3 q-mr-lg"
          color="light-green-12"
          dark
          label="Category"
          label-color="light-green-12"
          v-model="model"
          use-input
          hide-selected
          fill-input
          input-debounce="0"
          :options="options"
          @filter="filterFn"
          hint="Choice One"
          style="padding-bottom: 32px"
        >
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey"> No results </q-item-section>
            </q-item>
          </template>
        </q-select>

        <q-select
          spellcheck="false"
          class="col-grow"
          dark
          label="HashTags"
          label-color="light-green-12"
          v-model="model2"
          use-input
          use-chips
          multiple
          hide-dropdown-icon
          hint="Fill Input and Press ENTER (Can Multiple)"
          input-debounce="0"
          @new-value="createValue"
          style="padding-bottom: 32px"
        />
      </div>

      <div>
        <div class="q-mt-lg">
          <q-input
            dark
            :spellcheck="false"
            filled
            autofocus
            counter
            v-model="createPostData.title"
            color="light-green-12"
            label="Title"
            label-color="light-green-12"
            hint="Title Must Be Specified"
          />
        </div>
      </div>

      <div>
        <!-- @keydown.ctrl.v.prevent="handlingPaste" -->
        <div class="q-mt-md">
          <q-input
            :spellcheck="false"
            dark
            filled
            counter
            v-model="createPostData.content"
            label="Content"
            label-color="light-green-12"
            type="textarea"
            color="light-green-12"
            :input-style="{ minHeight: '400px', lineHeight: '30px' }"
            @keydown.tab.prevent="handlingTabKey"
            @paste="event => handlingPaste(event)"
          />
        </div>
      </div>
    </div>

    <div class="col-6 mark-down scroll">
      <p class="text-h3 text-weight-bold">{{ createPostData.title }}</p>
      <q-separator dark spaced />
      <div class="markdown-container" v-html="markdownToHtml"></div>
    </div>

    <!-- <img v-if="imageDataURL" :src="imageDataURL" alt="Pasted Image" /> -->
  </div>

  <div class="text-right q-pt-md q-mr-xl">
    <q-btn
      :ripple="false"
      label="upload"
      outline
      color="light-green-12"
      class="q-mr-md"
      @click="createPostDialog = !createPostDialog"
    />
    <q-btn
      :ripple="false"
      label="back"
      outline
      color="light-green-12"
      @click="$router.push({ name: 'Index' })"
    />
  </div>

  <!-- {{ createPostDialog }} -->
  <q-dialog v-model="createPostDialog">
    <q-card dark flat>
      <q-card-section>
        <div class="text-h6 text-font-color">Create Post?</div>
      </q-card-section>
      <q-card-section class="row items-center q-gutter-sm">
        <q-btn label="Create" color="primary" @click="createPostRequest()" />
        <q-btn v-close-popup label="cancel" color="primary" />
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { computed, ref } from 'vue';
import { marked } from 'marked';
import hljs from 'highlight.js';
import 'highlight.js/styles/atom-one-dark.css';
import { createPost } from 'src/api/posts';
import { useRouter } from 'vue-router';

// Select Category & Hashtag
const stringOptions = ['DEV', 'CTF', 'WRITEUP', 'CS', 'CHEETSHEET'];
const options = ref(stringOptions);
const model = ref(null);
const filterFn = (val, update, abort) => {
  update(() => {
    const needle = val.toLowerCase();
    options.value = stringOptions.filter(v => v.toLowerCase().indexOf(needle) > -1);
  });
};
const model2 = ref(null);
const createValue = (val, done) => {
  done(val, 'add-unique');
};

// routing
const router = useRouter();

// Create Post Data
const createPostData = ref({
  title: 'Title',
  content: `
  * Dummy
  `,
  hashtag: 'dummyTag'
});

const createPostDialog = ref(false);
const createPostRequest = async () => {
  createPostDialog.value = false;
  try {
    const jsonData = {
      title: createPostData.value.title,
      content: createPostData.value.content,
      hashtag: createPostData.value.hashtag
    };
    const { data } = await createPost(jsonData);
    router.push({ name: 'Index' });
  } catch (error) {
    console.error(error);
    alert('error');
  }
};

// MarkDown
const renderer = new marked.Renderer();
renderer.code = (code, language) => {
  const validLanguage = hljs.getLanguage(language) ? language : 'plaintext';
  const highlightedCode = hljs.highlight(code, { language }).value;
  return `<pre><code class="hljs ${validLanguage}">${highlightedCode}</code></pre>`;
};
marked.setOptions({
  renderer,
  mangle: false,
  headerIds: false
});
const markdownToHtml = computed(() => marked.parse(createPostData.value.content));

// Handling Tab Key
const handlingTabKey = event => {
  const refer = event.target;
  const currentPosition = refer.selectionStart;
  const newValue = refer.value.slice(0, currentPosition) + '    ' + refer.value.slice(currentPosition);
  refer.value = newValue;
  refer.setSelectionRange(currentPosition + 4, currentPosition + 4);
};

// Handling Copy & Paste
const imageDataURL = ref(null);

const handlingPaste = event => {
  const clipboardData = event.clipboardData || window.clipboardData;
  const items = clipboardData.items;
  for (let i = 0; i < items.length; i++) {
    if (items[i].type.indexOf('image') !== -1) {
      const imageFile = items[i].getAsFile(); // File Object
      uploadImage(imageFile);
    }
  }
};

import axios from 'axios';
const uploadImage = async imageFile => {
  const formData = new FormData();
  formData.append('file', imageFile, imageFile.name);
  const { data } = await axios.post('/api/post/image', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
  createPostData.value.content += `![](http://localhost:8080/api/post/image/${data.replace('.png', '')})`;
};
</script>

<!-- const handlingPaste = event => {
  const clipboardData = event.clipboardData || window.clipboardData;
  const items = clipboardData.items;
  for (let i = 0; i < items.length; i++) {
    if (items[i].type.indexOf('image') !== -1) {
      const imageFile = items[i].getAsFile(); // File Object
      const base64DataURL = toDataURL(imageFile);
      console.log(base64DataURL)
    }
  }
};

const toDataURL = imageFile => {
  const reader = new FileReader();
  reader.onload = event => (imageDataURL.value = event.target.result);
  reader.readAsDataURL(imageFile); // looks like data:image/png;base64,iVBORw0K...
}; -->
