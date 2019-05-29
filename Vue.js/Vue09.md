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

## 指令

指令（Directives）是带有 `v-` 前缀的特殊特性。指令特性的值预期是单个 JavaScript 表达式（`v-for`是个例外，后边会进行讨论）。指令的职责是，当表达式的值进行变化的时候，将其产生的连带影响，响应式的作用于DOM。
> 举个栗子：
`<p v-if="seen">现在你看到我了</p>`
**在这里会根据`seen`的值对其内的内容进行插入和移除**

### 参数

一些指令能够接受一个“参数”，在 指令名称后，以 **冒号** 表示。例如`v-bind`指令可以用于响应式地更新 HTMl 特性
> 举个栗子
`<a v-bind:href="url">...</a>`
在这里 href 是参数，告知 v-bind 指令将**该元素的 href 特性与表达式 url 的值绑定**。
> 再举个栗子
`<a v-on:click="doSomething">...</a>`
这里的参数是监听的事件名，会后续讨论。

### 动态参数

VUE.js 从 2.6.0 开始，可以用方括号括起来的 **JavaScript 表达式**作为一个指令的参数：
`<a v-bind:[attributeName]="url">...</a>`
这里的`attributeName`是被方括号括起来的，所以会作为一个JavaScript表达式进行动态求值，求得的值将会被作为最终的参数使用。例如：在Vue实例的 `data` 属性中，有个 `attributeName`属性，其属性值为 `href`，则，会被绑定为 `v-bind:href="url"`。
同样地，你可以使用动态参数为一个动态的事件名绑定处理函数：

`<a v-on:[eventName]="doSomething"> ... </a>`

同样地，当 `eventName` 的值为 "`focus`" 时，`v-on:[eventName]` 将等价于 `v-on:focus`。

#### 对动态参数的值的约束

动态参数预期会求出一个字符串，异常情况下值为 null。这个特殊的 null 值可以被显性地用于移除绑定。任何其它非字符串类型的值都将会触发一个警告。

#### 对动态参数表达式的约束
> 动态参数表达式有一些语法约束，因为某些字符，
> 例如空格和引号，放在 HTML 特性名里是无效的

### 修饰符

修饰符 (modifier) 是以半角句号 **.** 指明的特殊后缀，用于指出一个指令应该以特殊方式绑定。例如，.prevent 修饰符告诉 v-on 指令对于触发的事件调用 **event.preventDefault()**：
`<form v-on:submit.prevent="onSubmit">...</form>`
在接下来对 `v-on` 和 `v-for` 等功能的探索中，你会看到修饰符的其它例子。

## 缩写

对于常用的一些指令， VUE 提供了缩写，例如:

### v-bind缩写

```html
<!-- 完整语法 -->
<a v-bind:href="url">...</a>

<!-- 缩写 -->
<a :href="url">...</a>
```

### v-on缩写

```html
<!-- 完整语法 -->
<!-- 完整语法 -->
<a v-on:click="doSomething">...</a>

<!-- 缩写 -->
<a @click="doSomething">...</a>
```

它们看起来可能与普通的 HTML 略有不同，但 `:`与 `@`对于特性名来说都是合法字符，在所有支持 Vue 的浏览器都能被正确地解析。而且，它们不会出现在最终渲染的标记中。缩写语法是完全可选的，但随着你更深入地了解它们的作用，你会庆幸拥有它们。