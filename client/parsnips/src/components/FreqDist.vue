<template>
  <div class="freq-dist">

    <ul>
      <li v-for="(data, key) in dataFromServer" :key="key">
      </li>
    </ul>
    {{text}}
    <div>
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
    </div>
    <div>
      <FreqDistChart
          :data="chartData"
          :options="chartOptions"
          :width="400"
          :height="200"
          ></FreqDistChart>
    </div>
    <router-link to="/">return home</router-link>
  </div>
</template>
<script>
import FreqDistChart from './chartcomponents/FreqDistChart.vue'
export default {
  name: 'freq-dist',
  created () {
    console.log(this.$refs.chart)
    fetch('/api')
      .then(response => return response.json())
      .then(data => { this.loadData(data)})
      .catch(err => {console.log(err)})
  },
  data () {
    return {
      text: '',
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
      }
      dataFromServer: []
    }
  },
  methods: {
    loadData (data) {
      this.dataFromServer = data
    },
    sendText () {
      console.log('send text to server')
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


