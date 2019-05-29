# Vue.js 处理用户输入

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

这个代码文件是用于对 用户输入 的处理；为了让用户和你的应用进行交互，使用 `v-on` 指令添加监听器进行处理。
由于Vue的强大性，我们只需要关注 **逻辑** 层面即可，不需要去关心和操作 DOM

```html
<div id="app-5">
    <p>{{ message  }}</p>
    <button v-on:click="reverseMessage">逆转消息</button><!-- v-on 进行时间绑定 -->
</div>
```

```javascript
var app5 = new Vue({
    el : "#app-5",
    data : {
        message : "hello Vue.js World !"
    },
    methods : {
        reverseMessage : function(){
            this.message = this.message.split("").reverse().join("")
        }
    }
})
```

这里的 `v-on` 标签为组件绑定监听器，当然，后边的 `click` 也是十分重要的，它指定了绑定的监听器的类型。
注意在 `reverseMessage` 方法中，我们更新了应用的状态，但没有触碰 DOM——所有的 DOM 操作都 **由 Vue 来处理** ，我们编写的代码只需要关注逻辑层面即可。

> 一次编写和没有编写没什么区别，代码还是孰能生巧