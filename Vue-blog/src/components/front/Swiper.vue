<template>
    <div class="banbox ">
        <swiper class="swiper"  style="height: 260px;" :auto-update="true">
            <swiper-slide v-for="(item,index) in sliders" :key="index">
                <router-link :to="item.url" target="_blank"><img :src="item.img" width="100%" alt=""></router-link>
            </swiper-slide>
        </swiper>
    </div>
</template>

<script>
    import { Swiper, SwiperSlide } from 'vue-awesome-swiper'

    import 'swiper/swiper-bundle.css'
    export default {
        name: "FSwiper",
        components:{
            Swiper,SwiperSlide
        },
        data(){
            return{
                sliders:[
                    {
                        "img": "http://8.135.100.252/image/2012-3-26/1a3b706f-150b-4daf-947a-dadbdcc74f23.jpg",
                        url:"www.csdn.net "
                    },
                    {

                        "img": "http://8.135.100.252/image/2012-3-26/2ca64049-5482-41d0-98b9-3931e0c98f2f.jpg",
                        url:"juejin.cn "
                    },
                    {
                        "img": "http://8.135.100.252/image/2012-3-26/5d4b4abb-3419-4838-80b8-3eb0b25eac7b.jpg",
                        url:"segmentfault.com "
                    }

                ],

            }
        }
    }
</script>

<style scoped>

</style>
