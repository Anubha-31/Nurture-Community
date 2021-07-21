<template>
  <button @click="qrgen">Generate QR Code</button>
  <qrcode-vue :value="value" :size="size" level="H" />
<!--  <qrcode-stream @decode="onDecode"></qrcode-stream>-->
</template>

<script>
import { QrcodeStream } from 'vue3-qrcode-reader'
import QrcodeVue from 'qrcode.vue'
export default {
  name: "QRTest",
  data() {
    return {
      value: 'https://example.com',
      size: 300,
    }
  },
  components: {
    QrcodeStream,
    QrcodeVue
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