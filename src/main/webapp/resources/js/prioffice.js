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
				return '/api/rest/fileStorage/PROFILE/file/read/id/' + res.contact.pic
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
			$(e).find('.photo img').attr('src', profile.getPic())
			$(e).find('.sendMessage').on('click', function (event) {
				event.preventDefault()
				alert('send message?))')
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

//В качестве параметра указываем идентификатор диалога (содержится в атрибуте Dialogs.dialogIdAttr)
Dialogs.open = function (id) {
	Dialogs.dialogsScrollPosition = Dialogs.common.scrollTop()
	Dialogs.common.addClass(Dialogs.classOpened)
	Dialogs.unfixScroll(Dialogs.common)
	Dialogs.common.find('.dialog').each(function (num, e) {
		var dialog = $(this)
		dialog.addClass(Dialogs.classOpened)
		dialog.show()
		if ($(e).attr(Dialogs.dialogIdAttr) === id) {
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
Dialogs.close = function () {
	Dialogs.common.removeClass(Dialogs.classOpened)
	Dialogs.form.hide()
	Dialogs.common.find('.dialog').each(function (num, e) {
		var dialog = $(this)
		if (dialog.hasClass(Dialogs.classOpened)) {
			dialog.removeClass(Dialogs.classOpened)
			Dialogs.unfixScroll(dialog)
			if (dialog.length) {
				dialog.scrollTop(dialog[0].scrollHeight - dialog.height())
			}
		} else {
			dialog.show()
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
	Dialogs.fixScroll(Dialogs.common)
}
Dialogs.dialogs = []
Dialogs.Dial = (function () {
	function Dial(settings) {
		this.opt = {
			id: ''
		}
		$.extend(this.opt, settings)
		this.handle = Dialogs.dialogTemplate.clone()
		this.handle.text()
		Dialogs.common.prepend(this.handle)
		Dialogs.dialogs.push(this)

		this.messages = []
	}
	return Dial
})()
Dialogs.Dial.prototype.addMessage = function (data) {
	var msg = new Dialogs.Message(this, data)
	this.messages.push(msg)
	return msg
}
Dialogs.Message = (function () {
	function Message(dialog, settings) {
		var self = this
		this.opt = {
			authorId: '',
			message: 'No text assigned',
			date: 0
		}
		$.extend(this.opt, settings)
		self.handle = Dialogs.messageTemplate.clone()
		self.handle.find('.text').text(self.opt.message)
		self.handle.attr('data-author', self.opt.authorId)
		if (self.opt.authorId === User.current){
			self.handle.addClass('myself')
		}
		User.get(self.opt.authorId, function(err, profile){
			var persona = self.handle.find('.persona')
			persona.find('.avatar').attr('src', profile.getPic())
			if (profile.contact.member){
				persona.addClass('vip')
			}
		})
		self.handle.find('.date').text(localDateTime(self.opt.date))
		self.handle.appendTo(dialog.handle)
	}
	return Message
})()
Dialogs.dialogTemplate = $('<div class="dialog" data-id="11"></div>')
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
	R.Libra().dialogueService().dialogue().read().all(null, function (res) {
		for (var d in res) {
			var _dialog = res[d]
			var dialog = new Dialogs.Dial(_dialog)
			for (var m in _dialog.messages) {
				var _message = _dialog.messages[m]
				dialog.addMessage(_message)
			}
		}
	})
})
var ELoader = (function () {
	function PageLoader(options) {
		this.opt = {
			api: null,
			id: 0,
			callback: function () {},
			template: ELoader.templateDefault
		}
		$.extend(this.opt, options)
		this.page = 0

		var self = this
		console.log('constructor: ' + self.opt.id)
		$(document).ready(function () {
			console.log('ready: ' + self.opt.id)
			self.handle = $('#' + self.opt.id)
			self.historyContent = self.handle.find('.historyContent').first()
			self.handle.find('.arrow.loader').each(function(num, el){
				console.log('each')
				$(el).on('click', function(){
					self.load(self.opt.callback)
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
	this.opt.api(JSON.stringify(data), function(res){
		Toggler.turnToggled(self.handle)
		if (res.entities.length <= 0){
			return;
		}
		for(var r in res.entities){
			var entity = res.entities[r]
			var temp = self.opt.template.clone()
			temp.text(entity.title)
			self.historyContent.append(temp)
		}
		self.opt.callback(res)
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