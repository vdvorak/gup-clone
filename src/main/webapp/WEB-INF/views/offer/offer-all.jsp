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

    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link href="/resources/css/custom-new.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/dropdown-multicolumn.css" rel="stylesheet" type="text/css">
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


<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script>

    //    var filter = {skip: 0, limit: 10};
    var filter = new OfferFilterModule.OfferFilter();

    $.when(loadCategories).done(function() {
        $('.ItemADS div a').click(onClickCategory2lvl);
    });

    $(document).ready(function () {
        filter.parseUrlToFilter()
                .readAllByFilter();
    });

    $('#btn-offers-more').click(function () {
        filter.skip += 10;
        filter.setFilterOptions()
                .readAllByFilter();
    });

    $('#btn-offers-search').click(function (event) {
        event.preventDefault();
        filter.cleanResult()
                .setFilterOptions()
                .readAllByFilter();
    });

    $('#select-categories-3lvl').change(selectCategoryLvl3);

    function selectCategoryLvl3(event) {
        if (filter.categories.length > 2) filter.categories.pop();
        var cat3 = $(event.currentTarget).val();
        if (cat3) {
            filter.categories.push(cat3);
            filter.deleteFilterOptions()
            .drawFilterOptions(cat3);
        }
        $('#filter-price').change();
    }

    function onClickCategory1lvl(event) {
        var id1 = $(event.currentTarget).attr('id');
        filter.categories = [];
        filter.deleteFilterOptions();

        if(id1 !== 'free' && id1 !== 'exchange') {
            filter.categories.push(id1);
        } else {
            $('#filter-price').append('<option selected value="'+ id1 +'" id="'+ id1 +'"></option>');
        }
        filter.cleanResult()
                .drawFilterOptions(filter.categories[0])
                .setFilterOptions()
                .readAllByFilter();

        $('#select-categories-3lvl').css('display', 'none');
        $('label[for="select-categories-3lvl"]').css('display', 'none');
        $('#filter-price').change();

    }

    function onClickCategory2lvl(event) {
        var elem = $(event.currentTarget);
        var id2 = elem.attr('id');
        filter.categories  = [];
        filter.categories.push(elem.parent().parent().children('a:first').attr('id'));
        if(id2) filter.categories.push(id2);

        filter.cleanResult()
                .deleteFilterOptions()
                .drawFilterOptions((id2) ? filter.categories[1] : filter.categories[0])
                .drawCategories3lvl()
                .setFilterOptions()
                .readAllByFilter();

        $('#filter-price').change();
    }

    $('.ItemADS').each(function () {
        $(this).children('a:first').click(onClickCategory1lvl);
    })

    $('#filter-price').change(filter.selectFilterPrice);

    $('#filter-region-container').find('li').click(filter.selectRegionInFilter);


</script>
</body>
</html>
