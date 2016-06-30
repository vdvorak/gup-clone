/**
 * Created by Андрій on 26.02.2016.
 */
window.routerApp = angular.module('routerApp', [])

function localDateTime(long) {
    long = new Date(parseInt(long));
    long = moment(long).locale("ru").format('LLL');
    return long;
}
function localDateTimeShort(long) {
    long = new Date(parseInt(long));
    long = moment(long).format('d.m.YY');
    return long;
}
var loadingQueue = []
$(document).ready(function () {
    for (var q in loadingQueue) {
        loadingQueue[q]()
    }
})

Nots = {}
Nots.images = {
    offer: "/resources/css/images/rupor.png",
    blog: "/resources/css/images/newspaper.png",
    tender: "/resources/css/images/hammer.png",
    project: "/resources/css/images/compass.png",
    investments: "/resources/css/images/dollarUp.png",
    balance: "/resources/css/images/balance.png"
}
Nots.NType = (function () {
    function NType(settings) {
        this.text = 'No description'
        this.image = Nots.images.offer
        this.href = ''
        this.addition = ''
        $.extend(this, settings)
    }

    return NType
})()
Nots.NType.prototype.getText = function (data) {
    var self = this
    return Nots.NType.processData(self.text, data).replace(/(\[)(.+)(\[)(.+)(]])/, function (a, b1, b2, b3, b4, b5) {
        if (!self.hasOwnProperty(b2)) {
            console.error('Type not have command "' + b2 + '"')
            self[b2] = ''
        }
        return '<a href="' + Nots.NType.processData(self[b2], data) + '">' + b4 + '</a>'
    })
}
Nots.NType.prototype.getHref = function (data) {
    return Nots.NType.processData(
        this.href, data
    )
}
Nots.NType.processData = function (href, data) {
    return href.replace(/({{)(.+)(}})/, function (a, b1, b2, b3) {
        if (!data.hasOwnProperty(b2)) {
            console.error('Data not have field "' + b2 + '"')
            data[b2] = ''
        }
        return data[b2]
    })
}
Nots.types = {
    BLOG_SUBSCRIPTION: new Nots.NType({
        text: "У Вашего блога [a1[новый подписчик]]",
        image: Nots.images.blog,
        href: '/blog/{{contentStoreId}}',
        a1: '/profile/{{makerId}}'
    }),

    BLOG_POST_LIKE: new Nots.NType({
        text: "[a1[Пользователю]] понравилась Ваша запись",
        image: Nots.images.blog,
        href: '/blog/{{contentStoreId}}',
        a1: '/profile/{{makerId}}'
    }),
    BLOG_POST_DISLIKE: new Nots.NType({
        text: "[a1[Пользователю]] не нравится Ваша запись",
        image: Nots.images.blog,
        href: '/blog/{{contentStoreId}}',
        a1: '/profile/{{makerId}}'
    }),

    BLOG_POST_COMMENT: new Nots.NType({
        text: "Новый комментарий",
        image: Nots.images.blog
    }),
    BLOG_POST_COMMENT_REPLY: new Nots.NType({
        text: "На ваш комментарий ответили",
        image: Nots.images.blog
    }),
    BLOG_POST_COMMENT_LIKE: new Nots.NType({
        text: "Лайк Вашего комментария",
        image: Nots.images.blog
    }),

    PROJECT_COMMENT: new Nots.NType({
        text: "Новый комментарий к проекту",
        image: Nots.images.project,
        href: '/project?id={{contentStoreId}}#{{contentId}}'
    }),
    PROJECT_COMMENT_REPLY: new Nots.NType({
        text: "На ваш комментарий ответили",
        image: Nots.images.project,
        href: '/project?id={{contentStoreId}}#{{contentId}}'
    }),

    MONEY_TRANSFER_TO_USER: new Nots.NType({
        text: "Вам зачислены средства",
        image: Nots.images.balance
    }),
    MONEY_TRANSFER_TO_PROJECT: new Nots.NType({
        text: "Вы инвестировали в проект",
        image: Nots.images.balance
    }),
    PROJECT_BRING_BACK_MONEY: new Nots.NType({
        text: "Проект вернул {{contentStoreId}} грн",
        image: Nots.images.balance,
        href: '/project?id={{makerId}}'
    }),


//for doerService
    NEW_CLIENT_WANT_CONFIRM: new Nots.NType({
        text: "NEW_CLIENT_WANT_CONFIRM",
        image: Nots.images.investments
    }),
    USER_ADD_TO_DOER_CLIENT_LIST: new Nots.NType({
        text: "USER_ADD_TO_DOER_CLIENT_LIST",
        image: Nots.images.investments
    }),

//for tenderService
    TENDER_END_DAY_NEED_CHOOSE_WINNER: new Nots.NType({
        text: "Тендер закончился, выберите победителя.",
        image: Nots.images.tender,
        href: '/tender/{{contentStoreId}}'
    }),
    YOU_HAVE_BEEN_ADDED_TO_CLOSE_TENDER: new Nots.NType({
        text: "Вас добавили в закрытый тендер",
        image: Nots.images.tender,
        href: '/tender/{{contentStoreId}}'
    }),
    NEW_PROPOSE_IN_YOUR_TENDER: new Nots.NType({
        text: "NEW_PROPOSE_IN_YOUR_TENDER",
        image: Nots.images.tender
    }),
    YOU_WON_IN_TENDER: new Nots.NType({
        text: "Вы выиграли в тендере!",
        image: Nots.images.tender,
        href: '/tender/{{contentStoreId}}'
    }),

    OFFER_RESERVATION: new Nots.NType({
        text: "Обьявление [a1[забронировано пользователем]]",
        image: Nots.images.offer,
        href: '/offer/{{contentStoreId}}',
        a1: '/profile/{{makerId}}'
    }),
    OFFER_RENT: new Nots.NType({
        text: "OFFER_RENT",
        image: Nots.images.offer
    })
}
Nots.scope = null
/*routerApp.controller('notifications', function($scope, $http, $window){
 Nots.scope = $scope
 })*/
