<template>
  <md-layout md-align="center">
      <md-layout :style="{marginTop: '25px'}" md-flex="25">
      <h2>Parsnips</h2>
      <h4>Parse and analyze text snippets using CoreNLP</h4>
      {{res}}
    </md-layout>
  </md-layout>
</template>
<script>
import axios from 'axios'
export default {
  name: 'main-page',
  created () {
    this.loadParsedData()
  },
  data () {
    return {
      res: '',
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
    balancedParens (str) {
      var stack = [];
      var open = { '{': '}', '[': ']', '(': ')' };
      var closed = { '}': true, ']': true, ')': true };
      
      for (var i = 0; i < str.length; i ++) {
        var chr = str[i];
        if (open[chr]) {
          stack.push(chr);
        } else if (closed[chr]) {
          if (open[stack.pop()] !== chr) return false;
        }
      }
      return stack.length === 0;
    },
    parseData (data) {
      let str = data.substring(data.indexOf('S'), data.length - 2)
      console.log(str)
      let idx = str.indexOf('(')
      console.log(idx)
      console.log(this.balancedParens(str.slice(idx + 1)))
    },
    loadParsedData () {
      axios.get('api')
        .then(res => {
          this.parseData(res.data)
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



