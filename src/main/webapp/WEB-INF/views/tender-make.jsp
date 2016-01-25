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
    <title>Создание тендера</title>
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <link href="/resources/css/com.css" rel="stylesheet">
  <link href="/resources/css/bootstrap-datetimepicker.css" rel="stylesheet">
  <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" rel="stylesheet"
        type="text/css"/>
</head>
<body>
<h1>Создание тендера</h1>
<input id="areaInp" type="text" name="area" style="display: none">
<input id="cityInp" type="text" name="city" style="display: none">
<div class="row">
  <div class="col-xs-4">
    <input name="tenderNumber" type="text" class="form-control" placeholder="Введите номер тендера если он есть">
  </div>
  </div>
<div class="row">
  <div class="col-xs-2">
    <div class="radio">
      <label><input type="radio" id="open" name="optradio" checked>Открыйтый</label>
    </div>
  </div>
  <div class="col-xs-2">
    <div class="radio">
      <label><input type="radio" id="close" name="optradio">Закрытый</label>
    </div>
  </div>
</div>
<div class="row" id="members" style="display: none">
  <div class="col-xs-2">
    <input name="memberId" type="text" class="form-control">
  </div>
  <div class="col-xs-2">
    <button class="btn btn-default" id="add">Добавить участника</button>
  </div>
</div>
<br>
<div class="row">
  <div class="col-xs-4">
    <center id="membersList">
    </center>
  </div>
</div>






<div class="row">
  <div class="col-xs-4">
    <div class="input-group">

      <div class="col-xs-6" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите область<b class="caret"></b></a>
            <ul class="dropdown-menu multi-column columns-2">
              <div id="regions" class="row">
                <div class="col-sm-6">
                  <ul class="multi-column-dropdown">
                    <li><a role="menuitem" tabindex="-1" href="#"><b>Вся Украина</b></a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Винницкая область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Волынская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Донецкая область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Житомирская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Закарпатская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#" >Ивано‑Франковская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Киевская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Кировоградская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Крым</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Луганская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Львовская область</a></li>
                  </ul>
                </div>
                <div class="col-sm-6">
                  <ul class="multi-column-dropdown">
                    <li><a role="menuitem" tabindex="-1" href="#">Николаевская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Одесская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Полтавская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Ровенская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Сумская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Тернопольская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Харьковская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Херсонская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Хмельницкая область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Черкасская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Черниговская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Черновицкая область</a>
                    </li>
                  </ul>
                </div>
              </div>
            </ul>
          </li>
        </ul>
      </div>

      <div class="col-xs-6" id="bs-example-navbar-collapse-2" style="visibility: hidden">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a id="chosenCity" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите город<b
                    class="caret"></b></a>
            <ul class="dropdown-menu multi-column columns-2">
              <div id="cities" class="row">

                <div class="col-sm-6">
                  <ul id="cities1" class="multi-column-dropdown">
                  </ul>
                </div>

                <div class="col-sm-6">
                  <ul id="cities2" class="multi-column-dropdown">
                  </ul>
                </div>
              </div>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!-- city chosen -->



     <div class="row">
       <div class="col-xs-4">
         <center>Выберите дату окончания тендера</center>
         <input type='text' class="form-control" id='datetimepicker4' />
       </div>
     </div>



  <form id="photoInput" enctype="multipart/form-data" action="/api/rest/fileStorage/OFFERS/file/read/id/${id}"
        method="post">
    <p>Загрузите ваши фотографии на сервер</p>

    <p><input type="file" name="file" accept="image/*,image/jpeg">
      <input type="submit" value="Добавить"></p>
  </form>



<br>
<br>
<div class="imgBlock">
  <!--uploaded images-->
</div>

<div id="floating-panel">
  <input id="address" type="textbox" value="">
  <input id="submit" type="button" value="Сохранить">
</div>
<div id="map" style="height: 50%"></div>

<!-- script references -->
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/moment-with-locales.js" ></script>
<script src="/resources/js/bootstrap-datepicker.js" ></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"
        async defer></script>


<script>
  var imgsArr = new Object();
  var cities;
  var tender = {};
  var members = [];
  var type = 'OPEN';



 //--------------------   DATEPICKER ---------------------------------//
  $(function () {
    $('#datetimepicker4').datetimepicker({format: 'yyyy-mm-dd hh:ii'});
  });
