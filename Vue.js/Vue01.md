# Hello Vue.js World

这是我第一次尝试使用 **Vue.js** 来编写 html 文件
## 引入 Vue.js
1. 第一个 Vue.js CDN 路径
```html
    <!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
```
2. 第二个 Vue.js CDN 路径
```html
    <!-- 生产环境版本，优化了尺寸和速度 -->
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
```
如果是考虑到速度的话，可以选择第二个； 我是用的第一个CDN文件

**Vue.js** 官网给出一下注意，所以我们直接使用 CDN 资源即可
> 请注意我们不推荐新手直接使用 vue-cli，尤其是在你还不熟悉基于 Node.js 的构建工具时。

## 开始编写
```html
<div id="app">
  {{ message }}
</div
```
```javascript
var app = new Vue({
  el: '#app',       // el 相当于选择器，用于确定绑定的组件，这里是 #app ；因为在html中定义的 div 的id为 “app”
  data: {           // data 定义Vue中的数据，进行绑定，在这里我们定义了 message 
    message: 'Hello Vue!'
  }
})
```
这里需要注意的是，在html文件中，调用的VUE的变量是使用的 **{{ }}** 两个大括号；另外在 JS 声明 Vue类型 的对象并且进行实例化。