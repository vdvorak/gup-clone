<%--
  Created by IntelliJ IDEA.
  User: Комп2
  Date: 04.03.2016
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<button class="add_kved_button">ololo pew 3</button>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>

<script>

    kveds = [];

    $.ajax({
      type: "GET",
      url: "/resources/json/kved.json",
      dataType: "json",
      async: false,
      success: function (response) {
        kveds = response;
        alert(kveds.length);
      }
    });

    $(".add_kved_button").click(function(){
          $.ajax({
              type: "POST",
              url: "/api/rest/naceService/nace/create/all",
              contentType: "application/json; charset=utf-8",
              data: JSON.stringify(kveds),
              dataType: "json"
          });
      alert("fin2");
  });
</script>
</body>
</html>