//--------------------   END  DATEPICKER ---------------------------------//


//--------------------  RADIO CHECK ------------------------------------//

$('#open, #close').change(function(){
  var members = $('#members');
  var membersList = $('#membersList');
  if($('#open').prop('checked')){
    type = 'OPEN';
    members.attr("style", "display: none");
    membersList.attr("style", "display: none");

  }else{
    type = 'CLOSE';
    members.attr("style", "display: ");
    membersList.attr("style", "display: ");
  }
});

//--------------------   END RADIO CHECK ------------------------------------//



//-------------------- ADD MEMBER ------------------------------------------//

  $('#add').click(function(){
    var email = $('input[name="memberId"]').val();
    $.ajax({
      type: "POST",
      url: "/api/rest/profilesService/profile/email-check",
      data: {"email": email},
      success: function (data) {
        if (data === 'NOT FOUND'){
          alert("Нет пользователя с таким e-mail");
        }else{
          var member = {};
          member.id = data;
          member.name = email;
          var flag = true;
          for(var i = 0; i< members.length; i++){
            if (members[i].id === data) {
              flag = false;
              break;
            }
          }
          if(flag) {
            members.push(member);
            $('#membersList').append('<p name="'+email+'">'+email+'    <i name="'+email+'"class="icon-remove-sign"></i></p>');
            $('i[name="'+email+'"]').click(function(){
              $('p[name="'+email+'"]').detach();
              for(var i in members){
                if(members[i].name === email){
                  members.splice(i);
                }
              }
            });
          } else{
            alert("Собеседник уже добавлен!");
          }
        }
      }
    });
  });

  //-------------------- END ADD MEMBER ------------------------------------------//




  // ---------------    LOAD RESOURCES    --------------------------//

  $.ajax({
    type: "GET",
    url: "/resources/json/cities.json",
    async: false,
    success: function (response) {
      cities = response;
    }
  });

  // ---------------   END LOAD RESOURCES    --------------------------//





  //--------------------------- GOOGLE MAP API ---------------------------------------//

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

    document.getElementById('submit').addEventListener('click', function() {
      geocodeAddress(geocoder, map);
    });
  }

  function geocodeAddress(geocoder, resultsMap) {
    var address = document.getElementById('address').value;
    geocoder.geocode({'address': address}, function(results, status) {
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

  //--------------------------- END GOOGLE MAP API ---------------------------------------//



  // -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//

  $('#photoInput').submit(function (event) {
    event.preventDefault();
    var formImg = new FormData($(this)[0]);

    $.ajax({
      type: "POST",
      url: "/api/rest/fileStorage/NEWS/file/upload/",
      data: formImg,
      async: false,
      cache: false,
      contentType: false,
      processData: false,

      success: function (data, textStatus, request) {
        var id = data.id;
        imgsArr[id] = "someText";
        $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none">' +
        ' <li style="background-color: white"><a rel="example_group"> ' +
        '<img id="img1" alt="" src="/api/rest/fileStorage/NEWS/file/read/id/' + id + '"' + 'width="150" height="150"> ' +
        '</a> <div onclick=\"deleteImg(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
      }
    });
  });

  function deleteImg(idImg) {
    delete imgsArr[idImg];
    $.ajax({
      type: "POST",
      url: "/api/rest/fileStorage/NEWS/file/delete/id/" + idImg,
      success: function (data, textStatus, request) {
        $('#' + idImg).remove();
      }
    });
  }

  // -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//




  //--------------------------- REGIONS LIST --------------------------------------------//

  $('#regions').find('li').click(function () {
    var region = $(this).text();
    $('#chosenRegion').text(region);
    $('#areaInp').val(region);
    if (region !== 'Вся Украина') {
      $('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");
    } else {
      $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
    }
    $('#chosenCity').text("Выберите город");

    $('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();
    $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');
    for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {
      $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');
    }
    for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {
      $('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');
    }

    $('#cities').find('li').click(function () {
              var city = $(this).text();
              $('#chosenCity').text(city);
              $('#cityInp').val(city);
            }
    );
  });

  //--------------------------- END REGIONS LIST --------------------------------------------//

</script>


</body>
</html>
