<template>
    <div>
        Frequency Dist chart
        <freq-dist-chart
          :data="chartData"
          :options="chartOptions"
          :width="400"
          :height="200"
        ></freq-dist-chart>

    </div>
</template>
<script>
import FreqDistChart from './chartcomponents/FreqDistChart'
export default {
    name: 'freq-dist',
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
    components: {
        FreqDistChart
    }
}
</script>