<template>
    <div>
        <base-header>
            <span class="welcome" slot="welcome">欢迎注册</span>
            <div slot="choose">
                <span>已有账号？</span>
                <router-link to="/login" class="tip">
                    请登录<span class="glyphicon glyphicon-chevron-right"></span>
                </router-link>
            </div>
        </base-header>
        <div class="container" id="user-register">
            <form id="user-register-form" class="form-horizontal" role="form"
                  enctype="multipart/form-data" method="post" action="#">
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-4 warning" v-show="!isSuccess">注册失败，请重新尝试</div>
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
                        <div class="col-sm-8 col-sm-offset-4 warning" v-show="isRedundancy">用户名已存在</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">昵称</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="nick-name" required v-model.lazy.trim="user.nickname"
                                   name="nickname" type="text" placeholder="昵称">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-4 control-label">密码</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="password" v-model.lazy.trim="user.password"
                                   type="password" name="password" required @change="passwordcg" placeholder="密码">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-4 control-label">确认密码</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="repassword" @change="passwordcg"
                                   type="password" required placeholder="确认密码">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-4 warning" v-show="!isSame">密码与确认密码不一致</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-4 control-label">邮箱</label>
                        <div class="col-sm-8">
                            <input class="form-control" type="text" id="email" required @change="emailcg"
                                   name="email" placeholder="邮箱" v-model.lazy.trim="user.email">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-4 warning" v-show="!isValidate">邮箱格式不合法</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label class="col-sm-4 control-label">头像</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="adavar" type="file" name="realAdavar" @change="uploadImg">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-4 warning" v-show="!isSelectAdavar">未选择头像</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div id="form-submit" class="col-sm-7 col-sm-offset-5">
                            <button id="submit" class="btn btn-success" @click.prevent="submitBtn">注册</button>
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
    import {saveUser, login, checkUsername} from 'network/user'
    export default {
        name: "register",
        data(){
          return {
              isRedundancy: false,
              isSame: true,
              isValidate: true,
              isSelectAdavar: true,
              isSuccess: true,
              user: {
                  username: '',
                  nickname: '',
                  password: '',
                  email: '',
                  realAdavar: null
              },
          }
        },
        methods: {
            usernamecg(){
                checkUsername(this.user.username).then(res => {
                    this.isRedundancy = res.code == 1;
                })
            },
            passwordcg(){
                let repassword = $("#repassword").val();
                this.isSame = this.user.password === repassword;
            },
            emailcg(){
                let regExp = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                this.isValidate = regExp.test(this.user.email);
            },
            uploadImg(event){
                let realAdavar = event.target.files[0];
                this.user.realAdavar = realAdavar;
                this.isSelectAdavar = true;
            },
            submitBtn(){
                this.isSuccess = true;
                let isNotSend = this.user.username == '' || this.isRedundancy ||
                        this.user.password == '' || !this.isSame ||
                        this.user.email == '' || !this.isValidate;
                if(this.user.realAdavar == null || !this.isSelectAdavar){
                    this.isSelectAdavar = false;
                    return;
                }
                if(isNotSend){
                    return;
                }
                let formData = new FormData();
                formData.append("username", this.user.username);
                formData.append("nickname", this.user.nickname);
                formData.append("password", this.user.password);
                formData.append("email", this.user.email);
                formData.append("realAdavar", this.user.realAdavar);
                saveUser(formData).then(res => {
                    if(res.code ==  0){
                        this.$router.push("/login")
                    }else {
                        this.isSuccess = false;
                    }
                }).catch(err => {
                    //错误处理
                    this.isSuccess = false;
                });
            }
        },
        components: {
            BaseHeader,
            BaseFooter
        }
    }
</script>

<style scoped>
    #user-register {
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