<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Проекты | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

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

    <!--PAGE CONTENT START-->

    <div class="container2">
        <div class="contentContainer editor">
            <div class="title">Редактирование проекта</div>
            <form id="uploadProjectPhotoForm">
                <input id="uploadProjectPhotoInput" type="file" name="file" accept="image/*,image/jpeg" style="display:none">
            </form>
            <form class="project">
                <div class="field required tit">
                    <label for="main-title-info" class="editorLabel">Заголовок</label>
                    <input id="main-title-info" type="text" name='text' class="editorInput">
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

                <div class="field IMGUploader">
                    <label for="addProjPhoto" class="editorLabel">Изображения</label>
                    <div class="titleFile" data-title="Добавить изображение">
                        <button id="addProjPhoto" class="blogCreationSubmit"></button>
                    </div>
                    <div class="IMGBlock" id="IMGBlock"></div>
                </div>


                <div class="field required">
                    <label for="sum" class="editorLabel">Нужная сумма</label>
                    <input id="sum" type="number" name='sum' class="editorInput" style="width: 291px;">
                    <span class="currency">₴</span>
                </div>
                <div class="field description">
                    <label for="description" class="editorLabel">Описание</label>
                    <textarea id="description" name='description' class="editorInput"></textarea>
                </div>
                <div class="field">
                    <button id="editProjectBtn" class="info-submit">Сохранить</button>
                </div>
            </form>
        </div>
    </div>

    <!--PAGE CONTENT END-->

    <sec:authorize access="isAuthenticated()">
        <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
    </sec:authorize>

    <jsp:include page="/WEB-INF/templates/footer.jsp"/>

    <jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

    <jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/logo-section.js"></script>
    <script src="/resources/js/search-bar.js"></script>

    <script src="/resources/js/editProject.js"></script>
</body>
</html>

