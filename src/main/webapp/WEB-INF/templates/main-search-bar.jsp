<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 20.01.2016
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="first-sec">
  <div class="main-search-button-wrapper">
    <input id="tagsName" type="text" placeholder="Введите имя пользователя или компании">
    <a href="">Найти<span class="main-search-button-icon"><img src="/resources/img/magnifire.png"></span></a>
  </div>
  <%--<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">--%>
  <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <script>
    $(function() {
      $("#tagsName").autocomplete({
        source: function (request, response) {
          $.getJSON("${pageContext.request.contextPath}/search/profile", {
            term: request.term
          }, response);
        }
      });
    });
  </script>
</section>