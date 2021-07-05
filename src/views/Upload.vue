<template>
  <div>
    <header class="text-gray-600 body-font">
      <div class="container mx-auto flex flex-wrap pt-5 px-5 flex-col md:flex-row items-center">
        <router-link to="/" class="flex title-font font-medium items-center text-gray-900 mb-4 md:mb-0">
          <img src="..\assets\images\logo.png" alt="" class="w-36">
        </router-link>
        <nav class=" md:mr-auto md:ml-4 md:py-1 md:pl-4 md:border-l md:border-gray-400 flex flex-wrap items-center text-base justify-center">
          <a class="mr-5 hover:text-gray-900 cursor-pointer">About Us</a>
          <a class="mr-5 hover:text-gray-900 cursor-pointer">Contact Us</a>
        </nav>
      </div>
    </header>
    <div class="container mx-auto px-6">
      <div class="text-center">
        <h1 class="title-font sm:text-4xl text-3xl mb-4 font-medium text-gray-900">Sign Up</h1>
        <p v-if="fileData">{{ fileData }}</p>
      </div>
      <div class="mt-5 md:mt-10 md:w-1/2 md:mx-auto">
        <form method="POST" @submit.prevent="formSubmit">
          <div class="shadow overflow-hidden rounded-lg border border-gray-200">
            <div class="col-span-6">
              <input type="file" name="cover_image" accept="image/png, image/gif, image/jpeg" id="cover_image" ref="cover_image" @change="onFileUpload" autocomplete="restaurant-name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
            </div>
            <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
              <button type="submit" class="inline-flex w-full justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                Sign Up
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <Footer/>
</template>

<script>
import axios from 'axios'
import Footer from "@/components/Footer";
import {path} from './settings.js'

export default {
  name: "SignUp",
  components: {Footer},
  data() {
    return {
      formData: null,
      fileData: null,
      errors: []
    }
  },
  methods: {
    formSubmit: function () {
      this.errors = [];
        axios.defaults.withCredentials = true
        axios({
          url: path + '/upload',
          method: 'POST',
          data: this.formData,
          headers: {
            Accept: 'application/json',
            'Content-type': `multipart/form-data;boundary=--`,
          }
        }).then((response) => {
          if(response.status === 200) {
            this.fileData = response
          }
        }, (error) => {
          console.log(error);
        });
    },
    onFileUpload: function (event) {
      let t = this
      if(event.target.files[0].size > 5000000) {
        alert("Please upload a file less than 5MB")
        t.$refs.cover_image.value = null;
      } else {
        console.log(event.target.files[0])
        t.formData = new FormData();
        t.formData.append("cover_image", event.target.files[0])
        t.formData.append("model", JSON.stringify({ x: 5, y: 6 }))
      }
    }
  }
}
</script>

<style scoped>

</style>