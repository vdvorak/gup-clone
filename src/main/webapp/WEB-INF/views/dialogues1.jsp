
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Диалоги</title>
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">

<div class="container-fluid">

    <!--gallery-->
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
        <h2> Диалоги </h2>
        <div class="col-xs-9" style="padding-left: 5px; padding-right: 5px;">

            <a href="/dialogue/create">Создать новый диалог</a>
            <br>
            <c:choose>
                <c:when test="${empty dialogues || dialogues.size()==0}">
                    <h3>У вас ещё нет диалогов</h3>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${dialogues}" var="dialogue">
                        <p><a href="/dialogue/id/${dialogue.id}"> dialogue subject: ${dialogue.subject} </a>
                        <p> unread messages: ${dialogue.unreadMsgCounter.get(userid)} </p>
                        <ul style="display: inline-table; list-style-type: none">
                            <li style="background-color: white">
                                <a rel="example_group" href="/dialogue/id/${dialogue.id}">
                                    <c:forEach items="${dialogue.members}" var="member" varStatus="status">
                                        <a rel="example_group"
                                           href="/dialogue/id/${member.id}">
                                            <!-- api/rest/fileStorage/PROFILE/file/read/id/${member.userPicId} -->
                                            <img alt="/resources/images/no_photo.jpg"
                                                 src="/resources/img/reallySmallUserpic.png"
                                                 width="150"
                                                 height="150" ${status.first ? '' : 'style = "display:none"'}>
                                        </a>
                                    </c:forEach>
                                </a>

                            </li>
                        </ul>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


        </div>
    </div>

    <div id="pages">

    </div>
    <!--gallery-->
</div>

<!-- script references -->
<script src="/resources/js/bootstrap.min.js"></script>

</body>
</html>

