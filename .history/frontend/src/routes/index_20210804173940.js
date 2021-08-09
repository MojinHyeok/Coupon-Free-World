import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store/index'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: () => import('@/views/LoginPage.vue'),
      beforeEnter(to, form, next) {
        console.log('hi')
        console.log(store.getters.userID)
        console.log('bye')
        if (store.getters.userID) {
          next({ name: 'Main' })
        } else {
          next()
        }
      },
    },
    {
      path: '/account/signup',
      component: () => import('@/views/SignupPage.vue'),
    },
    {
      path: '/account/login',
      component: () => import('@/views/LoginPage.vue'),
      beforeEnter(to, form, next) {
        if (store.getters.userID) {
          next({ name: 'Main' })
        } else {
          next()
        }
      },
    },
    {
      path: '/account/edit',
      component: () => import('@/views/UserEdit.vue'),
    },
    {
      path: '/board',
      component: () => import('@/views/board/BoardPage.vue'),
    },
    {
      path: '/board/create',
      component: () => import('@/components/board/CreateForm.vue'),
    },
    {
      name: 'boardDetail',
      path: '/board/:id',
      props: true,
      component: () => import('@/views/board/DetailPage.vue'),
    },
    {
      path: '/board/edit/:id',
      component: () => import('@/components/board/editForm.vue'),
    },
    {
      path: '/main',
      name: 'Main',
      component: () => import('@/views/MainPage.vue'),
    },
    {
      path: '/user/profile/:userID',
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
    {
      path: '/user/confirmEmail',
      component: () => import('@/components/user/confirmEmail.vue'),
    },
    {
      path: '/feed',
      component: () => import('@/views/feed/FeedPage.vue'),
    },
    {
      path: '/feed/create',
      component: () => import('@/views/feed/CreatePage.vue'),
    },
    {
      path: '/feed/:id/update',
      component: () => import('@/views/feed/UpdatePage.vue'),
    },
    {
      path: '/feed/:id',
      component: () => import('@/views/feed/DetailPage.vue'),
    },
  ],
})

export default router