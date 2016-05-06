<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Zver
  Date: 05.01.2016
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>404</title>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" href="/resources/images/favicon.ico" />
        <link rel="stylesheet" href="/resources/css/bootstrap.css">
        <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
        <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
        <link rel="stylesheet" href="/resources/css/main.css">
        <link rel="stylesheet" href="/resources/css/font-awesome.css">
        <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

    </head>
    <body>

    <!--[if lt IE 8]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

    <jsp:include page="/WEB-INF/templates/common-header.jsp"/>

    <jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

    <jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

    <jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

        <div class="err">
            <i>404 <i>:(</i></i>
        </div>

    <sec:authorize access="isAuthenticated()">
        <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
    </sec:authorize>

    <jsp:include page="/WEB-INF/templates/footer.jsp"/>

    <jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

    <jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

    <script>
        var flag = '${flag}';
    </script>

    <jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

    <script src="/resources/js/top-news-block.js"></script>
    <script src="/resources/js/top-offers-block.js"></script>
    <script src="/resources/js/top-tenders-block.js"></script>
    <script src="/resources/js/top-projects-block.js"></script>

    </body>
</html>