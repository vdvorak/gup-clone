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
</head>

    <body>

    <div>
        <div>
            <h2 align="center">Просмотр публикации инвестора</h2>
            <h2 align="center"><a href="/investorPost/list?pageNumber=0">Посмотреть другие публикации</a></h2>
        </div>
        <div>

            <div>
                <label for="investorPostDescription"><b>Описание: </b></label>
                <label id="investorPostDescription"></label>
            </div>

            <div>
                <label for="amountOfMoney"><b>Сумма инвестирования: </b></label>
                <label id="amountOfMoney"></label>
            </div>

            <div>
                <label for="investorPostCreatedDate"><b>Дата создания: </b></label>
                <label id="investorPostCreatedDate"></label>
            </div>

            <div>
                <label for="projectIdInput"><b>Создать заявку на инвестирование: </b></label>
                <input id="projectIdInput" placeholder="Id проекта"/>
                <button type="button" id="sendAppButton">Отправить</button>
            </div>

            <div>
                <label for="applicationsTable" id="applicationsLabel"><b>Заявки на инвестирование: </b></label>
                <table id="applicationsTable" border="1" width="100%">
                    <thead>
                    <tr>
                        <th>Автор заявки</th>
                        <th>Проект</th>
                        <th>Дата создания</th>
                    </tr>
                    </thead>
                </table>
            </div>


        </div>
    </div>

    <jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>
    <script>
        var investorPostId = '';
        var applicationForInvestment = {};

        $(document).ready(function () {
            $.ajax({
                type: "GET",
                url: "/api/rest/projectsAndInvestmentsService/investorPost/id/${investorPostId}/read",
                success: function (investorPostData) {
                    projectId = investorPostData.id;

                    $('#investorPostDescription').text(investorPostData.description);
                    $('#amountOfMoney').text(investorPostData.amountOfMoney);

                    var createdDate = new Date(investorPostData.createdDate);
                    $('#investorPostCreatedDate').text(createdDate.getDate() + '/'
                            + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear());

                    if (investorPostData.thoseInNeed == null || investorPostData.thoseInNeed.length == 0) {
                        $('#applicationsLabel').append('Еще нет заявок');
                        $('#applicationsTable').hide();
                    } else {
                        for (var i = 0; i < investorPostData.thoseInNeed.length; i++) {
                            var createdDate = new Date(investorPostData.thoseInNeed[i].createdDate);
                            investorPostData.thoseInNeed[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();
                            investorPostData.thoseInNeed[i].projectId =
                                    "<a href='/project/id/" + investorPostData.thoseInNeed[i].projectId + "'>Посмотреть проект</a>";

                            var row = $('<tr>');
                            row.append($('<td>').html(investorPostData.thoseInNeed[i].uId));
                            row.append($('<td>').html(investorPostData.thoseInNeed[i].projectId));
                            row.append($('<td>').html(investorPostData.thoseInNeed[i].createdDate));

                            $('#applicationsTable').append(row);
                        }
                    }
                }
            });
        });


        $(document).on('click', '#sendAppButton', function (event) {
            applicationForInvestment.projectId = $('#projectIdInput').val();

            $.ajax({
                type: "POST",
                url: "/api/rest/projectsAndInvestmentsService/investorPost/id/${investorPostId}/apply",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(applicationForInvestment),
                statusCode: {
                    200: function() {
                        alert('Вы отправили заявку на инвестирование');
                        window.location.reload();
                    },
                    404: function() {
                        alert('Проект с таким id не найден. Проверьте введенный id.');
                    }
                }
            });
        });

    </script>
    </body>
</html>

