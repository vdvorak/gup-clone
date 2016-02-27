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

    <!--PAGE CONTENT START-->

    <div class="container2">
        <!-- Add "vip" class for "vip" users :3 -->
        <div class="contentContainer">
            <div class="projectContent">
                <div class="topSection">
                    <div class="statInfo">
                        <div class="publish" id="projCreatedDate">Опубликовано: </div>
                        <div class="views" id="projViewsNum">Просмотров: </div>
                    </div>
                </div>
                <div class="contentHeader">
                    <div class="leftSec">
                        <div class="author" id="authorName"></div>
                        <img class="mainPhoto" id="projCreatorPhoto" src="" alt="mainPhoto">
                    </div>
                    <div class="rightSec">
                        <div class="name" id="projName"></div>
                        <div class="additionalPhotos" id="projImages"></div>
                    </div>
                </div>
                <div id="investBlock" class="bottomSection">
                    <button type="button" class="abutton blue invest">Инвестировать</button>
                    <div class="projectProgressBlock">
                        <div class="current elem cash" id="investedAmount"></div>
                        <div class="bar elem">
                            <div class="colored"></div>
                            <div class="empty" id="projProgress" style="width: 100%;"></div>
                            <!--Change style width percentage to vizualize progress (INVERTED). Допускаються значення квантовані по 5 процентів, тобто типу такі: 0, 5, 10, 15, 20, ...-->
                        </div>
                        <div class="todo elem cash" id ="requestedAmount"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="description">
                    <div class="title">Описание</div>
                    <div class="text" id="projText"></div>
                </div>
                <div class="commentsSection">
                    <div class="count" id="commentsNum">Комментарии: </div>
                </div>
                <div class="clearfix"></div>

                <div class="downComments"><p>Комментировать</p></div>

                <div class="projectRating">
                    <div class="likes">
                        <div class="hearthPlace">
                            <div class="hearth"></div>
                        </div>
                        <div class="number">22 000</div>
                    </div>
                    <button type="button" class="abutton blue vote">Проголосовать</button>
                    <select class="ratingSelector">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>

                <div class="clearfix"></div>

                <div class="colNewsComments">
                    <div class="newsComments">
                        <div class="clearfix"></div>
                        <p class="newsCommentsHeader">Комментарий</p>
                        <div id="projectsCommentsForm" class="projectsCommentsForm">
                            <textarea name="projectsFormComments" id="projectsFormComments" class="projectsFormComments" placeholder="Введите свой комментарий" maxlength="1000" required></textarea>
                            <button id="sendProjComment" class="newsFormSubmit">Отправить</button>
                        </div>
                        <p id="chars"></p>
                    </div>
                </div>
                <div class="colComments" id="commentsBlock"></div>
                <div class="clearfix"></div>
                <div style="height: 15px;"></div>
            </div>
        </div>
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

    <script src="/resources/js/projectsAndInvestmentsUtil.js"></script>
    <script src="/resources/js/project.js"></script>
</body>
</html>

