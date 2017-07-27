<template>
  <div class="main-page">
      <v-toolbar
        <v-toolbar-title>parsnips</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items class="hidden-sm-and-down">
          <router-link to="/freqdist">Word Frequency Distribution</router-link>
          <router-link to="/ner">Named-Entity Recognition</router-link>
          <router-link to="/tok">Tokenization</router-link>
          <router-link to="/parse">Parsing</router-link>
          <router-link to="/pos">POS Tagging</router-link>
          <router-link to="/lemma">Lemmatization</router-link>
          <router-link to="/cr">Coreference Resolution</router-link>
        </v-toolbar-items>
      </v-toolbar>
  </div>
</template>
<script>
export default {
  name: 'main-page'
}
</script>

