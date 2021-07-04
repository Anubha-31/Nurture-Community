import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import SignUp from "@/views/SignUp";
import Login from "@/views/Login";
import AddPost from "@/views/AddPost";
import ContactUs from "@/views/ContactUs"

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/sign-up',
    name: 'Sign Up',
    component: SignUp
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/post',
    name: 'AddPost',
    component: AddPost
  },
  {
    path: '/ContactUs',
    name: 'ContactUs',
    component: ContactUs
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
