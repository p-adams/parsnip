
import {Bar, mixins} from 'vue-chartjs'
const {reactiveProp} = mixins
export default Bar.extend({
    name: 'freq-dist-chart',
    mixins: [reactiveProp],
    props: ['options'],
    mounted () {
        this.renderChart(this.chartData, this.options)
    }
})
