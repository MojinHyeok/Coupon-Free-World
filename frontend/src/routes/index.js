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
      path: '/board/list',
      component: () => import('@/components/board/List.vue'),
    },
    {
      path: '/board/create',
      component: () => import('@/components/board/Create.vue'),
    },
    {
      path: '/board/detail',
      name: 'Detail',
      component: () => import('@/components/board/Detail.vue'),
    },
    {
      path: '/main',
      component: () => import('@/views/MainPage.vue'),
    },
    {
      path: '/user/profile',
      component: () => import('@/views/Profile.vue'),
    },
    {
      path: '/user/followList',
      component: () => import('@/components/user/followList.vue'),
    },
    {
      path: '/user/requestFollowList',
      component: () => import('@/components/user/requestFollowList.vue'),
    },
  ],
})

export default router
