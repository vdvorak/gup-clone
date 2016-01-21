-<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 19.01.2016
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <title>Новости</title>
</head>
<body>
    <div align="center">
        <input id="tagsName" size="100" placeholder="Название новости">
        <button id="findBlogPostsButton">Найти новость</button>
    </div>
    <%--<div id="paginationDiv">--%>
        <%--<label id="pageLabel"><b>Страница:</b> </label>--%>
        <%--<p align="left" id="goToPage"></p>--%>
    <%--</div>--%>
    <div>
        <table id="blogPostsTable" border="1" width="100%">
            <thead>
            <tr>
                <th>Фото</th>
                <th>Название</th>
                <th>Просмотры</th>
                <th>Дата создания</th>
                <th>Колличество комментариев</th>
                <th>Дизлайки</th>
                <th>Лайки</th>
            </tr>
            </thead>
        </table>
    </div>
    </div>
    <%--<div id="wrapper">--%>
        <%--<c:forEach var="newsPost" items="${news.entities}">--%>
            <%--<div>--%>
                <%--Дата создания: <span class="date-create">${newsPost.createdDate}</span>--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--Просмотры: ${newsPost.views}--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--<a href="/blog-post/view/${newsPost.id}">${newsPost.title}</a>--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--<a href="/blog-post/view/${newsPost.id}">--%>
                    <%--<c:forEach var="id" items="${newsPost.imagesIds.keySet()}" end="0">--%>
                        <%--<img src="/api/rest/fileStorage/NEWS/file/read/id/${id}" width="200px" height="200px">--%>
                    <%--</c:forEach>--%>
                <%--</a>--%>
            <%--</div>--%>
            <%--<div>--%>
                    <%--${newsPost.text}--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--Колличество комментариев: ${newsPost.totalComments}--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--Дизлайки: ${newsPost.totalDislikes}--%>
            <%--</div>--%>
            <%--<div>--%>
                <%--Лайки: ${newsPost.totalLikes}--%>
            <%--</div>--%>
    <%----%>
            <%--<br>--%>
            <%--<hr>--%>
        <%--</c:forEach>--%>
    <%--</div>--%>


    <script src="/resources/libs/jquery-1.11.3.min.js"></script>
    <script src="/resources/js/moment-with-locales.js"></script>
    <script src="/resources/js/service.js"></script>

    <script>
        $(function() {
            $("#tagsName").autocomplete({
                source: function (request, response) {
                    $.getJSON("${pageContext.request.contextPath}/search/blogPost", {
                        term: request.term
                        }, response);
                }
            });
        });

        $(document).on('click', '#findBlogPostsButton', function (event) {
            $("#blogPostsTable").find("tr:not(:first)").remove();
            $("#paginationDiv").remove();

            var data;
            var projectFO = {};
            projectFO.searchField = $("#tagsName").val();
            projectFO.createdDateSortDirection = "DESC";
            projectFO.includeComments = false;
            projectFO.skip = ${pageNumber};
            projectFO.limit = 50;

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

                    if (projectFO.skip < response.totalEntities && response.totalEntities/projectFO.limit > 1) {
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
