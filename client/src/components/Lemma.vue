<template>
  <div>
       <md-layout md-align="center">
            <h2>Lemmatization</h2>
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
                    @click="getLemmas"
                    :disabled="text.length===0"
                    >lemmatize</md-button>
                </div>
            </md-layout>
        </md-layout>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'lemma',
  data () {
      return {
        text: ''
      }
  },
  methods: {
    fetchDemoLemmas () {
        axios.get('api/lemma')
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
<style>
     .container {
        margin-top: 75px;
    }
</style>

