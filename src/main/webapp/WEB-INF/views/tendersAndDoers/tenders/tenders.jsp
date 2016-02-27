<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>Тендеры | GUP</title>

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
    <div class="contentContainer" style="margin-top: 100px;"> <!-- Add "vip" class for "vip" users :3 -->
        <div class="tenderContent">
            <div class="topSection">
                <div class="statInfo">
                    <div class="publishDate">Опубликовано: 22. 10. 16</div>
                    <span class="number visible">№953745195</span>
                    <div class="sum">00 000 000$</div>
                </div>
                <div class="clearfix"></div>
                <div class="tenderButtons">
                    <div class="participate">
                        <div class="clock">
                            <div class="time">31 января 2016 г., 23:35</div>
                        </div>
                        <button type="button" class="abutton blue">Участвовать</button>
                    </div>
                    <button type="button" class="abutton leaveProposal">Оставить предложение</button>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="title">Название тендера</div>
            <img src="/resources/css/images/sample/tender1.png" alt="" class="mainPhoto">
            <p class="text">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc malesuada molestie euismod. In vulputate mi dictum orci vulputate auctor. Fusce sit amet orci suscipit neque lacinia pulvinar nec in est. Proin neque ante, elementum at maximus a, luctus nec diam. Mauris convallis tellus nec sapien accumsan, quis posuere odio congue. Etiam eu tincidunt arcu, pellentesque euismod odio. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec gravida dolor ac laoreet ultrices.

                Phasellus dignissim accumsan ligula convallis hendrerit. Quisque eu tincidunt sapien. Mauris molestie pharetra faucibus. Sed vestibulum magna ac accumsan pellentesque. Maecenas sit amet magna rhoncus, elementum ligula sed, volutpat neque. In sit amet nibh ante. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse in mauris tempus, pretium odio ac, semper metus. Praesent ut turpis sit amet ante interdum vulputate ultricies ac neque. Maecenas placerat tortor magna, quis consequat turpis feugiat non. Nunc vel ultricies metus. Morbi facilisis maximus augue, sed sagittis ligula tincidunt at. Mauris sed vestibulum sapien. Fusce ut iaculis sem.

                Nulla vitae lacus enim. Etiam malesuada ex at velit faucibus ultricies. Nunc quis tincidunt urna. Sed a erat faucibus, sodales velit at, lacinia massa. Aliquam porta at libero et gravida. Vivamus viverra ut odio blandit posuere. Nam sagittis justo at elit ornare dignissim. Maecenas eros tortor, sollicitudin non aliquet id, blandit id velit. Aenean scelerisque feugiat lacus eget mattis. Etiam convallis lacinia mauris at aliquam. Ut in nibh nisi. Nam tincidunt sollicitudin enim nec aliquet.

                Cras consectetur vitae erat eget porta. Aenean efficitur eget dolor nec suscipit. Curabitur sit amet tellus nec lorem maximus lobortis euismod quis libero. Donec molestie nibh ac consequat auctor. Aliquam sagittis blandit mauris. Phasellus eu ornare nisi. Aenean sagittis vestibulum pellentesque. Nam venenatis condimentum sapien, sed ornare neque porta fringilla. Vestibulum quis eleifend lacus, in molestie nulla. Suspendisse potenti. Sed rutrum tortor sit amet tortor gravida sollicitudin. Phasellus quis condimentum lectus. Quisque sapien eros, tristique et venenatis ultrices, volutpat vitae libero. In velit enim, tristique quis est feugiat, interdum laoreet augue. Sed maximus sollicitudin neque non sodales.

                Vestibulum tempus mauris neque, eu pretium sapien aliquam vitae. Aliquam ac porttitor mi. Vivamus pretium quam id metus venenatis luctus. Aenean ultrices a nunc vel semper. Proin non eleifend dui. Aenean placerat lectus et est porttitor bibendum. Donec at felis nec quam sagittis sagittis convallis eu nisi. Maecenas cursus consequat turpis vitae placerat. Praesent ac sagittis sapien, ut pretium odio. Curabitur et lectus est. Phasellus odio ex, placerat vel nibh in, rutrum porttitor est. Donec eget metus sit amet dolor consequat gravida. Pellentesque posuere orci quis accumsan vestibulum. Suspendisse lobortis eget orci tristique maximus. Quisque sollicitudin, mi ac mattis lacinia, sem turpis tristique metus, ut vehicula lectus justo vel sem. Nam eu imperdiet justo.
            </p>
            <div class="bottomSection">
                lololololololo i'm bottom section)))))azzaz
            </div>

        </div>
        <div class="sliderTender">
            <ul class="bxsliderTender">
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider" /></li>
            </ul>
        </div>
        <div class="tenderFils">
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
            <a href="#">doc.exel</a>
            <a href="#">system.docx</a>
        </div>
        <div class="tenderMap">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2534.961160242289!2d30.466304515735114!3d50.5534922794897!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4d31f1b52061f%3A0x7e2e7b1a78ac5cb7!2z0LLRg9C7LiDQodCw0LTQvtCy0LAsINCa0LjRl9Cy!5e0!3m2!1sru!2sua!4v1456576844954" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
        <div class="downComments"><p>КОММЕНТАРИИ</p></div>
    </div>

    <div class="colNewsComments">
        <div class="newsComments">
            <div class="clearfix"></div>
            <p class="newsCommentsHeader">ПРЕДЛОЖЕНИЯ</p>
            <form action="#" role="form" id="newsCommentsForm">
                <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий" maxlength="2000" required></textarea>
                <button type="submit" class="newsFormSubmit">Отправить</button>
            </form>
            <p id="chars">2000 символов осталось</p>
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
    <div class="clearfix"></div>
