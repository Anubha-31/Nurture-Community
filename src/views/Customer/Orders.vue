<template>
  <Header user_type="customer"></Header>
  <div class="leading-normal tracking-normal text-white gradient" style="font-family: 'Source Sans Pro', sans-serif;">
    <h1 class="w-full my-2 text-5xl font-bold leading-tight text-center text-gray-800">
     My Orders
    </h1>
    <div class="w-full mb-4">
      <div class="h-1 mx-auto gradient w-64 opacity-25 my-0 py-0 rounded-t"></div>
    </div>
    <p class="text-center mt-2 text-gray-800">List of food items that you have ordered from the restaurants</p>
<!--    <pre>{{foodItems}}</pre>-->
    <div class="overflow-x-auto px-2 md:px-0">
      <div class="min-w-screen min-h-screen flex justify-center font-sans overflow-x-auto md:overflow-hidden">
        <div class="w-full lg:w-4/6">
          <div class="shadow-md rounded my-6">
            <div class="space-y-5">
              <div class="bg-aqua-3 rounded-lg" v-for="(item, index) in sortedFoodItems" :key="index">

                <div class="flex flex-row" data-v-2a66f80c="">
                  <div class="relative w-2/5 md:w-auto mb-3" data-v-2a66f80c="">
                    <img src="https://www.indianhealthyrecipes.com/wp-content/uploads/2018/07/chicken-fry.jpg" alt="aji" class="w-40 h-40 md:w-56 md:h-40 object-cover rounded-2xl" data-v-2a66f80c="">
                  </div>

                  <div class="w-3/5 md:w-full ml-5 py-2 justify-evenly" data-v-2a66f80c="">
                    <div class="flex items-center justify-between sm:mt-2" data-v-2a66f80c="">
                      <div class="flex items-center" data-v-2a66f80c="">
                        <div class="flex flex-col" data-v-2a66f80c="">
                          <div class="w-full flex-none text-lg text-gray-200 font-bold leading-none" data-v-2a66f80c="">{{ item.item_name }}</div>
                          <div class="flex-auto text-gray-400 my-1" data-v-2a66f80c=""><span class="mr-3 " data-v-2a66f80c="">{{ item.item_description }}</span><span class="mr-3 border-r border-gray-600  max-h-0" data-v-2a66f80c=""></span><span data-v-2a66f80c="">{{ item.restaurant_name }}</span></div>
                        </div>
                      </div>
                    </div>
                    <div class="flex pt-2  text-sm text-gray-400" data-v-2a66f80c="">
                      <div class="flex-1 inline-flex items-center" data-v-2a66f80c="">
                        <i class="far fa-clock"></i>
                        <p class="pl-1" data-v-2a66f80c="">Ordered at: {{ item.created_at }}</p>
                      </div>
                      <div class="flex-1 inline-flex items-center" data-v-2a66f80c="">
                        <i class="far fa-clock"></i>
                        <p class="pl-1" data-v-2a66f80c="">Picked at: {{ item.picked_at }}</p>
                      </div>
                      <router-link :to="{ name: 'CustomerQRCode', params: { orderId: item.order_id, foodId: item.food_id }}" class="title-font font-medium items-center text-gray-900 md:mb-0">
                        <button class="hidden md:flex md:flex-no-shrink bg-yellow-500 hover:bg-yellow-400 px-5 ml-4 py-2 text-xs shadow-sm hover:shadow-lg font-medium tracking-wider border-2 border-yellow-500 hover:border-yellow-400 text-white rounded-full transition ease-in duration-300 mr-4">View QR Code</button>
                      </router-link>
                    </div>
                    <button class="flex-no-shrink md:hidden bg-yellow-500 hover:bg-yellow-400 px-5 ml-4 py-2 text-xs shadow-sm hover:shadow-lg font-medium tracking-wider border-2 border-yellow-500 hover:border-yellow-400 text-white rounded-full transition ease-in duration-300">View QR Code</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <p class="flex justify-end space-x-2 mt-1">
            <button
                @click="prevPage"
                :class="currentPage > 1 ? 'bg-aqua-3 hover:bg-aqua-1' : 'bg-gray-200 cursor-not-allowed'"
                class="inline-flex justify-center items-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white focus:outline-none focus:ring-2 focus:ring-offset-2">
              <i class="fas fa-angle-double-left pr-2 mt-1"></i>
            </button>
            <button
                @click="nextPage"
                :class="(currentPage*pageSize) < foodItems.length ? 'bg-aqua-3 hover:bg-aqua-1' : 'bg-gray-200 cursor-not-allowed'"
                class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white  focus:outline-none focus:ring-2 focus:ring-offset-2">
              <i class="fas fa-angle-double-right pl-2 mt-1"></i></button>
          </p>
        </div>
      </div>
    </div>
    <div class="relative -mt-24 md:-mt-56">
      <svg viewBox="0 0 1428 174" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
          <g transform="translate(-2.000000, 44.000000)" fill="#FFFFFF" fill-rule="nonzero">
            <path d="M0,0 C90.7283404,0.927527913 147.912752,27.187927 291.910178,59.9119003 C387.908462,81.7278826 543.605069,89.334785 759,82.7326078 C469.336065,156.254352 216.336065,153.6679 0,74.9732496" opacity="0.100000001"></path>
            <path
                d="M100,104.708498 C277.413333,72.2345949 426.147877,52.5246657 546.203633,45.5787101 C666.259389,38.6327546 810.524845,41.7979068 979,55.0741668 C931.069965,56.122511 810.303266,74.8455141 616.699903,111.243176 C423.096539,147.640838 250.863238,145.462612 100,104.708498 Z"
                opacity="0.100000001"
            ></path>
            <path d="M1046,51.6521276 C1130.83045,29.328812 1279.08318,17.607883 1439,40.1656806 L1439,120 C1271.17211,77.9435312 1140.17211,55.1609071 1046,51.6521276 Z" id="Path-4" opacity="0.200000003"></path>
          </g>
          <g transform="translate(-4.000000, 76.000000)" fill="#FFFFFF" fill-rule="nonzero">
            <path
                d="M0.457,34.035 C57.086,53.198 98.208,65.809 123.822,71.865 C181.454,85.495 234.295,90.29 272.033,93.459 C311.355,96.759 396.635,95.801 461.025,91.663 C486.76,90.01 518.727,86.372 556.926,80.752 C595.747,74.596 622.372,70.008 636.799,66.991 C663.913,61.324 712.501,49.503 727.605,46.128 C780.47,34.317 818.839,22.532 856.324,15.904 C922.689,4.169 955.676,2.522 1011.185,0.432 C1060.705,1.477 1097.39,3.129 1121.236,5.387 C1161.703,9.219 1208.621,17.821 1235.4,22.304 C1285.855,30.748 1354.351,47.432 1440.886,72.354 L1441.191,104.352 L1.121,104.031 L0.457,34.035 Z"
            ></path>
          </g>
        </g>
      </svg>
    </div>
  </div>
  <Footer/>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import EditFoodItemModal from "@/components/EditFoodItemModal";
