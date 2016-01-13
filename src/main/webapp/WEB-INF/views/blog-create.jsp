<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 13.01.2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание блога</title>
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
    <input id="blogTitle" type="text" name="blogTitle" minlength="2" maxlength="70" required placeholder="Название блога">
</div>

<div>
    <input id="blogDescription" type="text" name="blogDescription" minlength="50" maxlength="5000" required placeholder="Описание блога">
</div>

<div>
    <form id="photoInput" enctype="multipart/form-data" method="post">
        <input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">
    </form>

    <div class="imgBlock">
        <img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">
    </div>

</div>
<button id="createBlog">Создать</button>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script>
    //----------------------------------------------------- Image form -----------------------------------------------
    var imgId = '';
    var categories = [];
    var blog = {};

    $(document).on('change', '#photofile', function (e) {
        alert("Азазаз");

        var formImg = new FormData($('#photoInput')[0]);

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
                alert("Азаза опять");
                imgId = data.id;
                $('#imgPreview').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
            }
        });
    });
    //----------------------------------------------------- Image form -----------------------------------------------


    ///----------------------Delete photo from  DB-----------------------------------------
    function deleteImgFromDB(picId) {
        alert("Сейчас удалим фотку");
        $.ajax({
            url: '/api/rest/fileStorage/NEWS/file/delete/id/' + picId,
            method: 'POST',
            success: function (response) {
                alert("Фото успешно удалено");
            },
            error: function (response) {
                alert("Удаление фотки зафейлилось");
            }
        });
    }
    ///----------------------Delete photo from  DB-----------------------------------------



///------------------------- Upload Blog -----------------------------------------------
    $(document).on('click', '#createBlog', function (event) {

        blog.categories = $('#categories').value;
        blog.title = $('#blogTitle').val();
        blog.description = $('#blogDescription').val();
        blog.imageId = imgId;

        alert(JSON.stringify(blog));

        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blog),
            success: function (response) {
                alert("Всё ок");
//                window.location.href = '/account';
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
