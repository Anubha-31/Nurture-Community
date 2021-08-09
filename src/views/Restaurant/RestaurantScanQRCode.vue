<template>
  <HeaderRestaurants></HeaderRestaurants>
  <qrcode-stream @decode="onDecode"></qrcode-stream>
  <Footer></Footer>
</template>

<script>
import HeaderRestaurants from "../../components/HeaderRestaurants";
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
    const token = localStorage.getItem("token");
    axios({
      url: path + '/order/update',
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
    HeaderRestaurants,
    Footer,
    QrcodeStream
  },
}
</script>

<style scoped>

</style>