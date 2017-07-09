<template>
  <div class="freq-dist">
    <ul>
      <li v-for="(data, key) in dataFromServer" :key="key">
        {{data}}
      </li>
    </ul>
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
      msg: 'Welcome to Your Vue.js App',
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

