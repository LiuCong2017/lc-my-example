<template>
  <div class="son" >
    <div> 父组件对我说：{{ fatherMes  }} </div>
    <div> 父组件对我说(异步)：{{ fatherMesAsync  }} </div>
    <input  v-model="mes"   /> <button @click="send"  >对父组件说</button>
  </div>
</template>

<script setup>
import {computed, ref} from "vue";
import {useStore} from "vuex";

let mes = ref('');

const store = useStore();
const fatherMes = computed(()=>store.state.fatherMsg)
const fatherMesAsync = computed(()=>{
  return store.state.fatherMsgAsync
})

// const fatherMesAsync = computed(()=>store.state.fatherMsgAsync)

const send = ()=>{
  console.log('子组件')
  store.commit('saySon',mes)
}

</script>

<style scoped>

</style>
