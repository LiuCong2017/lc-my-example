/**
 asyncData()：
 只能在页面中使用，即pages目录下的.vue文件中，他会阻塞页面的加载，所以不能请求很大的数据量或者很多同步接口；
 不能使用this关键字；
 不能被调用，只在页面加载时，自动执行；
 */

// @ts-ignore
import { defineComponent } from "@vue/composition-api";
// @ts-ignore
import { SERVER_URL } from "~/assets/request";

export default defineComponent({
  name: "Portfolio",

  scrollToTop: true, // 这个配置会在加载页面时自动滚动到页面顶部

  // async asyncData({app: {$axios}}){ // asyncData()函数中不能使用this关键字，所以这里通过入参方式引入全局配置的一些参数，这里需要axios
  //   let [datas, categories] = await Promise.all([
  //     await $axios.$get(SERVER_URL.portfolio.concat("?page=0&size=10")),
  //     await $axios.$get(SERVER_URL.category.concat("?page=0&size=5")),
  //   ]);
  //   return {datas, categories};
  // },

  //优化

  async asyncData({ app: { $axios } }) {
    const [
      heroDatas,
      featuredDatas,
      topDatas,
      listDatas,
      recommendDatas,
    ] = await Promise.all([
      await $axios.$get(SERVER_URL.posts.concat("?page=0&size=3")),
      await $axios.$get(SERVER_URL.posts.concat("?page=1&size=4")),
      await $axios.$get(SERVER_URL.posts.concat("?page=0&size=3&order=viewed")),
      await $axios.$get(SERVER_URL.posts.concat("?page=0&size=10&order=likes")),
      await $axios.$get(SERVER_URL.posts.concat("?page=0&size=6&order=viewed")),
    ]);
    return { heroDatas, featuredDatas, topDatas, listDatas, recommendDatas };
  },

  data() {
    return {
      code: "",
      datas: [],
    };
  },

  methods: {
    retrieve(code: string) {
      this.code = code;
      this.$axios
        .get(SERVER_URL.portfolio.concat("?page=0&size=12&category=", code))
        .then((res) => (this.datas = res.data));
    },
  },

  head() {
    const title = "Portfolio - Leafage";
    const description =
      "Leafage的作品集，包含旅行记录、生活分享等资源信息，提供原创、优质、完整内容";
    return {
      title,
      meta: [
        { hid: "description", name: "description", content: description },
        // Open Graph
        { hid: "og:title", property: "og:title", content: title },
        {
          hid: "og:description",
          property: "og:description",
          content: description,
        },
        // Twitter Card
        { hid: "twitter:title", name: "twitter:title", content: title },
        {
          hid: "twitter:description",
          name: "twitter:description",
          content: description,
        },
      ],
    };
  },



})
