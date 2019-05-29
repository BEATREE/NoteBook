# Vue.js 计算属性缓存

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

## 计算属性

VUE的模板中的表达式十分的便利，但是设计他们的初衷是用于简单运算的。在模板中放入太多的逻辑会让模板过重难以维护。
例如：

```html
<div id="example">
    {{ message.split("").reverse().join("") }} 
</div>
```

在这里模板的作用不再是简单的声明式逻辑。
这里是想要显示变量 `message` 的翻转字符串。当你想要在模板中多次引用此处的翻转字符串时，就会更加难以处理。
所以，对于任何复杂逻辑，我们都应当使用**计算属性**。

## 基础例子

```html
<div id="example">
    <p>Original message: "{{ message }}"</p>
    <p>Computed reversed message: "{{ reversedMessage }}"</p>
</div>
```

```js
var example = new Vue({
    el: "#example",
    data: {
        message: "Hello Vue.js"
    },
    computed:{
        // 计算属性的 getter
        reversedMessage: function () {
            //this 指向vm实例
            return this.message.split("").reverse().join("")
        }
    }
})
```

这里我们声明了一个计算属性 `reversedMessage`。我们提供的函数将用作属性 `vm.reversedMessage` 的 getter 函数：

```js
console.log(example.reversedMessage) // => 'olleH'
vm.message = 'Goodbye'
console.log(example.reversedMessage) // => 'eybdooG'
```

你可以打开浏览器的控制台，自行修改例子中的 example。`example.reversedMessage` 的值始终取决于 `example.message` 的值。
你可以像绑定普通属性一样在模板中绑定计算属性。Vue 知道 `vm.reversedMessage` 依赖于 `vm.message`，因此当 `vm.message` 发生改变时，所有依赖 `vm.reversedMessage` 的绑定也会更新。而且最妙的是我们已经以声明的方式创建了这种依赖关系：计算属性的 getter 函数是没有副作用 (side effect) 的，这使它更易于测试和理解。

## 计算属性缓存 VS 方法

同样我们可以通过在表达式中调用方法来达到同样的效果：
`<p>Reversed message: "{{ reversedMessage() }}"</p>`

```js
// 在组件中
methods: {
  reversedMessage: function () {
    return this.message.split('').reverse().join('')
  }
}
```

我们可以将同一个函数定义为一个方法而不是上边的一个计算属性。两种方式的最终结果确实是完全相同的。然而，不同的是**计算属性是基于他们的依赖进行缓存的**。只在相关依赖发生改变时它们才会重新求值。也就是说，只要**message**的值没有变化，多次访问计算属性`reversedMessage()`会返回原来缓存的计算结果，而不需要再执行函数。

这也同样意味着下面的计算属性将不再更新，因为 `Date.now()` 不是响应式依赖：

```js
computed: {
    now: function(){
        return Date.now()
    }
}
```

相比之下，每当触发重新渲染时，调用方法将**总会**再次执行函数。
我们为什么需要缓存？假设我们有一个性能开销比较大的计算属性 A，它需要遍历一个巨大的数组并做大量的计算。然后我们可能有其他的计算属性依赖于 A 。如果没有缓存，我们将不可避免的多次执行 A 的 getter！也就是说可以节省资源。
如果你不希望有缓存，请用方法来替代。