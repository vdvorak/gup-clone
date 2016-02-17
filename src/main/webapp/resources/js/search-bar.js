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

$(".search-img").click(function(){
    $(".search-img").toggleClass('trolol');
    var selectedService = $('#selectedService').find(":selected").val();
    $("#" + selectedService + "FilterBlock").slideToggle();
});

$("#selectedService").change(function(){
    if ($('.trolol').is(':visible') ) {
        $(".hidefilter").hide('fast');
        var selectedService = $('#selectedService').find(":selected").val();
        $("#" + selectedService + "FilterBlock").show("slow");
    }
});