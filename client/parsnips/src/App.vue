<template>
  <div id="app">
     <v-btn flat>Normal</v-btn>
        <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background: #81c784;
  border: 1px solid magenta;
  color: white;
  height: 100vh;
}
</style>
