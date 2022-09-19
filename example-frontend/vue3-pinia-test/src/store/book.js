import {defineStore} from "pinia";

export const useBookStore = defineStore('book',{
    state: ()=>{
        return{
            bookName:'Harry Potter',
            price:30,
        }
    },

    actions:{

    },
})