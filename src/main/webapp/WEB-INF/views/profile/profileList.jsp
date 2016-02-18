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
    <title>Список пользователей | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">

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


    <div class="container2" id="profileListContainer">
        <%--<div class="profileList">--%>
            <%--<div class="profileListLogo"--%>
                 <%--style="background: url(/resources/css/images/profileListLogo.png) no-repeat center center;"></div>--%>
            <%--<a href="#" class="profileListName">Веган Фест</a>--%>

            <%--<p class="profileListDescription">Описание пользователя Описание пользователя Описание пользователя Описание--%>
                <%--пользователя Описание пользователя Описание пользователя Описание пользователя</p>--%>
        <%--</div>--%>
        <%--<div class="profileList vip-color-border">--%>
            <%--<!-- что бы цвет бордера стал виповым надо добавить клас vip-color-border-->--%>
            <%--<div class="profileListLogo vip-color-border"--%>
                 <%--style="background: url(/resources/css/images/profileListLogo.png) no-repeat center center;"></div>--%>
            <%--<!-- что бы цвет бордера стал виповым надо добавить клас vip-color-border-->--%>
            <%--<a href="#" class="profileListName">Веган Фест</a>--%>

            <%--<p class="profileListDescription">Описание пользователя Описание пользователя Описание пользователя Описание--%>
                <%--пользователя Описание пользователя Описание пользователя Описание пользователя</p>--%>
        <%--</div>--%>
        <%--<div class="profileList">--%>
            <%--<div class="profileListLogo"--%>
                 <%--style="background: url(/resources/css/images/profileListLogo.png) no-repeat center center;"></div>--%>
            <%--<a href="#" class="profileListName">Веган Фест</a>--%>

            <%--<p class="profileListDescription">Описание пользователя Описание пользователя Описание пользователя Описание--%>
                <%--пользователя Описание пользователя Описание пользователя Описание пользователя</p>--%>
        <%--</div>--%>
        <%--<div class="profileList vip-color-border">--%>
            <%--<!-- что бы цвет бордера стал виповым надо добавить клас vip-color-border-->--%>
            <%--<div class="profileListLogo vip-color-border"--%>
                 <%--style="background: url(/resources/css/images/profileListLogo.png) no-repeat center center;"></div>--%>
            <%--<!-- что бы цвет бордера стал виповым надо добавить клас vip-color-border-->--%>
            <%--<a href="#" class="profileListName">Веган Фест</a>--%>

            <%--<p class="profileListDescription">Описание пользователя Описание пользователя Описание пользователя Описание--%>
                <%--пользователя Описание пользователя Описание пользователя Описание пользователя</p>--%>
        <%--</div>--%>
        <%--<div class="profileList">--%>
            <%--<div class="profileListLogo"--%>
                 <%--style="background: url(/resources/css/images/profileListLogo.png) no-repeat center center;"></div>--%>
            <%--<a href="#" class="profileListName">Веган Фест</a>--%>

            <%--<p class="profileListDescription">Описание пользователя Описание пользователя Описание пользователя Описание--%>
                <%--пользователя Описание пользователя Описание пользователя Описание пользователя</p>--%>
        <%--</div>--%>
        <%--<div class="profileList">--%>
            <%--<div class="profileListLogo"--%>
                 <%--style="background: url(/resources/css/images/profileListLogo.png) no-repeat center center;"></div>--%>
            <%--<a href="#" class="profileListName">Веган Фест</a>--%>

            <%--<p class="profileListDescription">Описание пользователя Описание пользователя Описание пользователя Описание--%>
                <%--пользователя Описание пользователя Описание пользователя Описание пользователя</p>--%>
        <%--</div>--%>
    </div>

    <%--<div>--%>
    <%--<jsp:include page="/WEB-INF/templates/common-header.jsp"/>--%>
    <%--<jsp:include page="/WEB-INF/templates/authentification.jsp"/>--%>

    <%--<div>--%>
    <%--<h2 align="center">Профили</h2>--%>
    <%--<a href="/profile/list"><h3 align="center">Посмотреть все профили</h3></a>--%>
    <%--</div>--%>
    <%--<div align="center">--%>
    <%--<input id="searchInput" size="100" placeholder="Имя профиля">--%>
    <%--<button id="findProfilesButton">Найти профиль</button>--%>
    <%--</div>--%>
    <%--<div id="paginationDiv">--%>
    <%--<label id="pageLabel" for="pageNumLine"><b>Страница:</b> <label id="pageNumLine"></label></label>--%>

    <%--<button id="prevPageButton">Назад</button>--%>
    <%--<button id="nextPageButton">Вперед</button>--%>
    <%--</div>--%>
    <%--<div>--%>
    <%--<table id="profilesTable" border="1" width="100%">--%>
    <%--<thead>--%>
    <%--<tr>--%>
    <%--<th>Фото</th>--%>
    <%--<th>Имя</th>--%>
    <%--<th>О себе</th>--%>
    <%--<th>Добавить в контакты</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--</table>--%>
    <%--</div>--%>
    <%--</div>--%>

    <sec:authorize access="isAuthenticated()">
        <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
    </sec:authorize>

    <jsp:include page="/WEB-INF/templates/footer.jsp"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
    <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
    <script src="/resources/js/vendor/bootstrap.js"></script>
    <script src="/resources/js/jquery.bxslider.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
    <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

    <sec:authorize var="loggedIn" access="isAuthenticated()"/>
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

    <script src="/resources/js/top-news-block.js"></script>
    <script src="/resources/js/top-offers-block.js"></script>
    <script src="/resources/js/top-tenders-block.js"></script>
    <script src="/resources/js/top-projects-block.js"></script>

    <script>
        var profileFO = {skip: 0, limit: 20};
        <c:if test="${profileFO != null}">
            profileFO = ${profileFO};
        </c:if>

        loadAndAppendProfileBlocks();

        function loadAndAppendProfileBlocks() {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/profilesService/profile/read/all",
                data: JSON.stringify(profileFO),
                statusCode: {
                    200: function (responseEntity) {
                        responseEntity.entities.forEach(function (profile) {
                            appendProfileBlock(profile);
                        });
                    }
                }
            });
        }

        function getUrlForProfilePic(picId) {
            if (picId != null && picId != '') {
                return "/api/rest/fileStorage/PROFILE/file/read/id/" + picId;
            } else {
                return "/resources/images/no_photo.jpg";
            }
        }

        function appendProfileBlock(profile) {
            $('#profileListContainer').append(
                    '<div class="profileList <%-- vip-color-border --%>">' +
                        '<div class="profileListLogo <%-- vip-color-border --%>" style="background: url(' + getUrlForProfilePic(profile.contact.pic) + ') no-repeat center center;"></div>' +
                        '<a href="/profile/id/' + profile.id + '" class="profileListName">' + profile.username + '</a>' +
                        '<p class="profileListDescription">' + profile.contact.aboutUs + '</p>' +
                    '</div>');
        }
