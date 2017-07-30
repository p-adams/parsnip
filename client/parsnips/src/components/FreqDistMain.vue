<template>
    <md-layout md-align="center">
      <md-layout md-flex="45">
          <div v-if="!textSubmitted">
            <md-input-container class="container">
              <label v-show="text.length === 0">Enter text</label>
              <md-textarea
                  v-model="text"
                  maxlength="215"
                  autofocus
              >
              </md-textarea>
            </md-input-container>
            <br>
            <md-button
              @click="sendText"
              :disabled="text.length === 0 || text.length > 215"
            >send text</md-button>
          </div>
          <freq-dist
              :dist="res"
              :refresh="refreshText"
              v-else-if="textSubmitted && !isLoading"
            ></freq-dist>
        </md-layout>
      </md-layout>
</template>
<script>
import FreqDist from './FreqDist'
import axios from 'axios'
export default {
  name: 'freq-dist-main',
  data () {
    return {
      text: '',
      isLoading: true,
      res: new Map(),
      textSubmitted: false,
      dataFromServer: [],
    }
  },
  methods: {
    sendText (e) {
      e.preventDefault()
      axios.post('/api/freq-dist', {data: this.text})
        .then(response => {
          console.log(response)
          Object.keys(response.data).forEach(key => {
              //console.log(key, response.data[key])
              this.res.set(key, response.data[key])
          })
          this.isLoading = false
          this.textSubmitted = true
          })
        .catch(err => console.log(err))
    },
    refreshText () {
      this.textSubmitted = false
      this.isLoading = false
      this.text = ""
      this.res.clear()
    }
  },
  components: {
    FreqDist
  }
}
</script>
<style>
 .container {
    margin-top: 50px;
  }
</style>


