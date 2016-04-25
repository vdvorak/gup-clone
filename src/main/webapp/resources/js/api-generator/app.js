/**
 * Created by ����� on 20.01.2016.
 */
var Generator = (function () {
    function Generator() {
    }
    return Generator
})()
Generator.generator = {
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
                ret = 'return ' + Generator.generator.genIter(obj[prop], nn) + ';';
            }
            var propSplitted = prop.split('_');
            if (propSplitted.length > 1) {
                prop = propSplitted[0];
                propSplitted.splice(0, 1);
                //args = prop.slice(1) + ', ' + args;
                if (args.length > 0) {
                    args = ', ' + args;
                }
                args = propSplitted.join(', ') + args;
                arr.push(String.format('[0]: function([4]){' +
                    'var [2] = join([1], "[0]", [5]);' +
                    '[3]' +
                    '}',
                    prop, n, nn, ret, args, propSplitted.join(', ')));
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
        return 'R.Libra = function(){ var a = ""; var self = R.request; return ' + Generator.generator.genIter(obj, 'a') + '};';
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

var R = new Generator();

/* Source */
R._libra = {
    newsService: {
        search: {
            blogPost: '>'
        },
        blog: {
            id_id: {
                read: '>',
                delete: '>'
            },
            read: {
                all: '>'
            },
            create: '>',
            edit: '>'
        },
        blogPost: {
            id_id: {
                comment: {
                    id_id: {
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
        },
        blogNewsFeed: {
            read_skip_limit: '>',
            create: '>',
            update: '>'
        },
        blogSubscription: {
            read: '>',
            blogId_id: {
                update: {
                    subscription: {
                        add: '>',
                        delete: '>'
                    }
                }
            }
        }
    },
    profilesService: {
        profile: {
            id_id: {
                myContactList: {
                    add: '>'
                }
            },
            read: {
                id_id: '>',
                username_username: '>',
                all: '>'
            },
            edit: '>',
            updateByAdmin: '>',
            delete: {
                id_id: '>'
            }
        },
        friends: {
            addFriend_id: '>'
        }
    },
    activityFeed: {
        event: {
            read: {
                all: '>'
            },
            id_eventId: {
                delete: '>'
            }
        }
    },
    dialogueService: {
        dialogue: {
            create: '>',
            id_id: {
                message: {
                    create: '>'
                }
            },
            read: {
                id_id: '>',
                all: '>'
            },
            update: {
                id_id: '>'
            }
        }
    },
    emailService: {
        email: {
            create: '>'
        }
    },
    nace: {
        create: '>',
        read: {
            id_id: '>',
            all: '>'
        },
        update: {
            id_id: '>'
        }
    },
    tenderService: {
        tender: {
            read: {
                id_id: '>',
                all: '>',
                allmynace: '>'
            },
            create: '>',
            id_id: {
                propose: {
                    create: '>'
                },
                member: {
                    create: '>'
                },
                update: '>'
            },
            userislegal_email: '>',
            chooseWinner: '>'
        }
    },
    doerService: {
        doer: {
            read: {
                all: '>'
            }
        }
    },
    offersService: {
        offer: {
            read: {
                all: '>'
            }
        }
    },
    projectsAndInvestmentsService: {
        project: {
            read: {
                all: '>'
            }
        },
        investorPost: {
            read: {
                all: '>'
            }
        }
    },
    activityFeed: {
        event: {
            read: {
                all: '>'
            },
            id_eventId: {
                delete: '>'
            }
        }
    }
};

/* OUT */
var genout = Generator.generator.gen(R._libra);
var fs = require('fs')
fs.writeFileSync('api-generated.js', genout)
//console.log(genout);
//eval(genout);

/// PLACE HERE GENOUT ///

//R.Libra = function(){ var a = ""; var self = this; return {newsService: function(){var aa = join(a, "newsService");return {search: function(){var aaa = join(aa, "search");return {blogPost: function(data, success, error){var aaaa = join(aaa, "blogPost");self.post(aaaa, data, success, error);}};},blog: function(){var aaa = join(aa, "blog");return {id: function(id){var aaaa = join(aaa, "id", id);return {read: function(data, success, error){var aaaaa = join(aaaa, "read");self.post(aaaaa, data, success, error);},delete: function(data, success, error){var aaaaa = join(aaaa, "delete");self.post(aaaaa, data, success, error);}};},read: function(){var aaaa = join(aaa, "read");return {all: function(data, success, error){var aaaaa = join(aaaa, "all");self.post(aaaaa, data, success, error);}};},create: function(data, success, error){var aaaa = join(aaa, "create");self.post(aaaa, data, success, error);},edit: function(data, success, error){var aaaa = join(aaa, "edit");self.post(aaaa, data, success, error);}};},blogPost: function(){var aaa = join(aa, "blogPost");return {id: function(id){var aaaa = join(aaa, "id", id);return {comment: function(){var aaaaa = join(aaaa, "comment");return {id: function(id){var aaaaaa = join(aaaaa, "id", id);return {delete: function(data, success, error){var aaaaaaa = join(aaaaaa, "delete");self.post(aaaaaaa, data, success, error);},like: function(data, success, error){var aaaaaaa = join(aaaaaa, "like");self.post(aaaaaaa, data, success, error);}};},create: function(data, success, error){var aaaaaa = join(aaaaa, "create");self.post(aaaaaa, data, success, error);}};},read: function(data, success, error){var aaaaa = join(aaaa, "read");self.post(aaaaa, data, success, error);},dislike: function(data, success, error){var aaaaa = join(aaaa, "dislike");self.post(aaaaa, data, success, error);},like: function(data, success, error){var aaaaa = join(aaaa, "like");self.post(aaaaa, data, success, error);}};},read: function(){var aaaa = join(aaa, "read");return {all: function(data, success, error){var aaaaa = join(aaaa, "all");self.post(aaaaa, data, success, error);}};}};},blogNewsFeed: function(){var aaa = join(aa, "blogNewsFeed");return {read: function(skip, limit, data, success, error){var aaaa = join(aaa, "read", skip, limit);self.post(aaaa, data, success, error);},create: function(data, success, error){var aaaa = join(aaa, "create");self.post(aaaa, data, success, error);},update: function(data, success, error){var aaaa = join(aaa, "update");self.post(aaaa, data, success, error);}};},blogSubscription: function(){var aaa = join(aa, "blogSubscription");return {read: function(data, success, error){var aaaa = join(aaa, "read");self.post(aaaa, data, success, error);},blogId: function(id){var aaaa = join(aaa, "blogId", id);return {update: function(){var aaaaa = join(aaaa, "update");return {subscription: function(){var aaaaaa = join(aaaaa, "subscription");return {add: function(data, success, error){var aaaaaaa = join(aaaaaa, "add");self.post(aaaaaaa, data, success, error);},delete: function(data, success, error){var aaaaaaa = join(aaaaaa, "delete");self.post(aaaaaaa, data, success, error);}};}};}};}};}};},profilesService: function(){var aa = join(a, "profilesService");return {profile: function(){var aaa = join(aa, "profile");return {read: function(){var aaaa = join(aaa, "read");return {id: function(id, data, success, error){var aaaaa = join(aaaa, "id", id);self.post(aaaaa, data, success, error);},username: function(username, data, success, error){var aaaaa = join(aaaa, "username", username);self.post(aaaaa, data, success, error);},all: function(data, success, error){var aaaaa = join(aaaa, "all");self.post(aaaaa, data, success, error);}};},update: function(data, success, error){var aaaa = join(aaa, "update");self.post(aaaa, data, success, error);},updateByAdmin: function(data, success, error){var aaaa = join(aaa, "updateByAdmin");self.post(aaaa, data, success, error);},delete: function(){var aaaa = join(aaa, "delete");return {id: function(id, data, success, error){var aaaaa = join(aaaa, "id", id);self.post(aaaaa, data, success, error);}};}};},friends: function(){var aaa = join(aa, "friends");return {addFriend: function(id, data, success, error){var aaaa = join(aaa, "addFriend", id);self.post(aaaa, data, success, error);}};}};},activityFeed: function(){var aa = join(a, "activityFeed");return {event: function(){var aaa = join(aa, "event");return {read: function(){var aaaa = join(aaa, "read");return {all: function(data, success, error){var aaaaa = join(aaaa, "all");self.post(aaaaa, data, success, error);}};},id: function(eventId){var aaaa = join(aaa, "id", eventId);return {delete: function(data, success, error){var aaaaa = join(aaaa, "delete");self.post(aaaaa, data, success, error);}};}};}};},dialogueService: function(){var aa = join(a, "dialogueService");return {dialogue: function(){var aaa = join(aa, "dialogue");return {create: function(data, success, error){var aaaa = join(aaa, "create");self.post(aaaa, data, success, error);},id: function(id){var aaaa = join(aaa, "id", id);return {message: function(){var aaaaa = join(aaaa, "message");return {create: function(data, success, error){var aaaaaa = join(aaaaa, "create");self.post(aaaaaa, data, success, error);}};}};},read: function(){var aaaa = join(aaa, "read");return {id: function(id, data, success, error){var aaaaa = join(aaaa, "id", id);self.post(aaaaa, data, success, error);},all: function(data, success, error){var aaaaa = join(aaaa, "all");self.post(aaaaa, data, success, error);}};},update: function(){var aaaa = join(aaa, "update");return {id: function(id, data, success, error){var aaaaa = join(aaaa, "id", id);self.post(aaaaa, data, success, error);}};}};}};},emailService: function(){var aa = join(a, "emailService");return {email: function(){var aaa = join(aa, "email");return {create: function(data, success, error){var aaaa = join(aaa, "create");self.post(aaaa, data, success, error);}};}};},nace: function(){var aa = join(a, "nace");return {create: function(data, success, error){var aaa = join(aa, "create");self.post(aaa, data, success, error);},read: function(){var aaa = join(aa, "read");return {id: function(id, data, success, error){var aaaa = join(aaa, "id", id);self.post(aaaa, data, success, error);},all: function(data, success, error){var aaaa = join(aaa, "all");self.post(aaaa, data, success, error);}};},update: function(){var aaa = join(aa, "update");return {id: function(id, data, success, error){var aaaa = join(aaa, "id", id);self.post(aaaa, data, success, error);}};}};}}};

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