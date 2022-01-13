<template>

  <TestTree/>

  <div style="width: 30%;">
    <h2 style="color: blue">数据库名: {{dbName}}</h2>
    <el-table
        ref="multipleTable"
        :data="tables"
        height="300"
        @selection-change="handleSelectionChange"
        stripe border
    >
      <el-table-column type="selection" width="55" />
<!--      <el-table-column type="index" width="50" />-->
      <el-table-column prop="tableName" label="表名" align="center" />

      <el-table-column label="查看字段" width="100" align="center">
        <template v-slot:default="scope">
          <el-button type="text" :icon="Search" size="small" @click="getColumns(scope.$index, scope.row)"/>
        </template>
      </el-table-column>

    </el-table>
  </div>
  <div style="width: 30%;margin-top: 30px;">
    <h2 style="color: blue">表名: {{tableName}}</h2>
    <el-table :data="columns" height="300" stripe border>
      <el-table-column type="index" width="50" />
      <el-table-column prop="columnName" label="字段名" align="center" />
      <el-table-column prop="dataType" label="字段类型" align="center" />
      <el-table-column prop="dataLength" label="字段长度" align="center" />
<!--      <el-table-column label="查看字段" fixed="right" width="100" align="center">-->
<!--        <template v-slot:default="scope">-->
<!--          <el-button type="text" :icon="Search" size="small" @click="getColumns(scope.$index, scope.row)"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
  </div>

  <div>
    <!-- 数据源连接 -->
    <el-dialog v-model="dialogTableVisible" title="新建数据源连接">
      <el-form :model="form">

<!--        <el-form-item label="连接名称" :label-width="formLabelWidth">-->
<!--          <el-input v-model="form.content" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->

        <el-form-item label="数据库类型" :label-width="formLabelWidth">
          <el-select v-model="form.type" placeholder="请选择数据库类型">
            <el-option label="mysql" value="mysql"></el-option>
            <el-option label="sqlserver" value="mssql"></el-option>
            <el-option label="oracle" value="oracle"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="数据库地址" :label-width="formLabelWidth">
          <el-input v-model="form.host" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="端口" :label-width="formLabelWidth">
          <el-input v-model="form.port" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="数据库名" :label-width="formLabelWidth">
          <el-select v-model="form.database" clearable placeholder="Select">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <el-button type="primary" size="small" :loading="loadingButton" @click="reqAllDb">查询数据库</el-button>
<!--          <el-input v-model="form.content" autocomplete="off"></el-input>-->
        </el-form-item>
<!--        <span><el-button type="primary" :icon="Search" circle>查询数据库</el-button></span>-->

      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取消</el-button>
        <el-button type="primary" :loading="connectState" @click="connect" :disabled="addDisable">连接</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import TestTree from '../../components/tree/TestTree'

import { Search, Edit, Check, Message, Star, Delete } from '@element-plus/icons'
import {inject, provide, reactive, ref, watchEffect} from "vue";
import {getAllColumns, getAllDb, getAllTable, getAllViews, getConnect, getDbTreeData} from "@/api/api";
import {debounce, throttle} from "@/utils/tools";
import {ElMessage} from "element-plus";

const bus = inject('$bus')

let connectState = ref(false);
let multipleSelection = ref([]);
let tableName = ref('');
let dbName = ref('');
let loadingButton = ref(false);
let dialogTableVisible = ref(false);
let addDisable = ref(true);
let formLabelWidth = ref('120px')
//连接信息
let form = reactive({
  // connectName:'',
  type:'',
  host:'',
  port:'', //默认值: mysql- 3306  mssql- 1433
  username:'', //默认值: mysql- root  mssql- sa
  password:'',
  database:'', //(数据库名)默认值: 所有数据库- all 指定数据库- ‘dbname’
  table:''
})

const formReset = () => {

}

//字段表
let columns = reactive([]);
//table表格
let tables = reactive([]);
//dialog数据库下拉选
let options = reactive([]);

//监听组件事件决定是否弹出对话框
bus.on('dock-menu',e=>{
  let dockArr = e.path.split('>');
  if (dockArr.length>=3){
    if (dockArr[1] === '新建数据源连接'){
      // console.log(dockArr[2]);
      if (dockArr[2]==='sqlserver'){
        form.type = 'mssql';
      }else {
        form.type = dockArr[2];
      }
      console.log(form.type)
      // form.type = e.name;
      dialogTableVisible.value = true;
    }
  }
});

//设置默认值
watchEffect(()=>{
  if (form.type === 'mysql'){
    form.username = 'root'|| form.username;
    form.port = '3306'|| form.port;
    form.database = '';
    form.host = '';
    form.password = '';
    options.length = 0;
  }
  if(form.type === 'mssql'){
    form.username = 'sa' || form.username;
    form.port = '1433' || form.port;
    form.database = '';
    form.host = '';
    form.password = '';
    options.length = 0;
  }
})

//监听表单数据是否为空
watchEffect(()=>{
  addDisable.value = !((form.type !== '') && (form.host !== '') && (form.port !== '') && (form.username !== '') && (form.password !== '') && (form.database !== ''));
})

//监听table表格是否超选
watchEffect(()=>{
  if (multipleSelection.value.length >4){
    ElMessage.warning('最多只能选择4张表')
    multipleSelection.value.shift();
  }
})

//请求所有数据库
const reqAllDb = ()=>{
  loadingButton.value = true;
  options.length = 0;
  getAllDb(form).then(res=>{
    if (res.data.length !== 0){
      options.push(...res.data);
      // res.data.forEach((item)=>{
      //   options.push(item)
      // });
      ElMessage.success('获取数据库成功')
    }else {
      ElMessage.error('获取数据库失败')
    }
    loadingButton.value = false;
  }).catch(err=>{
     ElMessage.error('获取数据库失败，请检查连接参数');
    loadingButton.value = false;
  })
}

const treeData = reactive([]);
provide('treeData',treeData);
//连接到数据库 - 获取所有表
const connect = ()=>{
  dbName.value = form.database;
  tables.length = 0;
  columns.length = 0;
  connectState.value = true;
  getDbTreeData(form).then(res=>{
    treeData.push(...res.data);
    connectState.value = false;
    dialogTableVisible.value = false;
    // console.log(res.data)
  })

  // getAllViews(form).then(res=>{
  //
  // })

  // getAllTable(form).then(res=>{
  //   tables.push(...res.data);
  //   dialogTableVisible.value = false;
  // }).catch(err=>{
  //   ElMessage.error('获取数据表失败');
  // })

}

//获取指定表的字段
const getColumns = (index,row)=>{
  // console.log(row);
  columns.length = 0;
  tableName.value = row.tableName;
  form.table = row.tableName;

  getAllColumns(form).then(res=>{
    columns.push(...res.data);
    // console.log(res)
  }).catch(err=>{
    console.log(err)
  })

}

//多选框操作
const handleSelectionChange = (val)=>{
  multipleSelection.value = val
  console.log(multipleSelection.value)
}
const toggleSelection = (rows) => {
  if (rows) {
    rows.forEach((row) => {
      this.$refs.multipleTable.toggleRowSelection(row)
    })
  } else {
    this.$refs.multipleTable.clearSelection()
  }
}


</script>

<style scoped>

</style>
