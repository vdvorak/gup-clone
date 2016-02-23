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
    <link  href="/resources/css/cropper.css" rel="stylesheet">
</head>
<body>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>




<div class="container2">
    <div class="blogCreation">
        <p class="blogCreationHeader">Новый новостной блог</p>
        <form id="blogCreationForm" action="#" role="form">
            <label for="blogTitle" class="blogCreationLabel">Заголовок блога</label>
            <input type="text" name="blogTitle" id="blogTitle" class="blogCreationInput">
            <div class="clearfix"></div>

            <label for="blogCreationDescription" class="blogCreationLabel">Описание</label>
            <textarea name="blogCreationDescription" id="blogCreationDescription" class="blogCreationDescription"></textarea>

            <div class="group-info">
                <label for="blogCreationSocial" class="blogCreationLabel">Социальные сети</label>
                <input type="text" name="blogCreationSocial" id="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на Facebook" name="FACEBOOK">
                <div class="socialIconBlog">
                    <a href="#"><img class="img-responsive" src="resources/images/twitter-info.png" alt="TWITTER"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/facebook-info.png" alt="FACEBOOK"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/skype-info.png" alt="SKYPE"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/vk-info.png" alt="VKONTAKTE"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/g+info.png" alt="GOOGLEPLUS"></a>
                    <a href="#"><img class="img-responsive" src="resources/images/in-info.png" alt="LINKEDIN"></a>
                </div>
            </div>

            <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                <input id="photoInput" type="file" style="display: none;" multiple="multiple" accept="image/*">
            </form>
            <div class="drop_zone">
                <div class="defaultIMG">
                    <ul>
                        <li>
                            <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImgFromDB()"></i><i class="fa fa-pencil fa-2x" onClick="openImgCropper()"></i></span>
                            <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                        </li>
                    </ul>
                </div>
            </div>
            <div class="titleFile" data-title="Добавить изображение"><button type="submit" class="blogCreationSubmit"></button></div>
            <label class="blogCreationLabel">Фотографии</label>
        </form>


        <button type="button" class="SendEdition">Отправить редакции</button>

        <div class="clearfix"></div>
    </div>
</div>

<div id="cropperModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title">Редактирование фото</h4>
            </div>
            <div class="modal-body">
                <div class="drop_zone">
                    <img id="image" src="/resources/images/no_photo.jpg">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <button id="btn-cropp-done" type="button" class="btn btn-primary">Готово</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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

<jsp:include page="/WEB-INF/templates/footer.jsp"/>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<sec:authorize var="loggedIn" access="isAuthenticated()" />
<c:choose>
    <c:when test="${loggedIn}">
        <script src="/resources/js/autorizedHeader.js"></script>
    </c:when>
    <c:otherwise>
        <script src="/resources/js/anonymHeader.js"></script>
    </c:otherwise>
</c:choose>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>





