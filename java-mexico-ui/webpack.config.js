const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const config = {
  entry: {
    posts: './src/posts/index.js',
    comments: './src/comments/index.js'
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'java-mexico-[name].js'
  },
  module: {
    rules: [{
      test: /\.jsx|.js$/,
      exclude: /node_modules/,
      use: {
        loader: 'babel-loader',
        options: {
          plugins: [
            ['@babel/plugin-transform-react-jsx', {
              pragma: 'h',
              pragmaFrag: 'Fragment'
            }]
          ],
          presets: [
            ['@babel/env', {
              targets: {
                chrome: 58,
                ie: 11
              }
            }]
          ]
        }
      }
    }, {
      test: /\.less$/, // .less and .css
      use: [
        MiniCssExtractPlugin.loader,
        'css-loader',
        'less-loader'
      ],
    }]
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: 'java-mexico.css',
    }),
  ],
  resolve: {
    extensions: ['.tsx', '.ts', '.js', '.json'],
    alias: {
      react: 'preact/compat',
      'react-dom/test-utils': 'preact/test-utils',
      'react-dom': 'preact/compat'
    }
  },
  devtool: 'none'
};

if(process.env.NODE_ENV === 'development') {
  config.devtool = 'source-map';
  config.devServer = {
    contentBase: path.join(__dirname, 'dist'),
    compress: true,
    port: 9000,
    hot: true,
    proxy: {
      '/java-mexico-services-0.0.1': 'http://127.0.0.1:8080'
    }
  };
}

module.exports = config;
