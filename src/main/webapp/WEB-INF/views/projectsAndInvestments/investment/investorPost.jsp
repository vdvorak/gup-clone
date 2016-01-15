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
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>GUP - Проекты</title>
</head>

<body>

<div>
    <div>
        <h2 align="center">Просмотр публикации инвестора</h2>
        <h2 align="center"><a href="/investorPost/list?pageNumber=0">Посмотреть другие публикации</a></h2>
    </div>
    <div>

        <div>
            <label for="investorPostDescription"><b>Описание: </b></label>
            <label id="investorPostDescription"></label>
        </div>

        <div>
            <label for="amountOfMoney"><b>Сумма инвестирования: </b></label>
            <label id="amountOfMoney"></label>
        </div>

        <div>
            <label for="investorPostCreatedDate"><b>Дата создания: </b></label>
            <label id="investorPostCreatedDate"></label>
        </div>

        <%--<div>--%>
            <%--<textarea name="comment" id="comment" cols="40" rows="5"--%>
                      <%--placeholder="Минимум 5 символов"></textarea>--%>
            <%--<button type="button" id="commentButton">Комментировать</button>--%>
        <%--</div>--%>
    </div>
</div>

<jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>
<script>
    var investorPostId = '';
//    var comment = {};

    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "/api/rest/projectsAndInvestmentsService/investorPost/id/${investorPostId}/read",
            success: function (investorPostData) {
                projectId = investorPostData.id;

                $('#investorPostDescription').text(investorPostData.description);
                $('#amountOfMoney').text(investorPostData.amountOfMoney);

                var createdDate = new Date(investorPostData.createdDate);
                $('#investorPostCreatedDate').text(createdDate.getDate() + '/'
                        + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear());

            }
        });
    });


//    $(document).on('click', '#commentButton', function (event) {
//
//        comment.toId = projectId;
//        comment.comment = $('#comment').val();
//        $.ajax({
//            type: "POST",
//            url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/comment/create",
//            contentType: "application/json; charset=utf-8",
//            dataType: "json",
//            data: JSON.stringify(comment),
//            success: function () {
//                alert('Вы прокомментировали проект');
//                window.location.reload();
//            },
//            error: function (response) {
//                alert("Внутренняя ошибка сервера");
//            }
//        });
//    });

</script>
</body>
</html>

