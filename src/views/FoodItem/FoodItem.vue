<template>
  <Header/>
<!--  <div>debug: sort={{currentSort}}, dir={{currentSortDir}}, page={{currentPage}}</div>-->
  <pre>{{foodItems}}</pre>


  <h1 class="text-3xl md:text-5xl text-center">List of posted food items</h1>
  <p class="text-center mt-2">Below are the list of items that you have posted</p>
  <div class="min-h-screen w-full px-6 md:px-0 md:w-2/3 mx-auto bg-white mt-1 md:mt-5">
    <router-link to="/food-item/create" class="flex justify-end title-font font-medium items-center text-gray-900 mb-4 md:mb-0">
      <button class="inline-flex text-white bg-yellow-500 border-0 py-1 px-6 focus:outline-none hover:bg-yellow-600 rounded text-lg">Add Item</button>
    </router-link>
    <div class="col-span-12">
      <div class="overflow-auto lg:overflow-visible">
        <table class="table w-full text-gray-400 border-separate space-y-6 text-sm">
          <thead class="bg-gray-200 text-gray-500">
          <tr>
            <th class="p-3 w-96 text-left cursor-pointer" @click="sort('name')">Item name <i class="fas fa-exchange-alt transform rotate-90"></i></th>
            <th class="p-3 w-16 text-left cursor-pointer" @click="sort('age')">Quantity <i class="fas fa-exchange-alt transform rotate-90"></i></th>
            <th class="p-3 w-12 text-center">Edit</th>
            <th class="p-3 w-12 text-center">Delete</th>
          </tr>
          </thead>
          <tbody>
          <tr class="bg-gray-100 text-gray-800" v-for="(item, index) in foodItems" :key="index">
            <td class="p-3 w-96">{{item.item_name}}</td>
            <td class="p-3 w-16">{{item.number_of_packets}}</td>
            <td class="p-3 w-12 text-center cursor-pointer">
              <button type="button" class="btn" @click="showEditModal(item.id, item.number_of_packets)">
                <i class="fas fa-edit text-blue-700"></i>
              </button>
            </td>
            <td class="p-3 w-12 text-center cursor-pointer">
              <button type="button" class="btn" @click="showDeleteModal(item.id)">
                <i class="fas fa-trash-alt text-red-700"></i>
              </button>
            </td>
          </tr>
          </tbody>
        </table>
        <p class="flex justify-end space-x-1 mt-1">
          <button
              @click="prevPage"
              :class="currentPage > 1 ? 'bg-purple-900 hover:bg-purple-700' : 'bg-gray-200 cursor-not-allowed'"
              class="inline-flex justify-center items-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            <i class="fas fa-angle-double-left pr-2 mt-1"></i> Previous
          </button>
          <button
              @click="nextPage"
              :class="(currentPage*pageSize) < foodItems.length ? 'bg-purple-900 hover:bg-purple-700' : 'bg-gray-200 cursor-not-allowed'"
              class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white  focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            Next<i class="fas fa-angle-double-right pl-2 mt-1"></i></button>
        </p>
        <edit-food-item-modal v-show="isEditModalVisible" @close="closeEditModal" :food-item="editInfo" />
        <delete-food-item-modal v-show="isDeleteModalVisible" @close="closeDeleteModal" :food-id="deleteInfo" />
      </div>
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
  name: "FoodItem",
  props: ['id'],
  data () {
    return {
      foodItems:[],
      currentSort:'name',
      currentSortDir:'asc',
      pageSize:8,
      currentPage:1,
      isEditModalVisible: false,
      isDeleteModalVisible: false,
      editInfo: {},
      deleteInfo: null,
    }
  },
  created() {
    axios.defaults.withCredentials = true
    axios.get(path + '/Listoffooditems')
        .then(response => {
          console.log(response.data)
          this.foodItems = response.data
        }).catch(error => {
      console.log(error.data)
    })


    // axios.get('http://localhost:8080/Listoffooditems')
    //     .then(response => {
    //       console.log(response)
    //     }).catch(error => {
    //   console.log(error.data)
    // })

  },
  methods:{
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
    sortedCats:function() {
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
</style>