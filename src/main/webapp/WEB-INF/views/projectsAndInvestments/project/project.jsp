<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>GUP - Проекты</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
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
            <h2 align="center">Просмотр проекта</h2>
            <h2 align="center"><a href="/editProject/id/${projectId}">Редактировать проект</a></h2>
            <h2 align="center"><a href="/projectList?pageNumber=0">Посмотреть другие проекты</a></h2>
        </div>
        <div>

            <div>
                <img id="projectImg" src="#" width="200" height="200">
            </div>

            <div>
                <label for="projectName"><b>Название: </b></label>
                <label id="projectName"></label>
            </div>

            <div>
                <label for="projectType"><b>Тип: </b></label>
                <label id="projectType"></label>
            </div>

            <div>
                <hr>
                <label for="projectDescription"><b>Описание: </b></label>
                <label id="projectDescription"></label>
                <hr>
            </div>

            <div>
                <label for="amountRequested"><b>Нужная сумма: </b></label>
                <label id="amountRequested"></label>
            </div>

            <div>
                <label for="investedAmount"><b>Собраная сумма: </b></label>
                <label id="investedAmount"></label>
            </div>

            <div>
                <label for="totalScore"><b>Оценка: </b></label>
                <label id="totalScore"></label>
            </div>

            <div>
                <label for="projectCreatedDate"><b>Дата создания: </b></label>
                <label id="projectCreatedDate"></label>
            </div>
            <!-- Begin Social buttons html-->
            <jsp:include page="/WEB-INF/templates/social-button-html.jsp"/>
            <!-- End Social buttons html -->

            <div>
                <select name="projectScore" id="projectScore" required>
                    <option value="1">1</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                </select>
                <button type="button" id="voteButton">Проголосовать</button>
            </div>

            <div>
                <div>
                    <label><b>Написать комментарий: </b></label>
                </div>
                <textarea name="comment" id="comment" cols="40" rows="5"
                          placeholder="Минимум 5 символов"></textarea>
                <button type="button" id="commentButton">Комментировать</button>
            </div>

            <div>
                <label for="commentsTable" id="commentsLabel"><b>Комментарии к проекту: </b></label>
                <table id="commentsTable" border="1" width="100%">
                    <thead>
                    <tr>
                        <th>Комментарий</th>
                        <th>От кого</th>
                        <th>Дата создания</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>


    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
    <script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
    <script src="/resources/js/common.js"></script>
    <sec:authorize access="isAuthenticated()">
        <script src="/resources/js/autorizedHeader.js"></script>
    </sec:authorize>

    <!-- Begin Social buttons js -->
    <jsp:include page="/WEB-INF/templates/social-buttons-js.jsp"/>
    <!-- End Social buttons js -->


    <script>
        var projectId = '';
        var comment = {};

        $(document).ready(function () {
            $.ajax({
                type: "GET",
                url: "/api/rest/projectsAndInvestmentsService/project/id/${projectId}/read",
                success: function (projectData) {
                    projectId = projectData.id;
                    if (projectData.imagesIds !== null && projectData.imagesIds != '') {
                        for (var key in projectData.imagesIds) {
                            if (projectData.imagesIds[key] === "1") {
                                $('#projectImg').attr('src','/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key);
                                break;
                            }
                        }
                    } else {
                        $('#projectImg').attr('src','/resources/images/no_photo.jpg');
                    }
                    $('#projectName').text(projectData.projectName);
                    $('#projectType').text(projectData.typeOfProject);
                    $('#projectDescription').html(projectData.projectDescription);
                    $('#amountRequested').text(projectData.amountRequested);
                    $('#investedAmount').text(projectData.investedAmount);
                    $('#totalScore').text(projectData.totalScore);

                    var createdDate = new Date(projectData.createdDate);
                    $('#projectCreatedDate').text(createdDate.getDate() + '/'
                            + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear());

                    if (projectData.comments == null || projectData.comments.length == 0) {
                        $('#commentsLabel').append('Еще нет комментариев');
                        $('#commentsTable').hide();
                    } else {
                        for (var i = 0; i < projectData.comments.length; i++) {
                            var createdDate = new Date(projectData.comments[i].createdDate);
                            projectData.comments[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();

                            var row = $('<tr>');
                            row.append($('<td>').html(projectData.comments[i].comment));
                            row.append($('<td>').html(projectData.comments[i].fromId));
                            row.append($('<td>').html(projectData.comments[i].createdDate));

                            $('#commentsTable').append(row);
                        }
                    }
                },
                statusCode: {
                    404: function() {
                        alert('Такого проекта нет');
                        window.location.href = "/project/list";
                    }
                }
            });
        });

        $(document).on('click', '#voteButton', function (event) {
            $.ajax({
                type: "POST",
                url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/vote/" + $('#projectScore').val(),
                success: function () {
                    alert('Вы проголосовали за проект');
                    window.location.reload();
                },
                error: function (response) {
                    alert("Внутренняя ошибка сервера");
                }
            });
        });

        $(document).on('click', '#commentButton', function (event) {

            comment.toId = projectId;
            comment.comment = $('#comment').val();
            $.ajax({
                type: "POST",
                url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/comment/create",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(comment),
                success: function () {
                    alert('Вы прокомментировали проект');
                    window.location.reload();
                },
                statusCode: {
                    409: function() {
                        alert('Сначала нужно проголосовать');
                    }
                }
            });
        });

    </script>
</body>
</html>

