<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Блоги и новости | GUP</title>
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/custom-style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
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
        <a href="/blog-create">
            <button type="button" class="abutton">Создать новостной блог</button>
        </a>
    </div>

    <div id="tab-container-news" class="tab-container-news">
        <ul class='etabs-news'>
            <li class='tab-news'><a id="newsTab" href="#tabs1-news">Новости</a></li>
            <li class='tab-news'><a id="blogsTab" href="#tabs1-blogs">Блоги</a></li>
        </ul>

        <div id="tabs1-news">
            <div class="NewsTabsFilter">
                <p class="NewsTabsFilterItem">Киев</p>
                <p class="NewsTabsFilterItem">Днепропетровск</p>
                <p class="NewsTabsFilterItem">Запорожье</p>
                <p class="NewsTabsFilterItem">Львов</p>
                <p class="NewsTabsFilterItem">Одесса</p>
                <p class="NewsTabsFilterItem">Полтава</p>
                <p class="NewsTabsFilterItem">Харьков</p>
            </div>
            <div class="VIPNewsLarge">
                <a href="#" class="descriptionLarge">Бизнес-столица: Куда лучше вложить деньги?</a>
            </div>
            <div class="VIPNewsSmall">
                <a href="#" class="descriptionSmall">5 простых советов в кризис</a>
            </div>
            <div class="VIPNewsExtraSmall">
                <a href="#" class="descriptionExtraSmall">5 простых советов в кризис</a>
            </div>
            <div class="VIPNewsMedium">
                <a href="#" class="descriptionMedium">10 ошибок при управлении личными финансами</a>
            </div>

            <div>
                <div id="startBlockOfNews">
                    <div class="normalNews" style="display:none;">
                    <a href="#"><img class="news-img" src="" alt=""></a>
                    <a class="descriptionNormalNews" href="#">Студенты “топовых” бизнес—школ мира предпочитают
                        практиковаться на стартапах</a>
                    <p class="descriptionNormalNews2">&nbsp;&nbsp;</p>
                    <p class="normalNews-p">Просмотров: </p>
                    <p class="normalNews-p2">Опубликовано: </p>
                    <p class="normalNews-p3">Комментарии: </p>
                    </div>
                </div>
                <%--<button id="nextPageNews">Загрузить ещё новости</button>--%>
            </div>

        </div>

        <div id="tabs1-blogs">
            <div>
                <div id="startBlockOfBlogs">
                    <div class="blogs" style="display:none;">
                        <a href="#"><img class="blogs-img" src=""></a>
                        <a href="#" class="nameBlogs"></a>
                        <p class="text-blogs"></p>
                        <p class="DateOfCreation-blogs-num">Дата создания: </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="contentContainer" style="margin-top: 5px">
        <img class="projAndInvestCaretDown" name="getNextImg" id="nextPageNews" src="/resources/images/caret.png" alt="caret">
    </div>
</div>

<!--PAGE CONTENT END-->

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>
<script src="/resources/js/moment-with-locales.js"></script>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/blogs-and-news.js"></script>
</body>
</html>