loadingQueue.push(function () {
    $('#myBalance .historyItem').each(function (num, e) {
        var temp = $(e).find('.time')
        temp.text(localDateTime(temp.text()))
    })
    $('.notifications .notify').each(function (num, e) {
        var handle = $(e)
        var temp = handle.find('.text')
        var type = Nots.types[temp.html()]
        if (!type) {
            return console.error('Undefined event type: ' + temp.html())
        }
        var data = {
            targetUId: handle.attr('data-targetUId'),
            contentId: handle.attr('data-contentId'),
            makerId: handle.attr('data-makerId'),
            contentStoreId: handle.attr('data-contentStoreId')
        }
        temp.html(type.getText(data))
        handle.find('.avatar').attr('src', type.image)
        handle.attr('href', type.getHref(data))
    })
    /*Nots.scope.getText = function (type) {
     return Nots.types[type].text
     }
     Nots.scope.notifies = []
     var root = $('.notifications')
     R.Libra().activityFeed().event().read().all({
     skip: 0, limit: 999
     }, function(res){
     Nots.scope.notifies = res.entities
     Nots.scope.$apply()
     })*/
})

var User = {}
User.bank = {}
User.get = function (id, callback) {
    if (User.bank[id]) {
        return callback(null, User.bank[id])
    }
    else {
        R.Libra().profilesService().profile().read().id(id, null, function (res) {
            res.getPic = function () {
                return res.imgId ? '/api/rest/fileStorage/PROFILE/file/read/id/' + res.imgId : '/resources/images/no_photo.jpg'
            }
            res.getPage = function () {
                return '/profile?id=' + id
            }
            User.bank[id] = res
            return callback(null, User.bank[id])
        })
    }
}
loadingQueue.push(function () {
    User.current = $('.sideBlock .mainInfo').attr('data-id')
})

var updateHistoryLayout = null
loadingQueue.push(function () {
    var grid = $('.historyContainer').masonry({
        itemSelector: '.historyBox',
        columnWidth: 325,
        fitWidth: true,
        transitionDuration: '0.3s',
        resize: true,
        originLeft: true
    })
    updateHistoryLayout = function () {
        grid.masonry('layout')
    }
    updateHistoryLayout()
    $('.historyBox[toggler]').on('toggledOn toggledOff gboxOpen', function () {
        updateHistoryLayout()
    })
})

