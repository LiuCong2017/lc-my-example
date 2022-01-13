<template>
  <div>
    <NuxtLink to="/">
      <h1 class="m-4 hover:underline">Back to Home</h1>
    </NuxtLink>
    <quote/><!-- A <quote /> component should be added here later on -->
    <article class="m-4 md:w-1/2 lg:w-1/3">
      <h2 class="mb-2 capitalize text-2xl font-semibold">{{ post.title }}</h2>
      <p>{{ post.body }}</p>
    </article>
  </div>
</template>

<script>
export default {
  name: "post-[id]",
  layout: "blog"
}
</script>

<script setup>
  import Quote from "../components/quote";

  const route = useRoute()
  const {data: post} = await useFetch(`https://jsonplaceholder.typicode.com/posts/${route.params.id}`, { pick: ['title', 'body'] })
</script>
