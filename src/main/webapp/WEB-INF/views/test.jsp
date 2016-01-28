<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 28.01.2016
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
  <script>
    function init() {
      $("button:not(#show)").click(addTelephone);
      $("#show").click(showObj);
    }
    function deleteTel(e) {
    var el = $(e.currentTarget).parent();
      el.remove();
    }

    function addTelephone() {
      var e = $('<p><input type="text"><button>Del</button></p>');
      $("div").append(e);
      var c = e.children("button");
      c.click(deleteTel);
    }
    function showObj() {
      var result = {};
      var t = [];
      p = $("body").find("input");
      for (var i = 0; i < p.length; i++) {
        if (i = 0) {
          result.name = p[i].attr("value");
        } else if (i = 1) {
          result.email = p[i].attr("value");
        } else {
          t.push(p[i].attr("value"));
        }
      }
      result.tel = t;
      alert(JSON.stringify(p));
    }
    $(document).ready(init);
  </script>
</head>
<body>
  <div><p>
    Ваше имя
    <input type="text" >
  </p>
  <p>
    Ваш e-mail
    <input type="text">
  </p>
    <p>
      Телефон
      <input type="text">
    </p>
  </div>
  <button>Добавить телефон</button>
  <button id="show">Вывести</button>
</body>
</html>
