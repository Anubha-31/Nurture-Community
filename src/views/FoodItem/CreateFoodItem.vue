<template>
  <Header />
  <div>
    <div class="container mx-auto px-1">
      <div class="text-center">
        <h1 class="text-3xl">Post Available Food</h1>
        <p class="mt-5">
          Please Consider donating a leftover meal to the community by positing
          it here.
        </p>
      </div>

      <div class="mt-5 md:mt-10 md:w-1/2 md:mx-auto">
        <form method="POST" @submit.prevent="formSubmit">
          <div class="shadow overflow-hidden rounded-lg border border-gray-200">
            <div class="px-4 py-5 bg-white sm:p-6">
              <div class="grid grid-cols-5 gap-10">
                <div class="col-span-6">
                  <label
                    for="item_name"
                    class="block text-sm font-medium text-gray-700"
                    >Item name</label
                  >
                  <input
                    type="text"
                    name="Item_name"
                    v-model="food.itemName"
                    id="item_name"
                    autocomplete="item-name"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="Item_Descrption"
                    class="block text-sm font-medium text-gray-700"
                    >Item Descrption</label
                  >
                  <input
                    type="text"
                    name="Item_Descrption"
                    id="Item_Descrption"
                    v-model="food.itemDescription"
                    autocomplete="Item-Descrption"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="Upload_picture"
                    class="block text-sm font-medium text-gray-700"
                    >Upload Picture</label
                  >
                  <input
                    type="file"
                    name="uploadedPicture"
                    accept="image/png, image/gif, image/jpeg"
                    id="uploadedPicture"
                    ref="uploadedPicture"
                    @change="onFileUpload"
                    autocomplete="restaurant-name"
                    class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="quantity"
                    class="block text-sm font-medium text-gray-700"
                    >Quantity</label
                  >
                  <input
                    type="text"
                    name="Quantity"
                    id="Quantity"
                    v-model="food.numberofPackets"
                    autocomplete="Quantity"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="pick_up_location"
                    class="block text-sm font-medium text-gray-700"
                    >Choose Whether food must be picked from a restaurant
                    location</label
                  >
                  <select
                    id="pick_up_location"
                    name="pick_up_location"
                    autocomplete="pick_up_location"
                    v-model="food.locationChange"
                    class="
                      mt-1
                      block
                      w-full
                      py-2
                      px-3
                      border border-gray-300
                      bg-white
                      rounded-md
                      shadow-sm
                      focus:outline-none
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      sm:text-sm
                    "
                  >
                    <option>Yes</option>
                    <option>No</option>
                  </select>
                </div>

                <div class="col-span-6" v-if="food.locationChange === 'No'">
                  <label
                    for="address1"
                    class="block text-sm font-medium text-gray-700"
                    >Address Line 1</label
                  >
                  <input
                    type="text"
                    name="address1"
                    id="address1"
                    cols="30"
                    v-model="food.address1"
                    rows="4"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6" v-if="food.locationChange === 'No'">
                  <label
                    for="address2"
                    class="block text-sm font-medium text-gray-700"
                    >Address Line 2</label
                  >
                  <input
                    type="text"
                    name="address2"
                    id="address2"
                    cols="30"
                    v-model="food.address2"
                    rows="4"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-3" v-if="food.locationChange === 'No'">
                  <label
                    for="city"
                    class="block text-sm font-medium text-gray-700"
                    >City</label
                  >
                  <input
                    type="text"
                    name="city"
                    id="city"
                    cols="30"
                    v-model="food.city"
                    rows="4"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>
                <div class="col-span-6 sm:col-span-3" v-if="food.locationChange === 'No'">
                  <label
                    for="province"
                    class="block text-sm font-medium text-gray-700"
                    >Province</label
                  >
                  <input
                    type="text"
                    name="province"
                    id="province"
                    cols="30"
                    v-model="food.province"
                    rows="4"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>
                <div class="col-span-3" v-if="food.locationChange === 'No'">
                  <label
                    for="country"
                    class="block text-sm font-medium text-gray-700"
                    >Country</label
                  >
                  <input
                    type="text"
                    name="country"
                    id="country"
                    cols="30"
                    v-model="food.country"
                    rows="4"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>
                <br>
                <div class="col-span-3">
                  <label
                    for="pick_up_time"
                    class="block text-sm font-medium text-gray-700"
                    >Pick Up time
                  </label>
                  <input
                    type="time"
                    name="pick_up_time"
                    id="pick_up_time"
                    v-model="food.pickupTime"
                    autocomplete="pick_up_time"
                    class="
                      mt-1
                      p-2
                      focus:ring-indigo-500
                      focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>
              </div>
              <div class=" pt-4 text-right">
                <button
                  type="submit"
                  class="inline-flex w-full justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Submit
                </button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <Footer />
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import axios from "axios";
import { path } from "../settings.js";

export default {
  name: "CreateFoodItem",
  data() {
    return {
      food: {
        itemName: "",
        itemDescription: "",
        uploadedPicture: "",
        numberofPackets: "",
        locationChange: "No",
        address1: "",
        address2: "",
        city:"",
        province:"",
        country:"",
        pickupTime: "09:00",
      },
      formData: null,
    };
  },
  created() {
    this.formData = new FormData();
  },
  methods: {
    formSubmit: function() {
      this.formData = new FormData();
      this.formData.append("model", JSON.stringify(this.food));
      this.formData.append("uploadedPicture", this.food.uploadedPicture);
      axios.defaults.withCredentials = true;
      axios({
        url: path + "/addFoodDetails",
        method: "POST",
        data: this.formData,
        headers: {
          Accept: "application/json",
          "Content-type": `multipart/form-data;boundary=--`,
        },
      }).then(
        (response) => {
          console.log(response);
          if (response.status === 200) {
            alert("Post Added!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    onFileUpload: function(event) {
      if (event.target.files[0].size > 5000000) {
        alert("Please upload a file less than 5MB");
        this.$refs.uploadedPicture.value = null;
      } else {
        console.log(event.target.files[0]);
        this.uploadedPicture = event.target.files[0];
      }
    },
  },
  components: {
    Header,
    Footer,
  },
};
</script>

<style scoped></style>
