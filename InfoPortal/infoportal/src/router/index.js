import Vue from "vue"
import VueRouter from "vue-router";


const Home = () => import('@/views/listStu.vue')
const Login = () => import('@/views/accountCenter/loginStu.vue')
const Regist = () => import('@/views/accountCenter/registStu.vue')
// const ImgUpDown = () => import('@/components/imgUpDown.vue')
const addDoc = () => import('@/views/addDoc.vue')
const listDoc = () => import('@/views/listDoc.vue')
// const File = () => import('@/views/filetype/file.vue')

// 安装插件
Vue.use(VueRouter)

// 路由的注册
const routes = [
  //当 Home 页是个独立的页面，与导航栏中的页面都不相关时，
  //直接用 comonent 引入Home组件，记得要 import。
  // { 
  //   path: '/',
  //   name: 'Home',
  //   meta: { hidden: true },
  //   component: Home
  // },
  { //这里列举的是 Home 页也是“处理中心”的列表页，用 redirect 重定向到配置“处理中心”的 path 上去
    path: '/',
    name: 'Home',
    meta: { hidden: true },
    redirect: "/stulist"
  },
  
  //因为导航到的每个页面，都包含了导航栏、头部、底部、主体部分（统一到MyLayout组件中）
  //所以我每个菜单都会让根菜单component: MyLayout，再用 redirect 找到原本component的主体页面。（有多个子菜单的 redirect 可以省略）
  { 
    path: '/stulist',
    name: 'stu',
    component: Home,
    redirect: "/stulist/list",
    meta:{
      title: "学生信息",
      hidden: false,    //菜单不隐藏
      leaf: true //判断是否只有一个节点
    },
    children: [
      {
        path: "list",
        name: "stu_list",
        meta: { title: "学生信息列表" },
        component: Home
      }
    ]
  },

  // { 
  //   path: '/stuadd',
  //   name: 'stuadd',
  //   component: Regist,
  //   redirect: "/stuadd/add",
  //   meta:{
  //     title: "添加学生",
  //     hidden: false,    
  //     leaf: true
  //   },
  //   children: [
  //     {
  //       path: "add",
  //       name: "stu_add",
  //       meta: { title: "添加学生" },
  //       component: Regist
  //     }
  //   ]
  // },

  { 
    path: '/file',
    name: 'file',
    component: addDoc,
    redirect: "/file/upfile",
    meta:{
      title: "文件上传",
      hidden: false,    
      leaf: true
    },
    children: [
      {
        path: "upfile",
        name: "upfile",
        meta: { title: "文件上传" },
        component: addDoc
      }
    ]
  },

  { 
    path: '/fileall',
    name: 'fileall',
    component: listDoc,
    redirect: "/fileall/all",
    meta:{
      title: "查看所有文件",
      hidden: false,    
      leaf: true
    },
    children: [
      {
        path: "all",
        name: "file_all",
        meta: { title: "查看所有文件" },
        component: listDoc
      }
    ]
  },
  
  {
    path: '/filetype',
    name: 'filetype',
    component: () => import('@/views/filetype/filetype.vue'),
    redirect: "/filetype/txt",
    meta:{
      title: "文件类型",
      hidden: false,    
      leaf: false
    },
    children: [
      {
        path: ".txt",
        name: ".txt",
        meta: { title: ".txt" },
        component: () => import('@/views/filetype/filetxt.vue')
      },
      {
        path: ".jpg",
        name: ".jpg",
        meta: { title: ".jpg" },
        component: () => import('@/views/filetype/filejpg.vue')
      },
      {
        path: ".png",
        name: ".png",
        meta: { title: ".png" },
        component: () => import('@/views/filetype/filepng.vue')
      },
      {
        path: ".rar",
        name: ".rar",
        meta: { title: ".rar" },
        component: () => import('@/views/filetype/filerar.vue')
      }
    ]
  },
  
  {
    path: '/accountCenter',
    name: 'accountCenter',
    component: () => import('@/views/accountCenter/accountCenter.vue'),
    redirect: "/accountCenter/login",
    meta:{
      title: "账号中心",
      hidden: false,    
      leaf: false
    },
    children: [
      {
        path: "login",
        name: "login",
        meta: { title: "登录" },
        component: Login
      },
      {
        path: "regist",
        name: "regist",
        meta: { title: "注册" },
        component: Regist
      },
      {
        path: "logout",
        name: "logout",
        meta: { title: "退出登录" },
        component: Home
      }
    ]
  },
  // {
  //   path: '/refresh',
  //   component: () => import('@/components/fresh.vue')
  // }
]

const routerPush = VueRouter.prototype.push;
VueRouter.prototype.push = function (location) {
    return routerPush.call(this, location).catch(error => error)
}

const router = new VueRouter({
  routes,
  base: process.env.BASE_URL,
  mode: 'history'
})

// 暴露路由
export default router
