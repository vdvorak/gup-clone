var offerFilter = window.OfferFilter;
var offer = {};
var phonesSet;


if (typeof loggedInProfile == 'undefined') {
    $('#write-to-author').hide();
}


//    alert("Перед ажаксом: " + offerId);
$.ajax({
    type: "POST",
    contentType: "application/json; charset=utf-8",
    url: "/api/rest/offersService/offer/id/" + offerId + "/read",
    async: false,
    success: function (response) {
        offer = response;
        //alert("Первый" + JSON.stringify(offer))
    }
});

// ----------- Draw offer -------------------------------------------------------------------------------------------
$('.offer-title').text(offer.title);

if (offer.price) {
    $('.offer-price').text(offer.price);
    var currency = $('.currency');
    switch (offer.currency) {
        case 'UAH':
            currency.text(' грн.');
            break;
        case 'USD':
            currency.text(' дол.');
            break;
        case 'EUR':
            currency.text(' евро');
            break;
        default:
            currency.text(' грн.')
    }
} else {
    $('.offer-price').text("Нет цены")
}

$('#create-date').text(offer.createdDate);

if (offer.urgent) {
    if (offer.urgent == true) {
        $('#urgent').text('СРОЧНО')
    }
}

$('.view-counter').text(offer.views);

if (offer.videoUrl) {
    $('.offer-video').append('<iframe width="100%" height="auto" src="https://www.youtube.com/embed/' + offer.videoUrl.split('=')[1] + '" frameborder="0" allowfullscreen></iframe>');
}

if (offer.address.coordinates) {
    $('.offer-map').append('<iframe width="100%" height="auto" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=place_id:' + offer.address.coordinates + '&key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww" allowfullscreen></iframe>');
} else {
    $('.offer-map').remove();
}

if (offer.userInfo.skypeLogin) {
    $('.offer-skype').text(offer.userInfo.skypeLogin);
} else {
    $('.skype-block').remove()
}

if (offer.userInfo.contactName) {
    $('.contact-name-block-unit').text(offer.userInfo.contactName)
} else {
    $('.contact-name-block').remove();
}

$('.offer-description').text(offer.description);


// ------ Slider with photo
if (offer.imagesIds) {
    if (Object.keys(offer.imagesIds).length > 0) {
        for (var i in offer.imagesIds) {
            $('#offer-slider').append('<li><img src="/api/rest/fileStorage/OFFERS/file/read/id/' + i + '" /></li>');
        }
    } else {
        $('#offer-slider').append('<li><img src="/resources/images/no_photo.jpg" /></li>');
    }
    var SUKAA = $('#offer-slider').bxSlider({
        doubleControls: true,
        // pagerCustom`: '#bx-pager'
        buildPager: function (slideIndex) {
            var sourceAttribute = $('#offer-slider li img').eq(slideIndex + 1).attr('src');
            return '<div style="background-image: url(\'' + sourceAttribute + '\');"></div>';
        },
        onSlideAfter: function (elem) {
            var src = elem.find('img').attr('src');
            $('.modalSlider > img').attr('src', src);
        }

    });
    $('.super_netxt_knopka').on('click', function () {
        SUKAA.goToNextSlide();
        return false;
    })
    $('.super_prev_knopka').on('click', function () {
        SUKAA.goToPrevSlide();
        return false;
    })
}
// ------ Slider with photo


if (offer.address) {
    if (offer.address.country) {
        $('#offer-cities').append('<li><a id="address-country" href="#">' + offer.address.country + '</a>' + '</li>')
    }
    if (offer.address.area) {
        $('#offer-cities').append('<li><a id="address-area" href="#">' + " \ " + offer.address.area + '</a>' + '</li>')
    }
    if (offer.address.city) {
        $('#offer-cities').append('<li><a id="address-city" href="#">' + " \ " + offer.address.city + '</a>' + '</li>')
    }
}
$('#offer-cities li a').click(offerFilter.redirectToOfferAllByBreadcrumbs);

