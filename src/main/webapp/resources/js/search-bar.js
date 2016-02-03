$(document).on('click', '#userListBtn', function () {
    window.location.href = '/profile/list';
});

$(function() {
    $("#searchInput").autocomplete({
        source: function (request, response) {
            var selectedService = $('#selectedService').find(":selected").val();
            $.getJSON("/search/autocomplete/" + selectedService, {
                term: request.term
            }, response);
        }
    });
});

$(document).on('click', '#searchButton', function () {
    if ( $("#searchInput").val() == "") {
        $("#searchInput").focus();
    } else {
        var selectedService = $('#selectedService').find(":selected").val();
        window.location.href = '/' + selectedService + '/list?name=' + $("#searchInput").val();
    }
});