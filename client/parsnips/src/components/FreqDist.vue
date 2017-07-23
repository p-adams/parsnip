<template>
    <div>
        <md-layout md-align="center" md-gutter="16">
            <md-layout md-flex="50">
                <div class="chart">
                <h5>Frequency Dist chart</h5>
                    <freq-dist-chart
                        :chart-data="chartData"
                        :options="chartOptions"
                        :width="800"
                        :height="500"
                    ></freq-dist-chart>
                <h4>{{dist}}</h4>
                </div>
            </md-layout>
        </md-layout>
    </div>
</template>
<script>
import FreqDistChart from './chartcomponents/FreqDistChart'
import axios from 'axios'
export default {
    name: 'freq-dist',
    props: ['dist'],
    created () {
        console.log(this.dist)
        let labels = []
        let data = []
        this.dist.forEach((freq, word) => {
            //console.log(`freq=${freq} |-| word=${word}`)
            labels.push(word)
            data.push(freq)
        })
        this.chartData.labels = labels
        this.chartData.datasets[0].data = data
    },
    data () {
        return {
            chartData: {
                labels: [],
                datasets: [
                    {
                        label: 'Frequency Distribution',
                        backgroundColor: '#f87979',
                        fontSize: '20px',
                        data: []
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
        populateChart (freq) {
            this.populateLabels(freq)
            this.populateDatasets
        },
        populateLabels (freq) {
            
        },
        populateDatasets (freq) {

        }
    },
    components: {
        FreqDistChart
    }
}
</script>
<style scoped>
    h3 {
        text-align: center;
    }
    .chart {
        border: 1px solid white;
    }
</style>
