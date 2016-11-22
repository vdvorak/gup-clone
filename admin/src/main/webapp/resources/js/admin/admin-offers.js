let urlRussianLanguageForTables = '//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json';
let urlReadAllOffer = 'http://localhost:8184/api/rest/offersService/offer/read/all';
let ulrImg = 'http://localhost:8184/api/rest/fileStorage/offers/photo/read/id/';
let urlNoPhotoImg = 'http://localhost:8185/resources/images/no_photo.jpg';


function findFirstImg(arr) {
    var url = urlNoPhotoImg;
    var imgId = '';
    for (var i in arr) {
        if (arr[i] === '1') {
            imgId = i;
            url = ulrImg + imgId + "?cachedSize=small";
            break;
        }
    }
    return url;
}

$(document).ready(function () {
    var data;
    var offerFilterOptions = {};
    offerFilterOptions.skip = 0;
    offerFilterOptions.limit = 50;

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: urlReadAllOffer,
        data: JSON.stringify(offerFilterOptions),

        success: function (response) {


            // because we have offer wrapper
            var arr = [];
            response.forEach(el => {
                arr.push(el.offer)
            });


            data = arr;

            for (var i = 0; i < data.length; i++) {

                if (data[i].imagesIds !== null) {
                    console.log(data[i].imagesIds)
                    data[i].imagesIds = '<img src="' + findFirstImg(data[i].imagesIds) + '" width="100" height="100">';
                }
                else {
                    data[i].imagesIds = `<img src="${urlNoPhotoImg}" width="100" height="100">`;
                }
            }


            for (var i = 0; i < data.length; i++) {
                data[i].createdDate = new Date(parseInt(data[i].createdDate));
                data[i].createdDate = moment(data[i].createdDate).locale("ru").format('LLL');
            }


            var table = $('#offersTable').DataTable({
                select: {
                    style: 'single'
                },
                data: data,
                "columns": [
                    {"data": "imagesIds"},
                    {"data": "title"},
                    {"data": "createdDate"},
                    {"data": "moderationStatus"}
                ],
                "language": {
                    "url": urlRussianLanguageForTables
                }
            });

            table
                .on('select', function (e, dt, type, indexes) {
                    var rowData = table.rows(indexes).data().toArray();
                    $("input[name='transactionId']").attr("value", rowData[0].seoUrl);
                    $('#offerIdhref').attr("href", "/edit-offer/" + rowData[0].seoUrl);
                    $('#inp').removeAttr("readonly");
                    $('#editOfferButton').attr("class", "btn btn-danger");
                })
                .on('deselect', function (e, dt, type, indexes) {
                    $("input[name='transactionId']").attr("value", "");
                    $('#inp').attr("readonly", "readonly");
                    $('#editOfferButton').attr("class", "btn btn-danger disabled");
                });
        }
    });
});