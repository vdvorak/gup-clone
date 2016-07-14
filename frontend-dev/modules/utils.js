'use strict'
/*
  Expect options object like this:
  {
    "method" : "POST",
    "url" : "http://someurl.com/",
    "data" : "data",
    "headers" : {
      "Content-Type" : "application/json",
      "Content-Length" : "1023"
    }
  }

  DEFAULTS:
  Method - default is GET,
  URL - required,
  data - optional,
  headers - optional
*/

module.exports.request = function(options) {
  return new Promise( (resolve, reject) => {
    /* Setting defaults */
    let { method="GET", url, data, headers } = options

    /* Some validation */
    if(!url)
      return console.error("Url not specified")

    if( (method == "POST" || method == "PUT") && !data)
      return console.error("Nothing to send here =)")

    /* Start constructing request */
    var xhr = new XMLHttpRequest()
    xhr.open(method, url, true)

    if(headers)
      for( var prop in headers)
        xhr.setRequestHeader(prop, headers[prop])

    if(data)
      xhr.send(JSON.stringify(data))

    xhr.onreadystatechange = function() {
      if (this.readyState != 4)
        return

      if (this.status != 200)
        return reject('Error: ' + (this.status ? `(${this.status}) ${this.statusText}`: 'request fail'))
      else
        return resolve(this.responseText)

    }
  })
}
