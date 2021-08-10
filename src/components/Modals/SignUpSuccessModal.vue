<template>
  <transition name="modal-fade">
    <div class="modal-backdrop" role="dialog">
      <div class="bg-white rounded-lg w-4/5 md:w-1/4">
        <div class="flex flex-col items-start p-4">
          <div class="flex items-center w-full justify-center">
            <h1 class="text-gray-900 font-medium text-lg text-center">
              Congratulations!
            </h1>
          </div>
          <hr>
          <h2 class="text-center mx-auto mt-2">You have signed Up successfully!</h2>
          <router-link to="/login" class="w-full">
            <button type="submit" class="mt-4 w-full justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-green-600 hover:bg-green-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500">
              Take me to Login
            </button>
          </router-link>
          <hr>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
name: "SignUpSuccessModal"
}
</script>

<style scoped>
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background: #ffffff;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}



.modal-footer {
  border-top: 1px solid #eeeeee;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}




.modal-fade-enter,
.modal-fade-leave-active {
  opacity: 0;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.5s ease;
}
</style>