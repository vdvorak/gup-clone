(function($){				
	jQuery.fn.lightTabs = function(options){

		var createTabs = function(){
			tabs = this;
			i = 0;
			
			showPage = function(i){
				$(tabs).children("div").children("div").hide();
				$(tabs).children("div").children("div").eq(i).show();
				$(tabs).children("ul").children("li").removeClass("active");
				$(tabs).children("ul").children("li").eq(i).addClass("active");
			}
								
			showPage(0);				
			
			$(tabs).children("ul").children("li").each(function(index, element){
				$(element).attr("data-page", i);
				i++;                        
			});
			
			$(tabs).children("ul").children("li").click(function(){
				showPage(parseInt($(this).attr("data-page")));
			});				
		};		
		return this.each(createTabs);
	};	
})(jQuery);



/*$(document).ready(function(){
	$(".tabs").lightTabs();
});
$(function () {
	$("#accordion").accordion({
		collapsible: true
	});
});
$(document).on('click', '.prioffice-close-tenders-ico', function(e){
	e.preventDefault();
	$('.myitems-tenders ').css('display', 'none');
});
$(document).on('click', '.prioffice-close-projects-ico', function(e){
	e.preventDefault();
	$('.myitems-projects ').css('display', 'none');
});
$(document).on('click', '.prioffice-close-news-ico', function(e){
	e.preventDefault();
	$('.myitems-news ').css('display', 'none');
});
$(document).on('click', '.prioffice-close-founds1-ico', function(e){
	e.preventDefault();
	$('.myitems-founds ').css('display', 'none');
});*/






/**
 * Created by Андрій on 26.02.2016.
 */
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
	for (var q in loadingQueue){
		loadingQueue[q]()
	}
})

