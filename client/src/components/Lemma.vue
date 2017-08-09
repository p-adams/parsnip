<template>
  <div>
       <md-layout md-align="center">
            <h2>Lemmatizer</h2>
            <md-layout md-flex="45">
                <div>
                <md-input-container class="container">
                    <label>Enter text</label>
                    <md-textarea
                        v-model="text"
                        maxlength="250"
                        autofocus
                    >
                    </md-textarea>
                    </md-input-container>
                <md-button
                    class="md-raised"
                    @click="lemmatize"
                    :disabled="text.length===0"
                    >lemmatize</md-button>
                <p v-if="!clicked">Input: {{demoText}}</p>
                <p v-else>Input: {{text}}</p>
                </div>
                <md-table v-if="!isLoading">
                    <md-table-header>
                        <md-table-row>
                        <md-table-head>Lemmatization results</md-table-head>
                        <md-table-head>Lemma</md-table-head>
                        <md-table-head md-numeric>Frequency</md-table-head>
                        </md-table-row>
                    </md-table-header>

                    <md-table-body>
                        <md-table-row v-for="(row, index) in lemmas" :key="index">
                            <md-table-cell>
                                <span> Original: {{!clicked ? demoTextToTokens[index] : textToTokens[index]}} </span>
                                </md-table-cell>
                            <md-table-cell v-for="(col, index) in row" :key="index">
                               {{col}}
                            </md-table-cell>
                        </md-table-row>
                    </md-table-body>
                    </md-table>
            </md-layout>
        </md-layout>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'lemma',
  created () {
      this.fetchDemoLemmas()
  },
  data () {
      return {
        text: '',
        clicked: false,
        isLoading: true,
        demoText: 'The monkeys jumped from branch to branch',
        lemmas: []
      }
  },
  methods: {
    lemmatize () {
        this.lemmas = []
        this.clicked = true
        axios.post('api/lemma', {data: this.text})
        .then(res => {
            Object.keys(res.data).forEach(key => {
                console.log(key, res.data[key])
                this.lemmas.push({lemma: key, count: res.data[key]})
            })
            this.isLoading = false
        })
        .catch(err => {
            console.log(err)
        })
    },
    fetchDemoLemmas () {
        axios.get('api/lemma')
        .then(res => {
            console.log(res)
            Object.keys(res.data).forEach(key => {
                console.log(key, res.data[key])
                this.lemmas.push({lemma: key, count: res.data[key]})
            })
            this.isLoading = false
        })
        .catch(err => {
            console.log(err)
        })
    }
  },
  computed: {
      textToTokens () {
          return this.text.split(' ')
      },
      demoTextToTokens () {
          return this.demoText.split(' ')
      }
  }
}
</script>
<style>
     .container {
        margin-top: 75px;
    }
</style>

