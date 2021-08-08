<template>
    <transition name="modal-fade">
      <form action="#" method="POST" @submit.prevent="editFoodQuantity">
        <div class="modal-backdrop" role="dialog">

          <div class="flex items-center justify-center fixed left-0 bottom-0 w-full h-full">
            <div class="bg-white rounded-lg w-1/4">
              <div class="flex flex-col items-start p-4">
                <div class="flex items-center w-full">
                  <div class="text-gray-900 font-medium text-lg">Enter the updated quantity below</div>
                  <svg class="ml-auto fill-current text-gray-700 w-6 h-6 cursor-pointer" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18" @click="close" aria-label="Close modal">
                    <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"/>
                  </svg>
                </div>
                <hr>
                <div class="mt-4 flex items-center w-full">
                  <label for="quantity" class="w-1/3">Quantity</label>
                  <input type="number" id="quantity" v-model="item.numberofPackets" class="mt-1 p-2 text-center focus:ring-indigo-500 focus:border-indigo-500 shadow-sm sm:text-sm border border-gray-300 rounded-md w-2/3">
                </div>
                <hr>
                <div class="ml-auto space-x-2 mt-6">
                  <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Update
                  </button>
                  <button class="bg-transparent hover:bg-gray-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded" @click="close" aria-label="Close modal">
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </transition>
</template>

<script>
import axios from 'axios'
import {path} from '../views/settings'

export default {
  name: "EditFoodItemModal",
  props: {
    foodItem: Object
  },
  data() {
    return {
      item: {
        id: this.foodItem.id,
        numberofPackets: this.foodItem.quantity
      }
    }
  },
  watch: {
    foodItem: function(val){
      this.item.id = val.id;
      this.item.numberofPackets = val.quantity;
    }
  },
  methods: {
    close: function(event) {
      this.$emit('close');
    },
    editFoodQuantity: function () {
      console.log(this.item)
   //   axios.defaults.withCredentials = true
    //  axios.post(path + '/updateFood', this.item)
       const token = localStorage.getItem("token");
      axios({
      url: path + "/updateFood",
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Access-Control-Allow-Origin": "*",
        "Content-type": "application/json",
      },
      data : this.item
    })
        .then(response => {
          console.log(response.data)
          this.$emit('reload')
          this.$emit('close')
        })
        .catch(error => {
          console.log(error.data)
        })
    }
  }
}
</script>

<style scoped>


.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background: #ffffff;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}



.modal-footer {
  border-top: 1px solid #eeeeee;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}




.modal-fade-enter,
.modal-fade-leave-active {
  opacity: 0;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.5s ease;
}
</style>