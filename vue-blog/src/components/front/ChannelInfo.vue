<template>
    <div class="whitebg lanmu">
        <img v-if="channel.channelImg==null ||channel.channelImg=='' " width="130px" height="100px" src="http://8.135.100.252/image/2018-8-8/0d15b6b2-c3e8-45b9-9e2f-32ef380ddf03.jpg">
        <img v-else :src="channel.channelImg">
        <h1>{{channel.name}}</h1>
        <p>{{channel.summary}}</p>
    </div>
</template>

<script>

    export default {
        name: "ChannelInfo",
        props:['channel'],
        data(){
            return{}
        }
    }
</script>

<style scoped>

</style>
