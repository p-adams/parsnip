
import {Bar} from 'vue-chartjs'
export default Bar.extend({
    name: 'freq-dist-chart',
    props: ['data', 'options'],
    mounted () {
        this.renderChart(this.data, this.options)
    },
    template: `<div>Can I have template?</div>`
})
