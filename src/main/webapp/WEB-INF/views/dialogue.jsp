<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fairy
  Date: 01.12.2015
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Страница диалога</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">

<!--Header-->
<jsp:include page="/WEB-INF/templates/header.jsp"/>
<!--Header-->

<div class="container-fluid">

    <!--left-->
    <div class="col-sm-6">


        <div class="row">
            <div class="col-xs-4">
                <div class="row">
                    <h2 style="color: #ff8e35;">Тема диалога: ${dialogue.subject}</h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-8">
                <div class="row" style="color: #495d9a;">
                    <p class="pull-left">Последнее
                        сообщение: ${dialogue.messages.get(dialogue.messages.size()-1).date}</p>

                </div>

            </div>
        </div>

        <div class="row">
            <div class="col-xs-4">
                <div class="row">
                    <p> количество участников: ${dialogue.members.size()}</p>

                    <p> количество сообщений: ${dialogue.messages.size()}</p>
                </div>
            </div>
        </div>

        <div class="panel panel-default" style="background-color: transparent; border-color: transparent;">
            <div class="panel-body">
                <c:if test="${dialogue.members.size() < 1}">
                    нет участников
                    <%--<li><img src="/resources/images/user_no_photo.jpg"></li>--%>
                    <li><img src="/resources/images/no_photo.jpg"></li>
                </c:if>
                <c:if test="${dialogue.members.size() > 0}">
                    <ul class="pgwSlideshow">
                        <c:forEach items="${dialogue.members}" var="member">
                            <a href="/profile/id/${member.id}">
                                <p>${member.name}</p>
                                <li><img alt="user photo"
                                         src="/api/rest/fileStorage/PROFILE/file/read/id/${member.userPicId}"></li>
                            </a>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${dialogue.messages.size() > 0}">
                    <table border="5">
                        <ul>
                            <tr>
                                <td>Текст:</td>
                                <td>Дата:</td>
                                <td>От кого:</td>
                            </tr>
                            <c:forEach items="${dialogue.messages}" var="mes">
                                <tr>
                                    <td>
                                        <li>${mes.message}</li>
                                    </td>
                                    <td>
                                        <li>${mes.date}</li>
                                    </td>
                                    <td>
                                        <li>${mes.authorId}</li>
                                    </td>
                                </tr>
                            </c:forEach>
                        </ul>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
    <!--/right-->
</div>
<div>
        <textarea id="newMsg" minlength="5" maxlength="5000" required
                  placeholder="Введите текст сообщения"></textarea>
</div>
<!--/container-fluid-->
<button id="addMsg">Отправить сообщение</button>

<!-- script references -->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<script>
    var msg = {};

    ///------------------------- Add msg -----------------------------------------------
    $(document).on('click', '#addMsg', function (event) {

        msg.message = $('#newMsg').val();

        $.ajax({
            type: "POST",
//      url: "/api/rest/doerService/doer/create",
            url: "/api/rest/dialogueService/dialogue/id/${dialogue.id}/message/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(msg),
            success: function (response) {
                window.location.href = '/dialogue/id/${dialogue.id}';
//              перекидывает на страницу этого диалога - его просмотр
            }
        });
    });
    ///------------------------- Add msg -----------------------------------------------
</script>

</body>
</html>

