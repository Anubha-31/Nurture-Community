<template>
  <Header user_type="restaurant"></Header>
  <!--  <div>debug: sort={{currentSort}}, dir={{currentSortDir}}, page={{currentPage}}</div>-->
<!--    <pre>{{foodItems}}</pre>-->
  <div class="leading-normal tracking-normal text-white gradient" style="font-family: 'Source Sans Pro', sans-serif;">
      <h1 class="w-full my-2 text-5xl font-bold leading-tight text-center text-gray-800">
        Food Items
      </h1>
      <div class="w-full mb-4">
        <div class="h-1 mx-auto gradient w-64 opacity-25 my-0 py-0 rounded-t"></div>
      </div>
      <p class="text-center mt-2 text-gray-800">Below are the list of items that you have posted</p>
      <div class="overflow-x-auto px-2 md:px-0">
        <div class="min-w-screen min-h-screen flex justify-center font-sans overflow-x-auto md:overflow-hidden">
          <div class="w-full lg:w-4/6">
            <div class="flex justify-end mt-8 md:mt-0">
              <router-link to="/food-item/create" class="title-font font-medium items-center text-gray-900 md:mb-0">
                <button class="inline-flex text-white bg-yellow-500 border-0 py-2 px-6 focus:outline-none hover:bg-yellow-600 rounded text-md">Add Item</button>
              </router-link>
            </div>
            <div class="bg-white shadow-md rounded my-6">
              <table class="min-w-max w-full table-auto">
                <thead>
                <tr class="text-white bg-aqua-2 uppercase text-sm leading-normal">
                  <th class="py-3 px-6 text-left cursor-pointer" @click="sort('item_name')">Item Name <i class="fas fa-exchange-alt transform rotate-90"></i></th>
                  <th class="py-3 px-6 text-left cursor-pointer">Pickup Info</th>
                  <th class="py-3 px-6 text-left cursor-pointer" @click="sort('number_of_packets')">Quantity <i class="fas fa-exchange-alt transform rotate-90"></i></th>
                  <th class="py-3 px-6 text-center">Actions</th>
                </tr>
                </thead>
                <tbody class="text-gray-600 text-sm font-light bg-white">
                <tr class="border-b border-gray-200 hover:bg-gray-100" v-for="(item, index) in sortedFoodItems" :key="index">
                  <td class="py-3 px-6 text-left whitespace-nowrap">
                    <div class="flex items-center">
                      <div class="mr-2">
                        <img class="w-6 h-6 rounded-full" src="../../assets/images/food.png"/>
                      </div>
                      <div>
                        <span class="font-medium">{{ item.item_name }}</span>
                        <div class="text-xs">
                          {{ item.item_description }}
                        </div>
                      </div>
                    </div>
                  </td>
                  <td class="py-3 px-6 text-left whitespace-nowrap">
                    <span class="font-medium">{{ item.pickupTime }}</span>
                    <div class="text-xs">{{ item.address }}</div>
                  </td>
                  <td class="py-3 px-6 text-left whitespace-nowrap"><span class="font-medium">{{ item.number_of_packets }}</span></td>
                  <td class="py-3 px-6 text-center">
                    <div class="flex item-center justify-center space-x-1">
                      <div class="w-4 mr-2 transform hover:text-blue-700 hover:scale-110 cursor-pointer" @click="showEditModal(item.id, item.number_of_packets)">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z" />
                        </svg>
                      </div>
                      <div class="w-4 mr-2 transform hover:text-red-800 hover:scale-110 cursor-pointer" @click="showDeleteModal(item.id)">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                        </svg>
                      </div>
                    </div>
                  </td>
                </tr>
                </tbody>
              </table>
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
  <edit-food-item-modal v-show="isEditModalVisible" @close="closeEditModal" :food-item="editInfo" @reload="getFoodItems"/>
  <delete-food-item-modal v-show="isDeleteModalVisible" @close="closeDeleteModal" :food-id="deleteInfo" @reload="getFoodItems" />
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
  name: "FoodItem",
  data () {
    return {
      foodItems:[],
      currentSort:'item_name',
      currentSortDir:'asc',
      pageSize:5,
      currentPage:1,
      isEditModalVisible: false,
      isDeleteModalVisible: false,
      editInfo: {},
      deleteInfo: null,
    }
  },
  created() {
    this.getFoodItems()
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
.table {
  border-spacing: 0 15px;
}
i {
  font-size: 1rem !important;
}
.table tr {
  border-radius: 20px;
}
tr td:nth-child(n+5),
tr th:nth-child(n+5) {
  border-radius: 0 .625rem .625rem 0;
}
tr td:nth-child(1),
tr th:nth-child(1) {
  border-radius: .625rem 0 0 .625rem;
}
.gradient {
  background: linear-gradient(180deg, #ffffff 0%, #003963 100%);
}
</style>