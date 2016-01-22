/**
 * Created by qz on 1/18/2016.
 */

var tender = {};


$(document).on('click', '#submit', function () {

    tender.title = $('.tm-tender-name').val();
    tender.body = $('#text').val();
    tender.address[1] =$('.tm-area').val();
    tender.address[2] =$('.tm-city').val();
    tender.price =$('.tm-price').val();
    tender.tenderNumber =$('.tm-number').val();


    $.ajax({
        type: "POST",
        url: "/api/rest/newsService/tender/create/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(tender),
        success: function (response) {
            window.location.href = '/tender/'+ response.id;


        },
        error: function (response) {
            alert("Внутренняя ошибка сервера");
        }
    });
});