<template>
  <div>
    <router-view />
  </div>
  <div>
    <Particles
      id="tsparticles"
      :particlesInit="particlesInit"
      :particlesLoaded="particlesLoaded"
      :options="{
        // 여기 시작
        particles: {
          color: {
            value: '#FF0000',
            animation: {
              enable: false,
              speed: 1
            }
          },
          move: {
            attract: {
              enable: true,
              distance: 100,
              rotate: {
                x: 2000,
                y: 2000
              }
            },
            direction: 'none',
            enable: true,
            outModes: {
              // split, out, none, destroy, bounceVertical, bounceHorizontal, bounce
              default: 'split'
            },
            path: {
              clamp: false,
              enable: true,
              delay: {
                value: 0
              },
              generator: 'polygonPathGenerator',
              options: {
                sides: 6,
                turnSteps: 50, // 요놈 크기 30
                angle: 30
              }
            },
            random: false,
            speed: 1.7, // 요놈이랑 맞춰서 사이즈를 조절
            straight: true,
            trail: {
              fillColor: '#000',
              length: 50,
              enable: true
            }
          },
          number: {
            density: {
              enable: true,
              area: 800
            },
            value: 0
          },
          opacity: {
            value: 1
          },
          shape: {
            type: 'circle'
          },
          size: {
            value: 1
          }
        }, // 여까지가 particles 끝
        background: {
          color: $dark
        },
        fullScreen: {
          zIndex: -1
        },
        emitters: {
          direction: 'none',
          rate: {
            quantity: 2,
            delay: 1.5
          },
          size: {
            width: 70,
            height: 70
          },
          position: {
            x: 50,
            y: 50
          }
        },
        retina_detect: true
      }"
    />
  </div>
</template>

<script setup>
import { loadFull } from 'tsparticles';
import { loadPolygonPath } from 'tsparticles-path-polygon';

// tsParticles
const particlesInit = async engine => {
  loadPolygonPath(engine);
  await loadFull(engine);
};
const particlesLoaded = async container => {
  console.log('Particles container loaded', container);
};
</script>

<style lang="scss" scoped></style>
