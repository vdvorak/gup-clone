<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Создание новости | GUP</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/alster.css.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link href="/resources/css/com.css" rel="stylesheet">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

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
        <p class="blogCreationHeader blueColor">Новая новость</p>

        <form action="#" role="form">
            <label for="newsTitle" class="blogCreationLabel">Заголовок новости</label>
            <input type="text" name="newsTitle" id="newsTitle" class="blogCreationInput blueBorder">

            <div class="input-group cat">Категория
                <hr>
                    <input id="sciCategory" type="checkbox" name="sci"><label for="sciCategory">Наука и техника</label><br>
                    <input id="artCategory" type="checkbox" name="art"><label for="artCategory">Искусство</label><br>
                    <input id="savorCategory" type="checkbox" name="savor"><label for="savorCategory">Светская жизнь</label><br>
                    <input id="policyCategory" type="checkbox" name="policy"><label
                            for="policyCategory">Политика</label><br>
                    <input id="worldCategory" type="checkbox" name="world"><label for="worldCategory">Мир и общество</label><br>
                    <input id="economyCategory" type="checkbox" name="economy"><label
                            for="economyCategory">Экономика</label><br>
                    <input id="sportCategory" type="checkbox" name="sport"><label for="sportCategory">Спорт,хобби</label><br>
                    <input id="socialCategory" type="checkbox" name="social"><label for="socialCategory">Соц. сети</label>
                <hr>
            </div>


            <div class="clearfix"></div>
            <textarea id="newsCreationDescription" name="newsCreationDescription"
                      class="blogCreationDescription blueBorder"></textarea>


            <!-- city chosen -->
            <input id="countryInp" type="text" name="country" style="visibility: hidden;">
            <input id="areaInp" type="text" name="area" style="visibility: hidden;">
            <input id="cityInp" type="text" name="city" style="visibility: hidden;">

            <div class="row">
                <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
                    <div class="input-group">

                        <div class="col-xs-6" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="dropdown">
                                    <a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите
                                        область<b
                                                class="caret"></b></a>
                                    <ul class="dropdown-menu multi-column columns-2">
                                        <div id="regions" class="row">
                                            <div class="col-sm-6">
                                                <ul class="multi-column-dropdown">
                                                    <li><a role="menuitem" tabindex="-1" href="#"><b>Вся Украина</b></a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Винницкая область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Волынская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Донецкая область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Житомирская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Закарпатская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Ивано‑Франковская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Киевская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Кировоградская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Крым</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Луганская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Львовская область</a></li>
                                                </ul>
                                            </div>
                                            <div class="col-sm-6">
                                                <ul class="multi-column-dropdown">
                                                    <li><a role="menuitem" tabindex="-1" href="#">Николаевская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Одесская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Полтавская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Ровенская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Сумская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Тернопольская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Харьковская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Херсонская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Хмельницкая область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Черкасская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Черниговская область</a></li>
                                                    <li><a role="menuitem" tabindex="-1" href="#">Черновицкая область</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </ul>
                                </li>
                            </ul>
                        </div>

                        <div class="col-xs-6" id="bs-example-navbar-collapse-2" style="visibility: hidden">
                            <ul class="nav navbar-nav">
                                <li class="dropdown">
                                    <a id="chosenCity" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите
                                        город<b
                                                class="caret"></b></a>
                                    <ul class="dropdown-menu multi-column columns-2">
                                        <div id="cities" class="row">

                                            <div class="col-sm-6">
                                                <ul id="cities1" class="multi-column-dropdown">
                                                </ul>
                                            </div>

                                            <div class="col-sm-6">
                                                <ul id="cities2" class="multi-column-dropdown">
                                                </ul>
                                            </div>
                                        </div>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                <input id="photoInput" type="file" style="display: none;" multiple="multiple" accept="image/*">
            </form>

            <div class="titleFile" data-title="Добавить изображение"><button type="submit" class="blogCreationSubmit"></button></div>
            <label class="blogCreationLabel">Фотографии</label>

            <div id="drop_zone" class="defaultIMG">
                <ul>
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
            </div>

            <label for="blogTitle" class="blogCreationLabel">Добавить видео</label>
            <input type="text" name="blogTitle" id="blogTitle" class="blogCreationInput blueBorder"
                   placeholder="Youtube"
                   pattern="(?:https?:\/\/)?(?:www\.)?youtu\.?be(?:\.com)?\/?.*(?:watch|embed)?(?:.*v=|v\/|\/)([\w\-_]+)\&?">
        </form>
        <button type="button" class="SendEdition">Отправить редакции</button>

        <div class="clearfix"></div>

    </div>
</div>

