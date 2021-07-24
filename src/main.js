import { createApp } from 'vue'
import { createStore } from 'vuex'
import App from './App.vue'
import router from './router'
import './assets/tailwind.css'

// Create a new store instance.
const store = createStore({
    state () {
        return {
            count: 0,
            user_type: "restaurant"
        }
    },
    mutations: {
        increment (state) {
            state.count++
        },
        assignUserType (state, type) {
            state.user_type = type
        }
    }
})

const app = createApp(App)

app.use(router)
app.use(store)
app.mount('#app')
