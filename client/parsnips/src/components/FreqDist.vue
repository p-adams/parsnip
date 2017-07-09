<template>
  <div class="freq-dist">

    <ul>
      <li v-for="(data, key) in dataFromServer" :key="key">
        {{data}}
      </li>
    </ul>
    {{text}}
    <form>
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
    <router-link to="/">return home</router-link>
  </div>
</template>
<script>
export default {
  name: 'freq-dist',
  created () {
    fetch('/api').then(response => {
      return response.json()
    }).then(data => {
      console.log(data)
      this.loadData(data)
    })
  },
  data () {
    return {
      text: '',
      dataFromServer: []
    }
  },
  methods: {
    loadData (data) {
      this.dataFromServer = data
    }
  }
}
</script>
<style>
  textarea {
    resize: none;
  }
</style>


