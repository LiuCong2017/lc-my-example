import {getLogout, getUserInfo, login} from '@/api/api'
import {removeToken, setToken} from "@/utils/auth";
import {resetRouter} from "@/router";

const state = {
  token:'',
  name:'',
  roles:[],
  username:''
}

const mutations = {
  setToken(state,token){
    state.token = token;
  },
  setName(state,name){
    state.name = name
  },
  setRoles(state,roles){
    state.roles = roles
  },
  setUsername(state,username){
    state.username = username
  }
}

const actions = {

  login({commit},userInfo){
    const username = userInfo.username.trim()
    return new Promise((resolve,reject) =>{
      login({
        username: username,
        password: userInfo.password
      }).then(res=>{
        const data = res.data.data;
        commit('setToken',data.tokenValue);
        setToken(data.tokenValue);
        localStorage.removeItem('userinfo');
        resolve(res)
      }).catch(err=>{
        reject(err)
      })
    })
  },


  getInfo({commit},username){
    return new Promise((resolve,reject)=>{

      getUserInfo(username).then((res)=>{
        let response = res.data.data;
        const data = {
          roles: response.roles,
          name: response.name,
          username: response.username,
        }
        // //解构赋值
        const {roles,name,username} = data || {};
        if (!roles || roles.length <= 0){
            reject('角色必须为非空数组')
        }
        localStorage.setItem('userinfo',JSON.stringify(data));
        commit('setRoles',roles)
        commit('setName', name)
        commit('setUsername',username)
        resolve(data);
      }).catch(error=>{
        reject(error);
      })

    })
  },

  logout({commit}){
    commit('setToken','')
    commit('setRoles',[])
    removeToken();
    resetRouter();
    getLogout().then(res=>{
      console.log('服务端已登出')
    }).catch(err=>{
      console.log('服务端登出失败')
    })
  },

}

const getters = {
  token(state){
    return state.token
  },

  name(state){
    return state.name
  },

  username(state){
    return state.username
  },

  roles(state){
    return state.roles
  }

}

export const store =  {
    namespaced: true,
    getters,
    state,
    mutations,
    actions
}
