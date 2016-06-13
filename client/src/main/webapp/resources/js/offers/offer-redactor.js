(function (namespace) {

    'use strict';

    var gupValidator = new window.GupValidator.Constructor('offer').init(),
        isEditorPage = (window.location.pathname !== '/create-offer'),
        xhr,

        imgsArr = {},
        imgsArrResult = {},
        picArrDel = [],
        picArrNew = [],

        categories = [],
        lat = 50.401699,
        lng = 30.252512,
        placeKey = '';

    // ---------------    TOOLTIP    --------------------------//
    $(document).ready(function () {
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
    })
    // ---------------    TOOLTIP    --------------------------//

    // ---------------    INIT    --------------------------//
    init();

    function init() {
        if (typeof loggedInProfile == 'undefined') {
            $('#offer-container').empty()
                .append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания объявления вам необходимо зарегистрироваться</i></p></div>');
            return;
        }

        if (isUserAdmin(loggedInProfile)) {
            $('.new-adv-box').prepend('<input type="text" id="moderator-message" placeholder="Сообщение модератора" value="">')
        }


        initDropZone();
        initImages();
        initCategories();
        initEventHandlers();
        countTextLength();

        if (isEditorPage) initLoadOfferData();
    }

    function initEventHandlers() {
        $("#new-label-3").on('keyup', countTextLength);
        $('#region-container').find('li').click(selectRegion);
        $('#btn-add-tel').click(addTelephone);
        $('#selection-price').change(selectPrice);
        $('#btn-offer-save').click((isEditorPage) ? updateOffer : createOffer);
        if (isEditorPage) initDeleteOffer();
    }

    function initDeleteOffer() {
        $('#confirmOfferDelBtn').on('click', deleteOffer);
        $('#btn-offer-delete').on('click', function () {
            $("#confirmOfferDelete").show();
        });
        $('#cancelOfferDelBtn').on('click', function () {
            $("#confirmOfferDelete").hide();
        });
    }

    function initLoadOfferData() {
        xhr = readOfferData();
        $.when(xhr).done(drawLoadedOfferData);
    }

    function drawLoadedOfferData(offer) {
        if (offer.canBeReserved) $('#reserve-checkbox').prop('checked', true);
        if (offer.urgent) $('#new-label-check').prop('checked', true);

        categories = offer.categories;
        $.when(loadCategories, loadSubcategories, loadOptions, loadParameters).done(function () {
            drawLoadedCategories(offer)
        });

        imgsArr = offer.imagesIds;
        drawLoadedImages();

        drawLoadedAddress(offer.address);
        drawLoadedPhones(offer.userInfo.phoneNumbers);

        if (isUserAdmin(loggedInProfile) && offer.moderationMessage) {
            $('#moderator-message').val(offer.moderationMessage.message)
        }

        if (offer.moderationMessage && !offer.moderationMessage.read && !isUserAdmin(loggedInProfile)) {
            if (offer.moderationMessage.message !== null) {
                $('.new-adv-box').prepend('<div><span style="color: red">Сообщение от модератора: </span>' + offer.moderationMessage.message + '</div>')
            }
        }
    }

    function drawLoadedPhones(phonesArr) {
        for (var i = 0; i < phonesArr.length; i++) {
            if (i < 3) {
                if (i) addTelephone();
                $('.row-telephone:last').find('input').val(phonesArr[i]);
            } else {
                break;
            }
        }
    }

    function drawLoadedImages() {
        for (var id in imgsArr) {
            if (imgsArr[id] === "image" || imgsArr[id] === "pic1") {
                appendImg(id);
            }
        }
    }

    function drawLoadedAddress(address) {
        placeKey = (address.coordinates) ? address.coordinates : placeKey;

        if (address.area) $('#text-region').text(address.area);
        $.when(loadCities).done(function () {
            if (address.area && address.area !== 'Вся Украина') {
                if (address.city) $('#text-city').text(address.city);
                drawCities(address.area);
            }
        });
    }

    function drawLoadedCategories(offer) {
        var numberCategories = categories.length,
            copyCategories = categories.slice();

        if (numberCategories > 0) {
            $('#category-' + copyCategories[0]).parent().click();
            if (numberCategories > 1) {
                $('#category-' + copyCategories[1]).parent().click();
                if (numberCategories > 2) {
                    $('#category-' + copyCategories[2]).parent().click();
                }
            }
            for (var i = 0; i < offer.properties.length; i++) {
                var curProperty = offer.properties[i];
                $('[name="' + curProperty.key + '"]').val(curProperty.value);
            }
            if ($('#selection-price').val() === 'price') {
                $('#selection-currency').parent().css('display', 'inline-block');
                $('#offer-inpPrice').parent().css('display', 'inline-block');
            }
        }
    }

    // ---------------    INIT    --------------------------//

    // ---------------    DROP ZONE    --------------------------//

    function initDropZone() {
        var dropZone = document.getElementById('drop_zone');
        dropZone.addEventListener('dragover', handleDragOver, false);
        dropZone.addEventListener('drop', handleFileSelect, false);
        dropZone.addEventListener('dragleave', handleDragLeave, false);
    }

    function handleFileSelect(evt) {
        evt.stopPropagation();
        evt.preventDefault();

        this.classList.remove("good");

        var files = evt.dataTransfer.files; // FileList object.
        uploadImages(files);
    }

    function handleDragOver(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
        this.classList.add("good");
    }

    function handleDragLeave(evt) {
        this.classList.remove("good");
    }

    // ---------------    DROP ZONE    --------------------------//

    // -------------------------- IMAGES ------------------------------//
    function initImages() {
        $('#btn-offer-addImg').click(function () {
            $('#photoInput').trigger('click');
        });

        $('#photoInput').change(function (event) {
            event.preventDefault();
            var files = event.currentTarget.files;
            uploadImages(files);
        });
    }

    function uploadImages(files) {
        for (var i = 0, f; f = files[i]; i++) {
            var fd = new FormData();
            fd.append('file', f);
            $.ajax({
                type: "POST",
                url: "/api/rest/fileStorage/OFFERS/file/upload/",
                data: fd,
                async: false,
                cache: false,
                contentType: false,
                processData: false,

                success: function (data, textStatus, request) {
                    if (Object.keys(imgsArr).length <= 15) {
                        var id = data.id;
                        var isImage = f.type.substring(0, 5) === 'image';
                        if (isImage) {
                            imgsArr[id] = "image";
                            appendImg(id);
                        }
                    }
                }
            });
        }
    }

    function appendImg(id) {
        $(".li-defaultIMG").css("display", "none");
        var cloneImg = $(".li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
        cloneImg.find('img')
            .attr("alt", "")
            .attr("src", '/api/rest/fileStorage/OFFERS/file/read/id/' + id)
            .attr("id", id)
            .click(onClickSetMainImg);
        cloneImg.find('span')
            .click((isEditorPage) ? function () {
                deleteImgFromPage(id)
            } : deleteImgById);
        if (imgsArr[id] === "pic1") cloneImg.find('img').addClass('mainImg');
        cloneImg.appendTo('.ul-img-container');
    }


    function deleteImgById() {
        var idImg = $(event.currentTarget).parent()
            .find('img')
            .attr('id');
        delete imgsArr[idImg];
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/OFFERS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                deleteImgFromPage(idImg);
            }
        });
    }

    function deleteImgFromPage(idImg) {
        $('#' + idImg).parent().remove();

        var numberImg = $(".ul-img-container").find('img').length;
        if (numberImg < 2) {
            $(".li-defaultIMG").css("display", "inline-block");
        }
        if (isEditorPage) picArrDel.push(idImg);
    }

    function deleteImgFromDB(arr) {
        return $.ajax({
            url: '/api/rest/fileStorage/OFFERS/file/delete',
            method: 'POST',
            data: {'fileId': arr},
            traditional: true
        });
    }

    function onClickSetMainImg() {
        var img = $(event.currentTarget),
            id = img.attr("id"),
            isMain = img.hasClass("mainImg"),
            allImgs = $(".ul-img-container").find("img");

        for (var i = 0; i < allImgs.length; i++) {
            var curImg = $(allImgs[i]);
            if (curImg.hasClass("mainImg")) {
                curImg.removeClass("mainImg");
            }
        }
        if (!isMain) img.addClass("mainImg");

        for (var key in imgsArr) {
            if (imgsArr[key] === "pic1") {
                imgsArr[key] = "image";
            }
        }

        if (img.hasClass("mainImg")) {
            imgsArr[id] = "pic1";
        }
    }


    function checkMainImg() {
        var hasMainImg = false;

        for (var key in imgsArr) {
            if (imgsArr[key] === 'pic1') {
                hasMainImg = true;
                break;
            }
        }

        if (!hasMainImg) {
            for (var key in imgsArr) {
                imgsArr[key] = 'pic1';
                break;
            }
        }
    }

    function prepareImagesForUpdate() {
        for (var key in imgsArr) {
            if (picArrDel.indexOf(key) === -1) picArrNew.push(key);
        }
        for (var i = 0; i < picArrNew.length; i++) {
            imgsArrResult[picArrNew[i]] = imgsArr[picArrNew[i]];
        }
        for (var i = 0; i < picArrDel.length; i++) {
            deleteImgFromDB(picArrDel[i]);
        }

        imgsArr = {};
        $.extend(true, imgsArr, imgsArrResult);
    }

    // -------------------------- IMAGES ------------------------------//

    //--------------------------- REGIONS LIST --------------------------------------------//

    function selectRegion(event) {
        event.preventDefault();

        var region = $(event.currentTarget).children('a').text();

        $('#text-region').text(region);
        $('#city-container').find('li').remove();
        $('#text-city').text('Выберите город');

        if (region === 'Вся Украина') {
            $('#city-container').css('display', 'none');
        } else {
            drawCities(region);
        }
    }

    function drawCities(area) {
        var citiesArr = cities[area],
            numInColumn = citiesArr.length / 2 + (citiesArr.length % 2),
            parentBlock = $('#city-container').find('.multi-column-dropdown').first();

        var li = $('<li><a href="#" style="font-weight: bold">Все города</a></li>').click(selectCity);
        parentBlock.append(li);

        for (var i = 0; i < citiesArr.length; i++) {
            parentBlock = (i + 2 <= numInColumn) ? $('#city-container').find('.multi-column-dropdown').first() : $('#city-container').find('.multi-column-dropdown').last();
            li = $('<li><a href="#">' + citiesArr[i] + '</a></li>').click(selectCity);
            parentBlock.append(li);
        }

        $('#city-container').css('display', 'inline-block');
    }

    function selectCity(event) {
        event.preventDefault();
        var city = $(event.currentTarget).children('a').text();
        $('#text-city').text(city);
    }

    //--------------------------- REGIONS LIST --------------------------------------------//

    //--------------------------------- DRAW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

    function selectPrice(event) {
        var selectVal = $(event.currentTarget).val(),
            display = (selectVal === 'price') ? 'inline-block' : 'none';

        $('#selection-currency').parent().css('display', display);
        $('#offer-inpPrice').parent().css('display', display);
    }

    function drawOptions(id) {
        appendSelectParameters(id);
        appendInputParameters(id);

        if ($('#other-options').children().length) $('#offer-options-row').css('display', 'block');
    }

    function appendInputParameters(id) {
        for (var j in parameters) {
            if (parameters[j]['parameter']['type'] === "input" && parameters[j]['categories'][id] && parameters[j]['parameter']['key'] !== 'price') {

                var inpWrapper = $('<div style="display: inline-block; margin-bottom: 5px; margin-right: 5px;"></div>');
                var inp = $('<input id="' + parameters[j]['parameter']['key'] + '" type="text"  name="' + parameters[j]['parameter']['key'] + '" placeholder="' + parameters[j]['parameter']['label'] + '"/>');
                inp.appendTo(inpWrapper);
                $('#other-options').append(inpWrapper);
            }
        }
    }

    function appendSelectParameters(id) {
        for (var i in options) {
            if (options[i]['c'][id]) {
                var name = "";
                for (var j in options[i]['k']) {
                    name = j;
                }

                drawSelectElements(i, name);
                drawOptionsInSelectElements(i, name);

                if (name === 'price') $('#offer-price-row').css('display', 'block');
            }
        }
    }

    function drawOptionsInSelectElements(i, name) {
        for (var j in options[i]['v']) {
            var option = $('<option value = "' + j + '"  id ="' + j + '">' + options[i]['v'][j] + '</option>');
            if (name === 'price') {
                $('#selection-price').append(option);
            } else {
                $('#00' + i).append(option);
            }
        }
    }

    function drawSelectElements(i, name) {
        for (var j in parameters) {
            if (name !== 'price') {
                var selectWrapper = $('<div style="display: inline-block; margin-bottom: 5px; margin-right: 5px;"></div>');
                var select = $('<select class="prop" name="' + name + '" id="00' + i + '">' + '</select>');
                select.appendTo(selectWrapper);
                if (parameters[j]['parameter']['key'] === name && parameters[j]['parameter']['validators']['required'] === 1) {
                    select.prop("required", true);
                }
                $('#other-options').append(selectWrapper);
                break;
            }
        }
    }