//        $(document).ready(function () {
//            $('#searchInput').val(profileFO.searchField);
//            updateProfilesTable(profileFO);
//        });

        <%--$(function () {--%>
            <%--$("#searchInput").autocomplete({--%>
                <%--source: function (request, response) {--%>
                    <%--$.getJSON("${pageContext.request.contextPath}/search/autocomplete/profile", {--%>
                        <%--term: request.term--%>
                    <%--}, response);--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>

        <%--function updateProfilesTable(profileFO) {--%>
            <%--//            alert('updateProfilesTable: ' + JSON.stringify(profileFO));--%>

//            $.ajax({
//                type: "POST",
//                contentType: "application/json; charset=utf-8",
//                url: "/api/rest/profilesService/profile/read/all",
//                data: JSON.stringify(profileFO),
//                success: function (response) {

                    <%--updatePaginationBlock(response);--%>

                    <%--$("#profilesTable").find("tr:not(:first)").remove();--%>

                    <%--response.entities.forEach(function (profile) {--%>
                        <%--var row = $('<tr>');--%>

                        <%--if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != undefined) {--%>
                            <%--row.append($('<td>').html('<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '" width="100" height="100">'));--%>
                        <%--} else {--%>
                            <%--row.append($('<td>').html('<img src="/resources/images/no_photo.jpg" width="100" height="100">'));--%>
                        <%--}--%>

                        <%--if (profile.username == null) {--%>
                            <%--row.append($('<td>').html('<a href="/profile/id/' + profile.id + '">' + 'Безымянный' + '</a>'));--%>
                        <%--} else {--%>
                            <%--row.append($('<td>').html('<a href="/profile/id/' + profile.id + '">' + profile.username + '</a>'));--%>
                        <%--}--%>

                        <%--if (profile.contact != null && profile.contact.aboutUs != null) {--%>
                            <%--row.append($('<td>').html(profile.contact.aboutUs));--%>
                        <%--} else {--%>
                            <%--row.append($('<td>').html("Пользователь еще ничего на рассказал о себе"));--%>
                        <%--}--%>

                        <%--row.append($('<td>').html('<a href="/profile/id/' + profile.id + '">Добавить</a>'));--%>

                        <%--$('#profilesTable').append(row);--%>
                    <%--});--%>

                <%--}--%>
            <%--});--%>
        <%--}--%>

        <%--function updatePaginationBlock(responseEntities) {--%>
            <%--$('#pageNumLine').html((profileFO.skip / profileFO.limit + 1) + ' из ' + Math.ceil(responseEntities.totalEntities / profileFO.limit));--%>

            <%--if (profileFO.skip < profileFO.limit) {--%>
                <%--$('#prevPageButton').hide();--%>
            <%--} else {--%>
                <%--$('#prevPageButton').show();--%>
            <%--}--%>

            <%--if ((profileFO.skip + profileFO.limit) >= responseEntities.totalEntities) {--%>
                <%--$('#nextPageButton').hide();--%>
            <%--} else {--%>
                <%--$('#nextPageButton').show();--%>
            <%--}--%>
        <%--}--%>

        <%--$(document).on('click', '#prevPageButton', function () {--%>
            <%--profileFO.skip -= profileFO.limit;--%>
            <%--updateProfilesTable(profileFO);--%>
        <%--});--%>

        <%--$(document).on('click', '#nextPageButton', function () {--%>
            <%--profileFO.skip += profileFO.limit;--%>
            <%--updateProfilesTable(profileFO);--%>
        <%--});--%>

        <%--$(document).on('click', '#findProfilesButton', function (event) {--%>
            <%--// заполнять profileFO с фильтров--%>
            <%--profileFO.skip = 0;--%>

            <%--if ($("#searchInput").val() == "") {--%>
                <%--$("#searchInput").focus();--%>
            <%--} else {--%>
                <%--profileFO.searchField = $("#searchInput").val();--%>
            <%--}--%>

            <%--updateProfilesTable(profileFO);--%>
        <%--});--%>
    </script>
</body>
</html>
