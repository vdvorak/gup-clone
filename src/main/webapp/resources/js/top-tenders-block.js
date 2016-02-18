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

function getTenderImagePreviewTag(tender) {
    if (tender.uploadFilesIds !== null) {
        for (var key in tender.uploadFilesIds) {
            if (tender.uploadFilesIds[key] === "1") {
                return '<img src="/api/rest/fileStorage/TENDER/file/read/id/' + key + '" class="img-responsive" >';
            }
        }
    }
    return '<img src="/resources/images/no_photo.jpg" class="img-responsive" >';
}

function appendTender(elementId, tenderURL, tenderImagePreviewTag, title) {
    $('#' + elementId).append(
    '<div class="tend-top1">' +
        '<a href="' + tenderURL + '">' +
            tenderImagePreviewTag +
        '</a>' +
        '<a href="' + tenderURL + '" class="ad-a1">' + title + '</a>' +
    '</div>');
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
                    var tenderImagePreviewTag = getTenderImagePreviewTag(tenders[i]);

                    appendTender('topTendersBlock', tenderURl, tenderImagePreviewTag, tenders[i].title);
                }
            }
            ,
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
