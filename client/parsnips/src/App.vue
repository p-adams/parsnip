<template>
  <div id="app">
    <md-toolbar md-theme="green">
      <md-button class="md-icon-button" @click="toggleLeftSidenav">
        <md-icon>menu</md-icon>
      </md-button>
      <h1 class="md-title">parsnips</h1>
      <span style="flex: 1;"></span>
      <md-button class="md-icon-button home" @click="home">
        <md-icon>home</md-icon>
      </md-button>
    </md-toolbar>
    <md-sidenav class="md-left" ref="leftSidenav" @open="open('Left')" @close="close('Left')">
      <md-toolbar class="md-large">
        <div class="md-toolbar-container">
          <h3 class="md-title nav">Supported NLP Features</h3>
        </div>
      </md-toolbar>
    <md-list>
      <md-list-item v-for="(route, key) in routes" :key=key>
        <span class="link"
              @click="selectRoute(route.name)">{{route.text}}</span>
      </md-list-item>
    </md-list>
    </md-sidenav>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      isOpen: false,
      routes: [
            {name: "/tokenization", text: "Tokenization"},
            {name: "FreqDist", text: "Word Frequency Distribution"},
            {name: "/ner", text: "Named-Entity Recognition"},
            {name: "/lemma", text: "Lemmatization"},
            {name: "/pos", text: "Part of Speech Tagging"},
            {name: "/con-parse", text: "Constituency Parsing"},
            {name: "/dep-parse", text: "Dependency Parsing"},
            {name: "/coref-res", text: "Coreference Resolution"},
      ]
    }
  },
  methods: {
    home () {
      this.$router.push({name: 'Home'})
    },
    selectRoute (name) {
      this.$router.push(name)
      this.$refs.leftSidenav.close()
    },
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
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background: #81c784;
  border: 1px solid magenta;
  color: white;
  height: 100vh;
}
.link {
  cursor: pointer;
}

</style>
