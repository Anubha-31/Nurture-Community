<template>
  <Header />

<div x-data="{ cartOpen: false , isOpen: false }" class="bg-white">

    <!-- Search Box -->    
    <header>
      <!-- <pre>{{ user }}</pre> -->
        <form method="POST" @submit.prevent="formSubmit">
        <div class="container mx-auto px-6 py-3">
            <!-- <div class="relative mt-6 max-w-lg mx-auto">
            <span class="absolute inset-y-0 left-0 pl-3 flex items-center">
                <svg class="h-5 w-5 text-gray-500" viewBox="0 0 24 24" fill="none">
                    <path d="M21 21L15 15M17 10C17 13.866 13.866 17 10 17C6.13401 17 3 13.866 3 10C3 6.13401 6.13401 3 10 3C13.866 3 17 6.13401 17 10Z" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </svg>
            </span>
                <input name="search" id="search" v-model="user.search"  class="w-full border rounded-md pl-10 pr-4 py-2 focus:border-blue-500 focus:outline-none focus:shadow-outline" type="text" placeholder="Enter ZIP code to search near by restaurants">
            </div> -->
             <div class="relative mt-6 max-w-lg mx-auto">
                  <label for="province" class="block text-sm font-medium text-gray-700">Province*</label>
                  <select name="province" id="province" v-model="user.province" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                    <option disabled value="">Please select one</option>
                    <option v-for="(province, index) in provinces" :key="index" :value="index">{{ province }}</option>
                  </select>
                </div>
                            <div class="relative mt-6 max-w-lg mx-auto">
                  <label for="city" class="block text-sm font-medium text-gray-700">City*</label>
                  <select name="city" id="city" v-model="user.city" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                    <option disabled value="">Please select one</option>
                  <option v-for="(city, index) in cities" :key="index" :value="city[0]">{{ city[0] }}</option>
                  </select>
                </div>
             <button type="submit" class="flex items-center m-auto w-24 mt-2 justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                  Search
                </button>
                 <button type="button" v-on:click="reset" class="flex items-center m-auto w-24 mt-2 justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                  Reset Filter
                </button>
        </div>
        </form>
    </header>

    <!-- Main Body Starts Here -->
    <main class="my-8">
        <div class="container mx-auto px-6">
            <h3 class="text-gray-700 text-2xl font-medium">Restaurants List</h3>
            <span class="mt-3 text-sm text-gray-500">{{length}} available</span>
            <div class="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 mt-6">

                <!-- Common restaurant card starts below -->
                <div v-for="content in contents" :key="content.id" class="w-full max-w-sm mx-auto rounded-md shadow-md overflow-hidden">
                    <div class="flex items-end justify-end h-56 w-full bg-cover image-holder">
                      <img class="img-fluid" :src="'data:image/jpg;base64,'+content.picture" alt="" />
                    <!-- <img id="qr" :src="qrImg" /> -->
                        <button class="p-2 rounded-full bg-blue-600 text-white mx-5 -mb-4 hover:bg-blue-500 focus:outline-none focus:bg-blue-500">
                            <svg class="h-5 w-5" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor"><path d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"></path></svg>
                        </button>
                    </div>
                    <div class="px-5 py-3">
                         <h3 class="text-gray-700 uppercase">{{ content.restaurant_name }}</h3>
                        <!-- <h2 class="text-gray-700 uppercase">{{ content.item_name }}</h2> -->
                        <!-- <span class="text-gray-400 mt-2">Quantity {{ content.number_of_packets}}</span><br> -->
                        <span class="text-gray-400 mt-2">Timings {{ content.opens_at}} - {{ content.closes_at}}</span>
                    </div>
                </div>
            <!-- End of card -->

            </div>
           
        </div>
    </main>
</div>





  <Footer />
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import axios from 'axios'
import {path} from './settings.js'
import canada from 'canada'
import _ from 'lodash'

export default {
  name: "RestaurantList",
  components: {
    Header,
    Footer,
  },
  data() {
        return{
    contents: null ,
    image:null,
    length: '0',
    user: {
        city: null,
        provinces: null,
      },
    }
  },
      created() {
    this.provinces = canada.provinces
  },
  watch: {
    'user.province': function (val) {
        this.cities = canada.cities.filter(city => {
          return city[1] === val
        })
    }
  },
  
  mounted () {
      axios.defaults.withCredentials = true
    axios.get(path+'/ListOfRestaurants', this.user)
      .then((response) => {
        
       this.contents = response.data
       this.length = response.data.length
         

      }, (error) => {
        console.log(error);
      });
  },

   methods: {
    formSubmit: function () {
    
     axios.defaults.withCredentials = true
      axios.post(path+'/ListOfRestaurantzip', this.user
      
      ).then((response) => {
        console.log(response);
         (this.contents = response.data)
       this.length = response.data.length
      
      }, (error) => {
        console.log(error);
      });
    },
    reset: function()
    {
      this.provinces = canada.provinces
      this.user.city=""
      this.user.province = ""
      this.user.cities=""


            axios.defaults.withCredentials = true
    axios.get(path+'/ListOfRestaurants', this.user)
      .then((response) => {
        console.log(response.data);
       (this.contents = response.data)
       this.length = response.data.length

      }, (error) => {
        console.log(error);
      });
      console.log(this.user.provinces)
    }
        
   },  
};
</script>

<style scoped>
</style>