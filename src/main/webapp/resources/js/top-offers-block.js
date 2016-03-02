var offerFO = {};
offerFO.createdDateSortDirection = "DESC";
offerFO.skip = 0;
offerFO.limit = 3;

loadAndAppendTopOffers(offerFO);

$("#ad-caret").click(function () {
    loadAndAppendNextOffers(offerFO);
});

function getOfferUrl(offer) {
    return '/offer/' + offer.id;
}

function getOfferImageUrl(imagesIds) {
    if (imagesIds) {
        for (var imgId in imagesIds) {
            if (imagesIds[imgId] === "pic1") {
                return "/api/rest/fileStorage/OFFERS/file/read/id/" + imgId;
            }
        }
    }
    return "/resources/images/no_photo.jpg";
}

function appendOffer(offerURL, imageUrl, title) {
    $('#topOffersBlock').append($('.offer-item-wrapper').last().clone());
    $('.add-top1').last().css('background', 'url(' + imageUrl + ')  no-repeat center center').css('background-size', 'cover');
    $('.add-top1 span').last().text(title);
    $('.offer-item-wrapper').last().attr('href', offerURL).show();
}

function loadAndAppendTopOffers() {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/offersService/offer/read/all",
        data: JSON.stringify(offerFO),
        statusCode: {
            200: function (data) {
                var offers = data.entities;

                for (var i = 0; i < offers.length; i++) {
                    var offerURl = getOfferUrl(offers[i]);
                    appendOffer(offerURl, getOfferImageUrl(offers[i].imagesIds), offers[i].title);
                }
            }
            //,
            //204 : function() {
            //}
        }
    });
}

function loadAndAppendNextOffers() {

    if (offerFO.skip == 0) {
        offerFO.skip = offerFO.limit;
        offerFO.limit = 3;
    } else {
        offerFO.skip += 3;
    }

    loadAndAppendTopOffers();
}
