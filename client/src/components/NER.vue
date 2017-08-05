<template>
    <div>
        <md-layout md-align="center">
            <h2>Named-Entity Recognition</h2>
            <md-layout md-flex="45">
                <div>
                <md-input-container class="container">
                    <label>Enter text</label>
                    <md-textarea
                        v-model="text"
                        maxlength="500"
                        autofocus
                    >
                    </md-textarea>
                    </md-input-container>
                <md-button
                    class="md-raised"
                    @click="getTags"
                    :disabled="text.length===0"
                    >Create Tags</md-button>
                </div>
            </md-layout>
        </md-layout>
        <md-table class="table" v-once>
            <md-table-body>
                <md-table-row>
                    <md-layout md-gutter>
                        <md-layout class="cell" md-align="center" :style="{borderRight: '1px solid #b0bec5'}" md-flex="50">
                            <ner-chart
                                :chart-data="chartData"
                                :options="chartOptions"
                                :height="300"
                            ></ner-chart>
                        </md-layout>
                        <md-layout class="cell" md-align="center" :style="{color: 'gray'}" md-flex="50">
                            <ner-tags :nertags="results"></ner-tags>
                        </md-layout>
                    </md-layout>         
                </md-table-row>
            </md-table-body>
        </md-table>
    </div>
</template>
<script>
import NerChart from './chartcomponents/nerChart'
import NerTags from './NERTags'
import axios from 'axios'
export default {
    created () {
        this.fillPie()
        this.fetchDemoTags()
    },
    data () {
        return {
            text: '',
            chartData: null,
            chartOptions: null,
            people: [],
            organizations: [],
            locations: [],
            results: [],
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
                    this.results.push({word: key, tag: res.data[key]})
                    if (res.data[key] === 'PERSON') {
                        this.people.push(key)
                        this.chartData.datasets[0].data[0] = this.people.length
                    } else if (res.data[key]  === 'ORGANIZATION') {
                        this.organizations.push(key)
                        this.chartData.datasets[0].data[1] = this.organizations.length
                            
                    } else if (res.data[key]  === 'LOCATION') {
                        this.locations.push(key)
                        this.chartData.datasets[0].data[2] = this.locations.length
                    }
                })
            })
            .catch(err => {
                console.log(err)
            })
        },
        getTags () {
            this.emptyArrays()
            axios.post('api/ner', {data: this.text})
            .then(res => {
                console.log(res.data)
                 Object.keys(res.data).forEach(key => {
                    this.results.push({word: key, tag: res.data[key]})
                    if (res.data[key] === 'PERSON') {
                        this.people.push(key)
                        this.chartData.datasets[0].data[0] = this.people.length
                    } else if (res.data[key]  === 'ORGANIZATION') {
                        this.organizations.push(key)
                        this.chartData.datasets[0].data[1] = this.organizations.length
                            
                    } else if (res.data[key]  === 'LOCATION') {
                        this.locations.push(key)
                        this.chartData.datasets[0].data[2] = this.locations.length
                    }
                })
            })
            .catch(err => {
                console.log(err)
            })
            this.text = ''
        },
        emptyArrays () {
            this.results = []
        }

    },
    components: {
      NerChart,
      NerTags
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
        background: #e3f2fd;
    }
    .md-raised {
        float: right;
    }
</style>

