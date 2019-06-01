# 配置文件加载位置

Spring Boot 启动时候会扫描以下位置的 `application.properties` 或者 `application.yml` 文件作为 Spring Boot 的默认配置文件：

+ file:./config/
+ file:./
+ classpath:/config/
+ classpath:/

以上四个位置优先级 **<span style="color:red;">从高到低</span>** ，所有位置的配置文件都会被加载，但是 **高优先级文件会覆盖掉低优先级文件**。

我们也可以通过配置 `spring.config.location` 来改变默认配置。

配置之间可以互补，低优先级配置自己的内容，高优先级则改变部分内容；

我们还可以通过 `spring.config.location` 改变默认的配置文件位置。

**项目打包好之后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置； 指定的配置文件和默认加载的配置文件共同作用形成互补配置**。


