<template>
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
  import {useCounterStore} from "../store/counter.js";
  import {useUser} from "../store/user.js";
  import {computed, ref, watch} from "vue";

  const counter = useCounterStore()

  let pName = ref('')
  const userStore = useUser()
  const name = computed(()=>{
    return userStore.username
  })
  const changeName = (name)=>{
    return userStore.M_name(name)
  }
  watch(pName,(v)=>{
    changeName(v)
  },{deep:true})

</script>
