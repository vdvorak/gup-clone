<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru-RU">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>GUP</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">

        <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
    </head>

    <body>

        <jsp:include page="/WEB-INF/templates/common-header.jsp"/>

        Вступить в организацию

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>

        <jsp:include page="/WEB-INF/templates/authentification.jsp"/>

        <script src="/resources/libs/jquery-1.11.3.min.js"></script>
        <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
        <script src="/resources/libs/jquery.magnific-popup.min.js"></script>

        <script src="/resources/js/common.js"></script>

        <sec:authorize access="isAuthenticated()">
            <script src="/resources/js/autorizedHeader.js"></script>
        </sec:authorize>
    </body>
</html>