<template>
  <Header />
  <div>
    <div class="container mx-auto px-6 ">
      <div class="text-center">
        <h1 class="text-5xl">Contact Us</h1>
        <!-- <p class="mt-5">Please contact us using the below form.</p> -->
      </div>
    </div>
    <div class="min-h-screen flex flex-col">
      <div class="container mx-auto flex px-5 py-12 md:flex-row flex-col items-center text-gray-600 body-font">
        <div class="lg:max-w-lg lg:w-full md:w-1/2 w-5/6 mb-10 md:mb-0 items-center text-center">
          <h2 class="text-2xl">LOCATION</h2>
          <p>401 Sunset Ave, Windsor, ON N9B 3P4, Canada</p>
          <br />
          <h2 class=" text-2xl">TIME</h2>
          <p>Mon - Fri 9:00 a.m. - 6:00 p.m</p>
          <br />
          <h2 class="text-2xl">CONTACT</h2>
          <p>9814628320</p>
          <p>support@nurturecommunity.com</p>
        </div>
        

        <div
          class="lg:flex-grow md:w-auto md:pl-16 flex flex-col md:items-start md:text-left items-center text-center">
            <form action="#" method="POST" class="w-full">
              <div class="lg:max-w-xl lg:w-auto md:w-auto mb-10 md:mb-0 shadow overflow-hidden rounded-lg border border-gray-200" >
                <div class="px-4 py-5 bg-white sm:p-6">
                  <div class="grid grid-cols-1 gap-6">
                    <div class="col-span-6">
                      <label
                        for="first_name"
                        class="block text-sm font-medium text-gray-2000"
                        >First Name</label
                      >
                      <input
                        type="text"
                        name="first_name"
                        id="first_name"
                        class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                      />
                    </div>
                    <div class="col-span-6">
                      <label
                        for="last_name"
                        class="block text-sm font-medium text-gray-700"
                        >Last Name</label
                      >
                      <input
                        type="text"
                        name="last_name"
                        id="last_name"
                        class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block shadow-sm sm:text-sm border border-gray-300 rounded-md"
                      />
                    </div>

                    <div class="col-span-6 ">
                      <label
                        for="Email"
                        class="block text-sm font-medium text-gray-700"
                        >Email</label
                      >
                      <input
                        type="Email"
                        name="Email"
                        id="Email"
                        autocomplete="Email"
                        class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                      />
                    </div>

                    <div class="col-span-6">
                      <label
                        for="Item_Descrption"
                        class="block text-sm font-medium text-gray-700"
                        >Subject</label
                      >
                      <input
                        type="text"
                        name="Item_Descrption"
                        id="Item_Descrption"
                        autocomplete="Item-Descrption"
                        class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                      />
                    </div>

                    <div class="col-span-6">
                      <label
                        for="Message"
                        class="block text-sm font-medium text-gray-700"
                        >Message</label
                      >
                      <textarea
                        name="Message"
                        id="Message"
                        class="mt-1 p-2 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border border-gray-300 rounded-md"
                      ></textarea>
                    </div>
                  </div>
                </div>
                <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
                  <button
                    class="inline-flex text-white bg-yellow-500 border-0 py-2 px-6 focus:outline-none hover:bg-yellow-600 rounded text-lg"
                  >
                    Submit
                  </button>
                </div>
              </div>
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

export default {
  name: "ContactUs",
  data() {
    return {
      user_type: "",
    };
  },
  components: {
    Header,
    Footer,
  },
};
</script>

<style scoped></style>
