import {defineStore} from "pinia";
import {useBookStore} from "./book.js";

export const useUserStore = defineStore('user',{
    state: ()=>({
        username: '',
        jobId:0,
    }),
    /**
     * This is also be okay
     */
    // state: ()=>{
    //     return{
    //         username:'',
    //     }
    // },

    getters:{
        getJobId:(state)=>{
            const useBook = useBookStore()
            return state.jobId + useBook.getAddPrice;
        }
    },

    actions:{
        M_name(name){
            this.$patch((state)=>{
                state.username = name
            })
        },
    }

})