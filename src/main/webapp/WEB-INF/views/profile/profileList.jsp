<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 13.01.2016
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>GUP - Профили</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
    <script src="/resources/js/common.js"></script>
    <sec:authorize access="isAuthenticated()">
        <script src="/resources/js/autorizedHeader.js"></script>
    </sec:authorize>

    <script>
        var profileFO = {skip:0, limit:20};

        <c:if test="${profileFO != null}">
            profileFO = ${profileFO};
        </c:if>

        $(document).ready(function () {
            $('#searchInput').val(profileFO.searchField);
            updateProfilesTable(profileFO);
        });

        $(function() {
            $("#searchInput").autocomplete({
                source: function (request, response) {
                    $.getJSON("${pageContext.request.contextPath}/search/autocomplete/profile", {
                        term: request.term
                    }, response);
                }
            });
        });

        function updateProfilesTable(profileFO) {
//            alert('updateProfilesTable: ' + JSON.stringify(profileFO));

            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/profilesService/profile/read/all",
                data: JSON.stringify(profileFO),
                success: function (response) {

                    updatePaginationBlock(response);

                    $("#profilesTable").find("tr:not(:first)").remove();

                    response.entities.forEach(function(profile) {
                        var row = $('<tr>');

                        if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != undefined) {
                            row.append($('<td>').html('<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '" width="100" height="100">'));
                        } else {
                            row.append($('<td>').html('<img src="/resources/images/no_photo.jpg" width="100" height="100">'));
                        }

                        if (profile.username == null) {
                            row.append($('<td>').html('<a href="/profile/id/' + profile.id + '">' + 'Безымянный' + '</a>'));
                        } else {
                            row.append($('<td>').html('<a href="/profile/id/' + profile.id + '">' + profile.username + '</a>'));
                        }

                        if (profile.contact != null && profile.contact.aboutUs != null) {
                            row.append($('<td>').html(profile.contact.aboutUs));
                        } else {
                            row.append($('<td>').html("Пользователь еще ничего на рассказал о себе"));
                        }

                        row.append($('<td>').html('<a href="/profile/id/' + profile.id + '">Добавить</a>'));

                        $('#profilesTable').append(row);
                    });

                }
            });
        }

        function updatePaginationBlock(responseEntities) {
            $('#pageNumLine').html((profileFO.skip/profileFO.limit + 1) + ' из ' + Math.ceil(responseEntities.totalEntities/profileFO.limit));

            if (profileFO.skip < profileFO.limit) {
                $('#prevPageButton').hide();
            } else {
                $('#prevPageButton').show();
            }

            if ((profileFO.skip +  profileFO.limit) >= responseEntities.totalEntities) {
                $('#nextPageButton').hide();
            } else {
                $('#nextPageButton').show();
            }
        }

        $(document).on('click', '#prevPageButton', function () {
            profileFO.skip -= profileFO.limit;
            updateProfilesTable(profileFO);
        });

        $(document).on('click', '#nextPageButton', function () {
            profileFO.skip += profileFO.limit;
            updateProfilesTable(profileFO);
        });

        $(document).on('click', '#findProfilesButton', function (event) {
            // заполнять profileFO с фильтров
            profileFO.skip = 0;

            if ($("#searchInput").val() == "") {
                $("#searchInput").focus();
            } else {
                profileFO.searchField = $("#searchInput").val();
            }

            updateProfilesTable(profileFO);
        });
    </script>
</head>

<body>
<div>
    <jsp:include page="/WEB-INF/templates/common-header.jsp"/>
    <jsp:include page="/WEB-INF/templates/authentification.jsp"/>

    <div>
        <h2 align="center">Профили</h2>
        <a href="/profile/list"><h3 align="center">Посмотреть все профили</h3></a>
    </div>
    <div align="center">
        <input id="searchInput" size="100" placeholder="Имя профиля">
        <button id="findProfilesButton">Найти профиль</button>
    </div>
    <div id="paginationDiv">
        <label id="pageLabel" for="pageNumLine"><b>Страница:</b> <label id="pageNumLine"></label></label>

        <button id="prevPageButton">Назад</button>
        <button id="nextPageButton">Вперед</button>
    </div>
    <div>
        <table id="profilesTable" border="1" width="100%">
            <thead>
            <tr>
                <th>Фото</th>
                <th>Имя</th>
                <th>О себе</th>
                <th>Добавить в контакты</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
