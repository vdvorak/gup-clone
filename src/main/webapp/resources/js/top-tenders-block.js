var tenderFO = {};
tenderFO.createdDateSortDirection = "DESC";
tenderFO.skip = 0;
tenderFO.limit = 3;

loadAndAppendTopTenders(tenderFO);

$("#tend-caret").click(function(){
    loadAndAppendNextTenders(tenderFO);
});

function getTenderUrl(tender) {
    return '/tender/' + tender.id;
}

function getTenderImageUrl(imagesIds) {
    if (imagesIds) {
        for (var imgId in imagesIds) {
            if (imagesIds[imgId] === "1") {
                return "/api/rest/fileStorage/TENDER/file/read/id/" + imgId;
            }
        }
    }

    return "/resources/images/no_photo.jpg";
}

function appendTender(elementId, tenderURL, imageUrl, title) {
    $('#' + elementId).append(
    '<div class="tend-top1">' +
        '<a href="' + tenderURL + '" class="ad-a1">' + title + '</a>' +
    '</div>');

    $('.tend-top1').last()
        .css('background', 'url(' + imageUrl + ')  no-repeat center center')
        .css('background-size', 'cover');
}

function loadAndAppendTopTenders() {

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/tenderService/tender/read/all/",
        data: JSON.stringify(tenderFO),
        statusCode: {
            200: function(data) {
                var tenders = data.entities;

                for (var i = 0; i < tenders.length; i++) {
                    var tenderURl = getTenderUrl(tenders[i]);

                    appendTender('topTendersBlock', tenderURl, getTenderImageUrl(tenders[i].imagesIds), tenders[i].title);
                }
            },
            204 : function() {
            }
        }
    });
}

function loadAndAppendNextTenders() {

    if (tenderFO.skip == 0) {
        tenderFO.skip = tenderFO.limit;
        tenderFO.limit = 3;
    } else {
        tenderFO.skip += 3;
    }

    loadAndAppendTopTenders();
}
