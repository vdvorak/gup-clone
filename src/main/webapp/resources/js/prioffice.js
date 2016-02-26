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

$(document).ready(function(){
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
});






/**
 * Created by Андрій on 26.02.2016.
 */
var Toggler = {}
Toggler.togglerAttr = 'toggler'
Toggler.toggledClass = 'toggled'
Toggler.togglerClass = 'toggler'
Toggler.init = function () {
	$('.greenBox .' + Toggler.togglerClass).on('click', function () {
		Toggler.toggleToggler(this)
	})
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
	Dialogs.common.find('.dialog').each(function (num, e) {
		$(e).click(function () {
			Dialogs.open($(e).attr(Dialogs.dialogIdAttr))
		})
	})
	Dialogs.fixScroll(Dialogs.common)
}

$(document).ready(function () {
	Toggler.init()
	GBox.init()
	Dialogs.init()
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
	var grid = $('.historyContainer').masonry({
		// options
		itemSelector: '.historyBox',
		columnWidth: 325,
		fitWidth: true,
		transitionDuration: '0.3s',
		resize: true,
		originLeft: true
	})
	grid.masonry('layout')
	$('.historyBox[toggler]').on('toggledOn toggledOff gboxOpen', function(){
		grid.masonry('layout')
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