var User = {}
User.bank = {}
User.get = function (id, callback) {
	if (User.bank[id]){
		return callback(null, User.bank[id])
	}
	else {
		R.Libra().profilesService().profile().read().id(id, null, function (res) {
			res.getPic = function () {
				return res.contact.pic ? '/api/rest/fileStorage/PROFILE/file/read/id/' + res.contact.pic : '/resources/images/no_photo.jpg'
			}
			res.getPage = function () {
				return '/profile/id/' + id
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
loadingQueue.push(function(){
	var grid = $('.historyContainer').masonry({
		// options
		itemSelector: '.historyBox',
		columnWidth: 325,
		fitWidth: true,
		transitionDuration: '0.3s',
		resize: true,
		originLeft: true
	})
	updateHistoryLayout = function(){
		grid.masonry('layout')
	}
	updateHistoryLayout()
	$('.historyBox[toggler]').on('toggledOn toggledOff gboxOpen', function(){
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
	Toggler.runAct(Toggler.getRoot(e), function(t){
		$(t).addClass(Toggler.toggledClass)
	})(function(t){
		$(t).trigger('toggledOn')
	})
}
Toggler.turnUntoggled = function (e) {
	Toggler.runAct(Toggler.getRoot(e), function(t){
		$(t).removeClass(Toggler.toggledClass)
	})(function(t){
		$(t).trigger('toggledOff')
	})
}
Toggler.runAct = function(root, act){
	var targets = Toggler.getTogglers(root)
	targets.push(root)
	//eval(parent.attr(Toggler.togglerAttr))
	var process = function(action){
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
Contacts.init = function(){
	var main = $('.contactsMain')
	Contacts.place = main.find('.contactsContainer')
	var form = main.find('.searchContactsF')
	Contacts.searchBar = form.find('.text')
	var submit = form.find('.startSearch')

	Contacts.place.find('.persona').each(function (num, e) {
		var id = $(e).attr('data-id')
		User.get(id, function(err, profile){
			$(e).find('.name').text(profile.username)
			if (profile.contact.member){
				$(e).addClass('vip')
			}
			$(e).find('a').attr('href', profile.getPage())
			$(e).find('.photo img').attr('src', profile.getPic())
			$(e).find('.sendMessage').on('click', function (event) {
				event.preventDefault()
				Dialogs.close()
				var dialog = Dialogs.getDialogByMember(profile.id)
				if (dialog){
					Dialogs.open(dialog.id)
				}
				else {
					Dialogs.remove('new')
					Dialogs.addDialog({
						id: 'new',
						members: [
							{id: profile.id}
						]
					})
					Dialogs.open('new')
				}
			})
		})
	})

	Contacts.searchBar.keyup(function () {
		var filter = this.value.toLowerCase()
		var finded = false
		Contacts.place.find('.persona').each(function() {
			var _this = $(this)
			var title = _this.find('.name').text().toLowerCase()

			if (title.indexOf(filter) < 0) {
				if (_this.is(':visible')){
					_this.hide()
				}
			}
			else {
				if (!_this.is(':visible')){
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
}

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
		if (Dialogs.getId(dialog) === 'new'){
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
	$(Dialogs.common).on('click', '.dialog', function() {
		Dialogs.open($(this).attr(Dialogs.dialogIdAttr))
	})
	Dialogs.form.find('.messageSubmit').on('click', function (event) {
		event.preventDefault()
		var msg = {}
		msg.message = Dialogs.form.find('.text').val()
		Dialogs.form.find('.text').val('')
		if (Dialogs.opened.id === 'new'){
			var newDialog = {
				members: Dialogs.opened.members,
				messages: [msg]
			}
			Dialogs.close()
			R.Libra().dialogueService().dialogue().create(newDialog, function (res) {
				console.log('dialog created!')
			})
		}
		else {
			R.Libra().dialogueService().dialogue().id(Dialogs.opened.id).message().create(msg, function (res) {
				console.log('message writed!')
			})
		}
	})
	Dialogs.fixScroll(Dialogs.common)
	setInterval(Dialogs.update, 300)
}
Dialogs.update = function () {
	R.Libra().dialogueService().dialogue().read().all(null, function (res) {
		for (var d in res) {
			var resDialog = res[d]
			if (Dialogs.dialogs[resDialog.id]){
				Dialogs.dialogs[resDialog.id].updateMessages(resDialog.messages)
			}
			else {
				Dialogs.addDialog(resDialog)
			}
		}
	})
}
Dialogs.addDialog = function (data) {
	return new Dialogs.Dial(data)
}
Dialogs.remove = function (id) {
	var dialog = Dialogs.dialogs[id]
	if (dialog){
		dialog.remove()
	}
}
Dialogs.getDialogByMember = function (member) {
	for (var d in Dialogs.dialogs){
		var dialog = Dialogs.dialogs[d]
		for (var m in dialog.members){
			if (dialog.members[m].id === member){
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
		if (Dialogs.dialogs[this.id]){
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
Dialogs.Dial.prototype.updateMessages = function(messages){
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
		if (self.authorId === User.current){
			self.handle.addClass('myself')
		}
		User.get(self.authorId, function(err, profile){
			var persona = self.handle.find('.persona')
			persona.find('.avatar').attr('src', profile.getPic())
			if (profile.contact.member){
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
	'<div class="msg" data-author="">'+
		'<div class="persona">'+
			'<img src="/resources/css/images/profileListLogo.png" alt="" class="avatar">'+
			'<div class="date">25.10.15</div>'+
		'</div>'+
		'<div class="text">Message text</div>'+
		'<div class="clearfix"></div>'+
	'</div>')
loadingQueue.push(function () {
	/*R.Libra().dialogueService().dialogue().read().all(null, function (res) {
		for (var d in res) {
			var _dialog = res[d]
			var dialog = new Dialogs.addDialog(_dialog)
			for (var m in _dialog.messages) {
				var _message = _dialog.messages[m]
				dialog.addMessage(_message)
			}
		}
	})*/
})

var ELoader = (function () {
	function PageLoader(options) {
		this.api = null;
		this.id = 0;
		this.callback = function () {};
		this.template = ELoader.templateDefault;
		$.extend(this, options)
		this.page = 0

		var self = this
		console.log('constructor: ' + self.id)
		$(document).ready(function () {
			console.log('ready: ' + self.id)
			self.handle = $('#' + self.id)
			self.historyContent = self.handle.find('.historyContent').first()
			self.handle.find('.arrow.loader').each(function(num, el){
				console.log('each')
				$(el).on('click', function(){
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
	data.skip = this.page
	data.limit = 5
	this.api(JSON.stringify(data), function(res){
		Toggler.turnToggled(self.handle)
		if (res.entities.length <= 0){
			return;
		}
		for(var r in res.entities){
			var entity = res.entities[r]
			var temp = self.template.clone()
			temp.text(entity.title)
			self.historyContent.append(temp)
		}
		self.callback(res)
		updateHistoryLayout()
	}, null)
	this.page += 1
}
ELoader.templateDefault = $('<a class="historyItem" href="#">{title}</a>')
loadingQueue.push(function(){
	//new ELoader({
	//	api: R.Libra().tenderService().tender().read().all,
	//	id: 'myTenders'
	//}).load()
	//new ELoader({
	//	api: R.Libra().projectsAndInvestmentsService().project().read().all,
	//	id: 'myProjects'
	//}).load()
	//new ELoader({
	//	api: R.Libra().newsService().blog().read().all,
	//	id: 'myNews'
	//}).load()
	//new ELoader({
	//	api: R.Libra().offersService().offer().read().all,
	//	id: 'myOffers'
	//}).load()
	//new ELoader({
	//	api: R.Libra().projectsAndInvestmentsService().investorPost().read().all,
	//	id: 'myInvestments'
	//}).load()

	Toggler.init()
	GBox.init()
	Dialogs.init()
	Contacts.init()
	$('#tab-container-msAndNt').easytabs({
		animate: false,
		updateHash: false
	})
	$('.greenBox.optional').on('gboxClose', function (e, id) {
		$('#gboxTumblers input[type="checkbox"][gbox-id="' + id + '"]').attr('checked', false)
		Toggler.turnUntoggled($(this))
		$(this).find('.historyIcon').each(function (num, el) {
			$(el).hide()
			$(el).css('opacity', 0)
		})
	}).on('gboxOpen', function (e, id) {
		$(this).find('.historyIcon').each(function (num, el) {
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
})


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