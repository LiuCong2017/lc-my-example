import {createRouter, createWebHashHistory} from 'vue-router'
import {getToken} from "@/utils/auth";

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('@/views/dashboard'),
    meta:{requireAuth: true}
  },

  {
    path: '/test',
    component: () => import('@/views/test/Test.vue')
  },

  {
    path: '/login',
    name: 'Login',
    component: ()=> import('@/views/login/index.vue'),
    hidden: true
  },
  {
    path: '/register',
    component: ()=> import('@/views/user-manage/register/index.vue'),
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to,from,next)=>{

  if (to.path === '/login'){
    localStorage.setItem('preRoute',router.currentRoute.value.fullPath);
  }

  if (to.matched.some((record)=>record.meta.requireAuth)){
    if (getToken()){
      next()
      //可进一步加角色判断
    }else {
      next(`/login?redirect=${to.path}`)
    }
  }else {
    next();
  }

})

export function resetRouter() {
  const newRouter = router;
  router.matcher = newRouter.matcher // reset router
}

export default router
