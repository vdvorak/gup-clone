'use strict';

/* ENVIRONMENT VARIABLES AND DEPENDENCIES */
const NODE_ENV = process.env.NODE_ENV || "development",
      webpack = require('webpack'),
      path = require('path'),
      precss = require('precss'),
      autoprefixer = require('autoprefixer')

/* FRONT-END CONFIG */
var frontWebpackConfig = {
  entry: "./app.js",

  output: {
    path: path.join(__dirname, "../public/src/main/webapp/"),
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
      { test: /\.tsx?$/, loader: 'ts-loader' },
      {
        test: /\.scss$/,
        loaders: ["style", "css", "postcss", "sass"]
      },
       {
         test: /\.(jpe?g|png|gif|svg)$/i,
         loader: 'file?name=[path][name].[ext]'
       },
      {
        test: /\.json$/,
        loader : 'json'
      }
    ]
  },
  postcss: function () {
    return [precss, autoprefixer];
  }
};


/* EXPORTS */
module.exports = [ frontWebpackConfig ]
