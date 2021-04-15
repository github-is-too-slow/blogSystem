<template>
    <nav class="home-navbar navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="logo navbar-header">
                <router-link to="/" tag="span">
                    <img src="~assets/logo.png"/> OneBlog
                </router-link>
            </div>
            <slot></slot>
            <ul v-if="$store.state.id < 0" class="nav navbar-nav navbar-right">
                    <li>
                        <router-link to="/login"><span class="glyphicon glyphicon-log-in"></span> 登录</router-link>
                    </li>
                    <li>
                        <router-link to="/register"><span class="glyphicon glyphicon-user"></span> 注册</router-link>
                    </li>
            </ul>
            <ul v-else class="user-info nav navbar-nav navbar-right">
                <li class="user-info-show">
                    <div>
                        <img class="avatar" :src="adavar"/>
                        <span>{{$store.state.nickname}}</span>
                    </div>
                </li>
                <li>
                    <div @click="logout" class="cursor-pointer">
                        <span class="glyphicon glyphicon-user"></span> 退出
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "base-header",
        data(){
            return {
                defaultAdavarPath: "http://localhost:80" + "/api/image/adavar/Minions.jpg"
            }
        },
        computed: {
            adavar(){
                return this.$store.state.adavar == '' || this.$store.state.adavar == null ? this.defaultAdavarPath: this.$store.state.adavar
            }
        },
        methods: {
            logout(){
                this.$store.state.id = -1;
                this.$store.state.username = '';
                this.$store.state.nickname = '';
                this.$store.state.adavar = '';
                this.$router.push("")
            }
        }
    }
</script>

<style scoped>
    .home-navbar {
        margin-bottom: 0;
        height: 50px;
        line-height: 50px;
        text-align: center;
    }
    .home-navbar .logo {
        font-size: 22px;
        vertical-align:middle;
    }
    .home-navbar .logo img {
        margin-right: 5px;
    }
    .home-navbar .home-tabbar {
        font-size: 20px;
        margin-left: 50px;
    }
    .home-navbar .home-tabbar li {
        margin: 0 20px;
    }
    .user-info {
        margin-right: 15px;
    }
    .user-info .user-info-show{
        margin-right: 20px;
    }
    .user-info .avatar {
        width: 32px;
        height: 32px;
        margin-right: 5px;
        vertical-align: middle;
    }
    .cursor-pointer {
        cursor: pointer;
    }
</style>