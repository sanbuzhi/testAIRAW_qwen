```javascript
// vue.config.js
module.exports = {
  // 基本路径
  publicPath: process.env.NODE_ENV === 'production' ? '/cosmetics-management/' : '/',
  
  // 输出文件目录
  outputDir: 'dist',
  
  // 静态资源目录 (相对于 outputDir)
  assetsDir: 'static',
  
  // 是否在开发环境下通过 eslint-loader 在每次保存时 lint 代码
  lintOnSave: process.env.NODE_ENV !== 'production',
  
  // 是否使用包含运行时编译器的 Vue 构建版本
  runtimeCompiler: false,
  
  // 生产环境下的 source map 设置
  productionSourceMap: false,
  
  // webpack 配置
  configureWebpack: {
    // 解决警告：export 'default' (imported as 'ElementPlus') was not found in 'element-plus'
    resolve: {
      alias: {
        'element-plus': 'element-plus/es'
      }
    },
    // 其他自定义配置
    plugins: [
      // 可以在这里添加其他插件
    ]
  },
  
  // 或者使用 chainWebpack 进行更细粒度的配置
  chainWebpack: config => {
    // 移除 prefetch 插件，避免预加载过多资源
    config.plugins.delete('prefetch');
    
    // 设置 svg-sprite-loader
    const svgRule = config.module.rule('svg');
    svgRule.uses.clear();
    svgRule
      .use('babel-loader')
      .loader('babel-loader')
      .end()
      .use('vue-svg-loader')
      .loader('vue-svg-loader');
  },
  
  // CSS 相关配置
  css: {
    // 启用 CSS modules
    requireModuleExtension: true,
    // 是否将组件中的 CSS 提取到一个独立的 CSS 文件中 (仅在生产环境中生效)
    extract: process.env.NODE_ENV === 'production',
    // 是否开启 CSS source maps
    sourceMap: false,
    // CSS 预处理器选项
    loaderOptions: {
      sass: {
        additionalData: `@import "@/styles/variables.scss";`
      }
    }
  },
  
  // devServer 配置
  devServer: {
    port: 8080, // 端口号
    open: true, // 启动后自动打开浏览器
    proxy: {
      '/api': {
        target: 'http://localhost:3000', // 后端 API 地址
        changeOrigin: true,
        pathRewrite: { '^/api': '' }
      }
    }
  }
};
```