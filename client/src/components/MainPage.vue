<template>
  <md-layout md-align="center">
      <md-layout :style="{marginTop: '25px'}" md-flex="30">
      <h2>Parsnips</h2>
      <h4>Parse and analyze text snippets using CoreNLP</h4>
      <!--<div>
        <div
          class="lc"
          v-for="(row, i) in this.lc"
          :key="i"
        >
          <div
            v-for="(col, i) in row"
            :key="i"
          >
            {{col}}
          </div>
        </div>

      <div
        class="rc"
        v-for="(row, i) in this.rc"
        :key="i"
      >
        <div
          v-for="(col, i) in row"
          :key="i"
        >
          {{col}}
        </div>
      </div>
      </div>-->
    </md-layout>
  </md-layout>
</template>
<script>
import axios from 'axios'
import elp from 'elparser'
import {TagSet} from './tagset'
import flatten from 'lodash.flatten'
export default {
  name: 'main-page',
  created () {
    this.loadParsedData()
  },
  data () {
    return {
      treeData: [],
      nestedData: '',
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
                      .parse1(res.data)
                      .toJS()

          this.treeData[0] = sent[0]
          this.lc = sent[1]
          this.rc = sent[2]
        })
        .catch(err => {
          console.log(err)
        })
    },
    format (input) {
      /*let obj = {tagabbr: '', tags: '', txt: ''}
      let tags = TagSet(input[0].toString())
      obj.tagabbr = input[0].toString()
      obj.tags = tags
      obj.txt = input[1].toString()*/
      if(Array.isArray(input)) this.nestedData = input //console.log(input)
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
  .lc {
    border: 1px solid red;
    width: 50px;
    height: 100px;
    margin-bottom: 10px;
    margin-top: 20px;
  }
  .rc {
    border: 1px solid blue;
    width: 50px;
    height: 100px;
    margin-bottom: 10px;
    margin-top: 20px;
  }
</style>



