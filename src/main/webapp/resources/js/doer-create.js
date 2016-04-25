if (typeof loggedInProfile == 'undefined') {
    $('#doer-create-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания объявления вам необходимо зарегистрироваться</i></p></div>')
}else{
}

var imgId = '';
var doer = {};
var naceIds = [];

//----------------------------------------------------- Image form -----------------------------------------------

$(document).on('change', '#photofile', function (e) {

    var formImg = new FormData($('#photoInput')[0]);

    if (imgId !== '') {
        deleteImgFromDB(imgId);
    }

    $.ajax({
        type: "POST",
        url: "/api/rest/fileStorage/DOER/file/upload/",
        data: formImg,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data, textStatus, request) {
            imgId = data.id;
            $('#imgPreview').attr("src", "/api/rest/fileStorage/DOER/file/read/id/" + imgId);
        }
    });
});
//----------------------------------------------------- Image form -----------------------------------------------


///----------------------Delete photo from  DB-----------------------------------------
function deleteImgFromDB(picId) {
    $.ajax({
        url: '/api/rest/fileStorage/DOER/file/delete/id/' + picId,
        method: 'POST',
        success: function (response) {
        },
        error: function (response) {
        }
    });
}
///----------------------Delete photo from  DB-----------------------------------------

///------------------------- Upload Doer -----------------------------------------------
$(document).on('click', '#createDoer', function (event) {

    naceIds.push($('#naceIds').val());

    doer.title = $('#doerTitle').val();
    doer.body = $('#doerDescription').val();
    doer.imageId = imgId;
    doer.naceIds = naceIds;

//    alert(JSON.stringify(doer));

    $.ajax({
        type: "POST",
//      url: "/api/rest/doerService/doer/create",
        url: "/api/rest/doerService/doer/create/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(doer),
        success: function (response) {
            window.location.href = '/index';
//               в перспективе должно перекидывать на страницу этого исполнителя - его просмотр
        }
    });
});
///------------------------- Upload Doer -----------------------------------------------