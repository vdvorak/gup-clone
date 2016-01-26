<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="first-sec">
  <%--<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">--%>
  <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <script>
    $(function() {
      $("#searchInput").autocomplete({
        source: function (request, response) {
          $.getJSON("${pageContext.request.contextPath}/search/autocomplete/profile", {
            term: request.term
          }, response);
        }
      });
    });

    $(document).on('click', '#searchButton', function () {
        window.location.href = '/profile/list?term=' + $("#searchInput").val();
    });
  </script>

  <div class="main-search-button-wrapper">
    <%--<form:form method="POST" commandName="profileFO" action="/profile/list">--%>
      <%--<form:input path="searchField" id="searchInput"/>--%>
      <%--<button type="submit" value="Find"></button>--%>
    <%--</form:form>--%>

    <input id="searchInput" type="text" placeholder="Имя пользователя или компании">
    <button id="searchButton">Найти<span class="main-search-button-icon"><img src="/resources/img/magnifire.png"></span></button>
  </div>


</section>