<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 19.01.2016
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru-RU">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>GUP</title>
        <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">

    </head>
    <body>
        <jsp:include page="/WEB-INF/templates/common-header.jsp"/>
        <jsp:include page="/WEB-INF/templates/authentification.jsp"/>

        <div>
            <div>
                <h2 align="center">Просмотр профиля</h2>
                <h2 align="center"><a href="/">Перейти на главную</a></h2>
            </div>
            <div>

                <div>
                    <img id="mainProfileImg" src="" width="200" height="200">
                </div>

                <div>
                    <button id="addProfileToContactList" onclick="addProfileToContactList()">Добавить к себе в контакты</button>
                </div>

                <div>
                    <label for="profileName"><b>Имя: </b></label>
                    <label id="profileName"></label>
                </div>

                <div>
                    <label for="aboutProfile"><b>О себе: </b></label>
                    <label id="aboutProfile"></label>
                </div>

                <div>
                    <label for="contactTable" id="contactLabel"><b>Контактные данные: </b></label>
                    <table id="contactTable" border="1" width="25%">
                        <thead>
                        <tr>
                            <th>Email</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
        <script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
        <script src="/resources/js/common.js"></script>
        <sec:authorize access="isAuthenticated()">
            <script src="/resources/js/autorizedHeader.js"></script>
        </sec:authorize>

        <script>
            $(document).ready(function () {
                $.ajax({
                    type: "POST",
                    url: "/api/rest/profilesService/profile/read/id/${profileId}",
                    success: function (profile) {
                        if (profile.imgId) {
                            $('#mainProfileImg').attr('src','/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId);
                        } else {
                            $('#mainProfileImg').attr('src','/resources/images/no_photo.jpg');
                        }

                        if (profile.username == null) {
                            $('#profileName').text("Безымянный");
                        } else {
                            $('#profileName').text(profile.username);
                        }

                        if (profile.contact != null && profile.contact.aboutUs != null) {
                            $('#aboutProfile').text(profile.contact.aboutUs);
                        } else {
                            $('#aboutProfile').text("Пользователь еще ничего на рассказал о себе");
                        }

                        if (profile.contact == null || profile.contact.contactEmails == null || profile.contact.contactEmails.length == 0) {
                            $('#contactLabel').append('Пользователь еще не добавил контактных email-ов');
                            $('#contactTable').hide();
                        } else {
                            for (var i = 0; i < profile.contact.contactEmails.length; i++) {
                                var row = $('<tr>');
                                row.append($('<td>').html(profile.contact.contactEmails[i]));

                                $('#contactTable').append(row);
                            }
                        }
                    },
                    statusCode: {
                        404: function() {
                            alert('Такого пользователя нет');
                            window.location.href = "/";
                        }
                    }
                });
            });

            function addProfileToContactList() {
                $.ajax({
                    type: "POST",
                    url: '/api/rest/profilesService/profile/id/${profileId}/myContactList/add',
                    success: function () {
                        alert('Пользователь добавлен в контакты')
                        location.reload();
                    }
                });

            }
        </script>
    </body>
</html>


