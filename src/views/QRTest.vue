<template>
<!--  <qrcode-stream @decode="onDecode"></qrcode-stream>-->
</template>

<script>
import { QrcodeStream } from 'vue3-qrcode-reader'
export default {
  name: "QRTest",
  components: {
    QrcodeStream
  },
  methods: {
    onDecode: function (result) {
      console.log(result)
    }
  }
}
</script>

<style scoped>

</style>