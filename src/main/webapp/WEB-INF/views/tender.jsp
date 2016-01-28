<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 28.01.2016
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>Тендер | GUP</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">

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
<jsp:include page="/WEB-INF/templates/main-search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/main-menu-slider-bar.jsp"/>
<!--END 2nd section -->


<section>
    <div class="tender-wrap">
        <div class="tender-tabs-wrap">
            <div class="tabs">

                <div>

                    <!-- Repeated section with tender -->
                    <div id="startBlock">
                        <div class="tender-tabs-items-wrap">

                            <div class="tender-item-wrapper">
                                <div class="tender-item-leftside">
                                    <%--<div class="tender-pic-wrap">--%>
                                    <%--<img src="#">--%>
                                    <%--</div>--%>
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


                                            <div class="tender-cost-wrap">
                                                <p><span class="tender-cost"></span>$</p>
                                                <button class="tender-apply-for">Участвовать</button>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="imgGal"></div>
                                </div>
                            </div>
                        </div>
                        <%--<!-- End of repeated section with tender -->--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/prioffice.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>

<sec:authorize access="isAuthenticated()">
    <script src="/resources/js/autorizedHeader.js"></script>
    <script src="/resources/js/notification.js"></script>
</sec:authorize>
<!--END of libs-->
<script>

    var tenderId = '${id}';

    function sliderImg(arr) {
        var url = '';
        var imgId = '';
        for (var i in arr) {
            if (arr[i] === 'image') {
                imgId = i;
                url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;

                var element = '<img src="' + url + '" width="200" height="200"/>';
                $('.imgGal').append(element)
            }
        }
    }

    function localDateTime(long) {
        long = new Date(parseInt(long));
        long = moment(long).locale("ru").format('LLL');
        return long;
    }


    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/tenderService/tender/read/id/" + tenderId,
        success: function (response) {
            var data = response;
//            alert(data.body);


            sliderImg(data.uploadFilesIds);
            $(".tender-item-text p").last().html(data.body);
            $(".tender-number").last().text(data.tenderNumber);
            $(".tender-publish-date span").last().text(localDateTime(data.begin));
            $(".tender-veiws").last().text(data.visited);
            $(".tender-proposal-count").last().text(data.proposeNumber);
            $(".tender-name p").last().text(data.title);
            $(".date-create").last().text(localDateTime(data.end));
        }
    });


    $(document).ready(function () {
        $('.slider1').bxSlider({
            slideWidth: 200,
            minSlides: 2,
            maxSlides: 3,
            slideMargin: 5
        });
    });
</script>
</body>
</html>