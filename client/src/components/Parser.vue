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
            </md-layout>
        </md-layout>
</template>
<script>
import axios from 'axios'
export default {
  name: 'dep-parser',
  created () {
      this.fetchDemoDeps()
  },
  data () {
      return {
          text: '',
          clicked: false,
          demoText: 'Colorless green ideas sleep furiously'
      }
  },
  methods: {
      parse () {},
      fetchDemoDeps () {
        axios.get('api/parse')
        .then(res => {
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

