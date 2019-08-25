# Spring 学习笔记 Day2.0

## Spring 使用连接池

### 1. Spring 配置 c3p0 连接池

第一步：导入 jar 包：`c3p0-version*.jar` 和 `mchange-commons-java-version*.jar`

第二步：写入 c3p0 配置文件

我们实际上会执行的代码是：

```java
ComboPooledDataSource dataSource = new ComboPooledDataSource();
dataSource.setDriverClass(driverClass);
dataSource.setJdbcUrl(jdbcUrl);
dataSource.setUser(user);
dataSource.setPassword(password);
```

但是我们要将这个代码写入配置文件，所以在写入之前，我们首先要引入 c3p0 的约束。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd">

</beans>
```

然后注入对象即可

```xml
<!-- 配置C3P0连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <!-- 注入里边的属性值 -->
    <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
    <property name="jdbcUrl" value="jdbc:mysql:///chatroom"></property>
    <property name="user" value="root"></property>
    <property name="password" value="123456"></property>
</bean>
```

### 2. dao 使用 jdbcTemplate

首先我们创建 UserDao 和 UserService 两个java文件：

UserDao.java

```java
public class UserDao {
	
	// 得到 jdbcTemplate 对象
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void add(){
		String sql = "INSERT INTO user (username, password) VALUES (?,?)";
		jdbcTemplate.update(sql, "BEATREE", "QWER");
	}
}
```

UserService.java

```java
public class UserService {
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void add(){
		userDao.add();
	}
}
```

然后我们需要配置 applicationContext.xml 文件：首先创建两个类的 bean 配置，然后将 userDao 作为参数传递给 userService；

然后由于我们在 userDao 中需要 jdbcTemplate 的属性，所以我们再写入它的 bean 配置

```xml
<!-- 创建jdbcTemplate对象 -->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <!-- 把 dataSource 传递到 JdbcTemplate 中去 -->
    <property name="dataSource" ref="dataSource"></property>
</bean>
<bean id="userDao" class="club.teenshare.c3p0.UserDao">
    <!-- 在dao中注入模板对象 -->
    <property name="jdbcTemplate" ref="jdbcTemplate"></property>
</bean>
<bean id="userService" class="club.teenshare.c3p0.UserService">
    <!-- 注入dao对象 -->
    <property name="userDao" ref="userDao"></property>
</bean>
```

## Spring 的事务管理

### 什么是事务

事务是数据库操作的**最小工作单元**，是作为单个逻辑工作单元执行的一系列操作；这些操作作为一个整体一起向系统提交，**要么都执行、要么都不执行**；事务是一组不可再分割的操作集合（工作逻辑单元）；

### 事务特性

1. 原子性：强调事务的不可分割。
2. 一致性：事务的执行的前后数据的完整性保持一致。
3. 隔离性：一个事务执行的过程中，不应该受到其他事务的干扰。
4. 持久性：事务一旦结束，数据就持久化到数据库。

### 不考虑隔离性产生的问题

1. 脏读：一个事务读到了另一个事务的未提交的数据。
2. 不可重复读：一个事务读到了另一个事务已经提交的update的数据，导致多次查询的结果不一致。
3. 虚读：一个事务读到了另一个事务已经提交的insert的数据，导致多次查询的结果不一致。

### 解决读问题

设置事务的隔离级别：

1. 未提交读：脏读、不可重复读和虚读都有可能发生。
2. 已提交读：避免脏读，但是不可重复读和虚读有可能发生。
3. 可重复读：避免脏读和不可重复读，但是虚读有可能发生。
4. 串行化的：避免以上所有读问题。

### Spring 事务管理 API

**事务管理两种方式：**

第一种：编程式事务管理（不用）

第二种：声明式事务管理

1. 基于 xml 配置文件实现
2. 基于注解实现

**Spring事务管理的 api**

Spring事务管理实现类

Spring将事务管理委托给底层具体的持久化框架来完成。因此，Spring为不同的持久化框架提供了不同的PlantformTransactionManager接口的实现类。

事务 | 说明
--|--
`org.springframework.orm.jpa.JpaTransactionManager` |使用`JPA`进行持久化时候，使用该事务管理器
`org.springframework.orm.hibernateX.HibernateTransactionManager` | 使用`Hibernate X.0`版本进行持久化时，使用该事务管理器
`org.springframework.jdbc.datasource.DataSourceTransactionManager` |使用 `SpringJDBC` 或者MyBatis等基于 DataSource 数据源
的持久化技术时，使用该事务管理器
`org.springframework.orm.jdo.JdoTransactionManager` |使用JDO进行持久化时，使用该事务管理器
`org.springframework.transaction.jta.JtaTransactionManager` | 具有多个数据源的全局事务使用该事务管理器

## Example：转账模拟

1. 创建数据库的表，添加数据
2. 创建 service 和 dao 层，完成注入关系

    ```xml
    <!-- 创建jdbcTemplate对象 -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<!-- 把 dataSource 传递到 JdbcTemplate 中去 -->
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!-- 诸如两个实体类 -->
	<bean id="orderDao" class="club.teenshare.dao.OrderDao">
		<property name="jdbcTemplate" ref="jdbcTemplate"></property>
	</bean>
	<bean id="orderService" class="club.teenshare.service.OrderService">
		<property name="orderDao" ref="orderDao"></property>
	</bean>
    ```

    （1）service 又叫业务逻辑层

    （2）dao 单纯数据库操作层，在 dao 层不添加业务

3. 需求：小草给大树转 1000 元

    实现逻辑与代码：

    **OrderDao.java**

    ```java
    public class OrderDao {
        private JdbcTemplate jdbcTemplate;

        public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }
        /*
        * 完成对数据操作的方法，不写业务操作
        * */
        // 少钱的方法
        public void lessMoney(){
            String sql = "UPDATE account SET salary=salary-? WHERE username=?";
            jdbcTemplate.update(sql, 1000, "A");
        }
        // 增加钱的方法
        public void moreMoney(){
            String sql = "UPDATE account SET salary=salary+? WHERE username=?";
            jdbcTemplate.update(sql, 1000, "B");
        }
    }

    ```

    **OrderService.java**

    ```java
    public class OrderService {
        private OrderDao orderDao;

        public void setOrderDao(OrderDao orderDao) {
            this.orderDao = orderDao;
        }
        // 调用 dao 的方法
        //业务逻辑层，写转账的业务
        public void accountMoney(){
            //小草少 1000
            orderDao.lessMoney();
            // 忽然发生了异常，不如少了之后忽然银行停电了
            int i = 10/0;	// 手动出现故障
            //大树多 1000
            orderDao.moreMoney();
            System.out.println(".....................");
        }
    }
    ```

    如果是我们直接进行转账，则不会出现什么问题，但是如果中途停电或者其他情况则会出现数据不同步的问题。

4. 出现问题：

    如果A给B转账期间 A 少了1000后，B 并未收到，则出现了问题。

5. 解决方式：

    1. 添加事务解决，出现异常进行回滚


首先附注上MySQL事务隔离的级别，四种隔离界别的具体例子可以看这个文章 --> [面试官：谈谈Mysql事务隔离级别？](https://baijiahao.baidu.com/s?id=1629344395894429251)

事务隔离级别|脏读|不可重复读|幻读
-|-|-|-
读未提交（read-uncommitted）|是 |是 |是
不可重复读（read-committed）|否 |是 |是
可重复读（repeatable-read） |否 |否|是
串行化（serializable）|否|否|否

## 声明式事务管理（XML方式）

**第一步：配置事务管理器**

```xml
<!-- 第一步 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

