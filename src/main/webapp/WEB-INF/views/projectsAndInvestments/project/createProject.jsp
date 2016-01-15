<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 13.01.2016
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Создание Проекта</title>
</head>
    <body>
        <div>
            <h2 align="center">Создание Проекта</h2>
        </div>
        <div>
            <label for="projectType"><b>Тип: </b></label>
            <select name="projectType" id="projectType" required>
                <option value="RENOVATION">Реструктуризация</option>
                <option value="PROTOTYPE">Прототип</option>
                <option value="PROJECT_ON_PAPER">Проект на бумаге</option>
                <option value="KNOW_HOW">Ноу хау</option>
            </select>
        </div>

        <div>
            <label for="projectName"><b>Название: </b></label>
            <input id="projectName" type="text" name="projectName" min="4" maxlength="140"
                   placeholder="Минимум 4 символа" required>
        </div>

        <div>
            <label for="projectDescription"><b>Описание: </b></label>
            <textarea id="projectDescription"
                      placeholder="Минимум 50 символов, максимум 5000ю" required></textarea>
        </div>

        <div>
            <label for="amountRequested"><b>Нужная сумма: </b></label>
            <input id="amountRequested" type="number" min="1"
                   placeholder="Минимум 1" required>
        </div>

        <div>
            <label for="categoriesOfIndustry"><b>Категории индустрии: </b></label>
            <select multiple="multiple" size="4" name="categoriesOfIndustry" id="categoriesOfIndustry" required>
                <option value="cat1">Категория 1</option>
                <option value="cat2">Категория 2</option>
                <option value="cat3">Категория 3</option>
                <option value="cat4">Категория 4</option>
            </select>
        </div>

        <div>
            <form id="photoInput" enctype="multipart/form-data" method="post">
                <label for="photoFile"><b>Фотография: </b></label>
                <input id="photoFile" type="file" name="file" multiple accept="image/*,image/jpeg">
            </form>
        </div>
        <button id="createProject">Создать</button>

        <script src="/resources/libs/jquery-1.11.3.min.js"></script>
        <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <script>

            var imgId = '';
            var projectType = [];
            var project = {};
            var imagesIds = {};

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
                        $('#imgPreview').attr("src", "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/" + imgId);
                    }
                });
            });

            function deleteImgFromDB(picId) {
                $.ajax({
                    url: '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/delete/id/' + picId,
                    method: 'POST',
                    success: function (response) {
                    },
                    error: function (response) {
                    }
                });
            }

            $(document).on('click', '#createProject', function (event) {

                project.typeOfProject = $('#projectType').val();
                project.projectName = $('#projectName').val();
                project.projectDescription = $('#projectDescription').val();
                project.amountRequested = $('#amountRequested').val();
                project.categoriesOfIndustry = $('#categoriesOfIndustry').val();

                imagesIds[imgId] = 1;
                project.imagesIds = imagesIds;

                $.ajax({
                    type: "POST",
                    url: "/api/rest/projectsAndInvestmentsService/project/create",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify(project),
                    success: function (createdProjectId) {
                        window.location.href = '/project/id/' + createdProjectId.id;
                    },
                    error: function (response) {
                        alert("Внутренняя ошибка сервера");
                    }
                });
            });
        </script>
    </body>
</html>