var Toggler = {}
Toggler.togglerAttr = 'toggler'
Toggler.toggledClass = 'toggled'
Toggler.togglerClass = 'toggler'
Toggler.init = function () {
    $('.greenBox .' + Toggler.togglerClass).on('click', function () {
        Toggler.toggleToggler(this)
    })
    //$('.greenBox').on('click', '.' + Toggler.togglerClass, function() {
    //	Toggler.toggleToggler($(this))
    //})
}
//В качестве параметра указываем любой обьект, который находится внутри обьекта с атрибутом Toggler.togglerAttr
Toggler.toggleToggler = function (e) {
    var root = Toggler.getRoot(e)
    if (root.hasClass(Toggler.toggledClass)) {
        Toggler.turnUntoggled(e)
    } else {
        Toggler.turnToggled(e)
    }
}
Toggler.turnToggled = function (e) {
    Toggler.runAct(Toggler.getRoot(e), function (t) {
        $(t).addClass(Toggler.toggledClass)
    })(function (t) {
        $(t).trigger('toggledOn')
    })
}
Toggler.turnUntoggled = function (e) {
    Toggler.runAct(Toggler.getRoot(e), function (t) {
        $(t).removeClass(Toggler.toggledClass)
    })(function (t) {
        $(t).trigger('toggledOff')
    })
}
Toggler.runAct = function (root, act) {
    var targets = Toggler.getTogglers(root)
    targets.push(root)
    //eval(parent.attr(Toggler.togglerAttr))
    var process = function (action) {
        targets.each(function (num, el) {
            action($(el))
        })
    }
    process(act)
    return process;
}
Toggler.getRoot = function (e) {
    var root = null
    if ($(e).is('[' + Toggler.togglerAttr + ']')) {
        root = $(e)
    } else {
        root = $(e).parent().closest('*[' + Toggler.togglerAttr + ']')
    }
    return root;
}
Toggler.getTogglers = function (root) {
    return root.find('.' + Toggler.togglerClass);
}

var GBox = {}
GBox.className = 'greenBox'
GBox.init = function () {
    $('.' + GBox.className + ' .closeBox').on('click', function () {
        GBox.close(this)
    })
}
//В качестве параметра указываем любой обьект, который находится внутри обьекта с класом GBox.className
GBox.open = function (e) {
    var gbox = GBox.getBox(e)
    gbox.removeClass('closed')
    gbox.show()
    gbox.trigger('gboxOpen', [gbox.attr('id')])
}
GBox.close = function (e) {
    var gbox = GBox.getBox(e)
    gbox.addClass('closed')
    gbox.hide()
    gbox.trigger('gboxClose', [gbox.attr('id')])
}
GBox.getBox = function (e) {
    var box = null
    if ($(e).hasClass(GBox.className)) {
        box = $(e)
    } else {
        box = $(e).parent().closest('.' + GBox.className)
    }
    return box;
}

Contacts = {}
Contacts.place = null
Contacts.searchBar = null
Contacts.scope = null
Contacts.init = function () {
    var main = $('.contactsMain')
    Contacts.place = main.find('.contactsContainer')
    var form = main.find('.searchContactsF')
    Contacts.searchBar = form.find('.text')
    var submit = form.find('.startSearch')

    Contacts.place.on('click', '.persona .sendMessage', function (event) {
        event.preventDefault()
        Dialogs.close()
        var contactId = $(this).parent().closest('.persona').attr('data-id')
        var dialog = Dialogs.getDialogByMember(contactId)
        if (dialog) {
            Dialogs.open(dialog.id)
        }
        else {
            Dialogs.remove('new')
            Dialogs.addDialog({
                id: 'new',
                members: [
                    {id: contactId}
                ]
            })
            Dialogs.open('new')
        }
    })

    Contacts.searchBar.keyup(function () {
        var filter = this.value.toLowerCase()
        var finded = false
        Contacts.place.find('.persona').each(function () {
            var _this = $(this)
            var title = _this.find('.name').text().toLowerCase()

            if (title.indexOf(filter) < 0) {
                if (_this.is(':visible')) {
                    _this.hide()
                }
            }
            else {
                if (!_this.is(':visible')) {
                    _this.show()
                }
                finded = true
            }
        })
        if (!finded) {
            main.find('.noFinded').show()
        }
        else {
            main.find('.noFinded').hide()
        }
    })

    Contacts.scope.contacts = []
    $('.contactsMain').find('._contact').each(function (num, e) {
        var id = $(e).attr('data-id')
        User.get(id, function (err, profile) {
            Contacts.scope.contacts.push({
                id: profile.id,
                name: profile.username,
                pic: profile.getPic(),
                homepage: profile.getPage(),
                vip: profile.contact.member ? 'vip' : ''
            })
            Contacts.scope.$apply()
        })
    })
}
routerApp.controller('contacts', function ($scope, $http, $window) {
    Contacts.scope = $scope
})

