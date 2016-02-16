<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <title>Новости</title>
        <script>
            function updateBlogPostsTable(blogPostFO) {
                var data;

                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "/api/rest/newsService/blogPost/read/all",
                    data: JSON.stringify(blogPostFO),
                    success: function (response) {
                        data = response.entities;
                        var goToPageLinks = '';

                        $('#pageLabel').append((blogPostFO.skip + 1) + ' из ' + response.totalEntities);
                        if (blogPostFO.skip > 0) {
                            goToPageLinks += '<a href="blog-post/news?pageNumber=' + (blogPostFO.skip - 1)  + '"> Назад </a>';
                        }

                        if (blogPostFO.skip < response.totalEntities && response.totalEntities/blogPostFO.limit > 1) {
                            goToPageLinks += '<a href="blog-post/news?pageNumber=' + (blogPostFO.skip + 1) + '"> Следующая </a>';
                        }
                        $('#goToPage').append(goToPageLinks);

                        for (var i = 0; i < data.length; i++) {
                            data[i].title = '<a href="/blog-post/view/' + data[i].id + '">' + data[i].title + '</a>';
                            var createdDate = new Date(data[i].createdDate);
                            data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();

                            if (data[i].imagesIds !== null && data[i].imagesIds != '') {
                                for (var key in data[i].imagesIds) {
                                    if (data[i].imagesIds[key] === "1") {
                                        data[i].imagesIds = '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + key + '" width="100" height="100">';
                                    }
                                }
                            } else {
                                data[i].imagesIds = {};
                                data[i].imagesIds = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                            }

                            var row = $('<tr>');
                            row.append($('<td>').html(data[i].imagesIds));
                            row.append($('<td>').html(data[i].title));
                            row.append($('<td>').html(data[i].views));
                            row.append($('<td>').html(data[i].createdDate));
                            row.append($('<td>').html(data[i].totalComments));
                            row.append($('<td>').html(data[i].totalLikes));
                            row.append($('<td>').html(data[i].totalDislikes));

                            $('#blogPostsTable').append(row);
                        }
                    }
                });
            }

            $(function() {
                $("#tagsName").autocomplete({
                    source: function (request, response) {
                        $.getJSON("${pageContext.request.contextPath}/search/blogPost", {
                            term: request.term
                        }, response);
                    }
                });
            });

            $(document).ready(function () {
                var blogPostFO = {};
                blogPostFO.createdDateSortDirection = "DESC";
                blogPostFO.skip = ${pageNumber};
                blogPostFO.limit = 20;

                updateBlogPostsTable(blogPostFO);
            });

            $(document).on('click', '#findBlogPostsButton', function (event) {
                $("#blogPostsTable").find("tr:not(:first)").remove();
                $("#paginationDiv").remove();

                var blogPostFO = {};
                blogPostFO.searchField = $("#tagsName").val();
                blogPostFO.createdDateSortDirection = "DESC";
                blogPostFO.skip = ${pageNumber};
                blogPostFO.limit = 50;

                updateBlogPostsTable(blogPostFO);
            });

        </script>
    </head>

    <body>

    <div class="container2">

        <div class="news">
            <p class="newsVisits">Просмотров: 20 000</p>
            <p class="newsPublished">Опубликовано: 22. 10.2015</p>
            <p class="newsName">На Березняках между ямами во дворе уложили полицейского</p>
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

            <div class="clearfix"></div>

            <div class="newsRating">
                <a class="newsLike" href="#"></a>
                <p class="newsLikeNum">22 000</p>
                <a href="#" class="newsDislike"></a>
                <p class="newsDislikeNum">22 000</p>
            </div>

            <div class="downComments"><p>КОММЕНТАРИИ</p></div>

            <div class="clearfix"></div>

            <div class="colNewsComments">
                <div class="newsComments">
                    <div class="clearfix"></div>
                    <p class="newsCommentsHeader">КОММЕНТАРИИ</p>
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

        <%--<div>--%>
            <%--<h2 align="center">Новости</h2>--%>
        <%--</div>--%>
        <%--<div align="center">--%>
            <%--<input id="tagsName" size="100" placeholder="Название новости">--%>
            <%--<button id="findBlogPostsButton">Найти новость</button>--%>
            <%--<a href="/blog-create"><button>Создать блог</button></a>--%>
        <%--</div>--%>
        <%--<div id="paginationDiv">--%>
            <%--<label id="pageLabel"><b>Страница:</b> </label>--%>
            <%--<p align="left" id="goToPage"></p>--%>
        <%--</div>--%>
        <%--<div>--%>
            <%--<table id="blogPostsTable" border="1" width="100%">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th>Фото</th>--%>
                    <%--<th>Название</th>--%>
                    <%--<th>Просмотры</th>--%>
                    <%--<th>Дата создания</th>--%>
                    <%--<th>Колличество комментариев</th>--%>
                    <%--<th>Лайки</th>--%>
                    <%--<th>Дизлайки</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
            <%--</table>--%>
        <%--</div>--%>
    </body>
</html>
