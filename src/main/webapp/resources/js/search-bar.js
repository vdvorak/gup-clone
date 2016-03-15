$('#userListBtn').on('click', function () {
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

$('#searchButton').on('click', function () {
    window.location.href = getTargetUrlBasedOnCheckedFilters();
});

function getTargetUrlBasedOnCheckedFilters() {
    var selectedService = $('#selectedService').find(":selected").val();
    var targetUrl = '/' + selectedService + '/list' + '?';

    if ($("#searchInput").val()) {
        targetUrl += 'name=' + $("#searchInput").val() + '&';
    }

    switch(selectedService) {
        case 'profile': {
            if ($( "input[name='profileType']").is(":checked")) {
                targetUrl += 'type=' + $( "input[name='profileType']:checked").val() + '&';
            }
        }
            break;
        case 'news':
            //code block
            break;
        case 'project':
            //code block
            break;
        case 'tender':
            //code block
            break;
        case 'offer':
            //code block
            break;
        case 'doer':
            //code block
            break;
        //default:
        //default code block
    }

    return  targetUrl;
}

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

