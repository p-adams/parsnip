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
                <div v-for="(foo, index) in lSub" :key="index">
                    <div v-for="(meow, index) in foo" :key="index">
                        <span :style="{border: '1px solid red'}">
                            {{meow}}
                        </span>
                    </div>
                </div>

                <div v-for="(foo, index) in rSub" :key="index">
                    <div v-for="(meow, index) in foo" :key="index">
                        {{meow}}
                    </div>
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
      this.fetchDemoDeps()
  },
  data () {
      return {
          text: '',
          isLoading: true,
          clicked: false,
          demoText: 'Colorless green ideas sleep furiously',
          result: '',
          lSub: [],
          rSub: []
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
            console.log(TreeProcessor(res.data))
            let tree = TreeProcessor(res.data)[1]
            
            this.lSub.push(tree[1])
            this.rSub.push(tree[2])
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

