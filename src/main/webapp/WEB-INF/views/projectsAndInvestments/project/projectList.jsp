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
                <table id="projects" border="1" width="100%">
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
                projectFO.skip = 0;
                projectFO.limit = 10;

                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "/api/rest/projectsAndInvestmentsService/project/read/all",
                    data: JSON.stringify(projectFO),
                    success: function (response) {
                        data = response.entities;

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
                        }

                        var table = $('#projects').DataTable({
                            select: {
                                style: 'single'
                            },
                            data: data,
                                "columns": [
                                    {"data": "imagesIds"},
                                    {"data": "projectName"},
                                    {"data": "typeOfProject"},
                                    {"data": "views"},
                                    {"data": "totalComments"},
                                    {"data": "createdDate"}
                                ],
                            "language": {
                                "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
                            }
                        });

                    }
                });
            });
        </script>
    </body>
</html>
