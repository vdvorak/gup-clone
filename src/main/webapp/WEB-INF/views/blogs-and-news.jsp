<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Комп1
  Date: 10.02.2016
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Блоги и новости | GUP</title>
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
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>


<div class="container">
    <div id="tab-container-news" class="tab-container-news">
        <ul class='etabs-news'>
            <li class='tab-news'><a href="#tabs1-blogs">Блоги</a></li>
            <li class='tab-news'><a href="#tabs1-news">Новости</a></li>
        </ul>
        <div id="tabs1-blogs">


            <div id="startBlock">
                <div class="blogs">
                    <a href="#"><img class="blogs-img" src="resources/images/1+1.png" alt="1+1"></a>
                    <a href="#" class="nameBlogs">Название блога (блоггера)</a>

                    <p class="description">Описание</p>

                    <p class="text-blogs">Блог (англ. blog, от web log — интернет-журнал событий, интернет-дневник,
                        онлайн-дневник) — веб-сайт, основное содержимое которого — регулярно добавляемые записи,
                        содержащие
                        текст, изображения или мультимедиа.</p>

                    <p class="views-blogs">Просмотров:</p>

                    <p class="views-blogs-num">2233</p>

                    <p class="DateOfCreation-blogs">Дата создания :</p>

                    <p class="DateOfCreation-blogs-num">22.11.15</p>
                </div>
            </div>


        </div>
        <div id="tabs1-news">
            <h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Veritatis nulla, minima placeat, sit cum
                aliquid. Maxime reiciendis, aut officia dolorem aliquid magnam, tempore, accusantium veritatis
                laudantium ea reprehenderit amet odit.</h2>
            <!-- content -->
        </div>
    </div>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>

<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<sec:authorize var="loggedIn" access="isAuthenticated()"/>
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

<script src="/resources/js/top-news-block.js"></script>
<script src="/resources/js/top-projects-block.js"></script>
<script src="/resources/js/top-offers-block.js"></script>
<script src="/resources/js/top-tenders-block.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>


<script>

    var firstBlock = $('#startBlock').html();
    // ------------------- Create default block of tenders -------------------------------------------------------

    $(document).ready(function () {

        var blogsFO = {};
        blogsFO.skip = 0;
        blogsFO.limit = 5;

        function findFirstImg(pic) {
            var url = '/resources/images/no_photo.jpg';
            if (pic.length > 0) {
                return url = '/api/rest/fileStorage/NEWS/file/read/id/' + pic;
            }
            return url
        }

        function localDateTime(long) {
            long = new Date(parseInt(long));
            long = moment(long).locale("ru").format('LLL');
            return long;
        }

        function draw(data) {
            for (var i in data) {
                $('.blogs').last().attr('style', 'display:;');
                $(".blogs-img").last().attr('src', findFirstImg(data[i].imageId));
                $(".blogs-img").last().attr('alt', data[i].title);
                $(".tender-pic-wrap a").last().attr('href', '/tender/' + data[i].id);
                $(".tender-item-text p").last().html(data[i].body);
                $(".tender-number").last().text(data[i].tenderNumber);
                $(".tender-publish-date span").last().text(localDateTime(data[i].begin));
                $(".tender-veiws").last().text(data[i].visited);
                $(".tender-proposal-count").last().text(data[i].proposeNumber);
                $(".tender-name p").last().text(data[i].title);
                $(".date-create").last().text(localDateTime(data[i].end));
                $('#startBlock').append(firstBlock);
            }

            $('.blogs').last().attr('style', 'display: none;');
        }


        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/newsService/blog/read/all",
            data: JSON.stringify(projectFO),
            success: function (response) {
                alert(JSON.stringify(response.entities));
                draw(response.entities);
            }
        });

        $('#nextPage').on('click', function () {
            blogsFO.skip += 5;

            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/tenderService/tender/read/all/",
                data: JSON.stringify(blogsFO),
                success: function (response) {
                    draw(response.entities);
                }
            });
        })

    });
    // ------------------- End create default block of tenders -------------------------------------------------------

</script>
</body>
</html>
