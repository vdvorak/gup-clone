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
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
</head>

<body>
<div>
    <div>
        <h2 align="center">Просмотр проекта</h2>
    </div>
    <div>
        <div>
            <label for="projectName"><b>    Название: </b></label>
            <label id="projectName"></label>
        </div>
        <div>
            <label for="projectType"><b>    Тип: </b></label>
            <label id="projectType"></label>
        </div>
        <div>
            <label for="projectDescription"><b>     Описание: </b></label>
            <label id="projectDescription"></label>
        </div>
        <div>
            <label for="projectCreatedDate"><b>     Дата создания: </b></label>
            <label id="projectCreatedDate"></label>
        </div>
        <br>
        <form id="voteForm"  method="post"
              action="/api/rest/projectsAndInvestmentsService/project/id/${projectId}/vote/10">
            <input type="number" name="score" id="score" path="score" value="10" />
            <input type="submit" name="button" value="Проголосовать" />
        </form>

        <form id="commentForm"  method="post"
              action="/api/rest/projectsAndInvestmentsService/project/id/${projectId}/comment/create">
            <textarea name="comment" id="textarea" cols="45" rows="5"></textarea>
            <input type="submit" name="button" id="button" value="Отправить" />
        </form>

    </div>
</div>

<jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>

<script>

    $(document).ready(function () {


        $.ajax({
            type: "GET",
            url: "/api/rest/projectsAndInvestmentsService/project/id/${projectId}/read",
            success: function (projectData) {
                document.getElementById("projectName").innerHTML = projectData.projectName;
                document.getElementById("projectType").innerHTML = projectData.typeOfProject;
                document.getElementById("projectDescription").innerHTML = projectData.projectDescription;

                var createdDate = new Date(projectData.createdDate);
                data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();
                document.getElementById("projectCreatedDate").innerHTML = createdDate;
            }
        });
    });
</script>
</body>
</html>

