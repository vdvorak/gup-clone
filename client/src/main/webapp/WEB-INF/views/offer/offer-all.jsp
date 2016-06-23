<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Объявления | Портал GUP</title>

    <title>Объявления на Gup</title>
    <meta name="description"
          content="Global Ukrainian Portal - твой украинский портал. Актуальные новости, объявления, интересные проекты и тендеры.">

    <meta property="og:title" content="Объявления на Gup"/>
    <meta property="og:description"
          content="Global Ukrainian Portal - актуальные новости, объявления, интересные проекты и тендеры."/>

    <meta property="og:type" content="other"/>
    <meta property="og:url" content="http://gup.com.ua/offers"/>
    <meta property="og:image" content="http://gup.com.ua/resources/css/images/brand.png"/>
    <meta property="og:site_name" content="Gup.com.ua - Global Ukrainian Portal"/>


    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link href="/resources/css/custom-new.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/dropdown-multicolumn.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
</head>
<body>
<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->
<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>
<!--END 2nd section -->

<div class="container2">

    <div id="cluster-map"></div>

    <div class="contentContainer" style="padding: 5px;">
        <a href="/create-offer">
            <button type="button" id="createProject" class="abutton">Создать объявление</button>
        </a>
    </div>

    <h2 id="h2-top-offers" style="display:none;">ТОП обьявлений</h2>

    <!-- offers not found -->
    <div id="offers-notFound" style="display:none;">
        <span>По Вашему запросу ничего не найдено.</span>
    </div>
    <!-- offers not found -->

    <ul class="notice-box">
    </ul>
    <!-- li pattern for clone -->
    <li id="li-offer-basic" style="display:none">
        <a href="#" class="image"><img src="/resources/images/no_photo.jpg" alt="">

            <p></p></a>

        <div class="priceButton"></div>
        <span></span>
    </li>
    <!-- li pattern for clone -->

    <div class="contentContainer" style="margin-top: 5px">
        <img class="projAndInvestCaretDown" id="btn-offers-more" src="/resources/images/caret.png" alt="caret">
    </div>

    <div class="feedFooter"></div>
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


<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true"></script>
<%--<script src="https://maps.googleapis.com/maps/api/js"></script>--%>



<script type="text/javascript" src="/resources/js/markerclusterer.js"></script>
<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/offers/offer-all.js"></script>

<script>

</script>


</body>
</html>
