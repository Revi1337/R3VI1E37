<template>
  <div class="q-pt-xl row no-wrap q-col-gutter-xl" :style="{ height: '800px' }">
    <div class="col-6">
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

      <!-- <div class="text-right q-mt-xl">
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
      </div> -->
    </div>

    <div class="col-6 mark-down scroll">
      <p class="text-h3 text-weight-bold">{{ createPostData.title }}</p>
      <q-separator dark spaced />
      <div class="markdown-container" v-html="markdownToHtml"></div>
    </div>
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

// routing
const router = useRouter();

// Create Post Data
const createPostData = ref({
  title: 'Title',
  content: `
| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |
# PC
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
### Port Scan
\`\`\`bash
$nmap
\`\`\`
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
### Information Gathering
asdasd \`as\` dasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
### SQL Injection (SQLite3)
asd \`DUMMY\` \n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
### Initial Access
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
### Network Pivoting (SSH Tunneling)
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
### 1-Day Exploit(CVE-2023-0297)
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
### Privilege Escalation
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
asdasdasdasdDUMMY\n
\`\`\`bash
$curl 10.10.11.211 -I
HTTP/1.1 200 OK
Server: nginx/1.18.0 (Ubuntu)
...
X-Powered-By: PHP/7.4.33
...
P3P: CP="CAO PSA OUR"
...
Set-Cookie: Cacti=3a7ee08d96abc903030aba5226681aad; path=/; HttpOnly; SameSite=Strict
\`\`\`
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
</script>
