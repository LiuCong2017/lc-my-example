<template>
  <div class="grandSon" >
    <input  v-model="msg"  />
    <button @click="send"  >对爷爷组件说</button>
  </div>
</template>

<script>
export default {
  name: "GrandSon",
  inject:['father'],//引入爷爷组件
  data(){
    return{
      msg:''
    }
  },
  methods:{
    send(){
      this.father.grandSonSay(this.msg)
    }
  },
}
</script>

<style scoped>

</style>
