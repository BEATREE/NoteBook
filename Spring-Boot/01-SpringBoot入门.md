# Spring Boot 入门

## 1. Spring Boot 简介

> 简化 Spring 应用框架开发<br>
> 整个 Spring技术栈 的大整合<br>
> JavaEE的一站式解决方案

[官方辅助文档](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/)

## 2. 微服务

微服务：架构风格（服务微小化）
一个应用应该是一组小型服务；可以通过HTTP方式进行沟通；

单体应用 ALL IN ONE

每一个功能元素最终都一个可以独立替换或者独立升级的软件单元。

详细参照：官方文档，去百度搜

## 3. 运行环境 老师

jdk： 1.8 我的：12
maven： 3.3 我的 3.6

## 4. Spring Boot HelloWorld

一个功能：
浏览器发送hello请求，服务器接受请求并处理，返回 Hello World 字符串

### 1. 创建 Maven 项目 （jar）

### 2. 依赖导入 Spring Boot 相关依赖

最新SpringBoot导入依赖

```xml
 <!-- Inherit defaults from Spring Boot -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.0.BUILD-SNAPSHOT</version>
    </parent>

    <!-- Add typical dependencies for a web application -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <!-- Package as an executable jar -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <!-- Add Spring repositories -->
    <!-- (you don't need this if you are using a .RELEASE version) -->
    <repositories>
        <repository>
            <id>spring-snapshots</id>
            <url>https://repo.spring.io/snapshot</url>
            <snapshots><enabled>true</enabled></snapshots>
        </repository>
        <repository>
            <id>spring-milestones</id>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <id>spring-snapshots</id>
            <url>https://repo.spring.io/snapshot</url>
        </pluginRepository>
        <pluginRepository>
            <id>spring-milestones</id>
            <url>https://repo.spring.io/milestone</url>
        </pluginRepository>
    </pluginRepositories>
```

### 3. 编写主程序；启动SpringBoot

```java
/*
* @SpringBootApplication 用来标注一个主程序，说明这个类是SpringBoot应用
* */
@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args){
        // Spring 应用启动
        SpringApplication.run(HelloWorldApplication.class);
    }
}
```

### 4. 编写相关的Controller、Service

```java
@Controllerpublic
class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World !";
    }
}
```

### 5. 运行主程序测试

### 6. 简化部署

```xml
<!-- Package as an executable jar 打包成可执行的jar包 -->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>

```

然后执行 maven 的 package 指令，将项目打成可执行的 jar 包

`mvn package`

`java -jar filename.jar`

即使本机没有tomcat也可以启动，因为封装的 jar 内部已经封装了 Tomcat

## 5. HelloWorld 探究

### 1. POM 文件

#### 1. 父项目

```xml
<!-- Inherit defaults from Spring Boot -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.0.BUILD-SNAPSHOT</version>
</parent>

他的父项目是:
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.2.0.BUILD-SNAPSHOT</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>

它来管理SpringBoot中所有的依赖版本
```

Spring Boot的仲裁中心：
以后我们导入依赖默认是不需要写版本的：（没有在dependencies里边管理的依赖仍然要声明版本号）

#### 2. 导入的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### spring-boot-starter-**web**，不用指定版本，因为在dependencies中进行了管理

> So what's *spring-boot-starter*?

spring-boot-starter: spring-boot 场景启动器，帮我们导入web模块正常运行所依赖的组件；

[官方文档](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/#using-boot-starter)

Spring Boot 将所有的功能场景都抽取出来，做成了一个个的 **starter** 启动器，我们只要在项目中引入相关的starter，那么相关场景的依赖就都会导入进来。我们需要根据需求**按需导入**

### 2. 主程序，主入口类

```java
/*
* @SpringBootApplication 用来标注一个主程序，说明这个类是SpringBoot应用
* */
@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args){
        // Spring 应用启动
        SpringApplication.run(HelloWorldApplication.class);
    }
}
```

核心注解 `@SpringBootApplication` ，标记在某个类上说明该类是Spring-Boot的主配置类，Spring-Boot 就应该运行这个类 `main` 方法启动相关的 SpringBoot 应用。

`@SpringBootApplication` 点进去：

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
@ConfigurationPropertiesScan
public @interface SpringBootApplication {
    ...
}
```

**@SpringBootConfiguration**: Spring Boot的配置类

+ 标注在某个类上，表示这是 Spring Boot 的一个配置类

    - @Configuration：用于标记配置类

        - 配置类---配置文件；配置类也是容器中的一个组件； @Component

**@EnableAutoConfiguration**: 开启自动配置

+ 以前我们需要配置的，现在都不需要配置类；因为 *@EnableAutoConfiguration*告诉了Spring Boot开启自动配置，从而 Spring Boot 可以帮我们进行自动配置。

```java
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
    String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

    Class<?>[] exclude() default {};

    String[] excludeName() default {};
}
```

@AutoConfigurationPackage: 自动配置包

+ `@Import({Registrar.class})`：

    - Spring 的底层注解 `@Import` ，给容器导入一个组件；导入的组件由 `AutoConfigurationPackages.Registrar.class`
    - 将主配置类（@SpringBootApplication所标记的类）所在的包，所有的组件都扫描到Spring容器中；
    - 将所有需要导入的组件以全类名的方式返回；这些组件就会被添加到容器中

