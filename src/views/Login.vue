<template>
  <div class="min-h-screen flex flex-col space-y-3">
    <Header></Header>
    <div
      v-if="notVerified"
      class="bg-red-300 rounded-md shadow-tranparent text-center w-1/3 m-auto text-sm py-2 "
    >
      We have sent you a verification email.<br />
      Please login and verify email before logging in
    </div>
    <div
      v-if="verificationCompleted"
      class="bg-green-300 rounded-md shadow-tranparent text-center w-1/3 m-auto text-sm py-2 "
    >
      Your email has been verified<br />
      Please login to continue browsing
    </div>
    <div class="flex-grow">
      <div class="container mx-auto px-6">
        <div class="text-center">
          <h1
            class="title-font sm:text-4xl text-3xl mb-4 font-medium text-gray-900"
          >
            Login
          </h1>
          <p class="mt-5">
            Please provide the information below to sign in as a user or admin
          </p>
        </div>
        <div class="mt-1 md:mt-10 md:w-1/2 md:mx-auto">
          <form method="POST" @submit.prevent="formSubmit">
            <div
              class="shadow overflow-hidden rounded-lg border border-gray-200"
            >
              <div class="px-4 py-5 bg-white sm:p-6">
                <div class="grid grid-cols-1 gap-1">
                  <div class="col-span-6 sm:col-span-1 ">
                    <label
                      for="Email"
                      class="block text-sm font-medium text-gray-700"
                      >Email*</label
                    >
                    <input
                      type="Email"
                      name="Email"
                      v-model="user.emailaddress"
                      id="Email"
                      autocomplete="Email"
                      class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                    />
                  </div>
                  <div class="col-span-6 sm:col-span-1">
                    <label
                      for="Password"
                      class="block text-sm font-medium text-gray-700"
                      >Password*</label
                    >
                    <input
                      type="Password"
                      name="Password"
                      v-model="user.password"
                      id="Password"
                      autocomplete="Password"
                      v-on:keyup="keyMonitor"
                      class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                    />
                  </div>
                </div>
                <div v-if="capsOn" class="col-span-6 text-red-500 font-medium">
                  Capslock in on!
                </div>
              </div>
              <div
                class="px-4 sm:px-6 pt-2 pb-10 bg-gray-50 text-right sm:col-span-1"
              >
                <button
                  type="submit"
                  class="inline-flex w-full justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Login
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import axios from "axios";
import Footer from "@/components/Footer.vue";
import Header from "@/components/Header";
import { path } from "./settings.js";

export default {
  name: "Login",
  mounted() {
    this.url_data = this.$route.params.userId;
    if (this.url_data != null && this.url_data != "") {
      console.log(this.url_data);
      axios.defaults.withCredentials = true;
      axios.post(path + "/verify/login", { urldata: this.url_data}).then(
        (response) => {
          console.log(response.status)
          if (response.status === 200) {
            this.verificationCompleted = true;
          } else if(response.status === 404){
            this.notVerified = true
          }
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  data() {
    return {
      user: {
        emailaddress: "",
        password: ""
      },
      notVerified: false,
      url_data: "",
      verificationCompleted: false,
      capsOn: false,
    };
  },
  methods: {
    formSubmit: function() {
      axios({
      url: path + "/users/login",
      method: "POST",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "application/json",
        
      },
      data : this.user
    }).then(
        (response) => {
          localStorage.setItem('token', response.data.token.token)
          console.log(response.data.usertype)
          if (response.status === 200) {
            if (response.data.usertype === "restaurant") {
              window.location.href = "/restaurant/food-items";
            } else {
              window.location.href = "/restaurant";
            }
          }
        },
        (error) => {
          console.log(error);
          if (error.response.status === 401) {
            alert("Please enter the correct password!!");
          } else if(error.response.status === 409){
            this.notVerified = true
          }
        }
      );
    },
    keyMonitor: function(event) {
      // console.log(event);
      if (event.getModifierState("CapsLock")) {
        this.capsOn = true;
      } else {
        this.capsOn = false;
      }
    },
  },
  components: {
    Footer,
    Header,
  },
};
</script>
<style scoped></style>