import DeleteFoodItemModal from "@/components/DeleteFoodItemModal";
import axios from 'axios'
import {path} from '../settings'

export default {
  name: "Orders",
  data () {
    return {
      foodItems:[
        {
          order_id: 1,
          food_id: 3,
          item_name: 'Laddu',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 2,
          food_id: 5,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 3,
          food_id: 4,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 4,
          food_id: 6,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 5,
          food_id: 7,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 6,
          food_id: 11,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 7,
          food_id: 1,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 8,
          food_id: 5,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 9,
          food_id: 8,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 10,
          food_id: 12,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 11,
          food_id: 13,
          item_name: 'Chicken Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
        {
          order_id: 12,
          food_id: 13,
          item_name: 'Beef Fry',
          item_description: 'lorem ipsum',
          restaurant_name: 'KFC',
          picture: '',
          created_at: '22:00',
          picked_at: ''
        },
      ],
      currentSort:'item_name',
      currentSortDir:'asc',
      pageSize:3,
      currentPage:1,
      isEditModalVisible: false,
      isDeleteModalVisible: false,
      editInfo: {},
      deleteInfo: null,
    }
  },
  created() {
    // this.getFoodItems()
  },
  methods:{
    getFoodItems: function () {
      axios.defaults.withCredentials = true
      axios.get(path + '/Listoffooditems')
          .then(response => {
            console.log(response.data)
            this.foodItems = response.data
          }).catch(error => {
        console.log(error.data)
      })
    },
    sort:function(s) {
      //if s == current sort, reverse
      if(s === this.currentSort) {
        this.currentSortDir = this.currentSortDir==='asc'?'desc':'asc';
      }
      this.currentSort = s;
    },
    nextPage:function() {
      if((this.currentPage*this.pageSize) < this.foodItems.length) this.currentPage++;
    },
    prevPage:function() {
      if(this.currentPage > 1) this.currentPage--;
    },
    showEditModal(id, quantity) {
      this.editInfo = { id: id, quantity: quantity };
      this.isEditModalVisible = true;
    },
    showDeleteModal(id) {
      this.deleteInfo = id;
      this.isDeleteModalVisible = true;
    },
    closeEditModal() {
      this.isEditModalVisible = false;
    },
    closeDeleteModal() {
      this.isDeleteModalVisible = false;
    }
  },
  computed:{
    sortedFoodItems: function() {
      return this.foodItems.sort((a,b) => {
        let modifier = 1;
        if(this.currentSortDir === 'desc') modifier = -1;
        if(a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
        if(a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
        return 0;
      }).filter((row, index) => {
        let start = (this.currentPage-1)*this.pageSize;
        let end = this.currentPage*this.pageSize;
        if(index >= start && index < end) return true;
      });
    }
  },
  components: {
    Header,
    Footer,
    EditFoodItemModal,
    DeleteFoodItemModal
  }
}
</script>

<style scoped>

</style>