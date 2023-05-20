```bash
$npm i vue3-particles
$npm i tsparticles
```

```javascript
// src/boot/particles.js --> file name doesn't matter
import Particles from 'vue3-particles';
import { boot } from 'quasar/wrappers';

export default boot(({ app }) => {
  app.use(Particles);
});
```

```javascript
// quasar.config.js
module.exports = configure(function (ctx) {
  return {
    ...
    boot: ['particles'],  // append your boot file name --> exclude suffix

  }
```

```javascript
// It must be written in the component where you want to use Particle.
<template>
  <div>
    <Particles
      id="tsparticles"
      :particlesInit="particlesInit"
      :particlesLoaded="particlesLoaded"
      :options="{
        background: {
          color: {
            value: '#0d47a1'
          }
        },
        fpsLimit: 120,
        interactivity: {
          events: {
            onClick: {
              enable: true,
              mode: 'push'
            },
            onHover: {
              enable: true,
              mode: 'grab'
            },
            resize: true
          },
          modes: {
            bubble: {
              distance: 400,
              duration: 2,
              opacity: 0.8,
              size: 40
            },
            push: {
              quantity: 4
            },
            repulse: {
              distance: 200,
              duration: 0.4
            }
          }
        },
        particles: {
          color: {
            value: '#ffffff'
          },
          links: {
            color: '#ffffff',
            distance: 150,
            enable: true,
            opacity: 0.5,
            width: 1
          },
          collisions: {
            enable: true
          },
          move: {
            direction: 'none',
            enable: true,
            outMode: 'bounce',
            random: false,
            speed: 6,
            straight: false
          },
          number: {
            density: {
              enable: true,
              area: 800
            },
            value: 80
          },
          opacity: {
            value: 0.5
          },
          shape: {
            type: 'circle'
          },
          size: {
            random: true,
            value: 5
          }
        },
        detectRetina: true
      }"
    />
  </div>
</template>

<script setup>
import { loadFull } from 'tsparticles';

const particlesInit = async engine => {
  await loadFull(engine);
};

const particlesLoaded = async container => {
  console.log('Particles container loaded', container);
};
</script>

<style lang="scss" scoped></style>

```

```shell
# use hexagon path plugin
$npm i tsparticles-path-polygon
```

```javascript
import { loadFull } from 'tsparticles';
import { loadPolygonPath } from 'tsparticles-path-polygon';
const particlesInit = async engine => {
  loadPolygonPath(engine);
  await loadFull(engine);
};
```
