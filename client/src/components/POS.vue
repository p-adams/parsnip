<template>
  <div>POS Tagger
  <ul>
      <li v-for="(tag, index) in posTags" :key="index">
          {{tag.word}} - {{tag.tag}} - {{tag.tagset}}
      </li>
  </ul>
  </div>
</template>
<script>
import axios from 'axios'
import {TagSet} from './tagset'
export default {
 name: 'pos',
 created() {
     this.loadDemoPOSTags()
 },
 data () {
     return {
         posTags: [],
     }
 },
 methods: {
     loadDemoPOSTags () {
         axios.get('api/pos')
         .then(res => {
             console.log(res)
             Object.keys(res.data).forEach(key => {
                 console.log(TagSet(res.data[key]))
                 this.posTags.push({word: key, tag: res.data[key], tagset: TagSet(res.data[key])})
             })
         })
         .catch(err => {
             console.log(err)
         })
     }
 }
}
</script>