var Dialogs = {}
Dialogs.dialogIdAttr = 'data-id'
Dialogs.classOpened = 'opened'
Dialogs.dialogsScrollPositionOffset = 30

Dialogs.common = null
Dialogs.form = null
Dialogs.dialogsScrollPosition = 0
Dialogs.opened = null

//В качестве параметра указываем идентификатор диалога (содержится в атрибуте Dialogs.dialogIdAttr)
Dialogs.open = function (id) {
    Dialogs.form.find('.text').val('')
    Dialogs.dialogsScrollPosition = Dialogs.common.scrollTop()
    Dialogs.common.addClass(Dialogs.classOpened)
    Dialogs.unfixScroll(Dialogs.common)
    Dialogs.common.find('.dialog').each(function (num, e) {
        var dialog = $(this)
        dialog.addClass(Dialogs.classOpened)
        dialog.show()
        if ($(e).attr(Dialogs.dialogIdAttr) === id) {
            Dialogs.opened = Dialogs.dialogs[id]
            if (!Dialogs.form.is(':visible')) {
                Dialogs.form.show()
                Dialogs.fixScroll(dialog)
            }
            Dialogs.form.attr(Dialogs.dialogIdAttr, dialog.attr(Dialogs.dialogIdAttr))
        } else {
            dialog.removeClass(Dialogs.classOpened)
            dialog.hide()
        }
    })
    $('html,body').animate({
        scrollTop: $('.msAndNt').offset().top - Dialogs.dialogsScrollPositionOffset
    }, 0)
    Dialogs.form.find('.text').focus()
}//Закрываем любой открытый диалог
Dialogs.getId = function (obj) {
    return obj.attr('data-id')
}
Dialogs.close = function () {
    Dialogs.opened = null
    Dialogs.common.removeClass(Dialogs.classOpened)
    Dialogs.form.hide()
    Dialogs.common.find('.dialog').each(function (num, e) {
        var dialog = $(this)
        if (Dialogs.getId(dialog) === 'new') {
            Dialogs.remove(Dialogs.getId(dialog))
        }
        else {
            if (dialog.hasClass(Dialogs.classOpened)) {
                dialog.removeClass(Dialogs.classOpened)
                Dialogs.unfixScroll(dialog)
                if (dialog.length) {
                    dialog.scrollTop(dialog[0].scrollHeight - dialog.height())
                }
            } else {
                dialog.show()
            }
        }
    })
    $('html,body').animate({
        scrollTop: $('.msAndNt').offset().top - Dialogs.dialogsScrollPositionOffset
    }, 0)
    Dialogs.fixScroll(Dialogs.common)
    Dialogs.common.scrollTop(Dialogs.dialogsScrollPosition)
}
Dialogs.fixScroll = function (elem) {
    elem.enscroll({
        showOnHover: false,
        verticalTrackClass: 'track3',
        verticalHandleClass: 'handle3',
        verticalScrolling: true,
        easingDuration: 200,
        propagateWheelEvent: false
    })
}
Dialogs.unfixScroll = function (e) {
    e.enscroll('destroy')
}
Dialogs.init = function () {
    Dialogs.common = $('.msAndNt .messages')
    Dialogs.form = Dialogs.common.find('.messageForm')
    Dialogs.form.find('.arrowHide').click(function () {
        Dialogs.close()
    })
    $(Dialogs.common).on('click', '.dialog', function () {
        Dialogs.open($(this).attr(Dialogs.dialogIdAttr))
    })
    var onSubmit = function () {
        var msg = {}
        msg.message = Dialogs.form.find('.text').val()
        if (msg.message.length == 0) {
            return;
        }
        Dialogs.form.find('.text').val('')
        if (Dialogs.opened.id === 'new') {
            var newDialog = {
                members: Dialogs.opened.members,
                messages: [msg]
            }
            Dialogs.close()
            R.Libra().dialogueService().dialogue().create(newDialog, function (res) {

            })
        }
        else {
            R.Libra().dialogueService().dialogue().id(Dialogs.opened.id).message().create(msg, function (res) {

            })
        }
    }
    Dialogs.form.find('.text').keydown(function (e) {
        if (e.ctrlKey && e.keyCode == 13) {
            onSubmit()
        }
    })
    Dialogs.form.find('.messageSubmit').on('click', function (event) {
        event.preventDefault()
        onSubmit()
    })
    Dialogs.fixScroll(Dialogs.common)
    /*setInterval(Dialogs.update, 300)*///////////////////////////////////////////////////////////////////////////////////
}
Dialogs.update = function () {
    var unreaded = 0
    R.Libra().dialogueService().dialogue().read().all(null, function (res) {
        if (res.length == 0) {
            Dialogs.common.find('.noContent').show()
            return;
        }
        Dialogs.common.find('.noContent').hide()
        for (var d in res) {
            var resDialog = res[d]
            if (Dialogs.dialogs[resDialog.id]) {
                Dialogs.dialogs[resDialog.id].updateMessages(resDialog.messages)
            }
            else {
                Dialogs.addDialog(resDialog)
            }
            var unreadedFinded = false
            for (var m in Dialogs.dialogs[resDialog.id].messages) {
                var msg = Dialogs.dialogs[resDialog.id].messages[m]
                if (msg.whoRead.indexOf(User.current) < 0) {
                    unreadedFinded = true
                }
            }
            if (unreadedFinded) {
                unreaded++
            }
        }
        var tab = $('.greenBox.msAndNt .ptabs li[messagesTab]')
        tab.find('.count').text(unreaded)
    })
}
Dialogs.addDialog = function (data) {
    return new Dialogs.Dial(data)
}
Dialogs.remove = function (id) {
    var dialog = Dialogs.dialogs[id]
    if (dialog) {
        dialog.remove()
    }
}
Dialogs.getDialogByMember = function (member) {
    for (var d in Dialogs.dialogs) {
        var dialog = Dialogs.dialogs[d]
        for (var m in dialog.members) {
            if (dialog.members[m].id === member) {
                return dialog;
            }
        }
    }
    return null;
}
Dialogs.dialogs = {}
Dialogs.Dial = (function () {
    function Dial(settings) {
        this.id = ''
        this.members = []
        this.messages = []
        $.extend(this, settings)
        if (Dialogs.dialogs[this.id]) {
            console.error('Dialog ' + this.id + ' already created')
            return;
        }
        Dialogs.dialogs[this.id] = this
        this.handle = Dialogs.dialogTemplate.clone()
        this.handle.attr('data-id', this.id)
        this.handle.text()
        Dialogs.common.prepend(this.handle)

        var messages = this.messages.slice()
        this.messages = []
        this.updateMessages(messages)
    }

    return Dial
})()
Dialogs.Dial.prototype.addMessage = function (data) {
    var msg = new Dialogs.Message(this, data)
    this.messages.push(msg)
    return msg
}
Dialogs.Dial.prototype.updateMessages = function (messages) {
    if (messages.length > this.messages.length) {
        for (var i = this.messages.length == 0 ? 0 : this.messages.length; i > -1 && i < messages.length; i++) {
            this.addMessage(messages[i])
        }
    }
}
Dialogs.Dial.prototype.remove = function () {
    delete Dialogs.dialogs[this.id]
    this.handle.remove()
}
Dialogs.Message = (function () {
    function Message(dialog, settings) {
        var self = this
        this.authorId = ''
        this.message = 'No text assigned'
        this.date = 0
        $.extend(this, settings)
        self.handle = Dialogs.messageTemplate.clone()
        self.handle.find('.text').text(self.message)
        self.handle.attr('data-author', self.authorId)
        if (self.authorId === User.current) {
            self.handle.addClass('myself')
        }
        User.get(self.authorId, function (err, profile) {
            var persona = self.handle.find('.persona')
            persona.attr('href', profile.getPage())
            persona.find('.avatar').attr('src', profile.getPic())
            if (profile.contact.member) {
                persona.addClass('vip')
            }
        })
        self.handle.find('.date').text(localDateTimeShort(self.date))
        self.handle.prependTo(dialog.handle)
    }

    return Message
})()
Dialogs.dialogTemplate = $('<div class="dialog" data-id=""></div>')
Dialogs.messageTemplate = $(
    '<div class="msg" data-author="">' +
    '<a class="persona" href="#"">' +
    '<img src="/resources/css/images/profileListLogo.png" alt="" class="avatar">' +
    '<div class="date">25.10.15</div>' +
    '</a>' +
    '<div class="text">Message text</div>' +
    '<div class="clearfix"></div>' +
    '</div>')

