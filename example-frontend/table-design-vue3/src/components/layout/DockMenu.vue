<template>
<!--  dock="LEFT"-->
  <DockMenu
      :items="items"
      :on-selected="handleSelected"
      :theme="{
      // primary: '#b2b2b2',
      // secondary: '#e5e5e5',
      // // tertiary: '#008ecc',
      // tertiary: '#000',
      // textColor: '#000',
      textHoverColor: '#fff',

      primary: '#001B48',
      secondary: '#02457a',
      tertiary: '#018abe',
      textColor: '#fff'

      // primary: '#000'
    }"
  >
    <template #file>
      <img
          src="@/assets/svg/file.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #window>
      <img
          src="@/assets/svg/window-maximize.svg"
          alt="file"
          :style="style"
      ></template>
    <template #edit>
        <img
            src="@/assets/svg/edit.svg"
            alt="file"
            :style="style"
        >
    </template>

    <template #db>
      <img
          src="@/assets/svg/db.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #mysql>
      <img
          src="@/assets/svg/mysql.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #sqlserver>
      <img
          src="@/assets/svg/mssql.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #oracle>
      <img
          src="@/assets/svg/oracledb.svg"
          alt="file"
          :style="style"
      >
    </template>

    <template #setting>
      <img
          src="@/assets/svg/cog.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #copy>
    <img
        src="@/assets/svg/copy.svg"
        alt="file"
        :style="style"
    >
  </template>
    <template #cut>
    <img
        src="@/assets/svg/cut.svg"
        alt="file"
        :style="style"
    >
  </template>
    <template #download>
    <img
        src="@/assets/svg/download.svg"
        alt="file"
        :style="style"
    >
  </template>
    <template #folder>
    <img
        src="@/assets/svg/folder.svg"
        alt="file"
        :style="style"
    >
  </template>
    <template #folder-open>
      <img
          src="@/assets/svg/folder-open.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #hammer>
      <img
          src="@/assets/svg/hammer.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #info>
      <img
          src="@/assets/svg/info-circle.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #paint-brush>
      <img
          src="@/assets/svg/paint-brush.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #paste>
      <img
          src="@/assets/svg/paste.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #replace>
      <img
          src="@/assets/svg/replace.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #redo>
      <img
          src="@/assets/svg/redo.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #save>
      <img
          src="@/assets/svg/save.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #search>
      <img
          src="@/assets/svg/search.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #exit>
      <img
          src="@/assets/svg/sign-out-alt.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #close>
      <img
          src="@/assets/svg/times.svg"
          alt="file"
          :style="style"
      >
    </template>
    <template #undo>
      <img
          src="@/assets/svg/undo.svg"
          alt="file"
          :style="style"
      >
    </template>

  </DockMenu>
  <router-view/>
</template>
<script>
import { DockMenu } from "vue-dock-menu";
export default {
  components: {
    DockMenu
  },
}
</script>
<script setup>
import { DockMenu } from "vue-dock-menu";
import "vue-dock-menu/dist/vue-dock-menu.css";
import {inject, onBeforeUnmount, reactive} from "vue";

const bus = inject('$bus');

let items = reactive([
    {
      name: "新建",
      menu: [
        {
          name:'新建数据源连接',iconSlot: 'db',
          menu: [
            {name:'MySQL',iconSlot:'mysql'},
            {name:'SqlServer',iconSlot:'sqlserver'},
            {name:'oracle',iconSlot:'oracle'},
          ]
        },
        { name: "新建文件",iconSlot: 'file' },
        { name: "新建窗口",iconSlot: 'window' },
        { name: "打开文件",iconSlot: 'folder' },
        { isDivider: true },
        {
          name: "修改", iconSlot: 'edit',
          menu: [
            { name: "设置" },
            {
              name: "格式设置",
              menu: [
                {
                  name: "图标",
                  menu: [{ name: "表格" }, { name: "画笔" }],
                },
                {
                  name: "文字",
                },
              ],
            },
          ],
        },
        { name: "打开工作空间" },
        { isDivider: true },
        { name: "保存" ,iconSlot: 'save',},
        { name: "另存为", },
        { isDivider: true },
        { name: "关闭" ,iconSlot: 'close',},
        { name: "退出",iconSlot: 'exit', },
      ],
    },
    {
      name: "编辑",
      menu: [
        { name: "撤销",iconSlot: 'undo', },
        { name: "重做" ,iconSlot: 'redo',},
        { isDivider: true },
        { name: "剪切" ,iconSlot: 'cut',},
        { name: "复制" ,iconSlot: 'copy',},
        { name: "粘贴" ,iconSlot: 'paste',},
        { isDivider: true },
        { name: "查找" ,iconSlot: 'search',},
        { name: "替换" ,iconSlot: 'replace',},
      ],
    },
    {
      name: "窗口",
      menu: [
        { name: "浏览" },
        { name: "搜索" },
        { name: "运行",iconSlot: 'harmmer' },
        { isDivider: true },
        {
          name: "外观",
          menu: [{ name: "全屏" }, { name: "自适应" }],
        },
      ],
    },
    {
      name: "帮助",
      menu: [{ name: "关于",iconSlot: 'info' }, { name: "检查更新" }],
    },
  ]);
const handleSelected = (params)=>{
  console.log(params);
  bus.emit('dock-menu',params);
}



// onBeforeUnmount(()=>{
//   bus.all.clear();
//   // bus.off('*',sayHi);
// })

</script>

<!--<script>-->
<!--import { DockMenu } from "vue-dock-menu";-->
<!--import "vue-dock-menu/dist/vue-dock-menu.css";-->

<!--export default {-->
<!--  name: "App",-->
<!--  components: {-->
<!--    DockMenu,-->
<!--  },-->
<!--  setup() {-->
<!--    const handleSelected = (data) => {-->

<!--      console.log(data);-->
<!--      // console.log('&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;')-->
<!--      // console.log(curr)-->
<!--    };-->

<!--    return {-->
<!--      handleSelected,-->
<!--    };-->
<!--  },-->
<!--  data() {-->
<!--    return -->
<!--  },-->
<!--};-->
<!--</script>-->

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
