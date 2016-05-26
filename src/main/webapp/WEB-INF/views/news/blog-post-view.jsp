<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="ru"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Главная страница | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
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

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<div class="container2">

    <div class="news"> <!-- когда чувак вип надо добавить класс vip-color-border -->
        <p class="newsVisits" id="bpViewsNum">Просмотров: </p>

        <p class="newsPublished" id="bpCreatedDate">Опубликовано: </p>

        <h1 class="newsName" id="bpTitle"></h1> <!-- когда чувак вип надо добавить класс vip-color -->
        <div id="bpText" class="descriptionNormalNews2">
        </div>

        <div class="clearfix"></div>

        <div class="newsRating">
            <a id="newsLike" class="newsLike"></a>

            <p class="newsLikeNum" id="bpLikeNum"></p>
            <a id="newsDislike" class="newsDislike"></a>

            <p class="newsDislikeNum" id="bpDislikeNum"></p>
        </div>

        <div class="downComments"><p>Комментировать</p></div>

        <div class="clearfix"></div>

        <div class="colNewsComments">
            <div class="newsComments">
                <div class="clearfix"></div>
                <p class="newsCommentsHeader">Комментарий</p>

                <form id="newsCommentsForm">
                    <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий"
                              maxlength="1000" required></textarea>
                    <button id="sendNewsComment" class="newsFormSubmit">Отправить</button>
                </form>
                <p id="chars"></p>
            </div>
            <div class="replyToComment">
                <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                <a class="NameUser" href="#">Вася Петров</a>

                <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam repellat earum
                    aliquid explicabo nesciunt! Quidem sit facilis sunt odit quis repellendus quasi consectetur maiores,
                    blanditiis omnis hic labore, veritatis atque?</p>
            </div>
        </div>
        <div class="colComments" id="commentsBlock">

        </div>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script src="/resources/js/moment-with-locales.js"></script>


<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script>
    var blogPostId = "${blogPostId}";
</script>

<script src="/resources/js/blog-post-view.js"></script>
</body>
</html>
