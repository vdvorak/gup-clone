<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 30.12.2015
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru-RU">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>GUP</title>
  <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
  <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
  <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
  <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
</head>
<body>


<sec:authorize access="isAuthenticated()" var="isAuthenticated">
  <jsp:include page="/WEB-INF/templates/authorizedHeader.jsp"/>
</sec:authorize>

<c:if test="${!isAuthenticated}">
  <jsp:include page="/WEB-INF/templates/headerAnonym.jsp"/>
</c:if>

<!--1st section with search-->
<section class="first-sec">
  <div class="logo-wrap">
    <a href="/index"><img src="/resources/img/logo-site.png"></a>
    <p class="logo-title">global ukrainian portal</p>
  </div>
  <div class="shop-wrap-right">
    <div class="shop-wrap">
      <a class="main-winStore" href="#"><img src="/resources/img/wins-icon.png"></a>
      <a class="main-googlePlay" href="#"><img src="/resources/img/goop-icon.png"></a>
      <a class="main-appStore" href="#"><img src="/resources/img/apps-icon.png"></a>
    </div>
    <div class="join-button-wrap">
      <div class="join-button">
        <a href="#" title="Вступить в организацию"><img src="/resources/img/join-button.png"></a>
      </div>
    </div>
  </div>
  <div class="main-search-button-wrapper">
    <input type="text" placeholder="Поиск">
    <a href="">Найти<span class="main-search-button-icon"><img src="/resources/img/magnifire.png"></span></a>
  </div>
</section>
<!-- END1st section -->



<!--3rd section news timeline-->
<section>
  <div class="tm-main-wrap">
    <div class="">
      <h2>СОЗДАНИЕ ТЕНДЕРА</h2>
    </div>
    <div class="tm-mainForm-wrap">


        <div>
          <p class="tm-tender-name">Введите название</p>
          <input type="text">
        </div>

       <!-- <div>
          <p class="tm-tender-nace">Выберите отрасль</p>
          <select>
            <option value="household">торговля (00.02.03)</option>
            <option value="electronics">услуги (00.05.01)</option>
            <option value="apartments">банковаская деятельность (00.28.09)</option>
          </select>
        </div>-->

        <div>
          <p class="tm-tender-term">Дата окончания тендера</p>
          <input type="date">
        </div>

        <div>
          <p>Укажите адресс</p>
        </div>

        <div>
          <p>Выберите область</p>
          <input class="tm-area" type="text">
        </div>

        <div>
          <p>Выберите город</p>
          <input class="tm-city" type="text">
        </div>

        <div>
          <img class="tm-tender-map" src="">
        </div>

        <div>
          <input type="radio" name="browser" value="open">Открытый<Br>
          <input type="radio" name="browser" value="close">Закрытый<Br>
        </div>

        <div>
          <p>Скрывать учасников тендера</p>
          <input type="checkbox">
        </div>

        <div>
          <p>Пригласить учасников тендера</p>
          <input class="tm-tender-members" type="text" placeholder="Название">
        </div>

        <div>
          <p>Ожидаемая стоимость</p>
          <input class="tm-price" type="number">
          <img class="tm-tender-currency" src="">
        </div>

        <div>
          <p>Номер тендера</p>
          <input id="text"  class="tm-number" type="text" inactive>
          <button id="txt">Нажми меня</button>
        </div>

        <div>
          <p>Описание</p>
          <textarea id="textarea"></textarea>
        </div>
        <div>
          <input type="file"  multiple accept="image/*,image/jpeg">
        </div>
        <button id="#submit" >Save</button>

    </div>
  </div>
</section>
<!--END 3rd section-->
<footer>

</footer>
<!-- hiden stuff-->
<jsp:include page="/WEB-INF/templates/authentification.jsp"/>
<!-- form itself -->

<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>
<script src="/resources/js/common.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="/resources/js/tender.js"></script>


<!--END of libs-->
<script>

  $('#txt').click(function(){
    alert( tinymce.activeEditor.getContent());
  });

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
      { title: 'Test template 1', content: 'Test 1' },
      { title: 'Test template 2', content: 'Test 2' }
    ],
    content_css: [
      '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
      '//www.tinymce.com/css/codepen.min.css'
    ]
  });
</script>
</body>
</html>