<template>
  <div>
    <header class="text-gray-600 body-font">
      <div class="container mx-auto flex flex-wrap pt-5 px-5 flex-col md:flex-row items-center">
        <router-link to="/" class="flex title-font font-medium items-center text-gray-900 mb-4 md:mb-0">
          <img src="..\assets\images\logo.png" alt="" class="w-36">
        </router-link>
        <nav class=" md:mr-auto md:ml-4 md:py-1 md:pl-4 md:border-l md:border-gray-400 flex flex-wrap items-center text-base justify-center">
          <a class="mr-5 hover:text-gray-900 cursor-pointer">About Us</a>
          <a class="mr-5 hover:text-gray-900 cursor-pointer">Contact Us</a>
        </nav>
      </div>
    </header>
    <div class="container mx-auto px-6">
      <div class="text-center">
        <h1 class="title-font sm:text-4xl text-3xl mb-4 font-medium text-gray-900">Sign Up</h1>
        <p class="mt-5">Please provide the information below to sign up as a user</p>
      </div>

      <div class="mt-5 md:mt-10 md:w-1/2 md:mx-auto">
        <form method="POST" @submit.prevent="formSubmit">

          <div class="shadow overflow-hidden rounded-lg border border-gray-200">
            <div class="px-4 py-5 bg-white sm:p-6">
              <ul v-if="errors.length > 0" class="text-red-500 font-medium mb-5">
                <li v-for="error in errors" :key="error.message">{{ error }}</li>
              </ul>
              <div class="grid grid-cols-6 gap-6">
                <div class="col-span-6">
                  <label for="user_type" class="block text-sm font-medium text-gray-700">Sign Up As</label>
                  <select id="user_type" name="user_type" autocomplete="user_type" v-model="user.user_type" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    <option value="restaurant">Restaurant Representative</option>
                    <option value="client">Client</option>
                  </select>
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="first_name" class="block text-sm font-medium text-gray-700">First name*</label>
                  <input type="text" name="first_name" id="first_name" autocomplete="given-name" v-model="user.first_name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="last_name" class="block text-sm font-medium text-gray-700">Last name*</label>
                  <input type="text" name="last_name" id="last_name" autocomplete="family-name" v-model="user.last_name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="email_address" class="block text-sm font-medium text-gray-700">Email address*</label>
                  <input type="email" name="email_address" id="email_address" autocomplete="email" v-model="user.emailaddress" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>

                <div class="col-span-6 sm:col-span-3">
                  <label for="phone_number" class="block text-sm font-medium text-gray-700">Phone Number</label>
                  <div class="flex space-x-1">
                    <input type="text" value="+1" disabled class="w-1/5 mt-1 p-2 bg-gray-100 block shadow-sm sm:text-sm border border-gray-300 rounded-md text-center">
                    <input type="number" name="phone_number" id="phone_number" v-model="user.phone" class="w-4/5 mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                  </div>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3">
                  <label for="password" class="block text-sm font-medium text-gray-700">Password*</label>
                  <input type="password" name="password" id="password" v-on:keyup="keyMonitor" v-model="user.password" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3">
                  <label for="confirm_password" class="block text-sm font-medium text-gray-700">Confirm Password*</label>
                  <input type="password" name="confirm_password" id="confirm_password" v-on:keyup="keyMonitor" v-model="user.confirm_password" autocomplete="email" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>

                <div v-if="capsOn" class="col-span-6 text-red-500 font-medium">Capslock in on!</div>
                <div v-if="passwordValidation.length > 0" class="col-span-6">
                  <ul>
                    <li class="text-xs text-red-500 font-medium" v-for="error in passwordValidation" :key="error.key">{{ error }}</li>
                  </ul>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3" v-if="user.user_type === 'restaurant'">
                  <label for="restaurant_name" class="block text-sm font-medium text-gray-700">Restaurant's Name*</label>
                  <input type="text" name="restaurant_name" id="restaurant_name" v-model="user.restaurant_name" autocomplete="restaurant-name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3" v-if="user.user_type === 'restaurant'">
                  <label for="license_number" class="block text-sm font-medium text-gray-700">License Number*</label>
                  <input type="text" name="license_number" id="license_number" v-model="user.license_number" autocomplete="license-number" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>

                <div class="col-span-6" v-if="user.user_type === 'restaurant'">
                  <label for="cover_image" class="block text-sm font-medium text-gray-700">Cover Image of you restaurant (only .png, .gif, jpg or jpeg of less than size 5MB)</label>
                  <input type="file" name="cover_image" accept="image/png, image/gif, image/jpeg" id="cover_image" ref="cover_image" @change="onFileUpload" autocomplete="restaurant-name" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>


                <div class="col-span-6 sm:col-span-3 lg:col-span-3" v-if="user.user_type === 'restaurant'">
                  <label for="opens_at" class="block text-sm font-medium text-gray-700">Opens at</label>
                  <input type="time" name="opens_at" id="opens_at" v-model="user.opens_at"  autocomplete="opens-at" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3" v-if="user.user_type === 'restaurant'">
                  <label for="closes_at" class="block text-sm font-medium text-gray-700">Closes at</label>
                  <input type="time" name="closes_at" id="closes_at" v-model="user.closes_at"  autocomplete="closes-at" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>

                <div class="col-span-6">
                  <label for="address1" class="block text-sm font-medium text-gray-700">Address Line 1</label>
                  <input name="address1" id="address1" cols="30" rows="4" v-model="user.address1" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"/>
                </div>

                <div class="col-span-6">
                  <label for="address2" class="block text-sm font-medium text-gray-700">Address Line 2</label>
                  <input name="address2" id="address2" cols="30" rows="4" v-model="user.address2" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"/>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3">
                  <label for="city" class="block text-sm font-medium text-gray-700">City</label>
                  <input name="city" id="city" cols="30" rows="4" v-model="user.city" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"/>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3">
                  <label for="province" class="block text-sm font-medium text-gray-700">Province</label>
                  <input name="province" id="province" cols="30" rows="4" v-model="user.province" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"/>
                </div>

                <div class="col-span-6 sm:col-span-3 lg:col-span-3" >
                  <label for="country" class="block text-sm font-medium text-gray-700">Country</label>
                  <input name="country" id="country" cols="30" rows="4" v-model="user.country" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"/>
                </div>

                <div class="col-span-6">
                  <label for="postal_code" class="block text-sm font-medium text-gray-700">ZIP / Postal*</label>
                  <input type="text" name="postal_code" id="postal_code" v-model="user.zip" autocomplete="postal-code" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md" required>
                </div>
                <div v-if="zipValidation.length > 0" class="col-span-6">
                  <ul>
                    <li class="text-xs text-red-500 font-medium" v-for="error in zipValidation" :key="error.key">{{ error }}</li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
              <button type="submit" class="inline-flex w-full justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                Sign Up
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
import axios from 'axios'
import Footer from "@/components/Footer";
import {path} from './settings.js'


