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

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
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

    <body>

        <!--PAGE CONTENT START-->
        <div class="container2">
            <ul class="projectsVSInvestments-block">
                <li class="projectsVSInvestments-btn projects active" data-atab="projects" id="projectsTab"><a href="#tabs1-project">ПРОЕКТЫ</a></li>
                <!--Add "active" class to show this element as selected-->
                <li class="projectsVSInvestments-btn investments" data-atab="investments" id="investmentsTab"><a href="#tabs1-investment">ИНВЕСТИЦИИ</a></li>
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

            <div class="feedContainer investmentsFeedContainer contentContainer" id="investmentsContainer" style="display: none">
                <div id="investorPostsBlock">
                    <div class="feedItem vip">
                        <!--Add class "vip" to vip-tialize investment-->
                        <div class="publishDate">Опубликовано: 22. 10. 16</div>
                        <div class="photo border-color">
                            <img src="/resources/css/images/profileListLogo.png" alt="user avatar"/>
                        </div>
                        <a href="#" class="content">
                            <div class="title">Заголовок (ФИО/Компании)</div>
                            <div class="desc">Описание</div>
                            <p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                        </a>
                        <div class="cats">
                            <!--Recommended max 14 elements-->
                            <ul>
                                <!--Emmet shortcut-->
                                <!--li*14>a[href="#"]>{IT Ресурсы}-->
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                            </ul>
                        </div>

                        <div class="details">
                            <div class="canInvest">15468900$</div>
                            <div class="alreadyInvested">
                                <div class="desc">Проинвестировано:</div>
                                <div class="total">15468900$</div>
                                <div class="totalProjects">25 проектов</div>
                            </div>
                        </div>
                    </div>
                    <div class="feedItem">
                        <!--Add class "vip" to vip-tialize investment-->
                        <div class="publishDate">Опубликовано: 22. 10. 16</div>
                        <div class="photo">
                            <img src="/resources/css/images/profileListLogo.png" alt="user avatar"/>
                        </div>
                        <a href="#" class="content">
                            <div class="title">Заголовок (ФИО/Компании)</div>
                            <div class="desc">Описание</div>
                            <p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                        </a>
                        <div class="cats">
                            <!--Recommended max 14 elements-->
                            <ul>
                                <!--Emmet shortcut-->
                                <!--li*14>a[href="#"]>{IT Ресурсы}-->
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                            </ul>
                        </div>

                        <div class="details">
                            <div class="canInvest">15468900$</div>
                            <div class="alreadyInvested">
                                <div class="desc">Проинвестировано:</div>
                                <div class="total">15468900$</div>
                                <div class="totalProjects">25 проектов</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contentContainer" style="margin-top: 5px">
                <img class="projAndInvestCaretDown" id="showNext" src="/resources/images/caret.png" alt="caret">
            </div>

            <div class="feedFooter"></div>

        </div>

        <!--PAGE CONTENT END-->

        <sec:authorize access="isAuthenticated()">
            <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
        </sec:authorize>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>


        <jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

        <jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>
        <script src="/resources/js/main.js"></script>
        <script src="/resources/js/logo-section.js"></script>
        <script src="/resources/js/search-bar.js"></script>

        <script src="/resources/js/profileUtil.js"></script>
        <script src="/resources/js/projectsAndInvestmentsUtil.js"></script>
        <script src="/resources/js/projectList.js"></script>
        <script src="/resources/js/investorList.js"></script>
    </body>
</html>
