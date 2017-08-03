<template>
  <div class="tok-comp">
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
                  <md-table-cell>
                    <span class="count">{{totalTokens}}</span>
                  </md-table-cell>
                  <md-table-cell>
                    <span class="count">{{totalUniqueTokens}}</span>
                  </md-table-cell>
                  <md-table-row>
                     <md-table-cell>
                      <md-radio v-model="radio5" id="my-test13" name="my-test-group4" md-value="1" class="md-primary">View all tokens</md-radio>
                    </md-table-cell>
                    <md-table-cell>
                       <md-radio v-model="radio5" id="my-test13" name="my-test-group4" md-value="1" class="md-primary">View unique tokens</md-radio>
                    </md-table-cell>
                  </md-table-row>
              </md-table-body>
            </md-table>
             <h4>{{tokenTitle}}</h4>
             <div class="token-chips">
              <span v-for="(token, key) in tokens" :key="key">
                <md-chip class="md-primary">{{token}}</md-chip>
              </span>
            </div>     
        </div>
      </md-layout>
    </md-layout>
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
.count {
  font-weight: bolder;
}
.md-primary {
   margin: 5px;
}
h4 {
  color: #424242;
}
.token-chips {
  height: 200px;
  overflow: scroll;
}

</style>

