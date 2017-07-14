<template>
  <div class="freq-dist">

    <ul>
      <li v-for="(data, key) in dataFromServer" :key="key">
        {{data}}
      </li>
    </ul>
      <form :style="{border: '1px solid red'}" v-if="!textSubmitted">
        <textarea
            v-model="text"
            rows="10"
            cols="50"
            autofocus
            spellcheck="true"
        >
        </textarea>
        <br>
        <button @click="sendText">send text</button>
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


