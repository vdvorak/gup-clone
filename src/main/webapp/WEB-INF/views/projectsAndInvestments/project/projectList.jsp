<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 13.01.2016
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
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
    </head>

    <body>
        <div>
            <div>
                <h2 align="center">Проекты</h2>
                <h3 align="center"><a href="/createProject">Создать свой проект</a></h3>
            </div>
            <div>
                <label align="left" id="pageLabel"><b>Страница:</b> </label>
                <p align="left" id="goToPage"></p>
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

        <jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>

        <script>



            $(document).ready(function () {
                var data;
                var projectFO = {};
                projectFO.createdDateSortDirection = "DESC";
                projectFO.includeComments = false;
                projectFO.skip = ${pageNumber};
                projectFO.limit = 10;

                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "/api/rest/projectsAndInvestmentsService/project/read/all",
                    data: JSON.stringify(projectFO),
                    success: function (response) {
                        data = response.entities;
                        var goToPageLinks = '';

                        $('#pageLabel').append((projectFO.skip + 1) + ' из ' + response.totalEntities);
                        if (projectFO.skip > 0) {
                            goToPageLinks += '<a href="/projectList?pageNumber=' + (projectFO.skip - 1)  + '"> Назад </a>';
                        }

                        if (projectFO.skip < response.totalEntities) {
                            goToPageLinks += '<a href="/projectList?pageNumber=' + (projectFO.skip + 1) + '"> Следующая </a>';
                        }
                        $('#goToPage').append(goToPageLinks);

                        for (var i = 0; i < data.length; i++) {
                            data[i].projectName = '<a href="/project/id/' + data[i].id + '">' + data[i].projectName + '</a>';
                            var createdDate = new Date(data[i].createdDate);
                            data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();

                            if (data[i].imagesIds !== null) {
                                for (var key in data[i].imagesIds) {
                                    if (data[i].imagesIds[key] === "1") {
                                        data[i].imagesIds = '<img src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key + '" width="100" height="100">';
                                    }
                                }
                            } else {
                                data[i].imagesIds = {};
                                data[i].imagesIds = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                            }

                            var row = $('<tr>');
                            row.append($('<td>').html(data[i].imagesIds));
                            row.append($('<td>').html(data[i].projectName));
                            row.append($('<td>').html(data[i].typeOfProject));
                            row.append($('<td>').html(data[i].views));
                            row.append($('<td>').html(data[i].totalComments));
                            row.append($('<td>').html(data[i].createdDate));

                            $('#projectsTable').append(row);
                        }
                    }
                });
            });
        </script>
    </body>
</html>
