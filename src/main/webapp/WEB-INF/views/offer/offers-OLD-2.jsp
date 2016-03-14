<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Объявления</title>
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <link href="/resources/css/com.css" rel="stylesheet">
  <link href="/resources/css/bootstrap-datetimepicker.css" rel="stylesheet">
  <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="/resources/css/jquery-ui.css" rel="stylesheet">

</head>
<body>
<input id="areaInp" type="text" name="area" style="display: none">
<input id="cityInp" type="text" name="city" style="display: none">
<h1 align="center">ОБЪЯВЛЕНИЯ</h1>
<div class="row">
  <div class="col-xs-9">
<div class="row">
  <%--<div class="col-xs-2">--%>

  <%--</div>--%>
  <div class="col-md-4">
    <a href="/create-offer"><button>Создать объявление</button>  </a>
    <div class="input-group">

      <div class="col-xs-6" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите область<b
                    class="caret"></b></a>
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
                    <li><a role="menuitem" tabindex="-1" href="#">Ивано‑Франковская область</a></li>
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
    <input class="form-control" id="keyWords" type="text" placeholder="Ключевые слова">
  </div>

  <div class="col-md-8">
      <div class="col-xs-8" style="padding-left: 5px; padding-right: 5px;">
        <!--multilevel category-->
        <input id="category1inp" type="text" name="category1inp" style="display: none;">
        <input id="category2inp" type="text" name="category2inp" style="display: none;">
        <input id="category3inp" type="text" name="category3inp" style="display: none;">

        <div class="container" style="width: 700px;">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          </div>
          <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
              <li>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category1lvlText">Выберите категорию<b class="caret"></b></a>
                <ul id="category1lvl" class="dropdown-menu multi-level">
                </ul>
              </li>
              <li id="container2lvl" style="visibility: hidden">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category2lvlText">Выберите подкатегорию<b class="caret"></b></a>
                <ul id="category2lvl" class="dropdown-menu multi-level">
                </ul>
              </li>
              <li id="container3lvl" style="visibility: hidden">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="category3lvlText">Выберите подкатегорию<b class="caret"></b></a>
                <ul id="category3lvl" class="dropdown-menu multi-level">
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
        <!--multilevel category-->
        <br>

        <%--<a id="test" tabindex="0" class="btn btn-lg btn-danger" role="button" data-toggle="popover" data-trigger="focus" title="Dismissible popover" data-content="And here's some amazing content. It's very engaging. Right?">Dismissible popover</a>--%>
      </div>
    <button  id="submit" class="btn btn-info btn-block">Поиск</button>

  </div>
</div>
<h1 align="center">Результаты поиска</h1>
  <div class="row">
      <div id="result1" class="col-xs-3"></div>
      <div id="result2" class="col-xs-3"></div>
      <div id="result3" class="col-xs-3"></div>
      <div id="result4" class="col-xs-3"></div>
</div>
</div>

  <div class="col-xs-3">
      <div id="options"></div>

      <div id="inputs" class="input-group"></div>

      <div id="inptPrice" class="input-group" style="display: none">
        <div>Цена от<input id="priceMin" type="number" class="form-control"></div>
        <div>Цена до<input id="priceMax" type="number" class="form-control"></div>
      </div>
  </div>
  <div class="row">
    <div class="col-xs-4"></div>
    <div class="col-xs-4">
      <button  id="more" class="btn btn-info btn-block">Еще</button>
    </div>
    <div class="col-xs-4"></div>
  </div>