export default {
  name: "SignUp",
  components: {Footer},
  data() {
    return {
      user: {
        user_type: "restaurant",
        first_name: "",
        last_name: "",
        emailaddress: "",
        password: "",
        confirm_password: "",
        restaurant_name: "",
        license_number: "",
        cover_image: "",
        opens_at: "09:00",
        closes_at: "22:00",
        phone: "",
        address1: "",
        address2: "",
        city:"",
        province:"",
        country:"",
        zip: ""
      },
      formData: null,
      fileData: null,
      capsOn: false,
      errors: [],
      passwordValidation: [],
      zipValidation: []
    }
  },
  created() {
    this.formData = new FormData();
  },
  methods: {
    formSubmit: function () {
      this.formData = new FormData();
      this.formData.append("model", JSON.stringify(this.user));
      this.formData.append("cover_image", this.user.cover_image)
      this.errors = [];
      this.validatePassword();
      this.validateZip();
      if(this.user.password === this.user.confirm_password) {
        axios.defaults.withCredentials = true
        axios({
          url: path +'/users/register',
          method: 'POST',
          data: this.formData,
          headers: {
            Accept: 'application/json',
            'Content-type': `multipart/form-data;boundary=--`,
          }
        }).then((response) => {
          console.log(response.status)
          if(response.status === 200) {
            this.fileData = response
            alert("Signed Up successfully")
          } else if (response.status === 409) {
            alert("You have already registered with this email address")
          }
        }, (error) => {
          if(error.request.status === 409) {
            alert("User with given email already exist!")
          }
        });
      } else {
        this.errors.push('Passwords should match')
      }
    },
    onFileUpload: function (event) {
      if(event.target.files[0].size > 5000000) {
        alert("Please upload a file less than 5MB")
        this.$refs.cover_image.value = null;
      } else {
        console.log(event.target.files[0])
        this.user.cover_image = event.target.files[0]
      }
    },
    keyMonitor: function (event) {
      // console.log(event);
      if(event.getModifierState("CapsLock")) {
        this.capsOn = true
      } else {
        this.capsOn = false
      }
    },
    validatePassword: function () {
      let p = this.user.password;

      if (p.length < 8) {
        this.passwordValidation.push("Your password must be at least 8 characters");
      }
      if (p.search(/[a-z]/i) < 0) {
        this.passwordValidation.push("Your password must contain at least one letter.");
      }
      if (p.search(/[0-9]/) < 0) {
        this.passwordValidation.push("Your password must contain at least one digit.");
      }
      if (this.passwordValidation.length > 0) {
        this.passwordValidation.join("\n");
        return false;
      }
      return true;
    },
    validateZip: function () {
      let p = this.user.zip;

      if (p.length < 6) {
        this.zipValidation.push("Zip code must be 6 characters long");
      }
      if (/\s/g.test(p)) {
        this.zipValidation.push("It should not contain any space");
      }
      if (this.zipValidation.length > 0) {
        this.zipValidation.join("\n");
        return false;
      }
      return true;
    }
  }
}
</script>

<style scoped>

</style>