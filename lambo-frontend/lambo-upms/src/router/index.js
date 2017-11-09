import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login/login'

Vue.use(Router)

export default new Router({

  routes: [
    {
      path:"/frame",
      component: User,
      children:[
        {
          path: '/manage/system/index',
          name: '系统管理',
          component: login
        },
        {
          path: '/manage/organization/index',
          name: '组织管理',
          component: login
        },
        {
          path: '/manage/user/index',
          name: '用户管理',
          component: login
        },
        {
          path: '/manage/role/index',
          name: '角色管理',
          component: login
        },
        {
          path: '/manage/permission/index',
          name: '权限管理',
          component: login
        },
        {
          path: '/manage/log/index',
          name: '日志记录',
          component: login
        }
      ]
    }
  ]
})

