<template>
  <div>
    Main Page
  </div>
</template>
<script>
export default {
  name: 'main-page',
  data () {
    return {
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
    }
  }
}
</script>
<style>
  .md-icon-button.home {
    border: 1px solid white;
    
  }
</style>
