var offerFO = {};
offerFO.createdDateSortDirection = "DESC";
offerFO.skip = 0;
offerFO.limit = 3;

loadAndAppendTopOffers(offerFO);

$("#ad-caret").click(function(){
    loadAndAppendNextOffers(offerFO);
});

function getOfferUrl(offer) {
    return '/offer/' + offer.id;
}

function getOfferImageUrl(imagesIds) {
    if (imagesIds) {
        for (var imgId in imagesIds) {
            if (imagesIds[imgId] === "1") {
                return "/api/rest/fileStorage/OFFERS/file/read/id/" + imgId;
            }
        }
    }

    return "/resources/images/no_photo.jpg";
}

function appendOffer(elementId, offerURL, imageUrl, title) {
    $('#' + elementId).append(
    '<div class="add-top1">' +
        '<a href="' + offerURL + '" class="ad-a1">' + title + '</a>' +
    '</div>');

    $('.add-top1').last()
        .css('background', 'url(' + imageUrl + ')  no-repeat center center')
        .css('background-size', 'cover');
}

function loadAndAppendTopOffers() {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/offersService/offer/read/all",
        data: JSON.stringify(offerFO),
        statusCode: {
            200: function(data) {
                var offers = data.entities;

                for (var i = 0; i < offers.length; i++) {
                    var offerURl = getOfferUrl(offers[i]);

                    appendOffer('topOffersBlock', offerURl, getOfferImageUrl(offers[i].imagesIds), offers[i].title);
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
