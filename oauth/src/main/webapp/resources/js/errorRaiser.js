/**
 * Created by lenovoRoma on 25.04.2016.
 */
var MessageRaiser = (function () {
    function MessageRaiser(options) {
        $.extend(this, options)
        this.root = $(this.rootSelector)
        this.messageHandler = this.root.find(this.messageSelector)

        var self = this
        this.root.hover(
            function () {
                self.root.stop(true, false)
                self.root.animate({
                    opacity: 1
                }, 300, 'swing', function () {
                })
            }, function () {
                self.tryHide()
            }
        )
    }
    return MessageRaiser
})()
MessageRaiser.prototype.raise = function (msg) {
    this.messageHandler.text(msg)
    this.tryHide()
}
MessageRaiser.prototype.tryHide = function () {
    this.root.show()
    this.root.stop(true, false)
    var self = this
    this.root.animate({
        opacity: 1
    })
    this.root.animate({
        opacity: 0
    }, 3000, 'swing', function () {
        self.root.hide()
    })
}

var errorRaiser = null;
$(document).ready(function () {
    errorRaiser = new MessageRaiser({
        rootSelector: '#errorRaiser',
        messageSelector: '.messagePlace'
    })
})