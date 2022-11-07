import { createApp } from 'vue'
import {createPinia} from "pinia";
import './style.css'
import App from './App.vue'
import router from "./router";
import ELementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import en from 'element-plus/es/locale/lang/en'

const app = createApp(App)

app.use(createPinia)
    .use(router)
    .use(ELementPlus,{locale: en})
    .mount('#app')
