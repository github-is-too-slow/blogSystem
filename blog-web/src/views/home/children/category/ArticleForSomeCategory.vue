<template>
    <div class="category-index container">
        <article-list :articles="page.pageRecords"/>
        <div>
            <ul class="pagination">
                <template v-if="page.pageNumber > 1">
                    <li><a @click.prevent="changePage(1)" href="#">首页</a></li>
                    <li><a @click.prevent="changePage(page.pageNumber - 1)" href="#">&laquo;</a></li>
                </template>
                <li v-for="pageNumber in pageRange" :class="pageNumber == page.pageNumber? 'active': ''">
                    <a @click.prevent="changePage(pageNumber)" href="#">{{pageNumber}}</a>
                </li>
                <template v-if="page.pageNumber < page.pageTotal">
                    <li><a @click.prevent="changePage(page.pageNumber + 1)" href="#">&raquo;</a></li>
                    <li><a @click.prevent="changePage(page.pageTotal)" href="#">末页</a></li>
                </template>
            </ul>
        </div>
    </div>
</template>

<script>
    import {getArticleByCategory} from "network/article";
    import {generateArray} from "common/utils/array";
    import ArticleList from "components/article/ArticleList"
    export default {
        name: "article-for-some-category",
        components: {
            ArticleList
        },
        computed: {
            pageRange(){
                return generateArray(this.begin, this.end)
            }
        },
        data(){
            return {
                page: {
                    pageNumber: 0,
                    pageTotal: 0,
                    pageRecordTotal: 0,
                    pageRecords: [],
                    pageSize: 5
                },
                begin: -1,
                end: -1,
                categoryName: ''
            }
        },
        created(){
            this.categoryName = this.$route.query.categoryName
            this.getArticles(1, this.page.pageSize).then(res => {
                if(res.code == 1){
                    this.page.pageRecords = []
                }else {
                    this.page.pageTotal = res.data.page.pages;
                    this.page.pageRecordTotal = res.data.page.total;
                    this.page.pageRecords = res.data.page.records;
                    this.page.pageNumber = res.data.page.current;
                    this.initBeginAndEnd();
                }
            });
        },
        methods: {
            initBeginAndEnd(){
                if(this.page.pageTotal <= 5){
                    this.begin = 1;
                    this.end = this.page.pageTotal;
                }else {
                    this.begin = 1;
                    this.end = 5
                }
            },
            changePage(pageNumber){
                this.getArticles(pageNumber, this.page.pageSize).then(res => {
                    if(res.code == 1){
                        this.page.pageRecords = [];
                    }else {//请求成功
                        this.page.pageTotal = res.data.page.pages;
                        this.page.pageRecordTotal = res.data.page.total;
                        this.page.pageRecords = res.data.page.records;
                        this.page.pageNumber = res.data.page.current;
                        if(this.page.pageTotal > 5){
                            if(this.page.pageNumber <= 3){
                                this.begin = 1;
                                this.end = 5;
                            }else if(this.page.pageNumber >= this.page.pageTotal - 2){
                                this.begin = this.page.pageTotal - 4;
                                this.end = this.page.pageTotal
                            }else {
                                this.begin = this.page.pageNumber - 2;
                                this.end = this.page.pageNumber + 2
                            }
                        }
                    }
                })
            },
            getArticles(pageNumber, pageSize){
                return getArticleByCategory(pageNumber, pageSize, this.categoryName)
            }
        }
    }
</script>

<style scoped>
    .category-index {
        width: 100%;
        margin-top: 30px;
    }
</style>