<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 05.11.2015
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Редактирование новости</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/confirmDeleteAlert.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/dropdown-multicolumn.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

</head>
<body>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
    <div class="blogCreation">
        <p class="blogCreationHeader blueColor">Редактирование новости</p>

        <div>
            <label for="newsTitle" class="blogCreationLabel">Заголовок новости</label>
            <input type="text" name="newsTitle" id="newsTitle" class="blogCreationInput blueBorder" value="${blogPost.title}">
            <div class="clearfix"></div>

            <p class="blogCreationHeader blueColor">Категория</p>
            <div class="input-group cat">
                <hr>
                <label class="labelLookGood" for="sciCategory"><input id="sciCategory" type="checkbox" name="sci"><span></span></label>
                <label class="LabelForLabel" for="sciCategory">Наука и техника</label>
                <label class="labelLookGood" for="artCategory"><input id="artCategory" type="checkbox" name="art"><span></span></label>
                <label class="LabelForLabel" for="artCategory">Искусство</label>
                <label class="labelLookGood" for="savorCategory"><input id="savorCategory" type="checkbox" name="savor"><span></span></label>
                <label class="LabelForLabel" for="savorCategory">Светская жизнь</label>
                <label class="labelLookGood" for="policyCategory"><input id="policyCategory" type="checkbox" name="policy"><span></span></label>
                <label class="LabelForLabel" for="policyCategory">Политика</label>
                <label class="labelLookGood" for="worldCategory"><input id="worldCategory" type="checkbox" name="world"><span></span></label>
                <label class="LabelForLabel" for="worldCategory">Мир и общество</label>
                <label class="labelLookGood" for="economyCategory"><input id="economyCategory" type="checkbox" name="economy"><span></span></label>
                <label class="LabelForLabel" for="economyCategory">Экономика</label>
                <label class="labelLookGood" for="sportCategory"><input id="sportCategory" type="checkbox" name="sport"><span></span></label>
                <label class="LabelForLabel" for="sportCategory">Спорт,хобби</label>
                <label class="labelLookGood" for="socialCategory"><input id="socialCategory" type="checkbox" name="social"><span></span></label>
                <label class="LabelForLabel" for="socialCategory">Соц. сети</label>
                <div class="clearfix"></div>
                <hr>
            </div>

            <label for="region-row" class="blogCreationLabel">Регион</label>
            <div id="blog-region-wrapper">
                <div id="region-row">
                    <div id="region-container" class="dropdown" style="display: inline-block">
                        <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span
                                id="text-region">Выберите область</span>
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu  multi-column columns-2">
                            <div class="row">
                                <div class="col-sm-6">
                                    <ul class="multi-column-dropdown">
                                        <li><a href="#" style="font-weight: bold">Вся Украина</a></li>
                                        <li><a href="#">Винницкая область</a></li>
                                        <li><a href="#">Волынская область</a></li>
                                        <li><a href="#">Донецкая область</a></li>
                                        <li><a href="#">Житомирская область</a></li>
                                        <li><a href="#">Закарпатская область</a></li>
                                        <li><a href="#">Ивано‑Франковская область</a></li>
                                        <li><a href="#">Киевская область</a></li>
                                        <li><a href="#">Кировоградская область</a></li>
                                        <li><a href="#">Крым</a></li>
                                        <li><a href="#">Луганская область</a></li>
                                        <li><a href="#">Львовская область</a></li>
                                    </ul>
                                </div>
                                <div class="col-sm-6">
                                    <ul class="multi-column-dropdown">
                                        <li><a href="#">Николаевская область</a></li>
                                        <li><a href="#">Одесская область</a></li>
                                        <li><a href="#">Полтавская область</a></li>
                                        <li><a href="#">Ровенская область</a></li>
                                        <li><a href="#">Сумская область</a></li>
                                        <li><a href="#">Тернопольская область</a></li>
                                        <li><a href="#">Харьковская область</a></li>
                                        <li><a href="#">Херсонская область</a></li>
                                        <li><a href="#">Хмельницкая область</a></li>
                                        <li><a href="#">Черкасская область</a></li>
                                        <li><a href="#">Черниговская область</a></li>
                                        <li><a href="#">Черновицкая область</a>
                                    </ul>
                                </div>
                            </div>
                        </ul>
                    </div>
                    <div id="city-container" class="dropdown" style="display: none">
                        <button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span
                                id="text-city">Выберите город</span>
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu  multi-column columns-2">
                            <div class="row">
                                <div class="col-sm-6">
                                    <ul class="multi-column-dropdown">

                                    </ul>
                                </div>
                                <div class="col-sm-6">
                                    <ul class="multi-column-dropdown">

                                    </ul>
                                </div>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="clearfix"></div>
            <textarea id="newsCreationDescription" name="newsCreationDescription"
                      class="blogCreationDescription blueBorder"></textarea>


            <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                <input id="photoInput" type="file" style="display: none;" multiple="multiple" accept="image/*">
            </form>

            <div class="titleFile" data-title="Добавить изображение">
                <button type="submit" class="blogCreationSubmit"></button>
            </div>
            <label class="blogCreationLabel">Фотографии</label>

            <div id="drop_zone">
                <ul id="blog-post-img-block" class="ul-img-container ul-img-container-green">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImg()"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
            </div>

            <label for="blogTitle" class="blogCreationLabel">Добавить видео</label>
            <input type="text" name="blogTitle" id="blogTitle" class="blogCreationInput blueBorder"
                   placeholder="Youtube"
                   pattern="(?:https?:\/\/)?(?:www\.)?youtu\.?be(?:\.com)?\/?.*(?:watch|embed)?(?:.*v=|v\/|\/)([\w\-_]+)\&?">
        </div>


        <div class="editor">
            <div class="field">
                <button id="deleteBpBtn" class="delete-btn">Удалить новость</button>
                <button id="sendBpToEdition" class="info-submit">Отправить редакции</button>
            </div>

            <div class="confirm" id="confirmBpDelete" style="display: none">
                <h1>Подтвердите удаление</h1>
                <p>Статья будет навсегда удалена</p>
                <button id="cancelBpDelBtn" autofocus>Отмена</button>
                <button id="confirmBpDelBtn">Удалить</button>
            </div>
        </div>
        <div class="clearfix"></div>

    </div>
