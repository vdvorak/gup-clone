<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Проекты | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

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

    <body>

        <!--PAGE CONTENT START-->
        <div class="container2">
            <div class="projectFeedContainer feedContainer contentContainer">
                <div class="projectsVSInvestments-block">
                    <div class="projectsVSInvestments-btn projects selected">ПРОЕКТЫ</div>
                    <!--Add "selected" class to show this element as selected-->
                    <div class="projectsVSInvestments-btn investments">ИНВЕСТИЦИИ</div>
                </div>
                <div class="projectsVSInvestmentsCats">
                    <div class="catContainer selected">
                        <!--Add "selected" class to show this element as selected-->
                        <div class="catLogo restruct"></div>
                        <div class="catName">Реструктуризация</div>
                    </div>
                    <div class="catContainer">
                        <div class="catLogo prototype"></div>
                        <div class="catName">Готовый прототип</div>
                    </div>
                    <div class="catContainer">
                        <div class="catLogo paper"></div>
                        <div class="catName">Проект на бумаге</div>
                    </div>
                    <div class="catContainer">
                        <div class="catLogo nouHau"></div>
                        <div class="catName">Ноу-Хау</div>
                    </div>
                </div>

                <div class="feedItem vip">
                    <!--Add class "vip" to vip-tialize project-->
                    <div class="publishDate">Опубликовано: 22. 10. 16</div>
                    <div class="preview">
                        <img src="/resources/css/images/sample/project2.png" alt="project photo" />
                        <div class="likes">
                            <div class="hearthPlace">
                                <div class="hearth"></div>
                            </div>
                            <div class="number">22 000</div>
                        </div>
                    </div>
                    <a href="#" class="content">
                        <div class="title">Название проекта</div>
                        <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                    </a>
                    <div class="bottomContent">
                        <button type="button" class="abutton invest">Инвестировать</button>
                        <div class="projectProgressBlock">
                            <div class="current elem cash">234 $</div>
                            <div class="bar elem">
                                <div class="colored"></div>
                                <div class="empty" style="width: 30%;"></div>
                                <!--Change style width percentage to vizualize progress (INVERTED). Допускаються значення квантовані по 5 процентів, тобто типу такі: 0, 5, 10, 15, 20, ...-->
                            </div>
                            <div class="todo elem cash">2304 $</div>
                        </div>
                    </div>
                </div>
                <div class="feedItem">
                    <!--Add class "vip" to vip-tialize project-->
                    <div class="preview">
                        <img src="/resources/css/images/sample/project1.png" alt="project photo" />
                        <div class="likes">
                            <div class="hearthPlace">
                                <div class="hearth"></div>
                            </div>
                            <div class="number">22 000</div>
                        </div>
                    </div>
                    <a href="#" class="content">
                        <div class="publishDate">Опубликовано: 22. 10. 16</div>
                        <div class="title">Название проекта</div>
                        <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                    </a>
                    <div class="bottomContent">
                        <button type="button" class="abutton invest">Инвестировать</button>
                        <div class="projectProgressBlock">
                            <div class="current elem cash">234 $</div>
                            <div class="bar elem">
                                <div class="colored"></div>
                                <div class="empty" style="width: 70%;"></div>
                                <!--Change style width percentage to vizualize progress (INVERTED). Допускаються значення квантовані по 5 процентів, тобто типу такі: 0, 5, 10, 15, 20, ...-->
                            </div>
                            <div class="todo elem cash">2304 $</div>
                        </div>
                    </div>
                </div>

                <div class="feedFooter"></div>
            </div>
        </div>

        <!--PAGE CONTENT END-->


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

        <script>
            $("#selectedService option[value='project']").attr("selected","selected");

            <%--var projectFO = {skip:0, limit:20};--%>

            <%--<c:if test="${projectFO != null}">--%>
                <%--projectFO = ${projectFO};--%>
                <%--projectFO.includeComments = false;--%>
            <%--</c:if>--%>

            <%--function updateProjectsTable(projectFO) {--%>
                <%--$.ajax({--%>
                    <%--type: "POST",--%>
                    <%--contentType: "application/json; charset=utf-8",--%>
                    <%--url: "/api/rest/projectsAndInvestmentsService/project/read/all",--%>
                    <%--data: JSON.stringify(projectFO),--%>
                    <%--success: function (response) {--%>
                        <%--$("#projectsTable").find("tr:not(:first)").remove();--%>

                        <%--updatePaginationBlock(response);--%>

                        <%--response.entities.forEach(function(project) {--%>
                            <%--project.projectName = '<a href="/project/id/' + project.id + '">' + project.projectName + '</a>';--%>
                            <%--var createdDate = new Date(project.createdDate);--%>
                            <%--project.createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();--%>

                            <%--if (project.imagesIds !== null && project.imagesIds != '') {--%>
                                <%--for (var key in project.imagesIds) {--%>
                                    <%--if (project.imagesIds[key] === "pic1") {--%>
                                        <%--project.imagesIds = '<img src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key + '" width="100" height="100">';--%>
                                    <%--}--%>
                                <%--}--%>
                            <%--} else {--%>
                                <%--project.imagesIds = {};--%>
                                <%--project.imagesIds = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';--%>
                            <%--}--%>

                            <%--var row = $('<tr>');--%>
                            <%--row.append($('<td>').html(project.imagesIds));--%>
                            <%--row.append($('<td>').html(project.projectName));--%>
                            <%--row.append($('<td>').html(project.typeOfProject));--%>
                            <%--row.append($('<td>').html(project.views));--%>
                            <%--row.append($('<td>').html(project.totalComments));--%>
//                            row.append($('<td>').html(project.createdDate));

                            <%--$('#projectsTable').append(row);--%>
                        <%--});--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>

            <%--$(function() {--%>
                <%--$("#searchInput").autocomplete({--%>
                    <%--source: function (request, response) {--%>
                        <%--$.getJSON("${pageContext.request.contextPath}/search/project", {--%>
                            <%--term: request.term--%>
                        <%--}, response);--%>
                    <%--}--%>
                <%--});--%>
            <%--});--%>

            <%--$(document).ready(function () {--%>
                <%--$('#searchInput').val(projectFO.searchField);--%>
                <%--updateProjectsTable(projectFO);--%>
            <%--});--%>

            <%--function updatePaginationBlock(responseEntities) {--%>
                <%--$('#pageNumLine').html((projectFO.skip/projectFO.limit + 1) + ' из ' + Math.ceil(responseEntities.totalEntities/projectFO.limit));--%>

                <%--if (projectFO.skip < projectFO.limit) {--%>
                    <%--$('#prevPageButton').hide();--%>
                <%--} else {--%>
                    <%--$('#prevPageButton').show();--%>
                <%--}--%>

                <%--if ((projectFO.skip +  projectFO.limit) >= responseEntities.totalEntities) {--%>
                    <%--$('#nextPageButton').hide();--%>
                <%--} else {--%>
                    <%--$('#nextPageButton').show();--%>
                <%--}--%>
            <%--}--%>

            <%--$(document).on('click', '#prevPageButton', function () {--%>
                <%--projectFO.skip -= projectFO.limit;--%>
                <%--updateProjectsTable(projectFO);--%>
            <%--});--%>

            <%--$(document).on('click', '#nextPageButton', function () {--%>
                <%--projectFO.skip += projectFO.limit;--%>
                <%--updateProjectsTable(projectFO);--%>
            <%--});--%>

            <%--$(document).on('click', '#findPojectsButton', function () {--%>
                <%--projectFO.skip = 0;--%>

                <%--if ($("#searchInput").val() == "") {--%>
                    <%--$("#searchInput").focus();--%>
                <%--} else {--%>
                    <%--projectFO.searchField = $("#searchInput").val();--%>
                <%--}--%>

                <%--updateProjectsTable(projectFO);--%>
            <%--});--%>
        </script>
    </body>
</html>
