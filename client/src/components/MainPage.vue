<template>
  <md-layout md-align="center">
      <md-layout :style="{marginTop: '25px'}" md-flex="25">
      <h2>Parsnips</h2>
      <h4>Parse and analyze text snippets using CoreNLP</h4>
      <h4>colorless green ideas sleep furiously</h4>
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
          let sent = elp
                      .parse1('(' + res.data + ')')
                      .toObject()
          this.lc = sent['S'][0]
          this.rc = sent['S'][1]
        })
        .catch(err => {
          console.log(err)
        })
    },
    format (input) {
      // console.log(input)
      // console.log(flattenDeep(input))
      let tags = TagSet(input[0].toString())
      return `${tags} ${input[1].toString().replace(/,/, ' ')}`
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