</div>

<!-- script references -->
<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>


<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>

<script>

    var imgsArr = {};
    var cities;
    var inpCategories = [];
    var oldCategories = '${blogPost.categories}'.slice(1, -1).split(","); // make array from string
    var oldImgArr = {};
    var imgsArrResult = {};
    var picArrDel = [];
    var picArrNew = [];


    // ---------------    LOAD RESOURCES    --------------------------//
    var area = '${blogPost.address.area}';
    if (area) $('#text-region').text(area);

    $.ajax({
        type: "GET",
        url: "/resources/json/cities.json",
        dataType: 'json',
        async: false,
        success: function (response) {
            cities = response;

            var city = '${blogPost.address.city}';

            if (area && area !== 'Вся Украина') {
                if (city) $('#text-city').text(city);
                drawCities(area);
            }
        }
    });
    // ---------------   END LOAD RESOURCES    --------------------------//

    //----------------------  DROP ZONE -------------------------------------//
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
                url: "/api/rest/fileStorage/NEWS/file/upload/",
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
    //----------------------  DROP ZONE -------------------------------------//

    //----------------------  HTML EDITOR-------------------------------------//
    tinymce.init({
        selector: 'textarea',
        height: 300,
        theme: 'modern',
        plugins: [
            'advlist autolink lists link image charmap print preview hr anchor pagebreak',
            'searchreplace wordcount visualblocks visualchars code fullscreen',
            'insertdatetime media nonbreaking save table contextmenu directionality',
            'emoticons template paste textcolor colorpicker textpattern imagetools'
        ],
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        image_advtab: true,
        templates: [
            {title: 'Test template 1', content: 'Test 1'},
            {title: 'Test template 2', content: 'Test 2'}
        ],
        content_css: [
            '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
            '//www.tinymce.com/css/codepen.min.css'
        ],
        init_instance_callback : function(editor) {
            editor.setContent('${blogPost.text}');
        }
    });

    //---------------------- END  HTML EDITOR-------------------------------------//


    // ---------------    SET CATEGORIES    --------------------------//
    if(!oldCategories[0]) oldCategories = [];
    for (var i = 0; i < oldCategories.length; i++) {
        $('input[name=' + oldCategories[i] + ']').prop('checked', true);
    }

    // ---------------    END SET CATEGORIES  --------------------------//


    // --------------------- MAIN FORM CONSTRUCTION ----------------------//
    $('#sendBpToEdition').click(function (event) {
        event.preventDefault();

        var title = $('#newsTitle').val();
        var text = tinymce.activeEditor.getContent({format : 'raw'});

        if (title.length < 4 || title.length > 140) return;
        if (text.length < 50 || text.length > 5000) return;

        for(var key in imgsArr) {
            if(picArrDel.indexOf(key) === -1) picArrNew.push(key);
        }

        for (var i = 0; i < picArrNew.length; i++) {
            imgsArrResult[picArrNew[i]] = imgsArr[picArrNew[i]];
        }

        for(var i = 0; i < picArrDel.length; i++) {
            deleteImgFromDB(picArrDel[i]);
        }

        checkMainImg();
        
        var blogPost = {};
        blogPost.id = '${blogPost.id}';
        blogPost.blogId = '${blogPost.blogId}';
        blogPost.title = title;
        blogPost.text = text;
        blogPost.address = {};
        blogPost.address.country = 'Украина';
        var city = $('#text-city').text();
        if (city !== 'Выберите город' && city !== 'Все города') {
            blogPost.address.city = city;
        }

        var area = $('#text-region').text();
        if (area !== 'Выберите область') {
            blogPost.address.area = area;
        }
        blogPost.imagesIds = imgsArrResult;
        blogPost.categories = [];

        $('.cat input').each(function (index) {
            if ($(this).prop('checked')) {
                inpCategories.push($(this).attr('name'));
            }
        });

        blogPost.categories = inpCategories;
        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/edit",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blogPost),
            statusCode: {
                200: function (data, textStatus, request) {
                    window.location.href = '/blog-post/view/id/' + '${blogPost.id}';
                }
            }
        });
    });

    // --------------------- END MAIN FORM CONSTRUCTION ----------------------//

    // place photo from received model on the page
    if ('${blogPost.imagesIds}'.length > 5 ){
        oldImgArr = JSON.parse('${blogPost.imagesIds}'.replace('{', '{"').replace(/=/g, '":"').replace(/,/g, '","').replace('}', '"}').replace(/ /g, ''));
    }
    for (var key in oldImgArr) {
        imgsArr[key] = oldImgArr[key];
        appendImg(key);
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

    function appendImg(id) {
        $(".li-defaultIMG").css("display", "none");
        var cloneImg = $(".li-defaultIMG").clone()
                .removeClass('li-defaultIMG')
                .css("display", "inline-block");
        cloneImg.find('img')
                .attr("alt", "")
                .attr("src", '/api/rest/fileStorage/NEWS/file/read/id/' + id)
                .attr("id", id)
                .click(onClickSetMainImg);
        cloneImg.find('span')
                .click(deleteImg);

        if(imgsArr[key] === "pic1") cloneImg.find('img').addClass('mainImg');

        cloneImg.appendTo('.ul-img-container');
    }

    function checkMainImg() {
        var hasMainImg = false;

        for(var key in imgsArrResult) {
            if(imgsArrResult[key] === 'pic1') {
                hasMainImg = true;
                break;
            }
        }

        if(!hasMainImg) {
            for(var key in imgsArrResult) {
                imgsArrResult[key] = 'pic1';
                break;
            }
        }
    }
    // -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//
    $('#photoInput').change(function (event) {
        event.preventDefault();

        var files = event.currentTarget.files;
        for (var i = 0, f; f = files[i]; i++) {
            var fd = new FormData();
            fd.append('file', f);

        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/upload/",
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
    });

    $('#deleteBpBtn').click(function(){
        $("#confirmBpDelete").show();
    });


    $('#cancelBpDelBtn').on('click', function () {
        $("#confirmBpDelete").hide();
    });

    $('#confirmBpDelBtn').on('click', function () {
        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/id/" + '${blogPost.id}' + "/delete",
            statusCode: {
                204: function () {
                    window.location.href = '/news/list';
                }
            }
        });
    });

    $('button.blogCreationSubmit').click(function(){
        $('#photoInput').trigger('click');
    });

    function deleteImgFromDB(idImg) {
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
            }
        });
    }

    function deleteImg() {
        var idImg = $(event.currentTarget).parent()
                .find('img')
                .attr('id');
        $('#' + idImg).parent().remove();

        var numberImg = $(".ul-img-container").find('img').length;
        if(numberImg < 2) {
            $(".li-defaultIMG").css("display", "inline-block");
        }

        picArrDel.push(idImg);
    }
    // -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


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

</script>

</body>
</html>