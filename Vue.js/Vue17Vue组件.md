# Vue.js 

## 定义Vue组件

组件：为了拆分 Vue实例 的代码量而出现的，能够让我们在不同的组件中，来划分不同的代码块。<br>
将来我们需要什么功能，就调用对应的组件即可。<br>
新定义组件的标签，需要放在实例化的 Vue 实例内部。

组件化和模块化的不同：

+ 模块化：是从代码逻辑的角度划分的；方便代码分层开发，保证每个功能模块的职能单一。
+ 组件化：是从 UI 角度进行划分的；方便UI组件的重用。

1. 使用 `Vue.extend()` 和 `Vue.component()` 创建组件

如果使用 `Vue.component()` 定义组件的时候，组件名称使用了驼峰命名，需要将 大写的驼峰 改为小写的字母，同时两个单词之间使用 `-` 连接,如果不适用驼峰，直接使用即可。

Vue.component("name", extend) : 第一个参数是 组件的名称； 第二个参数是 `Vue.component` 定义的模板内容。

```js
// 1.1使用 Vue.extend 来创建全局组件，有返回值
var com1 = Vue.extend({
    template: "<h3>这是第一次使用Vue.extend创建的组件</h3>"   // 通过 template 属性，指定了组件要展示的 html 结构
})
// 1.2 使用 Vue.component("组件的名称", 创建出来的组件模板对象)
Vue.component("myComp1", com1)

Vue.component("mycomp1", Vue.extend({
    template: "<h3>这是第一次使用Vue.extend创建的组件</h3>"  
}))
```

2. 只使用 `Vue.component()` 创建组件

注意: 无论是那种方式创建的组件，组件的 template 属性指向的模板内容，**必须有且只有一个唯一的根元素**，即不能两个元素并列，例如`<div></div><span></span>`

```js
Vue.component("mycomp2", {
    // Attention: 无论是那种方式创建的组件，组件的 template 属性指向的模板内容，必须有且只有一个唯一的根元素
    // template: "<h3>这是直接使用Vue.component创建的组件</h3><span>123</span>"
    template: "<div><h3>这是直接使用Vue.component创建的组件</h3><span>123</span></div>"
})
```

3. 使用 `<template>` 标签创建组件

```html
<!-- 在被控制的 #app 外部，使用 template 元素定义组件的 HMTL 模板结构 -->
<template id="templ">
    <div>
        <h1>这是通过 template 元素在外部定义的组件结构，这个方式，有代码提示和高亮</h1>
    </div>
</template>
```

```js
Vue.component("mycomp3", {
    template: "#templ"  //指定 id 为 templ
})
```

4. 组件中的 `data` 和 `methods`

// 1. 组件可以有自己的 data 

// 2. 组件上的 data 于实例中的不同，实例中的data可以是一个对象，而组件中的 data 必须是一个方法

// 3. 组件中的 data 除了是一个方法外，方法内部还需要返回一个对象才行

// 4. 组件中的 data 数据和实例中的 data 使用方式完全一样！！！

```js
Vue.component("mycomp1", {
    template: "<h1>这是全局组件-{{msg}}</h1> ",  // 组件的 template (模板)属性
    data: function(){                           // 组件的 data 属性
        return {
            msg: "这是组件中自己的 data 属性的 msg "
        }
    }
})

var app = new Vue({
    el: "#app",
    data: {},
    components: {},
})
```

关于 `methods` 属性，可以看文件 `17.5` 也可以查看下方部分代码：

html:
```html
<div id="app">
    <counter></counter>
    <hr>
    <counter></counter>
    <hr>
    <counter></counter>
</div>

<template id="templ">
    <div>
        <input type="button" value="+1" @click="increment">
        <h3>{{ count }}</h3>
    </div>
</template>
``` 

js:
```js

var dataObj = {count: 0, }

// 这是个计数器的组件，身上有个按钮，每次点击按钮 data 中的 count+1
Vue.component("counter", {
    template: "#templ",
    data: function(){
        // return {dataObj}     加上大括号不行，因为它本身就是以恶搞对象，加上后会返回 dataObj, 而非它内部的值
        //return dataObj     // 外界的对象，会让通过模板创建的组件公用一个数据，所以data需要是一个函数，返回自己内部的数据
        return { count: 0 }
    },
    methods: {
        increment(){
            this.count++
        }
    }
})
```