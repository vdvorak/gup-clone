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
Рубрика <span id="rubrics"></span>

<div>

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
<div>
    Автор: ${username}
</div>
<div>
    Описание
    <br>
    ${blog.description}
</div>

<c:if test="${check}">
    <button>Создать новость</button>
</c:if>


<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<script>

    var categories = '${blog.categories}'.replace('[', '').replace(']', '').replace(' ', '').split(','); // make array from string

    for (var i = 0; i < categories.length; i++) {

        var rubric = $('#rubrics');
        switch (categories[i]) {
            case 'world':
                rubric.append('Новости мира');
                break;
            case 'ukr':
                rubric.append('Новости Украины');
                break;
            case 'economic':
                rubric.append('Экономика');
                break;
            case 'sport':
                rubric.append('Спорт');
                break;
            case 'crime':
                rubric.append('Криминал');
                break;
            case 'science':
                rubric.append('Наука');
                break;
            default:
                rubric.append('Нет категории');
        }
    }

</script>

</body>
</html>
