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
        path: 'post',
        name: 'Post',
        children: [
          {
            path: 'create',
            name: 'CreatePost',
            component: () => import('pages/post/CreatePostPage.vue'),
            meta: {
              layoutPadding: 20
            }
          },
          {
            path: ':category',
            name: 'CategoryPost',
            component: () => import('pages/PostCategoryPage.vue'),
            props: true,
            beforeEnter: ({ params }) => {
              if (
                Object.keys(params).length === 0 ||
                !['dev', 'ctf', 'writeup', 'cs', 'cheetsheet'].includes(params.category)
              )
                return { name: 'CategoryPost', params: { category: 'dev' } };
              console.log(params);
            }
          }
        ]
      },
      {
        path: 'posts/:id',
        name: 'PostDetails',
        component: () => import('pages/post/PostDetails.vue'),
        props: true
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
