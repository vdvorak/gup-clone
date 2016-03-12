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
    if (typeof flag != 'undefined'){
        alert(flag);
    }


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

    $('.bxslider').bxSlider({
        // pagerCustom: '#bx-pager'
    });

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

    $('input#datepicker').datepicker({
        onSelect: function(date) {
            $('input#datepicker').val(date);
        }
    });
    $('input#datepicker2').datepicker({
        onSelect: function(date) {
            $('input#datepicker2').val(date);
        }
    });

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
            data: {'amount': $('#header_money_amount').val()},
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

    });

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
                    $('.modal').fadeOut(400);
                    $('#socialBtn').remove();
                }
            }
        });
    });

    $('.money > .dropDownMoney > button').click( function() {
        $('.modal').fadeIn(400);
    });

    $('.modal > .FillUpBalance > i').click(function() {
        $('.modal').fadeOut(400);
    });

    var modal = document.getElementsByClassName('modal');
    window.onclick = function(event) {
        if (event.target == modal[0]) {
            $('.modal').fadeOut(400);
        }
    }

    $('.contactA').click( function() {
        $('.contactA').hide(500);
        $('.restore').fadeIn(1000);
        return false;
    });
});