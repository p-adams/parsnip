<template>
    <ul>
        <li v-for="(ntag, index) in tags" :key="index">
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
    name: 'ner-tags',
    props: ['nertags'],
    data () {
        return {
            tags: this.nertags
        }
    }
}
</script>
<style>
    ul {
        margin-top: 50px;
    }
    li {
        list-style-type: none;
        display: inline;
    }
    .person {
        color: #FF6384;
        font-size: 25px;
    }
    .organization {
        color: #36A2EB;
        font-size: 25px;
    }
    .location {
        color: #FFCD56;
        font-size: 25px;
    }
</style>

