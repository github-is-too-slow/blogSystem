<template>
    <div class="category">
        <div class="row">
            <ul>
                <li v-for="category in categorys">
                    <div class="col-md-3 col-sm-3 category-item">
                        <a href="#" class="thumbnail" @click.prevent="getArticleByCategory(category.name)">
                            <div class="img-box"><img class="img" :src="category.img"></div>
                            <div class="info">
                                <h4>{{category.name}}</h4>
                                <p>{{category.description}}</p>
                                <div class="count"><span>{{category.counts}} 文章</span></div>
                            </div>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
    import {getCategoryInfo} from "network/article"
    export default {
        name: "category",
        data(){
            return {
                categorys: []
            }
        },
        methods: {
            getCategoryInfo(){
                getCategoryInfo().then(res => {
                    this.categorys = res.data.categorys
                })
            },
            getArticleByCategory(categoryName){
                this.$router.push({
                    path: '/home/categorytag/somecategory',
                    query: {
                        categoryName,
                        title: categoryName
                    }
                })
            }
        },
        created(){
            this.getCategoryInfo()
        }
    }
</script>

<style scoped>
    .category {
        padding: 20px;
    }
    .category-item {
        margin-bottom: 45px;
        padding: 10px;
    }
    .category-item .img-box {
        width: 90%;
        height: 160px;
        margin: 0 auto;
    }
    .category-item .img-box .img{
        width: 100%;
        heigth: 100%;
    }
    .category-item .info {
        padding-left: 15px;
    }
    .category-item .info p{
        line-height: 30px;
        height: 60px;
        max-height: 60px;
        overflow: hidden;/* 超出的文本隐藏 */
        text-overflow: ellipsis; /* 溢出用省略号显示 */
        display: -webkit-box; /* 将对象作为弹性伸缩盒子模型显示 */
        -webkit-box-orient: vertical; /*  从上到下垂直排列子元素（设置伸缩盒子的子元素排列方式） */
        -webkit-line-clamp: 2; /* 这个属性不是css的规范属性，需要组合上面两个属性，表示显示的行数 */
    }
    .category-item a:hover {
        text-decoration:none;
        cursor:pointer;
    }
</style>