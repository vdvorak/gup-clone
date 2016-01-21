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
    <title>Блоги | Портал GUP</title>
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
        <div>Дата создания<span class="date-create"></span> ${blog.createdDate}</div>
    </c:forEach>
</div>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
</body>
</html>
