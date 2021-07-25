<template>
  <qrcode-stream @decode="onDecode"></qrcode-stream>
</template>

<script>
import { QrcodeStream } from 'vue3-qrcode-reader'

export default {
  name: "QRReader",
  data() {
    return {
      value: "test",
      size: 300,
    }
  },
  components: {
    QrcodeStream
  },
  methods: {
    onDecode: function (result) {
      // console.log(result)
      let obj = JSON.parse(result)
      console.log(obj.id)
    }
  }
}
</script>

<style scoped>

</style>