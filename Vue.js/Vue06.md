# Vue.js

> 一次编写和没有编写没什么区别，代码还是孰能生巧


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

这个代码文件是用于对 用户输入 的处理；为了让用户和你的应用进行交互，使用 `v-on` 指令添加监听器进行处理

```html
<div id="app-7">
    <ol>
        <todo-list></todo-list>
    </ol>
</div>
------
<div id="app-7">
    <ol>
        <todo-list
            v-for="item in groceryList"
            v-bind:todo="item"
            v-bind:key="item.id">
        <todo-list>
    </ol>
```

```javascript
Vue.componet("todo-item",{
    props:  ["todo"],
    template:   "<li>{{ todo.text }}</li>"
})

var app7 = new Vue({
    el: "#app-7",
    data:   {
        groceryList:    [
            {id:0, text: "蔬菜"},
            {id:1, text: "奶酪"},
            {id:2, text: "花生"}
        ]
    }
})
```
