<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 15.01.2016
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Блоги | Портал GUP</title>
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

<div>
    <a href="/blog-create"><button>Создать блог</button></a>
    <c:forEach var="blog" items="${blogPages.entities}">
        <h3>
            <a href="/blog/${blog.id}">${blog.title}</a>
        </h3>
        <div>
            <a href="/blog/${blog.id}">
                <c:choose>
                    <c:when test="${not empty blog.imageId}">
                        <img id="imgPreview" src="/api/rest/fileStorage/NEWS/file/read/id/${blog.imageId}" width="200"
                             height="200">
                    </c:when>
                    <c:otherwise>
                        <img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">
                    </c:otherwise>
                </c:choose>
            </a>
        </div>
        <br>
        <div>Описание<br>${blog.description}</div>
        <div>Дата создания<span class="date-create">${blog.createdDate}</span></div>
    </c:forEach>
</div>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
</body>
</html>
