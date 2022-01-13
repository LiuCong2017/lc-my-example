const path = require('path')

module.exports = {
    publicPath: '/',
    outputDir: 'dist',

    devServer:{
        host: "0.0.0.0",
        port: 2233,
        open: true,
        //跨域配置
        proxy:{
            '/m': {
                target: 'http://192.168.2.200:8083', //http://xxx:xx,服务器接口
                changeOrigin: true,
                pathRewrite: {
                    '^/m': '/'
                }
            },
            '/d': {
                target: 'http://127.0.0.1:20029', //http://xxx:xx,服务器接口
                changeOrigin: true,
                pathRewrite: {
                    '^/d': '/'
                }
            },
        }
    },

    pluginOptions:{

    },

    configureWebpack(){
        return{
            resolve:{
                alias:{
                    '@':path.resolve(__dirname,'src')
                }
            },
            module:{},
            plugins: [],
        }
    },

}
