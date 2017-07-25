<template>
    <div>
        <md-layout md-align="center" md-gutter="16">
            <md-layout md-flex="90">
                <div class="chart">
                        <freq-dist-chart
                            :style="{backgroundColor: 'white'}"
                            :chart-data="chartData"
                            :options="chartOptions"
                            :width="1000"
                            :height="500"
                        ></freq-dist-chart>
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
                        data: []
                    }
                ]
            },
            chartOptions: {
                responsive: false, 
                maintainAspectRation: false,
                scales: {
                    xAxes: [{
                            display: true,
                            gridLines: {
                                display: false,
                                color: "black"
                            },
                            scaleLabel: {
                                display: true,
                                labelString: 'words'
                            },
                            ticks: {
                                fontSize: 9,
                                fontColor: 'black',
                            }
                        }],
                    yAxes: [{
                            display: true,
                            gridLines: {
                                display: false,
                                color: "black"
                            },
                            scaleLabel: {
                                display: true,
                                padding: 10,
                                labelString: 'frequency'
                            },
                            ticks: {
                                drawBorder: true,
                                beginAtZero: true,
                                max: 20,
                                stepSize: 1,
                                fontSize: 12,
                                fontColor: 'black',
                                padding: 10
                            }
                        }]
                }
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
        padding: 5px;
        margin-top: 50px;
        margin-left: 5%;
       
    }
</style>
