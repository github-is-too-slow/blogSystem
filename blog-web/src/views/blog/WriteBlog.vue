<template>
    <div class="blog">
        <base-header class="blog-header">
            <ul class="home-tabbar blog-tabbar nav navbar-nav navbar-left">
                <li><p class="tag">写文章</p></li>
                <li><span class="text-success" id="publish" @click="publish">发布</span></li>
                <li><span class="text-warning" id="unpublish" @click="unpublish">取消</span></li>
            </ul>
        </base-header>
        <div class="article-body container-fluid">
            <div class="article-title">
                <textarea placeholder="请输入标题" v-model="article.title"></textarea>
            </div>
        </div>
        <mark-down-editor class="blog-body" :editor="article.editor"/>
        <div class="modal fade" id="publishModal" tabindex="1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">×
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            摘要 分类 标签
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form id="publish-form" class="form-horizontal" role="form" action="#">
                            <div class="form-group">
                                <div class="row">
                                    <label class="col-sm-2 control-label">摘要</label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" id="summary" required
                                                  v-model.lazy.trim="article.summary" placeholder="请输入摘要"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <label class="col-sm-2 control-label">文章分类</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" v-model="article.category">
                                            <option v-for="category in categorys">{{category}}</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <form class="form-inline" role="form">
                                    <label class="col-sm-2 control-label">文章标签</label>
                                    <div class="col-sm-8">
                                        <div class="checkbox tag-checkbox" v-for="tag in tags">
                                            <label>
                                                <input type="checkbox" v-model="article.tags" :value="tag">{{tag}}
                                            </label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                        <button type="button" class="btn btn-primary" @click="publishArticle">
                            发布
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import BaseHeader from "components/headerAndFooter/BaseHeader"
    import MarkDownEditor from 'components/markdown/MarkDownEditor'
    import {getCategorysAndTags, publishArticle} from 'network/article'
    export default {
        name: "write-blog",
        data() {
            return {
                categorys: [],
                tags: [],
                article: {
                    id: '',
                    title: '',
                    summary: '',
                    category: '',
                    tags: [],
                    editor: {
                        value: '床前明月光，疑是地上霜。\n' +
                        '举头望明月，低头思故乡。',
                        default_open: 'edit',
                        toolbars: {
                            bold: true, // 粗体
                            italic: true, // 斜体
                            header: true, // 标题
                            underline: true, // 下划线
                            strikethrough: true, // 中划线
                            mark: true, // 标记
                            superscript: true, // 上角标
                            subscript: true, // 下角标
                            quote: true, // 引用
                            ol: true, // 有序列表
                            ul: true, // 无序列表
                            imagelink: true, // 图片链接
                            code: true, // code
                            fullscreen: true, // 全屏编辑
                            readmodel: true, // 沉浸式阅读
                            help: true, // 帮助
                            undo: true, // 上一步
                            redo: true, // 下一步
                            trash: true, // 清空
                            navigation: true, // 导航目录
                            //subfield: true, // 单双栏模式
                            preview: true, // 预览
                        }
                    }
                }
            }
        },
        components: {
            BaseHeader,
            MarkDownEditor
        },
        methods: {
            publish(){
                $('#publishModal').modal('show')
            },
            unpublish(){
                const res = confirm("文章将不会保存, 是否继续?")
                if(res){
                    this.$router.back()
                }
            },
            publishArticle(){
                //前置判断省略
                let article = {
                    author: this.$store.state.id,//用户id
                    title: this.article.title,
                    summary: this.article.summary,
                    body: this.article.editor.value,
                    category: this.article.category,
                    tags: this.article.tags
                }
                publishArticle(article).then(res => {
                    $('#publishModal').modal('hide')
                    this.$router.push("/home/view?id=" + res.data.id)
                }).catch(err => {
                    //错误处理
                })
            }
        },
        created(){
            getCategorysAndTags().then(res => {
                this.categorys = res.data.categorys;
                this.tags = res.data.tags;
            })
        }
    }
</script>

<style scoped>
    .blog .blog-body {
        min-height: calc(100vh - 50px);
    }
    .blog-header .tag {
    margin-left: 100px;
    font-size: 20px;
    }
    .text-success, .text-warning{
        border: 1px solid;
        border-radius: 20px;
        padding: 5px;
    }
    .text-success {
        margin-left: 200px;
        margin-right: 50px;
    }
    .article-title textarea {
        resize: none;
        border: 0;
        outline: none;
        width: 100%;
        font-size: 18px;
    }
    .blog-body {
        z-index: 1;
    }
    #publishModal .tag-checkbox {
        margin-right: 10px;
    }
    #summary {
        resize: none;
    }
</style>