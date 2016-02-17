<%--
  Created by IntelliJ IDEA.
  User: Комп1
  Date: 17.02.2016
  Time: 15:52
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

<div class="clearfix"></div>

<form id="filterForm" action="#" role="form">
  <div class="filterContacts">
    <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter1" value="1" name="k"/><span></span></label>
    <label for="checkbox-filter1">Юр. лицо</label>
  </div>
  <div class="filterContacts">
    <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter2" value="1" name="k"/><span></span></label>
    <label for="checkbox-filter2">Физ. лицо</label>
  </div>
  <div class="filterContacts">
    <label class="label-checkbox"><input type="checkbox" class="greenCheckbox" id="checkbox-filter3" value="1" name="k"/><span></span></label>
    <label for="checkbox-filter3">ФОП</label>
  </div>
</form>

<div class="clearfix"></div>

<script>
  $(".search-img").click(function(){
    $(".search-img").toggleClass('trolol');
    $("#filterForm").slideToggle();
  });
</script>

</body>
</html>
