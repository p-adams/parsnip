<template>
    <div>
        <div class="chart">
            <freq-dist-chart
                :style="{backgroundColor: 'white', padding: '10px'}"
                :chart-data="chartData"
                :options="chartOptions"
                :width="1000"
                :height="500"
            ></freq-dist-chart>
            <md-layout md-gutter :style="{background: '#3F51B5'}">
                <md-layout
                    md-flex="25"
                    md-flex-offset="40"
                >
                    <md-button @click="this.refresh">Select new text</md-button>
                </md-layout>
                <md-layout md-flex="25"></md-layout>
            </md-layout>
        </div>
    </div>
</template>
<script>
import FreqDistChart from './chartcomponents/FreqDistChart'
import axios from 'axios'
export default {
    name: 'freq-dist',
    props: ['dist', 'refresh'],
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
                        label: 'Word Frequency Distribution',
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
                                fontSize: 8,
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
        width: 800px;
        margin-top: 50px;
        margin-left: -100px;
        box-shadow: 5px 5px 5px #616161;
    }
</style>
