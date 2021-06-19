<template>
  <Header/>
  <Main />
  <Footer />
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
