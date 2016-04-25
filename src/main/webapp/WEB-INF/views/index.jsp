<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Главная страница | GUP</title>
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

        <div class="container2">
			<jsp:include page="/WEB-INF/templates/top-news-block.jsp"/>

			<jsp:include page="/WEB-INF/templates/top-offers-block.jsp"/>

			<jsp:include page="/WEB-INF/templates/top-tenders-block.jsp"/>

			<jsp:include page="/WEB-INF/templates/top-projects-block.jsp"/>
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
