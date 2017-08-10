<template>
  <md-layout md-align="center">
            <h2>Parser</h2>
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
                    class="md-raised md-primary"
                    @click="parse"
                    :disabled="text.length===0"
                    >tag</md-button>
                <p v-if="!clicked">Input: {{demoText}}</p>
                <p v-else>Input: {{text}}</p>
                </div>
                 <md-card v-if="!isLoading">
                    <md-card-content>
                        {{result}}
                    </md-card-content>
                </md-card>
                <div>
                </div>
            </md-layout>
        </md-layout>
</template>
<script>
import axios from 'axios'
export default {
  name: 'parser',
  created () {
      this.fetchDemoDeps()
  },
  data () {
      return {
          text: '',
          isLoading: true,
          clicked: false,
          demoText: 'Colorless green ideas sleep furiously',
          result: ''
      }
  },
  methods: {
      parse () {
        this.clicked = true
        this.result = ''
        axios.post('api/parse', {data: this.text})
        .then(res => {
            this.result = res.data
        })
        .catch(err => {
            console.log(err)
        })
      },
      fetchDemoDeps () {
        axios.get('api/parse')
        .then(res => {
            this.result = res.data
            this.isLoading = false
            console.log(res.data)
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

