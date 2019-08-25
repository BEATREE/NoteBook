# Spring的基于AspectJ的AOP开发

## @AspectJ的简介

AspectJ是一个**面向切面的框架**，它扩展了Java语言。AspectJ定义了AOP语法所以它有一个专门的编译器用来生成遵守Java字节编码规范的Class文件。AspectJ是一个基于Java语言的AOP框架。*Spring2.0以后新增了对AspectJ切点表达式的支持*。@AspectJ是JDK5新增的功能，通过JDK5注解技术，允许直接在Bean类中定义切面。

**新版本Spring框架，建议使用AspectJ方式来开发AOP，使用AspectJ需要导入Spring AOP和AspectJ相关的Jar包。**

从上面的阐述中，我们应认识到AspectJ并不是Spring框架的一部分，而是一个单独的面向切面的框架，只不过它经常和Spring框架一起使用进行AOP的操作而已。

使用AspectJ方式来开发AOP共有两种方式：

1. 基于AspectJ的 **xml配置文件** 的方式
2. 基于AspectJ的 **注解** 的方式

---

## 使用表达式配置切入点

1. 切入点： 实际增强的方法

2. 常用表达式：`execution(<访问修饰符>?<返回类型><方法名>(<参数>)<异常>)`

    具体例子：(注意在访问修饰符后有一个空格)

    1. 匹配所有类的public方法：`execution(public *.*(..))`
    2. 匹配所有类里面的所有的方法：`execution(* *.*(..))`
    3. `execution(* cn.itcast.dao..*(..))`，`..*`表示包、子孙包下所有类。
    4. 匹配指定类所有方法：`execution(* cn.itcast.service.UserService.*(..))`
    5. 匹配实现特定接口的所有类的方法：`execution(* cn.itcast.dao.GenericDAO+.*(..))`
    6. 匹配所有 save 开头的方法：`execution(* save*(..))`
    7. 匹配指定包下所有类的方法：`execution(* cn.itcast.dao.*(..))`，但**不包含子包**

## Spring使用AspectJ进行AOP的开发：XML的方式

### 第一步，引入相应的Jar包

使用`@AspectJ`的话，除了导入最基本的Jar包外，使用AspectJ还需要导入`Spring AOP`和`AspectJ`相关的Jar包。

Spring的传统AOP的开发的包:

1. spring-aop-4.2.4.RELEASE.jar
2. aopalliance-1.0.jar

AspectJ的开发包:

1. aspectjweaver-1.8.7.jar
2. spring-aspects-4.2.4.RELEASE.jar

### 第二步，创建 spring 核心配置文件，导入 aop 约束

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
    xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
						http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
						http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
						">
```

### 第三步：编写目标类以及增强类（增强类用于增强目标类）

目标类 Book.java

```java
// 目标类，会被增强类增强

package club.teenshare.aop;

public class Book {
	public void add(){
		System.out.println("add..........");
	}
}
```

增强类 MyBook.java

```java
// 增强类，用于增强目标类

// 增强类，用于增强目标类

package club.teenshare.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyBook {
	public void before1(){
		System.out.println("前置增强.........");
	}
	public void after1(){
		System.out.println("后置增强.........");
	}
	// 环绕通知
	public void arround1(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("方法之前执行...........环绕");
        // 让被增强的方法执行
        joinPoint.proceed();
        System.out.println("方法之后执行...........环绕");
	}
}
```

### 第四步，配置 applicationContext.xml 文件指定增强类与目标类

```xml
<!-- 1 配置对象 -->
<bean id="book" class="club.teenshare.aop.Book"></bean>
<bean id="myBook" class="club.teenshare.aop.MyBook"></bean>
<!-- 2 配置aop的操作 -->
<aop:config>
<!-- 2.1 配置切入点 -->
<aop:pointcut expression="execution(* club.teenshare.aop.Book.*(..))" id="pointcut1"/>
    <!-- 2.2 配置切入面
        将增强用到方法上的过程
    -->
    <aop:aspect ref="myBook">
        <!-- 配置增强的类型
            method：有不同的增强方式，前置增强等等
            pointcut-ref: 指定 pointcut的id
            -->
        <aop:before method="before1" pointcut-ref="pointcut1"/>
        <!-- 后置通知 -->
        <aop:after-returning method="after1" pointcut-ref="pointcut1"/>
        <!-- 环绕类型 -->
        <aop:around method="arround1" pointcut-ref="pointcut1"/>
</aop:aspect>
</aop:config>
```

测试类代码：

```java
@Test
public void testAop(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
    Book book = (Book)context.getBean("book");
    book.add();
}
```



