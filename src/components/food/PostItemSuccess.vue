<template>
  <transition name="modal-fade">
      <div class="modal-backdrop" role="dialog">
        <div class="bg-white rounded-lg w-4/5 md:w-1/4">
          <div class="flex flex-col items-start p-4">
            <div class="flex items-center w-full">
              <div class="text-gray-900 font-medium text-lg">
                Item Added!
              </div>
              <svg class="ml-auto fill-current text-gray-700 w-6 h-6 cursor-pointer" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18" @click="close">
                <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"/>
              </svg>
            </div>
            <hr>
            <div class="mt-5">Would you like to see the list of items posted so far?</div>
            <hr>
            <div class="ml-auto mt-5 space-x-2">
              <router-link to="/restaurant/food-items">
                <button type="submit" class="bg-green-600 hover:bg-green-500 text-white font-bold py-2 px-4 rounded">
                  Yes
                </button>
              </router-link>
              <button type="button" @click="close" class="bg-transparent hover:bg-blue-700 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded">
                No
              </button>
            </div>
          </div>
        </div>
      </div>
  </transition>
</template>

<script>
export default {
  name: "PostItemSuccess",
  methods: {
    close: function(event) {
      this.$emit('close');
    },
  }
}
</script>

<style scoped>

</style>