<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 14.01.2016
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${blog.title} | Портал GUP</title>
    <link href="/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/custom-new.css" rel="stylesheet" type="text/css">


    <link href="/resources/css/bootstrap.css" rel="stylesheet">

    <link href="/resources/css/com.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
    <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
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

<div class="container2">
    <h2 class="title-h2-blue text-center">ТОП обьявлений</h2>

    <ul class="notice-box">
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic1.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic2.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic3.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic4.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
    </ul>

    <ul class="notice-box">
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
        <li>
            <a href="#" class="image"><i class="fa fa-star"></i><img src="images/pic.jpg" alt="">Заголовок</a>
            <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
            <span>Просмотров: 222</span>
        </li>
    </ul>


    <p>&nbsp;</p>
</div>


<%--<div class="container2">--%>

    <%--<div class="contentContainer" style="padding: 5px;">--%>
        <%--<a href="/create-offer">--%>
            <%--<button type="button" id="createProject" class="abutton">Создать объявление</button>--%>
        <%--</a>--%>
    <%--</div>--%>



    <%--<h2>ТОП обьявлений</h2>--%>

    <%--<ul class="notice-box">--%>
    <%--</ul>--%>
    <%--<!-- li pattern for clone -->--%>
    <%--<li id="li-offer-basic" style="display:none">--%>
        <%--<a href="#" class="image"><img src="/resources/images/no_photo.jpg" alt=""><p>Заголовок обьявления</p></a>--%>
        <%--<a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>--%>
        <%--<span>Просмотров: 222</span>--%>
    <%--</li>--%>
    <%--<!-- li pattern for clone -->--%>
    <%--<div class="contentContainer" style="margin-top: 5px">--%>
        <%--<img class="projAndInvestCaretDown" id="btn-offers-more" src="/resources/images/caret.png" alt="caret">--%>
    <%--</div>--%>

    <%--<div class="feedFooter"></div>--%>
<%--</div>--%>


<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<!-- Begin Social buttons js -->
<jsp:include page="/WEB-INF/templates/social-buttons-js.jsp"/>
<!-- End Social buttons js -->
<!-- script references -->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery.maskedinput.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>

<script src="/resources/js/common.js"></script>

<script>

    var filter = {skip: 0, limit: 10};
    var cities;
    var category1Id = '';
    var category2Id = '';
    var category3Id = '';
    var categoryResult = [];
    var parameters = [];
    var properties = [];
    var options;
    var jsonCategory;
    var jsonSubcategory;
    var skip = 10;

    // ---------------    LOAD RESOURCES    --------------------------//
    $(document).ready(function () {
        setFilterProperties();
        readAllByFilter();
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/cities.json",
        dataType: "json",
        success: function (response) {
            cities = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchCategories.json",
        dataType: "json",
        success: function (response) {
            jsonCategory = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchSubcategories.json",
        dataType: "json",
        success: function (response) {
            jsonSubcategory = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/searchValues.json",
        dataType: "json",
        success: function (response) {
            options = response;
        }
    });

    $.ajax({
        type: "GET",
        url: "/resources/json/parameters.json",
        dataType: "json",
        success: function (response) {
            parameters = response;
        }
    });

    // ---------------   END LOAD RESOURCES    --------------------------//

    // ---------------    BEGIN DRAW OFFERS    --------------------------//
    function readAllByFilter() {

        $.ajax({
            type: "POST",
            url: "/api/rest/offersService/offer/read/all",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(filter),
            success: function (response) {
                if (response) {
                    var offersArr = response.entities;
                    var count = 0;
                    var maxCount = 5;

                    for (var i = 0; i < offersArr.length; i++) {
                        var offerObj = offersArr[i];

                        var imagesIds = offerObj.imagesIds;
                        var imgSrc = "";
                        var arrKeys = Object.keys(imagesIds);
                        if(arrKeys.length) {
                            for (var key in imagesIds) {
                                if (imagesIds[key] === 'pic1') {
                                    imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + key;
                                    break;
                                }
                            }
                            if(imgSrc === '') imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + arrKeys[0];
                        } else {
                            imgSrc = "/resources/images/no_photo.jpg";
                        }

                        var priceStr = "Нет цены";
                        if(offerObj.price) {
                            priceStr = offerObj.price.toString();
                            if(offerObj.currency) {
                                priceStr = priceStr + offerObj.currency;
                            }
                        }

                        var newLi = $('#li-offer-basic').clone()
                                .attr('id', "")
                                .css("display", "inline-block");
                        newLi.find('p').text(offerObj.title);
                        newLi.find('img').attr("href", '/offer/' + offerObj.id + '').attr("src", imgSrc);
                        newLi.children('span').text("Просмотров: " + offerObj.views);
                        newLi.find('a.btn').text(priceStr).attr("href", '/offer/' + offerObj.id + '');

                        if (count === maxCount) {
                            count = 0;
                            var newBox = $('ul.notice-box').last()
                                    .clone()
                                    .text("")
                                    .insertAfter($('ul.notice-box').last());
                        }
                        newLi.appendTo($('ul.notice-box').last());
                        count++;
                    }
                }
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    }

    function setFilterProperties() {

    }

    function cleanResult() {
        var offerBoxArr = $('ul.notice-box:not(:first)');
        for (var i = 0; i < offerBoxArr.length; i++) {
            offerBoxArr[i].remove();
        }
        $('ul.notice-box:first').text("");    
    }

    $('#btn-offers-more').click(function () {
        filter.skip += skip;
        readAllByFilter();
    });

    /*  $('#btn-offers-search').click(function () {
     filter = {};
     filter.skip = 0;
     filter.limit = 10;

     cleanResult();

     setFilterProperties();
     readAllByFilter();
     });*/


    // ---------------    END DRAW OFFERS    --------------------------//

</script>
</body>
</html>
