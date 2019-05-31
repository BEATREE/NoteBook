# Spring Boot 配置文件占位符

## RandomValuePropertySource：配置文件中可以使用随机数

```java
${random.value}` ， `${random.int}` ， `${random.long}` ， `${random.int(10)}` ， `${random.int(10, 1000)}
```

## 属性配置占位符

占位符获取之前使用的值，如果没有则使用 `:` 指定默认值

举个栗子：

```properties
person.name=做棵大树${random.int}
person.age=${random.int(1,20)}
person.boss=false
person.birth=2018/08/08
person.maps.k1=v1
person.maps.k2=v2
person.lists=a,b,c
person.handsome-boy.name=${person.name}${person.hello}BEATREE
person.handsome-boy.age=15
```

然后我们执行单元测试，输出一下person，我们会得到以下的内容：

```cmd
Person{name='做棵大树58347165', age=14, boss=false, birth=Wed Aug 08 00:00:00 CST 2018, maps={k1=v1, k2=v2}, lists=[a, b, c], handsomeBoy=Boy{name='做棵大树-1699437886${person.hello}BEATREE', age=15}}
```

注意：这里的`${person.hello}`被原样输出了，因为我们对他并没有进行定义；我们也可以写为 `${person.hello:hello}` 这个表达式就是表示:**如果没有 persion.hello 的值，则取为 hello**
