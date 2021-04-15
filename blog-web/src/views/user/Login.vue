<template>
    <div>
        <base-header>
            <span class="welcome" slot="welcome">欢迎登录</span>
            <div slot="choose">
                <span>没有账号？</span>
                <router-link to="/register" class="tip">
                    请注册<span class="glyphicon glyphicon-chevron-right"></span>
                </router-link>
            </div>
        </base-header>
        <div class="container" id="user-login">
            <form id="user-login-form" class="form-horizontal" role="form" method="post" action="#">
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-4 warning" v-show="!isSuccess">用户名或密码有误，请重新输入</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-4 control-label">用户名</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="user-name" required v-model.lazy.trim="user.username"
                                   name="username" type="text" placeholder="用户名" @change="usernamecg">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-4 warning" v-show="isUsernameBlank">用户名不能为空</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-4 control-label">密码</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="password" v-model.lazy.trim="user.password"
                                   type="password" name="password" required placeholder="密码" @change="passwordcg">
                        </div>
                        <div class="row">
                            <div class="col-sm-8 col-sm-offset-4 warning" v-show="isPasswordBlank">密码不能为空</div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div id="form-submit" class="col-sm-7 col-sm-offset-5">
                            <button id="submit" class="btn btn-success" @click.prevent="submitBtn">登录</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <base-footer/>
    </div>
</template>

<script>
    import BaseHeader from "views/user/children/BaseHeader"
    import BaseFooter from "components/headerAndFooter/BaseFooter"
    export default {
        name: "login",
        components: {
            BaseHeader,
            BaseFooter
        },
        data(){
            return {
                isUsernameBlank: false,
                isPasswordBlank: false,
                isSuccess: true,
                user: {
                    username: '',
                    password: ''
                }
            }
        },
        methods: {
            usernamecg(){
                this.isUsernameBlank = this.user.username == '';
            },
            passwordcg(){
                this.isPasswordBlank = this.user.password == '';
            },
            submitBtn(){
                this.isSuccess = true;
                if(this.user.username == ''){
                    this.isUsernameBlank = true;
                    return;
                }
                if(this.user.password == ''){
                    this.isPasswordBlank = true;
                    return;
                }
                this.$store.dispatch("login", this.user).then(res => {
                    if(res.code == 0){//登录成功
                        this.$router.back()
                    }else {
                        this.isSuccess = false;
                    }
                }).catch(err => {
                    //错误处理
                })
            }
        }
    }
</script>

<style scoped>
    #user-login {
        width: 30%;
        margin-top: 5%;
        margin-bottom: 50px;
    }
    #form-submit {
        /*text-align: center;*/
    }
    .warning {
        color: red;
    }
</style>