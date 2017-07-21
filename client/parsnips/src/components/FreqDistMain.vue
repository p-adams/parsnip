<template>
  <div class="freq-dist">

    <ul>
      <li v-for="(data, key) in dataFromServer" :key="key">
        {{data}}
      </li>
    </ul>
      <form
          @submit="sendText"
          :style="{border: '1px solid red'}"
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
        <button type="submit">send text</button>
      </form>
      <freq-dist v-else="textSubmitted"></freq-dist>
    <router-link to="/">return home</router-link>
  </div>
</template>
<script>
import FreqDist from './FreqDist'
export default {
  name: 'freq-dist-main',
  created () {
    fetch('/api')
      .then(response => response.json())
      .then(data => { this.loadData(data)})
      .catch(err => {console.log(err)})
  },
  data () {
    return {
      text: '',
      textSubmitted: false,
      dataFromServer: [],
    }
  },
  methods: {
    loadData (data) {
      this.dataFromServer.push(...data.greetings)
    },
    sendText () {
      console.log(this.text)
      fetch('/api', {
        method: 'post',
        body: {text: this.text, op: 'freq-dist'}
        })
        .then(response => console.log(response))
        .catch(err => console.log(err))
      this.textSubmitted = true
    }
  },
  components: {
    FreqDist
  }
}
</script>
<style>
  textarea {
    resize: none;
  }
</style>


