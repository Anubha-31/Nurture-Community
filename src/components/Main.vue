<template>
      <section class="text-gray-600 body-font">
        <div class="container mx-auto flex px-5 py-24 md:flex-row flex-col items-center">
            <div class="lg:max-w-lg lg:w-full md:w-1/2 w-5/6 mb-10 md:mb-0">
                <img class="object-cover object-center rounded" alt="hero"
                    src="https://source.unsplash.com/720x600/?restaurant">
            </div>
            <div
                class="lg:flex-grow md:w-1/2 lg:pl-24 md:pl-16 flex flex-col md:items-start md:text-left items-center text-center">
                <h1 class="title-font sm:text-4xl text-3xl mb-4 font-medium text-gray-900">Cutting food waste is a
                    delicious way of saving money, helping to feed the world and protect the planet
                    <h3 class="hidden lg:inline-block">-Tristram Stuart</h3>
                        <br>
                </h1>
                <br>
                <h2 class="mb-8 leading-relaxed">During this time of uncertainty, we have come together as a team to fight back hunger. Join with us to reduce food wastage and provide it to people who truly need it. With your support we can make a difference.</h2>
                <div class="flex justify-center">
                    <router-link to="/login">
                      <button class="inline-flex text-white bg-yellow-500 border-0 py-2 px-6 focus:outline-none hover:bg-yellow-600 rounded text-lg">Login</button>
                    </router-link>
                    <router-link to="/sign-up">
                      <button class="ml-4 inline-flex text-gray-700 bg-gray-100 border-0 py-2 px-6 focus:outline-none hover:bg-gray-200 rounded text-lg">Sign Up</button>
                    </router-link>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {
  name: "Main",
};
</script>