//---------------------------- END DRAW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

    //--------------------------------BEGIN CATEGORY-------------------------------------------------//

    function initCategories() {
        $.when(loadCategories, loadSubcategories, loadOptions, loadParameters).done(function () {
            for (var i in jsonCategory) {
                var li = $('<li><a id="category-' + jsonCategory[i].id + '" href="#">' + jsonCategory[i].name + '</a></li>')
                    .click(selectCategoryLvl1);
                $('#ul-category1').append(li);
            }
        })
    }

    function selectCategoryLvl1(event) {
        event.preventDefault();

        $('#ul-category2, #ul-category3').html("");
        categories.splice(0, 3);

        $('#text-category2, #text-category3').text("Выберите подкатегорию");
        $('#category3-container').attr("style", "display: none");
        $('#category2-container').attr("style", "display: inline-block");

        var a1 = $(event.currentTarget).children('a'),
            category1Id = a1.attr("id");
        category1Id = category1Id.substring(category1Id.indexOf("-") + 1);
        categories.push(category1Id);
        $('#text-category1').text(a1.text());

        drawCategoryLvl2(category1Id);
        cleanCategoryParameters();
        if (!$('#ul-category2 li').length) {
            drawOptions(category1Id);
            $('#category2-container').attr("style", "display: none");
            $('#selection-price').change();
        }
    }

    function drawCategoryLvl2(category1Id) {
        var childArr = jsonCategory.filter(function (obj) {
                return obj.id === +category1Id; // Filter out the appropriate one
            }),
            child1 = (childArr[0]) ? childArr[0].children : {};
        for (var key in child1) {
            var li = $('<li><a id="category-' + child1[key].id + '" href="#">' + child1[key].name + '</a></li>')
                .click(selectCategoryLvl2);
            $('#ul-category2').append(li);
        }
    }

    function selectCategoryLvl2(event) {
        event.preventDefault();

        $('#ul-category3').html("");
        $('#text-category3').text("Выберите подкатегорию");
        $('#category3-container').attr("style", "display: inline-block");
        categories.splice(1, 2);

        var a2 = $(event.currentTarget).children('a'),
            category2Id = a2.attr("id");
        category2Id = category2Id.substring(category2Id.indexOf("-") + 1);
        categories.push(category2Id);
        $('#text-category2').text(a2.text());

        drawCategoryLvl3(category2Id);
        cleanCategoryParameters();

        if (!$('#ul-category3 li').length) {
            drawOptions(category2Id);
            $('#category3-container').attr("style", "display: none");
            $('#selection-price').change();
        }
    }

    function drawCategoryLvl3(category2Id) {
        var child2 = {};
        if (jsonSubcategory[category2Id]) {
            child2 = jsonSubcategory[category2Id].children;
            for (var key in child2) {
                var li = $('<li><a id="category-' + key + '" href="#">' + child2[key].label + '</a></li>')
                    .click(selectCategoryLvl3);
                $('#ul-category3').append(li);
            }
        }
    }

    function selectCategoryLvl3(event) {
        event.preventDefault();

        var a3 = $(event.currentTarget).children('a'),
            category3Id = a3.attr("id");
        category3Id = category3Id.substring(category3Id.indexOf("-") + 1);
        categories.push(category3Id);
        $('#text-category3').text(a3.text());

        cleanCategoryParameters();
        drawOptions(category3Id);
        $('#selection-price').change();
    }

    function getLastCategory() {
        var lastCategory = null;

        var blocks = document.querySelectorAll('#categories-row div');
        for (var i = 0; i < blocks.length; i++) {
            if (blocks[i].style.display !== 'none') lastCategory = blocks[i].querySelector('[id^="text-category"]').textContent;
        }

        return lastCategory;
    }

    function cleanCategoryParameters() {
        $('#offer-price-row').css('display', 'none');
        $('#offer-options-row').css('display', 'none');
        $('#selection-price').empty();
        $('#other-options').empty();
    }

