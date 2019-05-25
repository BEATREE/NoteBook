# 配置文件

## 1. 配置文件

Spring Boot 使用一个全局的配置文件，配置文件的名称是固定的

- application.properties
- application.yml

配置文件的作用：是用于修改Spring Boot自动配置的默认值；

Spring Boot 在底层已经对我们进行了默认的整体配置，如果我们需要修改一些个别配置，我们可以通过配置文件进行修改

### 配置语言

以前的配置语言大多是都是 ***.xml 文件；
YAML： **以数据为中心**， 比 json、xml 更适合做配置语言。

### YAML

YAML是"YAML Ain't a Markup Language"（YAML不是一种置标语言）的递归缩写。

在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种置标语言）。

（(⊙o⊙)额，有点绕）

[简书一个作者的文章(**YAML快速入门**)](https://www.jianshu.com/p/97222440cd08)

**默认创建的项目中没有这个`application.yml`文件**，我们可以在 resources 文件夹下进行手动创建配置

举例对比两种配置文件修改端口的方式：

application.properties:

```properties
server.port=8088
```

application.yml:

```yaml
server:
  port: 8088
```