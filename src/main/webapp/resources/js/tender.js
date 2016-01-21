/**
 * Created by qz on 1/18/2016.
 */
                                                        /*Create tender*/
$('#submit').click(function () {

    var tenderCreate = {};
    tenderCreate .tenderId = '${tenderId}';
    tenderCreate .title = $('#tc-tenderTitle').val();
    tenderCreate .text = $('#text').val();

    $.ajax({
        type: "POST",
        url: "/api/rest/tenderService/tender/create/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(tenderCreate),
        success: function (response) {
            window.location.href = '/tender' + response.id;
        },
        error: function (response) {
            alert("Внутренняя ошибка сервера");
        }

    });
});