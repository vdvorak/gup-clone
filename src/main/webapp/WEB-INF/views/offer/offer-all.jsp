<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 14.01.2016
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${blog.title} | Портал GUP</title>
  <link href="/resources/css/main.css" rel="stylesheet" type="text/css">
  <link href="/resources/css/custom-new.css" rel="stylesheet" type="text/css">


  <link href="/resources/css/bootstrap.css" rel="stylesheet">

  <link href="/resources/css/com.css" rel="stylesheet">

  <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
  <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
  <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
  <link rel="stylesheet" href="/resources/css/font-awesome.css">
</head>
<body>
<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->
<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>
<!--END 2nd section -->

<div class="container2">

  
  <h2>ТОП обьявлений</h2>

  <ul class="notice-box">
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
  </ul>

  <ul class="notice-box">
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
    <li>
      <a href="#" class="image"><img src="/resources/images/pic.jpg" alt="">Заголовок обьявления</a>
      <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
      <span>Просмотров: 222</span>
    </li>
  </ul>


  <p>&nbsp;<br>&nbsp;</p>
  <p>&nbsp;<br>&nbsp;</p>
  <p>&nbsp;<br>&nbsp;</p>
  <p>&nbsp;<br>&nbsp;</p>
  <p>&nbsp;<br>&nbsp;</p>
  <p>&nbsp;<br>&nbsp;</p>
  <p>&nbsp;<br>&nbsp;</p>


</div>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<!-- Begin Social buttons js -->
<jsp:include page="/WEB-INF/templates/social-buttons-js.jsp"/>
<!-- End Social buttons js -->
<!-- script references -->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>

<script src="/resources/js/common.js"></script>
</body>
</html>
