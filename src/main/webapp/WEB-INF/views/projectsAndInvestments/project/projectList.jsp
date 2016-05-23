<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Проекты | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/gup-custom-modal-window.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
</head>

<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<!--PAGE CONTENT START-->
<div class="container2">
    <div class="contentContainer" style="padding: 5px;">
        <button type="button" id="createProject" class="abutton">Создать свой проект</button>
        <button type="button" id="createInvestorPost" class="abutton">Сделать инвестицию</button>
    </div>

    <ul class="projectsVSInvestments-block">
        <li class="projectsVSInvestments-btn projects active" data-atab="projects" id="projectsTab"><a
                href="#tabs1-project">ПРОЕКТЫ</a></li>
        <!--Add "active" class to show this element as selected-->
        <li class="projectsVSInvestments-btn investments" data-atab="investments" id="investmentsTab"><a
                href="#tabs1-investment">ИНВЕСТИЦИИ</a></li>
    </ul>

    <div class="projectFeedContainer feedContainer contentContainer" id="projectsContainer">
        <div class="projectsVSInvestmentsCats" id="projectTypeBlock">
            <div class="catContainer" name="RENOVATION">
                <!--Add "selected" class to show this element as selected-->
                <div class="catLogo restruct"></div>
                <div class="catName">Реструктуризация</div>
            </div>
            <div class="catContainer" name="PROTOTYPE">
                <div class="catLogo prototype"></div>
                <div class="catName">Готовый прототип</div>
            </div>
            <div class="catContainer" name="PROJECT_ON_PAPER">
                <div class="catLogo paper"></div>
                <div class="catName">Проект на бумаге</div>
            </div>
            <div class="catContainer" name="KNOW_HOW">
                <div class="catLogo nouHau"></div>
                <div class="catName">Ноу-Хау</div>
            </div>
        </div>

        <div id="projectsBlock">

        </div>
    </div>

    <div class="feedContainer investmentsFeedContainer contentContainer" id="investmentsContainer"
         style="display: none">
        <div id="investorPostsBlock">
        </div>
    </div>
    <div class="contentContainer" style="margin-top: 5px">
        <img class="projAndInvestCaretDown" id="showNext" src="/resources/images/caret.png" alt="caret">
    </div>

    <div class="feedFooter"></div>

</div>


<!-- The Modal -->
<div id="cropperModal" class="cropper-modal">

    <!-- Modal content -->
    <div class="cropper-modal-content">
        <div class="cropper-modal-header">
            <span>ИНВЕСТИРОВАНИЕ В ПРОЕКТ</span>
        </div>
        <input id="investInput" placeholder="Сумма инвестирования">
        <%--<div class="cropper-modal-body drop_zone">--%>
        <%--<img id="cropper-image" src="/resources/images/no_photo.jpg" style="max-width: 100%">--%>
        <%--</div>--%>
        <div class="cropper-modal-footer">
            <button id="confirmInvest" class="cropper-btn cropper-btn-success">Ок</button>
            <button class="cropper-btn cropper-btn-cancel">Отмена</button>
        </div>
    </div>

</div>
<!-- End of the Modal -->

<!--PAGE CONTENT END-->

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

<script src="/resources/js/profileUtil.js"></script>
<script src="/resources/js/projectsAndInvestmentsUtil.js"></script>
<script src="/resources/js/projectList.js"></script>
<script src="/resources/js/investorList.js"></script>
</body>
</html>
