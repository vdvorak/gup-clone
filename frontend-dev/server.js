'use strict'

let express = require('express'),
    app = express(),
    path = require('path'),
    fs = require('fs')

const PORT = 3000

app.use(express.static(path.join(__dirname, "../public/src/main/webapp")))

let handler = (req, res)=>
  res.sendFile(path.join(__dirname, "../public/src/main/webapp/layout.html"))


app.get('/', handler)
app.get('/favourites', handler)
app.get('/403', handler)
app.get('/404', handler)
app.get('/500', handler)
app.get('/bulletinDetails', handler)
app.get('/bulletinAdd', handler)
app.get('/editProfile', handler)
app.get('/profile', handler)
app.get('/login', handler)
app.get('/register', handler)
app.get('/searchResults', handler)

app.get('/socketTest', handler) // <=== Это на java не переносить

app.get('/mail', handler)


console.log(`Server started at port: ${PORT}. Gl & HF =)`)

app.listen(PORT)