<!-- script references -->
<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>


<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script>
    var imgsArr = {};
    var inpCategories = [];
    var cities;

    // ---------------    LOAD RESOURCES    --------------------------//
    $.ajax({
        type: "GET",
        url: "/resources/json/cities.json",
        dataType: 'json',
        async: false,
        success: function (response) {
            cities = response;
        }
    });
    // ---------------   END LOAD RESOURCES    --------------------------//


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
        ]
    });

    //---------------------- END  HTML EDITOR-------------------------------------//


    // --------------------- MAIN FORM CONSTRUCTION ----------------------//

    $(document).on('click', 'button.SendEdition', function (event) {
        event.preventDefault();

        var title = $('#newsTitle').val();
        var text = tinymce.activeEditor.getContent({format : 'raw'});

        if (title.length < 4 || title.length > 140) return;
        if (text.length < 50 || text.length > 5000) return;

        var blogPost = {};
        blogPost.blogId = '${blogId}';
        blogPost.title = title;
        blogPost.text = text;
        blogPost.address = {};
        blogPost.address.country = 'Украина';
        blogPost.address.area = $('#areaInp').val();
        blogPost.address.city = $('#cityInp').val();
        blogPost.imagesIds = imgsArr;
        blogPost.categories = [];

        $('.cat input').each(function (index) {
            if ($(this).prop('checked')) {
                inpCategories.push($(this).attr('name'));
            }
        });

        blogPost.categories = inpCategories;

        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blogPost),
            success: function (response) {
                window.location.href = '/blog-post/view/id/' + response.id;
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    });

    // --------------------- END MAIN FORM CONSTRUCTION ----------------------//


    // -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//
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
                    url: "/api/rest/fileStorage/NEWS/file/upload/",
                    data: fd,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,

                    success: function (data, textStatus, request) {
                        var id = data.id;
                        var isImage = f.type.substring(0, 5) === 'image';
                        if (isImage) {
                            imgsArr[id] = "image";
                            appendImg(id);

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

    $('.blogCreationSubmit').click(function(){
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
                url: "/api/rest/fileStorage/NEWS/file/upload/",
                data: fd,
                async: false,
                cache: false,
                contentType: false,
                processData: false,

                success: function (data, textStatus, request) {
                    var id = data.id;
                    var isImage = f.type.substring(0, 5) === 'image';
                    if (isImage) {
                        imgsArr[id] = "image";
                        appendImg(id);
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
                .attr("src", '/api/rest/fileStorage/NEWS/file/read/id/' + id)
                .attr("id", id);
        cloneImg.find('span')
                .click(deleteImg);
        cloneImg.appendTo('.defaultIMG ul');
    }

//    $('#photoInput').submit(function (event) {
//        event.preventDefault();
//        var formImg = new FormData($(this)[0]);
//
//        $.ajax({
//            type: "POST",
//            url: "/api/rest/fileStorage/NEWS/file/upload/",
//            data: formImg,
//            async: false,
//            cache: false,
//            contentType: false,
//            processData: false,
//
//            success: function (data, textStatus, request) {
//                var id = data.id;
//                imgsArr[id] = "someText";
//                $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none">' +
//                        ' <li style="background-color: white"><a rel="example_group"> ' +
//                        '<img id="img1" alt="" src="/api/rest/fileStorage/NEWS/file/read/id/' + id + '"' + 'width="150" height="150"> ' +
//                        '</a> <div onclick=\"deleteImg(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
//            }
//        });
//    });

    function deleteImg() {
        var idImg = $(event.currentTarget).parent()
                .find('img')
                .attr('id');
        delete imgsArr[idImg];
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                $('#' + idImg).parent().remove();

                var numberImg = $(".defaultIMG").find('img').length;
                if(numberImg < 2) {
                    $(".li-defaultIMG").css("display", "inline-block");
                }
            }
        });
    }

    // -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


    //--------------------------- REGIONS LIST --------------------------------------------//

    $('#regions').find('li').click(function (event) {
        event.preventDefault();

        var region = $(this).text();
        $('#chosenRegion').text(region);
        $('#areaInp').val(region);
        if (region !== 'Вся Украина') {
            $('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");
        } else {
            $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
        }
        $('#chosenCity').text("Выберите город");

        $('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();
        $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');
        for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {
            $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');
        }
        for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {
            $('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');
        }

        $('#cities').find('li').click(function (event) {
                    event.preventDefault();
                    var city = $(this).text();
                    $('#chosenCity').text(city);
                    $('#cityInp').val(city);
                }
        );
    });

    //--------------------------- END REGIONS LIST --------------------------------------------//
</script>
</body>
</html>