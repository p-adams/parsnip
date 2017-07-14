<template>
  <div class="freq-dist">

    <ul>
      <li v-for="(data, key) in dataFromServer" :key="key">
        {{data}}
      </li>
    </ul>
      <form v-if="!textSubmitted">
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
      <freq-dist-chart v-else="textSubmitted"
          :data="chartData"
          :options="chartOptions"
          :width="400"
          :height="200"
      ></freq-dist-chart>
    <router-link to="/">return home</router-link>
  </div>
</template>
<script>
import FreqDistChart from './chartcomponents/FreqDistChart'
export default {
  name: 'freq-dist',
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
      chartData: {
        labels: ['January', 'February'],
        datasets: [
          {
            label: 'Github Commits',
            backgroundColor: '#f87979',
            data: [40, 20]
          }
        ]
      },
      chartOptions: {
          responsive: false, 
          maintainAspectRation: false
      },
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
    FreqDistChart
  }
}
</script>
<style>
  textarea {
    resize: none;
  }
</style>


