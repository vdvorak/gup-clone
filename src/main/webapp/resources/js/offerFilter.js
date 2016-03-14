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
        /*        console.log(this.properties);
         console.log(this.categories);*/
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

        return this;
    }

    OfferFilter.prototype.drawFilterOptions = function (id) {

        for (var i = 0; i < options.length; i++) {
            if (options[i]['c'][id]) {
                var name = "";
                for (j in options[i]['k']) {
                    name = j;
                }

                for (var j = 0; j < parameters.length; j++) {
                    if (name !== 'price') {
                        var select = $('<select class="prop" name="' + name + '" id="00' + i + '">' + '</select>');
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
                        $('select[name="price"]').append(option);
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
        $('select[name="price"]').empty();
        $('#select-categories-3lvl option:not(:first)').remove();

        return this;
    }

    OfferFilter.prototype.drawCategories3lvl = function() {
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

    namespace.OfferFilter = OfferFilter;

})(window.Offer = window.Offer || {});

