import { createWebHistory, createRouter } from 'vue-router';

import Home from '@/components/home/HomeComponent.vue';
import Login from '@/components/home/LoginComponent.vue';
import Register from '@/components/home/RegisterComponent.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    }
];

export const routers = createRouter({
    history: createWebHistory(),
    routes
});