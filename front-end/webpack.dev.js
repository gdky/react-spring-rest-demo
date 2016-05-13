var path = require('path');
var webpack = require('webpack');
var WebpackDevServer = require("webpack-dev-server");
var ExtractTextPlugin = require('extract-text-webpack-plugin');

var config = {
    entry: {
        index: [
            'webpack/hot/dev-server',
            'webpack-dev-server/client?http://localhost:8001',
            path.resolve(__dirname, './src/entry/index.jsx')
        ],
        vendor: [
            'react',
            'react-dom',
            'react-router',
            'reqwest'
        ]
    },

    output: {
        path: path.resolve(__dirname, './asset/'),
        filename: '[name].js'
    },
    resolve: {
        alias: {},
        extensions: ['', '.js', '.jsx'],
        // 添加项目中引用模块时的扫描起始目录，如require('common/lib')，则会扫描src/common/lib
        // 每项都必须为绝对路径
        root:[
            path.resolve('./src')
        ]
    },
    module: {
        loaders: [{
            test: /\.jsx?$/,
            include: [
             path.resolve(__dirname, 'src'),
             ],
            loader: 'babel',
            query: {
                presets: ['es2015', 'react', 'stage-0'],
                plugins: ['antd']
            }
        }, {
            test: /\.css$/,
            loader: ExtractTextPlugin.extract(
                'css!' +
                'autoprefixer-loader'
            )
        }, {
            test: /\.less$/,
            loader: ExtractTextPlugin.extract(
                'css!' +
                'autoprefixer-loader!' +
                'less'
            )
        }, {
            test: /\.(gif|png|jpg)(\?v=\d+\.\d+\.\d+)?$/,
            loader: 'url-loader?limit=10000&name=img/[name].[ext]'
        }, {
            test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
            loader: 'url?limit=10000&minetype=application/font-woff'
        }, {
            test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
            loader: 'url?limit=10000&minetype=application/font-woff'
        }, {
            test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
            loader: 'url?limit=10000&minetype=application/octet-stream'
        }, {
            test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
            loader: 'file'
        }, {
            test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
            loader: 'url?limit=10000&minetype=image/svg+xml'
        }]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.optimize.DedupePlugin(),
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': JSON.stringify('development')
            }
        }),
        /* new webpack.optimize.UglifyJsPlugin({
             compressor: {
                 warnings: false
             }
         }),*/
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            minChunks: Infinity
        }),
        new ExtractTextPlugin('[name].css', {
            allChunks: true
        })
    ],
    devServer: {
        contentBase: "entry",
        hot:true,
        proxy: {
            "/api/*": "http://localhost:8080/"
        },
        compress: true
    }
};

module.exports = config;