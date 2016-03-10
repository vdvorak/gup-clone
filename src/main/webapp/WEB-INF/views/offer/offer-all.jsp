<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <title>Объявления | Портал GUP</title>

    <link rel="shortcut icon" href="/resources/images/favicon.ico" />
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link href="/resources/css/custom-new.css" rel="stylesheet" type="text/css">
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

    <div class="contentContainer" style="padding: 5px;">
        <a href="/create-offer">
            <button type="button" id="createProject" class="abutton">Создать объявление</button>
        </a>
    </div>

    <h2>ТОП обьявлений</h2>

    <ul class="notice-box">
    </ul>
    <!-- li pattern for clone -->
    <li id="li-offer-basic" style="display:none">
        <a href="#" class="image"><img src="/resources/images/no_photo.jpg" alt="">

            <p>Заголовок обьявления</p></a>
        <a href="#" class="btn btn-xs btn-warning">1 000 грн.</a>
        <span>Просмотров: 222</span>
    </li>
    <!-- li pattern for clone -->
    <div class="contentContainer" style="margin-top: 5px">
        <img class="projAndInvestCaretDown" id="btn-offers-more" src="/resources/images/caret.png" alt="caret">
    </div>

    <div class="feedFooter"></div>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>
<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>
<script src="/resources/js/enscroll-0.6.1.min.js"></script>

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
                        if (arrKeys.length) {
                            for (var key in imagesIds) {
                                if (imagesIds[key] === 'pic1') {
                                    imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + key;
                                    break;
                                }
                            }
                            if (imgSrc === '') imgSrc = '/api/rest/fileStorage/OFFERS/file/read/id/' + arrKeys[0];
                        } else {
                            imgSrc = "/resources/images/no_photo.jpg";
                        }

                        var priceStr = "Нет цены";
                        if (offerObj.price) {
                            priceStr = offerObj.price.toString();
                            if (offerObj.currency) {
                                priceStr = priceStr + offerObj.currency;
                            }
                        }

                        var newLi = $('#li-offer-basic').clone()
                                .attr('id', "")
                                .css("display", "inline-block");
                        newLi.find('p').text(offerObj.title);
                        newLi.find('.image').attr("href", '/offer/' + offerObj.id + '');
                        newLi.find('img').attr("src", imgSrc);


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
