<template>
<!--  <button @click="qrgen">Generate QR Code</button>-->
  <div class="flex justify-center mt-48">
    <qrcode-vue :value="value" :size="size" level="H" />
  </div>
<!--  <qrcode-stream @decode="onDecode"></qrcode-stream>-->
</template>

<script>
import { QrcodeStream } from 'vue3-qrcode-reader'
import QrcodeVue from 'qrcode.vue'
export default {
  name: "QRTest",
  data() {
    return {
      value: '{"id":"1", "foodID":2, "emailaddress":"rr@yahoo.com"}',
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
    },
    qrgen: function () {
      this.value = "matha"
    }
  }
}
</script>

<style scoped>

</style>