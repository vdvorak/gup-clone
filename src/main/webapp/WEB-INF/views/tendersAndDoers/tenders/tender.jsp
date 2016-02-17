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
<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>
<!--END 2nd section -->


<section>
    <div class="tender-wrap">
        <div class="tender-tabs-wrap">
            <div class="tabs">

                <div>

                    <!-- Repeated section with tender -->

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

                                <div class="map">
                                </div>

                                <sec:authorize access="isAuthenticated()">
                                    <div id="no-propose" style="display: none">Нет предложений. Будье первыми!
                                    </div>

                                    <div id="start">
                                        <div class="proposes-wraper" style="outline: 2px solid #000;">
                                            <div class="propose-author">Вася</div>
                                            <img class="member-pic" src="#" width="50" height="50">

                                            <div class="propose-date"> 1 февраля</div>
                                            <button class="chooseWinner">Выбрать победителем</button>
                                            <div class="poropse-text">Азазаз</div>
                                        </div>
                                    </div>

                                    <div class="offer-wraper" style="height: 200px; background-color: #006dcc">
                                        <div class="offer-input-group">
                                            <textarea id="tenderPropose"></textarea>

                                            <div id="textLength"></div>
                                        </div>

                                        <input id="visionSelect" type="checkbox"><label for="visionSelect">Скрыть
                                        предложение от других участников</label>

                                        <button id="makePropose" disabled>Отправить</button>
                                    </div>
                                </sec:authorize>

                            </div>
                        </div>
                    </div>
                    <%--<!-- End of repeated section with tender -->--%>

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
</sec:authorize>
<!--END of libs-->
<script>

    var proposes;

    var firstBlock = $('#start').html();





    // ----------------------- Begin Tender propose text length counter ------------------------------
    $("#tenderPropose").on('keyup', function (event) {
        var button = $('#makePropose');
        var counter = $("#textLength");

        var currentString = $("#tenderPropose").val();
        counter.html(currentString.length);
        if (currentString.length <= 50) {  /*or whatever your number is*/
            button.attr("disabled", true);
            counter.css("color", "red");
        } else {
            if (currentString.length > 500) {
                button.attr("disabled", true);
                counter.css("color", "red");
            } else {
                button.attr("disabled", false);
                counter.css("color", "green");
            }
        }
    });
    // ----------------------- End Tender propose text length counter ------------------------------


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

            sliderImg(data.uploadFilesIds);
            $(".tender-item-text p").last().html(data.body);
            $(".tender-number").last().text(data.tenderNumber);
            $(".tender-publish-date span").last().text(localDateTime(data.begin));
            $(".tender-veiws").last().text(data.visited);
            $(".tender-proposal-count").last().text(data.proposeNumber);
            $(".tender-name p").last().text(data.title);
            $(".date-create").last().text(localDateTime(data.end));


            var map = '<iframe width="500" height="400" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=place_id:' + data.address.googleMapKey + '&key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww" allowfullscreen></iframe>';
            $('.map').append(map);


            if (data.proposes === undefined) {
                if (data.proposes.length < 1) {
                    $('#no-propose').attr('style', 'display: ');
                    $('#start').attr('style', 'display: none')
                }
            }


            for (var i in data.proposes) {
                $(".propose-author").last().text(data.proposes[i].authorId);
                $(".propose-date").last().text(localDateTime(data.proposes[i].time));
                $(".poropse-text").last().text(data.proposes[i].body);
                $(".chooseWinner").last().attr('id', data.proposes[i].authorId);
                $('#start').append(firstBlock);
            }

            $(".chooseWinner").on('click', function () {
                alert("Азазаз");

                var Tender = {};
                Tender.winnerId = $(this).attr('id');
                Tender.id = tenderId;

                $.ajax({
                    type: "POST",
                    url: "/api/rest/tenderService/tender/chooseWinner",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify(Tender),
                    statusCode: {
                        200: function () {
                            alert("Победитель выбран!")
                        }
                    }
                });

            });


            $('.proposes-wraper').last().attr('style', 'display: none;');

            for (var j in data.members) {
                $('.propose-author').each(function (index) {
                    if ($(this).text() === data.members[j].id) {
                        $(this).text(data.members[j].name);
                        $(this).next('.member-pic').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data.members[j].userPic)
                    }
                });
            }
        },
        statusCode: {
            403: function () {
                $('.tender-tabs-items-wrap').detach();
                $('.tender-wrap').text("Войдите в систему, чтобы просмотреть информацию о тендере.");
            }
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

    // ----------------- BEGIN Propose sent -------------------------------------------------
    $('#makePropose').on('click', function () {
        var Propose = {};
        Propose.body = $('#tenderPropose').val();
        Propose.hidden = $('#visionSelect').prop('checked');


        $.ajax({
            type: "POST",
            url: "/api/rest/tenderService/tender/id/" + tenderId + "/propose/create/",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(Propose),
            statusCode: {
                201: function () {
                    window.location.href = '/tender/' + tenderId;
                }
            }
        });
    });

    // ----------------- END Propose sent -------------------------------------------------
</script>
</body>
</html>