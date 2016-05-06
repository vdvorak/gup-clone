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
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>

    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <link rel="stylesheet" href="resources/css/media-queries.css">
    <link href="/resources/css/cropper.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/gup-custom-modal-window.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
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


<div id="blog-create-container" class="container2">
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

<div id="gup-validator-popup" class="gup-popup-overlay">
    <div class="gup-popup">
        <h2>Ошибка создания блога</h2>
        <a class="popup-close" href="#">&times;</a>
        <div class="popup-content">

        </div>
    </div>
</div>

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

<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/cropper.js"></script>

<script src="/resources/js/blog-create.js"></script>
</body>
</html>
