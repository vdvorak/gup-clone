<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Исполнители | Портал GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
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

<div class="container2">

    <div class="contentContainer" style="padding: 5px;">
        <a href="/tender-make">
            <button type="button" id="createProject" class="abutton">Создать свой тендер</button>
        </a>
        <a href="/doer-create">
            <button type="button" id="createInvestorPost" class="abutton">Создать исполнителя</button>
        </a>
    </div>

    <div class="doers">
        <ul class="projectsVSInvestments-block">
            <li class="projectsVSInvestments-btn projects" data-atab="projects"><a href="#tabs1-tenders">ТЕНДЕРЫ</a>
            </li>
            <!--Add "active" class to show this element as selected-->
            <li class="projectsVSInvestments-btn investments" data-atab="investments"><a href="#tabs1-investment">ИСПОЛНИТЕЛИ</a>
            </li>
        </ul>


        <div id="tabs1-tenders">
            <div id="tenders-start-block" class="feedContainer tenders">
                <div class="feedItem build-item-wrap">
                    <!--Add class "vip" to vip-tialize tender-->
                    <a class="preview build-pic-wrap" href="#">
                        <img src="#" alt="project photo"/>
                        <span class="proposals build-proposal-count">Предложений: <span></span></span>
                        <span class="views build-veiws">Просмотров: <span></span></span>
                    </a>
                    <section class="content">
                        <div class="publishDate build-publish-date">Опубликовано: <span></span></div>
                        <!-- Добавь класс "visible" и ты узреешь номер -->
                        <span class="number visible">№ <span class="build-number"></span> </span>

                        <a class="build-name-wrap" href="#">
                            <div class="title build-name"></div>
                        </a>

                        <div class="text build-item-text"></div>
                    </section>
                    <div class="bottomContent">
                        <div class="participate">
                            <div class="clock">
                                <div class="time build-end"></div>
                            </div>
                            <a href="#" class="build-link-wrap">
                                <button type="button" class="abutton blue">Участвовать</button>
                            </a>
                        </div>
                        <div class="sum"><span class="build-sum"></span>₴</div>
                    </div>
                </div>
            </div>

            <img id="tenderNextPage" src="/resources/images/caret.png" alt="caret">
        </div>


        <div id="tabs1-investment">

            <section id="doers-start-block">

                <div class="doersFeed build-item-wrap-2">
                    <!-- если чувак вип, добавь сюда класс vip и ты увидишь магию -->
                    <section class="build-pic-wrap-2">
                        <a href="#">
                            <img src="/resources/images/doersLogo.png" alt="doersLogo">
                        </a>
                    </section>

                    <div class="artistData">
                        <ul>
                            <li>
                                <p>Дата создания: <span class="build-publish-date-2"></span></p>
                            </li>
                            <li>
                                <p>Дата обновления: <span class="build-publish-date-update-2"></span></p>
                            </li>
                            <li>
                                <p>Просмотров: <span class="build-veiws-2"></span></p>
                            </li>
                        </ul>
                    </div>
                    <section class="build-pic-wrap-2">
                        <a href="#">
                            <h2 class="build-name-2"></h2>
                        </a>
                    </section>

                    <p class="build-item-text-2"></p>

                    <div class="doersRang">
                        <div></div>
                        <div></div>
                        <button type="button">Добавить в клиенты</button>
                    </div>

                    <div class="clearfix"></div>

                    <div class="colNewsComments">
                        <div class="newsComments">
                            <div class="clearfix"></div>
                            <p class="newsCommentsHeader">КОММЕНТАРИИ</p>

                            <form action="#" role="form" id="newsCommentsForm">
                            <textarea name="newsFormComments" id="newsFormComments"
                                      placeholder="Введите свой комментарий" maxlength="2000" required></textarea>
                                <button type="submit" class="newsFormSubmit">Отправить</button>
                            </form>
                            <p id="chars">2000 символов осталось</p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>


            </section>

            <%--<button id="doerNextPage">Загрузить ещё исполнителей</button>--%>

            <img id="doerNextPage" src="/resources/images/caret.png" alt="caret">

        </div>
    </div>
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

<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
<script src="/resources/js/tendersDoersList.js"></script>

<script>
    $('.doers').easytabs({
        animate: false
    })
</script>



</body>
</html>
