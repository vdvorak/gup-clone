<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 13.01.2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание блога</title>
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <link rel="stylesheet" href="resources/css/media-queries.css">

    <%-- Cropper style --%>
    <link href="/resources/css/cropper.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/gup-custom-modal-window.css">

    <link href="/resources/css/cropper.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/gup-custom-modal-window.css">
    <link rel="stylesheet" href="/resources/css/mini.css">

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
        <p class="blogCreationHeader">Новый новостной блог</p>

        <form id="blogCreationForm" action="#" role="form">
            <label for="blogTitle" class="blogCreationLabel">Заголовок блога <em>*</em></label>
            <input type="text" name="blogTitle" id="blogTitle" class="blogCreationInput"
                   placeholder="Длина заголовка от 2 до 70 символов">

            <div class="clearfix"></div>

            <label for="blogCreationDescription" class="blogCreationLabel">Описание <em>*</em></label>
            <textarea name="blogCreationDescription" id="blogCreationDescription"
                      class="blogCreationDescription" placeholder="Длина описания от 50 до 5000 символов"></textarea>

            <div class="group-info">
                <label for="blogCreationSocial" class="blogCreationLabel">Социальные сети</label>
                <input type="text" id="blogCreationSocial" class="blogCreationSocial"
                       placeholder="Добавить ссылку на Facebook" name="FACEBOOK">

                <div class="socialIconBlog">
                    <a href="#"><img class="img-responsive" src="resources/images/twitter-info.png" alt="TWITTER"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/facebook-info.png" alt="FACEBOOK"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/skype-info.png" alt="SKYPE"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/vk-info.png" alt="VKONTAKTE"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/g+info.png" alt="GOOGLEPLUS"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/in-info.png" alt="LINKEDIN"></a>
                </div>
                <div class="blog-social-container" style="display: none;"><input type="text" class="blog-social-input"
                                                                                 placeholder="Добавить ссылку на Facebook"
                                                                                 name="FACEBOOK"
                        ><img src="/resources/img/minus.png" class="blog-btn-removeSocial"></div>
            </div>

            <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                <input id="photoInput" type="file" style="display: none;" multiple="multiple" accept="image/*">
            </form>
            <div class="drop_zone">
                <div class="blog-img">
                    <ul>
                        <li class="li-defaultIMG">
                            <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImgFromDB()"></i></span>
                            <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                        </li>
                    </ul>
                </div>
            </div>
            <div class="titleFile" data-title="Добавить изображение">
                <button type="submit" class="blogCreationSubmit"></button>
            </div>
            <label class="blogCreationLabel">Фотографии</label>
        </form>


        <button type="button" class="SendEdition">Отправить редакции</button>

        <div class="clearfix"></div>
    </div>
</div>

<!-- The Modal -->
<div id="cropperModal" class="cropper-modal">

    <!-- Modal content -->
    <div class="cropper-modal-content">
        <div class="cropper-modal-header">
            <span>Редактирование фотографии</span>
        </div>
        <div class="cropper-modal-body drop_zone">
            <img id="cropper-image" src="/resources/images/no_photo.jpg" style="max-width: 100%">
        </div>
        <div class="cropper-modal-footer">
            <button class="cropper-btn cropper-btn-success">Готово</button>
            <button class="cropper-btn cropper-btn-cancel">Отмена</button>

        </div>
    </div>

</div>

<%--<div>--%>
<%--<input id="blogTitle" type="text" name="blogTitle" minlength="2" maxlength="70" required--%>
<%--placeholder="Название блога">--%>
<%--</div>--%>

<%--<div>--%>
<%--<textarea id="blogDescription" minlength="50" maxlength="5000" required--%>
<%--placeholder="Описание блога"></textarea>--%>

<%--<div id="textLength"></div>--%>
<%--</div>--%>

<%--<div id="drop_zone">--%>
<%--<form id="photoInput" enctype="multipart/form-data" method="post">--%>
<%--<input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">--%>
<%--</form>--%>
<%--Перетяните файлы сюда--%>
<%--<div class="imgBlock">--%>
<%--<img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">--%>
<%--</div>--%>

<%--</div>--%>

