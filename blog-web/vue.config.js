const webpack = require("webpack");
module.exports = {
    devServer: {
        sockHost: 'http://localhost:80/',
        disableHostCheck: true,
    },
    configureWebpack: {
        resolve: {
            extensions: ['.js', '.vue', '.json', '.css'],
            alias: {
                'assets': '@/assets',
                'common': '@/common',
                'components': '@/components',
                'network': '@/network',
                'views': '@/views',
                'router': '@/router',
                'store': '@/store'
            }
        },
        plugins: [
            new webpack.ProvidePlugin({
                $: "jquery",
                jQuery: "jquery",
                "windows.jQuery": "jquery"
            })

        ]
    }
}