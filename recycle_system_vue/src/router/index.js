import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login'
import Index from '../views/index'
import UserLayout from '../views/User/userlayout'
import AllInorder from '../views/User/allInorder'
import DoingInorder from '../views/User/doingInorder'
import Recycle from '../views/User/recycle'
import AlterInfo from '../views/User/alterinfo'
import Test from  '../views/User/test'
import HomePage from '../views/User/homepage'
import maptest from '../views/User/maptest'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:"主页",
      component: Index,
      redirect:"/login"
    },
    {
      path:'/login',
      name:"登录页面",
      component: Login
    },
    {
      path: '/userlayout',
      component: UserLayout,
      redirect:"/homepage",
      children: [
        {
          path: '/homepage',
          component: HomePage
        },

        {
          path: '/allinorder',
          component: AllInorder
        },
        {
          path: '/doinginorder',
          component: DoingInorder
        },
        {
          path: '/recycle',
          component: Recycle
        },
        {
          path: '/alterinfo',
          component: AlterInfo
        },
        {
          path:'/test',
          component: Test
        },{
          path:'/maptest',
          component:maptest
        }
      ]
    }

  ]
})
