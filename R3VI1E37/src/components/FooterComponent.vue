<template>
  <footer
    class="row justify-between items-center no-wrap"
    :style="{ minHeight: '62px', position: props.position }"
  >
    <p class="footer-content policy cursor-pointer">Privacy Policy</p>
    <p class="footer-content">Copyright (&COPY;) 2023 R3VI1E37</p>
  </footer>
  <!-- <footer class="row justify-center" :style="{ minHeight: '62px' }">
    <div class="col-8 row items-center justify-between no-wrap">
      <p class="footer-content policy cursor-pointer">Privacy Policy</p>
      <p class="footer-content">Copyright (&COPY;) 2023 R3VI1E37</p>
    </div>
  </footer> -->
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  type: {
    type: [String],
    required: false,
    default: 'intro'
  }
});
</script>

<style lang="scss" scoped>
footer {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  padding: 0 75px 0;
}
.footer-content {
  margin-bottom: 0;
}
.policy {
  position: relative;
  text-decoration: none;
}
.policy:before {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 0;
  width: 0;
  height: 2px;
  background-color: $font-color;
  transition: width 0.3s ease-in-out;
}
.policy:hover:before {
  width: 100%;
}
</style>