$.when(window.loadCategories, window.loadSubcategories).done(function () {
    var breadcrumbs = offer.categories;
    if (breadcrumbs[0]) {
        for (var i = 0; i < jsonCategory.length; i++) {
            if (jsonCategory[i].id === +breadcrumbs[0]) {
                $('#breadcrumbs').append('<li><a href="#" id="' + breadcrumbs[0] + '">' + jsonCategory[i].name + '</a>' + '</li>');

                if (breadcrumbs[1]) {
                    for (var m in jsonCategory[i].children) {
                        if (jsonCategory[i].children[m].id == +breadcrumbs[1]) {
                            $('#breadcrumbs').append('<li><a id="' + breadcrumbs[1] + '" href="#">' + jsonCategory[i].children[m].name + '</a>' + '</li>');
                        }
                    }
                }
                if (breadcrumbs[2]) {
                    var obj1 = +breadcrumbs[1] + "";
                    var obj2 = +breadcrumbs[2] + "";
                    $('#breadcrumbs').append('<li><a id="' + obj2 + '" href="#">' + jsonSubcategory[obj1].children[obj2].label + '</a>' + '</li>');
                }
            }
        }
    }
    $("#breadcrumbs li a").click(offerFilter.redirectToOfferAllByBreadcrumbs);
})

$('.show-number').on('click', function () {
    var phoneList = ' ';
    for (var i = 0; i < offer.userInfo.phoneNumbers.length; i++) {
        phoneList = phoneList += offer.userInfo.phoneNumbers[i] + "\n";
    }
    $('.phone-numbers').text(phoneList);
    $('.show-number').remove();
});

if (typeof loggedInProfile != 'undefined') {
    if (offer.authorId === loggedInProfile.id) {
        var editOfferLink = $('#edit-offer-link');
        editOfferLink.show();
        editOfferLink.attr('href', '/edit-offer/' + offer.id);
    }
}
// ----------- Draw offer -------------------------------------------------------------------------------------------


// ----------- Draw additional information about offer author ----------------------------------------------------
$.ajax({
    type: "POST",
    url: "/api/rest/profilesService/profile/read/id/" + offer.authorId,
    success: function (profile) {

        var imgSrc = (profile.imgId != '' && profile.imgId !== null)
            ? '/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId
            : '/resources/images/doersLogo.png';
        $('#avatar-img').attr('src', imgSrc);

        if (profile.username) {
            $('.author-name').text(profile.username)
        } else {
            $('.author-name').text("Имя автора не указано")
        }
        $('.author-link').attr('href', '/profile?id=' + offer.authorId);
        $('.author-rating').text(profile.point);
    }
});
// ----------- Draw additional information about offer author ----------------------------------------------------


// ---------------------------------------- Draw properties -------------------------------------------------------
$.when(window.loadOptions, window.loadParameters).done(function () {
    var offerProperties = offer.properties;
    for (var i in offerProperties) {
        var key = offerProperties[i].key;
        var value = offerProperties[i].value;
        var key_ru = '';
        var value_ru = '';
        for (var j in parameters) {
            if (parameters[j]["parameter"]["key"] === key) {
                key_ru = parameters[j]["parameter"]["label"];
                if (parameters[j]["parameter"]["type"] === 'input') {
                    value_ru = value;
                }
                break;
            }
        }
        if (value_ru === '') {
            for (var m in options) {
                if (options[m]["k"][key] !== undefined && options[m]["v"][value] !== undefined) {
                    value_ru = options[m]["v"][value];
                }
            }
        }
        if (value_ru !== 'Цена' && value_ru !== '') {
            $('#options').append('<div class="col-xs-6">' + key_ru + '</div><div class="col-xs-6">' + value_ru + '</div>')
        }

    }
})

// ---------------------------------------- Draw properties -------------------------------------------------------


