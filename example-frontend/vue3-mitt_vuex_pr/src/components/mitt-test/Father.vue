<template>
  <div class="father">
    <div>Message from son: {{sonMsg}}</div>
  </div>
  <son/>
</template>

<script setup>
import Son from "./Son.vue";
import {inject, onBeforeUnmount, ref} from "vue";

let sonMsg = ref('');
const bus = inject('$bus');
const sayHi = (msg)=>{
  console.log(msg)
  sonMsg.value = msg.a;
}

//监听所有事件，则参数包含type
// bus.on('*',(type,e)=>{
//   console.log(type,e)
// });

bus.on('son',e=>{
  sayHi(e)
})

onBeforeUnmount(()=>{
  bus.all.clear();
  // bus.off('*',sayHi);
})

</script>

<style scoped>

</style>
