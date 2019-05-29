# webpack 入门学习使用的项目

项目去除了 `node_modules` ，在使用项目前需要先使用 `npm install` 进行初始化

## webpack.config.js

webpack配置的基础js文件，其内包含webpack 相关的笔记内容，另外还有 `package.json` 中的配置文件信息。

## webpack 打包时候做了哪些事？

> 当我们在控制台，直接输入 webpack 命令执行的时候， webpack做了以下几步：

1. 首先，webpack 发现我们并没有通过命令的形式指定入口和出口
2. webpack 就会去项目的根目录中查找 webpack.config.js 配置文件
3. 当找到配置文件后，webpack 会去解析执行文件
4. 当解析执行完配置文件后，就得到了配置文件中导出的配置对象  line:4 的内容
5. 当webpack 拿到配置对象后，就拿到了配置对象中，指定的入口和出口，然后进行打包组件

> 使用 webpack-dev-server 这个工具，来实现自动打包编译的功能

1. 运行 npm install webpack-dev-server -D 这个工具安装到，项目的本地开发依赖
2. 安装完毕后，这个工具的用法 和 webpack 的用法完全一样
3. 如果是项目中安装的 webpack-dev-server 所以无法把他当作我们的脚本命令，在终端中直接运行
     只有安装到全局 -g 的工具才能在终端正常执行
4. 注意： webpack-dev-server 这个工具，如果想要正常运行，在本地项目中也得安装 webpack, 全局装过也要在本地装
5. webpack-dev-server 帮我们打包生成的 bundle.js 文件，并没有放到实际的物理磁盘上；
     而是， 直接托管到了电脑的 内存 中，所以我们在项目根目录中根本找不到这个打包好的bundle.js;
6. 我们可以认为 webpack-dev-server 把这个打包好的文件，以一种虚拟的方式托管到了 项目的根目录中，虽然我们看不到它
     但是可以认为和 dist 、src 、node_modules 平级，有一个看不见的额文件，叫做 bundle.js

在package.json 中 
`webpack-dev-server --open --port 3000 --contentBase src`
`--open` 打开浏览器， `--port` 指定端口 `--contentBase` 指定默认根目录
不会重新完全生成 bundle.js 而是 添加一个js文件和json文件，进行局部修改，避免了多次修改造成的浪费

## SOMETHING ABOUT LOADER

对于一些特定的类型，webpack 需要依靠第三方的 loader 进行封装和解析。相关的loader有：

`css` 对应的 loader ： `style-loader, css-loader`

`less` 对应的 loader：`style-loader, css-loader, less-loader`

安装 `less-loader` 需要安装内部依赖的 `less` ，才可以。命令 `npm install less -D`

`scss` 对应的 loader：`style-loader, css-loader, sass-loader, node-sass`

安装 `sass-loader` 还需要安装 `node-sass` 支持 `sass-loader`

其他的 loader 会在使用到的项目中，提到