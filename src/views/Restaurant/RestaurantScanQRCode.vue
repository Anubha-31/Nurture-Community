<template>
  <HeaderRestaurants></HeaderRestaurants>
  <h1 class="text-center text-lg md:text-3xl mb-5 md:mb-10">Place the QR code in front of the Camera</h1>
  <div class="w-full md:w-1/4 mx-auto">
    <qrcode-stream @decode="onDecode"></qrcode-stream>
  </div>
  <q-r-loading-modal v-if="showLoadingModal"></q-r-loading-modal>
  <Footer></Footer>
</template>

<script>
import HeaderRestaurants from "../../components/HeaderRestaurants";
import Footer from "../../components/Footer";
import { QrcodeStream } from 'vue3-qrcode-reader'
import axios from "axios";
import {path} from "@/views/settings";
import QRLoadingModal from "../../components/Modals/QRLoadingModal";


export default {
  name: "RestaurantScanQRCode",
  data() {
    return {
      showLoadingModal: false
    }
  },
  // for testing purpose
  created() {
    // alert("test")
    // let obj = {
    //   orderId: 11,
    //   foodId: 22
    // }

  },
  methods: {
    onDecode: function (result) {
      this.showLoadingModal = true
      let obj = JSON.parse(result)
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
      })
      .then(response => {
        console.log(response)
        this.showLoadingModal = false
        window.location.href = "/restaurant/food-items";
      })
      .catch(error => {
        console.log(error)
      });

      // console.log(obj)
    },
  },
  components: {
    HeaderRestaurants,
    Footer,
    QrcodeStream,
    QRLoadingModal
  },
}
</script>

<style scoped>

</style>