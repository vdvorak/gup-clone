<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 13.01.2016
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование блога</title>
</head>
<body>
<div>
    <select name="categories" required>
        <option value="ukr">Новости Украины</option>
        <option value="world">Мировые новости</option>
        <option value="economic">Экономика</option>
        <option value="sport">Спорт</option>
        <option value="crime">Криминал</option>
        <option value="science">Наука</option>
    </select>
</div>

<div>
    <input id="blogTitle" type="text" name="blogTitle" minlength="2" maxlength="70" required
           placeholder="Название блога" value="${blog.title}">
</div>

<div>
    <textarea id="blogDescription" minlength="50" maxlength="5000" required
              placeholder="Описание блога">${blog.description}</textarea>
</div>

<div>
    <form id="photoInput" enctype="multipart/form-data" method="post">
        <input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">
    </form>


    <div class="imgBlock">

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

</div>
<button id="createBlog">Сохранить</button>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script>

    var imgId = '';
    var oldImgId = '';
    var categories = [];
    var blog = {};
    var blogId = '${blog.id}';

    if ('${blog.imageId}'.length > 2) {
        oldImgId = '${blog.imageId}';
    }

    //----------------------------------------------------- Image form -----------------------------------------------
    $(document).on('change', '#photofile', function (e) {

        var formImg = new FormData($('#photoInput')[0]);

        if (oldImgId !== '') {
            deleteImgFromDB(oldImgId);
        }

        if (imgId !== '') {
            deleteImgFromDB(imgId);
        }

        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/upload/",
            data: formImg,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data, textStatus, request) {
                imgId = data.id;
                alert("id новой загруженной фотки: " + data.id);
                $('#imgPreview').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
            }
        });
    });
    //----------------------------------------------------- Image form -----------------------------------------------


    ///----------------------Delete photo from  DB-----------------------------------------
    function deleteImgFromDB(picId) {
        $.ajax({
            url: '/api/rest/fileStorage/NEWS/file/delete/id/' + picId,
            method: 'POST',
            success: function (response) {
            },
            error: function (response) {
            }
        });
    }
    ///----------------------Delete photo from  DB-----------------------------------------

    ///------------------------- Upload Blog -----------------------------------------------
    $(document).on('click', '#createBlog', function (event) {

        blog.categories = $('#categories').value;
        blog.title = $('#blogTitle').val();
        blog.description = $('#blogDescription').val();
        blog.id = blogId;

        if (imgId !== oldImgId) {
            blog.imageId = imgId;
        }

        if (imgId === '') {
            blog.imageId = oldImgId;
        }

        alert(JSON.stringify(blog));

        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/edit",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blog),
            success: function (response) {
                window.location.href = '/index';
//               в перспективе должно перекидывать на страницу этого блога - его просмотр
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    });
    ///------------------------- Upload Blog -----------------------------------------------
</script>
</body>
</html>