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
                        <p class="res">{{result}}</p>
                    </md-card-content>
                </md-card>
                <div>
                </div>
            </md-layout>
        </md-layout>
</template>
<script>
import axios from 'axios'
import {TreeProcessor} from './TreeProcessor'
export default {
  name: 'parser',
  created () {
      this.fetchDemoTree()
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
            this.result = res.data.tree
        })
        .catch(err => {
            console.log(err)
        })
      },
      fetchDemoTree () {
        axios.get('api/parse')
        .then(res => {
            this.result = res.data.tree
            this.isLoading = false
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
    .res {
        font-size: 25px;
        color: #616161;
    }
</style>

