<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru-RU">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>GUP - Проекты</title>
        <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">

    </head>
    <body>



    <%--<!--PAGE CONTENT START-->--%>

    <%--<div class="container2">--%>
        <%--<div class="feedContainer investmentsFeedContainer">--%>
            <%--<ul class="projectsVSInvestments-block">--%>
                <%--<li class="projectsVSInvestments-btn projects" data-atab="projects"><a href="#tabs1-project">ПРОЕКТЫ</a></li>--%>
                <%--<!--Add "active" class to show this element as selected-->--%>
                <%--<li class="projectsVSInvestments-btn investments active" data-atab="investments"><a href="#tabs1-investment">ИНВЕСТИЦИИ</a></li>--%>
            <%--</ul>--%>

            <%--<div class="feedItem vip">--%>
                <%--<!--Add class "vip" to vip-tialize investment-->--%>
                <%--<div class="publishDate">Опубликовано: 22. 10. 16</div>--%>
                <%--<div class="photo border-color">--%>
                    <%--<img src="/resources/css/images/profileListLogo.png" alt="user avatar"></img>--%>
                <%--</div>--%>
                <%--<a href="#" class="content">--%>
                    <%--<div class="title">Заголовок (ФИО/Компании)</div>--%>
                    <%--<div class="desc">Описание</div>--%>
                    <%--<p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
                <%--</a>--%>
                <%--<div class="cats">--%>
                    <%--<!--Recommended max 14 elements-->--%>
                    <%--<ul>--%>
                        <%--<!--Emmet shortcut-->--%>
                        <%--<!--li*14>a[href="#"]>{IT Ресурсы}-->--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>

                <%--<div class="details">--%>
                    <%--<div class="canInvest">15468900$</div>--%>
                    <%--<div class="alreadyInvested">--%>
                        <%--<div class="desc">Проинвестировано:</div>--%>
                        <%--<div class="total">15468900$</div>--%>
                        <%--<div class="totalProjects">25 проектов</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="feedItem">--%>
                <%--<!--Add class "vip" to vip-tialize investment-->--%>
                <%--<div class="publishDate">Опубликовано: 22. 10. 16</div>--%>
                <%--<div class="photo">--%>
                    <%--<img src="/resources/css/images/profileListLogo.png" alt="user avatar"></img>--%>
                <%--</div>--%>
                <%--<a href="#" class="content">--%>
                    <%--<div class="title">Заголовок (ФИО/Компании)</div>--%>
                    <%--<div class="desc">Описание</div>--%>
                    <%--<p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
                <%--</a>--%>
                <%--<div class="cats">--%>
                    <%--<!--Recommended max 14 elements-->--%>
                    <%--<ul>--%>
                        <%--<!--Emmet shortcut-->--%>
                        <%--<!--li*14>a[href="#"]>{IT Ресурсы}-->--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                        <%--<li><a href="#">IT Ресурсы</a></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>

                <%--<div class="details">--%>
                    <%--<div class="canInvest">15468900$</div>--%>
                    <%--<div class="alreadyInvested">--%>
                        <%--<div class="desc">Проинвестировано:</div>--%>
                        <%--<div class="total">15468900$</div>--%>
                        <%--<div class="totalProjects">25 проектов</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="feedFooter"></div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<!--PAGE CONTENT END-->--%>



        <jsp:include page="/WEB-INF/templates/common-header.jsp"/>
        <jsp:include page="/WEB-INF/templates/authentification.jsp"/>

        <div>
            <div>
                <h2 align="center">Публикации инвесторов</h2>
                <h3 align="center"><a href="/investorPost/create">Создать свою публикацию</a></h3>
            </div>
            <div>
                <label id="pageLabel"><b>Страница:</b> </label>
                <p align="left" id="goToPage"></p>
            </div>
            <div>
                <table id="investorPostTable" border="1" width="100%">
                    <thead>
                    <tr>
                        <th>Описание</th>
                        <th>Сумма инвестирования</th>
                        <th>Колличество заявок</th>
                        <th>Дата создания</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>












        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
        <script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
        <script src="/resources/js/common.js"></script>
        <sec:authorize access="isAuthenticated()">
            <script src="/resources/js/autorizedHeader.js"></script>
        </sec:authorize>

        <script>
            $(document).ready(function () {
                var data;
                var investorPostFO = {};
                investorPostFO.skip = ${pageNumber};
                investorPostFO.limit = 10;

                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "/api/rest/projectsAndInvestmentsService/investorPost/read/all",
                    data: JSON.stringify(investorPostFO),
                    success: function (response) {
                        data = response.entities;
                        var goToPageLinks = '';

                        $('#pageLabel').append((investorPostFO.skip + 1) + ' из ' + response.totalEntities);
                        if (investorPostFO.skip > 0) {
                            goToPageLinks += '<a href="/investorPost/list?pageNumber=' + (investorPostFO.skip - 1)  + '"> Назад </a>';
                        }

                        if (investorPostFO.skip < response.totalEntities && response.totalEntities/investorPostFO.limit > 1) {
                            goToPageLinks += '<a href="/investorPost/list?pageNumber=' + (investorPostFO.skip + 1) + '"> Следующая </a>';
                        }
                        $('#goToPage').append(goToPageLinks);

                        for (var i = 0; i < data.length; i++) {
                            var createdDate = new Date(data[i].createdDate);
                            data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();

                            data[i].createdDate = '<a href="/investorPost/id/' + data[i].id + '">' + data[i].createdDate + '</a>';


                            var row = $('<tr>');
                            row.append($('<td>').html(data[i].description));
                            row.append($('<td>').html(data[i].amountOfMoney));
                            row.append($('<td>').html(data[i].totalThoseInNeed));
                            row.append($('<td>').html(data[i].createdDate));

                            $('#investorPostTable').append(row);
                        }
                    }
                });
            });
        </script>
    </body>
</html>