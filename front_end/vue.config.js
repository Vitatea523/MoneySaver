// vue.config.js
const TerserPlugin = require('terser-webpack-plugin')
const CopyWebpackPlugin = require('copy-webpack-plugin')
const path = require('path')
const isPro = process.env.NODE_ENV === 'production'

function resolve(dir) {
    return path.join(__dirname, dir)
}
module.exports = {
    publicPath: './',
    outputDir: 'vue_web_dist',
    assetsDir: '',
    lintOnSave: process.env.NODE_ENV !== 'production',
    productionSourceMap: false,
    css: {
        extract: isPro, // 是否将组件中的 CSS 提取至一个独立的 CSS 文件中
        loaderOptions: {
            css: {
                url: true
                    // 这里的选项会传递给 css-loader
            }
        }
    },
    configureWebpack: config => {
        if (isPro) {
            // 为生产环境修改配置
            // config.output = {
            //   // 输出重构  打包编译后的 文件名称  【模块名称.版本号】
            //   filename: `js/[name]_${baseConfig.version}.js`,
            //   chunkFilename: `js/[name]_${baseConfig.version}.js`
            // }
            config.optimization.minimizer = [
                new TerserPlugin({
                    terserOptions: {
                        ecma: undefined,
                        warnings: false,
                        parse: {},
                        compress: {
                            drop_console: true,
                            drop_debugger: false,
                            pure_funcs: ['console.log'] // 移除console
                        }
                    }
                })
            ]
            console.log(config)
        } else {
            // 为开发环境修改配置

        }
        config.plugins.push(
            new CopyWebpackPlugin([{
                    from: path.resolve(__dirname, 'src/assets/copy'),
                    to: 'images'
                },
                {
                    from: path.resolve(__dirname, 'src/assets/font'),
                    to: 'font'
                }
            ]))
    },
    chainWebpack: config => {
        config
            .module
            .rule('images')
            .test(/\.(jpg|png|gif)$/)
            .use('url-loader')
            .loader('url-loader')
            .options({
                limit: 1000 * 3,
                // 以下配置项用于配置file-loader
                // 根据环境使用cdn或相对路径
                publicPath: './images',
                // 将图片打包到dist/images文件夹下, 不配置则打包到dist文件夹下
                outputPath: 'images',
                // 配置打包后图片文件名
                name: '[name].[ext]'
            })
            .end()
        config.resolve.alias
            .set('@', resolve('src'))
            .set('@views', resolve('src/views'))
            .set('@assets', resolve('src/assets'))
            .set('@js', resolve('src/assets/js'))
            .set('@images', resolve('src/assets/images'))
            .set('@components', resolve('src/components'))
    },
    devServer: {
        // 自动打开浏览器
        open: true,
        host: '127.0.0.1',
        // 端口
        port: 8000,
        // https
        https: false,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                // 如果接口跨域，需要进行这个参数配置
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}