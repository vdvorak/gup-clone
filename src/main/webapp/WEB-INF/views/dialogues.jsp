<%--
  Created by IntelliJ IDEA.
  User: Fairy
  Date: 30.11.2015
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>Диалоги</title>
  <meta name="generator" content="Bootply"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <link href="/resources/css/com.css" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
  <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="collapse navbar-collapse">
    <ul class="nav navbar-nav">
      <li><a href="#">Моя страница</a></li>
      <li><a href="#">Друзья</a></li>
      <li><a href="#">Сообщения</a></li>
    </ul>
    <div class="col-sm-4 col-md-4 pull-right">
      <ul class="nav navbar-nav">
        <li><a href="#">Вступить в организацию</a></li>
        <li><a href="#">Баланс</a></li>
        <li><a href="#">Укр/Рус</a></li>
        <li><a href="/logout">Выход</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container-fluid">

  <!--category-->
  <div class="row" style="padding: 10px;">
    <div class="col-xs-3" style="padding-left: 5px; padding-right: 5px;">
      <a><img src="/resources/images/logo.png"></a>
    </div>
    <div class="col-xs-3"
         style="padding-left: 5px; padding-right: 5px; color: white; font-size: 25px;  margin-top: 30px; ">
      ПОРТАЛ<br>РОЗВИТКУ<br>УКРАЇНИ
    </div>
    <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

    </div>
    <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

    </div>
    <div class="col-xs-2 pull-right" style="padding-left: 5px; padding-right: 5px; margin-top: 60px;">

    </div>
  </div>
  <!--category-->

  <!--gallery-->
  <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">

    <div class="col-xs-9" style="padding-left: 5px; padding-right: 5px;">

      <c:choose>
        <c:when test="${empty dialogues || empty dialogues.entities || dialogues.entities.size()==0}">
          <h2>У вас ещё нет диалогов</h2>
        </c:when>
        <c:otherwise>
          <c:forEach items="${dialogues.entities}" var="dialogue">
            <ul style="display: inline-table; list-style-type: none">
              <li style="background-color: white">
                <a rel="example_group"
                   href="/dialogues/${dialogue.id}">
                  <c:forEach items="${dialogue.members}}" var="member" varStatus="status">

                    <a rel="example_group"
                       href="/dialogues/${member.id}">
                      <img alt="" src="/api/rest/fileStorage/PROFILE/file/read/id/${member.userPicId}"
                         width="150" height="150" ${status.first ? '' : 'style = "display:none"'}>
                    </a>
                  </c:forEach>
                      ${dialogue.subject}
                </a>

              </li>
            </ul>
          </c:forEach>
        </c:otherwise>
      </c:choose>

    </div>
  </div>

  <div id="pages">

  </div>
  <!--gallery-->

</div>

<!-- script references -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/pgwslideshow.js"></script>
<script type="text/javascript" src="/resources/js/jquery.simplePagination.js"></script>

<script>

  var cities;
  $.ajax({
    url: '/resources/json/cities.json',
    dataType: 'json',
    async: false,
    success: function(response) {
      cities = response;
    }
  });


  $(function () {
    $('#pages').pagination({
      cssStyle: 'light-theme',
      currentPage: ${pageNumber},
      pages: ${pages}
    });
    var region = $('#chosenRegion').text();
    if (region === 'Выберите область' || region === 'Вся Украина'){
      $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
    }

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
            }
    );
  });

  $('#regions').find('li').click(function () {
            var region = $(this).text();
            $('#chosenRegion').text(region);
            if (region !== 'Вся Украина') {
              $('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");
            } else {
              $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
            }

            $('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();
            $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');
            for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {
              $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');
            }
            for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {
              $('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');
            }
            $('#chosenCity').text('Выберите город');
            $('#cities').find('li').click(function () {
                      var city = $(this).text();
                      $('#chosenCity').text(city);
                    }
            );
          }
  );


  var storedHash = window.location.hash;
  window.setInterval(function () {
    var getParams = {};
    getParams.minPrice = $('#minPrice').val();
    getParams.maxPrice = $('#maxPrice').val();
    getParams.search = $('#search').val();
    var chosenRegion = $('#chosenRegion').text();
    if (chosenRegion === 'Выберите область' || chosenRegion === 'Вся Украина'){
      chosenRegion = "";
    }
    getParams.chosenRegion = chosenRegion;
    var chosenCity = $('#chosenCity').text();
    if (chosenCity === 'Выберите город'){
      chosenCity = "";
    }
    getParams.chosenCity = chosenCity;
    if (window.location.hash != storedHash) {
      storedHash = window.location.hash;
      window.location.href = "/offers/" + storedHash.substring(6) + "?" + $.param(getParams);
    }
  }, 100);

  var goSearch = function () {
    var getParams = {};
    getParams.minPrice = $('#minPrice').val();
    getParams.maxPrice = $('#maxPrice').val();
    getParams.search = $('#search').val();
    var chosenRegion = $('#chosenRegion').text();
    if (chosenRegion === 'Выберите область' || chosenRegion === 'Вся Украина'){
      chosenRegion = "";
    }
    getParams.chosenRegion = chosenRegion;
    var chosenCity = $('#chosenCity').text();
    if (chosenCity === 'Выберите город'){
      chosenCity = "";
    }
    getParams.chosenCity = chosenCity;
    if (storedHash.substring(6) === "") {
      window.location.href = "/offers/1?" + $.param(getParams);
    } else {
//      alert($.param(getParams));
      window.location.href = "/offers/" + storedHash.substring(6) + "?" + $.param(getParams);
    }
  };
</script>
</body>


</html>

