import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/MainPage'
import FreqDist from '@/components/FreqDistMain'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css';
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
Vue.use(VueMaterial)
Vue.use(Router)
Vue.use(Vuetify)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: MainPage
    },
    {
      path: '/freqdist',
      name: 'FreqDist',
      component: FreqDist
    }
  ]
})
