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


<div class="input-group">
    <div id="socLinkGroup" class="input_soc_wrap">
        <div class="left-tag">
            <p>Социальные сети</p>
        </div>
        <div class="right-tag">
            <a class="FACEBOOK"><img src="/resources/img/faceb-icon.png"></a>
            <a class="TWITTER"><img src="/resources/img/twit-icon.png"> </a>
            <a class="VKONTAKTE"><img src="/resources/img/vk-icon.png"></a>
            <a class="GOOGLEPLUS"><img src="/resources/img/goo-icon.png"></a>
            <a class="LINKEDIN"><img src="/resources/img/link-icon.png"></a>
            <c:forEach var="socLink" items="${blog.socLinks.entrySet()}">
                <c:choose>
                    <c:when test="${socLink.getKey() == 'FACEBOOK'}">
                        <div><input type="text" name="FACEBOOK" value="${socLink.getValue()}"><span
                                class="remove_field"><img
                                src="/resources/img/minus.png" width="15" height="15"></span>
                        </div>
                    </c:when>
                    <c:when test="${socLink.getKey() == 'VKONTAKTE'}">
                        <div><input type="text" name="VKONTAKTE" value="${socLink.getValue()}"><span
                                class="remove_field"><img
                                src="/resources/img/minus.png" width="15" height="15"></span>
                        </div>
                    </c:when>
                    <c:when test="${socLink.getKey() == 'LINKEDIN'}">
                        <div><input type="text" name="LINKEDIN" value="${socLink.getValue()}"><span
                                class="remove_field"><img
                                src="/resources/img/minus.png" width="15" height="15"></span>
                        </div>
                    </c:when>
                    <c:when test="${socLink.getKey() == 'GOOGLEPLUS'}">
                        <div><input type="text" name="GOOGLEPLUS" value="${socLink.getValue()}"><span
                                class="remove_field"><img
                                src="/resources/img/minus.png" width="15" height="15"></span>
                        </div>
                    </c:when>
                    <c:when test="${socLink.getKey() == 'TWITTER'}">
                        <div><input type="text" name="TWITTER" value="${socLink.getValue()}"><span class="remove_field"><img
                                src="/resources/img/minus.png" width="15" height="15"></span>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>
        </div>
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
    var socialSizeOld = ${blog.socLinks.size()} +0;

    if ('${blog.imageId}'.length > 2) {
        oldImgId = '${blog.imageId}';
    }

    // -------------------------------------------------------BEGIN soc network links --------------------------------------------
    // Add/Remove social Input Fields Dynamically with jQuery
    $(document).ready(function () {
        var max_fields = 5; //maximum input boxes allowed
        var wrapper = $(".input_soc_wrap"); //Fields wrapper
        var x = 1;

        if (socialSizeOld < 1) {
            x = socialSizeOld;
        }

        $(".input_soc_wrap a").click(function (e) {
            e.preventDefault();
            var socName = $(this).attr("class");

            if (x < max_fields) { //max input box allowed
                x++; //text box increment
                $(wrapper).append('<div><input name="' + socName + '" type="text" placeholder = "Страница ' + socName + '"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png" width="15" height="15"></a></div>');
            }
        });

        $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            x--;
        })
    });
    // ---------------------------------------------------- END Soc network links --------------------------------------


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
        blog.id = blogId;
        blog.categories = categories;

        if (imgId !== oldImgId) {
            blog.imageId = imgId;
        }

        if (imgId === '') {
            blog.imageId = oldImgId;
        }

        var socArr = {};
        $(".input_soc_wrap input").each(function (index) {
            var socName = $(this).attr("name");
            var url = $(this).val();

            var entity = {};
            if (url.length > 0) {
                socArr[socName] = url;
            }
        });

        blog.socLinks = socArr;

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