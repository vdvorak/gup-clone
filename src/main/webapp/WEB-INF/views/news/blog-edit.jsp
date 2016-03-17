<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <link href="/resources/css/cropper.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/css/gup-custom-modal-window.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/confirmDeleteAlert.css">
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
        <p class="blogCreationHeader">Редактирование новостного блога</p>

        <form id="blogCreationForm" action="#" role="form">
            <label for="blogTitle" class="blogCreationLabel">Заголовок блога</label>
            <input type="text" name="blogTitle" id="blogTitle" class="blogCreationInput" value="${blog.title}" placeholder="Длина заголовка от 2 до 70 символов">

            <div class="clearfix"></div>

            <label for="blogCreationDescription" class="blogCreationLabel">Описание</label>
            <textarea name="blogCreationDescription" id="blogCreationDescription"
                      class="blogCreationDescription" placeholder="Длина описания от 50 до 5000 символов">${blog.description}</textarea>

            <div class="group-info">
                <label for="blogCreationSocial" class="blogCreationLabel">Социальные сети</label>
                <input type="text" id="blogCreationSocial" class="blogCreationSocial"
                       placeholder="Добавить ссылку на Facebook" name="FACEBOOK">

                <div class="socialIconBlog">
                    <a href="#"><img class="img-responsive" src="/resources/images/twitter-info.png" alt="TWITTER"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/facebook-info.png"
                                     alt="FACEBOOK"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/skype-info.png" alt="SKYPE"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/vk-info.png" alt="VKONTAKTE"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/g+info.png" alt="GOOGLEPLUS"></a>
                    <a href="#"><img class="img-responsive" src="/resources/images/in-info.png" alt="LINKEDIN"></a>
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
                <c:choose>
                    <c:when test="${not empty blog.imageId}">
                    <div class="blog-img">
                            <ul>
                                <li class="li-defaultIMG">
                                    <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImg()"></i></span>
                                    <img src="/api/rest/fileStorage/NEWS/file/read/id/${blog.imageId}" alt="">
                                </li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="blog-img">
                            <ul>
                                <li class="li-defaultIMG">
                                    <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImg()"></i></span>
                                    <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                                </li>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="titleFile" data-title="Добавить изображение">
                <button type="submit" class="blogCreationSubmit"></button>
            </div>
            <label class="blogCreationLabel">Фотографии</label>
        </form>

        <div class="clearfix"></div>

        <button type="button" class="SendEdition">Отправить редакции</button>
        <button id="btn-blog-delete" type="button">Удалить блог</button>

        <div class="clearfix"></div>
    </div>
</div>

<div class="confirm" id="confirmBlogDelete" style="display: none">
    <h1>Подтвердите удаление</h1>
    <p>Объявление будет навсегда удалено</p>
    <button id="cancelBlogDelBtn" autofocus>Отмена</button>
    <button id="confirmBlogDelBtn">Удалить</button>
</div>

<!-- The Modal -->
<div id="cropperModal" class="cropper-modal">

    <!-- Modal content -->
    <div class="cropper-modal-content">
        <div class="cropper-modal-header">
            <span>Редактирование фотографии</span>
        </div>
        <div class="cropper-modal-body drop_zone">
            <c:choose>
                <c:when test="${not empty blog.imageId}">
                    <img id="cropper-image" src="/api/rest/fileStorage/NEWS/file/read/id/${blog.imageId}">
                </c:when>
                <c:otherwise>
                    <img id="cropper-image" src="/resources/images/no_photo.jpg">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="cropper-modal-footer">
            <button class="cropper-btn cropper-btn-success">Готово</button>
            <button class="cropper-btn cropper-btn-cancel">Отмена</button>

        </div>
    </div>

</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>
<script src="/resources/js/cropper.js"></script>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script>

    var imgId = '';
    var oldImgId = '';
    var categories = [];
    var blog = {};
    var blogId = '${blog.id}';
    var socialSizeOld = ${blog.socLinks.size()} +0;
    var socialLinks = {};
    if ('${blog.imageId}'.length > 2) {
        oldImgId = '${blog.imageId}';
        imgId = oldImgId;
    }

    if ('${blog.socLinks}'.length > 5) {
        socialLinks = JSON.parse('${blog.socLinks}'.replace('{', '{"').replace(/=/g, '":"').replace(/,/g, '","').replace('}', '"}').replace(/ /g, ''));
    }

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
    var max_fields = 6;//maximum input boxes allowed

    var cur_fields = 1;

    if (socialSizeOld < 1) {
        cur_fields = socialSizeOld;
    }

    var firstFacebookLink = "";
    for (var key in socialLinks) {
        if (!firstFacebookLink && key === 'FACEBOOK') {
            firstFacebookLink = socialLinks[key];
            $('#blogCreationSocial').val(firstFacebookLink);
        } else {
            var newSocLink = addSocialLink(key)
            newSocLink.val(socialLinks[key]);
        }
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

    function deleteSocialLink(event) {
        event.preventDefault();
        var soc = $(event.currentTarget).parent().remove();
        cur_fields--;
    }

    $(".img-responsive").click(function (e) {
        e.preventDefault();
        var el = e.currentTarget;
        if (cur_fields < max_fields) {

            var socName = $(el).attr("alt");
            addSocialLink(socName);
        }
    });

    // ---------------------------------------------------- END Soc network links --------------------------------

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


    //----------------------------------------------------- Image form -----------------------------------------------

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
        imgId = '';
        $('.blog-img ul').find('img').attr("src", "/resources/images/no_photo.jpg");
    }

    ///----------------------Delete photo from  DB-----------------------------------------

    ///------------------------- Upload Blog -----------------------------------------------//
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
            return /^(http:\/\/|https:\/\/)?(www\.)?vk\.com\/(\w|\d|.)+?\/?$/.test(url);
        } else if (socName === "SKYPE") {
            return /[a-zA-Z][a-zA-Z0-9\.,\-_]{5,31}/.test(url);
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

        for(var i = 0; i < arrValidate.length; i++) {
            arrValidate[i].addClass('error-validation');
        }
        if(arrValidate.length) {
            return false;
        } else {
            return true;
        }
    }

    $(document).on('click', 'button.SendEdition', function (event) {
        event.preventDefault();

        if (validateBlog()) {

            blog.id = blogId;
            blog.title = $('#blogTitle').val();
            blog.description = $('#blogCreationDescription').val();

            blog.imageId = imgId;

            if (oldImgId !== '' || oldImgId !== imgId) {
                deleteImgFromDB(oldImgId);
            }

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
                url: "/api/rest/newsService/blog/edit",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(blog),
                statusCode: {
                    200: function (response) {
                        window.location.href = '/blog/' + blogId;
                    },
                    400: function (response) {
                    },
                    404: function (response) {
                    }
                }
            });
        }
    });
    ///------------------------- Upload Blog -----------------------------------------------

    //------------------ BEGIN DELETE BLOG ------------------------------------//
    $('#btn-blog-delete').on('click', function () {
        $("#confirmBlogDelete").show();
    });

    $('#cancelBlogDelBtn').on('click', function () {
        $("#confirmBlogDelete").hide();
    });

    $('#confirmBlogDelBtn').on('click', function () {
        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/id/" + blogId + "/delete",
            statusCode: {
                204: function () {
                    window.location.href = '/blogs-and-news';
                }
            }
        });
    });
    //------------------ END DELETE BLOG ------------------------------------//

</script>
</body>
</html>