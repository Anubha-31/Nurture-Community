<template>
  <!-- <Header user_type="customer"></Header> -->
  <HeaderCustomer/>
    <div class="flex justify-center items-center md:min-h-screen">
      <div class="pt-10 pb-48 md:-mt-56">
        <qrcode-vue :value="value" :size="size" level="H" />
      </div>
    </div>
  <Footer/>
</template>

<script>
import HeaderCustomer from "@/components/HeaderCustomer";
import Footer from "@/components/Footer";
import { QrcodeStream } from 'vue3-qrcode-reader'
import QrcodeVue from 'qrcode.vue'
export default {
  name: "CustomerQRCode",
  props: {
    orderId: Number,
    foodId: Number,
  },
  data() {
    return {
      value: `{
        "orderId": ${this.orderId},
        "foodId": ${this.foodId}
      }`,
      size: 300,
    }
  },
  components: {
    HeaderCustomer,
    Footer,
    QrcodeStream,
    QrcodeVue
  }
}
</script>

<style scoped>

</style>