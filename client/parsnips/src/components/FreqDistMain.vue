<template>
  <div class="freq-dist">
    <div :style="{border: '1px solid red'}">
      <div
          v-if="!textSubmitted">
        <textarea
            v-model="text"
            rows="10"
            cols="50"
            autofocus
            spellcheck="true"
        >
        </textarea>
        <br>
        <md-button
          class="md-raised md-primary"
          @click="sendText"
        >send text</md-button>
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
//import FreqDist from './FreqDist'
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
    'freq-dist': () => import('./FreqDist')
  }
}
</script>
<style>
  textarea {
    resize: none;
  }
</style>


