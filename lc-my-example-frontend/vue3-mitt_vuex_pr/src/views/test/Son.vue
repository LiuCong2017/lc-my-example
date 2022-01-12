<template>
  <div class="son">
    <input v-model="msg"/>
    <button @click="send">对父组件说</button>
<!--    <grand-son/>-->
    <slot/>
  </div>
</template>

<script>
import GrandSon from "./GrandSon.vue";
export default {
  name: "Child",
  components: {GrandSon},
  data(){
    return{
      msg:''
    }
  },
  //引入父组件
  inject:['father'],
  methods:{
    send(){
      this.father.sonSay(this.msg);
    }
  },

}
</script>

<style scoped>

</style>
