import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login'
import Index from '../views/index'
import UserLayout from '../views/User/userlayout'
import UserAllInorder from '../views/User/userallInorder'
import UserDoingInorder from '../views/User/userdoingInorder'
import Recycle from '../views/User/recycle'
import UserAlterInfo from '../views/User/useralterinfo'
import userupdateorder from '../views/User/userupdateorder'
import Test from  '../views/User/test'
import UserHomePage from '../views/User/userhomepage'
import maptest from '../views/User/maptest'
import CollectorLayout from '../views/Collector/collectorlayout'
import CollectorHomePage from '../views/Collector/collectorhomepage'
import CollectorAllInorder from '../views/Collector/collectorallInorder'
import CollectorDoingInorder from '../views/Collector/collectordoingInorder'
import CollectorAlterInfo from '../views/Collector/collectoralterinfo'
import confirmorder from '../views/Collector/confirmorder'
import NotFound from '../views/404'
import AdminLayout from '../views/Admin/adminlayout'
import ItemTypeManager from '../views/Admin/ItemTypeManager'
import ItemManager from '../views/Admin/ItemManager'
import adminallInorder from '../views/Admin/adminallInorder'
import admindoingInorder from '../views/Admin/admindoingInorder'
import userregister from '../views/userregister'
import collectorregister from '../views/collectorregister'
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
      component: Login,
    },
    {
      path:'/userregister',
      component:userregister,
    },
    {
      path:'/collectorregister',
      component:collectorregister,
    },
    {
      path:'/404error',
      component:NotFound,
    },
    {
      path:'/adminlayout',
      component:AdminLayout,
      meta: {
        needLogin: true, //需要登录
        needAdmin:true,
      },
      children:[
        {
          path:'/ItemTypeManage',
          component:ItemTypeManager,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/ItemManage',
          component:ItemManager,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/adminallinorder',
          component:adminallInorder,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        },
        {
          path:'/admindoinginorder',
          component:admindoingInorder,
          meta: {
            needLogin: true, //需要登录
            needAdmin:true,
          },
        }
      ]
    },
    {
      path:'/collectorlayout',
      component:CollectorLayout,
      redirect:"collectorhomepage",
      meta: {
        needLogin: true, //需要登录
        needCollector:true,
      },
      children:[
        {
          path: '/collectorHomePage',
          component: CollectorHomePage,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectorallinorder',
          component: CollectorAllInorder,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/confirmorder',
          component: confirmorder,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectordoinginorder',
          component: CollectorDoingInorder,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
        {
          path: '/collectoralterinfo',
          component: CollectorAlterInfo,
          meta: {
            needLogin: true, //需要登录
            needCollector:true,
          },
        },
      ]
    },
    {
      path: '/userlayout',
      component: UserLayout,
      redirect:"/userhomepage",
      meta: {
        needLogin: true, //需要登录
        needUser:true,
      },
      children: [
        {
          path: '/userhomepage',
          component: UserHomePage,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },

        {
          path: '/userallinorder',
          component: UserAllInorder,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/userdoinginorder',
          component: UserDoingInorder,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/recycle',
          component: Recycle,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/userupdateorder',
          component: userupdateorder,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path: '/useralterinfo',
          component: UserAlterInfo,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },
        {
          path:'/test',
          component: Test,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        },{
          path:'/maptest',
          component:maptest,
          meta: {
            needLogin: true, //需要登录
            needUser:true,
          },
        }
      ]
    }

  ]
})
