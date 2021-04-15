<template>
    <div>
        <base-header>
            <ul class="home-tabbar nav navbar-nav navbar-left">
                <li><router-link to="/">首页</router-link></li>
                <li><router-link to="/home/categorytag">文章分类</router-link></li>
                <li><router-link to="/">文章归档</router-link></li>
                <li><router-link to="/">日志</router-link></li>
                <li><router-link to="/">留言板</router-link></li>
                <li><router-link to="/writeBlog">写文章</router-link>
                </li>
            </ul>
        </base-header>
        <keep-alive>
            <router-view/>
        </keep-alive>
        <base-footer/>
    </div>
</template>

<script>
    import BaseHeader from "components/headerAndFooter/BaseHeader"
    import BaseFooter from "components/headerAndFooter/BaseFooter"
    export default {
        name: "home",
        components: {
            BaseHeader,
            BaseFooter
        },
        data(){
            return {
                isLogin: false
            }
        },
        computed: {
            // isLogin(){
            //     return this.$store.state.id > 0
            // }
        },
        methods: {
        },
        mounted(){
        }
    }
</script>

<style scoped>
</style>