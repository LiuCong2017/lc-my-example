/**
 fetch()：
 可以在组件中使用，也可以在页面中使用，即在components目录和pages目录下的.vue文件中都可以；
 可以使用this关键字；
 可以重复调用，即在组件中直接调用fetch()来重新获取数据
 */

/**
 使用fetch()会有一个$fetchState来判断fetch()执行情况，可以根据不同情况处理不同的页面逻辑
 <div>
   <p v-if="$fetchState.pending">Fetching mountains...</p>
   <p v-else-if="$fetchState.error">An error occurred :(</p>
   <div v-else class="mb-12">
   </div>
 </div>
 */

// @ts-ignore
import {defineComponent} from '@vue/composition-api';
// @ts-ignore
import {SERVER_URL} from '~/assets/request';

export default defineComponent({
  name: "Main",

  async fetch(){
    this.datas = await this.$axios
      .get(SERVER_URL.posts.concat("?page=0&size=10&order=",this.order))
      .then((res)=>res.data);
  },

  data(){
    return {
      datas: [],
      orders: "likes",
    };
  },

  methods: {
    retrieve(order: string){
      this.order = order;
      this.$fetch(); // 这里调用fetch()函数，复用接口请求
    }
  }


})
