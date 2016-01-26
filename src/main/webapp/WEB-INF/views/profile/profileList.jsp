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
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
    <script>
        var profileFO = {skip:0, limit:20};

        <c:if test="${profileFO != null}">
            profileFO.skip = ${profileFO.skip};
            profileFO.searchField = '${profileFO.searchField}';
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

                        $('#profilesTable').append(row);
                    });

                }
            });
        }

        function updatePaginationBlock(responseEntities) {
            $('#pageNumLine').html((profileFO.skip + 1) + ' из ' + (Math.round(responseEntities.totalEntities/profileFO.limit) + 1));

            if (profileFO.skip <= 0) {
                $('#prevPageButton').hide();
            }

            if ((profileFO.skip + 1) * profileFO.limit >= responseEntities.totalEntities) {
                $('#nextPageButton').hide();
            }
        }

        $(document).on('click', '#prevPageButton', function (event) {
            profileFO.skip = profileFO.skip - 1;
            updateProfilesTable(profileFO);
        });

        $(document).on('click', '#nextPageButton', function (event) {
            profileFO.skip = profileFO.skip + 1;
            updateProfilesTable(profileFO);
        });

        $(document).on('click', '#findProfilesButton', function (event) {

            // заполнять projectFO с фильтров
            var searchProfileFO = {};
            searchProfileFO.skip = profileFO.skip;
            searchProfileFO.limit = profileFO.limit;
            searchProfileFO.searchField = profileFO.searchField;

            if ($("#searchInput").val()) {
                searchProfileFO.searchField = $("#searchInput").val();
            }

            updateProfilesTable(searchProfileFO);
        });
    </script>
</head>

<body>
<div>
    <div>
        <h2 align="center">Профили</h2>
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
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
