<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>Тендеры | GUP</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">

    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
</head>
<body>

<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->

<section class="first-sec">
    <div class="main-search-button-wrapper">
        <input id="searchInput" type="text" placeholder="Имя пользователя или компании" value="">
        <button id="searchButton">Найти<span class="main-search-button-icon"><img
                src="/resources/img/magnifire.png"></span></button>
        <sec:authorize access="isAuthenticated()">
            <a href="/tender-make">
                <button>Создать тендер</button>
            </a>
        </sec:authorize>
    </div>
</section>

<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/main-menu-slider-bar.jsp"/>
<!--END 2nd section -->


<section>
    <div class="tender-wrap">
        <div class="tender-tabs-wrap">
            <div class="tabs">
                <ul style="margin-bottom: 50px;">
                    <li class="tender-tabs-title">ТЕНДЕРЫ</li>
                    <li class="tender-tabs-title">ИСПОЛНИТЕЛИ</li>
                </ul>
                <div>


                    <%--<!-- Repeated section with tender -->--%>
                    <%--<div id="1">--%>
                    <%--<div class="tender-tabs-items-wrap">--%>
                    <%--<c:forEach var="tender" items="${tenders.entities}">--%>
                    <%--<div class="tender-item-wrapper">--%>
                    <%--<div class="tender-item-leftside">--%>
                    <%--<div class="tender-pic-wrap">--%>
                    <%--<img src="/resources/img/tender-example-pic.png">--%>
                    <%--</div>--%>
                    <%--<div class="tender-subpic-stuff">--%>
                    <%--<p style="margin-top: 0px; display: inline-block;">Предложений:<span--%>
                    <%--class="tender-proposal-count">${tender.proposeNumber}</span></p>--%>

                    <%--<p style="margin-top: 0px; display: inline-block; float: right;">--%>
                    <%--Просмотров:<span--%>
                    <%--class="tender-veiws">${tender.visited}</span></p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="tender-item-rightside">--%>
                    <%--<div class="tender-item-header-wrap">--%>
                    <%--<div class="tender-name">--%>
                    <%--<p>${tender.title}</p>--%>
                    <%--</div>--%>
                    <%--<div class="tender-item-info">--%>
                    <%--<p class="tender-publish-date">Опубликовано:<span--%>
                    <%--class="date-create">${tender.begin}</span></p>--%>

                    <%--<p class="tender-number">№<span>${tender.tenderNumber}</span></p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="tender-item-text">--%>
                    <%--<p>${tender.body}</p>--%>
                    <%--</div>--%>
                    <%--<div class="tender-item-subtext-stuff">--%>
                    <%--<div class="tender-time-remain">--%>
                    <%--<img src="/resources/img/alarm.png">--%>

                    <%--<p class="tender-time date-create">${tender.end}</p>--%>
                    <%--</div>--%>
                    <%--<div class="tender-cost-wrap">--%>
                    <%--<p><span class="tender-cost">${tender.expectedPrice}</span></p>--%>
                    <%--<button class="tender-apply-for">Участвовать</button>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</c:forEach>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<!-- End of repeated section with tender -->--%>


                    <!-- Repeated section with tender -->
                    <div id="startBlock">
                        <div class="tender-tabs-items-wrap">

                            <div class="tender-item-wrapper">
                                <div class="tender-item-leftside">
                                    <div class="tender-pic-wrap">
                                        <a href=""><img src="#" alt=""></a>

                                    </div>
                                    <div class="tender-subpic-stuff">
                                        <p style="margin-top: 0px; display: inline-block;">Предложений:<span
                                                class="tender-proposal-count"></span></p>

                                        <p style="margin-top: 0px; display: inline-block; float: right;">
                                            Просмотров:<span
                                                class="tender-veiws"></span></p>
                                    </div>
                                </div>
                                <div class="tender-item-rightside">
                                    <div class="tender-item-header-wrap">
                                        <div class="tender-name">
                                            <p></p>
                                        </div>
                                        <div class="tender-item-info">
                                            <p class="tender-publish-date">Опубликовано:<span
                                                    class="date-create"></span></p>

                                            <p class="tender-number">№<span></span></p>
                                        </div>
                                    </div>
                                    <div class="tender-item-text">
                                        <p></p>
                                    </div>
                                    <div class="tender-item-subtext-stuff">
                                        <div class="tender-time-remain">
                                            <img src="/resources/img/alarm.png">

                                            <p class="tender-time date-create"></p>
                                        </div>
                                        <div class="tender-cost-wrap">
                                            <p><span class="tender-cost"></span>$</p>
                                            <button class="tender-apply-for">Участвовать</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<!-- End of repeated section with tender -->--%>

                    <%--<button>Ещё</button>--%>

                </div>


                <!-- 2-nd tab-->
                <%--<div>--%>
                <%--<div class="doer-items-wrap">--%>
                <%--<div class="doer-wrapper">--%>
                <%--<div class="doer-item-left">--%>
                <%--<div class="doer-userpic-wrap">--%>
                <%--<img src="/resources/img/doer-userpic.png">--%>
                <%--</div>--%>
                <%--<div class="doer-rating">--%>
                <%--<p>523</p>--%>
                <%--</div>--%>
                <%--</div>--%>


                <%--<div class="doer-item-mid">--%>
                <%--<div class="doer-name">--%>
                <%--<p>ФИО исполнителя</p>--%>
                <%--</div>--%>
                <%--<div class="doer-description">--%>
                <%--<p class="doer-description-title">Описание</p>--%>

                <%--<p class="doer-description-main">Как появляется инвестор, о проблемах и--%>
                <%--противоречиях на пути его появления. Каждого инвестора окружают посредники,--%>
                <%--которые существенно влияют на процессы взаимодействия с ним. Как появляется--%>
                <%--инвестор, о проблемах и противоречиях на пути его появления. Каждого инвестора--%>
                <%--окружают посредники, которые существенно влияют на процессы взаимодействия с--%>
                <%--ним.--%>
                <%--Как появляется инвестор, о проблемах и противоречиях на пути его появления.--%>
                <%--Каждого инвестора окружают посредники, которые существенно влияют на процессы--%>
                <%--взаимо</p>--%>
                <%--</div>--%>
                <%--</div>--%>


                <%--<div class="doer-item-right">--%>
                <%--<div class="doer-info">--%>
                <%--<p class="doer-creation-date">Дата создания:<span>12.09.15</span></p>--%>

                <%--<p class="doer-update-date">Дата обновления:<span>12.09.15</span></p>--%>

                <%--<p class="doer-views-count">Просмотры:<span>129</span></p>--%>
                <%--</div>--%>
                <%--<div class="doer-likes-buttons-wrap">--%>
                <%--<img class="doer-like" src="/resources/img/doer-like-icon.png">--%>
                <%--<img class="doer-dislike" src="/resources/img/doer-dislike-icon.png">--%>
                <%--</div>--%>
                <%--<div class="doer-addToClient-button-wrap">--%>
                <%--<button class="doer-addToClient-button">Добавить в клиенты</button>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <!-- 2-nd tab-->

            </div>
        </div>
    </div>
    <button id="nextPage">Загрузить ещё тендеров</button>
</section>
<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/prioffice.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>

<script src="/resources/js/common.js"></script>

<sec:authorize access="isAuthenticated()">
    <script src="/resources/js/autorizedHeader.js"></script>
</sec:authorize>

<script src="/resources/js/index.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>

<!--END of libs-->
<script>

    var firstBlock = $('#startBlock').html();
    // ------------------- Create default block of tenders -------------------------------------------------------

    $(document).ready(function () {

        var projectFO = {};
        projectFO.skip = 0;
        projectFO.limit = 3;

        function findFirstImg(arr) {
            var url = '';
            var imgId = '';
            for (var i in arr) {
                if (arr[i] === 'image') {
                    imgId = i;
                    break;
                }
            }
            url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
            return url;
        }

        function localDateTime(long) {
            long = new Date(parseInt(long));
            long = moment(long).locale("ru").format('LLL');
            return long;
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

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/tenderService/tender/read/all/",
            data: JSON.stringify(projectFO),
            success: function (response) {
                draw(response.entities);
            }
        });

        $('#nextPage').on('click', function () {
            projectFO.skip += 3;

            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/tenderService/tender/read/all/",
                data: JSON.stringify(projectFO),
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