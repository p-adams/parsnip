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
                    :disabled="text.length===0"
                    >lemmatize</md-button>
                    <ul>
                        <li v-for="(lemma, index) in lemmas" :key="index">
                            {{lemma}}
                        </li>
                    </ul>
                </div>
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
        lemmas: []
      }
  },
  methods: {
    fetchDemoLemmas () {
        axios.get('api/lemma')
        .then(res => {
            console.log(res)
            Object.keys(res.data).forEach(key => {
                console.log(key, res.data[key])
                this.lemmas.push({word: res.data[key], count: key})
            })
        })
        .catch(err => {
            console.log(err)
        })
    }
  }
}
</script>
<style>
     .container {
        margin-top: 75px;
    }
</style>

