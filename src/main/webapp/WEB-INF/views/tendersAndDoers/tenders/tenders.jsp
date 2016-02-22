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


<section>
    <div class="tender-wrap">
        <div class="tender-tabs-wrap">
            <div class="tabs">
                <ul style="margin-bottom: 50px;">
                    <li class="tender-tabs-title">ТЕНДЕРЫ</li>
                    <li class="tender-tabs-title">ИСПОЛНИТЕЛИ</li>
                </ul>
                <div>

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

                </div>
            </div>
        </div>
    </div>
    <button id="nextPage">Загрузить ещё тендеров</button>
</section>


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