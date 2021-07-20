import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/account/signup',
      component: () => import('@/views/SignupPage.vue'),
    },
    {
      path: '/account/login',
      component: () => import('@/views/LoginPage.vue'),
    },
    {
      path: '/account/edit',
      component: () => import('@/views/UserEdit.vue'),
    },
    {
      path: '/main',
      component: () => import('@/views/MainPage.vue'),
    },
  ],
})

export default router
