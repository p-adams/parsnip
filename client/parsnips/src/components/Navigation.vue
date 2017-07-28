<template>
  <div>
      meow {{isOpen}}
    <v-navigation-drawer
        class="pb-0"
        persistent
        absolute
        height="100%"
        clipped
        enable-resize-watcher
        v-model="isOpen"
    >
        <v-list dense>
            <v-list-tile v-for="(route, key) in routes" :key=key>
                    <v-list-tile-content>
                        <v-list-tile-title>
                            {{route.text}}
                        </v-list-tile-title>
                    </v-list-tile-content>
            </v-list-tile>
        </v-list>
    </v-navigation-drawer>
    </div>
</template>
<script>
export default {
  name: 'navigation',
  props: ['open'],
  updated () {
    console.log(this.open)
  },
  data () {
      return {
        isOpen: this.open,
        routes: [
            {link: "/freqdist", text: "Word Frequency Distribution"}
        ]
      }
  }
}
</script>
 <!--<v-toolbar-items class="hidden-sm-and-down">
        <router-link to="/freqdist">Word Frequency Distribution</router-link>
        <router-link to="/ner">Named-Entity Recognition</router-link>
        <router-link to="/tok">Tokenization</router-link>
        <router-link to="/parse">Parsing</router-link>
        <router-link to="/pos">POS Tagging</router-link>
        <router-link to="/lemma">Lemmatization</router-link>
        <router-link to="/cr">Coreference Resolution</router-link>
    </v-toolbar-items>-->