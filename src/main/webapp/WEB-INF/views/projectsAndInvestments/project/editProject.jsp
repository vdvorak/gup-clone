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
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<jsp:include page="/WEB-INF/templates/authentification.jsp"/>

<div>
    <div>
        <h2 align="center">Редактирование проекта</h2>
    </div>
    <div>
        <!--
        <div>
            <img id="projectImg" src="#" width="200" height="200">
        </div>

        <div>
            <form id="projectPhotoInput" enctype="multipart/form-data" method="post">
                <label for="photoFile"><b>Новая фотография: </b></label>
                <input id="photoFile" type="file" name="file" multiple accept="image/*,image/jpeg">
            </form>
        </div>
        -->
        <div id="drop_zone">

            <button id="addImg">Загрузить фото</button>
            <form id="uploadProfilePhotoForm" enctype="multipart/form-data"
                  method="post" style="display:none">
                <p><input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg" multiple></p>
            </form>

            <div class="imgBlock">
                <!--uploaded images-->
            </div>
            Перетяните файлы сюда
        </div>

        <div>
            <label for="title"><b>Название: </b></label>
            <input id="title"/>
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

        <div class="row">
            <div class="col-xs-12">
                <label for="textarea"><b>Описание: </b></label>
                <textarea id="textarea"></textarea>
            </div>
        </div>


        <button id="editProject">Сохранить изменения</button>
    </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/js/common.js"></script>
<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
<sec:authorize access="isAuthenticated()">
    <script src="/resources/js/autorizedHeader.js"></script>
