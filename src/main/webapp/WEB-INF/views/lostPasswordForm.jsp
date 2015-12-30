<%--
  Created by IntelliJ IDEA.
  User: Zver
  Date: 28.12.2015
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/resetPassword" method="post">
        <input type="text" value="${token}" style="visibility: hidden;" name="token"/>
        <div>
            <label for="newPassword">Введите новый пароль:</label>
            <input type="password" id="newPassword" name="newPassword"/>
        </div>
        <div class="button">
            <button type="submit">Изменить пароль</button>
        </div>
    </form>
</body>