<template>
  <Header></Header>
  <qrcode-stream @decode="onDecode"></qrcode-stream>
  <Footer></Footer>
</template>

<script>
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { QrcodeStream } from 'vue3-qrcode-reader'

export default {
  name: "RestaurantScanQRCode",
  methods: {
    onDecode: function (result) {
      // console.log(result)
      let obj = JSON.parse(result)
      console.log(obj.id)
    },
  },
  components: {
    Header,
    Footer,
    QrcodeStream
  },
}
</script>

<style scoped>

</style>