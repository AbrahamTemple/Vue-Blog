<template>
    <div id="ResponsiveNav">
        <nav ref="menuWrapper">
            <div class="menubar">
                <a href="#0" class="home"><img src="@/assets/img/logo.jpg"/><span>微光实验室</span></a>
                <div class="nav-items">
                    <div class="nav-item" v-for="(item,index) in navItems" :key="index">
                        <button class="btn-3" @click="goTo(item.link)">{{item.title}}</button>
                    </div>
                </div>
                <div class="icons">
                    <div class="secondary-icons">
                        <i :class="right.c" v-for="(right,index) in icon" :key="index"><img :src="right.i"></i>
                    </div>
                    <i class="icon-menu" ref="iconMenu" @click="openMenu"><span></span></i>
                </div>
            </div>
            <ul class="menu" ref="menu">
<!--                <li v-for="(submenu,index) in menu" :key="index" :class="submenu.mode" @click="openSubmenu">-->
<!--                    <a href="#0" class="text-item">{{submenu.title}}<span class="icon"></span></a>-->
<!--                    <ul class="submenu" v-show="submenu.isShow">-->
<!--                        <li class="sub-item" v-for="(child,i) in submenu.children" :key="i"><a href="#0">{{child}}</a></li>-->
<!--                    </ul>-->
<!--                </li>-->
                <li  v-for="(item,index) in channels " :key="index" :class="{menu: item.children,'menu-link':true,'sub':true}" @click="openSubmenu">
                    <router-link v-if="item.single"  :to='"/channel_detail/"+item.id' class="text-item">{{item.name}}<span class="icon"></span></router-link>
                    <router-link v-else :to='"/list/"+item.id'>{{item.name}}</router-link>

                    <ul class="submenu" v-if="item.children" v-show="true">
                        <li v-for="(sub,sindex) in item.children" :key="sindex" class="sub-item">
                            <router-link v-if="sub.single" :to='"/channel_detail/"+sub.id'>{{sub.name}}</router-link>
                            <router-link v-else :to='"/list/"+sub.id'>{{sub.name}}</router-link></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div class="searchbox">
            <div class="search">
                <div>
                    <el-form :inline="true" ref="queryForm" :model="queryForm" label-width="80px">
                        <el-form-item>
                            <el-input v-model="queryForm.title" style="width: 330px" placeholder="按标题查询"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <a href="#content"  name="search">
                                <el-button icon="el-icon-search" @click="search" type="primary" >查询</el-button>
                            </a>
                        </el-form-item>
                    </el-form>
                </div>

                <!--</form>-->
            </div>
            <div class="searchclose"></div>
        </div>
    </div>
</template>

