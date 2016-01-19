<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        Дата создания: ${newsPost.createdDate}
    </div>
    <div>
        Просмотры: ${newsPost.views}
    </div>
    <div>
            ${newsPost.title}
    </div>
    <div>
            ${newsPost.text}
    </div>



    <%--<div>--%>
    <%--<h2>--%>
    <%--<a href="/blog-post/view/${tender.id}"> ${tender.title}</a>--%>
    <%--</h2>--%>
    <%--<span>--%>
    <%--<c:forEach var="id" items="${tender.uploadFilesIds.keySet()}">--%>
    <%--<img src="/api/rest/fileStorage/NEWS/file/read/id/${id}" width="200px" height="200px">--%>
    <%--</c:forEach>--%>
    <%--</span>--%>
    <%--</div>--%>
    <br>
    <hr>
</c:forEach>


</body>
</html>
