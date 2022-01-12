import { createApp } from 'vue'
import App from './App.vue'
import store from "./store";
import bus from './utils/bus'

const app = createApp(App);
//全局注册推荐方式，mitt似乎也只能通过这种方式才能使用
app.provide('$bus',bus)
app.use(store).mount('#app')