<script>
    import $ from 'jquery';
    import {queryByPos,search} from '@/api/front'
    export default {
        name: "ResponsiveNav",
        data(){
            return{
                channels: [],
                queryForm:{
                    name:'',
                    userName: ''
                },
                articles:[],
                pageNo :1,
                navItems:[
                    { title:"首页", link:"/"},
                    { title:"社交圈", link:"/cover"},
                    { title:"历程", link:"/space"},
                    { title:"音乐室", link:"/box"},
                    { title:"编辑室", link:"/user"},
                ],
                icon:[
                    { i:require('@/assets/img/icon-171.svg'), a:'/user', c: {'searchico':false,'icon-second':true}},
                    { i:require('@/assets/img/icon-198.svg'), a:'', c: {'searchico':true,'icon-second':true}},
                    { i:require('@/assets/img/icon-178.svg'), a:'', c: {'searchico':false,'icon-second':true}}
                ],
                menu:[
                    {
                        mode:'menu-link',
                        title:'About',
                        isShow:false,
                        children:[]
                    },
                    {
                        mode:'menu-link sub',
                        title:'Clients',
                        isShow:true,
                        children:["Burger King","Southwest Airlines","Levi Strauss"]
                    },
                    {
                        mode:'menu-link sub',
                        title:'Services',
                        isShow:true,
                        children:["Print Design","Web Design","Mobile App Development"]
                    },
                    {
                        mode:'menu-link',
                        title:'Services',
                        isShow:false,
                        children:[]
                    }
                ]
            }
        },
        mounted() {
            queryByPos('a').then(data=>{
                this.channels=data.data
            }).catch(error=>{

            })
            //search
            $(".searchico").click(function() {
                $(".search").toggleClass("open");
            });
            //searchclose
            $(".searchclose").click(function() {
                $(".search").removeClass("open");
            });
        },
        methods:{
            openMenu() {
                if(this.$refs.menu.classList.contains('open')) {
                    $('body').css('overflow','inherit');
                    this.$refs.menu.classList.add('close');
                    this.$refs.iconMenu.classList.remove('icon-closed');
                    let menuList = this.$refs.menu.classList;
                    setTimeout(function(){ menuList.remove('open'); }, 1300);
                } else {
                    this.$refs.menu.classList.remove('close');
                    this.$refs.menu.classList.add('open');
                    this.$refs.iconMenu.classList.add('icon-closed');
                    $('body').css('overflow','hidden');
                }
            },

            openSubmenu(event) {
                if (event.currentTarget.classList.contains("active")) {
                     event.currentTarget.classList.remove("active");
                } else {
                    event.currentTarget.classList.add("active");
                }
            },
            search(){
                let  param =this.queryForm
                this.pageNo =1
                param.page=this.pageNo
                this.list(param)
            },
            list(param){
                search(param).then(data=>{
                    this.articles=data
                    this.$emit('child-even',this.articles)
                    this.$emit('child-evenTitle',this.queryForm.title)
                    this.total=data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            goTo(url){
                this.$router.push({ path: url }).catch(() => {
                    console.log('错误触发');
                });
            }
        }
    }
</script>

<style scoped>
    /*@import "../../assets/css/nav.css";*/
    body{
        margin: 0;
        font-family: "Barlow", sans-serif;
        background-color: #f1f3f7;
        --primary:25,91,255;
        --color:44,62,80;
        align-items:center;
        justify-content:center;
        color:rgb(var(--color));
        width:100%;
    }
    ul {
        margin: 0;
        padding: 0;
    }
    ul li {
        list-style: none;
    }

    a {
        text-decoration: none;
        color: white;
    }

    /* Navigation */
    nav {
        z-index: 16;
        position: relative;
    }
    nav .menubar {
        width: 100%;
        height: 80px;
        position: fixed;
        top: 0;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 40px;
        box-sizing: border-box;
        background-color: white;
        box-shadow: 0px 0px 20px -10px rgba(0, 0, 0, 0.3);
    }
    @media screen and (max-width: 767px) {
        nav .menubar {
            height: 60px;
            padding: 0 40px;
        }
    }
    nav .menu .menu-link a{color: white;text-decoration: none;}
    nav .menubar .home {
        font-size: 20px;
        font-weight: 600;
        color: #c05c7e;
        transition: all 0.4s ease;
        text-decoration: none;
    }
    nav .menubar .nav-items {
        position: relative;
        text-align: center;
    }
    nav .menubar .nav-items .nav-item button{
        font-size: 2.3vh;
        font-weight: 600;
        color: #555555;
        transition: all 0.4s ease;
        text-decoration: none;
        background: transparent;
        outline: none;
        border: 0;
        line-height: 4.6vh;
    }
    nav .menubar .nav-items .nav-item button:hover{
        color: #c05c7e;
        text-decoration: none;
    }
    nav .menubar .nav-item{
        height: 4.6vh;
        width: 11vh;
        margin-right: 20px;
        float: left;
    }

    nav .menubar .nav-item:hover{
        color: #c05c7e;
        text-decoration: none;
    }
    nav .menubar .home:hover {
        opacity: 0.5;
    }
    nav .menubar .home img{
        width: 6vh;
        float: left;
        margin-top: 3%;
    }
    nav .menubar .home span{
        font-size: 3vh;
        position: relative;
        float: left;
        left: 2vh;
        vertical-align: middle;
    }
    @media screen and (max-width: 767px) {
        nav .menubar .home img{
            width: 5vh;
            margin-top: 8%;
        }
        nav .menubar .home span{
            font-size: 20%;
            position: relative;
            top: 0;
            vertical-align: middle;
        }
        nav .menubar .home{
            margin-left: -5%;
        }
    }
    @media screen and (max-width: 767px) {
        nav .menubar .icons .icon-menu {
            width: 30px;
            height: 30px;
            z-index: 2;
            position: relative;
            display: flex;
            align-items: center;
            cursor: pointer;
            padding: 5px;
            /* Icon Close */
        }
    }
    @media screen and (min-width: 768px) {
        nav .menubar .icons .icon-menu {
            display: none;
            /* Icon Close */
        }
    }
    nav .menubar .icons {
        display: flex;
        /* Icon Menu Hamburguer */
        /* Secondary Icons */
    }
    nav .menubar .icons .icon-menu span {
        background-color: #c05c7e;
        width: 30px;
        height: 3px;
        position: absolute;
        display: flex;
        justify-content: flex-end;
        transition: all 0.2s ease;
        right: 0;
        border-radius: 5px;
    }
    nav .menubar .icons .icon-menu span:before, nav .menubar .icons .icon-menu span:after {
        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        background-color: #c05c7e;
        border-radius: 5px;
    }
    nav .menubar .icons .icon-menu span:before {
        transform: translateY(-10px);
        transition: all 0.3s 0.1s ease;
    }
    nav .menubar .icons .icon-menu span:after {
        transform: translateY(10px);
        transition: all 0.3s 0.2s ease;
    }
    nav .menubar .icons .icon-menu:hover span {
        width: 30px;
    }
    nav .menubar .icons .icon-menu:hover span:before {
        width: 25px;
    }
    nav .menubar .icons .icon-menu:hover span:after {
        width: 20px;
    }
    nav .menubar .icons .icon-menu.icon-closed {
        justify-content: center;
    }
    nav .menubar .icons .icon-menu.icon-closed span {
        background-color: white;
        justify-content: center;
        width: 0px;
        right: initial;
        opacity: 0.5;
        transition: all 0.6s ease;
    }
    nav .menubar .icons .icon-menu.icon-closed span:before, nav .menubar .icons .icon-menu.icon-closed span:after {
        width: 30px;
        background-color: white;
    }
    nav .menubar .icons .icon-menu.icon-closed span:before {
        transform: rotate(45deg);
    }
    nav .menubar .icons .icon-menu.icon-closed span:after {
        transform: rotate(-45deg);
    }
    nav .menubar .icons .icon-menu.icon-closed:hover span {
        width: 0;
        opacity: 1;
    }
    @media screen and (max-width: 767px) {
        nav .menubar .icons .icon-menu.icon-closed:hover span:before {
            transform: rotate(45deg);
        }
        nav .menubar .icons .icon-menu.icon-closed:hover span:after {
            transform: rotate(-45deg);
        }
        nav .menubar .nav-items{
            display: none;
        }
    }
    nav .menubar .icons .secondary-icons {
        display: flex;
        /*flex-wrap: wrap;*/
    }
    nav .menubar .icons .secondary-icons .icon-second {
        width: 35px;
        height: 35px;
        margin: 20px 20px 0 0;
        cursor: pointer;
        transition: all 0.3s ease;
        padding: 5px;
    }
    nav .menubar .icons .secondary-icons .icon-second img{
        width: 65%;
    }
    @media screen and (max-width: 767px) {
        nav .menubar .icons .secondary-icons {
            display: none;
        }
        nav .menubar .nav-items{
            display: none;
        }
    }
    nav .menubar .icons .secondary-icons .icon-second:last-child {
        margin-right: 30px;
    }
    nav .menubar .nav-items{
        width: 60%;
        left: 10%;
        top: -2%;
        position: relative;
    }
    nav .menubar .nav-items button {
        width: 110px;
        padding:25% 20%;
        margin-top: -20.5%;
        left: 5%;
        font-size:1.5rem;
        cursor:pointer;
        border:0;
        background:transparent;
        position:relative;
        transition:all .25s ease
    }
    nav .menubar .nav-items .btn-3:hover::after {
        width:70%
    }
    nav .menubar .nav-items .btn-3::after {
        content:'';
        position:absolute;
        border-radius:inherit;
        width:0;
        height:5px;
        background:#c05c7e;
        bottom:0;
        z-index:2;
        transition:all .25s ease;
        left:52%;
        transform:translate(-50%)
    }
    @media screen and (max-width: 767px) {
        nav .menubar .icons .secondary-icons .icon-second:last-child {
            margin-right: 15px;
        }
        nav .menubar .nav-items{
            display: none;
        }
    }
    nav .menubar .icons .secondary-icons .icon-second:hover {
        transform: translateY(-5px);
        opacity: 0.5;
    }
    @media screen and (max-width: 767px) {
        nav .menubar .icons .secondary-icons .icon-second:hover {
            transform: translateY(0px);
            opacity: 1;
        }
        nav .menubar .nav-items{
            display: none;
        }
    }
    nav .menu {
        display: none;
        /* Open Menu */
        /* Animation - Close */
    }
    nav .menu.open {
        display: flex;
        z-index: 3;
        /* Menu Open - Main Link */
    }
    nav .menu.open .menu-link {
        width: 25%;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        transition: all 0.4s ease-in-out;
        animation: menu 0.6s ease forwards;
        height: 100vh;
        position: relative;
        /* Main Link - text */
        /* Sub Menu */
        /* Menu Links - Hover */
    }
    @keyframes menu {
        0% {
            height: 0;
            transform: translatey(-50%);
        }
        100% {
            height: 100vh;
            transform: translatey(0%);
        }
    }
    nav .menu.open .menu-link:nth-child(1) {
        background-color: #2d3561;
        animation-duration: 0.6s;
    }
    nav .menu.open .menu-link:nth-child(2) {
        background-color: #c05c7e;
        animation-duration: 0.8s;
    }
    nav .menu.open .menu-link:nth-child(3) {
        background-color: #f3826f;
        animation-duration: 1s;
    }
    nav .menu.open .menu-link:nth-child(4) {
        background-color: #ffb961;
        animation-duration: 1.2s;
    }
    nav .menu.open .menu-link .text-item {
        color: white;
        text-transform: uppercase;
        font-weight: 800;
        font-size: 40px;
        opacity: 0.3;
        width: 100%;
        height: 20%;
        letter-spacing: 5px;
        transform: rotate(-90deg) translateX(0px);
        transition: all 0.4s 0.2s ease-in-out, letter-spacing 0.2s ease-in, opacity 0.2s ease-in;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        transform-origin: center;
        animation: text-in 0.6s ease;
        position: absolute;
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link .text-item {
            transform: rotate(0deg);
            font-size: 30px;
        }
    }
    @keyframes text-in {
        0% {
            opacity: 0;
        }
        20% {
            opacity: 0;
        }
        100% {
            opacity: 0.3;
        }
    }
    nav .menu.open .menu-link .text-item .icon {
        position: absolute;
        display: flex;
        width: 30px;
        height: 30px;
        opacity: 0;
        transform: translateY(0px);
        transition: all 0.4s ease-in-out;
        background-image: url("https://rafaelalucas91.github.io/assets/icons/white/icon-54.svg");
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link .text-item .icon {
            opacity: 0.5;
            transform: translateY(40px);
        }
    }
    nav .menu.open .menu-link .submenu {
        display: flex;
        flex-direction: column;
        justify-content: center;
        padding: 0 40px;
        box-sizing: border-box;
        opacity: 0;
        z-index: -1;
        transition: all 0.6s ease-in-out;
        height: 0%;
        width: 100%;
        background-color: rgba(255, 255, 255, 0.1);
        bottom: 0;
        transform: translateY(50%);
        position: absolute;
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link .submenu {
            padding: 0 5%;
        }
    }
    nav .menu.open .menu-link .submenu .sub-item {
        display: none;
        margin: 10px 0;
        color: white;
        text-transform: uppercase;
        letter-spacing: 2px;
        font-size: 20px;
        font-weight: 600;
        position: relative;
        width: fit-content;
        cursor: pointer;
        padding: 5px 15px;
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link .submenu .sub-item {
            font-size: 16px;
        }
    }
    nav .menu.open .menu-link .submenu .sub-item:after {
        content: "";
        height: 0%;
        width: 3px;
        background-color: white;
        opacity: 0.3;
        position: absolute;
        top: 0;
        left: 0;
        transition: height 0.4s 0.3s ease, width 0.4s ease;
    }
    nav .menu.open .menu-link .submenu .sub-item:hover:after {
        width: 100%;
        height: 100%;
        transition: height 0.4s ease, width 0.4s 0.3s ease;
    }
    nav .menu.open .menu-link:hover {
        width: 50%;
        transition: all 0.4s ease-in-out;
        /* When Click to Open Sub Menu */
    }
    nav .menu.open .menu-link:hover .text-item {
        height: 50%;
        transform: rotate(0deg) translateX(0px);
        font-size: 30px;
        opacity: 1;
        letter-spacing: 10px;
    }
    nav .menu.open .menu-link:hover .text-item .icon {
        opacity: 1;
        transform: translateY(40px);
        transition: all 0.3s 0.4s ease-in-out;
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link:hover .text-item {
            width: 100%;
            font-size: 30px;
            opacity: 0.3;
            letter-spacing: 5px;
        }
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link:hover.active {
            height: 80vh;
        }
    }
    nav .menu.open .menu-link:hover.active .text-item {
        transform: rotate(0deg) translateX(0px) translateY(-50%);
        transform-origin: center center;
        transition: all 0.6s ease-in-out;
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link:hover.active .text-item {
            height: 20%;
            transform: translateY(-180%);
        }
    }
    nav .menu.open .menu-link:hover.active .text-item .icon {
        transition: all 0.3s ease-in-out;
        transform: translateY(40px) rotate(-180deg);
    }
    nav .menu.open .menu-link:hover.active .submenu {
        transform: translateY(0);
        height: 50%;
        z-index: 1;
        opacity: 1;
        bottom: 0;
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link:hover.active .submenu {
            height: 70%;
        }
    }
    nav .menu.open .menu-link:hover.active .submenu .sub-item {
        display: flex;
        animation: submenu 1s ease forwards;
        z-index: 2;
    }
    @keyframes submenu {
        0% {
            transform: translatex(50px) rotate(5deg);
            opacity: 0;
        }
        50% {
            transform: translatex(50px) rotate(-5deg);
            opacity: 0;
        }
        100% {
            opacity: 1;
            transform: translatex(0px) rotate(0deg);
        }
    }
    nav .menu.open .menu-link:hover.active .submenu .sub-item:nth-child(1) {
        animation-duration: 1s;
    }
    nav .menu.open .menu-link:hover.active .submenu .sub-item:nth-child(2) {
        animation-duration: 1.2s;
    }
    nav .menu.open .menu-link:hover.active .submenu .sub-item:nth-child(3) {
        animation-duration: 1.4s;
    }
    @media screen and (max-width: 767px) {
        nav .menu.open .menu-link {
            width: 100%;
            height: 25%;
            animation: menu-mobile 0.6s ease forwards;
            transform: translatey(0%);
            right: 0;
        }
        @keyframes menu-mobile {
            0% {
                transform: translateX(200%);
            }
            100% {
                transform: translateX(0%);
            }
        }
        nav .menu.open .menu-link:hover {
            width: 100%;
        }
    }
    @media screen and (max-width: 767px) {
        nav .menu.open {
            height: 100vh;
            flex-direction: column;
        }
    }
    nav .menu.close .menu-link {
        animation: menu-close 1s ease forwards;
    }
    @keyframes menu-close {
        0% {
            height: 100vh;
        }
        100% {
            height: 0;
        }
    }
    nav .menu.close .menu-link .text-item {
        animation: text-out 0.6s ease forwards;
    }
    @keyframes text-out {
        0% {
            opacity: 0.3;
        }
        80% {
            opacity: 0;
        }
        100% {
            opacity: 0;
        }
    }
    article {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
        color: #c05c7e;
        z-index: -1;
        opacity: 0.4;
        transition: all 0.4s ease;
        width: 80%;
    }
    article h1 {
        font-size: 20px;
        font-weight: 500;
    }
    article p {
        color: #f3826f;
    }
    article:hover {
        opacity: 1;
    }
    /*search*/
    .searchico { margin-top: 5px; width: 39px; height: 39px; float: right; display: inline; cursor: pointer; background: url(../../assets/images/bg.png) no-repeat center !important; }
    .searchbox { width: 1200px; margin: 10px auto; overflow: hidden; background: #ffffff; position: relative; }
    .search { width: 50%; margin: 20px auto;  border-radius: 0 5px 5px 0; position: relative;  display: none; }
    .search input.input_submit { border: 0; background: 0; color: #fff; outline: none; position: absolute; top: 10px; right: 10% }
    .search input.input_text { border: 0; line-height: 36px; height: 36px; width: 72%; padding-left: 10px; outline: none }
    .open { display: block !important }
    .searchclose { background: url(../../assets/images/close.png) no-repeat; display: block; position: absolute; width: 28px; height: 30px; right: 5px; top: 5px }
</style>
