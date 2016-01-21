-<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 19.01.2016
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="newsPost" items="${news.entities}">
    <div>
        Дата создания: <span class="date-create">${newsPost.createdDate}</span>
    </div>
    <div>
        Просмотры: ${newsPost.views}
    </div>
    <div>
        <a href="/blog-post/view/${newsPost.id}">${newsPost.title}</a>
    </div>
    <div>
        <a href="/blog-post/view/${newsPost.id}">
            <c:forEach var="id" items="${newsPost.imagesIds.keySet()}" end="0">
                <img src="/api/rest/fileStorage/NEWS/file/read/id/${id}" width="200px" height="200px">
            </c:forEach>
        </a>
    </div>
    <div>
            ${newsPost.text}
    </div>
    <div>
        Колличество комментариев: ${newsPost.totalComments}
    </div>
    <div>
        Дизлайки: ${newsPost.totalDislikes}
    </div>
    <div>
        Лайки: ${newsPost.totalLikes}
    </div>

    <br>
    <hr>
</c:forEach>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>

<script>

</script>

</body>
</html>
