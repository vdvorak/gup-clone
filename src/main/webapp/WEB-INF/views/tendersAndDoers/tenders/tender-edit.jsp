<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: RAYANT
  Date: 25.01.2016
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Редактирование тендера</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="/resources/images/favicon.ico" />

  <link rel="stylesheet" href="/resources/css/bootstrap.css">
  <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
  <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/font-awesome.css">
  <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
  <link rel="stylesheet" href="/resources/css/mini.css">
  <link rel="stylesheet" href="/resources/css/confirmDeleteAlert.css">
  <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
</head>
<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
  <div class="tenderMake">
    <h1>РЕДАКТИРОВАНИЕ ТЕНДЕРА</h1>
    <form id="tender-make-form" action="#">
      <label for="EnterTheTitle">Введите название</label>
      <input type="text" id="EnterTheTitle" required value="${tender.title}">

      <label for="selectKved">Выберите отрасль</label>
      <select id="selectKved" class="chosen" multiple data-placeholder="Выберите отрасль" style="width: 553px;">
      </select>

      <div class="clearfix"></div>

      <label for="tender-date" class="label-notRequered">Сроки</label>
      <div id="tender-date">
        <input type="text" id="tender-datepicker1" class="datepicker-input" placeholder="Дата начала"> - <input type="text" id="tender-datepicker2" class="datepicker-input" placeholder="Дата окончания">
        <span>Дата начала не должна превышать дату окончания</span>
      </div>
      <%--<p class="datePickPi">Сроки <input type="text" id="datepicker"></p>
      <p class="datePickPi">&nbsp;- <input type="text" id="datepicker2"></p>--%>

      <div class="clearfix"></div>

      <h2>Укажите адрес</h2>
      <div class="location">
        <label for="SelectArea" class="label-notRequered">Выберете область</label>
        <input type="text" id="SelectArea" value="${tender.address.area}">

        <div class="clearfix"></div>

        <label for="SelectCity" class="label-notRequered">Выберете город</label>
        <input type="text" id="SelectCity" value="${tender.address.area}">
      </div>
      <label>Тип</label>
      <div class="tenderRadio">
        <label><input class="input-tenderRadio" type="radio" value="open" name="k" data-type="OPEN"/><span></span></label><p>открытый</p>
        <label><input class="input-tenderRadio" type="radio" value="open" name="k" data-type="CLOSE"/><span></span></label><p>закрытый</p>
      </div>

      <div class="clearfix"></div>

      <div class="description">
        <label for="HideBidders">Скрывать участников тендера</label>
        <label><input type="checkbox" id="HideBidders" value="open" name="k"/><span></span></label>
        <%--<label for="InviteBidders">Пригласить участников тендера</label>--%>
        <%--<input type="text" id="InviteBidders" placeholder="Название">--%>
        <label for="HideContacts">Скрывать контактные данные</label>
        <input type="checkbox" id="HideContacts" value="open"/>

        <div class="clearfix"></div>

        <label for="selectParticipants" style="display: none">Пригласить участников тендера</label>
        <select id="selectParticipants" class="chosen" multiple data-placeholder="Участники тендера" style="display: none">
        </select>

        <div class="clearfix"></div>

        <label for="ExpectedValue">Ожидаемая стоимость</label>
        <input type="text" id="ExpectedValue" placeholder="456" value="${tender.expectedPrice}">
        <label for="TenderNumber">Номер тендера</label>
        <input type="text" id="TenderNumber" placeholder="XX12345678-90" value="${tender.tenderNumber}">

        <div class="clearfix"></div>

        <label for="Description">Описание</label>
        <textarea name="Description" id="Description"></textarea>
      </div>

      <div class="titleFile" data-title="Добавить изображение"><button type="submit" class="blogCreationSubmit" form="photoForm"></button></div>
      <img id="tender-btn-addDoc" src="/resources/images/clip.png" alt="clip">

      <div class="clearfix"></div>

      <div id="drop_zone">
        <ul id="tender-img-block" class="ul-img-container ul-img-container-green">
          <li class="li-containerIMG li-defaultIMG">
            <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
            <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
          </li>
        </ul>
        <ul id="tender-doc-block" class="ul-img-container ul-img-container-green">
          <li class="li-containerIMG li-defaultIMG">
            <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
            <img src="http://www.uzscience.uz/upload/userfiles/images/doc.png" alt="defaultIMG">
            <div></div>
          </li>
        </ul>
      </div>

      <button id="tender-btn-save" type="submit" form="tender-make-form">Сохранить</button>
      <button id="tender-btn-delete" type="button">Удалить</button>

      <div class="confirm" id="confirmTenderDelete" style="display: none">
        <h1>Подтвердите удаление</h1>
        <p>Объявление будет навсегда удалено</p>
        <button id="cancelTenderDelBtn" autofocus>Отмена</button>
        <button id="confirmTenderDelBtn">Удалить</button>
      </div>

      <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
        <input id="photoInput" type="file" style="display: none;" multiple="multiple">
      </form>

    </form>

  </div>
