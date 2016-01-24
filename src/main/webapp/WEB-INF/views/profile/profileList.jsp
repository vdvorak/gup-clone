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
        var profileFO = {};
        var term = ${term};

        $(document).ready(function () {
            if (term != null) {
                profileFO.searchField = term;
            }

            profileFO.skip = ${pageNumber};
            profileFO.limit = 20;

            updateProfilesTable(profileFO);
        });

        $(function() {
            $("#tagsName").autocomplete({
                source: function (request, response) {
                    $.getJSON("${pageContext.request.contextPath}/search/autocomplete/profile", {
                        term: request.term
                    }, response);
                }
            });
        });

        function updateProfilesTable(profileFO) {
            var data;

            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/profilesService/profile/read/all",
                data: JSON.stringify(profileFO),
                success: function (response) {
                    data = response.entities;
                    var goToPageLinks = '';

                    $('#pageLabel').append((profileFO.skip + 1) + ' из ' + response.totalEntities);
//                    if (profileFO.skip > 0) {
//                        goToPageLinks += '<a href="/profile/list?pageNumber=' + (projectFO.skip - 1)  + '"> Назад </a>';
//                    }
//
//                    if (profileFO.skip < response.totalEntities && response.totalEntities/profileFO.limit > 1) {
//                        goToPageLinks += '<a href="/profile/list?pageNumber=' + (profileFO.skip + 1) + '"> Следующая </a>';
//                    }
//
//                    $('#goToPage').append(goToPageLinks);

                    for (var profile in data) {
                        if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != '') {
                            profile.contact.pic = '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '" width="100" height="100">';
                        } else {
                            profile.contact.pic = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                        }

                        if (profile.username == null) {
                            profile.username = '<a href="/profile/id/' + profile.id + '">' + 'Безымянный' + '</a>';
                        } else {
                            profile.username = '<a href="/profile/id/' + profile.id + '">' + profile.username + '</a>';
                        }

                        var row = $('<tr>');
                        row.append($('<td>').html(profile.contact.pic));
                        row.append($('<td>').html(profile.username));

                        $('#profilesTable').append(row);
                    }
                }
            });
        }

        function setProfileFO() {

        }

        <%--$(document).on('click', '#findProfilesButton', function (event) {--%>
            <%--$("#projectsTable").find("tr:not(:first)").remove();--%>
            <%--$("#paginationDiv").remove();--%>

            <%--var projectFO = {};--%>
            <%--projectFO.searchField = $("#tagsName").val();--%>
            <%--projectFO.skip = ${pageNumber};--%>
            <%--projectFO.limit = 20;--%>

            <%--updateProfilesTable(projectFO);--%>
        <%--});--%>
    </script>
</head>

<body>
<div>
    <div>
        <h2 align="center">Профили</h2>
    </div>
    <div align="center">
        <input id="tagsName" size="100" placeholder="Имя профиля">
        <button id="findProfilesButton">Найти профиль</button>
    </div>
    <%--<div id="paginationDiv">--%>
        <%--<label id="pageLabel"><b>Страница:</b> </label>--%>
        <%--<p align="left" id="goToPage"></p>--%>
    <%--</div>--%>
    <div>
        <table id="profilesTable" border="1" width="100%">
            <thead>
            <tr>
                <th>Фото</th>
                <th>Имя</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
