<template>
<!--  可加属性-->
<!--  :props="defaultProps"-->
  <el-tree
      :data="data"
      show-checkbox
      @check-change="handleCheckChange"
      @node-click="handleNodeClick" />
</template>

<script setup>
import {inject, reactive} from "vue";

// const data = reactive([
//   {
//     label: "表",
//     children: [
//       {
//         label: "表1",
//         children: [
//           {
//             label: "字段1"
//           },
//           {
//             label: "字段2"
//           }
//         ]
//       },
//       {
//         label: "表2",
//         children: [
//           {
//             label: "字段1"
//           },
//           {
//             label: "字段2"
//           }
//         ]
//       }
//     ]
//   },
//
//   {
//     label: "视图",
//     children: [
//       {
//         label: "视图1",
//         children: [
//           {
//             label: "字段1"
//           },
//           {
//             label: "字段2"
//           }
//         ]
//       },
//       {
//         label: "视图2",
//         children: [
//           {
//             label: "字段1"
//           },
//           {
//             label: "字段2"
//           }
//         ]
//       }
//     ]
//   },
//
//
//   {
//     label: "存储过程",
//     children: [
//       {
//         label: "存储过程1",
//         children: [
//           {
//             label: "字段1"
//           },
//           {
//             label: "字段2"
//           }
//         ]
//       },
//       {
//         label: "存储过程2",
//         children: [
//           {
//             label: "字段1"
//           },
//           {
//             label: "字段2"
//           }
//         ]
//       }
//     ]
//   }
//
// ])



// const defaultProps = reactive({
//   children: 'children',
//   label: 'label',
// })

const data = inject('treeData');

const handleCheckChange = (data, checked, indeterminate)=>{
  //可以读到选中节点
  console.log(data, checked, indeterminate)
}
const handleNodeClick = (data)=>{
  //只能读到节点level第一次变化
  // console.log(data);
}
</script>

<style scoped>

</style>
