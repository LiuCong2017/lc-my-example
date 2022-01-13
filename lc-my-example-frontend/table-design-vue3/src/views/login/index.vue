<template>
  <div>
    <navbar-component></navbar-component>
    <main>
      <section class="absolute w-full h-full">
        <div
            class="absolute top-0 w-full h-full bg-gray-900"
            style="background-size: 100%; background-repeat: no-repeat;"
        ></div>
        <div class="container mx-auto px-4 h-full">
          <div class="flex content-center items-center justify-center h-full">
            <div class="w-full lg:w-4/12 px-4">
              <div
                  class="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-gray-300 border-0"
              >
                <div class="rounded-t mb-0 px-6 py-6">
                  <div class="text-center mb-3">
                    <img class="mx-auto h-12 w-auto" src="../../assets/logo.png" alt="Workflow">
                    <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
                      xxx系统
                    </h2>
                  </div>
<!--                  <div class="btn-wrapper text-center">-->
<!--                    <button-->
<!--                        class="bg-white active:bg-gray-100 text-gray-800 font-normal px-4 py-2 rounded outline-none focus:outline-none mr-2 mb-1 uppercase shadow hover:shadow-md inline-flex items-center font-bold text-xs"-->
<!--                        type="button"-->
<!--                        style="transition: all 0.15s ease 0s;"-->
<!--                    >-->
<!--                      <img-->
<!--                          alt="..."-->
<!--                          class="w-5 mr-1"-->
<!--                          src="../../assets/img/github.svg"-->
<!--                      />Github</button-->
<!--                    ><button-->
<!--                      class="bg-white active:bg-gray-100 text-gray-800 font-normal px-4 py-2 rounded outline-none focus:outline-none mr-1 mb-1 uppercase shadow hover:shadow-md inline-flex items-center font-bold text-xs"-->
<!--                      type="button"-->
<!--                      style="transition: all 0.15s ease 0s;"-->
<!--                  >-->
<!--                    <img-->
<!--                        alt="..."-->
<!--                        class="w-5 mr-1"-->
<!--                        src="../../assets/img/google.svg"-->
<!--                    />Google-->
<!--                  </button>-->
<!--                  </div>-->
                  <hr class="mt-6 border-b-1 border-gray-400" />
                </div>
                <div class="flex-auto px-4 lg:px-10 py-10 pt-0">
<!--                  <div class="text-gray-500 text-center mb-3 font-bold">-->
<!--                    <small class="mt-2 text-center text-sm text-gray-600">-->
<!--                      没有账号？-->
<!--                      <a href="#" class="font-medium text-indigo-600 hover:text-indigo-500">-->
<!--                        注册账号试试-->
<!--                      </a>-->
<!--                    </small>-->
<!--                  </div>-->
                  <form>
                    <div class="relative w-full mb-3">
                      <label
                          class="block uppercase text-gray-700 text-lg font-bold mb-2"
                      >用户名</label
                      ><input
                        type="username"
                        class="border-0 px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full"
                        placeholder="用户名"
                        style="transition: all 0.15s ease 0s;"
                        autocomplete="off"
                        v-model="loginForm.username"
                    />
                    </div>
                    <div class="relative w-full mb-3">
                      <label
                          class="block uppercase text-gray-700 text-lg font-bold mb-2"
                      >密码</label
                      ><input
                        type="password"
                        class="border-0 px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded text-sm shadow focus:outline-none focus:ring w-full"
                        placeholder="密码"
                        style="transition: all 0.15s ease 0s;"
                        autocomplete="off"
                        v-model="loginForm.password"
                        @keyup.enter="handleLogin"
                    />
                    </div>
                    <div>
<!--                      <label class="inline-flex items-center cursor-pointer"-->
<!--                      ><input-->
<!--                          id="customCheckLogin"-->
<!--                          type="checkbox"-->
<!--                          class="form-checkbox border-0 rounded text-gray-800 ml-1 w-5 h-5"-->
<!--                          style="transition: all 0.15s ease 0s;"-->
<!--                      /><span class="ml-2 text-sm font-semibold text-gray-700"-->
<!--                      >记住我</span-->
<!--                      ></label-->
<!--                      >-->
                    </div>
                    <div class="text-center mt-6">
                      <button
                          class="bg-gray-900 text-white active:bg-gray-700 text-sm font-bold uppercase px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 w-full"
                          type="button"
                          style="transition: all 0.15s ease 0s;"
                          @click.prevent="handleLogin"
                      >
                        登录
                      </button>
                    </div>
                    <div class="mt-2 text-center text-sm text-black">
                      没有账号？
                      <a class="font-medium text-blue-700 text-indigo-600 hover:text-indigo-500">
                        <router-link to="/register">注册账号试试</router-link>
                      </a>
                    </div>
                  </form>
                </div>
              </div>

            </div>
          </div>
        </div>
        <footer-component></footer-component>
      </section>
    </main>
  </div>
</template>
<script setup>
import NavbarComponent from "./component/Navbar.vue";
import FooterComponent from "./component/Footer.vue";
import {reactive, watch} from 'vue';
import {useStore} from "vuex";
import {useRoute, useRouter} from "vue-router";
import {ElMessage} from "element-plus";

    const loginForm = reactive({
      username:'',
      password:''
    })

    const state = {
      redirect: undefined,
      otherQuery: {}
    }

    const store = useStore();
    const router = useRouter();
    const handleLogin = ()=>{
       store.dispatch('user/login',loginForm).then(res=>{
        if (res.data.code === 200){
          store.dispatch('user/getInfo',loginForm.username).then(res=>{
            // window.location.href = "http://192.168.3.86:8080";
            const curr = localStorage.getItem('preRoute')
            if (curr == null || curr === '/register'){
              router.push('/')
            }else {
              router.push({path:curr})
            }
          })
        }else {
          alert(res.message)
        }
      }).catch((err)=>{
         ElMessage.error('登录失败')
      })
    }

</script>
<style scoped>

</style>
