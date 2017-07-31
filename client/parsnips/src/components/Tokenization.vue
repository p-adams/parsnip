<template>
  <div>
    Tokenization component
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'tokenization',
  created () {
    this.fetchDemoTokens()
  },
  data () {
    return {
      tokens: []
    }
  },
  methods: {
    fetchDemoTokens () {
      axios.get('api/tokenization')
      .then(response => {
        console.log(response)
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
}
</script>
