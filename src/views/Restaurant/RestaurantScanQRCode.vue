<template>
  <Header></Header>
  <qrcode-stream @decode="onDecode"></qrcode-stream>
  <Footer></Footer>
</template>

<script>
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { QrcodeStream } from 'vue3-qrcode-reader'
import axios from "axios";
import {path} from "@/views/settings";


export default {
  name: "RestaurantScanQRCode",
  // for testing purpose
  created() {
    // alert("test")
    let obj = {
      orderId: 11,
      foodId: 22
    }
    axios.defaults.withCredentials = true
    axios.post(path +  '/order/update', obj)
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error)
        });
  },
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