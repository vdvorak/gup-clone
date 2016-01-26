<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Комп2
  Date: 26.01.2016
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <title>Редактирование исполнителя</title>
</head>
<body>
<p><form method="post" action="/profile">


  <p><input type="hidden" name="id" value="${doer.id}" />
  <p><input type="hidden" name="authorId" value="${doer.authorId}" />

  <p> <h4>Title </h4><input type="text" name="title" value="${doer.title}" autofocus required/>

  <p> <h4>Body </h4><input type="text" name="body" value="${doer.body}" autofocus required/>

   <p><input type="submit" name="commit" value="Change your profile"></p>

</form>
<!-- script references -->
<script src="/resources/js/jquery.min.js"></script>

</body>
</html>
