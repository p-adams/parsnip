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
                    >Label words</md-button>
                </div>
            </md-layout>
        </md-layout>
        <md-table class="table" v-once>
            <md-table-body>
                <md-table-row>
                    <md-layout md-gutter>
                        <md-layout class="cell" md-align="center" :style="{background: 'grey', border: '1px solid red'}" md-flex="50">
                            baz
                            <ner-chart
                                :chart-data="chartData"
                                :options="chartOptions"
                                :height="300"
                            ></ner-chart>
                        </md-layout>
                        <md-layout class="cell" md-align="center" :style="{background: 'grey'}" md-flex="50">quux</md-layout>
                    </md-layout>         
                </md-table-row>
            </md-table-body>
        </md-table>
    </div>
</template>
<script>
import NerChart from './chartcomponents/nerChart'
export default {
    created () {
        this.fillPie()
    },
    data () {
        return {
           chartData: null,
           chartOptions: null
        }
    },
    methods: {
        fillPie () {
            this.chartData = {
                labels: ['January', 'February', 'March'],
                datasets: [
                    {
                        label: 'Data One',
                        backgroundColor: '#FC2525',
                        data: [40, 39, 40]
                    }
                ]
                }
            this.chartOptions = {responsive: false, maintainAspectRatio: false}
            
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

