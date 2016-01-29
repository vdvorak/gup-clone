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

        <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
        <script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
        <script src="/resources/js/common.js"></script>
        <sec:authorize access="isAuthenticated()">
            <script src="/resources/js/autorizedHeader.js"></script>
        </sec:authorize>
        <script>
            var changedProject = {};
            var projectId = '';
            var imgId = '';
            var imagesIds = {};

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
                        $('#projectName').attr('placeholder', projectData.projectName);
                        $('#projectType').attr('placeholder', projectData.typeOfProject);
                        $('#projectDescription').attr('placeholder', projectData.projectDescription);

                    },
                    statusCode: {
                        404: function() {
                            alert('Такого проекта нет');
                            window.location.href = "/projectList?pageNumber=0";
                        }
                    }
                });
            });

            $(document).on('change', '#photoFile', function (e) {

                var formImg = new FormData($('#photoInput')[0]);

                if (imgId !== '') {
                    deleteImgFromDB(imgId);
                }

                $.ajax({
                    type: "POST",
                    url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",
                    data: formImg,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (data, textStatus, request) {
                        imgId = data.id;
                        $('#projectImg').attr("src", "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/" + imgId);
                    }
                });
            });

            $(document).on('click', '#editProject', function (event) {

                changedProject.id = projectId;

                if ( '#projectType'.val() != "") {
                    changedProject.typeOfProject = $('#projectType').val();
                }

                if ($('#projectName').val() != "") {
                    changedProject.projectName = $('#projectName').val();
                }

                if ($('#projectDescription').val() != "") {
                    changedProject.projectDescription = $('#projectDescription').val();
                }

                if (imgId != "")  {
                    imagesIds[imgId] = 1;
                    changedProject.imagesIds = imagesIds;
                }

                $.ajax({
                    type: "POST",
                    url: "/api/rest/projectsAndInvestmentsService/project/edit",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify(changedProject),
                    statusCode: {
                        200: function() {
                            window.location.href = '/project/id/' + projectId;
                        }
                    }
                });
            });

        </script>
    </head>

    <body>
        <jsp:include page="/WEB-INF/templates/common-header.jsp"/>
        <jsp:include page="/WEB-INF/templates/authentification.jsp"/>

        <div>
            <div>
                <h2 align="center">Редактирование проекта</h2>
            </div>
            <div>

                <div>
                    <img id="projectImg" src="#" width="200" height="200">
                </div>

                <div>
                    <form id="projectPhotoInput" enctype="multipart/form-data" method="post">
                        <label for="photoFile"><b>Новая фотография: </b></label>
                        <input id="photoFile" type="file" name="file" multiple accept="image/*,image/jpeg">
                    </form>
                </div>

                <div>
                    <label for="projectName"><b>Название: </b></label>
                    <input id="projectName"/>
                </div>

                <div>
                    <label for="projectType"><b>Тип: </b></label>
                    <select name="projectType" id="projectType">
                        <option value="" selected></option>
                        <option value="RENOVATION">Реструктуризация</option>
                        <option value="PROTOTYPE">Прототип</option>
                        <option value="PROJECT_ON_PAPER">Проект на бумаге</option>
                        <option value="KNOW_HOW">Ноу хау</option>
                    </select>
                </div>

                <div>
                    <label for="projectDescription"><b>Описание: </b></label>
                    <input id="projectDescription"/>
                </div>

                <button id="editProject">Сохранить изменения</button>
            </div>
        </div>
    </body>
</html>

