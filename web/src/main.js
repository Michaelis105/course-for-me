import Vue from 'vue'
import VueRouter from 'vue-router'
import vuetify from './plugins/vuetify';
import Home from './views/Home.vue'

Vue.use(VueRouter)

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/register',
            name: 'Register',
            component: () => import('./views/Register.vue')
        },
        {
            path: '/status',
            name: 'Status',
            component: () => import('./views/Status.vue')
        },
        {
            path: '/schedule',
            name: 'Schedule',
            component: () => import('./views/Schedule.vue')
        },
        {
            path: '/plan',
            name: 'Plan',
            component: () => import('./views/Plan.vue')
        },
        {
            path: '/profile',
            name: 'Profile',
            component: () => import('./views/Profile.vue')
        },
        {
            path: '/quick',
            name: 'Quick',
            component: () => import('./views/Quick.vue')
        },
        {
            path: '/settings',
            name: 'Settings',
            component: () => import('./views/Settings.vue')
        }
    ]
});

Vue.config.productionTip = false
new Vue({
  vuetify,
  router,
  render: h => h(Home)
}).$mount('#home')
