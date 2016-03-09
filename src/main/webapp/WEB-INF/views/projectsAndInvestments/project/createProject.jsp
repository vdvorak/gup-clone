<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Создание проекта | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/chosen.min.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/mini.css">

    <%--<link rel="stylesheet" href="/resources/css/font-awesome.css">--%>
    <%--<link rel="stylesheet" href="/resources/css/media-queries.css">--%>

    <%--<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">--%>
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
    <div class="contentContainer editor">
        <div class="title">Создание проекта</div>
        <div class="project">
            <div class="field required tit">
                <label for="main-title-info" class="editorLabel">Заголовок</label>
                <input id="main-title-info" type="text" name='text' class="editorInput" placeholder="От 4 до 70 символов">
            </div>
            <div class="field required projType">
                <div class="editorLabel">Тип проекта</div>
                <div class="radioGroup">
                    <div class="column c1">
                        <div class="row">
                            <label for="type-restruct">Реструктуризация</label>
                            <label class="label-checkbox">
                                <input type="radio" class="greenCheckbox" id="type-restruct" value="RENOVATION" name="type" /><span></span></label>
                        </div>
                        <div class="row">
                            <label for="type-paper">Проект на бумаге</label>
                            <label class="label-checkbox">
                                <input type="radio" class="greenCheckbox" id="type-paper" value="PROJECT_ON_PAPER" name="type" /><span></span></label>
                        </div>
                    </div>
                    <div class="column c2">
                        <div class="row">
                            <label for="type-prototype">Готовый прототип</label>
                            <label class="label-checkbox">
                                <input type="radio" class="greenCheckbox" id="type-prototype" value="PROTOTYPE" name="type" /><span></span></label>
                        </div>
                        <div class="row">
                            <label for="type-nouHau">Ноу-Хау</label>
                            <label class="label-checkbox">
                                <input type="radio" class="greenCheckbox" id="type-nouHau" value="KNOW_HOW" name="type" /><span></span></label>
                        </div>
                    </div>
                </div>

            </div>

            <div class="field required">
                <label for="sum" class="editorLabel">Нужная сумма</label>
                <input id="sum" type="number" name='sum' class="editorInput" style="width: 291px;" placeholder="Минимум 1">
                <span class="currency">₴</span>
            </div>

            <div class="field required">
                <label for="categoriesOfIndustry" class="editorLabel">Категории индустрии</label>
                <select  id="categoriesOfIndustry" class="chosen" multiple="true" data-placeholder="Выберите категории" style="width: 550px;">
                    <optgroup label="Инженерно-строительные услуги">
                        <option>Строительство коттеджей, домов</option>
                        <option>Монтаж наладка и ремонт инженерных систем</option>
                        <option>Категория 3</option>
                        <option>Категория 4</option>
                    </optgroup>
                    <optgroup label="Swedish Cars">
                        <option>Категория 5</option>
                        <option>Категория 7</option>
                        <option>Категория 10</option>
                    </optgroup>
                </select>
            </div>

            <div class="field description">
                <label for="description" class="editorLabel">Описание</label>
                <textarea id="description" name='description' class="editorInput" placeholder="От 50 до 5000 символов"></textarea>
            </div>

            <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                <input id="photoInput" type="file" style="display: none;" multiple="multiple">
            </form>

            <div class="field IMGUploader">
                <div class="titleFile" data-title="Добавить изображение"><button type="submit" class="blogCreationSubmit"></button></div>
            </div>

            <div id="drop_zone">
                <ul id="project-img-block" class="ul-img-container ul-img-container-green">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
                <ul id="project-doc-block" class="ul-img-container ul-img-container-green">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="http://www.uzscience.uz/upload/userfiles/images/doc.png" alt="defaultIMG">
                        <div style="width: 100%; text-align: center; font-weight: bold"></div>
                    </li>
                </ul>
            </div>

            <div class="field">
                <button class="info-submit">Сохранить</button>
            </div>
        </div>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>
<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>
<script src="/resources/js/createProject.js"></script>
</body>
</html>
