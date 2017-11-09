import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login/login'
import frame from '@/components/frame/frame'
import systemIndex from '@/components/system/index'
import organizationIndex from '@/components/organization/index'
import userIndex from '@/components/user/index'
import roleIndex from '@/components/role/index'
import permissionIndex from '@/components/permission/index'
import logIndex from '@/components/log/index'

Vue.use(Router)

export default new Router({

  routes: [
    {
      path: '/',
      redirect: {name:'登录页'}
    },
    {
      path: '/login',
      name: '登录页',
      component: login
    },
    {
      path: "/frame",
      name: "菜单框架",
      component: frame,
      children: [
        {
          path: 'manage/system/index',
          name: '系统管理',
          component: systemIndex
        },
        {
          path: 'manage/organization/index',
          name: '组织管理',
          component: organizationIndex
        },
        {
          path: 'manage/user/index',
          name: '用户管理',
          component: userIndex
        },
        {
          path: 'manage/role/index',
          name: '角色管理',
          component: roleIndex
        },
        {
          path: 'manage/permission/index',
          name: '权限管理',
          component: permissionIndex
        },
        {
          path: 'manage/log/index',
          name: '日志记录',
          component: logIndex
        }
      ]
    }
  ]
})

