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
            <md-table>
              <md-table-header>
                <md-table-row>
                  <md-table-head>Total number of tokens</md-table-head>
                  <md-table-head>Number of unique tokens</md-table-head>
                </md-table-row>
              </md-table-header>
              <md-table-body>
                  <md-table-cell>{{totalTokens}}</md-table-cell>
                  <md-table-cell>{{totalUniqueTokens}}</md-table-cell>
                  <md-table-row :style="{border: '1px solid red'}">
                    <div>
                      <h4>{{tokenTitle}}</h4>
                      <span v-for="(token, key) in tokens" :key="key">
                        <md-chip class="md-primary">{{token}}</md-chip>
                      </span>
                    </div>     
                  </md-table-row>
              </md-table-body>
            </md-table>
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
        this.tokens.push(...response.data)
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  computed: {
    totalTokens () {
      return this.tokens.length
    },
    totalUniqueTokens () {
      return new Set(this.tokens).size
    },
    tokenTitle () {
      return this.tokens.length > 1 || this.tokens.length === 0 ? 'Tokens' : 'Token'
    }
  }
}
</script>
<style scoped>
  .container {
    width: 300px;
    margin-top: 50px;
  }
 .md-primary {
   margin: 5px;
 }
  h4 {
    color: #424242;
  }
</style>

