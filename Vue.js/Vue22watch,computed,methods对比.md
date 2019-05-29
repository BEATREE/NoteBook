# watch、computed、methods对比

对于相同的名称案例需求，三种实现方式如下；

`methods`属性代码实例(Vue21.1)：

```js
methods: {
    getFullname(){
        this.fullname = this.firstname + "-" + this.lastname
    }
}
```

`watch`属性代码实例(Vue21.2)：

```js
 watch: {    // 使用这个属性，可以监测data中指定数据的变化，然后触发watch中对用的function处理函数
    "firstname": function (newValue, oldValue) {    // newValue是新的数据，oldValue是旧的数据
        console.log('监视到了firstname的变化！')
        console.log(newValue + "-" + oldValue)
        this.fullname = this.firstname + "-" + this.lastname

    },
    lastname(newValue, oldValue) {  // 监听lastname的改变， 两种写法均可
        console.log('LastName watch 监视到了firstname的变化！')
        console.log(newValue + "-" + oldValue)
        this.fullname = this.firstname + "-" + this.lastname
    },
},
```

`computed`属性代码实例(Vue21.4)：

```js
computed: {     // 在 computed 中可以定义一些属性，叫做 计算属性
    // 计算属性本质是一个方法，只不过我们在使用的时候将 他们的名称，当作属性进行使用。并不会把计算属性当作方法进行调用
    
    // 注意： 1.计算属性，在调用的时候，一定不要加 “()” 去调用，直接把他当成普通属性就好
    // 注意： 2.只要计算属性function内部的任何数据发生了变化，就会立即重新计算属性的值
    // 注意： 3. 计算属性的返回结果会被 缓存 ，方便下次直接使用。 如果计算属性方法中的任何数据都没有发生过变化，则，不会重新对计算属性求值

    // 'fullname': function(){  //两种形式写法均可
    fullname() {
        console.log('调用了 computed 计算属性')
        return this.firstname + "-" + this.middlename + "-" + this.lastname 
    },
},
```

1. `computed` 属性结果会被缓存，除非依赖的响应式属性变化才会进行重新计算，主要当作**属性**使用。、
2. `methods` 表示一个具体的操作，主要书写大量的业务逻辑
3. `watch` 一个对象，键是需要关注的表达式，值是对应的回调函数。主要用来监听某些特定数据的变化，从而进行某些业务逻辑的操作，可以看错是 `methods` 和 `computed` 的结合体

`watch` 可以更多监听一些虚拟的数据，例如 **路由**； `methods` 则进行一些复杂方法的处理操作； `computed` 则可以对数据进行相关的处理，并返回一些新的数据，但不适合复杂业务逻辑计算。

## watch 和 computed

相同： `watch` 和 `computed` 都是通过 `function()` 进行方法调用 <br>
不同： `computed` 内部，无论如何都要 `return` 一个值；`watch` 则不用，直接修改即可

## methods 和 computed

相同： `methods` 和 `computed` 都可以实现一些功能作用，一定方面上可以等价 <br>
不同： `methods` 中更注重业务逻辑的处理，故，在`methods`中可以写大量业务逻辑处理；`computed` 则只能进行一些简单的计算处理，作为普通属性使用。