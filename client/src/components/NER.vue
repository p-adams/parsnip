<template>
    <div>
        <md-layout md-align="center">
            <h2>Named-Entity Recognition</h2>
            <md-layout md-flex="45">
                <div>
                <md-input-container class="container">
                    <label>Enter text</label>
                    <md-textarea
                        maxlength="500"
                        autofocus
                    >
                    </md-textarea>
                    </md-input-container>
                <md-button
                    class="md-raised"
                    >Create Tags</md-button>
                </div>
            </md-layout>
        </md-layout>
        <md-table class="table" v-once>
            <md-table-body>
                <md-table-row>
                    <md-layout md-gutter>
                        <md-layout class="cell" md-align="center" :style="{background: '#e3f2fd', border: '1px solid red'}" md-flex="50">
                            <ner-chart
                                :chart-data="chartData"
                                :options="chartOptions"
                                :height="300"
                            ></ner-chart>
                        </md-layout>
                        <md-layout class="cell" md-align="center" :style="{background: 'grey'}" md-flex="50">
                            <ul>
                                <li v-for="(t, key) in people" :key="key">
                                    {{t}} {{key}}
                                </li>
                            </ul>
                        </md-layout>
                    </md-layout>         
                </md-table-row>
            </md-table-body>
        </md-table>
    </div>
</template>
<script>
import NerChart from './chartcomponents/nerChart'
import axios from 'axios'
export default {
    created () {
        this.fillPie()
        this.fetchDemoTags()
    },
    mounted () {
       
    },
    data () {
        return {
           chartData: null,
           chartOptions: null,
           isLoading: true,
           people: [],
           organizations: [],
           locations: [],
           other: [],
        }
    },
    methods: {
        fillPie () {
            this.chartData = {
                labels: ['PERSON', 'OCCUPATION', 'LOCATION'],
                datasets: [
                    {
                        label: 'Data One',
                        backgroundColor: ['#FF6384', '#36A2EB', '#FFCD56'],
                        data: []
                    }
                ]
            }
            this.chartOptions = {
                responsive: true,
                maintainAspectRatio: false
            }
        },
        fetchDemoTags () {
            axios.get('api/ner')
            .then(res => {
                Object.keys(res.data).forEach(key => {
                if (res.data[key] === 'PERSON') {
                    this.people.push(key)
                    this.chartData.datasets[0].data[0] = this.people.length
                } else if (res.data[key]  === 'ORGANIZATION') {
                    this.organizations.push(key)
                    this.chartData.datasets[0].data[1] = this.organizations.length
                        
                } else if (res.data[key]  === 'LOCATION') {
                    this.locations.push(key)
                     this.chartData.datasets[0].data[2] = this.locations.length
                } else {
                    this.other.push(key)
                }
                })
            })
            .catch(err => {
                console.log(err)
            })
        }
    },
    components: {
      NerChart
  }
}
</script>
<style>
    h2 {
        color: #424242;
    }
    .container {
        margin-top: 40px;
    }
    .table {
        margin-top: 30px;
    }
    .cell {
        height: 50vh;
    }
    .md-raised {
        float: right;
    }
</style>

