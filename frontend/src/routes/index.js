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
      path: '/board/list',
      component: () => import('@/components/board/List.vue')
    },
    {
      path: '/board/create',
      component: () => import('@/components/board/Create.vue')
      path: '/main',
      component: () => import('@/views/MainPage.vue'),
    },
  ],
})

export default router
