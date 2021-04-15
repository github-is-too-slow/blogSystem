<template>
    <div class="container category-tag">
        <nav class="navbar navbar-default category-nav" role="navigation">
            <div>
                <div class="navbar-header">
                    <router-link to="/home/categorytag/category" class="navbar-brand category">分类</router-link>
                </div>
                <div class="navbar-header">
                    <router-link class="navbar-brand tag" to="/home/categorytag/tag">标签</router-link>
                </div>
            </div>
        </nav>
        <router-view/>
    </div>
</template>

<script>
    export default {
        name: "category-and-tag"
    }
</script>

<style scoped>
    .category-tag {
        width: 60%;
        border-top: 0;
    }
    .navbar-brand:hover {
        color: #000;
    }
    .router-link-active {
        color: #000;
    }
</style>