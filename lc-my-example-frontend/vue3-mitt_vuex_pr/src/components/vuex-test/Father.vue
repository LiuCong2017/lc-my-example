<template>
  <div class="father" >
    <input  v-model="mes"   /> <button @click="send"  >同步：对子组件说</button><br/>
    <input  v-model="asyncMes"   /> <button @click="asyncSend" >异步：对子组件说</button><br/>
    <div>子组件对我说：{{  sonMes  }}</div>
    <div>------------------------------</div>
    <son />
  </div>
</template>

<script setup>
import Son from "./Son.vue";
import {computed, onMounted, ref} from "vue";
import {useStore} from "vuex";

let mes = ref('');
let asyncMes = ref('');

const store = useStore();
const sonMes = computed(()=>{
  return store.state.sonMsg;
})
onMounted(()=>{
  console.log(store.state.sonMsg)
  // console.log(sonMes.value)
})

const send = ()=>{
  console.log(mes.value)
  store.commit('sayFather',mes.value)
}
const asyncSend = ()=>{
  console.log(asyncMes.value)
  store.dispatch('asyncSayFather',asyncMes)
}

</script>

<style scoped>

</style>
