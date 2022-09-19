import {defineStore} from "pinia";

export const useBookStore = defineStore('book',{
    state: ()=>{
        return{
            bookName:'Harry Potter',
            price:0,
        }
    },
    getters: {
        getAddPrice: (state) => state.price+10,
    },
    actions:{
        M_price(){
            this.$patch((state)=>{
                state.price = 100
            })
        },

        async registerUser(login, password) {
            console.log(password)
            // try {
            //     this.userData = await api.post({ login, password })
            //     showTooltip(`Welcome back ${this.userData.name}!`)
            // } catch (error) {
            //     showTooltip(error)
            //     // 让表单组件显示错误
            //     return error
            // }
        },

        async fetchUserPreferences(preferences) {
            // const auth = useAuthStore()
            // if (auth.isAuthenticated) {
            //     this.preferences = await fetchPreferences()
            // } else {
            //     throw new Error('User must be authenticated')
            // }
        },
    },
})