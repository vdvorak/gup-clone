/*
  Expect options object like this:
  {
    "method" : "POST",
    "url" : "http://someurl.com/",
    "data" : "data",
    "error" : console.error,
    "success": console.log,
    "headers" : {
      "Content-Type" : "application/json",
      "Content-Length" : "1023"
    }
  }

  DEFAULTS:
  Method - default is GET,
  URL - required,
  data - optional,
  error - default is console.error
  success  - default is console.log,
  headers - optional
*/
module.exports.request = function(options) {
  /* Setting defaults */
  let { method="GET", url, data, error, success, headers } = options

  /* Some validation */
  if(!url)
    return console.error("Url not specified")

  if((method == "POST" || method == "PUT") && !data)
    return console.error("Nothing to send here =)")

  /* Start constructing request */
  var xhr = new XMLHttpRequest()
  xhr.open(method, url, true)

  if(headers)
    for( var prop in headers)
      xhr.setRequestHeader(prop, headers[prop])

  xhr.send(data)

  xhr.onreadystatechange = function() {
    if (this.readyState != 4)
      return

    if (this.status != 200) {
      if( error ) return error( 'Error: ' + (this.status ? `(${this.status}) ${this.statusText}`: 'request fail'))
    } else {
      if( success ) return success(this.responseText)
    }
  }
}
