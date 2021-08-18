<template>

    <div class="headline">
        <ul>
            <li><router-link :to='"/detail/"+title1.id'  :title="title1.title">
                <img src="http://8.135.100.252/image/2018-8-8/c7b29b12-74e8-4404-8592-b3e71aca500e.jpg" alt="深度学习">
                <span>{{title1.title}}</span>
            </router-link></li>
            <li><router-link :to='"/detail/"+title2.id' :title="title2.title">
                <img src="http://8.135.100.252/image/2018-8-8/a88dbb33-1e9c-4bdc-8f4c-13c5efbec3dc.jpg" alt="网络通信">
                <span>{{title2.title}}</span>
            </router-link></li>
        </ul>
    </div>
</template>

<script>

    import {getRandomArticle} from '@/api/front'
    export default {
        name: "HeaderLine",
        data(){
            return{
                articles:[],
                title1: [],
                title2: []
            }
        },
        created() {
            getRandomArticle().then(data=>{
                this.articles=data.data
                this.title1=this.articles[0]
                this.title2=this.articles[1]
            })
        },

    }
</script>

<style scoped>

</style>
