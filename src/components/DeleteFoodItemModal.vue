<template>
  <transition name="modal-fade">
    <div class="modal-backdrop" role="dialog">
      <div class="modal relative rounded-md" ref="modal">
        <header class="modal-header">
          <slot name="header">

            <button type="button" class="font-extrabold px-4 py-1 absolute top-0 right-0" @click="close" aria-label="Close modal">x</button>
          </slot>
        </header>

        <form action="#" method="POST" @submit.prevent="deleteFoodQuantity">
          <section class="modal-body">
            <slot name="body">
              <h2 class="pt-5">Are you sure you want to delete this item?</h2>
              <input type="hidden" v-model="item.id">
            </slot>
          </section>

          <footer class="modal-footer">
            <slot name="footer">
              <div class="space-x-2">
                <button type="submit" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red-800 hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                  Yes
                </button>
                <button type="button" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-green-700 hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" @click="close" aria-label="Close modal">
                  No
                </button>
              </div>
            </slot>
          </footer>
        </form>
      </div>
    </div>
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
      console.log(this.item.id)
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