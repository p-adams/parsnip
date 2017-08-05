<template>
    <ul>
        <li v-for="(ntag, index) in nertags" :key="index">
            <span
            :class="{
                person: ntag.tag === 'PERSON',
                organization: ntag.tag === 'ORGANIZATION',
                location: ntag.tag === 'LOCATION'
            }"> {{ntag.word}}</span>
        </li>
    </ul>
</template>
<script>
export default {
  props: ['nertags']
}
</script>
<style>
    li {
        list-style-type: none;
        display: inline;
    }
    .person {
        color: #FF6384
    }
    .organization {
        color: #36A2EB;
    }
    .location {
        color: #FFCD56;
    }
</style>

