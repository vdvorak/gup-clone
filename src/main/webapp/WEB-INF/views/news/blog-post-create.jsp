<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Создание новости | GUP</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/dropdown-multicolumn.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

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

            <div class="clearfix"></div>

            <p class="blogCreationHeader blueColor">Категория</p>

            <div class="input-group cat">
                <hr>
                <label class="labelLookGood" for="sciCategory"><input id="sciCategory" type="checkbox"
                                                                      name="sci"><span></span></label>
                <label class="LabelForLabel" for="sciCategory">Наука и техника</label>
                <label class="labelLookGood" for="artCategory"><input id="artCategory" type="checkbox"
                                                                      name="art"><span></span></label>
                <label class="LabelForLabel" for="artCategory">Искусство</label>
                <label class="labelLookGood" for="savorCategory"><input id="savorCategory" type="checkbox" name="savor"><span></span></label>
                <label class="LabelForLabel" for="savorCategory">Светская жизнь</label>
                <label class="labelLookGood" for="policyCategory"><input id="policyCategory" type="checkbox"
                                                                         name="policy"><span></span></label>
                <label class="LabelForLabel" for="policyCategory">Политика</label>
                <label class="labelLookGood" for="worldCategory"><input id="worldCategory" type="checkbox" name="world"><span></span></label>
                <label class="LabelForLabel" for="worldCategory">Мир и общество</label>
                <label class="labelLookGood" for="economyCategory"><input id="economyCategory" type="checkbox"
                                                                          name="economy"><span></span></label>
                <label class="LabelForLabel" for="economyCategory">Экономика</label>
                <label class="labelLookGood" for="sportCategory"><input id="sportCategory" type="checkbox" name="sport"><span></span></label>
                <label class="LabelForLabel" for="sportCategory">Спорт,хобби</label>
                <label class="labelLookGood" for="socialCategory"><input id="socialCategory" type="checkbox"
                                                                         name="social"><span></span></label>
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
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
                <p>Drop Image To Upload</p>
            </div>

            <%--            <label for="blogTitle" class="blogCreationLabel">Добавить видео</label>
                        <input type="text" name="blogTitle" id="blogTitle" class="blogCreationInput blueBorder"
                               placeholder="Youtube"
                               pattern="(?:https?:\/\/)?(?:www\.)?youtu\.?be(?:\.com)?\/?.*(?:watch|embed)?(?:.*v=|v\/|\/)([\w\-_]+)\&?">--%>
        </form>
        <button type="button" class="SendEdition">Отправить редакции</button>

        <div class="clearfix"></div>

    </div>
</div>

<div id="gup-validator-popup" class="gup-popup-overlay">
    <div class="gup-popup">
        <h2>Ошибка создания новости</h2>
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
<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
<script>
    var flag = '${flag}';
    var blogId = '${blogId}';
</script>
<script src="/resources/js/blog-post-create.js"></script>
</body>
</html>