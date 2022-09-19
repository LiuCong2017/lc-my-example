<template>
  <div>
    <Book/>
  </div>
  <div>-------------------------------------------------</div>
  <div style="background-color: red">
    count: {{counter.count}}<br/>
    <button type="button" @click="counter.increment()">add</button>
    <button type="reset" @click="counter.reset()">reset</button>
  </div><br/><br/>

  <div style="background-color: yellow">
    user:{{name}}<br/>
    <input type="text" v-model="pName"/>
  </div>

</template>

<script setup>
  import Book from "../components/Book.vue"
  import {useCounterStore} from "../store/counter.js";
  import {useUserStore} from "../store/user.js";
  import {computed, ref, watch} from "vue";

  const counter = useCounterStore()

  let pName = ref('')
  const useUser = useUserStore()

  if (useUser.getJobId !== 0){
    console.log(useUser.getJobId)
  }

  const name = computed(()=>{
    return useUser.username
  })
  const changeName = (name)=>{
    return useUser.M_name(name)
  }
  watch(pName,(v)=>{
    changeName(v)
  },{deep:true})

</script>
