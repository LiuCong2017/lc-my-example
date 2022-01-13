<template>
  <div>
    <button @click="toFather">sendFather</button>
  </div>
</template>

<script setup>
import {inject, onMounted} from "vue";

const bus = inject('$bus')
const toFather = ()=>{
  console.log('send to father')
  bus.emit('son',{a:'This is the message from son'})
}


</script>

<style scoped>

</style>
