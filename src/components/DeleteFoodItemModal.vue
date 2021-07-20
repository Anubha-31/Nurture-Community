<template>
  <transition name="modal-fade">
    <form action="#" method="POST" @submit.prevent="deleteFoodQuantity">
    <div class="modal-backdrop" role="dialog">
      <div class="bg-white rounded-lg w-4/5 md:w-1/4">
        <div class="flex flex-col items-start p-4">
          <div class="flex items-center w-full">
            <div class="text-gray-900 font-medium text-lg">
              <img src="../assets/svg/warning.svg" alt="" class="w-6 inline-block">
              Warning!
            </div>
            <svg class="ml-auto fill-current text-gray-700 w-6 h-6 cursor-pointer" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18" @click="close">
              <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"/>
            </svg>
          </div>
          <hr>
          <div class="mt-5">Are you sure you want to delete this item?</div>
          <hr>
          <input type="hidden" v-model="item.id">
          <div class="ml-auto mt-5 space-x-2">
            <button type="submit" class="bg-red-800 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
              Yes
            </button>
            <button type="button" @click="close" class="bg-transparent hover:bg-green-700 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded">
              No
            </button>
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
  name: "DeleteFoodItemModal",
  props: {
    foodId: Number
  },
  data() {
    return {
      item: {
        id: this.foodId
      }
    }
  },
  watch: {
    foodId: function(val){
      this.item.id = val;
    }
  },
  methods: {
    close: function(event) {
      this.$emit('close');
    },
    deleteFoodQuantity: function () {
      // console.log(this.item.id)
      axios.post(path + '/deleteFood', this.item)
        .then(response => {
          console.log(response.data)
          this.$emit('reload')
          this.$emit('close');
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