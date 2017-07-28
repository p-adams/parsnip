<template>
  <v-app>
    {{isOpen}}
    <navigation :open="isOpen"></navigation>
    <toolbar :open="onToggleOpen"></toolbar>
  </v-app>
</template>
<script>
import Toolbar from './Toolbar'
import Navigation from './Navigation'
export default {
  name: 'main-page',
  data () {
    return {
      isOpen: false
    }
  },
  methods: {
    onToggleOpen () {
      this.isOpen = !this.isOpen
    }
  },
  components: {
    Toolbar,
    Navigation
  }
}
</script>
<style>
</style>
