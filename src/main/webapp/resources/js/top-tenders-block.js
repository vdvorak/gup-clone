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
            if (imagesIds[imgId] === "pic1") {
                return "/api/rest/fileStorage/TENDER/file/read/id/" + imgId;
            }
        }
    }
    return "/resources/images/no_photo.jpg";
}

function appendTender(tenderURL, imageUrl, title) {
    $('#topTendersBlock').append($('.tender-item-wrapper').last().clone());
    $('.tend-top1').last().css('background', 'url(' + imageUrl + ')  no-repeat center center').css('background-size', 'cover');
    $('.tend-top1 span').last().text(title);
    $('.tender-item-wrapper').last().attr('href', tenderURL).show();
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

                    appendTender(tenderURl, getTenderImageUrl(tenders[i].uploadFilesIds), tenders[i].title);
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
