import { createStore } from 'vuex'
import {store as user} from './modules/user'

export const store = createStore({
  modules: {
    user,
  }
})


