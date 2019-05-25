# YAML 语法

## 1. 基本语法

`k: v`：表示一对键值对（注意，在value前有一个空格，这个空格是**必需**的）

若配置项之间有层级关系，则仍是**通过空格**来控制层级关系，左对齐的是同一层级的属性。 属性和值也是**大小写敏感的**

例子：

```yaml
server:
  port: 8088
  path: /hello
```

## 2. YAML支持的三种数据结构

### 字面量： 普通的值（数字，字符串，布尔 ...)

`k: v`: 字面量直接来写；

+ 字符串默认不需加上单引号或双引号
+ 双引号 ""：不会转义字符串中的特殊字符，即特殊字符会作为本身的意思；`name: hello \n world` 输出：`hello 换行 world`； 的形式
+ 单引号 ''：特殊字符会转为普通的字符串；`name: hello \n world` 输出：`hello \n world`； 的形式

### 对象、map（属性和值）：键值对类型

k: v: 再下一行来写属性和值的关系，同样，注意缩进。

```yaml
friends:
    name: BEATREE
    age: 20
    sex: handsome boy
```

行内写法：

```yaml
friends: { name: BEATREE, age: 20, sex: handsome boy }
```

### 数组(List, Set)

1. 用 `- ` (短横线+空格)表示数组中的一个元素

例子：

```yaml
girlfriends:
 - Lily
 - Keven
 - Coingzn
```

行内写法：

```yaml
girlfriends: [Lily,Keven,Coingzn]
```