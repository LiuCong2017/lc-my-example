<template>
  <p
    class="my-4 leading-relaxed tracking-wide markdown-body"
    v-html="$md.render(data.content)"
  ></p>
</template>

<script>
export default {
  name: "Markdown-it-Parse"
}
</script>

<style scoped>

</style>
