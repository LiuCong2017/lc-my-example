<template>
  <div>
    book-name: {{bookName}} <br/>
    author: {{username}} <br/>
    price: {{price}}
  </div>
</template>

<script setup>
  import {useBookStore} from "../store/book.js";
  import { storeToRefs } from 'pinia'
  import {useUserStore} from "../store/user.js";

  const useBook = useBookStore()
  const {bookName,price} = storeToRefs(useBook)

  const useUser = useUserStore();
  const {username} = storeToRefs(useUser)

</script>
