<template>
  <div class="freq-dist" :style="{border: '1px solid red'}">
    <div>
      <div
          v-if="!textSubmitted">
        <h5 class="warn" v-if="doesExceed">* Input exceeds 215 characters</h5>
        <h5 v-else>characters: {{text.length}}</h5>
        <textarea
            v-model="text"
            rows="10"
            cols="50"
            autofocus
            placeholder="enter upto 215 characters"
        >
        </textarea>
        <br>
        <md-button
          class="md-raised md-primary"
          @click="sendText"
          :disabled="text.length === 0 || doesExceed"
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
      inputExceeds: false,
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
  computed: {
    doesExceed() {
      return this.text.length > 250
    }
  },
  components: {
    'freq-dist': () => import('./FreqDist')
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
</style>