</div>
</body>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script>

  var filter = {skip: 0, limit: 12};
  var cities;
  var category1Id = '';
  var category2Id = '';
  var category3Id = '';
  var categoryResult = [];
  var parameters = [];
  var properties = [];
  var options;
  var jsonCategory;
  var jsonSubcategory;
  var skip = 12;

  // ---------------    LOAD RESOURCES    --------------------------//
  $(document).ready(function(){
    setFilterProperties();
    readAllByFilter();


  });

  $.ajax({
    type: "GET",
    url: "/resources/json/cities.json",
    dataType: "json",
    success: function (response) {
      cities = response;
    }
  });

  $.ajax({
    type: "GET",
    url: "/resources/json/searchCategories.json",
    dataType: "json",
    success: function (response) {
      jsonCategory = response;
    }
  });

  $.ajax({
    type: "GET",
    url: "/resources/json/searchSubcategories.json",
    dataType: "json",
    success: function (response) {
      jsonSubcategory = response;
    }
  });

  $.ajax({
    type: "GET",
    url: "/resources/json/searchValues.json",
    dataType: "json",
    success: function (response) {
      options = response;
    }
  });

  $.ajax({
    type: "GET",
    url: "/resources/json/parameters.json",
    dataType: "json",
    success: function (response) {
      parameters = response;
    }
  });

  // ---------------   END LOAD RESOURCES    --------------------------//


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

  //--------------------------------1-LVL CATEGORY-------------------------------------------------//
  $('#category1lvlText').click( function(){

    for (var i in jsonCategory) {
      $('#category1lvl').append('<li><a id="'+jsonCategory[i].id +'" role="menuitem" tabindex="-1" href="#">' + jsonCategory[i].name + '</a></li>');
    }
    //--------------------------------1-LVL CATEGORY CLICK-------------------------------------------------//
    $('#category1lvl').find('li').click(function () {

      $('#container2lvl').attr("style", "display: none");
      $('#container3lvl').attr("style", "display: none");
      isComplete = 0;
      category2Id = '';
      category3Id = '';
      category1Id = $(this).find('a').attr("id");
      var category1 = $(this).text();
      $('#category1lvlText').text(category1);
      $('#category1inp').val(category1);

      if (category1 !== 'Выберите категорию') {
        $('#container2lvl').attr("style", "display: ");
      } else {
        $('#container2lvl').attr("style", "display: none");
      }

      $('#category2lvl').empty();

      $('#category2lvlText').text("Выберите подкатегорию");

      for (var j in jsonCategory) {

        if (jsonCategory[j].name === category1) {
          for (var i in jsonCategory[j].children) {
            $('#category2lvl').append('<li><a id="' + jsonCategory[j].children[i].id + '" role="menuitem" tabindex="-1" href="#">' + jsonCategory[j].children[i].name + '</a></li>');
          }
          if (jsonCategory[j].children.length === 0){
            $('#container2lvl').attr("style", "display: none");
            isComplete = 1;
            drawOptions(category1Id);
          }else{
            erase(category1Id);
          }
        }}



//--------------------------------- 2-LVL CATEGORY ON CLICK ---------------------------------------------//

          $('#category2lvl').find('li').click(function () {
            $('#container3lvl').attr("style", "display: none");
            isComplete = 0;
            category3Id = '';
            category2Id = $(this).find('a').attr("id");
            var category2 = $(this).text();
            $('#category2lvlText').text(category2);
            $('#category2inp').val(category2);

            if (category2 !== 'Выберите подкатегорию' && jsonSubcategory[category2Id]!==undefined && jsonSubcategory[category2Id].children!==undefined) {
              $('#container3lvl').attr("style", "display: ");
            } else {
              $('#container3lvl').attr("style", "display: none");
            }

            $('#category3lvl').empty();

            $('#category3lvlText').text("Выберите подкатегорию");

            if(jsonSubcategory[category2Id]!==undefined && jsonSubcategory[category2Id].children!==undefined){
              for (var i in jsonSubcategory[category2Id].children){
                $('#category3lvl').append('<li><a id="'+i +'" role="menuitem" tabindex="-1" href="#">' + jsonSubcategory[category2Id].children[i].label + '</a></li>');
              }
              erase(category2Id);
            }else{
              isComplete = 1;
          drawOptions(category2Id);
        }



//------------------------------------------------3-LVL CATEGORY ON CLICK-----------------------------------//
        $('#category3lvl').find('li').click(function () {
          erase(category2Id);
          category3Id = $(this).find('a').attr("id");
          var category3 = $(this).text();
          $('#category3lvlText').text(category3);
          $('#category2inp').val(category3);
          isComplete = 1;
          drawOptions(category3Id);
        });
      });
    });
  });

  //-------------------------------- END 1-LVL CATEGORY-------------------------------------------------//

  //--------------------------------- END 2-LVL CATEGORY ON CLICK ------------------------------------------//

  //------------------------------------END 3-LVL CATEGORY ON CLICK-----------------------------------------//

  //--------------------------------- DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

  var drawOptions = function(id){
    $('#options').empty();
    for(var i in options){
      if(options[i]['c'][id]!==undefined){
        var name;
        for (j in options[i]['k']){
          name = j;
        }

        for (j in parameters){

          if (parameters[j]['parameter']['key'] === name && parameters[j]['parameter']['validators']['required'] === 1){
            $('#options').append('<div><select class="form-control" required name="'+name+'"  id="00'+i+'">'+ '</select></div>');
            break;
          }else{
            $('#options').append('<div><select class="form-control" name="'+name+'"  id="00'+i+'">'+ '</select></div>');
            break;
          }
        }

        $('#00'+i).on('change',function(){
          if(this.value === 'price'){
            $('#inptPrice').attr("style", "display: ");
          }else if (this.value === 'exchange' || this.value === 'arranged' || this.value === 'free') {
            $('#inptPrice').attr("style", "display: none");
          }
        });

        for ( var j in options[i]['v']){
          $('#00'+i).append('<option value = "'+j+'"  id ="'+ j +'">'+ options[i]['v'][j]+'</option>');
        }

      }
    }

    for ( j in parameters){
      if (parameters[j]['parameter']['type'] === "input" && parameters[j]['categories'][id]  !== undefined ){
        $('#inputs').append('<input class="form-control" id="'+ parameters[j]['parameter']['key'] +'" type="number" name="'+ parameters[j]['parameter']['key'] +'" placeholder="'+parameters[j]['parameter']['key']+'"/>');
      }
    }
  };

  //---------------------------- END DROW SELECT AND INPUTS FOR CATEGORY ------------------------------------//

  //------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//
  var erase = function(id){
    $('#options').empty();
    $('#inputs').empty();
    $('#inptPrice').attr("style", "display: none");
  };
  //------------------ DELETE SELECT AND INPUTS FOR CATEGORY IF IT CHENGES ------------------------------------//


  $('#submit').click(function () {
    filter = {};
    filter.skip = 0;
    filter.limit = 12;

    cleanResult();

    setFilterProperties();
    readAllByFilter();

  })

  $('#more').click(function () {
    filter.skip += skip;
    readAllByFilter();
  })

  function setFilterProperties() {
    categoryResult = [];
    properties = [];
    var keyWords = $('#keyWords').val();
    if (keyWords !== "")  filter.searchField = keyWords;

    if (category1Id !==''){
      categoryResult.push(category1Id)
    }
    if (category2Id !==''){
      categoryResult.push(category2Id)
    }
    if (category3Id !==''){
      categoryResult.push(category3Id)
    }
    filter.address = {};
    filter.address.country = 'Украина';

    var city = $('#cityInp').val();
    var area = $('#areaInp').val();

    if (city !== 'Выберите город' && city !== '' && city !== 'Все города') {
      filter.address.city = city;
    }

    if (area !== 'Вся Украина' && area !== 'Выберите область' && area !== '') {
      filter.address.area = area;
    }

    if ($('#inptPrice').attr('style') !== "display: none") {
      filter.fromPrice = $('#priceMin').val();
      filter.toPrice = $('#priceMax').val();
    } else {
      delete filter.fromPrice;
      delete filter.toPrice;
    }


    $('#options').find('select').each(function(){
      var prop = {};
      prop.key = this.name;
      prop.value = this.value;
      properties.push(prop);
    });

    $('#inputs').find('input').each(function(){
      var prop = {};
      prop.key = this.name;
      prop.value = this.value;
      properties.push(prop);
    });


    if (categoryResult.length > 0) filter.categories = categoryResult;
    if (properties.length > 0) filter.properties = properties;
    console.log(filter);
  }

  function readAllByFilter() {
    $.ajax({
      type: "POST",
      url: "/api/rest/offersService/offer/read/all",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      data: JSON.stringify(filter),
      success: function (response) {
//        alert(JSON.stringify(response));
        var count = 0;

        var col1 = $('#result1');
        var col2 = $('#result2');
        var col3 = $('#result3');
        var col4 = $('#result4');
        for (var i in response.entities){
          var picId = '/resources/images/no_photo.jpg';
          for (j in response.entities[i].imagesIds){
            picId = j;
            if(response.entities[i].imagesIds[j]==="pic1") {
              picId = j;
              break;
            }
          }
          var content = '<div>'+'<a rel="example_group" href="/offer/'+response.entities[i].id+'">' +response.entities[i].title+'</a></div>';
          if (picId !== '/resources/images/no_photo.jpg'){
            content +=  '<a rel="example_group" href="/offer/'+response.entities[i].id+'">' + '<img id="img1" alt="" src="/api/rest/fileStorage/OFFERS/file/read/id/' + picId + '"' + 'width="150" height="150"></a>';
          }else{
            content +=  '<a rel="example_group" href="/offer/'+response.entities[i].id+'">' + '<img id="img1" alt="" src="/resources/images/no_photo.jpg" width="150" height="150"></a>';
          }
          picId = '/resources/images/no_photo.jpg';
          switch (count%4){
            case 0:
              col1.append('<div>'+content+'</div><br>');
              count++;
              break;
            case 1:
              col2.append('<div>'+content+'</div><br>');
              count++;
              break;
            case 2:
              col3.append('<div>'+content+'</div><br>');
              count++;
              break;
            case 3:
              col4.append('<div>'+content+'</div><br>');
              count++;
              break;
          }
        }

      },
      error: function (response) {
        alert("Внутренняя ошибка сервера");
      }
    });
  }

  function cleanResult() {
    $('#result1').html("");
    $('#result2').html("");
    $('#result3').html("");
    $('#result4').html("");
  }


</script>

</html>
