<template>
    <md-layout md-align="center">
      <md-layout md-flex="45">
        <div>
          <md-input-container class="container">
              <label>Enter text</label>
              <md-textarea
                  maxlength="500"
                  autofocus
              >
              </md-textarea>
            </md-input-container>
        </div>
        <div>
          <div v-for="(token, key) in tokens" :key="key">
           {{token}}
          </div>
        </div>
      </md-layout>
    </md-layout>
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
        console.log(response.data)
        this.tokens.push(response.data)
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
}
</script>
<style scoped>
  .container {
    width: 300px;
    margin-top: 50px;
  }
</style>