**第二步：配置事务的增强**

```xml
<!-- 配置事务的增强 -->
<tx:advice id="txadvice" transaction-manager="transactionManager">
    <!-- 做事务操作 -->
    <tx:attributes>
        <!-- 设置进行事务操作的方法匹配规则
            下文是匹配以account开头的方法
            -->
        <tx:method name="account*" />
    </tx:attributes>
</tx:advice>
```

**第三步：配置切面**（将增强用到方法的过程叫做切面）

```xml
<!-- 第三步：配置切面 -->
<aop:config>
    <!-- 切入点 
        第一个*含义是：代表所有类型的返回值，第二个*是代表club.teenshare.service.BookService下的所有类，
        第三个是类下的所有方法，括号中两个点表示任意个形参。
    -->
    <aop:pointcut expression="execution(* club.teenshare.service.BookService.*.*(..))" id="pointcut1"/>
    <!-- 切面，即表示把哪个增强用在哪个切入点上 -->
    <aop:advisor advice-ref="txadvice" pointcut-ref="pointcut1"/>
</aop:config>
```

## 声明式事务管理（注解方式）

**第一步 配置事务管理器**

```xml
<!-- 第一步 配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

**第二步 配置事务注解**

```xml
<!-- 第2步：： 开启事务的注解 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
```

**第三步 在要使用事务的方法所在类上添加注解**

注意是在方法所在类上添加了 `@Transactional` 注解。

```java
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	// 调用 dao 的方法
	//业务逻辑层，写转账的业务
	public void accountMoney(){
		//小草少 1000
		orderDao.lessMoney();
		// 忽然发生了异常，不如少了之后忽然银行停电了
		int i = 10/0;	// 手动出现故障
		//大树多 1000
		orderDao.moreMoney();
		System.out.println(".....................");
	}
}
```

**注解配置**下的最终的 applicationContext.xml 文件如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd">

	<!-- 配置C3P0连接池 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<!-- 注入里边的属性值 -->
	    <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
	    <property name="jdbcUrl" value="jdbc:mysql:///testdb"></property>
	    <property name="user" value="root"></property>
	    <property name="password" value="123456"></property>
	</bean>

	<!-- 创建jdbcTemplate对象 -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<!-- 把 dataSource 传递到 JdbcTemplate 中去 -->
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!-- 诸如两个实体类 -->
	<bean id="orderDao" class="club.teenshare.dao.OrderDao">
		<property name="jdbcTemplate" ref="jdbcTemplate"></property>
	</bean>
	<bean id="orderService" class="club.teenshare.service.OrderService">
		<property name="orderDao" ref="orderDao"></property>
	</bean>
	
	<!-- 第一步 配置事务管理器 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	
	<!-- 第2步：： 开启事务的注解 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
	

</beans>
```
