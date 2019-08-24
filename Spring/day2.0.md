# Spring 学习笔记 Day2.0

## Spring 的 bean 管理 （注解）

### 注解介绍

1. 代码中的特殊标识，使用注解可以完成功能

2. 注解写法 `@注解名称` (属性名称=属性值)

3. 注解可以使用在类上边，方法上班和属性上边

### Spring 注解开发准备

1. 导入 jar 包
    + 导入基本的 jar 包
    + 导入 aop 的 jar 包

2. 创建类与方法

    在src下创建一个 `club.teenshare.bean` 的包，并再创建一个 `User` 的类

    ```java
    package club.teenshare.bean;

    public class User {
        private String name;
        public void say(){
            System.out.println("I said my name is:");
        }
    }
    ```

3. 创建 spring 配置文件，引入约束
    + 第一天做 ioc 的基本功能，引入约束 beans
    + 做 spring 开发还需要引入新的约束 context 约束

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans
        xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:p="http://www.springframework.org/schema/p"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
                            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

        <!-- 开启注解扫描
            1. 到包所包含的类中进行扫描类，方法，属性上是否有注解存在
        -->
        <context:component-scan base-package="club.teenshare"></context:component-scan>

        <!-- 
            只会扫描属性上面的注解
        -->
        <!-- <context:annotation-config></context:annotation-config> -->

    </beans>
    ```

    其中 `xmlns:context="http://www.springframework.org/schema/context"` 以及 `xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd  http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"`十分重要；用于引入注解的约束

    开启注解扫描有两种方法：

    ```xml
    <!-- 开启注解扫描
        1. 到包所包含的类中进行扫描类，方法，属性上是否有注解存在
    -->
    <context:component-scan base-package="cn.itcast"></context:component-scan>
    ```

    以及

    ```xml
    <!-- 
        只会扫描属性上面的注解
    -->
    <context:annotation-config></context:annotation-config>
    ```

    我们通常采用第一种方式开启扫描

### 注解创建对象

在创建对象的类上面使用注解实现

```java
@Service(value="user")			// 相当于<bean id="user" class=".."></bean>
public class User {
```

进行测试注解的创建：

```java
@Test
public void test(){
    ApplicationContext context = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
    User user = (User)context.getBean("user");
    user.say();
}
```

### Spring的bean管理中常用的注解

创建对象的注解有四个：

1. `@Component`：作用在类上
2. `@Repository`：用于对DAO实现类进行标注(持久层)。
3. `@Service`：用于对Service实现类进行标注(业务层)。
4. `@Controller`：用于对Controller实现类进行标注(WEB层)。

后三个注解是Spring中提供的`@Component`的三个衍生注解(功能目前来讲是一样的)，它们是为了让标注类本身的用途更清晰，Spring在后续的版本中会对其进行增强。

#### bean的作用范围的注解

`@Scope`

1. singleton：单例，默认值
2. prototype：多例

```java
@Service(value="user")			// 相当于<bean id="user" class=".."></bean>
@Scope(value="prototype")
public class User {
```

### 注解注入属性（对象）

1. 创建 service 类，创建 dao 类，在 service 中得到 dao 对象
    + 创建 service 和 dao 对象

    ```java
    @Service(value="userService")
    public class UserService {
    ```

    ```java
    @Component(value="userDao")
    public class UserDao {
    ```

    + 在 service 类中定义一个 dao 的属性；并加上注解  **`@Autowired`**

    ```java
    @Service(value="userService")
    public class UserService {

        // 创建 dao 属性
        // 在 userDao 上使用注解完成对象注入
        @Autowired
        private UserDao userDao;
        // 使用注解方式不需要写 set 方法

        public void say(){
            System.out.println("In Service....");
            userDao.say();
        }
    }
    ```

    注意：使用注解`@Autowired`，它不是根据名字去找Dao，而是**默认按类型进行装配**。

    + 另外一种注解是`@Resource`，使用`@Resource`注解，它默认是按名称进行注入的，也就是它的 `name` 属性需要同装载类的 `value` 一致。在实际开发中，因为 `@Resoure`更为准确，我们更多使用`@Resource`注解来注入属性的，注解`@Autowired`用到的并不多。

    ```java
    @Service(value="userService")
    public class UserService {

        // name 属性为 UserDao 的 value 属性
        @Resource(name="userDao")
        private UserDao userDao;
        public void say(){
            System.out.println("In Service....");
            userDao.say();
        }
    }
    ```
