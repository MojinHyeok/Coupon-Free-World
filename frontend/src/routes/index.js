import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
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
    },
  ],
})

export default router
