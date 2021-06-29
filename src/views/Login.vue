<template>
  <div class="min-h-screen flex flex-col">
    <Header></Header>
    <div class="flex-grow">
      <div class="container mx-auto px-6">
        <div class="text-center">
          <h1 class="title-font sm:text-4xl text-3xl mb-4 font-medium text-gray-900">Login</h1>
          <p class="mt-5">Please provide the information below to sign in as a user or as a restaurant representative </p>
        </div>
        <div class="mt-1 md:mt-10 md:w-1/2 md:mx-auto">
          <form action="#" method="POST">
            <div class="shadow overflow-hidden rounded-lg border border-gray-200">
              <div class="px-4 py-5 bg-white sm:p-6">
                <div class="grid grid-cols-1 gap-1">
                  <div class="col-span-6 sm:col-span-1 ">
                    <label for="Email" class="block text-sm font-medium text-gray-700">Email</label>
                    <input type="Email" name="Email" id="Email" autocomplete="Email" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                  </div>
                  <div class="col-span-6 sm:col-span-1">
                    <label for="Password" class="block text-sm font-medium text-gray-700">Password</label>
                    <input type="Password" name="Password" id="Password" autocomplete="Password" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                  </div>
                </div>
              </div>
              <div class="px-4 sm:px-6 pt-2 pb-10 bg-gray-50 text-right sm:col-span-1">
                <button type="submit" class="inline-flex w-full justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-yellow-500 hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
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
import Footer from "@/components/Footer.vue";
import Header from "@/components/Header";

export default {
  name: "Login",
  components: {
    Footer,
    Header
  },
};
</script>
<style scoped>
</style>