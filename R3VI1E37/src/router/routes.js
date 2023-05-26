const routes = [
  {
    path: '/',
    name: 'Intro',
    component: () => import('pages/IntroPage.vue')
  },
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('pages/IndexPage.vue')
      },
      {
        path: 'web',
        name: 'Web',
        component: () => import('pages/WebPage.vue')
      },
      {
        path: 'hacking',
        name: 'Hacking',
        component: () => import('pages/HackingPage.vue')
      },
      {
        path: 'pentest',
        name: 'Pentest',
        component: () => import('pages/PentestPage.vue')
      },
      {
        path: 'cs',
        name: 'CS',
        component: () => import('pages/ComputerSciencePage.vue')
      },
      {
        path: 'cheet',
        name: 'Cheet',
        component: () => import('pages/CheetSheetPage.vue')
      },
      {
        path: 'post',
        name: 'Post',
        component: () => import('pages/post/CreatePostPage.vue')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('layouts/AuthLayout.vue'),
    children: [
      {
        path: '',
        name: 'Login',
        component: () => import('pages/auth/LoginPage.vue')
      }
    ]
  },
  {
    path: '/join',
    component: () => import('layouts/AuthLayout.vue'),
    children: [
      {
        path: '',
        name: 'Join',
        component: () => import('pages/auth/JoinPage.vue')
      }
    ]
  },
  {
    path: '/demo/particle',
    name: 'Particle',
    component: () => import('pages/IntroPage.vue')
  },
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
];

export default routes;
