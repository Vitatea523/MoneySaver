import {
    createApp
} from 'vue'
import App from './App.vue'
import router from './route/index'
import ElementPlus from 'element-plus'
import Axios from './http/axios'
import 'element-plus/dist/index.css'
import './assets/css/reset.css'
const app = createApp(App)
app.config.globalProperties.$axios = Axios
app.use(ElementPlus)
app.use(router)
app.mount('#app')