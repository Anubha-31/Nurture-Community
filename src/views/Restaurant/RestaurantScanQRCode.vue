<template>
  <h1>Test</h1>
  <qrcode-stream @decode="onDecode"></qrcode-stream>
</template>

<script>
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
    QrcodeStream
  },
}
</script>

<style scoped>

</style>