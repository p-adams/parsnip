<template>
  <md-layout md-align="center">
      <md-layout :style="{marginTop: '25px'}" md-flex="30">
      <h2>Parsnips</h2>
      <h4>Parse and analyze text snippets using CoreNLP</h4>
      <md-list class="custom-list md-triple-line">
        <md-list-item v-for="(c, key) in lc" :key="key">
          <span v-if="key > 0">{{format(c)}}</span>
          <span v-else>{{c}}</span>
        </md-list-item>
      </md-list>
      <md-list class="custom-list md-triple-line">
        <md-list-item v-for="(c, key) in rc" :key="key">
          <span v-if="key > 0">{{format(c)}}</span>
          <span v-else>{{c}}</span>
        </md-list-item>
      </md-list>
    </md-layout>
  </md-layout>
</template>
<script>
import axios from 'axios'
import elp from 'elparser'
import {TagSet} from './tagset'
import flattenDeep from 'lodash.flattendeep'
export default {
  name: 'main-page',
  created () {
    this.loadParsedData()
  },
  data () {
    return {
      treeData: '',
      lc: '',
      rc: '',
      isOpen: false,
      routes: [
            {link: "/tokenization", text: "Tokenization"},
            {link: "/freqdist", text: "Word Frequency Distribution"},
            {link: "/ner", text: "Named-Entity Recognition"},
            {link: "/lemma", text: "Lemmatization"},
            {link: "/pos", text: "Part of Speech Tagging"},
            {link: "/con-parse", text: "Constituency Parsing"},
            {link: "/dep-parse", text: "Dependency Parsing"},
            {link: "/coref-res", text: "Coreference Resolution"},
      ]
    }
  },
  methods: {
    toggleLeftSidenav() {
      this.$refs.leftSidenav.toggle();
    },
    open(ref) {
      console.log('Opened: ' + ref);
    },
    close(ref) {
      console.log('Closed: ' + ref);
    },
    loadParsedData () {
      axios.get('api')
        .then(res => {
          console.log(res.data)
          let sent = elp
                      .parse1('(' + res.data + ')')
                      .toJS()
          this.treeData = res.data
          /*let mainTag = Object.keys(sent)
          this.lc = sent[mainTag[0]][0]
          this.rc = sent[mainTag[0]][1]*/
        })
        .catch(err => {
          console.log(err)
        })
    },
    format (input) {
      let obj = {tagabbr: '', tags: '', txt: ''}
      let tags = TagSet(input[0].toString())
      obj.tagabbr = input[0].toString()
      obj.tags = tags
      obj.txt = input[1].toString()
      input.forEach(el => {
        console.log(el)
      })
      return obj
    }
  }
}
</script>
<style scoped>
  h2 {
    font-size: 50px;
  }
  h4 {
    color: #616161;
  }
  li {
    list-style: none;
    color: #616161;
  }
</style>



