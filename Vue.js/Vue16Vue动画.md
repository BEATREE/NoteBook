# Vue.js教程

## Vue 动画

![动画周期图示](https://cn.vuejs.org/images/transition.png)

### 使用过渡类名实现动画

Vue中提供了 `<transition></transition>` 标签来标记动画，同时在 css 中需要定义相关的 css 内容。

```css
 <style >
        /* v-enter 是一个时间点，此时还没有进入，发生在进入之前 */
        /* v-leave-to 是在动画离开之后，动画的终止状态，此时元素已经离开动画范围 */
        .v-enter, .v-leave-to{
            opacity: 0;     /*不透明度，0全透明 1不透明*/
            transform: translateX(80px);
        }
        /* v-enter-active 入场动画的时间段， 是一个 时间段 */
        /* .v-leave-active 入场动画的时间段， 是一个 时间段 */
        .v-enter-active, .v-leave-active{
            transition: all 0.6s ease;
        }

        /* 自定义的name属性对应的css */
        .vflag-enter, .vflag-leave-to{
            opacity: 0;     /*不透明度，0全透明 1不透明*/
            transform: translateY(80px);
        }
        .vflag-enter-active, .vflag-leave-active{
            transition: all 0.6s ease;
        }
    </style>
```

其中，默认的是 `v-`开头的，表示所有 **transition 通用**的 css 样式。第二个 `.vflag-enter` 对应的是定义了 **name** 属性的transition标签。

```html
<transition name="vflag"></transition>
```

### 使用第三方类库实现动画

引入 animate.css 库文件
`<link href="https://cdn.bootcss.com/animate.css/3.7.0/animate.css" rel="stylesheet">`

类名直接指用**zoomIn和zoomOut**，**不会**出现动画效果。需要加上基础类 `animated`，然后才会有相关效果。

```html
    <!-- 使用 :duration="{ enter:200, leave: 300 }" 来分别设置入场时长和离场的时长 -->
    <transition enter-active-class="zoomIn" leave-active-class="zoomOut" :duration="{ enter:200, leave: 600 }"><!-- 花费500ms -->
        <!-- “：”属性绑定 -->
        <h3 v-if="flag" class="animated">{{ msg }}</h3>
    </transition>
```

### 钩子函数半场动画

```html
<transition
    mode="out-in"
    @before-enter="beforeEnter"
    @enter="enter"
    @before-leave="beforeLeave"
    @leave="leave"
    :css="false">
        
</transition>

```

其中在定义@enter对应的 `enter` 函数的时候，以及 @leave 对应的 `leave` 函数的时候，都必须定义两个参数，并且调用 `done()`，举例如下：

```js
 enter(el, done) {
    // el.offsetWidth 这句话会出现动画效果，但是不写没有动画效果
    // el.offsetWidth 会强制动画刷新
    // 和 offset 相关的就可以 el.offsetWidth el.offsetHeight el.offsetLeft。。。
    el.offsetWidth
    // 表示动画开始进入后的样式，设置小球完成动画后的结束状态
    el.style.transform = "translate(150px, 250px)"
    el.style.transition = "all .5s ease"

    //这里的 done() 其实激素hi afterEnter 函数，也就是 done() 是 afterEnter 的引用
    done()
},
```

### 列表动画

在实现列表过渡的时候，如果需要过渡的元素是通过 `v-for` 渲染出来的不能使用 `transition` 包裹，需要使用`transition-group`。
如果要为 `v-for` 循环创建的元素设置动画，**必须**为每个元素设置 **`:key`** 属性

```html
<!-- appear 属性实现刚开始入场时候的效果 -->
<transition-group appear>
    <li v-for="item in list" :key="item.id">{{ item.name }}</li>
</transition-group>
```

在 `transition-group` 中，**默认会将它渲染成 `<span></span>` 元素**，但是很多时候不符合规格，比如说在ul中循环li。 这个时候可以使用 `<transition-group appear tag="type">` 中的 `tag` 属性，指定为 `type`， 则会被渲染成 `ul`。