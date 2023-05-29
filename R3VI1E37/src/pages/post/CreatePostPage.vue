<template>
  <div>
    <div class="q-gutter-y-lg">
      <div>
        <p class="text-h5">TITLE</p>
        <q-separator dark spaced />
        <div class="q-mt-md">
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
        <p class="text-h5">CONTENT</p>
        <q-separator dark spaced />
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
          />
        </div>
      </div>
    </div>

    <!-- <div class="col">
        <p class="text-h5">PREVIEW</p>
        <q-separator dark spaced />
        <div v-html="markdownToHtml"></div>
      </div> -->
  </div>

  <div class="text-right">
    <q-btn
      :ripple="false"
      label="save"
      outline
      color="light-green-12"
      class="q-mr-md"
      @click="createPostRequest"
    />
    <q-btn
      :ripple="false"
      label="login"
      outline
      color="light-green-12"
      @click="userLoginRequest"
    />
  </div>

  <div class="mark-down col">
    <p class="text-h5">PREVIEW</p>
    <q-separator dark spaced />
    <div v-html="markdownToHtml"></div>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue';
import { marked } from 'marked';
import hljs from 'highlight.js';
import 'highlight.js/styles/atom-one-dark.css';
import { createPost } from 'src/api/posts';

// Create Post Data
const createPostData = ref({
  title: null,
  content: `1. 구조는 이게 낫나? \n
2. \`Prieview\` 가 될까요? \n
3. 됬으면 좋겠다 진짜\n
\`\`\`java
public static void main(String args[])
\`\`\`


  `,
  hashtag: 'dummyTag'
});
const createPostRequest = async () => {
  try {
    const jsonData = {
      title: createPostData.value.title,
      content: createPostData.value.content,
      hashtag: createPostData.value.hashtag
    };
    const { data } = await createPost(jsonData, null);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
};

// MarkDown
const renderer = new marked.Renderer();
renderer.code = (code, language) => {
  const validLanguage = hljs.getLanguage(language) ? language : 'plaintext';
  // const highlightedCode = hljs.highlight(validLanguage, code).value;
  const highlightedCode = hljs.highlight(code, { language }).value;
  return `<pre><code class="hljs ${validLanguage}">${highlightedCode}</code></pre>`;
};
marked.setOptions({
  renderer,
  mangle: false,
  headerIds: false
});
const markdownToHtml = computed(() =>
  marked.parse(createPostData.value.content)
);

// Handling Tab Key
const handlingTabKey = event => {
  const refer = event.target;
  const currentPosition = refer.selectionStart;
  const newValue =
    refer.value.slice(0, currentPosition) +
    '    ' +
    refer.value.slice(currentPosition);
  refer.value = newValue;
  refer.setSelectionRange(currentPosition + 4, currentPosition + 4);
};
</script>

<style lang="scss">
// mark-down
.mark-down {
  font-size: 18px;
  h6 {
    font-size: 21px;
  }
  h5 {
    font-size: 24px;
  }
  h4 {
    font-size: 27px;
  }
  h3 {
    font-size: 30px;
  }
  h2 {
    font-size: 33px;
  }
  h1 {
    font-size: 36px;
  }
}

// markdown >
.mark-down blockquote {
  padding-left: 20px;
  border-left: 3px solid $light-green-12;
}
// markdown Single code block
.mark-down code {
  padding: 0 5px 2px;
  color: $font-color;
  background-color: $grey-9;
  font-family: RoobertPRO, RoobertPRO override, sans-serif;
  border-radius: 3px;
}
// markdown Multi Code Block
.mark-down code.hljs {
  border-radius: 4px;
  font-family: RoobertPRO, RoobertPRO override, sans-serif;
  background-color: $grey-10;
  font-size: 16px;
}
.mark-down code.hljs::before {
  content: 'CODE \A \A';
  white-space: pre;
  color: $light-green-12;
}
</style>
