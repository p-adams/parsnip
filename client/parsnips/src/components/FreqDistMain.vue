<template>
  <div class="freq-dist" :style="{border: '1px solid red'}">
    <div>
      <div v-if="!textSubmitted">
      <md-whiteframe :style="{background: 'white'}" md-elevation="24">
        <md-layout md-align="center" md-gutter="16">
          <md-layout md-flex="35">
              <md-input-container class="text-area">
                <label>Enter text</label>
                  <md-textarea
                      v-model="text"
                      rows="10"
                      cols="50"
                      maxlength="215"
                      autofocus
                  >
                </md-textarea>
              </md-input-container>
            </md-layout>
          </md-layout>
        <br>
        <md-button
          class="md-raised md-primary"
          @click="sendText"
          :disabled="text.length === 0 || text.length > 215"
        >send text</md-button>
        </md-whiteframe>
      </div>
      <freq-dist
          :dist="res"
          v-else-if="textSubmitted && !isLoading"
        ></freq-dist>
    </div>
    <router-link to="/">return home</router-link>
  </div>
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
    }
  },
  components: {
    FreqDist
  }
}
</script>
<style>
  .freq-dist {
    height: 100vh;
  }
  textarea {
    resize: none;
  }
  .warn {
    color: red;
  }
  .text-area {
    border: 1px solid white;
  }
</style>


