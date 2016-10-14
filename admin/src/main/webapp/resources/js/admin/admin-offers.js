function findFirstImg(arr) {
    var url = '/resources/images/no_photo.jpg';
    var imgId = '';
    for (var i in arr) {
        if (arr[i] === 'pic1') {
            imgId = i;
            url = '/api/rest/fileStorage/OFFERS/file/read/id/' + imgId;
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
        url: "/api/rest/offersService/offer/read/all",
        data: JSON.stringify(offerFilterOptions),

        success: function (response) {

            data = response.entities;

            for (var i = 0; i < data.length; i++) {
                if (data[i].imagesIds !== null) {
                    data[i].imagesIds = '<img src="' + findFirstImg(data[i].imagesIds) + '" width="100" height="100">';
                }
                else {
                    data[i].imagesIds = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                }
            }


            for (var i = 0; i < data.length; i++) {
                data[i].createdDate = new Date(parseInt(data[i].createdDate));
                data[i].createdDate = moment(data[i].createdDate).locale("ru").format('LLL');
            }



            var table = $('#accountant').DataTable({
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
                    "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
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