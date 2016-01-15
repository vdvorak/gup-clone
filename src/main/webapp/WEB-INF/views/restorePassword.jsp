<%--
  Created by IntelliJ IDEA.
  User: Zver
  Date: 12.01.2016
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/sendLostPasswordEmail" method="post">
        <div>
            <label for="email">Email: </label>
            <input type="email" id="email" name="email"/>
        </div>
        <div class="button">
            <button type="submit">Далее</button>
        </div>
    </form>
</div>
</body>
</html>
