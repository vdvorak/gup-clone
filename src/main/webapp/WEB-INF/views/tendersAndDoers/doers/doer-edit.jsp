<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Создание исполнителя</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/confirmDeleteAlert.css">
</head>
<body>

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div id="doer-create-container" class="container2">
    <div class="doerCreate">
        <h1>РЕДАКТИРОВАНИЕ ИСПОЛНИТЕЛЯ</h1>

        <form action="#">
            <label for="doerName">Введите название</label>
            <input id="doerName" type="text" placeholder="Длина заголовка от 5 до 70 символов">

            <div class="clearfix"></div>

            <label for="doerNaceIds">Выберите отрасль</label>
            <select id="doerNaceIds" class="chosen" multiple data-placeholder="Выберите отрасль" style="width: 553px;">
            </select>

            <div class="clearfix"></div>

            <label for="doerDescription">Описание</label>
            <textarea name="doerDescription" id="doerDescription"
                      placeholder="Длина описания от 50 до 4000 символов"></textarea>

            <div class="clearfix"></div>
        </form>

        <div class="drop_zone">
            <div class="doer-img">
                <ul>
                    <li class="li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x" onclick="deleteImg()"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
            </div>
        </div>

        <div class="titleFile" data-title="Добавить изображение">
            <input type="button" class="doerCreationSubmit">
        </div>
        <label class="doerCreationLabel">Фотографии</label>

        <div class="clearfix"></div>

        <button id="createDoer">Сохранить</button>
        <button id="deleteDoer">Удалить</button>

        <div class="clearfix"></div>

        <div class="confirm" id="confirmDoerDelete" style="display: none">
            <h1>Подтвердите удаление</h1>
            <p>Исполнитель будет навсегда удален</p>
            <button id="cancelDoerDelBtn" autofocus>Отмена</button>
            <button id="confirmDoerDelBtn">Удалить</button>
        </div>

    </div>
</div>

<form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
    <input id="photoInput" type="file" style="display: none;" multiple="multiple">
</form>

<div id="gup-validator-popup" class="gup-popup-overlay">
    <div class="gup-popup">
        <h2>Ошибка редактирования исполнителя</h2>
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
    var doerId = '${doer.id}';
</script>


<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/doer-edit.js"></script>

</body>
</html>