(function (namespace) {

    'use strict';

    var isComplete = false;

    function OfferFilter() {
        this.skip = 0;
        this.limit = 10;
    }

    OfferFilter.prototype.cleanResult = function () {
        var offerBoxArr = $('ul.notice-box:not(:first)');
        for (var i = 0; i < offerBoxArr.length; i++) {
            offerBoxArr[i].remove();
        }
        $('ul.notice-box:first').text("");

        return this;
    }

    OfferFilter.prototype.completeCategories = function (bool) {
        if (arguments.length) {
            return isComplete;
        } else {
            return bool;
        }
    }

    OfferFilter.prototype.readAllByFilter = function () {
        if (this.categories && !this.categories.length) delete this.categories;
        if (this.properties && !this.properties.length) delete this.properties;

        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/read/all",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(this),
            statusCode: {
                200: function (data, textStatus, request) {
                    drawOffers(data.entities);
                }
            }
        });
        return this;
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
                priceStr = priceStr + currency;
            }
        }
        return priceStr;
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
        newLi.find('a.btn').text(priceStr).attr("href", '/offer/' + offerObj.id + '');

        return newLi;
    }

    function drawOffers(offersArr) {

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

    OfferFilter.prototype.setFilterOptions = function () {
/*
        var keyWords = $('#searchInput').val();
        if (keyWords !== "")  this.searchField = keyWords;
*/
        this.address = {
            country: 'Украина'
        };
        var city = $('#filter-text-city').text();
        var area = $('#filter-text-region').text();
        if (city !== 'Выберите город' && city !== '' && city !== 'Все города') {
            filter.address.city = city;
        }
        if (area !== 'Вся Украина' && area !== 'Выберите область' && area !== '') {
            filter.address.area = area;
        }

        this.properties = [];
        var typeOfPrice = $('#filter-price').val();
        if(typeOfPrice) this.properties.push({
            key: 'price',
            value: typeOfPrice
        });

        var param = $('.parameters').children();
        for(var i = 0; i < param.length; i++) {
            var prop = {};
            prop.key = param[i].name;
            prop.value = param[i].value;
            this.properties.push(prop);
        }

        if ($('#price-wrapper').css('display') !== "none") {
            this.fromPrice = $('#priceMin').val();
            this.toPrice = $('#priceMax').val();
            /*this.properties.push({
             key: 'currency',
             value: $('#filter-currency').val()
             });*/
        } else {
            delete filter.fromPrice;
            delete filter.toPrice;
        }

        return this;
    }

    OfferFilter.prototype.drawFilterOptions = function (id) {
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
        return this;
    }

    OfferFilter.prototype.deleteFilterOptions = function () {

        $('.price').css('display', 'none');
        $('.parameters').empty();
        $('#filter-price').empty();

        return this;
    }

    OfferFilter.prototype.drawCategories3lvl = function() {
        var jsonSubcategory = window.jsonSubcategory || {};

        $('#select-categories-3lvl option:not(:first)').remove();

        var id = this.categories[1];
        var select = $('#select-categories-3lvl');
        var child2 = {};
        if (jsonSubcategory[id]) {
            child2 = jsonSubcategory[id].children;
            for (var key in child2) {
                var option = $('<option id="' + key + '" value="' + key + '">' + child2[key].label + '</option>');
                select.append(option);
            }
        }
        if(select.children().length > 1) {
            select.css('display', 'inline-block');
            $('label[for="select-categories-3lvl"]').css('display', 'inline');
        }
        return this;
    }

    OfferFilter.prototype.drawSubcategories = function() {
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
                    var newA = $('<a id="' + child1[key].id + '" href="#">' + child1[key].name + '</a>');
                    $(subcategoriesBox).append(newA);
                }

                if (Object.keys(child1).length) {
                    var newA = $('<a href="#">Cмотреть все обьявления</a>');
                    $(subcategoriesBox).append(newA);
                }
            }
        });
    }

    OfferFilter.prototype.selectFilterPrice = function (event) {
        var selectVal = $(event.currentTarget).val();
        if (selectVal === 'price') {
            $('#price-wrapper').css('display', 'inline-block');
        } else {
            $('#price-wrapper').css('display', 'none');
        }
    }

    OfferFilter.prototype.selectRegionInFilter = function(event) {
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
    }

    OfferFilter.prototype.redirectToOfferAll = function(event) {
        event.preventDefault();

        var url = "/offers";
        $.get(url, function() {
            window.location.href = url;
        });
    }

    OfferFilter.prototype.parseUrlToFilter = function() {
        var url = window.location.href;
        if(url !== "/offers") {
            this.categories = [];
            var cat1 = getUrlParam("category1lvl");
            if (cat1) {
                this.categories.push(cat1);
                var cat2 = getUrlParam("category2lvl");
                if(cat2) this.categories.push(cat2);
            }

            this.address = {
                country: 'Украина'
            };

            var area = getUrlParam("area");
            if(area) this.address.area = area;
            var city = getUrlParam("city");
            if(city) this.address.city = city;
        }
        return this;
    }

    namespace.OfferFilter = OfferFilter;

})(window.OfferFilterModule = window.OfferFilterModule || {});

