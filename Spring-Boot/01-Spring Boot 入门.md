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