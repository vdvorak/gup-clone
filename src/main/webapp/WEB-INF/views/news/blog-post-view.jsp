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

        <link rel="stylesheet" href="/resources/css/bootstrap.css">
        <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
        <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
        <link rel="stylesheet" href="/resources/css/main.css">
        <link rel="stylesheet" href="/resources/css/font-awesome.css">
        <link rel="stylesheet" href="/resources/css/media-queries.css">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    </head>
    <body>
        <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <jsp:include page="/WEB-INF/templates/common-header.jsp"/>

        <jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

        <jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

        <div class="container2">

            <div class="news"> <!-- когда чувак вип надо добавить класс vip-color-border -->
                <p class="newsVisits" id="bpViewsNum">Просмотров: </p>
                <p class="newsPublished" id="bpCreatedDate">Опубликовано: </p>
                <p class="newsName" id="bpTitle"></p> <!-- когда чувак вип надо добавить класс vip-color -->
                <div id="bpText">
                    <img class="newsIMG1" src="/resources/images/newsImages.png" alt="images">
                    <p class="newsText">Перед выборами забота о гражданах максимально зашкаливает. Чинятся подъезды, ремонтируются дороги, ну в общем кому как повезет. Жителям одного из дворов на Березняках повезло не особо: не хватило асфальта. Но хоть что-то ради электората сделать надо. И коммунальщики оторвали от сердца дорогое: лежачего полицейского.
                        Уложили его аккуратно между ямами, чтоб водитель уж точно никуда не делся.
                        Жители двора новшевство комментируют ярко, но по понятным причинам привести цитаты мы не можем, в основном они сводятся к экспрессивным формам вопроса "Зачем?". Перед выборами забота о гражданах максимально зашкаливает. Чинятся подъезды, ремонтируются дороги, ну в общем кому как повезет. Жителям одного из дворов на Березняках повезло не особо: не хватило асфальта. Но хоть что-то ради электората сделать надо. И коммунальщики оторвали от сердца дорогое: лежачего полицейского.
                        Уложили его аккуратно между ямами, чтоб водитель уж точно никуда не делся.
                        Жители двора новшевство комментируют ярко, но по понятным причинам привести цитаты мы не можем, в основном они сводятся к экспрессивным формам вопроса "Зачем?".</p>
                    <img class="newsIMG2" src="/resources/images/newsIMG2.png" alt="images">
                    <p class="newsText">Перед выборами забота о гражданах максимально зашкаливает. Чинятся подъезды, ремонтируются дороги, ну в общем кому как повезет. Жителям одного из дворов на Березняках повезло не особо: не хватило асфальта. Но хоть что-то ради электората сделать надо. И коммунальщики оторвали от сердца дорогое: лежачего полицейского.
                        Уложили его аккуратно между ямами, чтоб водитель уж точно никуда не делся.
                        Жители двора новшевство
                        комментируют ярко, но по понятным причинам привести цитаты мы не можем, в основном они сводятся к экспрессивным формам вопроса "Зачем?".
                        Перед выборами забота о гражданах максимально зашкаливает. Чинятся подъезды, ремонтируются дороги, ну в общем кому как повезет. Жителям одного из дворов на Березняках повезло не особо: не хватило асфальта. Но хоть что-то ради электората сделать надо. И коммунальщики оторвали от сердца дорогое: лежачего полицейского.
                        Уложили его аккуратно между ямами, чтоб водитель уж точно никуда не делся.
                        Жители двора новшевство
                        комментируют ярко, но по понятным причинам привести цитаты мы не можем, в основном они сводятся к экспрессивным формам вопроса "Зачем?".</p>
                    <img class="newsIMG1" src="/resources/images/newsIMG3.png" alt="images">
                    <p class="newsText">Перед выборами забота о гражданах максимально зашкаливает. Чинятся подъезды, ремонтируются дороги, ну в общем кому как повезет. Жителям одного из дворов на Березняках повезло не особо: не хватило асфальта. Но хоть что-то ради электората сделать надо. И коммунальщики оторвали от сердца дорогое: лежачего полицейс
                        кого.

                        Жители двора новшевство
                        комментируют ярко, но по понятным причинам привести цитаты мы не можем, в основном они сводятся к экспрессивным формам вопроса "Зачем?".</p>
                </div>

                <div class="clearfix"></div>

                <div class="newsRating">
                    <a class="newsLike" href="#"></a>
                    <p class="newsLikeNum" id="bpLikeNum"></p>
                    <a href="#" class="newsDislike"></a>
                    <p class="newsDislikeNum" id="bpDislikeNum"></p>
                </div>

                <div class="downComments"><p>Комментировать</p></div>

                <div class="clearfix"></div>

                <div class="colNewsComments">
                    <div class="newsComments">
                        <div class="clearfix"></div>
                        <p class="newsCommentsHeader">КОММЕНТАРИЙ</p>
                        <form action="#" role="form" id="newsCommentsForm">
                            <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий" maxlength="22000" required></textarea>
                            <button type="submit" class="newsFormSubmit">Отправить</button>
                        </form>
                        <p id="chars"></p>
                    </div>
                </div>
                <div class="colComments">
                    <div class="comments">
                        <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                        <a class="NameUser" href="#">Вася Петров</a>
                        <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Qui quisquam, voluptate at magni neque. Ab illum hic asperiores voluptate voluptatem. Optio alias, numquam sint delectus quod recusandae dolores tempora. Aliquam!</p>
                    </div>
                    <div class="comments">
                        <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                        <a class="NameUser" href="#">Вася Петров</a>
                        <p class="commentUser">Интересно было узнать, история повторяется циклично!</p>
                    </div>
                </div>
            </div>
        </div>

        <sec:authorize access="isAuthenticated()">
            <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
        </sec:authorize>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
        <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
        <script src="/resources/js/vendor/bootstrap.js"></script>
        <script src="/resources/js/jquery.bxslider.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
        <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

        <sec:authorize var="loggedIn" access="isAuthenticated()" />
        <c:choose>
            <c:when test="${loggedIn}">
                <script src="/resources/js/autorizedHeader.js"></script>
            </c:when>
            <c:otherwise>
                <script src="/resources/js/anonymHeader.js"></script>
            </c:otherwise>
        </c:choose>

        <script src="/resources/js/main.js"></script>
        <script src="/resources/js/logo-section.js"></script>
        <script src="/resources/js/search-bar.js"></script>

        <script>
            var blogPostId = "${blogPostId}";

            $.ajax({
                type: "POST",
                url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/read",
                statusCode: {
                    200: function (blogPost) {
                        alert(JSON.stringify(blogPost));
                        $('#bpViewsNum').append(blogPost.views);
                        var createdDate = new Date(blogPost.createdDate);
                        $('#bpCreatedDate').append(createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear());
                        $('#bpTitle').append(blogPost.title);
                        $('#bpLikeNum').append(blogPost.totalLikes);
                        $('#bpDislikeNum').append(blogPost.totalDislikes);
                        $('#bpText').append(blogPost.text);
                    }
                }
            });
            <%--function updateBlogPostsTable(blogPostFO) {--%>
                <%--var data;--%>

                <%--$.ajax({--%>
                    <%--type: "POST",--%>
                    <%--contentType: "application/json; charset=utf-8",--%>
                    <%--url: "/api/rest/newsService/blogPost/read/all",--%>
                    <%--data: JSON.stringify(blogPostFO),--%>
                    <%--success: function (response) {--%>
                        <%--data = response.entities;--%>
                        <%--var goToPageLinks = '';--%>

                        <%--$('#pageLabel').append((blogPostFO.skip + 1) + ' из ' + response.totalEntities);--%>
                        <%--if (blogPostFO.skip > 0) {--%>
                            <%--goToPageLinks += '<a href="blog-post/news?pageNumber=' + (blogPostFO.skip - 1)  + '"> Назад </a>';--%>
                        <%--}--%>

                        <%--if (blogPostFO.skip < response.totalEntities && response.totalEntities/blogPostFO.limit > 1) {--%>
                            <%--goToPageLinks += '<a href="blog-post/news?pageNumber=' + (blogPostFO.skip + 1) + '"> Следующая </a>';--%>
                        <%--}--%>
                        <%--$('#goToPage').append(goToPageLinks);--%>

                        <%--for (var i = 0; i < data.length; i++) {--%>
                            <%--data[i].title = '<a href="/blog-post/view/' + data[i].id + '">' + data[i].title + '</a>';--%>
                            <%--var createdDate = new Date(data[i].createdDate);--%>
                            <%--data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();--%>

                            <%--if (data[i].imagesIds !== null && data[i].imagesIds != '') {--%>
                                <%--for (var key in data[i].imagesIds) {--%>
                                    <%--if (data[i].imagesIds[key] === "1") {--%>
                                        <%--data[i].imagesIds = '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + key + '" width="100" height="100">';--%>
                                    <%--}--%>
                                <%--}--%>
                            <%--} else {--%>
                                <%--data[i].imagesIds = {};--%>
                                <%--data[i].imagesIds = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';--%>
                            <%--}--%>

                            <%--var row = $('<tr>');--%>
                            <%--row.append($('<td>').html(data[i].imagesIds));--%>
                            <%--row.append($('<td>').html(data[i].title));--%>
                            <%--row.append($('<td>').html(data[i].views));--%>
                            <%--row.append($('<td>').html(data[i].createdDate));--%>
                            <%--row.append($('<td>').html(data[i].totalComments));--%>
                            <%--row.append($('<td>').html(data[i].totalLikes));--%>
                            <%--row.append($('<td>').html(data[i].totalDislikes));--%>

                            <%--$('#blogPostsTable').append(row);--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>

            <%--$(function() {--%>
                <%--$("#tagsName").autocomplete({--%>
                    <%--source: function (request, response) {--%>
                        <%--$.getJSON("${pageContext.request.contextPath}/search/blogPost", {--%>
                            <%--term: request.term--%>
                        <%--}, response);--%>
                    <%--}--%>
                <%--});--%>
            <%--});--%>

            <%--$(document).ready(function () {--%>
                <%--var blogPostFO = {};--%>
                <%--blogPostFO.createdDateSortDirection = "DESC";--%>
                <%--blogPostFO.skip = ${pageNumber};--%>
                <%--blogPostFO.limit = 20;--%>

                <%--updateBlogPostsTable(blogPostFO);--%>
            <%--});--%>

            <%--$(document).on('click', '#findBlogPostsButton', function (event) {--%>
                <%--$("#blogPostsTable").find("tr:not(:first)").remove();--%>
                <%--$("#paginationDiv").remove();--%>

                <%--var blogPostFO = {};--%>
                <%--blogPostFO.searchField = $("#tagsName").val();--%>
                <%--blogPostFO.createdDateSortDirection = "DESC";--%>
                <%--blogPostFO.skip = ${pageNumber};--%>
                <%--blogPostFO.limit = 50;--%>

                <%--updateBlogPostsTable(blogPostFO);--%>
            <%--});--%>

        </script>
    </body>
</html>
