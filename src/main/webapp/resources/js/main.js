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

$(document).ready(function () {

    $('#socialBtn').click(function (event) {
        event.preventDefault();
        $('#overlay').fadeIn(400,
            function () {
                $('#refill')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 200);
            });
    });

    $('.brokeAss > button, #overlay, .richAss > form > #close').click(function () {
        $('#refill')
            .animate({opacity: 0, top: '45%'}, 200,
                function () {
                    $(this).css('display', 'none');
                    $('#overlay').fadeOut(400);
                }
            );
    });

    $(".question-img").click(function () {
        $(".questionForm").slideToggle(1);
    });

    $(".caretContact").click(function () {
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

    $(".listArtist img").click(function () {
        $(".listArtist ul").css("height", "auto");
    });

    $(".doersRang div").click(function () {
        $(this).parent().closest('.doersFeed').find('.colNewsComments').slideToggle('slow');
    });

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

    $('#header_money_amount').on('input', function () {
        $.ajax({
            url: '/account/getLiqPayParam',
            method: 'POST',
            data: {'amount': $('#money_amount').val()},
            success: function (response) {
                $('#liq-pay-data').val(response[0]);
                $('#liq-pay-signature').val(response[1]);
            }
        });
    });

    $('#modal_money_amount').on('input', function () {
        $.ajax({
            url: '/account/getLiqPayParam',
            method: 'POST',
            data: {'amount': $('#modal_money_amount').val()},
            success: function (response) {
                $('#modal_liq-pay-data').val(response[0]);
                $('#modal_liq-pay-signature').val(response[1]);
            }
        });
    });

    $('.modal-pay-liq-pay').on('click', function () {
        $('#modal-bill-submit').click()

    })

    $.ajax({
        type: "POST",
        url: "/check-balance",
        cache: false,
        success: function (response) {
            $('#score').text(response);

            if (response >= 50) {
                $('.brokeAss').hide();
                $('.richAss').show()
            } else {
                $('.brokeAss').show();
                $('.richAss').hide()
            }
        }
    });


    $('#noMoneyStartRich').on('click', function () {
        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/join-organization",
            cache: false,
            success: function (response) {
                if (response == "2") {
                    alert("Поздравляем со вступлением в организацию!")
                }
            }
        });
    })

//  <js for header>

    $("body").prepend("<div class='fadeScreen'></div>");

    $(".fadeScreen").click(function() {
        $('.selecionado').removeClass( "selecionado" );
        $(".fadeScreen").hide()
        $(".user > div").slideUp("fast");
        $(".dropDownMail").slideUp("fast");
        $(".answer").slideUp('fast');
        $(".mailMessage").slideDown('fast');
        $(".dropDownBell").slideUp('fast');
        $(".book div").slideUp('fast');
        $(".dropDownMoney").slideUp('fast');
    });

    //

    $(".user").click(function() {
        $('.user > div').slideToggle('fast', function() {
            $(this).toggleClass('selecionado');
            if( $('.selecionado').is(':visible') ) {
                $(".fadeScreen").show()
            } else {
                $(".fadeScreen").hide()
            }
        });
    });

    $(".user > div").mouseleave(function() {
        setTimeout( function () {
            if ( !$('.user > div:hover').length ) {
                $('.selecionado').removeClass('selecionado');
                $('.user > div').slideUp('fast');
                $('.fadeScreen').hide('fast');
            }
        }, 1000);
    });

    $(".user > div").click(function(event) {
        event.stopPropagation();
    });

    //

    $(".mail").click(function(){
        $('.dropDownMail').slideToggle('fast', function() {
            $(this).toggleClass('selecionado');
            if( $('.selecionado').is(':visible') ) {
                $(".fadeScreen").show()
            } else {
                $(".fadeScreen").hide()
            }
        });
    });

    $(".mailMessage").click(function(event){
        event.stopPropagation();
        $(".mailMessage").hide('slow');
        $(".answer").show('slow');
    });

    $(".mailMessage, .answer").mouseleave(function() {
        setTimeout( function () {
            if ( !$('.mailMessage:hover, .answer:hover').length ) {
                $('.selecionado').removeClass('selecionado');
                $('.dropDownMail').slideUp('fast');
                $('.answer').slideUp('fast');
                $('.mailMessage').slideDown('fast');
                $('.fadeScreen').hide('fast');
            }
        }, 1000);
    });

    //

    $(".bell").click(function() {
        $('.dropDownBell').slideToggle('fast', function() {
            $(this).toggleClass('selecionado');
            if( $('.selecionado').is(':visible') ) {
                $(".fadeScreen").show()
            } else {
                $(".fadeScreen").hide()
            }
        });
    });

    $(".dropDownBell").mouseleave(function() {
        setTimeout( function () {
            if ( !$('.dropDownBell:hover').length ) {
                $('.selecionado').removeClass('selecionado');
                $('.dropDownBell').slideUp('fast');
                $('.fadeScreen').hide('fast');
            }
        }, 1000);
    });

    $(".dropDownBell").click(function(event) {
        event.stopPropagation();
    });

    //

    $(".book").click(function() {
        $('.book div').slideToggle('fast', function() {
            $(this).toggleClass('selecionado');
            if( $('.selecionado').is(':visible') ) {
                $(".fadeScreen").show()
            } else {
                $(".fadeScreen").hide()
            }
        });
    });

    $(".book div").mouseleave(function() {
        setTimeout( function () {
            if ( !$('.book div:hover').length ) {
                $('.selecionado').removeClass('selecionado');
                $('.book div').slideUp('fast');
                $('.fadeScreen').hide('fast');
            }
        }, 1000);
    });

    $(".book div").click(function(event) {
        event.stopPropagation();
    });

    //

    $(".money").click(function() {
        $('.dropDownMoney').slideToggle('fast', function() {
            $(this).toggleClass('selecionado');
            if( $('.selecionado').is(':visible') ) {
                $(".fadeScreen").show()
            } else {
                $(".fadeScreen").hide()
            }
        });
    });

    $(".dropDownMoney").mouseleave(function() {
        setTimeout( function () {
            if ( !$('.dropDownMoney:hover').length ) {
                $('.selecionado').removeClass('selecionado');
                $('.dropDownMoney').slideUp('fast');
                $('.fadeScreen').hide('fast');
            }
        }, 1000);
    });

    $(".dropDownMoney").click(function(event) {
        event.stopPropagation();
    });

    //

    $('.dropDownBook').enscroll({
        verticalTrackClass: 'track4',
        verticalHandleClass: 'handle4',
        minScrollbarLength: 28
    });

//  </js for header>
});