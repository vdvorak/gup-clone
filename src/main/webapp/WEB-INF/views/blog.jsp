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

<div class="right-tag">
    <c:forEach var="socLink" items="${blog.socLinks.entrySet()}">
        <c:choose>
            <c:when test="${socLink.getKey() == 'FACEBOOK'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="/resources/img/faceb-icon.png"></a>
            </c:when>
            <c:when test="${socLink.getKey() == 'VKONTAKTE'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="/resources/img/vk-icon.png"></a>
            </c:when>
            <c:when test="${socLink.getKey() == 'ODNOKLASSNIKI'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="#"></a>
            </c:when>
            <c:when test="${socLink.getKey() == 'LINKEDIN'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="/resources/img/link-icon.png"></a>
            </c:when>
            <c:when test="${socLink.getKey() == 'GOOGLEPLUS'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="/resources/img/goo-icon.png"></a>
            </c:when>
            <c:when test="${socLink.getKey() == 'YOUTUBE'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="#"></a>
            </c:when>
            <c:when test="${socLink.getKey() == 'TWITTER'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="/resources/img/twit-icon.png"></a>
            </c:when>
            <c:when test="${socLink.getKey() == 'SKYPE'}">
                <a class="faceb_soc_button" href="${socLink.getValue()}"><img src="#"></a>
            </c:when>
        </c:choose>
    </c:forEach>
</div>
<div>
    Автор: ${username}
</div>
<div>
    Описание
    <br>
    ${blog.description}
</div>

<c:if test="${check}">
    <a href="/blog-post/create/${blog.id}">
        <button>Создать новость</button>
    </a>
</c:if>
<a href="/blog-post/view-all/${blog.id}">
    <button>Все новости блога</button>
</a>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<script>

</script>
</body>
</html>
