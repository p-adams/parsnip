<template>
  <md-layout md-align="center">
      <md-layout :style="{marginTop: '25px'}" md-flex="25">
      <h2>Parsnips</h2>
      <h4>Parse and analyze text snippets using CoreNLP</h4>
      {{treeData}}
    </md-layout>
  </md-layout>
</template>
<script>
import axios from 'axios'
import elp from 'elparser'
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
          console.log(sent['S'][1])
          /*this.lc = sent[0]
          this.r.push({name: this.lc[0], children:[]})
          this.lc.splice(1).forEach(el => {
            this.r.push({children: children.push(el)})
          })
          console.log(r)
          this.rc = sent[1]*/
        })
        .catch(err => {
          console.log(err)
        })
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
</style>



