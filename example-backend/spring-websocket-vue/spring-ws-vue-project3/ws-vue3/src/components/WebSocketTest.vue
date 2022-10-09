<template>
  <el-button @click="webSocketSend">给后台发消息</el-button>
</template>

<script setup>
let webSocket = null;
//连接成功
import {onBeforeUnmount, onMounted, ref} from "vue";

const webSocketOnOpen = res=>{
  console.log("连接成功",res)
  keepAlive();
}
//接收消息
const webSocketOnMessage = res=>{
  console.log("来自服务器的消息:"+res.data)
}
//连接错误
const webSocketOnError = res=>{
  console.log("连接错误:",res)
}
//断开
const webSocketClose = res=>{
  console.log("断开连接",res)
  cancelKeepAlive();
}

const webSocketSend = ()=>{
  if (webSocket.readyState===1){
    webSocket.send('来自前端的数据')
  }else {
    console.log('连接失败')
  }
}

/**
 * 前台定时心跳WebSocket
 * @type {null}
 */
// let wsTimer = ref(null); // ws定时器
// // 设置了一个3秒的定时器去定时检查websocket的连接状态
// // 检查ws连接状态,readyState值为0表示尚未连接，1表示建立连接，2正在关闭连接，3已经关闭或无法打开
// clearInterval(wsTimer.value)
// wsTimer.value = setInterval(()=>{
//   if (webSocket.readyState===1){
//     webSocket.send('前端心跳')
//     // clearInterval(wsTimer.value)
//   }else {
//     console.log('ws建立连接失败')
//     window.location.reload();
//     // webSocketOnOpen();
//     // wsInit() //重新连接
//   }
// },5000)

// 解决websocket超时断连问题
let timerId = ref(0);
function keepAlive() {
  // let timeout = 20000;
  // if (webSocket.readyState === webSocket.OPEN) {
  //   webSocket.send('心跳');
  // }
  // timerId.value = setTimeout(keepAlive, timeout);
}
function cancelKeepAlive() {
  // if (timerId) {
  //   clearTimeout(timerId);
  // }
}


onMounted(()=>{
  webSocket = new WebSocket('ws://127.0.0.1:2022/ws/mis')
  webSocket.onopen = webSocketOnOpen;
  webSocket.onmessage = webSocketOnMessage;
  webSocket.onerror = webSocketOnError;
  webSocket.onclose = webSocketClose;
})

onBeforeUnmount(()=>{
  webSocketClose();
  webSocket = null;
})
</script>

<style scoped>

</style>