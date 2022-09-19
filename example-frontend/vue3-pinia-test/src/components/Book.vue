<template>
  <div>
    book-name: {{bookName}} <br/>
    author: {{username}} <br/>
    price: {{getAddPrice}}
  </div>
  <div>
    <input type="button" @click="changePrice" value="Change"/>
  </div>
</template>

<script setup>
  import {useBookStore} from "../store/book.js";
  import { storeToRefs } from 'pinia'
  import {useUserStore} from "../store/user.js";

  const useBook = useBookStore()
  const {bookName,price,getAddPrice} = storeToRefs(useBook)

  const useUser = useUserStore();
  const {username} = storeToRefs(useUser)
  const changePrice = ()=>{
    return useBook.M_price();
  }

</script>
