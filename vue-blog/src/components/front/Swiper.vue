<template>
    <div class="banbox row justify-content-center">
        <div class="box_skitter box_skitter_large" ref="skitters">
            <ul>
                <li v-for="(img,$index) in banner" :key="$index">
                    <a :href="img.link" target="_blank"><img :src="img.url" :class="img.mode"/></a>
                    <div class="label_text">
                        <p><strong><font :color="img.color">{{img.title}}</font></strong></p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
    import $ from 'jquery';
    import '@/assets/js/jquery.easing.1.3';
    import 'skitter-slider';
    export default {
        name: "Banner",
        data(){
            return{
                banner: [
                    {
                        url: require('@/assets/image/banner01.png'),
                        mode: 'circlesRotate',
                        link: '#list/41',
                        title: '为什么有人说Linux系统比Windows系统更好？',
                        color: '#ffffff'
                    },
                    {
                        url: require('@/assets/image/banner02.gif'),
                        mode: 'cubeRandom',
                        link: '#list/19',
                        title: '令无数人宠爱的Go语言到底适合做什么？',
                        color: '#FF00FF'
                    },
                    {
                        url: require('@/assets/image/banner03.png'),
                        mode: 'horizontal',
                        link: '#list/48',
                        title: '区块链技术你怎么理解？它只能用来做比特币？',
                        color: '#FFFF00'
                    }
                ]
            }
        },
        mounted() {
            $('.box_skitter_large').skitter({
                    label: true,
                    dots : false,
                    // numbers: true,
                    interval:3500,
                    show_randomly: true,
                    hideTools: true,
                    theme: 'minimalist'
            });
        },
    }
</script>

<style scoped>
    @import "../../assets/css/skitter.css";
</style>
