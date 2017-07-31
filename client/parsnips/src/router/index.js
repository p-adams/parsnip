import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/MainPage'
import FreqDist from '@/components/FreqDistMain'
import Tokenization from '@/components/Tokenization'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
Vue.use(Router)
Vue.use(VueMaterial)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: MainPage
    },
    {
      path: '/freqdist',
      name: 'frequencydistribution',
      component: FreqDist
    },
    {
      path: '/tokenization',
      name: 'Tokenization',
      component: Tokenization
    }
  ]
})
