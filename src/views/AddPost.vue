   <template>
     <Header/>
  <div>
    
    <div class="container mx-auto px-6">
      <div class="text-center">
        <h1 class="text-3xl">Post Avaiable Food </h1>
        <p class="mt-5">Please Consider donating a leftover meal to the community by positing it here.</p>
      </div>

      <div class="mt-5 md:mt-0 ">
        <form action="#" method="POST">
          <div class="shadow overflow-hidden rounded-lg border border-gray-200">
            <div class="px-4 py-5 bg-white sm:p-6">
              <div class="grid grid-cols-1 gap-6">

                
                <div class="col-span-6 sm:col-span-3">
                  <label for="item_name" class="block text-sm font-medium text-gray-700">Item name</label>
                  <input type="text" name="Item_name" id="item_name" autocomplete="item-name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="Item_Descrption" class="block text-sm font-medium text-gray-700">Item Descrption</label>
                  <input type="text" name="Item_Descrption" id="Item_Descrption" autocomplete="Item-Descrption" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6 sm:col-span-4">
                  <label for="Upload_picture" class="block text-sm font-medium text-gray-700">Upload Picture</label>
                  <input type="text" name="Upload_Picture" id="Upload_picture" autocomplete="Upload-picture" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6 sm:col-span-4">
                  <label for="quantity" class="block text-sm font-medium text-gray-700">Quantity</label>
                  <input type="text" name="Quantity" id="Quantity" autocomplete="Quantity" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>
                
                <div class="col-span-6 sm:col-span-4">
                    <label for="pick_up_location" class="block text-sm font-medium text-gray-700">Choose Weather food must be picked from a restaurant location</label>
                   <select id="pick_up_location" name="pick_up_location" autocomplete="pick_up_location" v-model="pick_up_location" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    <option>Yes</option>
                    <option>No</option>
                  </select>
                </div>
                
                <div class="col-span-6 sm:col-span-4">
                  <label for="address" class="block text-sm font-medium text-gray-700">Address</label>
                  <textarea name="address" id="address" cols="30" rows="4" class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"></textarea>
                </div>

                <div class="col-span-6 sm:col-span-4">
                  <label for="pick_up_time" class="block text-sm font-medium text-gray-700">Pick Up time </label>
                  <input type="text" name="pick_up_time" id="pick_up_time" autocomplete="pick_up_time" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>
              </div>
            </div>
            <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
              <button class="inline-flex text-white bg-yellow-500 border-0 py-2 px-6 focus:outline-none hover:bg-yellow-600 rounded text-lg">
                      Submit
              </button>
            </div>
          </div>
        </form>
      </div>

    </div>
  </div>
  <Footer/>
</template>


<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "AddPost",
  data() {
    return {
      user_type: ""
    }
  }, 
  components: {
    Header,
    Footer
  }
}
</script>

<style scoped>

</style>
