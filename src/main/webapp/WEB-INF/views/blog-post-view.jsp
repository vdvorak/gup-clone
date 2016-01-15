<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: RAYANT
  Date: 14.01.2016
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>${blogPost.title}</h1>
<h2> Категория: ${blogPost.text}</h2>
<h2> Страна: ${blogPost.address.country}</h2>
<h2> Область: ${blogPost.address.area}</h2>
<h2> Город: ${blogPost.address.city}</h2>
<br>
<h3>${blogPost.text}</h3>

<c:forEach var="id" items="${blogPost.imagesIds.keySet()}" >
<img src="/api/rest/fileStorage/NEWS/file/read/id/${id}" width="200px" height="200px">
</c:forEach>

<br>
<a href="/blog-post/edit/${blogPost.id}">редактировать</a>
<div></div>
</body>
</html>
