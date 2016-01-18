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
        <h2 align="center">Редактирование проекта</h2>
    </div>
    <div>

        <div>
            <img id="projectImg" src="#" width="200" height="200">
        </div>

        <div>
            <form id="photoInput" enctype="multipart/form-data" method="post">
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

<jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>
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
                if (projectData.imagesIds !== null) {
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

        if ($('#projectType').val() != "") {
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
</body>
</html>

