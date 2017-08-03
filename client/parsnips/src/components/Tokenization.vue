<template>
  <div class="tok-comp">
    <md-layout md-align="center">
      <md-layout md-flex="45">
        <div>
          <md-input-container class="container">
              <label>Enter text</label>
              <md-textarea
                  v-model="text"
                  maxlength="500"
                  autofocus
              >
              </md-textarea>
            </md-input-container>
          <md-button
            class="md-raised"
            @click="tokenize"
            :disabled="text.length === 0"
            >Tokenize</md-button>
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
                      <md-radio
                        v-model="tokenOption"
                        id="show-all"
                        name="show-tokens"
                        md-value="1"
                        class="md-primary"
                      >View all</md-radio>
                    </md-table-cell>
                    <md-table-cell>
                       <md-radio
                        v-model="tokenOption"
                        id="show-unique"
                        name="show-tokens"
                        md-value="2"
                        class="md-primary"
                      >View unique</md-radio>
                    </md-table-cell>
                  </md-table-row>
              </md-table-body>
            </md-table>
             <h4>{{tokenTitle}}</h4>
             <div class="token-chips">
              <span v-for="(token, key) in showTokens" :key="key">
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
      tokenOption: '1',
      text: '',
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
    },
    tokenize () {
      this.tokens = []
      axios.post('api/tokenization', {
        data: this.text
      })
      .then(res => {
        console.log(res.data)
        this.tokens.push(...res.data)
      })
      .catch(err => {
        console.log(err)
      })
      this.text = ''
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
    },
    showTokens() {
      return this.tokenOption === '1' ? this.tokens : Array.from(new Set(this.tokens))
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

