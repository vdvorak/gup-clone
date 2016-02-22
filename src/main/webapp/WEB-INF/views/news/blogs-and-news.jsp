<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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


<div class="container2">
    <div id="tab-container-news" class="tab-container-news">
        <ul class='etabs-news'>
            <li class='tab-news'><a href="#tabs1-news">Новости</a></li>
            <li class='tab-news'><a href="#tabs1-blogs">Блоги</a></li>
        </ul>

        <div id="tabs1-news">

            <div class="NewsTabsFilter">
                <p class="NewsTabsFilterItem" value="Киев">Киев</p>
                <p class="NewsTabsFilterItem" value="Киев1">Днепропетровск</p>
                <p class="NewsTabsFilterItem" value="Киев2">Запорожье</p>
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
                    <div class="normalNews">
                    <a href="#"><img class="news-img" src="" alt=""></a>
                    <a class="descriptionNormalNews" href="#">Студенты “топовых” бизнес—школ мира предпочитают
                        практиковаться на стартапах</a>
                    <p class="descriptionNormalNews2">&nbsp;&nbsp;</p>
                    <p class="normalNews-p">Просмотров: </p>
                    <p class="normalNews-p2">Опубликовано: </p>
                    <p class="normalNews-p3">Комментарии: </p>
                    </div>
                </div>
                <button id="nextPageNews">Загрузить ещё новости</button>
            </div>
        </div>






        <div id="tabs1-blogs">
            <div>
                <div id="startBlockOfBlogs">
                    <div class="blogs">
                        <a href="#"><img class="blogs-img" src="resources/images/1+1.png" alt="1+1"></a>
                        <a href="#" class="nameBlogs"></a>

                        <p class="text-blogs"></p>

                        <p class="DateOfCreation-blogs-num">Дата создания: </p>
                    </div>
                </div>
                <button id="nextPageBlog">Загрузить ещё блоги</button>
            </div>
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

    var firstBlockBlog = $('#startBlockOfBlogs').html();
    var firstBlockNews = $('#startBlockOfNews').html();

    var urlGetBlog = '/api/rest/newsService/blog/read/all';
    var urlGetNews = '/api/rest/newsService/blogPost/read/all';


    // ------------------- Create default block blogs and news -------------------------------------------------------

    $(document).ready(function () {

        var blogsFO = {};
        blogsFO.skip = 0;
        blogsFO.limit = 5;

        function findFirstImgBlog(pic) {
            var url = '/resources/images/no_photo.jpg';
            if (pic.length > 0) {
                return url = '/api/rest/fileStorage/NEWS/file/read/id/' + pic;
            }
            return url
        }

        function findFirstImgNews(arr) {
            var url = '/resources/images/no_photo.jpg';
            var imgId = '';

            for (var i in arr) {
                if (arr[i] === 'pic1') {
                    imgId = i;
                    url = '/api/rest/fileStorage/NEWS/file/read/id/' + imgId;
                    break;
                }
            }
            return url;
        }

        function localDateTime(long) {
            long = new Date(parseInt(long));
            long = moment(long).locale("ru").format('LLL');
            return long;
        }

        function doAjax(filterOptions, url, whatDraw) {
            alert("doAjax");
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: url,
                data: JSON.stringify(filterOptions),
                success: function (response) {
                    if (whatDraw === 'blogs') {
                        drawBlog(response.entities);
                    }
                    if (whatDraw === 'news') {
                        drawNews(response.entities);
                        alert("Draw === 'news'");
                        console.log(response);
                    }
                }
            });
        }

        function drawBlog(data) {
            for (var i in data) {
                $('.blogs').last().attr('style', 'display:;');
                $(".blogs-img").last().attr('src', findFirstImgBlog(data[i].imageId));
                $(".blogs-img").last().attr('alt', data[i].title);
                $(".blogs a").attr('href', '/blog/' + data[i].id);
                $(".text-blogs").last().text(data[i].description);
                $(".DateOfCreation-blogs-num").last().append(localDateTime(data[i].createdDate));
                $(".nameBlogs").last().text(data[i].title);
                $('#startBlockOfBlogs').append(firstBlockBlog);
            }
            $('.blogs').last().attr('style', 'display: none;');
        }

        doAjax(blogsFO, urlGetBlog, 'blogs');

        $('#nextPageBlog').on('click', function () {
            blogsFO.skip += 5;
            doAjax(blogsFO, urlGetBlog, 'blogs');
        })

        //---------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------


        var newsFO = {};
        var address = {};
        newsFO.skip = 0;
        newsFO.limit = 2;

        $(".NewsTabsFilterItem").on('click', function(){
            $('.intro').removeClass("intro");
            $(this).addClass("intro");
            address.city = $('.intro').text();
            newsFO.address = address;
            $("div.normalNews").remove();
            newsFO.skip = 0;
            newsFO.limit = 2;
            doAjax(newsFO, urlGetNews, 'news');
//            setTimeout(function() {alert("timout gone");doAjax(newsFO, urlGetNews, 'news');}, 1000);
        });

        function drawNews(data) {

            for (var i = 0; i<data.length; i++ ) {
                data[i].text = data[i].text.replace(/<\/?[^>]+(>|$)/g, "").replace('\\n', "");
                if($('.normalNews').length == 0){
                    $('#startBlockOfNews').append(firstBlockNews);
                    alert("pew");
                }
                $('.normalNews').last().attr('style', 'display:;');
                $(".news-img").last().attr('src', findFirstImgNews(data[i].imageId));
                $(".news-img").last().attr('alt', data[i].title);
                $(".normalNews a").attr('href', '/blog-post/view/id/' + data[i].id);
                $(".descriptionNormalNews2").last().text(data[i].text);  // - описание
                $(".normalNews-p2").last().append(localDateTime(data[i].createdDate)); // - дата создания
                $(".descriptionNormalNews").last().text(data[i].title);  // - заголовок
                $(".normalNews-p").last().append(data[i].views);  // - просмотры
                $(".normalNews-p3").last().append(data[i].totalComments);  // - комментарии
                $('#startBlockOfNews').append(firstBlockNews);
            }
            $('.normalNews').last().attr('style', 'display: none;');
        }


        doAjax(newsFO, urlGetNews, 'news');

        $('#nextPageNews').on('click', function () {
            newsFO.skip += 2;
            doAjax(newsFO, urlGetNews, 'news');
        })


    });
    // ------------------- End create default block of blogs and news -------------------------------------------------------

</script>
</body>
</html>
