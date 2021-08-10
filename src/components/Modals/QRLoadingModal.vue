<template>
  <transition name="modal-fade">
    <div class="modal-backdrop" role="dialog">
      <div class="bg-white rounded-lg w-4/5 md:w-1/4">
        <div class="flex flex-col items-start p-4">
          <div class="flex items-center w-full justify-center">
            <h1 class="text-gray-900 font-medium text-lg text-center">
             Updating Data! Please Wait...
            </h1>
          </div>
          <hr>
          <div v-if="status = 'loading'">
            <img src="./../../assets/gifs/loading.gif" alt="" class="w-1/2 mx-auto">
          </div>
          <div v-else-if="status = 'failed'">
            Scanning failed! :(
          </div>
          <hr>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "QRLoadingModal"
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