<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/cropper.js"></script>
<script>

    var imgId = '';
    var blog = {};

    var image = document.getElementById('image');
    var cropper = new Cropper(image, {
        aspectRatio: 1 / 1,
        crop: function(data) {
            console.log(data.x);
            console.log(data.y);
            console.log(data.width);
            console.log(data.height);
            console.log(data.rotate);
            console.log(data.scaleX);
            console.log(data.scaleY);
        }
    });


    $(document).ready(function () {

        // -------------------------------------------------------BEGIN soc network links --------------------------------------------
        // Add/Remove social Input Fields Dynamically with jQuery

        var max_fields = 6; //maximum input boxes allowed
        //var wrapper = $(".input_soc_wrap"); //Fields wrapper

        var cur_fields = 1;

        $(".img-responsive").click(function (e) {
            e.preventDefault();
            var el = e.currentTarget;
            var socName = $(el).attr("alt");
            var placeholder = (socName === "TWITTER") ? "Добавить ссылку на Twitter" :
                    (socName === "FACEBOOK") ? "Добавить ссылку на Facebook" :
                            (socName === "SKYPE") ? "Добавить ссылку на Skype" :
                                    (socName === "VKONTAKTE") ? "Добавить ссылку на Vkontakte" :
                                            (socName === "GOOGLEPLUS") ? "Добавить ссылку на Google +" :
                                                    (socName === "LINKEDIN") ? "Добавить ссылку на LinkedIn" : "Добавить ссылку";


            if(cur_fields < max_fields)
            $("#blogCreationSocial").clone()
                    .attr("placeholder", placeholder)
                    .removeAttr("id")
                    .attr("name", socName)
                    .appendTo(".group-info");
            cur_fields++;
        });

        /*
        $(".input_soc_wrap a").click(function (e) {
            e.preventDefault();
            var socName = $(this).attr("class");
            if (x < max_fields) { //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input class="input-social" name="' + socName + '" type="url" placeholder = "Страница ' + socName + '"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png" width="15" height="15"></a></div>');
            }
        });

        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            cur_fields--;
        })
        */

        function isMatchPatternSocialLinks(socName, url) {
            if(socName === "FACEBOOK") {
                return /^(https?:\/\/)?(www\.)?facebook.com\/[a-zA-Z0-9(\.\?)?]/.test(url);
            } else if(socName === "TWITTER") {
                return /(?:http:\/\/)?(?:www\.)?twitter\.com\/(?:(?:\w)*#!\/)?(?:pages\/)?(?:[\w\-]*\/)*([\w\-]*)/.test(url);
            } else if(socName === "LINKEDIN") {
                return /(http|https):\/\/?(?:www\.)?linkedin.com(\w+:{0,1}\w*@)?(\S+)(:([0-9])+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/.test(url);
            } else if(socName === "GOOGLEPLUS") {
                return /((http|https):\/\/)?(www[.])?plus\.google\.com\/.?\/?.?\/?([0-9]*)/.test(url);
            } else if(socName === "VKONTAKTE") {
                return /^(http:\/\/|https:\/\/)?(www\.)?vk\.com\/(\w|\d)+?\/?$/.test(url);
            } else if(socName === "SKYPE") {
                /[a-zA-Z][a-zA-Z0-9\.,\-_]{5,31}/.test(url);
            } else {
                return false;
            }
        }

        // ---------------------------------------------------- END Soc network links --------------------------------------

        // -------------------------------------------------------BEGIN drop zone ------------------------------------------

        var dropZone = document.getElementsByClassName('drop_zone');
        for(var i = 0; i < dropZone.length; i++) {
            dropZone[i].addEventListener('dragover', handleDragOver, false);
            dropZone[i].addEventListener('drop', handleFileSelect, false);
        }
        function handleFileSelect(evt) {
            evt.stopPropagation();
            evt.preventDefault();

            var files = evt.dataTransfer.files; // FileList object.

            var reader  = new FileReader();

            reader.addEventListener("load", function () {
                cropper.replace(reader.result);
            }, false);

            if (files[0]) {
                reader.readAsDataURL(files[0]);
            }

            if (files.length) {
                    var formImg = new FormData();
                    formImg.append('file', files[0]);
                    // files is a FileList of File objects. List some properties.

                    if (imgId !== '') {
                        deleteImgFromDB();
                    }

                    $.ajax({
                        type: "POST",
                        url: "/api/rest/fileStorage/NEWS/file/upload/",
                        data: formImg,
                        async: false,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function (data, textStatus, request) {
                            imgId = data.id;
                            $('.defaultIMG').find('img').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
                        }
                    });
            }


        }

        function handleDragOver(evt) {
            evt.stopPropagation();
            evt.preventDefault();
            evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
        }

        // ---------------------------------------------------- END Drop zone --------------------------------------

        //----------------------------------------------------- Begin Images-----------------------------------------
        $('.blogCreationSubmit').click(function(){
            $('#photoInput').trigger('click');
        });

        $('#photoInput').change(function (event) {
            event.preventDefault();

            var files = event.currentTarget.files;

            var reader  = new FileReader();

            reader.addEventListener("load", function () {
                cropper.replace(reader.result);
            }, false);

            if (files[0]) {
                reader.readAsDataURL(files[0]);
            }

            if (files.length) {
                var formImg = new FormData();
                formImg.append('file', files[0]);

                if (imgId !== '') {
                    deleteImgFromDB();
                }

                $.ajax({
                    type: "POST",
                    url: "/api/rest/fileStorage/NEWS/file/upload/",
                    data: formImg,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (data, textStatus, request) {
                        imgId = data.id;
                        $('.defaultIMG').find('img').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
                    }
                });

            }
        });

    });

    function deleteImgFromDB() {
        $('.defaultIMG').find('img').attr("src", "/resources/images/no_photo.jpg");
        $.ajax({
            url: '/api/rest/fileStorage/NEWS/file/delete/id/' + imgId,
            method: 'POST',
            success: function (response) {
            },
            error: function (response) {
            }
        });
    }

    function openImgCropper() {
        $('#cropperModal').modal('show');
    }
    //----------------------------------------------------- End Images -----------------------------------------------
    function dataURItoBlob(dataURI) {
        var binary = atob(dataURI.split(',')[1]);
        var array = [];
        for(var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
    }

    $("#btn-cropp-done").click(function() {
        $('#cropperModal').modal('hide');

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
                $('.defaultIMG').find('img').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
            }
        });
});
    ///------------------------- Upload Blog -----------------------------------------------
    $(document).on('click', 'button.SendEdition', function (event) {
        event.preventDefault();

        var title = $('#blogTitle').val();
        var description = $('#blogCreationDescription').val();
        if(title.length > 70 || title.length < 2) return;
        if(description.length > 5000 || description.length < 50) return;

        blog.title = title;
        blog.description = description;
        blog.imageId = imgId;

        var socArr = {};
        $(".group-info > input").each(function (index) {
            var socName = $(this).attr("name");
            var url = $(this).val();
            if(isMatchPatternSocialLinks(socName, url) && url.length) {
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
    });
    ///------------------------- Upload Blog -----------------------------------------------

</script>
</body>
</html>