var ELoader = (function () {
    function PageLoader(options) {
        this.api = null;
        this.id = 0;
        this.callback = function () {
        };
        this.href = function (id) {
            return id
        }
        this.template = ELoader.templateDefault;
        $.extend(this, options)
        this.skip = 5

        var self = this
        $(document).ready(function () {
            self.handle = $('#' + self.id)
            self.historyContent = self.handle.find('.historyContent').first()
            self.handle.find('.arrow.loader').each(function (num, el) {
                $(el).on('click', function () {
                    self.load(self.callback)
                })
            })
        })
    }

    return PageLoader
})()
ELoader.prototype.load = function () {
    var data = {}
    var self = this
    data.skip = this.skip
    data.limit = 5
    data.createdDateSortDirection = 'DESC'
    data.authorId = User.current
    //TODO DESC sorting & authorID
    this.api(JSON.stringify(data), function (res) {
        Toggler.turnToggled(self.handle)
        if (res.entities.length <= 0) {
            return;
        }
        self.skip += data.limit
        for (var r in res.entities) {
            var entity = res.entities[r]
            var temp = self.template.clone()
            temp.text(entity.title)
            temp.attr('href', self.href(entity.id))
            // self.historyContent.append(temp)
            self.historyContent.find('.historyItem').last().after(temp)
        }
        self.callback(res)
        updateHistoryLayout()
    }, null)
}
ELoader.templateDefault = $('<a class="historyItem" href="#" target="_blank">{title}</a>')
loadingQueue.push(function () {
    new ELoader({
        api: R.Libra().tenderService().tender().read().all,
        id: 'myTenders',
        href: function (id) {
            return '/tender/' + id
        }
    })
    new ELoader({
        api: R.Libra().projectsAndInvestmentsService().project().read().all,
        id: 'myProjects',
        href: function (id) {
            return '/project?id=' + id
        }
    })
    new ELoader({
        api: R.Libra().newsService().blog().read().all,
        id: 'myNews',
        href: function (id) {
            return '/blog/' + id
        }
    })
    new ELoader({
        api: R.Libra().offersService().offer().read().all,
        id: 'myOffers',
        href: function (id) {
            return '/offer/' + id
        }
    })
    new ELoader({
        api: R.Libra().projectsAndInvestmentsService().investorPost().read().all,
        id: 'myInvestments',
        href: function (id) {
            return '/investorPost/edit?id=' + id
        }
    })

    Toggler.init()
    GBox.init()
    Dialogs.init()
    Contacts.init()
    $('#tab-container-msAndNt').easytabs({
        animate: false,
        updateHash: false
    })
    $('.greenBox.optional').on('gboxClose', function (e, id) {
        $('#gboxTumblers input[type="checkbox"][gbox-id="' + id + '"]').attr('checked', true)
        Toggler.turnUntoggled($(this))
        $(this).find('.historyIcon').each(function (num, el) {
            $(el).hide()
            $(el).css('opacity', 0)
        })
        saveGBoxTogglers()
    }).on('gboxOpen', function (e, id) {
        $(this).find('.historyIcon').each(function (num, el) {
            $(el).css('opacity', 0)
            setTimeout(function () {
                $(el).show()
                $(el).animate({
                    opacity: 1
                }, {
                    duration: 300,
                    step: function (now) {
                        $(el).css({
                            opacity: now
                        });
                    }
                })
            }, 350)
        })
        saveGBoxTogglers()
    })
    $('#gboxTumblers input[type="checkbox"]').change(function () {
        var id = $(this).attr('gbox-id')
        var gbox = $('#' + id)
        if ($(this).is(':checked')) {
            GBox.open(gbox)
        } else {
            GBox.close(gbox)
        }
    })
    User.get(User.current, function (err, user) {
        var finded = false
        $('#gboxTumblers *[gbox-id]').each(function (num, e) {
            finded = false
            for (var s in user.priofficeSets) {
                if (user.priofficeSets[s] === $(e).val()) {
                    finded = true
                }
            }
            if (finded) return;
            $(e).attr('checked', true)
            var id = $(e).attr('gbox-id')
            var gbox = $('#' + id)
            GBox.open(gbox)
        })
    })
})
function saveGBoxTogglers() {
    var set = []
    $('#gboxTumblers *[gbox-id]').each(function (num, e) {
        if (!$(e).is(':checked')) {
            set.push($(e).val())
        }
    })
    R.Libra().profilesService().profile().edit({
        id: User.current,
        priofficeSets: set
    })
}


