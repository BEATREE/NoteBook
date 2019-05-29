# Vue.js 绑定元素特性

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
<div id="app-2">
    <span v-bind:title="message">    <!--将title属性进行绑定-->
        鼠标悬停几秒钟查看此处动态绑定的提示信息！
    </span> 
</div>
```

```javascript
var app2 = new Vue({
  el: '#app-2',
  data: {
    message: '页面加载于 ' + new Date().toLocaleString()
  }
})
```
在这个页面我们遇到了一点新东西。你看到的 `v-bind` 特性被称为指令。指令带有前缀 `v-`，以表示它们是 Vue 提供的特殊特性。可能你已经猜到了，它们会在渲染的 DOM 上应用特殊的响应式行为。在这里，该指令的意思是：**“将这个元素节点的 title 特性和 Vue 实例的 message 属性保持一致”**。