<template>
  <article
    class="article-container"
    :class="{ hovered: isHovered }"
    :style="{ boxShadow: getBoxShadowColor }"
    @click="goPostDetails()"
    @mouseenter="slideSwitch()"
    @mouseleave="slideSwitch()"
  >
    <div class="article-items">
      <q-avatar size="4rem">
        <q-img width="48px" :src="getSvgLink" />
      </q-avatar>
    </div>

    <div class="article-items article-content">
      <span class="title">{{ title }}</span>
      <span class="content">{{ getContent }} </span>
    </div>

    <div class="article-items article-info">
      <span>
        <q-badge rounded outline :color="getBadgeColor" :label="hashtag" />
      </span>
      <span>Created : {{ formattedDateTime }}</span>
      <span>createdBy : {{ createdBy }}</span>
    </div>
  </article>

  <Transition name="articleSlider">
    <div v-if="swch" class="q-ml-lg">
      <slot name="slide"> DEFUALT SLIDE </slot>
    </div>
  </Transition>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { computed, ref } from 'vue';

const swch = ref(false);
const isHovered = ref(false);
const slideSwitch = () => {
  isHovered.value = !isHovered.value;
  swch.value = !swch.value;
};

const props = defineProps({
  id: {
    type: Number,
    required: false
  },
  title: {
    type: String,
    required: true
  },
  content: {
    type: String,
    required: true
  },
  hashtag: {
    type: String,
    required: true
  },
  createdAt: {
    type: String,
    required: true
  },
  createdBy: {
    type: String,
    required: true
  }
});

// GotoPostDetails
const router = useRouter();
const goPostDetails = () => {
  router.push({ name: 'PostDetails', params: { id: props.id } });
  // router.push({ name: 'Index' });
};

// Parsing Datetime
const date = new Date(props.createdAt);
const year = date.getFullYear();
const month = String(date.getMonth() + 1).padStart(2, '0');
const day = String(date.getDate()).padStart(2, '0');
const hours = String(date.getHours()).padStart(2, '0');
const minutes = String(date.getMinutes()).padStart(2, '0');
const formattedDateTime = `${year}-${month}-${day} ${hours}:${minutes}`;

// Manipulize Props Data
const getContent = computed({
  get: () => props.content.slice(0, 80)
});
const getSvgLink = computed({
  get: () => {
    if (props.hashtag === 'Spring') return 'https://www.vectorlogo.zone/logos/springio/springio-icon.svg';
    else if (props.hashtag === 'Vue') return 'https://www.vectorlogo.zone/logos/vuejs/vuejs-icon.svg';
    else if (props.hashtag === 'Python')
      return 'https://upload.wikimedia.org/wikipedia/commons/archive/c/c3/20220821155028%21Python-logo-notext.svg';
    else if (props.hashtag === 'Bash')
      return 'https://upload.wikimedia.org/wikipedia/commons/4/4b/Bash_Logo_Colored.svg';
    else if (props.hashtag === 'Java') return 'https://www.vectorlogo.zone/logos/java/java-icon.svg';
    else if (props.hashtag === 'JavaScript')
      return 'https://upload.wikimedia.org/wikipedia/commons/9/99/Unofficial_JavaScript_logo_2.svg';
    else if (props.hashtag === 'Quasar') return 'https://cdn.quasar.dev/logo-v2/svg/logo-dark.svg';
    else if (props.hashtag === 'HackTheBox') return 'HackTheBox.svg';
    else if (props.hashtag === 'TryHackMe') return 'TryHackMe.svg';
    else return 'Unknown';
  }
});
const getBadgeColor = computed({
  get: () => {
    if (props.hashtag === 'Spring') return 'Spring';
    else if (props.hashtag === 'Vue') return 'Vue';
    else if (props.hashtag === 'Python') return 'Python';
    else if (props.hashtag === 'Bash') return 'Bash';
    else if (props.hashtag === 'Java') return 'Java';
    else if (props.hashtag === 'JavaScript') return 'JavaScript';
    else if (props.hashtag === 'Quasar') return 'Quasar';
    else if (props.hashtag === 'HackTheBox') return 'HackTheBox';
    else if (props.hashtag === 'TryHackMe') return 'TryHackMe';
    else return 'Unknown';
  }
});

// 0 0 5px #42b883, 0 0 15px #42b883, 0 0 30px #42b883, 0 0 65px #42b883
const getBoxShadowColor = computed({
  get: () => {
    if (isHovered.value) {
      if (props.hashtag === 'Spring') {
        return '0 0 3px #6cb52d, 0 0 11px #6cb52d, 0 0 25px #6cb52d, 0 0 45px #6cb52d';
      } else if (props.hashtag === 'Vue') {
        return '0 0 3px #42b883, 0 0 11px #42b883, 0 0 25px #42b883, 0 0 45px #42b883';
      } else if (props.hashtag === 'Python') {
        return '0 0 3px #3d7daf, 0 0 11px #3d7daf, 0 0 25px #3d7daf, 0 0 45px #3d7daf';
      } else if (props.hashtag === 'Bash') {
        return '0 0 3px #fefefe, 0 0 11px #fefefe, 0 0 25px #fefefe, 0 0 45px #fefefe';
      } else if (props.hashtag === 'Java') {
        return '0 0 3px #b07219, 0 0 11px #b07219, 0 0 25px #b07219, 0 0 45px #b07219';
      } else if (props.hashtag === 'JavaScript') {
        return '0 0 3px #f1e05a, 0 0 11px #f1e05a, 0 0 25px #f1e05a, 0 0 45px #f1e05a';
      } else if (props.hashtag === 'Quasar') {
        return '0 0 3px #00b4ff, 0 0 11px #00b4ff, 0 0 25px #00b4ff, 0 0 45px #00b4ff';
      } else if (props.hashtag === 'HackTheBox') {
        return '0 0 3px #9fef00, 0 0 11px #9fef00, 0 0 25px #9fef00, 0 0 45px #9fef00';
      } else if (props.hashtag === 'TryHackMe') {
        return '0 0 3px #ff0000, 0 0 11px #ff0000, 0 0 25px #ff0000, 0 0 45px #ff0000';
      } else return 'none';
    } else {
      return 'none';
    }
  }
});
</script>

<style lang="scss" scoped>
.text-Spring {
  color: #6cb52d !important;
}
.text-Vue {
  color: #42b883 !important;
}
.text-Python {
  color: #3d7daf !important;
}
.text-Bash {
  color: #fefefe !important;
}
.text-Java {
  color: #b07219 !important;
}
.text-JavaScript {
  color: #f1e05a !important;
}
.text-Quasar {
  color: #00b4ff !important;
}
.text-HackTheBox {
  color: #9fef00 !important;
}
.text-TryHackMe {
  color: #ff0000 !important;
}
span {
  display: block;
}

.article-container {
  margin: 20px 5px 20px 5px;
  min-height: 110px;
  border: 1px solid $font-color;
  border-radius: 7px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
  transition: all 0.2s ease;

  &:hover {
    box-shadow: var(--box-shadow-color);
  }

  .article-items {
    margin: 0 20px 0;
  }

  .article-content {
    flex-grow: 1;
    .title {
      font-size: 22px;
      font-weight: 900;
    }
    .content {
      max-width: 400px;
      letter-spacing: 1px;
    }
  }

  .article-info {
    width: 250px;
    line-height: 30px;
  }
}

.articleSlider-enter-from {
  opacity: 0;
}
.articleSlider-enter-active {
  transition: opacity 0.5s ease;
}
.articleSlider-enter-to {
  opacity: 1;
}
</style>
