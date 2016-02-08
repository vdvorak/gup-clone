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

function getOfferImagePreviewTag(offer) {
    JSON.stringify(offer);
    if (offer.imagesIds !== null) {
        for (var key in offer.imagesIds) {
            if (offer.imagesIds[key] === "1") {
                return '<img src="/api/rest/fileStorage/OFFERS/file/read/id/' + key + '" class="img-responsive" >';
            }
        }
    } else {
        return '<img src="/resources/images/no_photo.jpg" class="img-responsive" >';
    }
}

function appendOffer(elementId, offerURL, imagePreviewTag, title) {
    $('#' + elementId).append(
    '<div class="add-top1">' +
        '<a href="' + offerURL + '">' +
            imagePreviewTag +
        '</a>' +
        '<a href="' + offerURL + '" class="ad-a1">' + title + '</a>' +
    '</div>');
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
                    var imagePreviewTag = getOfferImagePreviewTag(offers[i]);

                    appendOffer('topOffersBlock', offerURl, imagePreviewTag, offers[i].projectName);
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
