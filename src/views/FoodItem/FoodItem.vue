<template>
  <Header/>
  debug: sort={{currentSort}}, dir={{currentSortDir}}, page={{currentPage}}
  <div class="min-h-screen w-2/3 mx-auto bg-white">
    <div class="col-span-12">
      <div class="overflow-auto lg:overflow-visible ">
        <table class="table w-full text-gray-400 border-separate space-y-6 text-sm">
          <thead class="bg-gray-200 text-gray-500">
          <tr>
            <th colspan="4" class="p-3 text-left cursor-pointer" @click="sort('name')">Item name</th>
            <th class="p-3 text-left cursor-pointer" @click="sort('age')">Quantity</th>
            <th class="p-3 text-left">Action</th>
            <th class="p-3 text-left">Action</th>
<!--            <th class="p-3 text-left" @click="sort('gender')">Gender</th>-->
          </tr>
          </thead>
          <tbody>
          <tr class="bg-gray-100 text-gray-800" v-for="cat in sortedCats">
            <td colspan="4" class="p-3">{{cat.name}}</td>
            <td class="p-3">{{cat.age}}</td>
            <td class="p-3">edit</td>
            <td class="p-3">delete</td>
<!--            <td class="p-3">{{cat.gender}}</td>-->
          </tr>
          </tbody>
        </table>
        <p class="flex justify-end space-x-1">
          <button @click="prevPage" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Previous</button>
          <button @click="nextPage" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-purple-900 hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Next</button>
        </p>
      </div>
    </div>
  </div>
  <Footer/>
</template>
<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import axios from 'axios'

export default {
  name: "FoodItem",
  data () {
    return {
      cats:[],
      currentSort:'name',
      currentSortDir:'asc',
      pageSize:3,
      currentPage:1
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
  components: {Header, Footer}
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