<template>
  <div class="father">
    <div>子组件对我说: {{ sonMsg }}</div>
    <div>孙组件对我说: {{ grandSonMsg }}</div>
    <son>
      <grand-son/>
    </son>
  </div>
</template>

<script>
import son from './Son.vue'
import GrandSon from "./GrandSon.vue";

export default {
  name: "Parent",
  components:{GrandSon, son},
  provide(){
    return {
      father:this
    }
  },
  data(){
    return{
      grandSonMsg:'',
      sonMsg:''
    }
  },
  methods:{
    //接收孙组件
    grandSonSay(value){
      this.grandSonMsg = value
    },
    //接收子组件
    sonSay(value){
      this.sonMsg = value
    },
  },

}
</script>

<style scoped>

</style>
