/**
 * Created by Андрій on 20.01.2016.
 */
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
    $.ajax({
        type: "POST",
        url: self.baseHref + url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: data,
        complete: function (e, xhr, settings) {
            function throwError(errorMessage){
                if (error == null) {
                    error = function (msg) {
                        alert('Request error: ' + JSON.stringify(msg));
                        //console.error('Request error: ' + JSON.stringify(msg));
                    };
                }
                e.url = url;
                e.errorMessage = errorMessage;
                error(e);
            }
            var res = '';
            if (e.status === 200) {
                try{
                    res = JSON.parse(e.responseText);
                }
                catch(err){
                    //throwError("can't parse response");
                }
                console.log('Request result: ' + JSON.stringify(res));
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
Request.generator = {
    genIter: function (obj, n) {
        var nn = n + "a";
        var arr = [];
        for (var prop in obj) {
            if (!obj.hasOwnProperty(prop) || prop === "0") continue;

            var ret = '';
            var args = '';
            if (obj[prop] === '>') {
                args = 'data, success, error';
                ret = 'self.post(' + nn + ', ' + args + ');';
            }
            else {
                ret = 'return ' + Request.generator.genIter(obj[prop], nn) + ';';
            }
            if (prop[0] === '_') {
                //args = prop.slice(1) + ', ' + args;
                if (args.length > 0){
                    args  = ', ' + args;
                }
                args = prop.slice(1) + args;
                arr.push(String.format('[0]: function([4]){' +
                    'var [2] = join([1], "[0]", [0]);' +
                    '[3]' +
                    '}',
                    prop.slice(1), n, nn, ret, args));
            }
            else {
                arr.push(String.format('[0]: function([4]){' +
                    'var [2] = join([1], "[0]");' +
                    '[3]' +
                    '}',
                    prop, n, nn, ret, args));
            }
        }
        return '{' + arr.join(',') + '}';
    },
    gen: function (obj) {
        return 'R.Libra = function(){ var a = ""; var self = this; return ' + Request.generator.genIter(obj, 'a') + '};';
    }
};

function join(/* path segments */) {
    // Split the inputs into a list of path commands.
    var parts = [];
    for (var i = 0, l = arguments.length; i < l; i++) {
        parts = parts.concat(arguments[i].split("/"));
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

var R = new Request("/api/rest/");

/* Source */
R._libra = {
    newsService: {
        blogPost: {
            _id: {
                comment: {
                    _id: {
                        delete: '>',
                        like: '>'
                    },
                    create: '>'
                },
                read: '>',
                dislike: '>',
                like: '>'
            },
            read: {
                all: '>'
            }
        }
    },
    profilesService: {
        profile: {
            read: {
                _id: '>',
                _username: '>',
                all: '>'
            },
            update: '>',
            updateByAdmin: '>',
            delete: {
                _id: '>'
            }
        },
        friends: {
            _addFriend: '>'
        }
    }
};

/* OUT */
//var genout = Request.generator.gen(R._libra);
//console.log(genout);
//eval(genout);

/// PLACE HERE GENOUT ///

R.Libra = function(){ var a = ""; var self = this; return {newsService: function(){var aa = join(a, "newsService");return {blogPost: function(){var aaa = join(aa, "blogPost");return {id: function(id){var aaaa = join(aaa, "id", id);return {comment: function(){var aaaaa = join(aaaa, "comment");return {id: function(id){var aaaaaa = join(aaaaa, "id", id);return {delete: function(data, success, error){var aaaaaaa = join(aaaaaa, "delete");self.post(aaaaaaa, data, success, error);},like: function(data, success, error){var aaaaaaa = join(aaaaaa, "like");self.post(aaaaaaa, data, success, error);}};},create: function(data, success, error){var aaaaaa = join(aaaaa, "create");self.post(aaaaaa, data, success, error);}};},read: function(data, success, error){var aaaaa = join(aaaa, "read");self.post(aaaaa, data, success, error);},dislike: function(data, success, error){var aaaaa = join(aaaa, "dislike");self.post(aaaaa, data, success, error);},like: function(data, success, error){var aaaaa = join(aaaa, "like");self.post(aaaaa, data, success, error);}};},read: function(){var aaaa = join(aaa, "read");return {all: function(data, success, error){var aaaaa = join(aaaa, "all");self.post(aaaaa, data, success, error);}};}};}};},profilesService: function(){var aa = join(a, "profilesService");return {profile: function(){var aaa = join(aa, "profile");return {read: function(){var aaaa = join(aaa, "read");return {id: function(id, data, success, error){var aaaaa = join(aaaa, "id", id);self.post(aaaaa, data, success, error);},username: function(username, data, success, error){var aaaaa = join(aaaa, "username", username);self.post(aaaaa, data, success, error);},all: function(data, success, error){var aaaaa = join(aaaa, "all");self.post(aaaaa, data, success, error);}};},update: function(data, success, error){var aaaa = join(aaa, "update");self.post(aaaa, data, success, error);},updateByAdmin: function(data, success, error){var aaaa = join(aaa, "updateByAdmin");self.post(aaaa, data, success, error);},delete: function(){var aaaa = join(aaa, "delete");return {id: function(id, data, success, error){var aaaaa = join(aaaa, "id", id);self.post(aaaaa, data, success, error);}};}};},friends: function(){var aaa = join(aa, "friends");return {addFriend: function(addFriend, data, success, error){var aaaa = join(aaa, "addFriend", addFriend);self.post(aaaa, data, success, error);}};}};}}};

/* Tests */
//function m(msg){
//    console.log(msg);
//}
//var blog = R.Libra.newsService().blogPost().id;
//var comment = blog('bbb').comment().id;
//m(comment('lol').like());
//m(comment('azaza').delete());
//comment = blog('zzzzz').comment().id;
//m(comment('lol').like());
//m(comment('azaza').delete());
//
//var readProfile = R.Libra.profilesService().profile().read();
//m(readProfile.id('someId'));
//m(readProfile.username('someName'));
//m(readProfile.all());