function contactToggle(e) {
    var handle = $('.contactsContainer .' + Toggler.togglerClass)
    GBox.open(handle)
}

/*function openGBox(e) {
 var handle = $('#' + $(e).attr('gbox-ref'))
 GBox.open(handle)
 }*/

function toggleSettingsBox(e) {
    $('#userSettingsSet').slideToggle()
    $(e).toggleClass('toggled')
}

// --------------------------------------  BEGIN cropper  ----------------------------------------------
var loadedProfile = {};
$.ajax({
    type: "POST",
    url: "/api/rest/profilesService/profile/read/id/" + profileId + "/wholeProfile",
    statusCode: {
        200: function (profile) {
            loadedProfile = profile;
        }
    }
});

var image = document.getElementById('cropper-image');
var cropper = new Cropper(image, {
    aspectRatio: 1 / 1,
    crop: function (data) {
    }
});

$('div.uploadButton').on('click', function () {
    $("#uploadProfilePhotoInput").click();
});

$('#uploadProfilePhotoInput').on('change', function (event) {
    var files = event.currentTarget.files;
    var reader = new FileReader();

    reader.addEventListener("load", function () {
        cropper.replace(reader.result);
    }, false);

    if (files[0]) {
        reader.readAsDataURL(files[0]);
    }

    $('#cropperModal').css('display', "block");
});

