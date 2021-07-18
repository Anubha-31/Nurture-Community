<template>
  <Header/>
<!--  <div>debug: sort={{currentSort}}, dir={{currentSortDir}}, page={{currentPage}}</div>-->
  <h1 class="text-3xl md:text-5xl text-center">List of posted food items</h1>
  <p class="text-center mt-2">Below are the list of items that you have posted</p>
  <div class="min-h-screen w-2/3 mx-auto bg-white mt-1 md:mt-5">
    <div class="col-span-12">
      <div class="overflow-auto lg:overflow-visible ">
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
          <tr class="bg-gray-100 text-gray-800" v-for="(cat, index) in sortedCats" :key="index">
            <td class="p-3 w-96">{{cat.name}}</td>
            <td class="p-3 w-16">{{cat.age}}</td>
            <td class="p-3 w-12 text-center cursor-pointer">
              <button type="button" class="btn" @click="showEditModal(index, cat.age)">
                <i class="fas fa-edit text-blue-700"></i>
              </button>
            </td>
            <td class="p-3 w-12 text-center cursor-pointer">
              <button type="button" class="btn" @click="showDeleteModal(index)">
                <i class="fas fa-trash-alt text-red-700"></i>
              </button>
            </td>
          </tr>
          </tbody>
        </table>
        <p class="flex justify-end space-x-1 mt-1">
          <button @click="prevPage" class="inline-flex justify-center items-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-purple-900 hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            <i class="fas fa-angle-double-left pr-2 mt-1"></i> Previous
          </button>
          <button @click="nextPage" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-purple-900 hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
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

export default {
  name: "FoodItem",
  data () {
    return {
      cats:[],
      currentSort:'name',
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
    axios.get('https://www.raymondcamden.com/.netlify/functions/get-cats')
        .then(response => {
          this.cats = response.data
        }).catch(error => {
      console.log(error.data)
    })
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
      if((this.currentPage*this.pageSize) < this.cats.length) this.currentPage++;
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
      return this.cats.sort((a,b) => {
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