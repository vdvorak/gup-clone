function join(/* path segments */) {
    // Split the inputs into a list of path commands.
    var parts = [];
    for (var i = 0, l = arguments.length; i < l; i++) {
        parts = parts.concat(arguments[i].toString().split("/"));
    }
    // Interpret the path commands to get the new resolved path.
    var newParts = [];
    for (i = 0, l = parts.length; i < l; i++) {
        var part = parts[i];
        // Remove leading and trailing slashes
        // Also remove "." segments
        if (!part || part === ".") continue;
        // Interpret ".." to pop the last segment
        if (part === "..") newParts.pop();
        // Push new path segments.
        else newParts.push(part);
    }
    // Preserve the initial slash if there was one.
    if (parts[0] === "") newParts.unshift("");
    // Turn back into a single string path.
    return newParts.join("/") || (newParts.length ? "/" : ".");
}
String.format = function () {
    // The string containing the format items (e.g. "{0}")
    // will and always has to be the first argument.
    var theString = arguments[0];

    // start with the second argument (i = 1)
    for (var i = 1; i < arguments.length; i++) {
        // "gm" = RegEx options for Global search (more than one instance)
        // and for Multiline search
        var regEx = new RegExp("\\[" + (i - 1) + "\\]", "gm");
        theString = theString.replace(regEx, arguments[i]);
    }

    return theString;
}

var Request = (function () {
    function Request(baseHref) {
        this.baseHref = baseHref
    }

    return Request
})()
Request.prototype.post = function (url, data, success, error) {
    var self = this;
    if (success == null) {
        console.error('Success callback is null for this request: ' + url);
        return;
    }
    if ((typeof data) === 'object'){
        data = JSON.stringify(data)
    }

    $.ajax({
        type: "POST",
        url: self.baseHref + url + '/',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: data,
        complete: function (e, xhr, settings) {
            function throwError(errorMessage) {
                if (error == null) {
                    error = function (msg) {
                        //alert('Request error: ' + JSON.stringify(msg));
                        //console.error('Request error: ' + JSON.stringify(msg));
                        console.error(String.format('data: [0]\nresponse: [1]',
                            data, JSON.stringify(msg)))
                    };
                }
                e.url = url;
                e.errorMessage = errorMessage;
                error(e);
            }

            var res = '';
            if (e.status === 200 || e.status === 201) {
                try {
                    res = JSON.parse(e.responseText);
                }
                catch (err) {
                    //throwError("can't parse response");
                }
                console.info(String.format('url: [0], data: [1]\nresponse: [2]', self.baseHref + url, data, JSON.stringify(res)))
                success(res);
            }
            else {
                throwError();
            }
        }
        /*success: success,
         error: function (response) {
         response.url = url;
         error(response)
         }*/
    });
}
var R = {}
R.request = new Request("/api/rest")