<template>
<v-toolbar class="green">
    <v-toolbar-title>
        <v-icon class="icon" @click="this.open" fa>list</v-icon>
        parsnips
    </v-toolbar-title>
    <v-spacer></v-spacer>
    </v-toolbar>
</template>
<script>
export default {
    name: 'toolbar',
    props: ['open']
}
</script>
<style>
    .icon {
        cursor: pointer;
    }
</style>
