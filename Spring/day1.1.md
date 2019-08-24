# Spring 整合 web 项目原理

1. 加载 Spring 核心配置文件

    ```java
    // 加载核心配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ```

    但是存在缺点：（1） new 对象，功能可以实现，但是效率低

2. 实现思想：把加载配置文件和创建对象的过程，在服务器启动的时候完成

3. 实现原理：

    （1）ServletContext 对象

    （2）监听器进行监听什么时候创建

    （3）具体使用：

        - 在服务器启动时候为每个项目创建一个 ServletContext 对象
        - 在 ServletContext 对象创建的时候，使用监听器可以监听到 ServletContext 对象在何时进行了创建
        - 使用监听器监听到 ServletContext 创建时，
        - 加载Spring的配置文件，将配置文件对象创建
        - 把创建出来的对象放到 ServletContext 域对象里边（setAttribute方法)
        - 需要获取对象时候，到 ServletContext 域中将其取出（getAttribute方法）
