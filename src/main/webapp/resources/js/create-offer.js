if (typeof loggedInProfile == 'undefined') {
$('#offer-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания объявления вам необходимо зарегистрироваться</i></p></div>')
}else{

    var imgsArr = {};
    var placeKey = '';
    var category1Id = '';
    var category2Id = '';
    var category3Id = '';
    var isComplete = 0;

    var gupValidator = new window.GupValidator.Constructor('offer').init();

// ---------------    LOAD RESOURCES    --------------------------//
    $(document).ready(function () {
        // Setup the dnd listeners.
        var dropZone = document.getElementById('drop_zone');
        dropZone.addEventListener('dragover', handleDragOver, false);
        dropZone.addEventListener('drop', handleFileSelect, false);

        function handleFileSelect(evt) {
            evt.stopPropagation();
            evt.preventDefault();

            var files = evt.dataTransfer.files; // FileList object.

            // files is a FileList of File objects. List some properties.
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
                        if (Object.keys(imgsArr).length < 15) {
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

        function handleDragOver(evt) {
            evt.stopPropagation();
            evt.preventDefault();
            evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
        }

    });

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

    countTextLength();
    $("textarea").on('keyup', countTextLength);

// --------------------- MAIN FORM CONSTRUCTION ----------------------//

    $('#btn-offer-save').click(function () {

        checkMainImg();

        var offer = {};
        offer.title = $("#new-label-1").val();
        offer.imagesIds = imgsArr;
        offer.canBeReserved = $("#reserve-checkbox").is(":checked");
        offer.address = {};
        offer.address.coordinates = placeKey;
        offer.address.country = 'Украина';


        var city = $('#text-city').text();
        if (city !== 'Выберите город' && city !== 'Все города') {
            offer.address.city = city;
        }

        var area = $('#text-region').text();
        if (area !== 'Выберите область') {
            offer.address.area = area;
        }

        if ($('#new-label-check').is(':checked')) {
            offer.urgent = true;
        }

        var phones = [];
        $.each($('.row-telephone').find('input'), function (index) {
            var val = $(this).val();
            if (val) phones.push(val);
        });

        var categoryResult = [];
        if (category1Id !== '') {
            categoryResult.push(category1Id)
        }
        if (category2Id !== '') {
            categoryResult.push(category2Id)
        }
        if (category3Id !== '') {
            categoryResult.push(category3Id)
        }

        offer.categories = categoryResult;
        offer.active = true;
        offer.description = $('#new-label-3').val();
        offer.userInfo = {};
        offer.userInfo.skypeLogin = $('#inpSkype').val();
        offer.userInfo.contactName = $('#inpAuthor').val();
        offer.userInfo.email = $('#inpEmail').val();
        offer.videoUrl = $('#inpVideo').val();
        offer.userInfo.phoneNumbers = phones;

        var properties = [];
        $('#other-options').find('select').each(function () {
            var prop = {};
            prop.key = this.name;
            prop.value = this.value;
            properties.push(prop);
        });

        $('#other-options').find('input').each(function () {
            var prop = {};
            prop.key = this.name;
            prop.value = this.value;
            properties.push(prop);
        });

        if ($('#offer-price-row').css('display') !== 'none') {
            properties.push({
                key: 'price',
                value: $('#selection-price').val()
            });
            offer.currency = $('#selection-currency').val();
            offer.price = $('#offer-inpPrice').val();
        }

        offer.properties = properties;

        gupValidator.validate(offer);
        if(!gupValidator.isValid) return;

        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(offer),
            success: function (response) {
                window.location.href = '/offer/' + response.id;
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    });

// --------------------- END MAIN FORM CONSTRUCTION ----------------------//


// -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//
    $('#btn-offer-addImg').click(function () {
        $('#photoInput').trigger('click');
    });

    $('#photoInput').change(function (event) {
        event.preventDefault();

        var files = event.currentTarget.files;
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
    });

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
            .click(deleteImg);
        cloneImg.appendTo('.ul-img-container');
    }


    function deleteImg() {
        var idImg = $(event.currentTarget).parent()
            .find('img')
            .attr('id');
        delete imgsArr[idImg];
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/OFFERS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                $('#' + idImg).parent().remove();

                var numberImg = $(".ul-img-container").find('img').length;
                if (numberImg < 2) {
                    $(".li-defaultIMG").css("display", "inline-block");
                }
            }
        });
    }

    function onClickSetMainImg() {
        var img = $(event.currentTarget);
        var id = img.attr("id");
        var isMain = img.hasClass("mainImg");
        var allImgs = $(".ul-img-container").find("img");
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
// -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


//--------------------------- GOOGLE MAP API ---------------------------------------//

    function initMap() {

        var input = document.getElementById('address');

        var options = {
            types: []
        };

        var autocomplete = new google.maps.places.Autocomplete(input, options);

        google.maps.event.addListener(autocomplete, 'place_changed', function () {
            var place = autocomplete.getPlace(); //получаем место
            console.log(place);
            console.log(place.name);  //название места
            console.log(place.id);  //уникальный идентификатор места
        });

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 17,
            center: {lat: 50.4501, lng: 30.523400000000038}
        });

        var geocoder = new google.maps.Geocoder();
        var infowindow = new google.maps.InfoWindow();

        document.getElementById('btn-save-adress').addEventListener('click', function () {
            geocodeAddress(geocoder, map);
        });
    }

    function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        geocoder.geocode({'address': address}, function (results, status) {
            placeKey = results[0].place_id;
            if (status === google.maps.GeocoderStatus.OK) {
                resultsMap.setCenter(results[0].geometry.location);
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

//--------------------------- END GOOGLE MAP API ---------------------------------------//

//--------------------------- REGIONS LIST --------------------------------------------//

    $('#region-container').find('li').click(selectRegion);

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
        var citiesArr = cities[area];

        var parentBlock = $('#city-container').find('.multi-column-dropdown').first();
        var li = $('<li><a href="#" style="font-weight: bold">Все города</a></li>').click(selectCity);
        parentBlock.append(li);

        var numInColumn = citiesArr.length / 2 + (citiesArr.length % 2);
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

//--------------------------- END REGIONS LIST --------------------------------------------//

//----------------------------- PHONES LIST ----------------------------------------------//


    function addTelephone() {
        var curNumber = $('.row-telephone').length;
        if (curNumber < 3) {
            var imgDel = $('<a href="#" class="remove_field"><img src="/resources/img/minus.png" style="width: 20px; height: 20px; margin-left: 0px;"></a>').click(deleteTel);
            var row = $('.row-telephone').first().clone();
            row.children('#btn-add-tel').remove();
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

//--------------------------------BEGIN CATEGORY-------------------------------------------------//
    $.when(window.loadCategories).done(function(){
        for (var i in jsonCategory) {
            var li = $('<li><a id="category-' + jsonCategory[i].id + '" href="#">' + jsonCategory[i].name + '</a></li>')
                .click(selectCategoryLvl1);
            $('#ul-category1').append(li);
        }
    })

    function selectCategoryLvl1(event) {
        event.preventDefault();

        $('#ul-category2').html("");
        $('#ul-category3').html("");
        isComplete = 0;
        category2Id = '';
        category3Id = '';

        $('#text-category2').text("Выберите подкатегорию");
        $('#text-category3').text("Выберите подкатегорию");
        $('#category3-container').attr("style", "display: none");
        $('#category2-container').attr("style", "display: inline-block");

        var a1 = $(event.currentTarget).children('a');
        category1Id = a1.attr("id");
        category1Id = category1Id.substring(category1Id.indexOf("-") + 1);
        $('#text-category1').text(a1.text());

        var child1 = {};
        var childArr = jsonCategory.filter(function (obj) {
            return obj.id === +category1Id; // Filter out the appropriate one
        });
        if (childArr[0]) {
            child1 = childArr[0].children;

            for (var key in child1) {
                var li = $('<li><a id="category-' + child1[key].id + '" href="#">' + child1[key].name + '</a></li>')
                    .click(selectCategoryLvl2);
                $('#ul-category2').append(li);
            }
        }
        if (Object.keys(child1).length) {
            erase();
        } else {
            isComplete = 1;
            drawOptions(category1Id);
            $('#category2-container').attr("style", "display: none");
            $('#selection-price').change();
        }
    }

    function selectCategoryLvl2(event) {
        event.preventDefault();

        $('#ul-category3').html("");
        $('#text-category3').text("Выберите подкатегорию");
        $('#category3-container').attr("style", "display: inline-block");
        isComplete = 0;
        category3Id = '';

        var a2 = $(event.currentTarget).children('a');
        category2Id = a2.attr("id");
        category2Id = category2Id.substring(category2Id.indexOf("-") + 1);
        $('#text-category2').text(a2.text());

        var child2 = {};
        if (jsonSubcategory[category2Id]) {
            child2 = jsonSubcategory[category2Id].children;
            for (var key in child2) {
                var li = $('<li><a id="category-' + key + '" href="#">' + child2[key].label + '</a></li>')
                    .click(selectCategoryLvl3);
                $('#ul-category3').append(li);
            }
        }
        if (Object.keys(child2).length) {
            erase();
        } else {
            isComplete = 1;
            drawOptions(category2Id);
            $('#category3-container').attr("style", "display: none");
            $('#selection-price').change();
        }
    }

    function selectCategoryLvl3(event) {
        event.preventDefault();

        isComplete = 1;
        var a3 = $(event.currentTarget).children('a');
        category3Id = a3.attr("id");
        category3Id = category3Id.substring(category3Id.indexOf("-") + 1);
        $('#text-category3').text(a3.text());
        erase();
        drawOptions(category3Id);
        $('#selection-price').change();
    }


//--------------------------------END CATEGORY-------------------------------------------------//

//--------------------------------- DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//
    $('#selection-price').change(selectPrice);

    function selectPrice(event) {
        var selectVal = $(event.currentTarget).val();
        if (selectVal === 'price') {
            $('#selection-currency').parent().css('display', 'inline-block');
            $('#offer-inpPrice').parent().css('display', 'inline-block');
        } else {
            $('#selection-currency').parent().css('display', 'none');
            $('#offer-inpPrice').parent().css('display', 'none');
        }
    }

    function drawOptions(id) {
        erase();

        for (var i in options) {
            if (options[i]['c'][id]) {
                var name = "";
                for (j in options[i]['k']) {
                    name = j;
                }

                for (j in parameters) {
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

                for (var j in options[i]['v']) {
                    var option = $('<option value = "' + j + '"  id ="' + j + '">' + options[i]['v'][j] + '</option>');
                    if (name === 'price') {
                        $('#selection-price').append(option);
                    } else {
                        $('#00' + i).append(option);
                    }
                }
                if (name === 'price') $('#offer-price-row').css('display', 'block');
            }
        }

        for (j in parameters) {
            if (parameters[j]['parameter']['type'] === "input" && parameters[j]['categories'][id] && parameters[j]['parameter']['key'] !== 'price') {

                var inpWrapper = $('<div style="display: inline-block; margin-bottom: 5px; margin-right: 5px;"></div>');
                var inp = $('<input id="' + parameters[j]['parameter']['key'] + '" type="text"  name="' + parameters[j]['parameter']['key'] + '" placeholder="' + parameters[j]['parameter']['label'] + '"/>');
                inp.appendTo(inpWrapper);
                $('#other-options').append(inpWrapper);
            }
        }
        if ($('#other-options').children().length) $('#offer-options-row').css('display', 'block');
    }

//---------------------------- END DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

//------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//
    function erase() {
        $('#offer-price-row').css('display', 'none');
        $('#offer-options-row').css('display', 'none');
        $('#selection-price').empty();
        $('#other-options').empty();
    }
//------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//
}



