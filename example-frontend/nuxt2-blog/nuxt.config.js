export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'lc-blog',
    htmlAttrs: {
      lang: 'en'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' },
      // 这里可以添加网站验证码信息
      // { name: 'google-site-verification', content: 'xxx' },
      // 实测百度无法通过验证，此问题还没解决
      // { name: 'baidu-site-verification', content: 'code-xxx' },
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    '~/node_modules/highlight.js/styles/github.css' // 例如：引入highlight.js的github样式
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '~/plugins/mock',
    '~/plugins/axios'
  ],

  // 自动引入组件 (https://go.nuxtjs.dev/config-components)
  components: true, // nuxtjs 2.15版本之前的方式
  // 2.15版本及之后使用此方式
  // components: [
  //   '~/components/global' // 扫描此路径，global目录下的.vue文件，作为组件，可自动引入，不需要在使用的时候，手动import
  // ],

  // 用于开发和构建的modules (recommended) (https://go.nuxtjs.dev/config-modules)
  buildModules: [
    // https://go.nuxtjs.dev/typescript
    '@nuxt/typescript-build',
    // https://go.nuxtjs.dev/tailwindcss
    '@nuxtjs/tailwindcss',
  ],

  // 工具module (https://go.nuxtjs.dev/config-modules)
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
    // https://go.nuxtjs.dev/pwa
    '@nuxtjs/pwa',
  ],

  // nuxt 加载进度条配置 (https://zh.nuxtjs.org/api/configuration-loading)
  loading: {
    color: 'black'
  },

  // 如果添加了@nuxt/axios则会需要此配置来覆盖默认的一些配置 (https://go.nuxtjs.dev/config-axios)
  axios: {
    https: true,
    progress: true, // 是否显示加载进度条
    credentials: true, // 请求携带cookie
    baseURL: 'https://www.abeille.top/api',
    proxy: true // 请求代理，开发中跨域问题解决方法
  },

  // 打包配置 (https://go.nuxtjs.dev/config-build)
  build: {
    // 例如：添加css分割配置
    extractCSS: true,
  },

  markdownit: {
    preset: 'default',
    linkify: true,
    breaks: true,
    use: [
      'markdown-it-div',
      'markdown-it-highlightjs',
      'markdown-it-mark',
      'markdown-it-deflist'
    ],
    runtime: true // Support `$md()`
  },

}
