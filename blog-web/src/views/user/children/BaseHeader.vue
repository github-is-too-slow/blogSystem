<template>
    <div id="user-register-navbar">
        <nav class="navbar navbar-default" role="navigation">
            <div id="user-register-container" class="container-fluid">
                <div class="navbar-header">
                    <div class="left">
                        <router-link to="/" tag="span">
                            <img src="~assets/logo.png"/>
                        </router-link>
                        <span class="company">OneBlog</span>
                        <slot name="welcome"></slot>
                    </div>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <slot name="choose"></slot>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>
    export default {
        name: "base-header"
    }
</script>

<style scoped>
    #user-register-container {
        height: 100px;
    }
    .left {
        line-height: 100px;
    }
    .left .company {
        margin: 0 10px;

        color: red;
        font-size: 24px;
    }
    .left .welcome {
        font-size: 26px;
    }
    .navbar-right {
        line-height: 100px;
        font-size: 16px;
        margin-right: 10px;
        padding-top: 30px;
    }
    .navbar-right .tip {
        color: #000;
    }
    .navbar-right .tip:hover {
        color: red;
    }
</style>