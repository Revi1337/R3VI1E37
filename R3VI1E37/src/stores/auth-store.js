import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    nickname: null,
    accessToken: null,
    isAuthenticated: false
  }),
  persist: true
});
