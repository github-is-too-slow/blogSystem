<template>
    <mavon-editor class="md-editor" editor="editor" v-model="editor.value"
                  @imgAdd="imgAdd" ref="md">
    </mavon-editor>
</template>

<script>
    import {mavonEditor} from 'mavon-editor'
    import 'mavon-editor/dist/css/index.css'
    import {upload} from 'network/article'
    export default {
        name: "make-down-editor",
        components: {
            mavonEditor
        },
        props: {
            editor: Object
        },
        methods: {
            imgAdd(fileIndex, imgfile){
                //1.上传文件至服务器，并获取URL
                let formData = new FormData();
                formData.append("img", imgfile);
                upload(formData).then(res => {
                    //2.替换markdown编辑器中图片的URL
                    if(res.code == 0){//上传成功
                        this.$refs.md.$img2Url(fileIndex, res.data.imgUrl)
                    }else {
                        //错误上传处理
                    }
                }).catch(err => {
                    //错误上传处理
                })
            }
        }
    }
</script>

<style scoped>
</style>