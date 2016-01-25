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
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                type: "POST",
                url: "/api/rest/profilesService/profile/read/id/${profileId}",
                success: function (profile) {
                    if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != '') {
                        $('#profileImg').attr('src','/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic);
                    } else {
                        $('#profileImg').attr('src','/resources/images/no_photo.jpg');
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

    </script>
</head>
<body>
    <div>
        <div>
            <h2 align="center">Просмотр профиля</h2>
            <%--<h2 align="center"><a href="/editProject/id/${projectId}">Редактировать проект</a></h2>--%>
            <h2 align="center"><a href="/">Перейти на главную</a></h2>
        </div>
        <div>

            <div>
                <img id="profileImg" src="" width="200" height="200">
            </div>

            <div>
                <label for="profileName"><b>Имя пользователя: </b></label>
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
</body>
</html>


