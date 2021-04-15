<template>
    <div class="tag">
        <div class="row">
            <ul>
                <li v-for="tag in tags">
                    <div class="col-md-2 col-sm-2 tag-item">
                        <a href="#" class="thumbnail" @click.prevent="getArticleByTag(tag.id, tag.name)">
                            <div class="img-box"><img class="img" :src="tag.img"></div>
                            <h4 class="info">{{tag.name}}</h4>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
    import {getTagInfo} from "network/article"
    export default {
        name: "tag",
        data(){
            return {
                tags: []
            }
        },
        methods: {
            getTagInfo(){
                getTagInfo().then(res => {
                    if(res.code == 1){
                        //错误处理
                    }else {
                        this.tags = res.data.tags
                    }
                })
            },
            getArticleByTag(tagId, tagName){
                this.$router.push({
                    path: '/home/categorytag/sometag',
                    query: {
                        tagId,
                        title: tagName
                    }
                })
            }
        },
        created(){
            this.getTagInfo()
        }
    }
</script>

<style scoped>
    .tag {
        padding: 20px;
    }
    .tag-item {
        margin-bottom: 20px;
        padding: 10px;
    }
    .tag-item .img-box {
        width: 90%;
        height: 90px;
        margin: 0 auto;
    }
    .tag-item .img-box .img{
        width: 100%;
        heigth: 100%;
    }
    .tag-item .info {
        width: 100%;
        text-align: center;
    }
    .tag-item a:hover {
        text-decoration:none;
        cursor:pointer;
    }
</style>