</div>

<%--<section>--%>
    <%--<div class="tender-wrap">--%>
        <%--<div class="tender-tabs-wrap">--%>
            <%--<div class="tabs">--%>
                <%--<ul style="margin-bottom: 50px;">--%>
                    <%--<li class="tender-tabs-title">ТЕНДЕРЫ</li>--%>
                    <%--<li class="tender-tabs-title">ИСПОЛНИТЕЛИ</li>--%>
                <%--</ul>--%>
                <%--<div>--%>

                    <%--<!-- Repeated section with tender -->--%>
                    <%--<div id="startBlock">--%>
                        <%--<div class="tender-tabs-items-wrap">--%>

                            <%--<div class="tender-item-wrapper">--%>
                                <%--<div class="tender-item-leftside">--%>
                                    <%--<div class="tender-pic-wrap">--%>
                                        <%--<a href=""><img src="#" alt=""></a>--%>

                                    <%--</div>--%>
                                    <%--<div class="tender-subpic-stuff">--%>
                                        <%--<p style="margin-top: 0px; display: inline-block;">Предложений:<span--%>
                                                <%--class="tender-proposal-count"></span></p>--%>

                                        <%--<p style="margin-top: 0px; display: inline-block; float: right;">--%>
                                            <%--Просмотров:<span--%>
                                                <%--class="tender-veiws"></span></p>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="tender-item-rightside">--%>
                                    <%--<div class="tender-item-header-wrap">--%>
                                        <%--<div class="tender-name">--%>
                                            <%--<p></p>--%>
                                        <%--</div>--%>
                                        <%--<div class="tender-item-info">--%>
                                            <%--<p class="tender-publish-date">Опубликовано:<span--%>
                                                    <%--class="date-create"></span></p>--%>

                                            <%--<p class="tender-number">№<span></span></p>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="tender-item-text">--%>
                                        <%--<p></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="tender-item-subtext-stuff">--%>
                                        <%--<div class="tender-time-remain">--%>
                                            <%--<img src="/resources/img/alarm.png">--%>

                                            <%--<p class="tender-time date-create"></p>--%>
                                        <%--</div>--%>
                                        <%--<div class="tender-cost-wrap">--%>
                                            <%--<p><span class="tender-cost"></span>$</p>--%>
                                            <%--<button class="tender-apply-for">Участвовать</button>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<button id="nextPage">Загрузить ещё тендеров</button>--%>
<%--</section>--%>


<jsp:include page="/WEB-INF/templates/support-questions.jsp"/>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

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

<!--END of libs-->
<script>

    var firstBlock = $('#startBlock').html();
    // ------------------- Create default block of tenders -------------------------------------------------------

    $(document).ready(function () {

        var tendersFO = {};
        tendersFO.skip = 0;
        tendersFO.limit = 3;

        function findFirstImg(arr) {
            var url = '/resources/images/no_photo.jpg';
            var imgId = '';
            for (var i in arr) {
                if (arr[i] === 'pic1') {
                    imgId = i;
                    url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
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


        function doAjax(filterOptions) {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/tenderService/tender/read/all/",
                data: JSON.stringify(filterOptions),
                success: function (response) {
                    draw(response.entities);
                }
            });
        }

        function draw(data) {
            for (var i in data) {
                $('.tender-tabs-items-wrap').last().attr('style', 'display:;');
                $(".tender-pic-wrap img").last().attr('src', findFirstImg(data[i].uploadFilesIds));
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

            $('.tender-tabs-items-wrap').last().attr('style', 'display: none;');
        }

        doAjax(tendersFO);

        $('#nextPage').on('click', function () {
            tendersFO.skip += 3;
            doAjax(tendersFO);
        })

    });
    // ------------------- End create default block of tenders -------------------------------------------------------
</script>
</body>
</html>