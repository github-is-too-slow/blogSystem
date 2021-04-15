<template>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="thumbnail">
                    <div class="caption">
                        <h1 class="blog-title">{{article.title}}</h1>
                        <div class="blog-detail">
                            <div class="blog-info">
                                <a class="pull-left adavar"><img :src="article.author.adavar"></img></a>
                                <div>
                                    <span>{{article.author.nickname}}</span>
                                    <div class="blog-info-meta">
                                        <span>{{article.createDate | format}}</span>
                                        <span class="view">阅读 {{article.viewCounts}}</span>
                                        <span>评论 {{article.commentCounts}}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <mavon-editor class="article-body" defaultOpen="preview" :toolbarsFlag="false" :subfield="false" :preview="true"
                        :value="article.body"/>
    </div>
</template>

<script>
    import {formatTime} from "common/utils/time";
    import {getArticleById} from "network/article";
    import {mavonEditor} from 'mavon-editor'
    import 'mavon-editor/dist/css/index.css'
    export default {
        name: "view-blog",
        filters: {
            format: formatTime,
        },
        data(){
            return {
                article: {
                    author: {
                        adavar: 'http://localhost:80/api/image/adavar/Minions.jpg',
                        nickname: '错误处理大师'
                    },
                    title: '错误处理大师为你服务',
                    body: '对于本次错误给你带来的不良体验，本错误处理大师深感抱歉。\n可能由于网络故障？可能后端有Bug？\n请你尝试刷新请求',
                    createDate: '2020/2/12',
                    viewCounts: 999999,
                    commentCounts: 999999
                }
            }
        },
        components: {
            mavonEditor
        },
        methods: {
            getArticle(){
                this.article.id = this.$route.query.id;
                getArticleById(this.article.id).then(res => {
                    this.article.author.adavar = res.data.author.adavar;
                    this.article.author.nickname = res.data.author.nickname;
                    this.article.title = res.data.article.title;
                    this.article.body = res.data.article.body;
                    this.article.createDate = res.data.article.createDate;
                    this.article.viewCounts = res.data.article.viewCounts;
                    this.article.commentCounts = res.data.article.commentCounts;
                }).catch(err => {
                    // 错误处理
                })

            }
        },
        beforeRouteEnter(to, from, next){
            getArticleById(to.query.id).then(res => {
                next(vm => {
                    vm.article.author.adavar = res.data.author.adavar;
                    vm.article.author.nickname = res.data.author.nickname;
                    vm.article.title = res.data.article.title;
                    vm.article.body = res.data.article.body;
                    vm.article.createDate = res.data.article.createDate;
                    vm.article.viewCounts = res.data.article.viewCounts;
                    vm.article.commentCounts = res.data.article.commentCounts;
                })
            }).catch(err => {
                //错误处理
            })
        },
        created(){
            // this.getArticle()
        },
        mounted(){
        }
    }
</script>

<style scoped>
    .blog-title {
        margin-bottom: 30px;
    }
    .blog-detail .adavar {
        margin-right: 15px;
        width: 36px;
        height: 36px;
        vertical-align: middle;
    }
    .blog-info-meta .view {
        margin: 0 15px;
    }
    .blog-info-meta .edit {
        margin-bottom: 30px;
    }
    .article-body {
        font-size: 18px;
    }
</style>