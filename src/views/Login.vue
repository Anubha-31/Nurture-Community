<template>
  <Header/>
   <div>
    
     <div class="container mx-auto px-1">
      <div class="text-center">
        <h1 class="text-3xl">Login</h1>
        <p class="mt-5">Please provide the information below to sign in as a user/restrurant </p>
      </div>

      <div class="mt-1 md:mt-0 ">
        <form action="#" method="POST">
          <div class="shadow overflow-hidden rounded-lg border border-gray-200">
            <div class="px-4 py-5 bg-white sm:p-6">
              <div class="grid grid-cols-1 gap-1">
                  <div class="col-span-6 sm:col-span-1 ">
                  <label for="Email" class="block text-sm font-medium text-gray-700">Email</label>
                  <input type="Email" name="Email" id="Email" autocomplete="Email" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>
                <div class="col-span-6 sm:col-span-1 ">
                  <label for="Password" class="block text-sm font-medium text-gray-700">Password</label>
                  <input type="Password" name="Password" id="Password" autocomplete="Password" class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md">
                </div>
               <div class="px-4 py-3 bg-gray-50 text-right sm:col-span-1">
              <button class="inline-flex text-white bg-yellow-500 border-0 py-2 px-6 focus:outline-none hover:bg-yellow-600 rounded text-lg">
                      Login
              </button>
            </div>
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

