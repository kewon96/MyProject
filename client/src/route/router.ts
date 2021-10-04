// @ts-ignore
import { createWebHistory, createRouter } from 'vue-router';

import Home from '@/components/HomeComponent.vue';
import Login from '@/components/login/LoginComponent.vue';

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
    }
];

export const router = createRouter({
    history: createWebHistory(),
    routes
});