</sec:authorize>
<script>
    var changedProject = {};
    var projectId = '';
    var imgId = '';
    var imagesIds = {};
    var picMapObj = {};
    var imgsArrResult = {};
    var picArrDel = [];
    var picArrNew = [];
    var picArrIn = {};

    $(document).ready(function () {
        /*$.ajax({
         type: "GET",
         url: "/api/rest/projectsAndInvestmentsService/project/id/
        ${projectId}/read",
         success: function (projectData) {
         projectId = projectData.id;
         if (projectData.imagesIds !== null && projectData.imagesIds != '') {
         for (var key in projectData.imagesIds) {
         if (projectData.imagesIds[key] === "pic1") {
         $('#projectImg').attr('src', '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key);
         break;
         }
         }
         } else {
         $('#projectImg').attr('src', '/resources/images/no_photo.jpg');
         }
         $('#title').attr('placeholder', projectData.title);
         $('#projectType').attr('placeholder', projectData.type);

         //----------------------  HTML EDITOR-------------------------------------//


         //---------------------- END  HTML EDITOR-------------------------------------//
         },
         statusCode: {
         404: function () {
         alert('Такого проекта нет');
         window.location.href = "/project/list";
         }
         }
         });
         */

        $.ajax({
            type: "GET",
            url: "/api/rest/projectsAndInvestmentsService/project/id/${projectId}/read",
            success: function (projectData) {
                projectId = projectData.id;
                if (projectData.imagesIds !== null && projectData.imagesIds != '') {
                    picMapObj = projectData.imagesIds;
                    for (var id in picMapObj) {
                        picArrIn[id] = picMapObj[id];
                        $('.imgBlock').append('<ul id="' + id + '" style="display: inline-table; list-style-type: none"' +
                                ' <li style="background-color: white">' +
                                '<a rel="example_group"> ' +
                                '<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + id + '"' + 'width="150" height="150"> ' +
                                '</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
                    }
                }
                $('#title').attr('placeholder', projectData.title);
                $('#projectType').attr('placeholder', projectData.type);

                //----------------------  HTML EDITOR-------------------------------------//
                tinymce.init({
                    selector: 'textarea',
                    height: 300,
                    theme: 'modern',
                    plugins: [
                        'advlist autolink lists link image charmap print preview hr anchor pagebreak',
                        'searchreplace wordcount visualblocks visualchars code fullscreen',
                        'insertdatetime media nonbreaking save table contextmenu directionality',
                        'emoticons template paste textcolor colorpicker textpattern imagetools'
                    ],
                    toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
                    toolbar2: 'print preview media | forecolor backcolor emoticons',
                    image_advtab: true,
                    templates: [
                        {title: 'Test template 1', content: 'Test 1'},
                        {title: 'Test template 2', content: 'Test 2'}
                    ],
                    content_css: [
                        '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
                        '//www.tinymce.com/css/codepen.min.css'
                    ],
                    init_instance_callback: function (editor) {
                        editor.setContent(projectData.description);
                    }
                });

                //---------------------- END  HTML EDITOR-------------------------------------//
            },
            statusCode: {
                404: function () {
                    alert('Такого проекта нет');
                    window.location.href = "/project/list";
                }
            }
        });

        // Setup the dnd listeners.
        var dropZone = document.getElementById('drop_zone');
        dropZone.addEventListener('dragover', handleDragOver, false);
        dropZone.addEventListener('drop', handleFileSelect, false);

        function handleFileSelect(evt) {
            evt.stopPropagation();
            evt.preventDefault();

            var files = evt.dataTransfer.files; // FileList object.

            // files is a FileList of File objects. List some properties.
            for (var i = 0, f; f = files[i]; i++) {
                var fd = new FormData();
                fd.append('file', f);
                $.ajax({
                    type: "POST",
                    url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",
                    data: fd,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,

                    success: function (data, textStatus, request) {
                        var id = data.id;
                        var isImage = f.type.substring(0, 5) === 'image';
                        if (isImage) {
                            picArrIn[id] = "image";
                            $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none"' +
                                    ' <li style="background-color: white">' +
                                    '<a rel="example_group"> ' +
                                    '<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
                                    '</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
                        }
                    }
                });


            }
        }

        function handleDragOver(evt) {
            evt.stopPropagation();
            evt.preventDefault();
            evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
        }

        $('#addImg').click(function(){
            $('#uploadProfilePhotoInput').trigger('click');
        });

        $('#uploadProfilePhotoInput').change(function (event) {
            event.preventDefault();

            var files = event.currentTarget.files;
            for (var i = 0, f; f = files[i]; i++) {
                var fd = new FormData();
                fd.append('file', f);
                $.ajax({
                    type: "POST",
                    url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",
                    data: fd,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,

                    success: function (data, textStatus, request) {
                        var id = data.id;
                        var isImage = f.type.substring(0, 5) === 'image';
                        if (isImage) {
                            picArrIn[id] = "image";
                            $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none">' +
                                    ' <li style="background-color: white">' +
                                    '<a rel="example_group"> ' +
                                    '<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
                                    '</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
                        }
                    }
                });
            }
            event.currentTarget.form.reset();
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

    // delete pictures only from page
    function deleteImgFromPage(idImg) {
        $('#' + idImg).remove();
        picArrDel.push(idImg);
    }
    /*
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
     */
    $(document).on('click', '#editProject', function (event) {

        for(var key in picArrIn) {
            if(picArrDel.indexOf(key) === -1) picArrNew.push(key);
        }

        for (var i = 0; i < picArrNew.length; i++) {
            imgsArrResult[picArrNew[i]] = picArrIn[picArrNew[i]];
        }

        for(var key in imgsArrResult) {
            imgId = key;
            break;
        }

        for(var i = 0; i < picArrDel.length; i++) {
            deleteImgFromDB(picArrDel[i]);
        }

        changedProject.id = projectId;

        if ($('#projectType').val() != "") {
            changedProject.type = $('#projectType').val();
        }

        if ($('#title').val() != "") {
            changedProject.title = $('#title').val();
        }

        if (imgId != "") {
            imgsArrResult[imgId] = "pic1";
            changedProject.imagesIds =  imgsArrResult;
        }

        changedProject.description = tinymce.activeEditor.getContent({format: 'raw'});

        $.ajax({
            type: "POST",
            url: "/api/rest/projectsAndInvestmentsService/project/edit",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(changedProject),
            statusCode: {
                200: function () {
                    window.location.href = '/project/id/' + projectId;
                }
            }
        });
    });


</script>
</body>
</html>

