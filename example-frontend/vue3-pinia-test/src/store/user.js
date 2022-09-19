import {defineStore} from "pinia";

export const useUserStore = defineStore('user',{
    state: ()=>({
        username: '',
    }),
    /**
     * This is also be okay
     */
    // state: ()=>{
    //     return{
    //         username:'',
    //     }
    // },

    actions:{
        M_name(name){
            this.$patch((state)=>{
                state.username = name
            })
        },
    }

})