
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

// Sample text for large input

/*Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut suscipit tincidunt convallis. Fusce molestie, erat vitae maximus placerat, arcu metus ultrices tortor, eu volutpat mi mauris vitae tortor. Nam sodales molestie tellus, sit amet aliquet eros feugiat id. Maecenas at metus ullamcorper, sollicitudin odio vitae, rhoncus quam. Praesent in vestibulum enim, eu varius nunc. Suspendisse est nibh, gravida venenatis gravida scelerisque, finibus molestie tortor. Quisque eleifend rhoncus tortor, et finibus nunc commodo sed. Duis nisl orci, efficitur eget elit a, lobortis sodales lectus. Pellentesque ac libero nibh. Fusce vitae erat nibh. Vivamus ut nulla sit amet tortor laoreet cursus eget in lacus. Aenean cursus scelerisque consequat. Phasellus orci nunc, suscipit sed pellentesque sed, elementum at turpis. Curabitur ligula ipsum, dictum ut mattis vel, facilisis ut nulla. Donec a mauris tortor. Aliquam malesuada ex leo, et cursus leo dignissim eu.*/