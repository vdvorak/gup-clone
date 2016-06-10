/**
 * Created by Lili on 06.05.2016.
 */
function ClearCookie(){
    Cookies.set('adialogs', '[]')
}

function guid() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
        s4() + '-' + s4() + s4() + s4();
}

var ADialog = (function () {
    function ADialog(options) {
        if (ADialog.dialogs.length > 0 && !ADialog.dialogs[0].dragged){
            ADialog.dialogs[0].remove()
        }
        ADialog.dialogs.push(this)
        this.dragged = false
        /*this.id = guid()*/
        this.isCollapsed = false
        var self = this
        this.handle = ADialog.dialogTemplate.clone()
        this.applyData(options)
        $('#adialog').append(this.handle)
        this.handle
            .on('mousemove', function(){
                $(this).draggable({
                    greedy: true,
                    containment:
                        [$(document).scrollLeft(),
                            $(document).scrollTop(),
                            $(document).scrollLeft()+$(window).width()-$(this).outerWidth(),
                            $(document).scrollTop()+$(window).height()-$(this).outerHeight()]
                });
            }).draggable({
            snap: "#adialog .dialog, html",
            handle: ".title",
            scroll: false,
            start: function(){
                self.select()
            },
            stop: function(){
                self.dragged = true
                ADialog.save()
            }
        })
        this.handle.click(function(){
            self.select()
            ADialog.save()
        })
        this.handle.find('.close').click(function(event){
            currentDialogId = $(event.target).parent().attr('id').split('_')[0];

            if (dialogues){

                for(var x = 0; x < dialogues.length; x++){
                    if(dialogues[x].id === currentDialogId){
                        dialogues[x].isOpenedOnce = false;
                        break;
                    }
                }
            }

            self.remove()
        })
    }
    return ADialog
})()
ADialog.prototype.select = function () {
    if (ADialog.dialogs[0] == this) return;
    ADialog.dialogs[0].deselect()
    this._select()
    var index = ADialog.dialogs.indexOf(this)
    if (index != -1) ADialog.dialogs.splice(index, 1)
    ADialog.dialogs.unshift(this)

    ADialog.update()
}
ADialog.prototype._select = function(){
    this.handle.addClass('selected')
}
ADialog.prototype.deselect = function () {
    this.handle.removeClass('selected')
}
ADialog.prototype.remove = function(){
    var index = ADialog.dialogs.indexOf(this)
    if (index != -1) ADialog.dialogs.splice(index, 1)

    this.handle.remove()
    ADialog.save()
    ADialog.update()
}
ADialog.prototype.serialize = function(){
    var pos = this.handle.position()
    return {
        left: pos.left,
        top: pos.top,
        id: this.id,
        isCollapsed: this.isCollapsed,
        dragged: this.dragged
    }
}
ADialog.prototype.apply = function (data) {
    this.handle.css({
        'z-index': data.zIndex
    })
    if (ADialog.dialogs[0] == this){
        this._select()
    }
    this.updateView()
}
ADialog.prototype.applyData = function (data) {
    $.extend(this, data)
    this.handle.css({
        top: this.top, left: this.left
    })
    this.updateView()
}
ADialog.prototype.updateView = function () {
    this.handle.find('.title').attr('id', this.id + '_title')
    this.handle.find('.messages').attr('id', this.id + "_messages")
    this.handle.find('textarea').attr('dialogueId', this.id)
    this.handle.find('textarea').attr('id', this.id + "_newMsg")
    this.handle.find('.minimize').attr('id', this.id + "_minimize")
    this.handle.find('.expand').attr('id', this.id + "_expand")
    this.handle.find('.expand > i').attr('id', this.id + "_expand-i")
    this.handle.find('.close').attr('id', this.id + "_close")
}
ADialog.dialogTemplate = $($("#adialogTemplate").html())
ADialog.dialogs = []
ADialog.init = function(options){
    var opts = {}
    $.extend(opts, options)
    var data = JSON.parse(Cookies.get('adialogs'))
    data.forEach(function(e){
        new ADialog(e)
    })
    ADialog.update()

    $(window).focus(ADialog.load)

    $(window).on('adialogs_update', function(){
        alert('LOOOL')
    });
}
ADialog.recieveUpdate = function(ev){
    alert('lol')
    if (ev.originalEvent.key != 'adialogs_update') return;
    var message = JSON.parse(ev.originalEvent.newValue)
//            if (!message) return;
    ADialog.update()
}
ADialog.save = function(){
    var data = ADialog.dialogs.map(function(e){
        return e.serialize()
    })
    Cookies.set('adialogs', JSON.stringify(data))

    localStorage.setItem('adialogs_update', guid())
    localStorage.removeItem('adialogs_update')
}
ADialog.load = function(){
    if (ADialog.dialogs.length > 0){
        ADialog.dialogs[0].deselect()
    }
    var data = []
    try{
        data = JSON.parse(Cookies.get('adialogs'))
    }catch(e){
        data = []
    }

    if(!data instanceof Array){
        data = []
    }

    var newArray = []
    data.forEach(function(dt){
        var dg = ADialog.getDialogById(dt.id)
        if (dg){
            newArray.push(dg)
            dg.applyData(dt)
        }
        else {
            if (!ADialog.dialogs.some(function(e){
                    e.id === dt.id
                })){
                newArray.push(new ADialog(dt))
            }
        }
    })

    for (var i = 0; i < ADialog.dialogs.length; i++){
        if (!data.some(function(e){
                return e.id === ADialog.dialogs[i].id
            })){
            ADialog.dialogs[i].remove()
            i--;
        }
    }

    ADialog.dialogs = newArray
    ADialog.update()
}
ADialog.getDialogById = function(id){
    for (var i in ADialog.dialogs){
        if (ADialog.dialogs[i].id === id){
            return ADialog.dialogs[i]
        }
    }
}
ADialog.update = function(){
    $('#dialogs').text(JSON.stringify(ADialog.dialogs.map(function(e){
        return e.serialize()
    })))
    ADialog.dialogs.forEach(function(e, index){
        e.apply({
            zIndex: ADialog.dialogs.length - index
        })
    })
}

function openDialog(dialogue, sender){
    if (ADialog.dialogs.some(function(e){
            return e.id === dialogue.id
        })) return;
    changePosition()
    var d = new ADialog({
        id: dialogue.id
    })
    d.select()
    ADialog.save()
    ADialog.update()
    setTitle(dialogue)
    showRecentMessages(dialogue, sender)
    setMinimizeChat()
    setExpandChat ()
}
ADialog.init()