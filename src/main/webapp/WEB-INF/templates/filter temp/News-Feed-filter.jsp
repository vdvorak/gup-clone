<%--
  Created by IntelliJ IDEA.
  User: Комп1
  Date: 17.02.2016
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <link rel="stylesheet" href="resources/css/media-queries.css">
</head>
<body>

<div class="categories">
  <p>Рубрики</p>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/science.png" alt="science"></a>
    <a href="#">Наука и техника</a>
  </div>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/art.png" alt="art"></a>
    <a href="#">Искусство</a>
  </div>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/savor.png" alt="savor"></a>
    <a href="#">Светская жизнь</a>
  </div>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/Policy.png" alt="Policy"></a>
    <a href="#">Политика</a>
  </div>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/WorldAndSociety.png" alt="World and Society"></a>
    <a href="#">Мир и общество</a>
  </div>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/Economy.png" alt="Economy"></a>
    <a href="#">Економика</a>
  </div>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/Sport,hobby.png" alt="Sport, hobby"></a>
    <a href="#">Спорт, хобби</a>
  </div>
  <div class="categoriesItem">
    <a href="#"><img src="/resources/images/SocialNetwork.png" alt="Social network"></a>
    <a href="#">Соц.сети</a>
  </div>
</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>

<script>
  $(".search-img").click(function(){
    $(".search-img").toggleClass('trolol');
    $(".categories").slideToggle();
  });
</script>

</body>
</html>