// ---------------    BEGIN DRAW OFFERS IN BOTTOM    --------------------------------------------------------------//
$(document).ready(function () {
    var offerFO = {};
    offerFO.skip = 0;
    offerFO.limit = 5;
    readAllByFilter();


    function readAllByFilter() {

        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/read/all",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(offerFO),
            success: function (response) {
                if (response) {
                    var offersArr = response.entities;
                    var count = 0;
                    var maxCount = 5;

                    for (var i = 0; i < offersArr.length; i++) {
                        var offerObj = offersArr[i];

                        var imagesIds = offerObj.imagesIds;
                        var imgSrc = "";
                        var arrKeys = Object.keys(imagesIds);
                        if (arrKeys.length) {
                            for (var key in imagesIds) {
                                if (imagesIds[key] === 'pic1') {
                                    imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + key;
                                    break;
                                }
                            }
                            if (imgSrc === '') imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + arrKeys[0];
                        } else {
                            imgSrc = "/resources/images/no_photo.jpg";
                        }

                        var priceStr = "Нет цены";
                        if (offerObj.price) {
                            priceStr = offerObj.price.toString();
                                var strCurrency = (offerObj.currency === 'UAH') ? ' грн.'
                                    : (offerObj.currency === 'USD') ? ' дол.'
                                    : (offerObj.currency === 'EUR') ? ' евро'
                                    : ' грн.';
                                priceStr = priceStr + strCurrency;
                        }

                        var newLi = $('#li-offer-basic').clone()
                            .attr('id', "")
                            .css("display", "inline-block");
                        newLi.find('p').text(offerObj.title);
                        newLi.find('.image').attr("href", '/offer/' + offerObj.id + '');
                        newLi.find('img').attr("src", imgSrc);


                        newLi.children('span').text("Просмотров: " + offerObj.views);
                        newLi.find('a.btn').text(priceStr).attr("href", '/offer/' + offerObj.id + '');

                        var noticeBox = $('ul.notice-box');
                        if (count === maxCount) {
                            count = 0;
                            var newBox = noticeBox.last()
                                .clone()
                                .text("")
                                .insertAfter(noticeBox.last());
                        }
                        newLi.appendTo(noticeBox.last());
                        count++;
                    }
                }
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    }

});

// ---------------    END DRAW OFFERS    ---------------------------------------------------------------------------//


// ---------------------------------- Reservation -----------------------------------------------------------
// - Need consultation. Don't delete this part

//$('#socialBtn').click(function (event) {
//    event.preventDefault();
//    $('#overlay').fadeIn(400,
//        function () {
//            $('#refill')
//                .css('display', 'block')
//                .animate({opacity: 1, top: '50%'}, 200);
//        });
//});
//
//$('.brokeAss > button, #overlay, .richAss > form > #close').click(function () {
//    $('#refill')
//        .animate({opacity: 0, top: '45%'}, 200,
//        function () {
//            $(this).css('display', 'none');
//            $('#overlay').fadeOut(400);
//        }
//    );
//});
//
//// - first we check balance to make sure we have required amount
//$('#make-reserve').click(function (event) {
//    event.preventDefault();
//
//    $.ajax({
//        type: "POST",
//        url: "/check-balance",
//        cache: false,
//        success: function (response) {
//
//            if (response >= 5) {
//                $('.brokeAss').hide();
//                $('.richAss').show();
//
//                $('#noMoneyStartRich').attr('id', 'acccept-reservation');
//
//
//                $('#overlay').fadeIn(400,
//                    function () {
//                        $('#refill')
//                            .css('display', 'block')
//                            .animate({opacity: 1, top: '50%'}, 200);
//                    });
//
//
//                $('.message-payment-accept').text("С вашего счёта будет снято 5 гривен за бронирование объявления")
//
//            } else {
//                $('.brokeAss').show();
//                $('.richAss').hide();
//
//                $('#overlay').fadeIn(400,
//                    function () {
//                        $('#refill')
//                            .css('display', 'block')
//                            .animate({opacity: 1, top: '50%'}, 200);
//                    });
//
//                //$('.show-message-for-payment').text("Для бронирования объявления на счету должно быть не менее 5 гривен")
//
//            }
//        },
//        error: function (response) {
//            alert("Для бронирования нужно войти в систему")
//        }
//    });
//
//    $(document).on('click', '#acccept-reservation', function () {
//        $.ajax({
//            type: "POST",
//            url: "/api/rest/offersService/offer/id/" + offerId + "/reserve",
//            cache: false,
//            success: function (response) {
//                alert("Объявление успешно забронировано за вами!")
//            },
//            error: function (response) {
//                alert("Недостаточно денег на балансе")
//            }
//        });
//        $('#acccept-reservation').attr('id', 'noMoneyStartRich');
//    })
//});
// ---------------------------------- Reservation -----------------------------------------------------------






