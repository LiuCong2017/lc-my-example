<template>
  <router-view/>
</template>
<script>
export default {

  //vuex页面刷新数据丢失的解决办法(sessionStorage页面关闭会丢失)
  // created() {
  //   if (localStorage.getItem("store") ) {
  //     this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
  //   }
  //
  //   //在页面刷新时将vuex里的信息保存到sessionStorage里
  //   window.addEventListener("beforeunload",()=>{
  //     localStorage.setItem("store",JSON.stringify(this.$store.state))
  //   })
  // },
  //
  // beforeDestroy() {
  //   window.addEventListener('close',()=>{
  //
  //   })
  // }

}
</script>
<style lang="scss">
body .el-table--scrollable-y {
.el-table__body-wrapper::-webkit-scrollbar {
  width: 9px;
  height:9px;
}
.gutter {
  width: 10px !important;
}
}
</style>
<style lang="scss">
//#app {
//  font-family: Avenir, Helvetica, Arial, sans-serif;
//  -webkit-font-smoothing: antialiased;
//  -moz-osx-font-smoothing: grayscale;
//  text-align: center;
//  color: #2c3e50;
//}
//
//#nav {
//  padding: 30px;
//
//  a {
//    font-weight: bold;
//    color: #2c3e50;
//
//    &.router-link-exact-active {
//      color: #42b983;
//    }
//  }
//}

//body .el-table th.gutter {
//  display: table-cell !important;
//}
//body .el-table colgroup.gutter {
//  display: table-cell !important;
//}

</style>
