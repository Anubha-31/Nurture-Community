<template>
  <div class="min-h-screen flex flex-col">
    <Header/>
    <Main class="flex-grow"></Main>
    <Footer />
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import Main from "@/components/Main.vue";
import Header from "@/components/Header";

export default {
  name: "Home",
  components: {
    Footer,
    Main,
    Header
  },
};
</script>