//--------------------------------END CATEGORY-------------------------------------------------//

    //--------------------------- GOOGLE MAP API ---------------------------------------//

    function initMap() {

        var input = document.getElementById('address');

        var options = {
            types: []
        };

        var autocomplete = new google.maps.places.Autocomplete(input, options);

        google.maps.event.addListener(autocomplete, 'place_changed', function () {
            var place = autocomplete.getPlace(); //получаем место
            //console.log(place);
            //console.log(place.name);  //название места
            //console.log(place.id);  //уникальный идентификатор места
        });

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 10,
            center: {lat: 50.4501, lng: 30.523400000000038}
        });

        var geocoder = new google.maps.Geocoder();
        var infowindow = new google.maps.InfoWindow();

        document.getElementById('btn-save-adress').addEventListener('click', function () {
            geocodeAddress(geocoder, map, infowindow);
        });

        if (xhr) $.when(xhr).done(function () {
            geocodePlaceId(geocoder, map, infowindow)
        });
    }

    function geocodeAddress(geocoder, resultsMap, infowindow) {
        var address = document.getElementById('address').value;
        geocoder.geocode({'address': address}, function (results, status) {
            placeKey = results[0].place_id;
            lat = results[0].geometry.location.lat();
            lng = results[0].geometry.location.lng();
            if (status === google.maps.GeocoderStatus.OK) {
                resultsMap.setCenter(results[0].geometry.location);
                resultsMap.fitBounds(results[0].geometry.viewport);
                var marker = new google.maps.Marker({
                    map: resultsMap,
                    position: results[0].geometry.location
                });
                infowindow.setContent(results[0].formatted_address);
                infowindow.open(map, marker);
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }

    function geocodePlaceId(geocoder, map, infowindow) {
        if (placeKey) geocoder.geocode({'placeId': placeKey}, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    map.setZoom(11);
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location
                    });
                    infowindow.setContent(results[0].formatted_address);
                    infowindow.open(map, marker);

                    document.getElementById('address').value = results[0].formatted_address;
                } else {
                    window.alert('No results found');
                }
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    }

//--------------------------- END GOOGLE MAP API ---------------------------------------//

//----------------------------- PHONES LIST ----------------------------------------------//

    function addTelephone() {
        var curNumber = $('.row-telephone').length;
        if (curNumber < 3) {
            var imgDel = $('<a href="#" class="remove_field"><img src="/resources/img/minus.png" style="width: 20px; height: 20px; margin-left: 0px;"></a>').click(deleteTel);
            var row = $('.row-telephone').first().clone();
            row.children('#btn-add-tel, div.tooltip').remove();
            row.find('label').parent().remove();
            var inputBlock = row.find('input').val("").parent().addClass('col-xs-offset-4');
            imgDel.insertAfter(inputBlock);
            row.appendTo('.new-adv-box');
        }
    }

    function deleteTel(event) {
        event.preventDefault();
        $(event.currentTarget).parent().remove();
    }

//----------------------------- END PHONES LIST ----------------------------------------------//

// --------------------- OFFER ----------------------//

    function Offer() {
    }

    Offer.prototype.sendCreatedOffer = sendCreatedOffer;
    Offer.prototype.setOfferAddress = setOfferAddress;
    Offer.prototype.setOfferUserInfo = setOfferUserInfo;
    Offer.prototype.setOfferProperties = setOfferProperties;
    Offer.prototype.setOfferPrice = setOfferPrice;
    Offer.prototype.setOfferCategories = setOfferCategories;
    Offer.prototype.setOfferOtherFields = setOfferOtherFields;
    Offer.prototype.setOfferId = setOfferId;
    Offer.prototype.setModeratorMessage = setModeratorMessage;
    Offer.prototype.sendUpdatedOffer = sendUpdatedOffer;

    function createOffer(event) {
        checkMainImg();

        var offer = new Offer()
            .setOfferAddress()
            .setOfferUserInfo()
            .setOfferProperties()
            .setOfferPrice()
            .setOfferCategories()
            .setOfferOtherFields();

        gupValidator.validate(offer);
        if (!gupValidator.isValid) return;

        offer.sendCreatedOffer();
    }

    function updateOffer(event) {
        prepareImagesForUpdate();
        checkMainImg();

        var offer = new Offer()
            .setOfferId()
            .setOfferAddress()
            .setOfferUserInfo()
            .setOfferProperties()
            .setOfferPrice()
            .setOfferCategories()
            .setModeratorMessage()
            .setOfferOtherFields();

        gupValidator.validate(offer);
        if (!gupValidator.isValid) return;

        offer.sendUpdatedOffer();
    }

    function sendCreatedOffer() {
        var data = JSON.stringify(this);
        return $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: data,
            statusCode: {
                201: function (response) {
                    window.location.href = '/obyavlenie/' + response.id;
                }
            }
        });
    }

    function readOfferData() {
        return $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/id/" + offerId + "/read",
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        });
    }

    function sendUpdatedOffer() {
        var data = JSON.stringify(this);
        return $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/edit",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: data,
            statusCode: {
                200: function (response) {
                    window.location.href = '/obyavlenie/' + response.id;
                }
            }
        });
    }

    function deleteOffer() {
        return $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/id/" + offerId + "/delete",
            statusCode: {
                204: function () {
                    window.location.href = '/offers';
                }
            }
        });
    }

    function setModeratorMessage() {
        this.moderationMessage = {};
        if (isUserAdmin(loggedInProfile)) {
            if ($('#moderator-message').val() !== '') {
                this.moderationMessage.message = $('#moderator-message').val();
            }
        } else {
            this.moderationMessage.isRead = true;
        }
        return this;
    }

    function setOfferAddress() {
        var city = $('#text-city').text(),
            area = $('#text-region').text();

        this.address = {};
        this.address.coordinates = placeKey;
        this.address.lat = lat;
        this.address.lng = lng;
        this.address.country = 'Украина';

        if (city !== 'Выберите город' && city !== 'Все города') this.address.city = city;
        if (area !== 'Выберите область') this.address.area = area;

        return this;
    }

    function setOfferId() {
        this.id = offerId;
        return this;
    }

    function setOfferUserInfo() {
        var phones = [];
        $.each($('.row-telephone').find('input'), function (index) {
            var val = $(this).val();
            if (val) phones.push(val);
        });

        this.userInfo = {};
        this.userInfo.skypeLogin = $('#inpSkype').val();
        this.userInfo.contactName = $('#inpAuthor').val();
        this.userInfo.email = $('#inpEmail').val();
        this.userInfo.phoneNumbers = phones;

        return this;
    }

    function setOfferProperties() {
        var self = this;
        if (!this.properties) this.properties = [];

        $('#other-options').find('select, input').each(function () {
            var prop = {};
            prop.key = this.name;
            prop.value = this.value;
            self.properties.push(prop);
        });

        return this;
    }

    function setOfferPrice() {
        if (!this.properties) this.properties = [];

        if ($('#offer-price-row').css('display') !== 'none') {
            var priceType = $('#selection-price').val();
            this.properties.push({
                key: 'price',
                value: priceType
            });
            if (priceType === 'price') {
                this.currency = $('#selection-currency').val();
                this.price = $('#offer-inpPrice').val();
            }
        }

        return this;
    }

    function setOfferCategories() {
        if (categories.length) this.categories = categories;
        this.seoCategory = getLastCategory();

        return this;
    }

    function setOfferOtherFields() {
        this.title = $("#new-label-1").val();
        this.imagesIds = imgsArr;
        this.canBeReserved = $("#reserve-checkbox").is(":checked");
        this.active = true;
        this.description = $('#new-label-3').val();
        this.videoUrl = $('#inpVideo').val();
        if ($('#new-label-check').is(':checked')) this.urgent = true;

        return this;
    }

// --------------------- OFFER ----------------------//

// ----------------------------- OTHER ----------------------------------------------//
    function countTextLength() {
        var counter = $("#p-textlength");
        var currentString = $("#new-label-3").val();
        counter.text("Количество символов: " + currentString.length);
        if (currentString.length <= 50) {  /*or whatever your number is*/
            counter.css("color", "red");
        } else {
            if (currentString.length > 500) {
                counter.css("color", "red");
            } else {
                counter.css("color", "green");
            }
        }
    }

// ----------------------------- OTHER ----------------------------------------------//

    namespace.initMap = initMap;

})(window.offerRedactor = window.offerRedactor || {});



