<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Создание тендера</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
</head>
<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div id="tender-container" class="container2">
    <div class="tenderMake">
        <h1>СОЗДАНИЕ ТЕНДЕРА</h1>
        <form id="tender-make-form" action="#">
            <label for="EnterTheTitle">Введите название</label>
            <input type="text" id="EnterTheTitle" required>
<%--        <label>Выберете отрасль</label>
            <input type="text" id="searchInputKved" class="form-control sear" name="search" placeholder="Поиск">--%>
            <label for="selectKved">Выберите отрасль</label>
            <select id="selectKved" class="chosen" multiple data-placeholder="Выберите отрасль" style="width: 553px;">
            </select>

            <div class="clearfix"></div>

            <label for="tender-date" class="label-notRequered">Сроки</label>
            <div id="tender-date">
                <input type="text" id="tender-datepicker1" class="datepicker-input" placeholder="Дата начала"> - <input type="text" id="tender-datepicker2" class="datepicker-input" placeholder="Дата окончания">
                <span>Дата начала не должна превышать дату окончания</span>
            </div>

            <%--<p class="datePickPi"><input type="text" id="tender-datepicker1" class="datepicker-input"></p>
            <p class="datePickPi"><span>Дата начала не должна превышать дату окончания</span>&nbsp;- <input type="text" id="tender-datepicker2" class="datepicker-input"></p>
--%>
            <div class="clearfix"></div>

            <h2>Укажите адрес</h2>
            <div class="location">
                <label for="SelectArea" class="label-notRequered">Выберете область</label>
                <input type="text" id="SelectArea">

                <div class="clearfix"></div>

                <label for="SelectCity" class="label-notRequered">Выберете город</label>
                <input type="text" id="SelectCity">
            </div>

<%--            <label for="map">Введите адрес</label>
            <div id="floating-panel">
                <input id="address" class="tender-map-address" type="text">
                <button id="btn-save-adress">Сохранить</button>
            </div>
            <div id="map" class="tender-map"></div>--%>

            <label>Тип</label>
            <div class="tenderRadio">
                <label><input class="input-tenderRadio" type="radio" value="open" name="k" data-type="OPEN" checked/><span></span></label><p>открытый</p>
                <label><input class="input-tenderRadio" type="radio" value="open" name="k" data-type="CLOSE"/><span></span></label><p>закрытый</p>
            </div>

            <div class="clearfix"></div>

            <div class="description">

                <label for="HideBidders">Скрывать участников тендера</label>
                <label><input type="checkbox" id="HideBidders" value="open" name="k"/><span></span></label>
                <%--<label for="InviteBidders" style="display: none">Пригласить участников тендера</label>--%>
                <%--<input type="text" id="InviteBidders" placeholder="Название" style="display: none">--%>

                <label for="HideContacts">Скрывать контактные данные</label>
                <input type="checkbox" id="HideContacts" value="open"/>

                <div class="clearfix"></div>

                <label for="selectParticipants" style="display: none">Пригласить участников тендера</label>
                <select id="selectParticipants" class="chosen" multiple data-placeholder="Участники тендера" style="display: none">
                </select>

                <div class="clearfix"></div>

                <label for="ExpectedValue">Ожидаемая стоимость</label>
                <input type="text" id="ExpectedValue" placeholder="456">
                <label for="TenderNumber">Номер тендера</label>
                <input type="text" id="TenderNumber" placeholder="XX12345678-90">

                <div class="clearfix"></div>

                <label for="Description">Описание</label>
                <textarea name="Description" id="Description"></textarea>
            </div>

            <div class="titleFile" data-title="Добавить изображение"><button type="submit" class="blogCreationSubmit" form="photoForm"></button></div>
            <img id="tender-btn-addDoc" src="/resources/images/clip.png" alt="clip">

            <div class="clearfix"></div>

            <div id="drop_zone">
                <ul id="tender-img-block" class="ul-img-container ul-img-container-green">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
                    </li>
                </ul>
                <ul id="tender-doc-block" class="ul-img-container ul-img-container-green">
                    <li class="li-containerIMG li-defaultIMG">
                        <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
                        <img src="http://www.uzscience.uz/upload/userfiles/images/doc.png" alt="defaultIMG">
                        <div style="width: 100%; text-align: center; font-weight: bold"></div>
                    </li>
                </ul>
            </div>


            <button id="tender-btn-save" type="submit" form="tender-make-form">Сохранить</button>

            <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
                <input id="photoInput" type="file" style="display: none;" multiple="multiple">
            </form>

        </form>

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

<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>

<script src="/resources/js/tender-make.js"></script>

</body>
</html>
