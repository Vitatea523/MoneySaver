import { createRouter,createWebHistory} from "vue-router";
const routerHistorys = createWebHistory()

  const router = createRouter({
    history: routerHistorys,
    routes: [
      {
        path: '/',
        redirect: "/login"
      },
      {
        path: '/login',
        component: ()=>import('@/views/login')
      },
      {
        path: '/register',
        component: ()=>import('@/views/register')
      },
      {
        path: '/personInfo',
        component: ()=>import('@/views/personInfo')
      },
      {
        path: '/home',
        component: ()=>import('@/views/home')
      }
    ]
  })
  router.beforeEach((to, from, next) => {
    if (to.path === '/login' || to.path === '/register') {
      next();
    } else {
      let token = sessionStorage.getItem('token');
      if (token === null || token === '') {
        next('/login');
      } else {
        next();
      }
    }
  });
export default router
