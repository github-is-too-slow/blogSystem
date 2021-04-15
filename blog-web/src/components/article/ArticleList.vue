<template>
    <div>
        <div class="article-list">
            <div v-if="articles.length == 0" class="no-data">当前分类没有数据哦</div>
            <article-item v-else v-for="article in articles" :key="article.id"  :article="article"></article-item>
        </div>
    </div>
</template>

<script>
    import ArticleItem from "components/article/ArticleItem"
    export default {
        name: "article-list",
        components: {
            ArticleItem,
        },
        props: {
            articles: Array
        },
        data(){
            return {
            }
        },
        computed: {
        }
    }
</script>

<style scoped>
    .no-data {
        color: #000;
        font-size: 30px;
        text-align: center;
    }
</style>