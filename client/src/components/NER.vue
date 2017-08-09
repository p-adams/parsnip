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
                    class="md-raised md-primary"
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
                        <md-layout
                            class="cell"
                            md-align="center"
                            :style="{color: 'gray'}"
                            md-flex="100"
                        >
                            <ner-tags
                                :nertags="results"
                            ></ner-tags>
                        </md-layout>
                    </md-layout>         
                </md-table-row>
            </md-table-body>
        </md-table>
    </div>
</template>
<script>
import NerTags from './NERTags'
import axios from 'axios'
export default {
    created () {
        this.fetchDemoTags()
    },
    data () {
        return {
            text: '',
            results: [],
        }
    },
    methods: {
        fetchDemoTags () {
            axios.get('api/ner')
            .then(res => {
               this.processData(res.data)
            })
            .catch(err => {
                console.log(err)
            })
        },
        getTags () {
            axios.post('api/ner', {data: this.text})
            .then(res => {
                this.reset()
                this.processData(res.data)
            })
            .catch(err => {
                console.log(err)
            })
            this.text = ''
        },
        processData (data) {
            Object.keys(data).forEach(key => {
                this.results.push({word: key, tag: data[key]})
            })
        },
        reset () {
            this.results.splice(0, this.results.length)
        }
    },
    components: {
      NerTags
  }
}
</script>
<style>
    h2 {
        color: #424242;
    }
    .container {
        margin-top: 85px;
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

