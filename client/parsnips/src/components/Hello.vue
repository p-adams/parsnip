<template>
  <div class="hello">
    <ul>
      <li v-for="(data, index) in dataFromServer" :key="index">
        {{data}}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'hello',
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
