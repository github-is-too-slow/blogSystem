<template>
    <div class="row">
        <div class="footer col-md-4 col-md-offset-4
                       col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4">
            <p>Designed by
                <strong>
                    <router-link to="/">Billion</router-link>
                </strong>
            </p>
        </div>
    </div>
</template>

<script>
    export default {
        name: "base-footer"
    }
</script>

<style scoped>
    .footer {
        margin-top: 50px;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        text-align: center;
    }
</style>