<template>
  <div>POS Tagger</div>
</template>
<script>
import axios from 'axios' 
export default {
 name: 'pos',
 created() {
     this.loadDemoPOSTags()
 },
 methods: {
     loadDemoPOSTags () {
         axios.get('api/pos')
         .then(res => {
             console.log(res)
         })
         .catch(err => {
             console.log(err)
         })
     }
 }
}
</script>

