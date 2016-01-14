<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 14.01.2016
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${blog.title} | Портал GUP</title>
</head>
<body>
<div>
  ${blog.title}
</div>
Рубрика
<%--<div>--%>
  <%--${blog.categories};--%>
<%--</div>--%>
<div>
  <c:choose>
    <c:when test="${not empty blog.imageId}">
      <img id="imgPreview" src="/api/rest/fileStorage/NEWS/file/read/id/${blog.imageId}" width="200"
           height="200">
    </c:when>
    <c:otherwise>
      <img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">
    </c:otherwise>
  </c:choose>
</div>
<div>
  Автор: ${username}
</div>
<div>
  Описание
  <br>
  ${blog.description}
</div>



</body>
</html>
