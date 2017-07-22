<template>
    <div>
        Frequency Dist chart
        <freq-dist-chart
          :data="chartData"
          :options="chartOptions"
          :width="400"
          :height="200"
        ></freq-dist-chart>
    {{dist}}
    </div>
</template>
<script>
import FreqDistChart from './chartcomponents/FreqDistChart'
import axios from 'axios'
export default {
    name: 'freq-dist',
    props: ['dist'],
    mounted () {
        console.log(this.dist)
    },
    data () {
        return {
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
        /*loadWordFreq () {
            axios.get('/api/freq-dist')
                .then(res => {
                    console.log(`meow: ${res.data}`)
                })
                .catch(err => {
                    console.log(err)
                })
        }*/
    },
    components: {
        FreqDistChart
    }
}
</script>