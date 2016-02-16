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
    <input id="blogTitle" type="text" name="blogTitle" minlength="2" maxlength="70" required
           placeholder="Название блога">
</div>

<div>
    <textarea id="blogDescription" minlength="50" maxlength="5000" required
              placeholder="Описание блога"></textarea>

    <div id="textLength"></div>
</div>

<div id="drop_zone">
    <form id="photoInput" enctype="multipart/form-data" method="post">
        <input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">
    </form>
    Перетяните файлы сюда
    <div class="imgBlock">
        <img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">
    </div>

</div>

<div class="input-group">
    <div id="socLinkGroup" class="input_soc_wrap">
        <div class="left-tag">
            <p>Социальные сети</p>
        </div>
        <div class="right-tag">
            <a class="FACEBOOK"><img src="/resources/images/faceb-icon.png"></a>
            <a class="TWITTER"><img src="/resources/images/twit-icon.png"> </a>
            <a class="VKONTAKTE"><img src="/resources/images/vk-icon.png"></a>
            <a class="GOOGLEPLUS"><img src="/resources/images/goo-icon.png"></a>
            <a class="LINKEDIN"><img src="/resources/images/link-icon.png"></a>

            <div><input type="text" name="FACEBOOK" placeholder="Страница FACEBOOK"><span class="remove_field"><img
                    src="/resources/img/minus.png" width="15" height="15"></span>
            </div>
        </div>
    </div>
</div>

<button id="createBlog" disabled>Создать</button>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script>
    // -------------------------------------------------------BEGIN soc network links --------------------------------------------
    // Add/Remove social Input Fields Dynamically with jQuery
    $(document).ready(function () {
        var max_fields = 5; //maximum input boxes allowed
        var wrapper = $(".input_soc_wrap"); //Fields wrapper

        var x = 1;

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

        // ---------------------------------------------------- END Soc network links --------------------------------------

        // -------------------------------------------------------BEGIN drop zone ------------------------------------------

        var dropZone = document.getElementById('drop_zone');
        dropZone.addEventListener('dragover', handleDragOver, false);
        dropZone.addEventListener('drop', handleFileSelect, false);

        function handleFileSelect(evt) {
            evt.stopPropagation();
            evt.preventDefault();

            var files = evt.dataTransfer.files; // FileList object.
            if (files.length) {
                var formImg = new FormData();
                formImg.append('file', files[0]);
                // files is a FileList of File objects. List some properties.

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
            }
        }

        function handleDragOver(evt) {
            evt.stopPropagation();
            evt.preventDefault();
            evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
        }

        // ---------------------------------------------------- END Drop zone --------------------------------------
    });


    var imgId = '';
    var blog = {};

    $("#blogDescription").on('keyup', function (event) {
        var button = $('#createBlog');
        var counter = $("#textLength");

        var currentString = $("#blogDescription").val();
        counter.html(currentString.length);
        if (currentString.length <= 50) {  /*or whatever your number is*/
            button.attr("disabled", true);
            counter.css("color", "red");
        } else {
            if (currentString.length > 500) {
                button.attr("disabled", true);
                counter.css("color", "red");
            } else {
                button.attr("disabled", false);
                counter.css("color", "green");
            }
        }
    });
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


        blog.title = $('#blogTitle').val();
        blog.description = $('#blogDescription').val();
        blog.imageId = imgId;

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
            url: "/api/rest/newsService/blog/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blog),
            success: function (response) {
                window.location.href = '/blog/' + response.id;
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
