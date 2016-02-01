<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru-RU">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>GUP - Проекты</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
        <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
    </head>

    <body>

        <jsp:include page="/WEB-INF/templates/common-header.jsp"/>
        <jsp:include page="/WEB-INF/templates/authentification.jsp"/>

        <div>
            <div>
                <h2 align="center">Проекты</h2>
                <h3 align="center"><a href="/createProject">Создать свой проект</a></h3>
                <h3 align="center"><a href="/investorPost/list?pageNumber=0">Публикации инвесторов</a></h3>
            </div>
            <div align="center">
                    <input id="searchInput" size="100" placeholder="Название проекта">
                    <button id="findPojectsButton">Найти проект</button>
            </div>
            <div id="paginationDiv">
                <label id="pageLabel" for="pageNumLine"><b>Страница:</b> <label id="pageNumLine"></label></label>

                <button id="prevPageButton">Назад</button>
                <button id="nextPageButton">Вперед</button>
            </div>
            <div>
                <table id="projectsTable" border="1" width="100%">
                    <thead>
                        <tr>
                            <th>Фото</th>
                            <th>Название</th>
                            <th>Тип</th>
                            <th>Просмотры</th>
                            <th>Колл. комментариев</th>
                            <th>Дата создания</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>


        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
        <script src="/resources/js/common.js"></script>
        <sec:authorize access="isAuthenticated()">
            <script src="/resources/js/autorizedHeader.js"></script>
        </sec:authorize>
        <script>
            var projectFO = {skip:0, limit:20};

            <c:if test="${projectFO != null}">
                projectFO = ${projectFO};
                projectFO.includeComments = false;
            </c:if>

            function updateProjectsTable(projectFO) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "/api/rest/projectsAndInvestmentsService/project/read/all",
                    data: JSON.stringify(projectFO),
                    success: function (response) {
                        $("#projectsTable").find("tr:not(:first)").remove();

                        updatePaginationBlock(response);

                        response.entities.forEach(function(project) {
                            project.projectName = '<a href="/project/id/' + project.id + '">' + project.projectName + '</a>';
                            var createdDate = new Date(project.createdDate);
                            project.createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();

                            if (project.imagesIds !== null && project.imagesIds != '') {
                                for (var key in project.imagesIds) {
                                    if (project.imagesIds[key] === "1") {
                                        project.imagesIds = '<img src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key + '" width="100" height="100">';
                                    }
                                }
                            } else {
                                project.imagesIds = {};
                                project.imagesIds = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                            }

                            var row = $('<tr>');
                            row.append($('<td>').html(project.imagesIds));
                            row.append($('<td>').html(project.projectName));
                            row.append($('<td>').html(project.typeOfProject));
                            row.append($('<td>').html(project.views));
                            row.append($('<td>').html(project.totalComments));
                            row.append($('<td>').html(project.createdDate));

                            $('#projectsTable').append(row);
                        });
                    }
                });
            }

            $(function() {
                $("#searchInput").autocomplete({
                    source: function (request, response) {
                        $.getJSON("${pageContext.request.contextPath}/search/project", {
                            term: request.term
                        }, response);
                    }
                });
            });

            $(document).ready(function () {
                $('#searchInput').val(projectFO.searchField);
                updateProjectsTable(projectFO);
            });

            function updatePaginationBlock(responseEntities) {
                $('#pageNumLine').html((projectFO.skip/projectFO.limit + 1) + ' из ' + Math.ceil(responseEntities.totalEntities/projectFO.limit));

                if (projectFO.skip < projectFO.limit) {
                    $('#prevPageButton').hide();
                } else {
                    $('#prevPageButton').show();
                }

                if ((projectFO.skip +  projectFO.limit) >= responseEntities.totalEntities) {
                    $('#nextPageButton').hide();
                } else {
                    $('#nextPageButton').show();
                }
            }

            $(document).on('click', '#prevPageButton', function () {
                projectFO.skip -= projectFO.limit;
                updateProjectsTable(projectFO);
            });

            $(document).on('click', '#nextPageButton', function () {
                projectFO.skip += projectFO.limit;
                updateProjectsTable(projectFO);
            });

            $(document).on('click', '#findPojectsButton', function () {
                projectFO.skip = 0;

                if ($("#searchInput").val() == "") {
                    $("#searchInput").focus();
                } else {
                    projectFO.searchField = $("#searchInput").val();
                }

                updateProjectsTable(projectFO);
            });
        </script>
    </body>
</html>
