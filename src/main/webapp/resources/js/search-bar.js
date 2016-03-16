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

var flag = window.flag || "";
if(flag === "default") {
    var func = OfferFilterModule.OfferFilter.prototype.redirectToOfferAll;
    $('.ItemADS').each(function () {
        $(this).children('a:first').click(func);
    })
    $.when(loadCategories).done(function(){
        $('.ItemADS div a').click(func);
    })
    $('#btn-offers-search').click(func);

    $('#filter-region-container').find('li').click(OfferFilterModule.OfferFilter.prototype.selectRegionInFilter);

    /*$('#input-region-search').click(showFilterRegionMenu);

    function showFilterRegionMenu() {
        $('#filter-region-menu').toggleClass('filter-elem-hidden');

        if($('#filter-region-menu').hasClass('filter-elem-hidden')) {
            initFilterRegionMenu();
        }
    }
    function initFilterRegionMenu() {
        $('#filter-city-box').addClass('filter-elem-hidden');
        $('#filter-city-box div.div-region').empty();
        $('#filter-area-box').removeClass('filter-elem-hidden');
        $('#filter-region-nav a:first').removeClass('filter-elem-hidden');
        $('#filter-region-nav a:not(:first)').addClass('filter-elem-hidden');
    }

    $('#filter-area-box').find('div.filter-region-elem').click(selectFilterArea);

    function selectFilterArea(event) {
        event.preventDefault();
        var area = $(event.currentTarget).find('a').text();
        $('#input-selected-area').val(area);
        $('#filter-area-box').addClass('filter-elem-hidden');
        drawCitiesInFilter(area);
        $('#filter-city-box').removeClass('filter-elem-hidden');
        $('#filter-region-nav a:first').addClass('filter-elem-hidden');
        $('#filter-region-nav a:not(:first)').removeClass('filter-elem-hidden');
        generateFilterRegionString();
    }

    function drawCitiesInFilter(area) {

        var cities = window.cities || {};
        var citiesArr = cities[area];

        var numInColumn = citiesArr.length / 2 + (citiesArr.length % 2);
        for (var i = 0; i < citiesArr.length; i++) {
            var parentBlock = (i + 1 <= numInColumn) ? $('#filter-city-box div.div-region-left') : $('#filter-city-box div.div-region-right');
            var li = $('<li class="filter-region-elem li-city"><a class="filter-region-a" href="#">' + citiesArr[i] + '</a></li>').click(selectFilterCity);
            parentBlock.append(li);
        }
    }

    $('#filter-region-nav a:eq(0)').click(allUkraineRegionClick);
    $('#filter-region-nav a:eq(1)').click(initFilterRegionMenu);
    $('#filter-region-nav a:eq(2)').click(selectAllCities);

    function allUkraineRegionClick(event) {
        event.preventDefault();
        $('#input-selected-area').val("");
        showFilterRegionMenu();
        generateFilterRegionString();
    }

    function selectAllCities(event) {
        event.preventDefault();

        showFilterRegionMenu();
        $('#input-selected-city').val("");
        generateFilterRegionString();
    }

    function selectFilterCity(event) {
        event.preventDefault();

        var city = $(event.currentTarget).children('a').text();
        $('#input-selected-city').val(city);
        showFilterRegionMenu();
        generateFilterRegionString();
    }

    $('#filter-icon-close').click(deleteFilterRegion);

    function deleteFilterRegion(event) {
        $('#input-selected-area').val("");
        $('#input-selected-city').val("");
        initFilterRegionMenu();
        $('#filter-region-menu').addClass('filter-elem-hidden');
        generateFilterRegionString();
    }

    function generateFilterRegionString() {
        var area = $('#input-selected-area').val();
        var city = $('#input-selected-city').val();
        var str = "";
        if(area) {
            str += area;
            if(city) str += ", " + city;
        }
        $('#input-region-search').val(str);
    }*/
}

