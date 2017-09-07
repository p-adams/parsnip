<template>
   <md-layout md-align="center">
            <h2>POS Tagger</h2>
            <md-layout md-flex="45">
                <div>
                <md-input-container class="container">
                    <label>Enter text</label>
                    <md-textarea
                        v-model="text"
                        maxlength="250"
                        autofocus
                    >
                    </md-textarea>
                    </md-input-container>
                <md-button
                    class="md-raised md-primary"
                    @click="getTags"
                    :disabled="text.length===0"
                    >tag</md-button>
                <p v-if="!clicked">Input: {{demoText}}</p>
                <p v-else>Input: {{text}}</p>
                </div>
                <md-table v-if="!isLoading">
                    <md-table-header>
                        <md-table-row>
                        <md-table-head>Word</md-table-head>
                        <md-table-head>Tag Symbol</md-table-head>
                        <md-table-head>Tag</md-table-head>
                        </md-table-row>
                    </md-table-header>
                    <md-table-body>
                        <md-table-row v-for="(row, index) in posTags" :key="index">
                            <md-table-cell v-for="(col, index) in row" :key="index">
                               {{col}}
                            </md-table-cell>
                        </md-table-row>
                    </md-table-body>
                    </md-table>
            </md-layout>
        </md-layout>
</template>
<script>
import axios from 'axios'
import {TagSet} from './tagset'
export default {
 name: 'pos',
 created() {
     this.loadDemoPOSTags()
 },
 data () {
     return {
         text: '',
         isLoading: true,
         clicked: false,
         demoText: 'Colorless green ideas sleep furiously',
         posTags: [],
     }
 },
 methods: {
     loadDemoPOSTags () {
         axios.get('api/pos')
         .then(res => {
             console.log(res)
             Object.keys(res.data).forEach(key => {
                 //console.log(TagSet(res.data[key]))
                 this.posTags.push({word: key, tag: res.data[key], tagset: TagSet(res.data[key])})
             })
             this.isLoading = false
         })
         .catch(err => {
             console.log(err)
         })
     },
     getTags () {
        this.posTags = []
        this.clicked = true
        axios.post('api/pos', {data: this.text})
        .then(res => {
            Object.keys(res.data).forEach(key => {
                 console.log(TagSet(res.data[key]))
                 this.posTags.push({word: key, tag: res.data[key], tagset: TagSet(res.data[key])})
             })
             this.isLoading = false
        })
        .catch(err => {
            console.log(err)
        })
     }
 },
 computed: {
     textToTokens () {
         return this.text.split(' ')
     },
     demoTextToTokens () {
         return this.demoText.split(' ')
     }
 }
}
</script>
<style>
    .container {
        margin-top: 75px;
    }
</style>


