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
    <select id="categories" required>
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
           placeholder="Название блога">
</div>

<div>
    <textarea id="blogDescription"  minlength="50" maxlength="5000" required
              placeholder="Описание блога"></textarea>
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

    var imgId = '';
    var categories = [];
    var blog = {};



    //----------------------------------------- I N F O -------------------------------------------------------------
    <%--Макет расходиться с логикой бекенда, возможно логично будет сделать выбор рубрик (категори) через чекбоксы--%>
    //----------------------------------------- I N F O -------------------------------------------------------------



    //----------------------------------------------------- Image form -----------------------------------------------

    $(document).on('change', '#photofile', function (e) {

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
                imgId = data.id;
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

        categories.push($('#categories').val());

        blog.title = $('#blogTitle').val();
        blog.description = $('#blogDescription').val();
        blog.imageId = imgId;
        blog.categories = categories;

        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/create",
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
