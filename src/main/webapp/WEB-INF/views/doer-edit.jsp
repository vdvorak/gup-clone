<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование исполнителя</title>
</head>
<body>
    <div>
        <select id="naceIds" required>
            <option value="nace1">01.11 Вирощування зернових культур (крім рису), бобових культур і насіння олійних
                культур
            </option>
            <option value="nace2">01.12 Вирощування рису</option>
            <option value="nace3">01.13 Вирощування овочів та баштанних культур, коренеплодів та бульбоплодів</option>
            <option value="nace4">01.15 Вирощування тютюну</option>
            <option value="nace5">01.16 Вирощування прядивних культур</option>
        </select>
    </div>
    <div>
        <input id="doerTitle" type="text" name="doerTitle" value="${doer.title}" minlength="2" maxlength="70" required
               placeholder="Название исполнителя">
    </div>

    <div>
    <textarea id="doerDescription" value="${doer.body}" minlength="50" maxlength="5000" required
              placeholder="Описание исполнителя"></textarea>
    </div>


    <div>
        <form id="photoInput" enctype="multipart/form-data" method="post">
            <input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">
        </form>

        <div class="imgBlock">
            <img id="imgPreview" src="/api/rest/fileStorage/DOER/file/read/id/${doer.imageId}" width="200" height="200"/>
        </div>

    </div>
    <p>
    <button id="updateDoer">Редактировать профиль исполнителя</button>
<p>
    <!-- private List<DoerClient> clients; -->
    <div>Ваши клиенты:
        <table>
            <c:forEach var="client" items="${doer.clients}">
                <td>
                    <tr>id: ${client.id}</tr>
                </td>
            </c:forEach>
        </table>
    </div>
    <div>Добавить клиента</div>

    <script src="/resources/libs/jquery-1.11.3.min.js"></script>
    <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
    <script>

        var imgId = '';
        var newDoer = {};
        var naceIds = [];


        //----------------------------------------------------- Image form -----------------------------------------------

        $(document).on('change', '#photofile', function (e) {

            var formImg = new FormData($('#photoInput')[0]);

            if (imgId !== '') {
                deleteImgFromDB(imgId);
            }

            $.ajax({
                type: "POST",
                url: "/api/rest/fileStorage/DOER/file/upload/",
                data: formImg,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data, textStatus, request) {
                    imgId = data.id;
                    $('#imgPreview').attr("src", "/api/rest/fileStorage/DOER/file/read/id/" + imgId);
                }
            });
        });
        //----------------------------------------------------- Image form -----------------------------------------------


        ///----------------------Delete photo from  DB-----------------------------------------
        function deleteImgFromDB(picId) {
            $.ajax({
                url: '/api/rest/fileStorage/DOER/file/delete/id/' + picId,
                method: 'POST',
                success: function (response) {
                },
                error: function (response) {
                }
            });
        }
        ///----------------------Delete photo from  DB-----------------------------------------

        ///------------------------- Upload Doer -----------------------------------------------
        $(document).on('click', '#updateDoer', function (event) {

            naceIds.push($('#naceIds').val());

            newDoer.id = "${doer.id}";
            newDoer.title = $('#doerTitle').val();
            newDoer.body = $('#doerDescription').val();
            newDoer.imageId = imgId;
            newDoer.naceIds = naceIds;

//    alert(JSON.stringify(doer));

            $.ajax({
                type: "POST",
                url: "/api/rest/doerService/doer/id/${doer.id}/update/",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(newDoer),
                success: function (response) {
                    window.location.href = '/doer/${doer.id}';
                }
            });
        });
        ///------------------------- Upload Doer -----------------------------------------------
    </script>
    </body>
</html>



