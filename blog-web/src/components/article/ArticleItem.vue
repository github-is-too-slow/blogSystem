<template>
    <div class="row">
        <div class="thumbnail">
            <div class="caption">
                <div class="article-header">
                    <h4 @click="view">{{article.title}}</h4>
                    <span class="pull-right">
                        <span class="glyphicon glyphicon-eye-open"></span> {{article.viewCounts}}
                    </span>
                    <span class="pull-right">
                        <span class="glyphicon glyphicon-comment"></span> {{article.commentCounts}}
                    </span>

                </div>
                <div class="artile-description">
                    {{article.summary}}
                </div>
                <div class="article-footer text-justify">
                    <span class="nickname"><span class="glyphicon glyphicon-user"></span> {{article.author.nickname}}</span>
                    <span v-for="tag in article.tags" :key="tag" class="text-success tag">{{tag}}</span>
                    <span class="pull-right">
                        <span class="glyphicon glyphicon-time"></span> {{article.createDate | format}}
                    </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {formatTime} from "common/utils/time";
    export default {
        name: "article-item",
        props: {
            article: Object
        },
        data() {
            return {
            }
        },
        methods: {
            view() {
                this.$router.push({
                    path: '/home/view',
                    query: {
                        id: this.article.id,
                        title: this.article.title
                    }
                })
            }
        },
        filters: {
            format: formatTime
        }
    }
</script>

<style scoped>
    .glyphicon {
        vertical-align: middle;
        padding: 6px;
    }
    .article-header h4 {
        display: inline-block;
        padding-bottom: 10px;
        font-weight: 600;
        cursor: pointer;
    }
    .article-header h4:hover {
        color: red;
    }
    .article-footer .tag{
        padding: 6px;
    }
    .article-footer .nickname{
        margin-right: 10px;
    }
</style>