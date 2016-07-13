'use strict'

let express = require('express'),
    app = express(),
    path = require('path'),
    fs = require('fs')

app.use(express.static(path.join(__dirname, "../public/src/main/webapp")))

let handler = (req, res)=>
  res.sendFile(path.join(__dirname, "../public/src/main/webapp/index.html"))


app.get('/', handler)
app.get('/favourites', handler)
app.get('/error/403', handler)
app.get('/error/404', handler)
app.get('/error/500', handler)
app.get('/bulletinDetails', handler)
app.get('/editProfile', handler)
app.get('/profile', handler)

app.listen(3000)
