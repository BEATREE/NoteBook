# Vue.js 条件与循环

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
<h2>Vue.js 条件语句</h2>
    <div id="app-3">
            <p v-if="seen">现在你看到我了</p>   
    </div>

<h2>Vue.js 循环语句</h2>
    <div id="app-4">
        <ol>
            <li v-for="todo in todos">
                {{ todo.text }}
            </li>
        </ol>
    </div>  
```

```javascript
//选择结构
var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true
    }
})
//循环结构
var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            { text: '学习 JavaScript' },
            { text: '学习 Vue' },
            { text: '整个牛项目' }
        ]
    }
})
```
这里的 `v-for` 标签其中的语法有些类似于在 Python 中的 `for` 循环，这里的 `todo` 相当于在 `todos` 列表中的一个对象，然后通过 **对象名.属性** 的方式，`todo.text` 进行调用其中的信息。

> 一次编写和没有编写没什么区别，代码还是孰能生巧