$(".cropper-btn-cancel").click(function () {
    $('#cropperModal').css('display', "none");
});

$(window).click(function (event) {
    var modal = document.getElementById('cropperModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
});

$(".cropper-btn-success").click(function () {
    $('#cropperModal').css('display', "none");

    var canvas = cropper.getCroppedCanvas();
    var dataURL = canvas.toDataURL('image/jpeg', 0.5);
    var blob = dataURItoBlob(dataURL);

    cropper.replace(dataURL);

    var formData = new FormData();
    formData.append('file', blob);

    var loadImg = $.ajax({
        type: "POST",
        url: "/api/rest/fileStorage/profile/file/upload?cacheImage=1",
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data, textStatus, request) {
            loadedProfile.imgId = data.id;
            $('div.photo img').attr("src", "/api/rest/fileStorage/profile/file/read/id/" + loadedProfile.imgId);
            cropper.replace('/api/rest/fileStorage/profile/file/read/id/' + loadedProfile.imgId);
        }
    });
    $("#uploadProfilePhotoInput").val("");
    $.when(loadImg).done(updateProfile);
});

function updateProfile() {
    return $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/edit",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(loadedProfile),
        statusCode: {
            200: function () {
            }
        }
    });
}

function dataURItoBlob(dataURI) {
    var binary = atob(dataURI.split(',')[1]);
    var array = [];
    for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
    }
    return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
}
// --------------------------------------  END cropper  ----------------------------------------------
