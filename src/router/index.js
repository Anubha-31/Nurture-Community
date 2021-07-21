import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import SignUp from "@/views/SignUp";
import Login from "@/views/Login";
import CreateFoodItem from "@/views/FoodItem/CreateFoodItem";
import ContactUs from "@/views/ContactUs"
import RestaurantList from "@/views/RestaurantList";
import Upload from "@/views/Upload";

import FoodItem from "@/views/FoodItem/FoodItem";
import EditFoodItem from "@/views/FoodItem/EditFoodItem";

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
    path: '/restaurant/food-items',
    name: 'FoodItem',
    component: FoodItem,
  },
  {
    path: '/food-item/create',
    name: 'CreateFoodItem',
    component: CreateFoodItem
  },
  {
    path: '/food-item/:id/edit',
    name: 'EditFoodItem',
    component: EditFoodItem,
    props: true
  },
  {
    path: '/ContactUs',
    name: 'ContactUs',
    component: ContactUs
  },
   {
    path: '/restaurant',
    name: 'Restaurant List',
    component: RestaurantList
  },
  {
    path: '/upload',
    name: 'Upload',
    component: Upload
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
