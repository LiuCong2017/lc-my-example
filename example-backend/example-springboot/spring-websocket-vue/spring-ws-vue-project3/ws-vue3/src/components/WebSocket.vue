<template>
  <el-button @click="sendDataToServer">给后台发消息</el-button>
</template>

<script setup>
import {onMounted, reactive, ref, toRefs} from "vue";

let wsIsRun = ref(false);// ws是否启动
const webSocket = reactive({}) // 定义ws对象
let ws = ref(''); // ws请求链接（类似于ws后台地址）
let wsTimer = ref(null); // ws定时器

onMounted(async ()=>{
  wsIsRun.value = true;
  wsInit();
})

/**
 * 初始化ws
 */
const wsInit = ()=>{
  ws.value = 'ws://127.0.0.1:2022/ws/mis';
  if (!wsIsRun.value) return;
  wsDestroy();// 销毁ws
  Object.assign(webSocket,new WebSocket(ws.value));// 初始化ws
  webSocket.addEventListener('open',wsOpenHandler);// ws连接建立时触发
  webSocket.addEventListener('message',wsMessageHanler);// ws服务端给客户端推送消息
  webSocket.addEventListener('error', wsErrorHanler);// ws通信发生错误时触发
  webSocket.addEventListener('close', wsCloseHanler);// ws关闭时触发

  // 设置了一个3秒的定时器去定时检查websocket的连接状态
  // 检查ws连接状态,readyState值为0表示尚未连接，1表示建立连接，2正在关闭连接，3已经关闭或无法打开
  clearInterval(wsTimer.value)
  wsTimer.value = setInterval(()=>{
    if (webSocket.readyState===1){
      clearInterval(wsTimer.value)
    }else {
      console.log('ws建立连接失败')
      wsInit()
    }
  },3000)

}

const sendDataToServer = ()=>{
  if (webSocket.readyState === 1){
    webSocket.send('来自前端的数据')
  }else {
    throw Error('服务未连接')
  }
}

const wsOpenHandler = (event) =>{
  console.log('ws建立连接成功')
}
const wsMessageHanler = (e) =>{
  console.log('wsMessageHanler')
  console.log(e)
  //const redata = JSON.parse(e.data)
  //console.log(redata)
}
/**
 * ws通信发生错误
 */
const wsErrorHanler = (event)=> {
  console.log(event, '通信发生错误')
  wsInit()
}
/**
 * ws关闭
 */
const wsCloseHanler = (event) =>{
  console.log(event, 'ws关闭')
  wsInit()
}
/**
 * 销毁ws
 */
const wsDestroy = ()=>{
  if (webSocket !== null){
    webSocket.removeEventListener('open', wsOpenHandler)
    webSocket.removeEventListener('message', wsMessageHanler)
    webSocket.removeEventListener('error', wsErrorHanler)
    webSocket.removeEventListener('close', wsCloseHanler)
    webSocket.close()
    Object.assign(webSocket,{})
    clearInterval(wsTimer.value)
  }
}
</script>

<style scoped>

</style>