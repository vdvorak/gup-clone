<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 14.01.2016
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Создание публикации инвестора</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

    <link rel="stylesheet" type="text/css" href="/resources/css/alster.css">

</head>
<body>
<%--<div>--%>
<%--<h2 align="center">Создание публикации инвестора</h2>--%>
<%--</div>--%>

<%--<div>--%>
<%--<label for="investorPostDescription"><b>Описание: </b></label>--%>
<%--<textarea id="investorPostDescription"--%>
<%--placeholder="Минимум 50 символов, максимум 5000." required></textarea>--%>
<%--</div>--%>

<%--<div>--%>
<%--<label for="amountOfMoney"><b>Сумма инвестирования: </b></label>--%>
<%--<input id="amountOfMoney" type="number" min="1"--%>
<%--placeholder="Минимум 1" required>--%>
<%--</div>--%>

<%--<div>--%>
<%--<label for="categoriesOfIndustry"><b>Категории индустрии: </b></label>--%>
<%--<select multiple="multiple" size="4" name="categoriesOfIndustry" id="categoriesOfIndustry" required>--%>
<%--<option value="cat1">Категория 1</option>--%>
<%--<option value="cat2">Категория 2</option>--%>
<%--<option value="cat3">Категория 3</option>--%>
<%--<option value="cat4">Категория 4</option>--%>
<%--</select>--%>
<%--</div>--%>

<%--<button id="createInvestorPost">Создать</button>--%>


<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
    <div class="contentContainer editor" id="tab-container-pVSi">

        <div class="title">РЕДАКТОР</div>
        <form class="investor" action="" id="tabs1-investment">
            <div class="field required kvd">
                <label for="main-kvd-info" class="editorLabel">Выберите отрасль</label>
                <input id="main-kvd-info" type="text" name='text' class="editorInput">
            </div>
            <div class="field required">
                <div class="editorLabel">Указать сумму</div>
            </div>
            <div class="sum">
                <div class="field">
                    <label for="sum1" class="editorLabel">Сумма</label>
                    <input id="sum1" type="number" name='sum1' class="editorInput">
                    <span class="currency">₴</span>
                </div>
                <div class="field">
                    <label for="sum2" class="editorLabel">Сумма</label>
                    <input id="sum2" type="number" name='sum2' class="editorInput">
                    <span class="currency">₴</span>
                </div>
            </div>
            <div class="field description">
                <label for="description" class="editorLabel">Описание</label>
                <textarea id="description" name='description' class="editorInput"></textarea>
            </div>
            <div class="field IMGUploader">
                <div class="titleFile" data-title="Добавить изображение">
                    <button type="submit" class="blogCreationSubmit"></button>
                </div>
                <input type="file" style="display: none;" multiple="multiple" accept="image/*">

                <div class="IMGBlock">
                    <div class="defaultIMG"><img src="/resources/images/defaultIMG.png" alt="defaultIMG"></div>
                    <div class="defaultIMG"><img src="/resources/images/defaultIMG.png" alt="defaultIMG"></div>
                </div>
            </div>
            <div class="field">
                <button type="submit" class="info-submit">Сохранить</button>
            </div>
        </form>

    </div>
</div>


<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

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
<script src="/resources/js/top-offers-block.js"></script>
<script src="/resources/js/top-tenders-block.js"></script>
<script src="/resources/js/top-projects-block.js"></script>


<script>

    var investorPost = {};

    $(document).on('click', '#createInvestorPost', function (event) {

        investorPost.description = $('#investorPostDescription').val();
        investorPost.amountOfMoney = $('#amountOfMoney').val();
        investorPost.categoriesOfIndustry = $('#categoriesOfIndustry').val();

        $.ajax({
            type: "POST",
            url: "/api/rest/projectsAndInvestmentsService/investorPost/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(investorPost),
            success: function (createdInvestorPostId) {
                window.location.href = '/investorPost/id/' + createdInvestorPostId.id;
            },
            error: function (response) {
                alert("Внутренняя ошибка сервера");
            }
        });
    });
</script>
</body>
</html>

