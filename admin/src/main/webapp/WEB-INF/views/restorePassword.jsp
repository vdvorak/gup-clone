<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
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
