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

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

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


<!--PAGE CONTENT START-->
<div class="container2">
    <div class="contentContainer" style="margin-top: 100px;"> <!-- Add "vip" class for "vip" users :3 -->
        <div class="tenderContent">
            <div class="topSection">
                <div class="statInfo">
                    <div class="publishDate tender-publish-date">Опубликовано: <span></span></div>


                    <!--Need fix-->
                    <br>

                    <div class="tender-veiws">Просмотров: <span></span></div>
                    <br>

                    <div class="tender-proposal-count">Предложений: <span></span></div>
                    <br>
                    <!--Need fix-->

                    <span class="number visible">№<span class="tender-number"></span></span>

                    <div class="sum tender-expectedPrice"><span></span>₴</div>
                </div>
                <div class="clearfix"></div>
                <div class="tenderButtons">
                    <div class="participate">
                        <div class="clock">
                            <div class="time date-finish"></div>
                        </div>
                        <button type="button" class="abutton blue">Участвовать</button>
                    </div>
                    <button type="button" class="abutton leaveProposal">Оставить предложение</button>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="title tender-name">Название тендера</div>
            <%--<img src="/resources/css/images/sample/tender1.png" alt="" class="mainPhoto">--%>
            <p class="text tender-item-text">

            </p>

            <div class="bottomSection">

            </div>

        </div>
        <div class="sliderTender">
            <ul class="bxsliderTender">
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider"/></li>
                <li><img src="/resources/images/tenderSlider.png" alt="tenderSlider"/></li>
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
        </div>
        <div class="downComments"><p>КОММЕНТАРИИ</p></div>
    </div>

    <div class="colNewsComments">
        <div class="newsComments">
            <div class="clearfix"></div>
            <p class="newsCommentsHeader">ПРЕДЛОЖЕНИЯ</p>

            <form action="#" role="form" id="newsCommentsForm">
                <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий"
                          maxlength="2000" required></textarea>
                <input id="visionSelect" type="checkbox"><label for="visionSelect">Скрыть
                предложение от других участников</label>
                <button id="makePropose" class="newsFormSubmit">Отправить</button>
            </form>
            <p id="chars">2000 символов осталось</p>
        </div>
    </div>
    <div class="colComments" id="commentStart">



        <div class="comments">
            <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
            <a class="NameUser propose-author" href="#">Вася Петров</a>
            <p class="propose-date">Дата публикации: <span></span></p>
            <p class="commentUser poropse-text">Lorem </p>
            <button class="chooseWinner">Выбрать победителем</button>
        </div>


    </div>
    <div class="clearfix"></div>
</div>
<!--PAGE CONTENT END-->


<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>

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

<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>

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

                var element = '<li><img src="' + url + '" /></li>';
                $('.bxsliderTender').append(element)
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
            $(".tender-item-text").last().html(data.body);
            $(".tender-number").last().text(data.tenderNumber);
            $(".tender-publish-date span").last().text(localDateTime(data.begin));
            $(".tender-veiws span").last().text(data.visited);
            $(".tender-proposal-count span").last().text(data.proposeNumber);
            $(".tender-expectedPrice span").last().text(data.expectedPrice);
            $(".tender-name").last().text(data.title);


            $(".date-finish").last().text(localDateTime(data.end));


            var map = '<iframe width="600" height="450" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=place_id:' + data.address.googleMapKey + '&key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww" allowfullscreen></iframe>';
            $('.tenderMap').append(map);

// ------------------------- Propose bulid block ---------------------------------------------------------------------
            for (var i in data.proposes) {
                $('#commentStart').append($('.comments').last().clone());
                $(".propose-author").last().text(data.proposes[i].authorId);
                $(".propose-date span").last().text(localDateTime(data.proposes[i].time));
                $(".poropse-text").last().text(data.proposes[i].body);
                $(".chooseWinner").last().attr('id', data.proposes[i].authorId);
            }
            $('.comments').first().remove();
// ------------------------- Propose bulid block ---------------------------------------------------------------------




            $(".chooseWinner").on('click', function () {
                alert($(this).attr('id'));

                var Tender = {};
                Tender.winnerId = $(this).attr('id');
                Tender.id = tenderId;
                alert(Tender.winnerId);
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
        Propose.body = $('#comment').val();
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
<script src="/resources/js/jquery.bxslider.js"></script>
</body>
</html>