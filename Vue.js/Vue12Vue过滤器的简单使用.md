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

# 过滤器

Vue的过滤器:

`Vue.filter('过滤器的名称',function(){})`

过滤器中的 function **第一个参数已经被规定死了**，永远都是管道符前边传来的数据。

概念：Vue.js中允许自定义过滤器，可用做**一些常用文本的格式化**。<br>
用处：过滤器可用于两个地方 **mustache插值** 和 **v-bind** 表达式。<br>
位置：过滤器应被添加在 JavaScript 表达式的尾部，由 *管道符* 指示



## 私有过滤器

1. Html

`<td>{{item.ctime | dateFormat('yyyy-mm-dd')}}</td>`

私有过滤器定义在 vue 实例内部，和methods类似，然后全局的过滤器放在vue实例外 <br>

```js
Vue.filter('messageChange', function(msg, arg) {    // 传入参数bs
    return msg.replace(/爱/g, arg)     //前边是个正则 /g 表示全局匹配
})
Vue.filter("test", function(msg){
    return msg + "==="
})            
//定义到实例后会无法识别，报错

var app = new Vue({
    el: "#app",
    data: {
        msg: "我爱你啊，爱你的一批，爱的难舍难分",
        
    },
    methods: { },
    filters: {  //定义私有过滤器 在vue实例内
        //两个条件 过滤器名称 和处理函数
        messageChange:function(msg){   //名字：函数
            return "这是个私有的过滤器"
        },//在调用过程中，如果全局的和私有的一致，则以私有的为先
        

    }
})
```
