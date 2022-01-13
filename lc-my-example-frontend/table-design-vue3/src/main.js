import { createApp } from 'vue'
import App from './App';
import router from './router'
import {store} from './store'
import './index.css' //tailwindCSS
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import bus from './utils/bus'

const app = createApp(App);
app.provide('$bus',bus);
app.use(store)
    .use(router)
    .use(ElementPlus,{
        locale: zhCn,
    })
    .mount('#app')
