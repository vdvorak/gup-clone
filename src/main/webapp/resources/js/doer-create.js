if (typeof loggedInProfile == 'undefined') {
    $('#doer-create-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания объявления вам необходимо зарегистрироваться</i></p></div>')
}else{
}

var imgId = '';
var gupValidator = new window.GupValidator.Constructor('doer').init();

//----------------------------------------------------- Image form -----------------------------------------------
$('.doerCreationSubmit').click(function () {
    $('#photoInput').trigger('click');
});

$('#photoInput').on('change', function (event) {
    event.preventDefault();

    var files = event.currentTarget.files;
    var formImg = new FormData();
    formImg.append('file', files[0]);

    if (imgId !== '') {
        deleteImgFromDB();
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
            $('.doer-img ul').find('img').attr("src", "/api/rest/fileStorage/DOER/file/read/id/" + imgId);
            $('.doer-img ul li').removeClass('li-defaultIMG');
        }
    });

    $("#photoInput").val("");
});
//----------------------------------------------------- Image form -----------------------------------------------


///----------------------Delete photo from  DB-----------------------------------------
function deleteImgFromDB() {
    $('.doer-img ul').find('img').attr("src", "/resources/images/no_photo.jpg");
    $('.doer-img ul li').addClass('li-defaultIMG');
    $.ajax({
        url: '/api/rest/fileStorage/DOER/file/delete/id/' + imgId,
        method: 'POST',
        success: function (response) {
        },
        error: function (response) {
        }
    });
}
///----------------------Delete photo from  DB-----------------------------------------

///------------------------- Upload Doer -----------------------------------------------
$('#createDoer').on('click', function (event) {
    event.preventDefault();

    var doer = {};
    doer.title = $('#doerName').val();
    doer.body = $('#doerDescription').val();
    doer.imageId = imgId;
    doer.naceIds = $("#doerNaceIds").val();

    gupValidator.validate(doer);
    if(!gupValidator.isValid) return;

    $.ajax({
        type: "POST",
        url: "/api/rest/doerService/doer/create/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(doer),
        success: function (response) {
            window.location.href = '/tenders#tabs1-investment';
//               в перспективе должно перекидывать на страницу этого исполнителя - его просмотр
        }
    });
});
///------------------------- Upload Doer -----------------------------------------------

$.when(loadNace).done(function(response){
    var select = $('#doerNaceIds');
    for(var i = 0; i < response.length; i++) {
        var option = $('<option id="'+ response[i].id +'" value="'+ response[i].id +'">'+ response[i].id + ": " +response[i].name +'</option>');
        select.append(option);
    }
    $("#doerNaceIds").chosen();
});