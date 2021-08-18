<template>

    <div>
        <div class="ad whitebg imgscale">
            <ul>
                <a href="/"><img src="http://8.135.100.252/image/2018-8-8/4970c965-39e5-4da9-a837-caa22a078856.jpg"></a>
            </ul>
        </div>

        <div class="links whitebg">
            <h2 class="htitle"><span class="sqlink"></span>友情链接</h2>
            <ul>
                <li v-for="(item,index) in friendLink" :key="index">
                    <a :href="item.url" target="_blank">{{item.title}}</a></li>

            </ul>
        </div>
    </div>
</template>

<script>
    import {getListLink} from "@/api/friendLink";
    export default {
        name: "FriendLink",
        data(){
            return{
                friendLink: []
            }
        },
        created() {
            getListLink().then(data=>{
                this.friendLink=data.data
            })
        }
    }
</script>

<style scoped>

</style>
