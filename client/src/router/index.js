import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/MainPage'
import FreqDist from '@/components/FreqDistMain'
import Tokenization from '@/components/Tokenization'
import NER from '@/components/NER'
import Lemma from '@/components/Lemma'
import POS from '@/components/POS'
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
      name: 'FrequencyDistribution',
      component: FreqDist
    },
    {
      path: '/tokenization',
      name: 'Tokenization',
      component: Tokenization
    },
    {
      path: '/ner',
      name: 'NER',
      component: NER
    },
    {
      path: '/lemma',
      name: 'Lemma',
      component: Lemma
    },
    {
      path: '/pos',
      name: 'POS',
      component: POS
    }
  ]
})
