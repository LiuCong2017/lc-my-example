<template>
  <footer class="absolute w-full bottom-0 bg-gray-900 pb-6">
    <div class="container mx-auto px-4">
      <hr class="mb-6 border-b-1 border-gray-700" />
      <div
        class="flex flex-wrap items-center md:justify-between justify-center"
      >
        <div class="w-full md:w-4/12 px-4">
          <div class="text-sm text-white font-semibold py-1">
            Copyright © {{date}}
            <a
              href="https://www.creative-tim.com"
              class="text-white hover:text-gray-400 text-sm font-semibold py-1"
              >xxx有限公司</a
            >
          </div>
        </div>
        <div class="w-full md:w-8/12 px-4">
          <ul
            class="flex flex-wrap list-none md:justify-end  justify-center"
          >
<!--            <li>-->
<!--              <a-->
<!--                href="http://www.excellentcare.com.cn/"-->
<!--                class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3"-->
<!--                >公司官网</a-->
<!--              >-->
<!--            </li>-->
            <li>
              <a
                href="#"
                class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3"
                >关于系统</a
              >
            </li>
            <li>
              <a
                href="#"
                class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3"
                >使用指南</a
              >
            </li>
            <li>
              <a
                href="#"
                class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3"
                >帮助</a
              >
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  data() {
    return {
      date: new Date().getFullYear()
    }
  }
}
</script>
