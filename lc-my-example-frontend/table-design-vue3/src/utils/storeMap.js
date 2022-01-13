import {computed} from "vue";
import {useStore} from "vuex";

const store = useStore

export function map(){
    const name = computed(()=>{
        return store.state.name
    })

}
