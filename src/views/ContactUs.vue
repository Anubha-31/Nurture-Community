<template>
  <Header />
  <div
    class="relative flex items-top justify-center bg-white dark:bg-gray-900 sm:items-center sm:pt-0"
  >
    <div class="max-w-6xl mx-auto sm:px-6 lg:px-8">
      <div class="mt-1 overflow-hidden">
        <div class="grid grid-cols-1 md:grid-cols-2">
          <div class="p-6 mr-2 bg-gray-100 dark:bg-gray-800 sm:rounded-lg">
            <h1
              class="text-4xl sm:text-5xl text-gray-800 dark:text-white font-extrabold tracking-tight"
            >
              Get in touch
            </h1>
            <p
              class="text-normal text-lg sm:text-2xl font-medium text-gray-600 dark:text-gray-400 mt-2"
            >
              Fill in the form to start a conversation
            </p>

            <div
              class="flex items-center mt-8 text-gray-600 dark:text-gray-400"
            >
              <svg
                fill="none"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="1.5"
                viewBox="0 0 24 24"
                class="w-8 h-8 text-gray-500"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1.5"
                  d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"
                />
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1.5"
                  d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"
                />
              </svg>
              <div class="ml-4 text-md tracking-wide font-semibold w-40">
                401 Sunset Ave, Windsor, Canada ON N9B 3P4
              </div>
            </div>

            <div
              class="flex items-center mt-4 text-gray-600 dark:text-gray-400"
            >
              <svg
                fill="none"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="1.5"
                viewBox="0 0 24 24"
                class="w-8 h-8 text-gray-500"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1.5"
                  d="M22.001 13c0-5.522-4.475-10.001-10.002-10.001-5.523 0-10.001 4.479-10.001 10.001 0 4.316 3.087 10 10.001 10 6.93 0 10.002-5.693 10.002-10zm-10.002 8c-4.411 0-8.001-3.59-8.001-8 0-4.413 3.59-8.001 8.001-8.001 4.412 0 8.002 3.588 8.002 8.001 0 4.41-3.59 8-8.002 8zm-1.279-6.817l-2.325-2.183-1.395 1.435 3.746 3.565 6.559-6.592-1.422-1.408-5.163 5.183zm-9.918-5.468c-.535-.824-.802-1.772-.802-2.718 0-2.757 2.233-4.995 4.991-4.995.948 0 1.896.268 2.721.803-3.172 1.217-5.692 3.739-6.91 6.91zm18.201-7.715c-.947 0-1.895.268-2.719.803 3.17 1.218 5.694 3.739 6.914 6.909.534-.823.801-1.77.801-2.717 0-2.761-2.236-4.995-4.996-4.995z"
                />
              </svg>
              <div class="ml-4 text-md tracking-wide font-semibold w-60">
                Mon - Fri 9:00 a.m. - 6:00 p.m
              </div>
            </div>

            <div
              class="flex items-center mt-4 text-gray-600 dark:text-gray-400"
            >
              <svg
                fill="none"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="1.5"
                viewBox="0 0 24 24"
                class="w-8 h-8 text-gray-500"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1.5"
                  d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"
                />
              </svg>
              <div class="ml-4 text-md tracking-wide font-semibold w-40">
                +44 1234567890
              </div>
            </div>

            <div
              class="flex items-center mt-2 text-gray-600 dark:text-gray-400"
            >
              <svg
                fill="none"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="1"
                viewBox="0 0 24 24"
                class="w-8 h-8 text-gray-500"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1"
                  d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
                />
              </svg>
              <div class="ml-4 text-md tracking-wide font-semibold w-40">
                nurturecommunityp13@gmail.com
              </div>
            </div>
          </div>

          <form
            method="POST"
            @submit.prevent="formSubmit"
            class="p-6 flex flex-col justify-center"
          >
            <div class="flex flex-col">
              <label for="name" class="hidden">Full Name</label>
              <input
                type="name"
                name="name"
                id="name"
                v-model="user.name"
                placeholder="Full Name"
                class="w-100 mt-2 py-3 px-3 rounded-lg bg-white dark:bg-gray-800 border border-gray-400 dark:border-gray-700 text-gray-800 font-semibold focus:border-indigo-500 focus:outline-none"
              />
            </div>

            <div class="flex flex-col mt-2">
              <label for="email" class="hidden">Email</label>
              <input
                type="email"
                name="email"
                id="email"
                v-model="user.email"
                placeholder="Email"
                class="w-100 mt-2 py-3 px-3 rounded-lg bg-white dark:bg-gray-800 border border-gray-400 dark:border-gray-700 text-gray-800 font-semibold focus:border-indigo-500 focus:outline-none"
              />
            </div>

            <div class="flex flex-col mt-2">
              <label for="tel" class="hidden">Number</label>
              <input
                type="tel"
                name="tel"
                id="tel"
                v-model="user.tel"
                placeholder="Telephone Number"
                class="w-100 mt-2 py-3 px-3 rounded-lg bg-white dark:bg-gray-800 border border-gray-400 dark:border-gray-700 text-gray-800 font-semibold focus:border-indigo-500 focus:outline-none"
              />
            </div>

            <div class="flex flex-col mt-2">
              <label for="message" class="hidden">Message</label>
              <textarea
                type="message"
                name="message"
                id="message"
                v-model="user.message"
                placeholder="How Can We Help You.."
                class="w-100 mt-2 py-3 px-3 rounded-lg bg-white dark:bg-gray-800 border border-gray-400 dark:border-gray-700 text-gray-800 font-semibold focus:border-indigo-500 focus:outline-none"
              ></textarea>
            </div>

            <button
              type="submit"
              class="md:w-32 bg-yellow-500 hover:bg-yellow-600 text-white font-bold py-3 px-6 rounded-lg mt-3  transition ease-in-out duration-300"
            >
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <Footer />
</template>

<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import axios from "axios";
import { path } from "./settings.js";

export default {
  name: "ContactUs",
  data() {
    return {
      user: {
        name: "",
        email: "",
        tel: "",
        message: "",
      },
    };
  },
  methods: {
    formSubmit: function() {
      axios.defaults.withCredentials = true;
      axios.post(path + "/ContactUs", this.user).then(
        (response) => {
          if(response.status === 200){
            alert("Your query has been registered,\nOur customer care operative will call you within 24-48 hours")
          }
        },
        (error) => {
          console.log(error);
          alert("Please try again later!")
        }
      );
    },
  },
   blurEventHandler: function (e) {
      if(tel(this.user.phone, "CAN").length === 0) {
        this.errors.phone= "Please enter a valid Canadian phone number"
      } else {
        delete this.errors.phone
      }
    },
  components: {
    Header,
    Footer,
  },
};
</script>

<style scoped></style>
