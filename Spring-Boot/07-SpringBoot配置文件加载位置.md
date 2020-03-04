# 配置文件加载位置与外部文件的加载

## 配置文件的加载位置

Spring Boot 启动时候会扫描以下位置的 `application.properties` 或者 `application.yml` 文件作为 Spring Boot 的默认配置文件：

+ file:./config/
+ file:./
+ classpath:/config/
+ classpath:/

以上四个位置优先级 **<span style="color:red;">从高到低</span>** ，所有位置的配置文件都会被加载，但是 **高优先级文件会覆盖掉低优先级文件**。

我们也可以通过配置 `spring.config.location` 来改变默认配置，在配置文件中无法使用，是在打包好之后通过命令行形式来指定配置文件的位置，指定加载的会和默认拥有的一起起作用。

配置之间可以互补，低优先级配置自己的内容，高优先级则改变部分内容；

我们还可以通过 `spring.config.location` 改变默认的配置文件位置。

**项目打包好之后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置； 指定的配置文件和默认加载的配置文件共同作用形成互补配置**。

## 外部配置加载顺序

SpringBoot也可以从以下位置加载配置；**优先级从高到低**；同样是高优先级的配置覆盖低优先级的配置

1. 命令行参数 - `java -jar a.jar --server.port=8088`
2. 来自Java:comp/env的JNDI属性
3. Java系统属性（System.getProperties())
4. 操作系统环境变量
5. RandomValuePropertySource配置的 `random.*` 属性值

**优先加载带 `profile` 的**

6. jar 包**外**部的 `application-{profile}.properties` 或 `application.yml（带spring.profile)` 配置文件
7. jar 包**内**部的 `application-{profile}.properties` 或 `application.yml（带spring.profile)` 配置文件

**再来加载不带 `profile` 的**

8. jar 包**外**部的`application.properties` 或 `application.yml （不带spring.profile)` 配置文件
9. jar 包**内**部的`application.properties` 或 `application.yml （不带spring.profile)` 配置文件

10. `@Configuration` 注解类上的 `@PropertySource`
11. 通过 `SpringApplication.setDefaultProperties` 指定的默认属性
