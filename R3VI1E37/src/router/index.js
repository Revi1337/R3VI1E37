import { route } from 'quasar/wrappers';
import {
  createRouter,
  createMemoryHistory,
  createWebHistory,
  createWebHashHistory,
  useRouter
} from 'vue-router';
import routes from './routes';
import { storeToRefs } from 'pinia';
import { useAuthStore } from 'src/stores/auth-store';

export default route(function (/* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : process.env.VUE_ROUTER_MODE === 'history'
    ? createWebHistory
    : createWebHashHistory;

  const router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,

    history: createHistory(process.env.MODE === 'ssr' ? void 0 : process.env.VUE_ROUTER_BASE)
  });

  const { isAuthenticated, roles } = storeToRefs(useAuthStore());

  router.beforeEach((to, from, next) => {
    const authenticated = isAuthenticated.value;
    const userRoles = roles.value;
    const { authorization } = to.meta;

    console.log(authorization);
    console.log(userRoles);

    if (authorization) {
      if (!authenticated) return next({ name: 'Login' });
      if (authorization.length && userRoles.filter(role => authorization.includes(role)).length === 0)
        return next({ name: 'Index' });
    }

    next();
  });
  return router;
});
