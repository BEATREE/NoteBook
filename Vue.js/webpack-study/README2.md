# 复习 webpack 使用方法

项目去除了 `node_modules` ，在使用项目前需要先使用 `npm install` 进行初始化

## 项目基本配置

建立项目后，首先进行环境初始化 `npm init -y` 进行初始化，然后创建 `webpack.config.js` 文件进行基础的 webpack 的配置。 对于指定文件和操作，需要引入 loader

**loader** 也可以进行参数的传递，传参方式和 url 传参方式相同！使用 **?** 传参。例子：

```js
{test: /\.(jpg|png|gif|jpeg)$/, use: 'url-loader?limit=1335536&name=[name].[ext]'},    // 处理图片路径的loader file-loader 是 url-loader 的内部依赖，所以不用写
            // ? 可以传参，其中有个固定的参数 limit 指图片的大小单位为 byte、
            // 当引用的图片大小 >= 设定的 limit时候，不会进行 base64 编码字符串，如果图片大小小于给定的值，则会被转为 base64 字符串
            // 参数 name=[name].[ext] 表示之前是什么名称，打包后还是什么名称，原来是什么后缀，打包后也不变
```

如果需要自己进行发布和更新内容 需要安装`webpack-dev-server`插件:

```shell
npm install webpack-dev-server -D
```

另外还需要 `html-webpack-plugin` 将页面加载到内存中。


同样，我们也要将 css 文件或者 less 或 sass 文件打包到 js 中，则我们需要安装 `style-loader`以及`css-loader`。如果要进行解析 sass 则要安装 `less-loader`; less 文件则要安装 `sass-loader`。

```shell
npm install style-loader css-loader -D
```

同时在 webpack.config.js 中进行配置：

```js
rules: [// 第三方模块的匹配规则
            {test: /\.css$/, use: ['style-loader', 'css-loader']}, // 处理css的loader
            {test: /\.less$/, use: ['style-loader', 'css-loader', 'less-loader']}, // 处理less的loader
            {test: /\.scss$/, use: ['style-loader', 'css-loader', 'sass-loader']}, // 处理scss的loader
        ]
```

当我们的 css 文件中出现了 路径引用 时候，例如插入 图片或者字体文件，需要加入以下loader：**url-loader file-loader**

```shell
npm install url-loader file-loader -D
```
