<template>
  <Header user_type="customer"></Header>
    <div class="flex justify-center items-center md:min-h-screen">
      <div class="pt-10 pb-48 md:-mt-56">
        <qrcode-vue :value="value" :size="size" level="H" />
      </div>
    </div>
  <Footer/>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import { QrcodeStream } from 'vue3-qrcode-reader'
import QrcodeVue from 'qrcode.vue'
export default {
  name: "CustomerQRCode",
  data() {
    return {
      value: '{"id":"1", "foodID":2, "emailaddress":"rr@yahoo.com"}',
      size: 300,
    }
  },
  components: {
    Header,
    Footer,
    QrcodeStream,
    QrcodeVue
  }
}
</script>

<style scoped>

</style>