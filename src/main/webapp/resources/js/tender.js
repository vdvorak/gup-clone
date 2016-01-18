/**
 * Created by qz on 1/18/2016.
 */
                                                        /*Create tender*/
$('#submit').click(function () {

    var tenderCreate = {};
    tenderCreate .tenderId = '${tenderId}';
    tenderCreate .title = $('#tc-tenderTitle').val();
    tenderCreate .text = $('#text').val();
    tenderCreate .address = {};
    tenderCreate .address.country = 'Украина';
    tenderCreate .address.area = $('#areaInp').val();
    tenderCreate .address.city = $('#cityInp').val();
    tenderCreate .imagesIds = imgsArr;
    tenderCreate .categories = [];
    tenderCreate .categories.push($('#category').val());

    $.ajax({
        type: "POST",
        url: "/api/rest/tenderService/tender/create/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(blogPost),
        success: function (response) {
            window.location.href = '/blog-post/view/' + response.id;
        },
        error: function (response) {
            alert("Внутренняя ошибка сервера");
        }
    });
});