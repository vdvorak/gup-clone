<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 13.01.2016
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Редактирование блога</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">

    <%-- Cropper style --%>
    <link  href="/resources/css/cropper.css" rel="stylesheet">
</head>
<body>

<div class="container2">
    <div class="blogCreation">
        <p class="blogCreationHeader">Редактирование новостного блога</p>
        <form id="blogCreationForm" action="#" role="form">
            <label for="blogTitle" class="blogCreationLabel">Заголовок блога</label>
            <input type="text" name="blogTitle" id="blogTitle" class="blogCreationInput" value="${blog.title}">
            <div class="clearfix"></div>

            <label for="blogCreationDescription" class="blogCreationLabel">Описание</label>
            <textarea name="blogCreationDescription" id="blogCreationDescription" class="blogCreationDescription">${blog.description}</textarea>

            <div class="group-info">
                <label for="blogCreationSocial" class="blogCreationLabel">Социальные сети</label>
                <input type="text" name="blogCreationSocial" id="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на Facebook" name="FACEBOOK">
                <div class="socialIconBlog">
                    <a href="#"><img class="img-responsive" src="/resources/images/twitter-info.png" alt="TWITTER"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/facebook-info.png" alt="FACEBOOK"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/skype-info.png" alt="SKYPE"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/vk-info.png" alt="VKONTAKTE"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/g+info.png" alt="GOOGLEPLUS"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/in-info.png" alt="LINKEDIN"></a>
                </div>

                <c:forEach var="socLink" items="${blog.socLinks.entrySet()}">
                    <c:choose>
                        <c:when test="${socLink.getKey() == 'FACEBOOK'}">
                            <input type="text" name="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на Facebook" name="FACEBOOK" value="${socLink.getValue()}">
                        </c:when>
                        <c:when test="${socLink.getKey() == 'VKONTAKTE'}">
                            <input type="text" name="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на Vkontakte" name="VKONTAKTE" value="${socLink.getValue()}">
                        </c:when>
                        <c:when test="${socLink.getKey() == 'LINKEDIN'}">
                            <input type="text" name="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на LinkedIn" name="LINKEDIN" value="${socLink.getValue()}">
                        </c:when>
                        <c:when test="${socLink.getKey() == 'GOOGLEPLUS'}">
                            <input type="text" name="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на Google +" name="GOOGLEPLUS" value="${socLink.getValue()}">
                        </c:when>
                        <c:when test="${socLink.getKey() == 'TWITTER'}">
                            <input type="text" name="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на Twitter" name="TWITTER" value="${socLink.getValue()}">
                        </c:when>
                        <c:when test="${socLink.getKey() == 'SKYPE'}">
                            <input type="text" name="blogCreationSocial" class="blogCreationSocial" placeholder="Добавить ссылку на Skype" name="SKYPE" value="${socLink.getValue()}">
                        </c:when>
                    </c:choose>
                </c:forEach>

            </div>

            <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                <input id="photoInput" type="file" style="display: none;" multiple="multiple" accept="image/*">
            </form>
            <div class="drop_zone">
                <c:choose>
                    <c:when test="${not empty blog.imageId}">
                        <div class="defaultIMG">
                            <ul>
                                <li>
                                    <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImg()"></i><i class="fa fa-pencil fa-2x" onClick="openImgCropper()"></i></span>
                                    <img src="/api/rest/fileStorage/NEWS/file/read/id/${blog.imageId}" alt="">
                                </li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="defaultIMG">
                            <ul>
                                <li>
                                    <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImg()"></i><i class="fa fa-pencil fa-2x" onClick="openImgCropper()"></i></span>
                                    <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                                </li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
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
                    <c:choose>
                        <c:when test="${not empty blog.imageId}">
                            <img id="image" src="/api/rest/fileStorage/NEWS/file/read/id/${blog.imageId}">
                        </c:when>
                        <c:otherwise>
                            <img id="image" src="/resources/images/no_photo.jpg">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <button id="btn-cropp-done" type="button" class="btn btn-primary">Готово</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/cropper.js"></script>
<script>

    var imgId = '';
    var oldImgId = '';
    var categories = [];
    var blog = {};
    var blogId = '${blog.id}';
    var socialSizeOld = ${blog.socLinks.size()} +0;

    if ('${blog.imageId}'.length > 2) {
        oldImgId = '${blog.imageId}';
        imgId = oldImgId;
    }

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

    // -------------------------------------------------------BEGIN soc network links --------------------------------------------

    // Add/Remove social Input Fields Dynamically with jQuery
    $(document).ready(function () {
        var max_fields =6;//maximum input boxes allowed

        var cur_fields = 1;

        if (socialSizeOld < 1) {
            cur_fields = socialSizeOld;
        }

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

        /*$(".input_soc_wrap a").click(function (e) {
            e.preventDefault();
            var socName = $(this).attr("class");

            if (x < max_fields) { //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input name="' + socName + '" type="text" placeholder = "Страница ' + socName + '"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png" width="15" height="15"></a></div>');
            }
        });

        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            x--;
        })*/


        // ---------------------------------------------------- END Soc network links --------------------------------

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
                    deleteImgFromDB(imgId);
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
    });



    //----------------------------------------------------- Image form -----------------------------------------------

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
                deleteImgFromDB(imgId);
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

    function openImgCropper() {
        $('#cropperModal').modal('show');
    }

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
            deleteImgFromDB(imgId);
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
        //----------------------------------------------------- Image form -----------------------------------------------


        ///----------------------Delete photo from  DB-----------------------------------------
        function deleteImgFromDB(picId) {
            $.ajax({
                url: '/api/rest/fileStorage/NEWS/file/delete/id/' + picId,
                method: 'POST',
                success: function (response) {
                },
                error: function (response) {
                }
            });
        }

    function deleteImg() {
        $('.defaultIMG').find('img').attr("src", "/resources/images/no_photo.jpg");
    }

        ///----------------------Delete photo from  DB-----------------------------------------

        ///------------------------- Upload Blog -----------------------------------------------//
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

        $(document).on('click', 'button.SendEdition', function (event) {
            event.preventDefault();

            var title = $('#blogTitle').val();
            var description = $('#blogCreationDescription').val();
            if (title.length > 70 || title.length < 2) return;
            if (description.length > 5000 || description.length < 50) return;

            blog.title = title;
            blog.description = description;

            blog.imageId = imgId;

            if (oldImgId !== '') {
                deleteImgFromDB(oldImgId);
            }

            var socArr = {};
            $(".group-info > input").each(function (index) {
                var socName = $(this).attr("name");
                var url = $(this).val();
                if (isMatchPatternSocialLinks(socName, url) && url.length) {
                    socArr[socName] = url;
                }
            });

            blog.socLinks = socArr;

            $.ajax({
                type: "POST",
                url: "/api/rest/newsService/blog/edit",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(blog),
                success: function (response) {
                    window.location.href = '/index';
//               в перспективе должно перекидывать на страницу этого блога - его просмотр
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