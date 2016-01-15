<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: RAYANT
  Date: 15.01.2016
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новости</title>
</head>
<body>
<h1>Новости</h1>
<br>

<c:forEach var="blogPost" items="${blogPostPages.entities}" >
  <div>
    <h2>
      <a href="/blog-post/view/${blogPost.id}"> ${blogPost.title}</a>
    </h2>
  <span>
  <c:forEach var="id" items="${blogPost.imagesIds.keySet()}" >
    <img src="/api/rest/fileStorage/NEWS/file/read/id/${id}" width="200px" height="200px">
  </c:forEach>
  </span>
  </div>
  <br>
</c:forEach>

</body>
</html>
