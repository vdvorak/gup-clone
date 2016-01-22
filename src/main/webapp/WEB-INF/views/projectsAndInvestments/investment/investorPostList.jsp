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
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>

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
</head>
    <body>
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
    </body>
</html>
