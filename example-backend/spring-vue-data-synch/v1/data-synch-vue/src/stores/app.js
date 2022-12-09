import {defineStore} from "pinia";

export const useAppStore = defineStore('app',{
    state: ()=>{
        return{
            info:[],
        }
    },
    actions:{
        changeInfo(data){
            this.$patch((state)=>{
                state.info = data
            })
        },
    }
})