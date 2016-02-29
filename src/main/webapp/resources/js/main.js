function getUrlParam(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? null : sParameterName[1];
        }
    }
}

$(document).ready(function(){
	$("#ad-caret").click(function(){
        $('.add-top1:first').clone().insertAfter('.add-top1:last');
        $('.add-top1:first').clone().insertAfter('.add-top1:last');
        $('.add-top1:first').clone().insertAfter('.add-top1:last');
    });
	
	$("#tend-caret").click(function(){
        $('.tend-top1:first').clone().insertAfter('.tend-top1:last');
        $('.tend-top1:first').clone().insertAfter('.tend-top1:last');
        $('.tend-top1:first').clone().insertAfter('.tend-top1:last');
    });
	
	$("#proj-caret").click(function(){
        $('.proj-top1:first').clone().insertAfter('.proj-top1:last');
        $('.proj-top1:first').clone().insertAfter('.proj-top1:last');
        $('.proj-top1:first').clone().insertAfter('.proj-top1:last');
    });
	
	$(".tidiDown").click(function(){
        $('.tidi4:first').clone().insertAfter('.tidi4:last');
    });
    
    $(".btnMail").mouseenter(function(){
        if ($('.answer').is(':visible') ) {
            return null;
        } else {
            $(".mailDrop").show('fast');
            $(".mailDrop-message").show('slow');
            $("#overlay").show();
        }
    });

    $(".mailDrop-message").click(function(){
        $(".mailDrop-message").hide('slow');
        $(".answer").show('slow');
        $("#overlay").show();
    });

    $("#overlay").click(function(){
        $(".mailDrop-message").show('slow');
        $(".answer").hide('slow');
        $("#overlay").hide();
        $(".mailDrop").hide();
    });
    
    $('#go').click( function(event){ // лoвим клик пo ссылки с id="go"
		event.preventDefault(); // выключaем стaндaртную рoль элементa
		$('#overlay').fadeIn(400, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
		 	function(){ // пoсле выпoлнения предъидущей aнимaции
				$('#modal_form') 
					.css('display', 'block') // убирaем у мoдaльнoгo oкнa display: none;
					.animate({opacity: 1, top: '50%'}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
		});
	});
	/* Зaкрытие мoдaльнoгo oкнa, тут делaем тo же сaмoе нo в oбрaтнoм пoрядке */
	$('#modal_close, #overlay').click( function(){ // лoвим клик пo крестику или пoдлoжке
		$('#modal_form')
			.animate({opacity: 0, top: '45%'}, 200,  // плaвнo меняем прoзрaчнoсть нa 0 и oднoвременнo двигaем oкнo вверх
				function(){ // пoсле aнимaции
					$(this).css('display', 'none'); // делaем ему display: none;
					$('#overlay').fadeOut(400); // скрывaем пoдлoжку
				}
			);
	});
    
    $('#goo').click( function(event){
		event.preventDefault();
		$('#overlay').fadeIn(400,
		 	function(){
				$('#modal_form') 
					.css('display', 'block')
					.animate({opacity: 1, top: '50%'}, 200);
		});
	});
	
	$('#modal_close, #overlay').click( function(){
		$('#modal_form')
			.animate({opacity: 0, top: '45%'}, 200,
				function(){
					$(this).css('display', 'none');
					$('#overlay').fadeOut(400);
				}
			);
	});
    
////////////////////
    
    $('#socialBtn').click( function(event){
		event.preventDefault();
		$('#overlay').fadeIn(400,
		 	function(){
				$('#refill') 
					.css('display', 'block')
					.animate({opacity: 1, top: '50%'}, 200);
		});
	});
	
	$('.brokeAss > button, #overlay, .richAss > form > #close').click( function(){
		$('#refill')
			.animate({opacity: 0, top: '45%'}, 200,
				function(){
					$(this).css('display', 'none');
					$('#overlay').fadeOut(400);
				}
			);
	});
    
    $(".question-img").click(function(){
        $(".questionForm").slideToggle(1);
    });
    
    $(".caretContact").click(function(){
        $(".caretContact").toggleClass('lol');
        $(".mapContact").slideToggle();
    });
    
    $('#tab-container').easytabs({
        animate: false
    });
    
    $('.bxslider').bxSlider();
    
    $('#tab-container-news').easytabs({
        animate: false
    });
    
    $(".NewsTabsFilterItem").on('click', function(){
        $('.intro').removeClass("intro");
        $(this).addClass("intro");
    });
    
    $('#newsFormComments').keyup(function() {
        var maxLength = 2000;
        var length = $(this).val().length;
        var length = maxLength-length;
        $('#chars').text(length + ' символов осталось');
    });
    
    $(".downComments").click(function(){
        $(".downComments").hide('slow');
        $(".colNewsComments").show('slow');
        $(".colComments").css("width", "50%");
    });
    
    $(".comments").click(function(){
        
        if ($('.backgroundColorComment').is(':visible') ) {
            return $('.backgroundColorComment').removeClass("backgroundColorComment");;
        } else {
            $(this).addClass("backgroundColorComment");
        }
    });
    
    $(".listArtist img").click(function(){
        $(".listArtist ul").css("height", "auto");
    });
    
    //
    
    $(".doersRang div").click(function(){
        $(this).parent().closest('.doersFeed').find('.colNewsComments').slideToggle('slow');
    });
    
    //
    
    (function (factory) {
        if (typeof define === "function" && define.amd) {

            // AMD. Register as an anonymous module.
            define(["../widgets/datepicker"], factory);
        } else {

            // Browser globals
            factory(jQuery.datepicker);
        }
    }(function (datepicker) {

        datepicker.regional.ru = {
            closeText: "Закрыть",
            prevText: "&#x3C;Пред",
            nextText: "След&#x3E;",
            currentText: "Сегодня",
            monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
"Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
            monthNamesShort: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн",
"Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
            dayNames: ["воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"],
            dayNamesShort: ["вск", "пнд", "втр", "срд", "чтв", "птн", "сбт"],
            dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
            weekHeader: "Нед",
            dateFormat: "dd.mm.yy",
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: false,
            yearSuffix: ""
        };
        datepicker.setDefaults(datepicker.regional.ru);

        return datepicker.regional.ru;

    }));

    $('#datepicker').datepicker();
    $('#datepicker2').datepicker();
    
    //
    
    $(".ItemADS").hover(
        function () {
            $(this).find('img').addClass("hoverIMG");
            $(this).find('.descriptionTitleLeft').fadeIn('fast');
            $(this).find('.descriptionTitleRight').fadeIn('fast');
        },
        function () {
            $(this).find('img').removeClass("hoverIMG");
            $(this).find('.descriptionTitleLeft').fadeOut('fast');
            $(this).find('.descriptionTitleRight').fadeOut('fast');
        }
    );
    
    //
    
    $(".descriptionTitleRight a").click(function(event){
        event.preventDefault();
        $(".superFilter").show('slow');
    });
    
    $(".descriptionTitleLeft a").click(function(event){
        event.preventDefault();
        $(".superFilter").show('slow');
    });
    
    $('.bxsliderTender').bxSlider({
        minSlides: 5,
        maxSlides: 5,
        slideWidth: 140,
        slideMargin: 20
    });
});