<template>
  <DockMenu/>
  <NavBar/>
  <div>
    <h1>This is HomePage</h1>
        <div>
          <ul>
            <li>name:{{name}}</li>
            <li>username:{{username}}</li>
            <li>roles:{{roles}}</li>
          </ul>
        </div>
  </div>
  <DataSource/>
<!--  <TestTree/>-->
<!--  <EditTree/>-->
  <div>
    <router-link to="/test">点击跳转到Test</router-link>
  </div>

</template>

<script setup>
import NavBar from "@/components/layout/NavBar";
import DockMenu from "@/components/layout/DockMenu";
import DataSource from "@/components/datasource/DataSource.vue"
import TestTree from "@/components/tree/TestTree.vue"
// import EditTree from "@/components/tree/EditTree";
import {inject, onBeforeUnmount} from "vue";

const {name,username,roles} = JSON.parse(localStorage.getItem('userinfo')) || ''

const bus = inject('$bus');
onBeforeUnmount(()=>{
  bus.all.clear();
})
</script>

<style scoped>

</style>
