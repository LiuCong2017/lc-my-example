import {defineStore} from "pinia";

export const useBookStore = defineStore('book',{
    state: ()=>{
        return{
            bookName:'Harry Potter',
            price:30,
        }
    },
    getters: {
        getAddPrice: (state) => {
            return state.price+10;
        },
    },
    actions:{
        M_price(){
            this.$patch((state)=>{
                state.price = 100
            })
        }
    },
})