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

    axios({
      url: path + '/order/update'+this.id,
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Access-Control-Allow-Origin": "*",
        "Content-type": "application/json",
      },
      data : obj
    }).catch(error => {
          console.log(error)
        });
  },
  methods: {
    onDecode: function (result) {
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