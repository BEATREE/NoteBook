# 路由

## 什么是路由

1. **后端路由：** 对于普通网站，所有的超链接都是url地址，所有url地址都对应服务器上的资源；

2. **前端路由：** 对于 ***单页面*** 程序来说，主要通过URL中的`hash`（#号）来实现不同页面之间的切换，同时，`hash`有一个特点； http请求中，不会包含 `hash` 相关的内容，所以，单页面程序中的页面跳转主要通过 hash 实现；相当于锚记链接

3. 在单页面中，这种通过hash改变切换页面的方式成为前端路由。（区别于后端路由）

## 在Vue中引用 vue-router

### Vue-router js引入安装：

    1. 前往下载：[下载](https://unpkg.com/vue-router/dist/vue-router.js)
    2. CDN：https://unpkg.com/vue-router@2.0.0/dist/vue-router.js

### NPM 安装

```sh
npm install vue-router
```

如果在一个模块化工程中使用它，必须要通过 `Vue.use()` 明确地安装路由功能：

```js
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)  // 将VueRouter注册给 Vue，属于手动安装
```

如果使用全局的 script 标签，则**无须如此 (手动安装)**。

### 基本使用

在引入 资源文件后，使用 `new VueRouter({})` 构造函数进行路由构造，而后将构造的路由器，注册到 vue实例 中去。

构造组件模板(注意一下代码中的构建，只创建对象，然后不注册为component)：

```js
// 创建组件的模板对象
var login = {
    template: "<h1>登录组件</h1>"
}
var register = {
    template: "<h1>注册组件</h1>"
}

// Vue.component("login",{  //不能使用这个，这个的login名称只能使用作为html标签使用
//     template: "<h1>登录组件</h1>"
// })
```

关于路由中，映射的 `path` `component` 的使用，看下面代码的注释内容。

```js
// 2.  创建一个路由对象，当导入 vue-router 后，在 window 全局对象中，就有了一个 路由的构造函数，叫做 VueRouter
// 在 new 路由对象的时候，可以为 构造函数，传递一个配置对象
var routerObj = new VueRouter({
    // route  这个配置对象中的 route 表示路由匹配规则的意思
    routes: [   //路由匹配规则
        // 每个路由规则都是一个对象，这个对象身上有两个必须的属性
        // 属性1： path， 表示监听哪个路由地址；
        // 属性2： component， 表示如果 路由是前面匹配到的 path， 则展示 component 属性对应的组件
        // 注意： component 属性值， 必须是一个组件模板对象， 不能是组件的引用名称
        // {path: "/", component: login},
        {path: "/", redirect: "/login"},  // 同 Node.js 中的不同，Node基于服务器端的转发，这里是重定位到对应的URL地址
        {path: "/login", component: login },
        {path: "/register", component: register }
    ],
    linkActiveClass: "myActive"     // 可修改默认激活类，进行样式设置
})

var app = new Vue({
    el: "#app",
    data: {},
    methods: {},
    components: {},
    router: routerObj   // 将路由规则对象，注册到 vm 实例上，用来监听 URL 地址的变化，然后展示对应网页
})
```

### 路由的嵌套

使用 `VueRouter` 中的 `routes` 属性中的 `children` 进行子组件的注册

```html
<div id="app">
        <router-link to="/account">Account</router-link>
        <router-view></router-view>
    </div>

    <template id="templ">
        <div>
            <h1> 这是一个 ACCOUNT 组件 </h1>
            <router-link to="/account/login">登录</router-link>
            <router-link to="/account/register">注册</router-link>

            <router-view></router-view>     <!--注意要放着这个 占位符 用于展示子组件-->
        </div>
    </template>
```

```js
var routerObj = new VueRouter({
    routes: [
        {
            path: "/account", 
            component: account,
            children: [ // 使用 children 实现子路由，同时子路由的 path 前不要带 /，
                { path: "login", component: login },  // login 前不加 “/” ，否则以根路径开始解析
                { path: "register", component: register }
            ]
        },
        // {path: "/account/login", component: login},
        // {path: "/account/register", component: register}
    ]
})
```

### 路由的参数传递：

1. `<router-link to="/login?id=10&name=张三">登录</router-link>` 通过 ? 进行传递，相关组件则通过`this.$route.query.Name`进行相关参数的获取

```js
var login = {
    template: "<h1>登录--{{ $route.query.id }}--{{ $route.query.name }}</h1>",
    data(){
        return {}
    },
    created(){  //组件的生命周期
        // console.log(this.$route)
        console.log(this.$route.query.id) // 获取url中的参数id
    }
}
```

2. 路由参数匹配的第二种，使用 `params` 配合`占位符:`，表示会放进来一个对应参数搁这，需要有个指定位置的参数

注意html中的 `to` 后直接是路径进行参数的传递
```html
<router-link to="/login/10/张三">登录</router-link>
```

```js
var login = {
    template: "<h1>登录--{{ $route.params.id }}--{{ $route.params.name }}</h1>",
    data(){
        return {}
    },
    created(){  //组件的生命周期
        // console.log(this.$route)
        // console.log(this.$route.query.id)
        // 获取url中的参数id
        console.log(this.$route.params.id)  // 路由参数匹配的第二种，使用 params 配合占位符
    }
}
var routerObj = new VueRouter({
    routes: [
        // :id 占位符，表示这里会放进来一个id搁这，需要有个指定位置的参数
        {path: "/login/:id/:name", component: login },
        {path: "/register", component: register },
    ]
})
```