<%--<div class="input-group">--%>
<%--<div id="socLinkGroup" class="input_soc_wrap">--%>
<%--<div class="left-tag">--%>
<%--<p>Социальные сети</p>--%>
<%--</div>--%>
<%--<div class="right-tag">--%>
<%--<a class="FACEBOOK"><img src="/resources/images/faceb-icon.png"></a>--%>
<%--<a class="TWITTER"><img src="/resources/images/twit-icon.png"> </a>--%>
<%--<a class="VKONTAKTE"><img src="/resources/images/vk-icon.png"></a>--%>
<%--<a class="GOOGLEPLUS"><img src="/resources/images/goo-icon.png"></a>--%>
<%--<a class="LINKEDIN"><img src="/resources/images/link-icon.png"></a>--%>

<%--<div><input class="input-social" type="url" name="FACEBOOK" pattern="http://www\.facebook\.com\/(.+)|https://www\.facebook\.com\/(.+)" placeholder="Страница FACEBOOK"><span class="remove_field"><img--%>
<%--src="/resources/img/minus.png" width="15" height="15"></span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<button id="createBlog" disabled>Создать</button>--%>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>
<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/cropper.js"></script>

<script>

    var imgId = '';
    var blog = {};

    // --------------------------------------  BEGIN cropper  ----------------------------------------------
    var image = document.getElementById('cropper-image');
    var cropper = new Cropper(image, {
        aspectRatio: 1 / 1,
        crop: function (data) {
            console.log(data.x);
            console.log(data.y);
            console.log(data.width);
            console.log(data.height);
            console.log(data.rotate);
            console.log(data.scaleX);
            console.log(data.scaleY);
        }
    });

    $(".cropper-btn-cancel").click(function () {
        $('#cropperModal').css('display', "none");
    });

    $(window).click(function (event) {
        var modal = document.getElementById('cropperModal');
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });

    $(".cropper-btn-success").click(function () {
        $('#cropperModal').css('display', "none");

        var canvas = cropper.getCroppedCanvas();
        var dataURL = canvas.toDataURL('image/jpeg', 0.5);
        var blob = dataURItoBlob(dataURL);

        cropper.replace(dataURL);

        var formData = new FormData();
        formData.append('file', blob);

        if (imgId !== '') {
            deleteImgFromDB();
        }

        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/upload/",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data, textStatus, request) {
                imgId = data.id;
                $('.blog-img ul').find('img').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
                cropper.replace('/api/rest/fileStorage/NEWS/file/read/id/' + imgId);
            }
        });
    });

    function dataURItoBlob(dataURI) {
        var binary = atob(dataURI.split(',')[1]);
        var array = [];
        for (var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
    }
    // --------------------------------------  END cropper  ----------------------------------------------

    // -------------------------------------------------------BEGIN soc network links --------------------------------------------
    // Add/Remove social Input Fields Dynamically with jQuery

    var max_fields = 6; //maximum input boxes allowed
    //var wrapper = $(".input_soc_wrap"); //Fields wrapper

    var cur_fields = 1;

    $(".img-responsive").click(function (e) {
        e.preventDefault();
        var el = e.currentTarget;
        if (cur_fields < max_fields) {

            var socName = $(el).attr("alt");
            addSocialLink(socName);

        }
    });

    function deleteSocialLink(event) {
        event.preventDefault();
        var soc = $(event.currentTarget).parent().remove();
        cur_fields--;
    }

    function addSocialLink(socName) {
        var placeholder = (socName === "TWITTER") ? "Добавить ссылку на Twitter" :
                (socName === "FACEBOOK") ? "Добавить ссылку на Facebook" :
                        (socName === "SKYPE") ? "Добавить ссылку на Skype" :
                                (socName === "VKONTAKTE") ? "Добавить ссылку на Vkontakte" :
                                        (socName === "GOOGLEPLUS") ? "Добавить ссылку на Google +" :
                                                (socName === "LINKEDIN") ? "Добавить ссылку на LinkedIn" : "Добавить ссылку";


        var social = $(".blog-social-container:first").clone();
        social.css('display', 'block')
                .children('.blog-social-input')
                .attr("placeholder", placeholder)
                .removeAttr("id")
                .attr("name", socName);
        social.children('img')
                .click(deleteSocialLink);
        social.appendTo(".group-info");
        cur_fields++;

        return social;
    }

    // ---------------------------------------------------- END Soc network links --------------------------------------

    // -------------------------------------------------------BEGIN drop zone ------------------------------------------

    var dropZone = document.getElementsByClassName('drop_zone');
    for (var i = 0; i < dropZone.length; i++) {
        dropZone[i].addEventListener('dragover', handleDragOver, false);
        dropZone[i].addEventListener('drop', handleFileSelect, false);
    }

    function handleFileSelect(evt) {
        evt.stopPropagation();
        evt.preventDefault();

        var files = evt.dataTransfer.files; // FileList object.

        var reader = new FileReader();

        reader.addEventListener("load", function () {
            cropper.replace(reader.result);
        }, false);

        if (files[0]) {
            reader.readAsDataURL(files[0]);
        }
        $('#cropperModal').css('display', "block");

    }

    function handleDragOver(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }

    // ---------------------------------------------------- END Drop zone --------------------------------------

    //----------------------------------------------------- Begin Images-----------------------------------------
    $('.blogCreationSubmit').click(function () {
        $('#photoInput').trigger('click');
    });

    $('#photoInput').change(function (event) {
        event.preventDefault();

        var files = event.currentTarget.files;

        var reader = new FileReader();

        reader.addEventListener("load", function () {
            cropper.replace(reader.result);
        }, false);

        if (files[0]) {
            reader.readAsDataURL(files[0]);
        }

        $('#cropperModal').css('display', "block");
    });

    function deleteImgFromDB() {
        $('.blog-img ul').find('img').attr("src", "/resources/images/no_photo.jpg");
        $.ajax({
            url: '/api/rest/fileStorage/NEWS/file/delete/id/' + imgId,
            method: 'POST',
            success: function (response) {
            },
            error: function (response) {
            }
        });
    }

    //----------------------------------------------------- End Images -----------------------------------------------


    ///------------------------- Upload Blog -----------------------------------------------

    function isMatchPatternSocialLinks(socName, url) {
        if (socName === "FACEBOOK") {
            return /^(https?:\/\/)?(www\.)?facebook.com\/[a-zA-Z0-9(\.\?)?]/.test(url);
        } else if (socName === "TWITTER") {
            return /(?:http:\/\/)?(?:www\.)?twitter\.com\/(?:(?:\w)*#!\/)?(?:pages\/)?(?:[\w\-]*\/)*([\w\-]*)/.test(url);
        } else if (socName === "LINKEDIN") {
            return /(http|https):\/\/?(?:www\.)?linkedin.com(\w+:{0,1}\w*@)?(\S+)(:([0-9])+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/.test(url);
        } else if (socName === "GOOGLEPLUS") {
            return /((http|https):\/\/)?(www[.])?plus\.google\.com\/.?\/?.?\/?([0-9]*)/.test(url);
        } else if (socName === "VKONTAKTE") {
            return /^(http:\/\/|https:\/\/)?(www\.)?vk\.com\/(\w|\d)+?\/?$/.test(url);
        } else if (socName === "SKYPE") {
            /[a-zA-Z][a-zA-Z0-9\.,\-_]{5,31}/.test(url);
        } else {
            return false;
        }
    }

    function validateBlog() {
        $('.error-validation').removeClass('error-validation');

        var arrValidate = [];

        var title = $('#blogTitle').val();
        var description = $('#blogCreationDescription').val();

        if (title.length > 70 || title.length < 2)  arrValidate.push($('#blogTitle'));
        if (description.length > 5000 || description.length < 50)  arrValidate.push($('#blogCreationDescription'));

        for (var i = 0; i < arrValidate.length; i++) {
            arrValidate[i].addClass('error-validation');
        }
        if (arrValidate.length) {
            return false;
        } else {
            return true;
        }
    }

    $(document).on('click', 'button.SendEdition', function (event) {
        event.preventDefault();

        if (validateBlog()) {

            blog.title = $('#blogTitle').val();
            blog.description = $('#blogCreationDescription').val();
            blog.imageId = imgId;

            var socArr = {};
            $(".group-info").find('input').each(function (index) {
                var socName = $(this).attr("name");
                var url = $(this).val();
                if (isMatchPatternSocialLinks(socName, url) && url.length) {
                    socArr[socName] = url;
                }
            });

            blog.socLinks = socArr;

            $.ajax({
                type: "POST",
                url: "/api/rest/newsService/blog/create",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(blog),
                success: function (response) {
                    window.location.href = '/blog/' + response.id;
                },
                error: function (response) {
                    alert("Внутренняя ошибка сервера");
                }
            });
        }
    });
    ///------------------------- Upload Blog -----------------------------------------------

</script>
</body>
</html>
