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
        },
        open: function( event, ui) {
            var content = $('#ui-id-1').children();
            for(var i = 0, contentLgth = content.length; i < contentLgth; i++) {
                $(content[i]).click(function(event){
                    $("#searchInput").val($(this).text());
                    window.location.href = getTargetUrlBasedOnCheckedFilters();
                });
            }
        }
    });
});

$('#searchButton').on('click', function () {
    window.location.href = getTargetUrlBasedOnCheckedFilters();
});

function getTargetUrlBasedOnCheckedFilters() {
    var selectedService = $('#selectedService').find(":selected").val();

    var targetUrl = (selectedService === 'profile' || selectedService === 'project') ? '/' + selectedService + '/list' + '?'
        : (selectedService === 'tender') ? '/tenders?'
        : (selectedService === 'doer') ? '/tenders#tabs1-investment?'
        : (selectedService === 'news') ? '/blog-post/news?'
        : (selectedService === 'offer') ? '/offers?'
        : '/';

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
// ------------------------------------ OFFER FILTER BEGIN ---------------------------------------------------//
    var offerFilter = window.OfferFilter;

    $('.ItemADS').each(function () {
        $(this).children('a:first').click(offerFilter.onClickCategory1lvl);
    })
    $.when(window.loadCategories).done(function(){
        $('.ItemADS div a').click(offerFilter.onClickCategory2lvl);
    })

    $('#input-region-search').click(offerFilter.showFilterRegionMenu);
    $('#filter-area-box').find('div.filter-region-elem').click(offerFilter.selectFilterArea);
    $('#filter-region-nav a:eq(0)').click(offerFilter.allUkraineRegionClick);
    $('#filter-region-nav a:eq(1)').click(offerFilter.initFilterRegionMenu);
    $('#filter-region-nav a:eq(2)').click(offerFilter.selectAllCities);
    $('#filter-icon-close').click(offerFilter.deleteFilterRegion);
    $('#btn-offers-search').click(offerFilter.submitFilter);

// ------------------------------------ OFFER FILTER END ---------------------------------------------------//


