<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Создание исполнителя</title>
</head>
<body>
<div>
  <select id="naceIds" required>
    <option value="nace1">01.11 Вирощування зернових культур (крім рису), бобових культур і насіння олійних культур</option>
    <option value="nace2">01.12 Вирощування рису</option>
    <option value="nace3">01.13 Вирощування овочів та баштанних культур, коренеплодів та бульбоплодів</option>
    <option value="nace4">01.15 Вирощування тютюну</option>
    <option value="nace5">01.16 Вирощування прядивних культур</option>
  </select>
</div>
<div>
  <input id="doerTitle" type="text" name="doerTitle" minlength="2" maxlength="70" required
         placeholder="Название исполнителя">
</div>

<div>
    <textarea id="doerDescription"  minlength="50" maxlength="5000" required
              placeholder="Описание исполнителя"></textarea>
</div>

<div>
  <form id="photoInput" enctype="multipart/form-data" method="post">
    <input id="photofile" type="file" name="file" multiple accept="image/*,image/jpeg">
  </form>

  <div class="imgBlock">
    <img id="imgPreview" src="/resources/images/no_photo.jpg" width="200" height="200">
  </div>

</div>
<button id="createDoer">Создать</button>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script>

  var imgId = '';
  var doer = {};
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
  $(document).on('click', '#createDoer', function (event) {

    naceIds.push($('#naceIds').val());

    doer.title = $('#doerTitle').val();
    doer.body = $('#doerDescription').val();
    doer.imageId = imgId;
    doer.naceIds = naceIds;

//    alert(JSON.stringify(doer));

    $.ajax({
      type: "POST",
//      url: "/api/rest/doerService/doer/create",
      url: "/api/rest/doerService/doer/create/",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      data: JSON.stringify(doer),
      success: function (response) {
        window.location.href = '/index';
//               в перспективе должно перекидывать на страницу этого исполнителя - его просмотр
      },
      error: function (response) {
        alert("Внутренняя ошибка сервера");
      }
    });
  });
  ///------------------------- Upload Doer -----------------------------------------------
</script>
</body>
</html>
