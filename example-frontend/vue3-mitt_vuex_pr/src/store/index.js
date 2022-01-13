import {createStore} from "vuex";

const store = createStore({
    state(){
        return{
            count:'',
            fatherMsg:'',
            sonMsg:'',
            fatherMsgAsync:''
        }
    },
    mutations:{
        increment(state,value){
          state.count = value++
        },
        sayFather(state,value){
            state.fatherMsg = value
        },
        saySon(state,value){
            state.sonMsg = value
        },
        sayAsyncFather(state,value){
            state.fatherMsgAsync = value
        }
    },
    actions:{
        incrementAction({commit},payload){
           return new Promise((resolve)=>{
              setTimeout((res)=>{
                  resolve(res);
              },2000)
          }).then(res=>{
              commit('increment',res)
          })
        },

        asyncSayFather({commit},payload){
            return new Promise((resolve)=>{
                setTimeout(()=>{
                    resolve(payload)
                },2000)
            }).then(res=>{
                commit('sayAsyncFather',res)
            })
        },

    }

})

export default store;
