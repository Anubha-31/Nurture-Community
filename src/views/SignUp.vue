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
        <p class="mt-5">Please provide the information below to sign up as a user</p>
      </div>

      <div class="mt-5 md:mt-10 md:w-1/2 md:mx-auto">
        <form method="POST" @submit.prevent="formSubmit">
          <div class="shadow overflow-hidden rounded-lg border border-gray-200">
            <div class="px-4 py-5 bg-white sm:p-6">
              <div class="grid grid-cols-6 gap-6">

                <div class="col-span-6">
                  <label for="user_type" class="block text-sm font-medium text-gray-700">Sign Up As</label>
                  <select id="user_type" name="user_type" autocomplete="user_type" v-model="user.user_type" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    <option value="restaurant">Restaurant Representative</option>
                    <option value="client">Client</option>
                  </select>
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="first_name" class="block text-sm font-medium text-gray-700">First name</label>
                  <input type="text" name="first_name" id="first_name" autocomplete="given-name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="last_name" class="block text-sm font-medium text-gray-700">Last name</label>
                  <input type="text" name="last_name" id="last_name" autocomplete="family-name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6">
                  <label for="email_address" class="block text-sm font-medium text-gray-700">Email address</label>
                  <input type="text" name="email_address" id="email_address" autocomplete="email" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3" v-if="user.user_type === 'restaurant'">
                  <label for="restaurant_name" class="block text-sm font-medium text-gray-700">Restaurant's Name</label>
                  <input type="text" name="restaurant_name" id="restaurant_name" autocomplete="restaurant-name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3" v-if="user.user_type === 'restaurant'">
                  <label for="license_number" class="block text-sm font-medium text-gray-700">License Number</label>
                  <input type="text" name="license_number" id="license_number" autocomplete="license-number" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6">
                  <label for="phone_number" class="block text-sm font-medium text-gray-700">Phone Number</label>
                  <div class="flex space-x-1">
                    <input type="text" value="+1" disabled class="w-1/5 mt-1 p-2 bg-gray-100 block shadow-sm sm:text-sm border border-gray-300 rounded-md">
                    <input type="number" name="phone_number" id="phone_number" class="w-4/5 mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                  </div>
                </div>

                <div class="col-span-6">
                  <label for="address" class="block text-sm font-medium text-gray-700">Address</label>
                  <textarea name="address" id="address" cols="30" rows="4" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"></textarea>
                </div>

                <div class="col-span-6">
                  <label for="postal_code" class="block text-sm font-medium text-gray-700">ZIP / Postal</label>
                  <input type="text" name="postal_code" id="postal_code" autocomplete="postal-code" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>
              </div>
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

export default {
  name: "SignUp",
  components: {Footer},
  data() {
    return {
      user: {
        user_type: "restaurant",
        first_name: "",
        last_name: "",
        email: "",
        restaurant_name: "",
        license_number: "",
        phone: "",
        address: "",
        zip: ""
      }
    }
  },
  methods: {
    formSubmit: function () {
      alert('foobar');
      // axios.post('https://jsonplaceholder.typicode.com/posts', this.user)
      // .then((response) => {
      //   console.log(response);
      // }, (error) => {
      //   console.log(error);
      // });
    }
  }
}
</script>

<style scoped>

</style>