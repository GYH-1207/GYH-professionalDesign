const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    resolve: {
      alias: {
        "assets": '@/assets',
        "common": '@/common',
        "components": '@/components',
        "views": '@/views',
        "network": '@/network',
      }
    }
  },
  // 取消ws
  devServer: {
    // ws: false
    host: '127.0.0.1',
	  port: 8080,
    proxy: {
      '^/api/.*': {
        target: 'http://127.0.0.1:8081',// 后端接口
        changeOrigin: true, // 是否跨域
        rewrite: path => path.replace(/^\/api/, "") //重写路径，去掉api
      }
    }
  }
})
