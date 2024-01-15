import axios from 'axios'
import apiConfig from './api.config'
// onLine  线上生产
// local 本地生产
// dev 本地开发

let url = ''
const isPro = process.env.NODE_ENV === 'production'
if (isPro) {
  // 编译
  url = apiConfig[process.env.VUE_APP_API_BASE_URL]
} else {
  // 开发
  url = apiConfig.dev
}
const Axios = axios.create({
  baseURL: url,
  timeout: 120000,
  headers: {
    'Content-Type': 'application/json'
  },
  withCredentials: false
})
// 添加请求拦截器

Axios.interceptors.request.use(function (config) {
  // 单个网络取消请求
  const cancelToken = (config.params && config.params.cancelToken) || ''
  if (sessionStorage.getItem('token')) {
    config.headers.token = sessionStorage.getItem('token');
  }
  if (cancelToken) {
    delete config.params.cancelToken
  } else {
    // 全局取消请求配置
  }
  // httpType为formdata Object转成FormData
  if (['put'].includes(config.method)) {
    config.headers = {
      'Content-Type': 'application/json'
    }
  }
  // 在发送请求之前做些什么
  return config
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 添加响应拦截器
Axios.interceptors.response.use(function (res) {
  return res.data
}, function (error) {
  const stack = error.stack || ''
  if (stack.indexOf('timeout') >= 0) {
    // 超时
  } else if ((!error.request || stack.indexOf('Network') >= 0) && error.message !== 'cancel') {
    // 无网络
  } else if (error.message !== 'cancel') {
    // 请求错误
  }
  // 对响应错误做点什么
  return Promise.reject(error)
})

export default Axios