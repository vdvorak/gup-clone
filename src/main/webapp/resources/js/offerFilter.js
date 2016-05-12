(function (namespace) {

    'use strict';

    var flag = window.flag || "";
    var utils = new OfferFilter();

    function OfferFilter() {
        this.skip = 0;
        this.limit = 10;
        this.searchField = getUrlParam('name');
        this.createdDateSortDirection = "DESC";
    }

    function cleanResult() {
        var offerBoxArr = $('ul.notice-box:not(:first)');
        for (var i = 0; i < offerBoxArr.length; i++) {
            offerBoxArr[i].remove();
        }
        $('ul.notice-box:first').text("");

        return namespace;
    }

    function readAllByFilter() {
        if (utils.categories && !utils.categories.length) delete utils.categories;
        if (utils.properties && !utils.properties.length) delete utils.properties;
        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/read/all",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(utils),
            statusCode: {
                200: function (data, textStatus, request) {
                    drawOffers(data.entities);
                },
                204: function (data, textStatus, request) {
                    drawNoFoundOffers();
                }
            }
        });
        return namespace;
    }

    function drawNoFoundOffers() {
        if(!$('.notice-box li:not(#li-offer-basic)').length) {
            $('#offers-notFound').removeClass('offers-display-none');
            $('#h2-top-offers').addClass('offers-display-none');
        }
    }

    function createNewBox() {
        var ulLast = $('ul.notice-box:last');
        ulLast.clone()
            .text("")
            .insertAfter(ulLast);
    }

    function getSrcOfMainImg(imagesIds) {
        var imgSrc = "";
        var arrKeys = Object.keys(imagesIds);
        if (arrKeys.length) {
            for (var key in imagesIds) {
                if (imagesIds[key] === 'pic1') {
                    imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + key;
                    break;
                }
            }
            if (imgSrc === '') imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + arrKeys[0];
        } else {
            imgSrc = "/resources/images/no_photo.jpg";
        }

        return imgSrc;
    }


    function getPriceStr(price, currency) {
        var priceStr = "Нет цены";
        if (price) {
            priceStr = price.toString();
            if (currency) {
                priceStr = priceStr + " " + getCurrency(currency);
            }
        }
        return priceStr;
    }

    function getCurrency(currency) {
        var resultCurrency = '';
        switch (currency) {
            case 'UAH':
                resultCurrency = 'грн.';
                break;
            case 'USD':
                resultCurrency = 'дол.';
                break;
            case 'EUR':
                resultCurrency = 'евро'
        }
        return resultCurrency;
    }

    function createNewOffer(offerObj) {

        var imgSrc = getSrcOfMainImg(offerObj.imagesIds);
        var priceStr = getPriceStr(offerObj.price, offerObj.currency);

        var newLi = $('#li-offer-basic').clone()
            .attr('id', "")
            .css("display", "inline-block");
        newLi.find('p').text(offerObj.title);
        newLi.find('.image').attr("href", '/offer/' + offerObj.id + '');
        newLi.find('img').attr("src", imgSrc);

        newLi.children('span').text("Просмотров: " + offerObj.views);
        newLi.children('.priceButton').text(priceStr);

        return newLi;
    }

    function drawOffers(offersArr) {
        $('#offers-notFound').addClass('offers-display-none');
        $('#h2-top-offers').removeClass('offers-display-none');

        var count = 0;
        var maxCount = 5;

        for (var i = 0; i < offersArr.length; i++) {
            var newOffer = createNewOffer(offersArr[i]);

            if (count === maxCount) {
                count = 0;
                createNewBox();
            }
            newOffer.appendTo($('ul.notice-box:last'));
            count++;
        }
    }

    function setFilterOptions() {
        /*
         var keyWords = $('#searchInput').val();
         if (keyWords !== "")  this.searchField = keyWords;
         */
        utils.address = {
            country: 'Украина'
        };
        var city = $('#input-selected-city').val();
        var area = $('#input-selected-area').val();
        if (city) utils.address.city = city;
        if (area) utils.address.area = area;

        utils.properties = [];
        var typeOfPrice = $('#filter-price').val();
        if (typeOfPrice) utils.properties.push({
            key: 'price',
            value: typeOfPrice
        });

        var param = $('.parameters').children();
        for (var i = 0; i < param.length; i++) {
            var prop = {};
            prop.key = param[i].name;
            prop.value = param[i].value;
            utils.properties.push(prop);
        }

        if ($('#price-wrapper').css('display') !== "none") {
            utils.fromPrice = $('#priceMin').val();
            utils.toPrice = $('#priceMax').val();
            /*utils.properties.push({
             key: 'currency',
             value: $('#filter-currency').val()
             });*/
        } else {
            delete utils.fromPrice;
            delete utils.toPrice;
        }

        return namespace;
    }

    function drawFilterOptions(id) {
        var parameters = window.parameters || [];
        var options = window.options || [];

        for (var i = 0; i < options.length; i++) {
            if (options[i]['c'][id]) {
                var name = "";
                for (j in options[i]['k']) {
                    name = j;
                }

                for (var j = 0; j < parameters.length; j++) {
                    if (name !== 'price') {
                        var select = $('<select name="' + name + '" id="00' + i + '">' + '</select>');
                        if (parameters[j]['parameter']['key'] === name && parameters[j]['parameter']['validators']['required'] === 1) {
                            select.prop("required", true);
                        }
                        $('.parameters').append(select);
                        break;
                    }
                }

                for (var j in options[i]['v']) {
                    var option = $('<option value = "' + j + '"  id ="' + j + '">' + options[i]['v'][j] + '</option>');
                    if (name === 'price') {
                        $('#filter-price').append(option);
                    } else {
                        $('#00' + i).append(option);
                    }
                }
                if (name === 'price') $('.price').css('display', 'inline-block');
            }
        }

        for (var j = 0; j < parameters.length; j++) {
            if (parameters[j]['parameter']['type'] === "input" && parameters[j]['categories'][id] && parameters[j]['parameter']['key'] !== 'price') {

                var inp = $('<input id="' + parameters[j]['parameter']['key'] + '" type="text"  name="' + parameters[j]['parameter']['key'] + '" placeholder="' + parameters[j]['parameter']['label'] + '"/>');
                $('.parameters').append(inp);
            }
        }
        if ($('.parameters').children().length) $('.parameters').css('display', 'block');

        return namespace;
    }

    function deleteFilterOptions() {

        $('.price').css('display', 'none');
        $('.parameters').empty();
        $('#filter-price').empty();

        return namespace;
    }

    function drawCategories3lvl() {
        var jsonSubcategory = window.jsonSubcategory || {};

        $('#select-categories-3lvl option:not(:first)').remove();

        var id = utils.categories[1];
        var select = $('#select-categories-3lvl');
        var child2 = {};
        if (jsonSubcategory[id]) {
            child2 = jsonSubcategory[id].children;
            for (var key in child2) {
                var option = $('<option id="' + key + '" value="' + key + '">' + child2[key].label + '</option>');
                select.append(option);
            }
        }
        if (select.children().length > 1) {
            select.css('display', 'inline-block');
            $('label[for="select-categories-3lvl"]').css('display', 'inline');
        }
        return namespace;
    }

    function drawSubcategories() {
        var jsonCategory = window.jsonCategory || [];

        $('.ItemADS').each(function () {
            var elem = $(this).children('a:first');
            var category1Id = elem.attr('id');
            var subcategoriesBox = elem.parent().find('div');

            var child1 = {};
            var childArr = jsonCategory.filter(function (obj) {
                return obj.id === +category1Id;
            });
            if (childArr[0]) {
                child1 = childArr[0].children;

                for (var key in child1) {
                    var newA = $('<a id="' + child1[key].id + '" href="#" data-level="2">' + child1[key].name + '</a>');
                    $(subcategoriesBox).append(newA);
                }

                if (Object.keys(child1).length) {
                    var newA = $('<a href="#">Cмотреть все обьявления</a>');
                    $(subcategoriesBox).append(newA);
                }
            }
        });
        return namespace;
    }

    function onClickCategory1lvl(event) {
        if (flag !== "offer-all") {
            redirectToOfferAllByCategories(event);
        } else {
            var id1 = $(event.currentTarget).attr('id');
            utils.categories = [];
            deleteFilterOptions();

            if (id1 !== 'free' && id1 !== 'exchange') {
                utils.categories.push(id1);
            } else {
                $('#filter-price').append('<option selected value="' + id1 + '" id="' + id1 + '"></option>');
            }
            cleanResult();
            setFilterOptions();
            readAllByFilter();
            drawFilterOptions(utils.categories[0]);

            $('#select-categories-3lvl').css('display', 'none');
            $('label[for="select-categories-3lvl"]').css('display', 'none');
            $('#filter-price').change();
        }

    }

    function onClickCategory2lvl(event) {
        if (flag !== "offer-all") {
            redirectToOfferAllByCategories(event);
        } else {
            var elem = $(event.currentTarget);
            var id2 = elem.attr('id');
            utils.categories = [];
            utils.categories.push(elem.parent().parent().children('a:first').attr('id'));
            if (id2) utils.categories.push(id2);

            cleanResult();
            deleteFilterOptions();
            setFilterOptions();
            readAllByFilter();
            drawFilterOptions((id2) ? utils.categories[1] : utils.categories[0]);
            drawCategories3lvl();

            $('#filter-price').change();
        }
    }

    function selectCategoryLvl3(event) {
        if (utils.categories.length > 2) utils.categories.pop();
        var cat3 = $(event.currentTarget).val();
        if (cat3) {
            utils.categories.push(cat3);
            deleteFilterOptions();
            drawFilterOptions(cat3);
        }
        $('#filter-price').change();
    }

    function selectFilterPrice(event) {
        var selectVal = $(event.currentTarget).val();
        if (selectVal === 'price') {
            $('#price-wrapper').css('display', 'inline-block');
        } else {
            $('#price-wrapper').css('display', 'none');
        }
    }

    /*    OfferFilter.prototype.selectRegionInFilter = function(event) {
     event.preventDefault();

     var region = $(event.currentTarget).children('a').text();

     $('#filter-text-region').text(region);
     $('#filter-city-container').find('li').remove();
     $('#filter-text-city').text('Выберите город');

     if (region === 'Вся Украина') {
     $('#filter-city-container').css('display', 'none');
     } else {
     drawCitiesInFilter(region);
     }
     }

     function drawCitiesInFilter(area) {
     var cities = window.cities || {};

     var citiesArr = cities[area];

     var parentBlock = $('#filter-city-container').find('.multi-column-dropdown').first();
     var li = $('<li><a href="#" style="font-weight: bold">Все города</a></li>').click(selectCityInFilter);
     parentBlock.append(li);

     var numInColumn = citiesArr.length / 2 + (citiesArr.length % 2);
     for (var i = 0; i < citiesArr.length; i++) {
     parentBlock = (i + 2 <= numInColumn) ? $('#filter-city-container').find('.multi-column-dropdown').first() : $('#filter-city-container').find('.multi-column-dropdown').last();
     li = $('<li><a href="#">' + citiesArr[i] + '</a></li>').click(selectCityInFilter);
     parentBlock.append(li);
     }

     $('#filter-city-container').css('display', 'inline-block');
     }

     function selectCityInFilter(event) {
     event.preventDefault();
     var city = $(event.currentTarget).children('a').text();
     $('#filter-text-city').text(city);
     }*/

    function getIdCategory1Lvl(id2lvl) {
        var id = $('#' + id2lvl).parent().parent().children('a:first').attr('id');
        return (id) ? id : "";
    }

    function parseUrlToFilter() {
        var url = decodeURI(window.location.href);
        if (url !== "/offers") {
            utils.categories = [];
            var cat1 = getUrlParam("category1lvl");
            if (cat1) {
                utils.categories.push(cat1);
                var cat2 = getUrlParam("category2lvl");
                if (cat2) {
                    utils.categories.push(cat2);
                    var cat3 = getUrlParam("category3lvl");
                    if(cat3) utils.categories.push(cat3);
                }
            }

            utils.address = {
                country: 'Украина'
            };
            var area = getUrlParam("area");
            if (area) utils.address.area = area;
            var city = getUrlParam("city");
            if (city) utils.address.city = city;

            utils.properties = [];
            var price = getUrlParam("price");
            if (price) utils.properties.push({key: 'price', value: price});

            var authorId = getUrlParam("authorId");
            if (authorId) utils.authorId = authorId;
        }
        return namespace;
    }

    function generateFilterRegionString() {
        var area = $('#input-selected-area').val();
        var city = $('#input-selected-city').val();
        var str = "";
        if (area) {
            str += area;
            if (city) str += ", " + city;
        }
        $('#input-region-search').val(str);
    }

    function initFilterRegionMenu() {
        $('#filter-city-box').addClass('filter-elem-hidden');
        $('#filter-city-box div.div-region').empty();
        $('#filter-area-box').removeClass('filter-elem-hidden');
        $('#filter-region-nav a:first').removeClass('filter-elem-hidden');
        $('#filter-region-nav a:not(:first)').addClass('filter-elem-hidden');
    }

    function showFilterRegionMenu() {
        $('#filter-region-menu').toggleClass('filter-elem-hidden');
    }

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

    function allUkraineRegionClick(event) {
        event.preventDefault();

        $('#input-selected-area').val("");
        $('#input-selected-city').val("");
        showFilterRegionMenu();
        generateFilterRegionString();
        if (flag !== "offer-all") redirectToOfferAllByRegion(event);
    }

    function selectAllCities(event) {
        event.preventDefault();

        showFilterRegionMenu();
        $('#input-selected-city').val("");
        generateFilterRegionString();
        if (flag !== "offer-all") {
            redirectToOfferAllByRegion(event);
        } else {
            cleanResult();
            setFilterOptions();
            readAllByFilter();
        }
    }

    function selectFilterCity(event) {
        event.preventDefault();

        var city = $(event.currentTarget).children('a').text();
        $('#input-selected-city').val(city);
        showFilterRegionMenu();
        initFilterRegionMenu();
        generateFilterRegionString();
        if (flag !== "offer-all") {
            redirectToOfferAllByRegion(event);
        } else {
            cleanResult();
            setFilterOptions();
            readAllByFilter();
        }
    }

    function deleteFilterRegion(event) {
        $('#input-selected-area').val("");
        $('#input-selected-city').val("");
        $('#filter-region-menu').addClass('filter-elem-hidden');
        initFilterRegionMenu();
        generateFilterRegionString();
        if (flag !== "offer-all") {
            redirectToOfferAllByRegion(event);
        } else {
            cleanResult();
            setFilterOptions();
            readAllByFilter();
        }
    }

    function submitFilter(event) {
        event.preventDefault();
        if (flag !== "offer-all") {
            redirectToOfferAll();
        } else {
            utils.skip +=10;

            setFilterOptions();
            readAllByFilter();
        }
    }

    function redirectToOfferAllByRegion(event) {
        event.preventDefault();
        var area = $('#input-selected-area').val();
        var city = $('#input-selected-city').val();

        var url = "/offers?";
        if (area) url += "area=" + area + "&";
        if (city) url += "city=" + city + "&";
        redirectToOfferAll(url);
    }

    function redirectToOfferAllByCategories(event) {
        event.preventDefault();
        var url = "/offers?";
        var elem = $(event.currentTarget);

        var id = elem.attr('id');
        var categoryLevel = elem.attr('data-level');
        if (id) {
            url += "category" + categoryLevel + "lvl=" + id + "&";
            if (categoryLevel === "2") {
                url += "category1lvl=" + getIdCategory1Lvl(id) + "&";
            }
        } else {
            id = elem.parent().parent().children('a:first').attr('id');
            url += "category1lvl=" + id + "&";
        }

        redirectToOfferAll(url);
    }

    function redirectToOfferAllByBreadcrumbs(event) {
        event.preventDefault();

        var elem = $(event.currentTarget);
        var parentId = elem.parent().parent().attr('id');
        var id = elem.attr('id');

        var url = "/offers?";

        if(parentId === "breadcrumbs") {
            utils.category = [];
            var childArr = $("#breadcrumbs li a");
            for (var i = 0; i < childArr.length; i++) {
                var thisID = $(childArr[i]).attr('id');
                utils.category.push(thisID);
                if (thisID === id) break;
            }
            for(var i = 0; i < utils.category.length; i++) {
                var parameter = (i < 1) ? "category1lvl" :
                    (i < 2) ? "category2lvl" : "category3lvl";
                url += parameter + "=" + utils.category[i] + "&";
            }
        } else if(parentId === "offer-cities") {
            utils.address = {};
            var childArr = $("#offer-cities li a");
            for (var i = 0; i < childArr.length; i++) {
                var thisID = $(childArr[i]).attr('id');
                utils.address[thisID.substring(thisID.indexOf("-") + 1)] = $.trim($(childArr[i]).text());
                if (thisID === id) break;
            }
            if(utils.address.area) url += "area=" + utils.address.area + "&";
            if(utils.address.city) url += "city=" + utils.address.city + "&";
        }

        redirectToOfferAll(url);
    }

    function redirectToOfferAll(url) {
        $.get(url, function () {
            window.location.href = (url !== "/offers?" && url) ? url : "/offers";
        });
    }

    function filterOffersByAuthor(event) {
        event.preventDefault();

        var url = '/offers?authorId='+ event.data +'&';
        redirectToOfferAll(url);
    }

    namespace.utils = utils;

    namespace.submitFilter = submitFilter;

    namespace.deleteFilterRegion = deleteFilterRegion;
    namespace.selectFilterCity = selectFilterCity;
    namespace.selectAllCities = selectAllCities;
    namespace.allUkraineRegionClick = allUkraineRegionClick;
    namespace.drawCitiesInFilter = drawCitiesInFilter;
    namespace.selectFilterArea = selectFilterArea;
    namespace.showFilterRegionMenu = showFilterRegionMenu;
    namespace.initFilterRegionMenu = initFilterRegionMenu;
    namespace.generateFilterRegionString = generateFilterRegionString;

    namespace.parseUrlToFilter = parseUrlToFilter;
    namespace.redirectToOfferAll = redirectToOfferAll;
    namespace.redirectToOfferAllByCategories = redirectToOfferAllByCategories;
    namespace.redirectToOfferAllByRegion = redirectToOfferAllByRegion;
    namespace.redirectToOfferAllByBreadcrumbs = redirectToOfferAllByBreadcrumbs;
    namespace.filterOffersByAuthor = filterOffersByAuthor;

    namespace.getIdCategory1Lvl = getIdCategory1Lvl;
    namespace.selectFilterPrice = selectFilterPrice;
    namespace.selectCategoryLvl3 = selectCategoryLvl3;
    namespace.onClickCategory2lvl = onClickCategory2lvl;
    namespace.onClickCategory1lvl = onClickCategory1lvl;
    namespace.drawSubcategories = drawSubcategories;
    namespace.drawCategories3lvl = drawCategories3lvl;
    namespace.deleteFilterOptions = deleteFilterOptions;
    namespace.drawFilterOptions = drawFilterOptions;
    namespace.setFilterOptions = setFilterOptions;
    namespace.readAllByFilter = readAllByFilter;
    namespace.cleanResult = cleanResult;

})(window.OfferFilter = window.OfferFilter || {});