</div>

<sec:authorize access="isAuthenticated()">
  <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>

<script>

  var placeKey = 'ChIJBUVa4U7P1EAR_kYBF9IxSXY';
  var filesArr = getFiles();
  var picArrDel = [];
  var oldNace = '${tender.naceIds}'.slice(1, -1).split(", "); // make array from string


  //--------------------   BEGIN NACE ---------------------------------//
  if(!oldNace[0]) oldNace = [];

  $.when(loadNace).done(function(response){
    var select = $('#selectKved');
    for(var i = 0; i < response.length; i++) {
      var option = $('<option id="'+ response[i].id +'" value="'+ response[i].id +'">'+ response[i].id + ": " +response[i].name +'</option>');
      select.append(option);
    }
    if(oldNace.length) select.val(oldNace);
    $("#selectKved").chosen();
  });

  //--------------------   END NACE ---------------------------------//

  $(document).ready(function () {

    if('${tender.hidePropose}' === 'true') $('#HideBidders').prop( "checked", true );
    if('${tender.hideContact}' === 'true') $('#HideContacts').prop('checked', true);

    if ('${tender.begin}') $('#tender-datepicker1').datepicker("setDate", new Date('${tender.begin}'*1000));
    if ('${tender.end}') $('#tender-datepicker2').datepicker("setDate", new Date('${tender.end}'*1000));

    // place photo from received model on the page
    for(var i = 0; i < filesArr.length; i++) {
      if (filesArr[i].fileType === "IMAGE" || filesArr[i].fileType === "MAINIMAGE") {
        appendImg(filesArr[i]);
      } else {
        appendDoc(filesArr[i]);
      }
    }

    // Setup the dnd listeners.
    var dropZone = document.getElementById('drop_zone');
    dropZone.addEventListener('dragover', handleDragOver, false);
    dropZone.addEventListener('drop', handleFileSelect, false);

    function handleFileSelect(evt) {
      evt.stopPropagation();
      evt.preventDefault();

      var files = evt.dataTransfer.files; // FileList object.

      // files is a FileList of File objects. List some properties.
      for (var i = 0, f; f = files[i]; i++) {
        var fd = new FormData();
        fd.append('file', f);
        $.ajax({
          type: "POST",
          url: "/api/rest/fileStorage/TENDER/file/upload/",
          data: fd,
          async: false,
          cache: false,
          contentType: false,
          processData: false,

          success: function (data, textStatus, request) {
            if (filesArr.length < 15) {
              var file, id = data.id;

              if (f.type.substring(0, 5) === 'image') {
                file = {id: id, name: f.name, fileType: 'IMAGE', size: f.size};
                appendImg(file);
              } else {
                file = {id: id, name: f.name, fileType: 'DOCUMENT', size: f.size};
                appendDoc(file);
              }

              filesArr.push(file);
            }
          }
        });
      }
    }

    function handleDragOver(evt) {
      evt.stopPropagation();
      evt.preventDefault();
      evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }
  });


  $('.blogCreationSubmit').click(function(){
    $('#photoInput').trigger('click');
  });
  $('#tender-btn-addDoc').click(function(){
    $('#photoInput').trigger('click');
  });

  function appendImg(img) {
    $("#tender-img-block > .li-defaultIMG").css("display", "none");
    var cloneImg = $("#tender-img-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
    cloneImg.find('img')
            .attr("alt", "")
            .attr("src", '/api/rest/fileStorage/TENDER/file/read/id/' + img.id)
            .attr("id", img.id)
            .click(onClickSetMainImg);
    cloneImg.find('span')
            .click(deleteImg);

    if(img.fileType === 'MAINIMAGE') cloneImg.find('img').addClass("mainImg");

    cloneImg.appendTo('#tender-img-block');
  }

  function appendDoc(doc) {
    $("#tender-doc-block > .li-defaultIMG").css("display", "none");
    var cloneDoc = $("#tender-doc-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
    cloneDoc.find('img')
            .attr("id", doc.id);
    cloneDoc.find('div')
            .text(doc.name);
    cloneDoc.find('span')
            .click(deleteImg);
    cloneDoc.appendTo('#tender-doc-block');
  }

  function findFileIndexById(fileId) {
    for(var i = 0; i < filesArr.length; i++) {
      if(filesArr[i].id === fileId) return i;
    }
  }

  //--------------------   DATEPICKER ---------------------------------//

  //--------------------   END  DATEPICKER ---------------------------------//


  //----------------------  HTML EDITOR-------------------------------------//
  tinymce.init({
    selector: 'textarea',
    height: 500,
    theme: 'modern',
    plugins: [
      'advlist autolink lists link image charmap print preview hr anchor pagebreak',
      'searchreplace wordcount visualblocks visualchars code fullscreen',
      'insertdatetime media nonbreaking save table contextmenu directionality',
      'emoticons template paste textcolor colorpicker textpattern imagetools'
    ],
    toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    toolbar2: 'print preview media | forecolor backcolor emoticons',
    image_advtab: true,
    templates: [
      {title: 'Test template 1', content: 'Test 1'},
      {title: 'Test template 2', content: 'Test 2'}
    ],
    content_css: [
      '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
      '//www.tinymce.com/css/codepen.min.css'
    ],
    init_instance_callback : function(editor) {
      editor.setContent('${tender.body}');
    }
  });

  //---------------------- END  HTML EDITOR-------------------------------------//


  //--------------------  RADIO CHECK ------------------------------------//
  if(('${tender.type}') === "CLOSE") {
    $('.input-tenderRadio[data-type="CLOSE"]').prop("checked", true);
  } else {
    $('.input-tenderRadio[data-type="OPEN"]').prop("checked", true);
  }

  $('.input-tenderRadio').change(function () {
    var display = $('.input-tenderRadio[data-type="CLOSE"]').prop('checked') ? "" : "none";

    $('#selectParticipants_chosen').css("display", display);
    $('label[for="selectParticipants"]').css("display", display);
  });

  //--------------------   END RADIO CHECK ------------------------------------//

  //-------------------- ADD MEMBER ------------------------------------------//

//  $('#add').click(function () {
//    var email = $('input[name="memberId"]').val();
//    $.ajax({
//      type: "POST",
//      url: "/api/rest/profilesService/profile/email-check",
//      data: {"email": email},
//      success: function (data) {
//        if (data === 'NOT FOUND') {
//          alert("Нет пользователя с таким e-mail");
//        } else {
//          var member = {};
//          member.id = data;
//          member.name = email;
//          var flag = true;
//          for (var i = 0; i < members.length; i++) {
//            if (members[i].id === data) {
//              flag = false;
//              break;
//            }
//          }
//          if (flag) {
//            members.push(member);
//            $('#membersList').append('<p name="' + email + '">' + email + '    <i name="' + email + '"class="icon-remove-sign"></i></p>');
//            $('i[name="' + email + '"]').click(function () {
//              $('p[name="' + email + '"]').detach();
//              for (var i in members) {
//                if (members[i].name === email) {
//                  members.splice(i);
//                }
//              }
//            });
//          } else {
//            alert("Собеседник уже добавлен!");
//          }
//        }
//      }
//    });
//  });

  //-------------------- END ADD MEMBER ------------------------------------------//


  /*//--------------------------- GOOGLE MAP API ---------------------------------------//

  function initMap() {

    var input = document.getElementById('address');

    var options = {
      types: []
    };

    var autocomplete = new google.maps.places.Autocomplete(input, options);

    google.maps.event.addListener(autocomplete, 'place_changed', function () {
      var place = autocomplete.getPlace(); //получаем место
      console.log(place);
      console.log(place.name);  //название места
      console.log(place.id);  //уникальный идентификатор места
    });

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 17,
      center: {lat: 50.4501, lng: 30.523400000000038}
    });

    var geocoder = new google.maps.Geocoder();

    document.getElementById('submit').addEventListener('click', function () {
      geocodeAddress(geocoder, map);
    });
  }

  function geocodeAddress(geocoder, resultsMap) {
    var address = document.getElementById('address').value;
    geocoder.geocode({'address': address}, function (results, status) {
      placeKey = results[0].place_id;
      if (status === google.maps.GeocoderStatus.OK) {
        resultsMap.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: resultsMap,
          position: results[0].geometry.location
        });
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }

  //--------------------------- END GOOGLE MAP API ---------------------------------------//*/


  // -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//

  $('#photoInput').change(function (event) {
    event.preventDefault();

    var files = event.currentTarget.files;
    for (var i = 0, f; f = files[i]; i++) {
      var fd = new FormData();
      fd.append('file', f);
      $.ajax({
        type: "POST",
        url: "/api/rest/fileStorage/TENDER/file/upload/",
        data: fd,
        async: false,
        cache: false,
        contentType: false,
        processData: false,

        success: function (data, textStatus, request) {
          if (filesArr.length < 15) {
            var file, id = data.id;

            if (f.type.substring(0, 5) === 'image') {
              file = {id: id, name: f.name, fileType: 'IMAGE', size: f.size};
              appendImg(file);
            } else {
              file = {id: id, name: f.name, fileType: 'DOCUMENT', size: f.size};
              appendDoc(file);
            }

            filesArr.push(file);
          }
        }
      });
    }
  });

  function deleteImgFromDB(idImg) {
    $.ajax({
      type: "POST",
      url: "/api/rest/fileStorage/TENDER/file/delete/id/" + idImg,
      success: function (data, textStatus, request) {
      }
    });
  }

  function deleteImg() {
    var idImg = $(event.currentTarget).parent()
            .find('img')
            .attr('id');
    var block = $(event.currentTarget).parent().parent();
    $('#' + idImg).parent().remove();

    var numberImg = block.find('img').length;
    if(numberImg < 2) {
      block.find(".li-defaultIMG").css("display", "inline-block");
    }
    picArrDel.push(idImg);
  }

  function onClickSetMainImg(event) {
    var img = $(event.currentTarget);
    var id = img.attr("id");
    var isMain = img.hasClass("mainImg");
    var allImgs = $("#tender-img-block").find("img");
    for (var i =0; i < allImgs.length; i++) {
      var curImg = $(allImgs[i]);
      if (curImg.hasClass("mainImg")) {
        curImg.removeClass("mainImg");
      }
    }
    if(!isMain) img.addClass("mainImg");

    for(var i = 0; i < filesArr.length; i++) {
      if(filesArr[i].fileType === "MAINIMAGE") {
        filesArr[i].fileType = 'IMAGE';
      }
    }

    if(img.hasClass("mainImg")) {
      filesArr[findFileIndexById(id)].fileType = "MAINIMAGE";
    }
  }

  function checkMainImg() {
    var hasMainImg = false;

    for(var i = 0; i < filesArr.length; i++) {
      if(filesArr[i].fileType === "MAINIMAGE") {
        hasMainImg = true;
        break;
      }
    }

    if(!hasMainImg) {
      for(var i = 0; i < filesArr.length; i++) {
        if(filesArr[i].fileType === "DOCUMENT") continue;
        filesArr[i].fileType = "MAINIMAGE";
        break;
      }
    }
  }

  function getFiles() {
    var str = "${tender.files}".replace(/File/g, "");
    var objs = [];
    if(str !== "[]") {
      str = str.substr(1, str.length - 2);
      var parts = str.split('}, ');
      parts.forEach(function (p) {
        var props = p.match(/(?:[^,"]+|"[^"]*")+/g);
        var o = {};
        props.forEach(function (pr) {
          var ps = pr.split('=');
          var name = ps[0].replace(/{/g, '').trim();
          var value = ps[1].replace(/'/g, '').replace(/}/g, '').trim();
          o[name] = value;
        });
        objs.push(o);
      });
    }
    return objs;
  }

  // -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//

  //---------------------------- SUBMIT -----------------------------------------------------//
  function checkDateInDatepicker() {
    var dateFrom = $('#tender-datepicker1').datepicker('getDate');
    var dateTo = $('#tender-datepicker2').datepicker('getDate');
    if (dateFrom && dateTo) {
      dateFrom = new Date(dateFrom).getTime() / 1000;
      dateTo = new Date(dateTo).getTime() / 1000;
      if (dateFrom > dateTo) {
        $('#tender-date span').addClass('tender-active-tooltip');
        $("html,body").animate({ scrollTop: $('span.tender-active-tooltip').offset().top }, "fast");
        setTimeout(function () {
          $('#tender-date span').removeClass('tender-active-tooltip');
        }, 6000);
        return false;
      }
    }
    return true;
  }

  $('#tender-make-form').submit(function (event) {
    if(!checkDateInDatepicker()) return false;

    var body = tinymce.activeEditor.getContent();
    if(!body) {
      return false;
    }

    var imgsArrResult = [], picArrNew = [];
    for(var i = 0; i < filesArr.length; i++) {
      if(picArrDel.indexOf(filesArr[i].id) === -1) picArrNew.push(filesArr[i].id);
    }

    for (var i = 0; i < picArrNew.length; i++) {
      imgsArrResult.push(filesArr[findFileIndexById(picArrNew[i])]);
    }

    for(var i = 0; i < picArrDel.length; i++) {
      deleteImgFromDB(picArrDel[i]);
    }


    checkMainImg();

    var tender = {};
    tender.files = imgsArrResult;
    tender.title = $('#EnterTheTitle').val();
    tender.body = body;
    tender.tenderNumber = $('#TenderNumber').val();
    var dateBegin = $('#tender-datepicker1').datepicker( 'getDate' );
    var dateEnd = $('#tender-datepicker2').datepicker( 'getDate' );
    tender.begin = (dateBegin) ? dateBegin.getTime() / 1000 : null;
    tender.end = (dateEnd) ? dateEnd.getTime() / 1000 : null;
    tender.type = $('.input-tenderRadio:checked').attr("data-type");
    tender.expectedPrice = $('#ExpectedValue').val();
    tender.hidePropose =  $('#HideBidders').prop('checked');
    tender.hideContact =  $('#HideContacts').prop('checked');
    if (tender.type === 'CLOSE') {
      tender.members = [];
      var arrOpt = $('#selectParticipants').children();
      for(var i = 0; i < arrOpt.length; i++) {
        tender.members.push({
          id: $(arrOpt[i]).attr('value'),
          name: $(arrOpt[i]).text()
        })
      }
    }
    var naceIds = $('#selectKved').val();
    if(naceIds) tender.naceIds = naceIds;

    tender.address = {};
    //tender.address.googleMapKey = placeKey;
    tender.address.area = $('#SelectArea').val();
    tender.address.city = $('#SelectCity').val();

    $.ajax({
      type: "POST",
      url: "/api/rest/tenderService/tender/id/${tender.id}/update/",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(tender),
      dataType: "json",
      success: function (response) {
        window.location.href = '/tender/' + response.id;
      }
    });

    event.preventDefault();
    
  });
  //---------------------------- END SUBMIT -------------------------------------------------//
  //------------------ BEGIN DELETE TENDER ------------------------------------//
  $('#tender-btn-delete').on('click', function () {
    $("#confirmTenderDelete").show();
  });

  $('#cancelTenderDelBtn').on('click', function () {
    $("#confirmTenderDelete").hide();
  });

  $('#confirmTenderDelBtn').on('click', function (event) {
    event.preventDefault();

    $.ajax({
      type: "POST",
      url: "/api/rest/tenderService/tender/" + "${tender.id}" + "/delete",
      statusCode: {
        200: function () {
          window.location.href = '/tenders';
        }
      }
    });
  });
  //------------------ END DELETE TENDER ------------------------------------//

  // ---------------------------- BEGIN participants -------------------------------------------------//

  function getMembers() {
    var str = "${tender.members}".replace(/Member/g, "");
    var objs = [];
    if(str !== "[]") {
      str = str.substr(1, str.length - 2);
      var parts = str.split('}, ');
      parts.forEach(function (p) {
        var props = p.match(/(?:[^,"]+|"[^"]*")+/g);
        var o = {};
        props.forEach(function (pr) {
          var ps = pr.split('=');
          var name = ps[0].replace(/{/g, '').trim();
          var value = ps[1].replace(/'/g, '').replace(/}/g, '').trim();
          o[name] = value;
        });
        objs.push(o);
      });
    }
    return objs;
  }

    var select = $('#selectParticipants');

    var members = getMembers();
    for(var i = 0; i < members.length; i++) {
      select.append('<option value="' + members[i].id + '" selected>' + members[i].name + ' </option>');
    }

    select.chosen({width: '545px', display_selected_options: false});

    select.on('change', function() {
      select.children('option:not(:selected)').remove();
      select.trigger("chosen:updated");
    });

    $('.input-tenderRadio').change();

    var input = $("#selectParticipants_chosen ul.chosen-choices li.search-field input");

    input.autocomplete({
      source: function (request, response) {
        $.getJSON("/search/autocomplete/profile/ids", {
          term: request.term
        }, function(response) {
          var $search_param = input.val();
          select.children('option:not(:selected)').remove();
          for (var key in response) {
            if(!select.children('option[value="' + response[key].id +'"]').length) {
              select.append('<option value="' + response[key].id + '">' + response[key].username + ' </option>');
            }
          }
          select.trigger("chosen:updated");
          input.val($search_param);
        });
      }
    });

  //---------------------------- END participants -------------------------------------------------//

</script>
<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"--%>
        <%--async defer></script>--%>
</body>
</html>
