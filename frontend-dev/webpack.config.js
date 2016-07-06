'use strict';

/* ENVIRONMENT VARIABLES AND DEPENDENCIES */
const NODE_ENV = process.env.NODE_ENV || "development",
      webpack = require('webpack'),
      fs = require('fs'),
      path = require('path')

/* FRONT-END CONFIG */
var frontWebpackConfig = {
  entry: "./app.js",

  output: {
    path: path.join(__dirname, "../public/src/main/webapp/WEB-INF/"),
    filename: "app.js"
  },

  devtool : NODE_ENV == "development" ? "cheap-inline-module-source-map" : null,

  plugins : [
    new webpack.NoErrorsPlugin(),
    new webpack.DefinePlugin({
      NODE_ENV : JSON.stringify(NODE_ENV)
    })
  ],

  module : {
    loaders : [
      {
        test : /\.js$/,
        loader : 'babel',
        query: {
          presets: ['es2015']
        }
      },
      //{ test: /\.css$/, loader: "style-loader!css-loader" },
      {
        test: /\.scss$/,
        loaders: ["style", "css", "sass"]
      },
      {
        test: /\.(jpe?g|png|gif|svg)$/i,
        loader: 'file?name=img/[name].[ext]'
      }
    ]
  }
};


/* EXPORTS */
module.exports = [ frontWebpackConfig ]
