<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Проекты | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</head>
<body>
    <!--[if lt IE 8]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
        your browser</a> to improve your experience.</p>
    <![endif]-->

    <jsp:include page="/WEB-INF/templates/common-header.jsp"/>

    <jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

    <jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

    <jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

    <!--PAGE CONTENT START-->

    <div class="container2">
        <div class="contentContainer editor">
            <div class="title">РЕДАКТОР ПРОЕКТА</div>
            <form class="project" action="">
                <div class="field required tit">
                    <label for="main-title-info" class="editorLabel">Заголовок</label>
                    <input id="main-title-info" type="text" name='text' class="editorInput">
                </div>
                <div class="field required projType">
                    <div class="editorLabel">Тип проекта</div>
                    <div class="radioGroup">
                        <div class="column c1">
                            <div class="row">
                                <label for="type-restruct">Реструктуризация</label>
                                <label class="label-checkbox">
                                    <input type="radio" class="greenCheckbox" id="type-restruct" value="restruct" name="type" /><span></span></label>
                            </div>
                            <div class="row">
                                <label for="type-paper">Проект на бумаге</label>
                                <label class="label-checkbox">
                                    <input type="radio" class="greenCheckbox" id="type-paper" value="paper" name="type" /><span></span></label>
                            </div>
                        </div>
                        <div class="column c2">
                            <div class="row">
                                <label for="type-prototype">Готовый прототип</label>
                                <label class="label-checkbox">
                                    <input type="radio" class="greenCheckbox" id="type-prototype" value="prototype" name="type" /><span></span></label>
                            </div>
                            <div class="row">
                                <label for="type-nouHau">Ноу-Хау</label>
                                <label class="label-checkbox">
                                    <input type="radio" class="greenCheckbox" id="type-nouHau" value="nouHau" name="type" /><span></span></label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="field required">
                    <label for="sum" class="editorLabel">Сумма</label>
                    <input id="sum" type="number" name='sum' class="editorInput" style="width: 291px;">
                    <span class="currency">₴</span>
                </div>
                <div class="field description">
                    <label for="description" class="editorLabel">Описание</label>
                    <textarea id="description" type="text" name='description' class="editorInput"></textarea>
                </div>
                <div class="field IMGUploader">
                    <div class="titleFile" data-title="Добавить изображение"><button type="submit" class="blogCreationSubmit"></button></div>
                    <input type="file" style="display: none;" multiple="multiple" accept="image/*">
                    <div class="IMGBlock">
                        <div class="defaultIMG"><img src="/resources/images/defaultIMG.png" alt="defaultIMG"></div>
                        <div class="defaultIMG"><img src="/resources/images/defaultIMG.png" alt="defaultIMG"></div>
                    </div>
                </div>
                <div class="field">
                    <button type="submit" class="info-submit">Сохранить</button>
                </div>
            </form>
        </div>
    </div>

    <!--PAGE CONTENT END-->

    <sec:authorize access="isAuthenticated()">
        <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
    </sec:authorize>

    <jsp:include page="/WEB-INF/templates/footer.jsp"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
    <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
    <script src="/resources/js/vendor/bootstrap.js"></script>
    <script src="/resources/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
    <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

    <script src="/resources/js/autorizedHeader.js"></script>
    <script src="/resources/js/main.js"></script>
    <script src="/resources/js/logo-section.js"></script>
    <script src="/resources/js/search-bar.js"></script>

    <script>
        $("#selectedService option[value='project']").attr("selected","selected");

    </script>

    <%--var changedProject = {};--%>
    <%--var projectId = '';--%>
    <%--var imgId = '';--%>
    <%--var imagesIds = {};--%>
    <%--var picMapObj = {};--%>
    <%--var imgsArrResult = {};--%>
    <%--var picArrDel = [];--%>
    <%--var picArrNew = [];--%>
    <%--var picArrIn = {};--%>

    <%--$(document).ready(function () {--%>
        <%--/*$.ajax({--%>
         <%--type: "GET",--%>
         <%--url: "/api/rest/projectsAndInvestmentsService/project/id/--%>
        <%--${projectId}/read",--%>
         <%--success: function (projectData) {--%>
         <%--projectId = projectData.id;--%>
         <%--if (projectData.imagesIds !== null && projectData.imagesIds != '') {--%>
         <%--for (var key in projectData.imagesIds) {--%>
         <%--if (projectData.imagesIds[key] === "pic1") {--%>
         <%--$('#projectImg').attr('src', '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key);--%>
         <%--break;--%>
         <%--}--%>
         <%--}--%>
         <%--} else {--%>
         <%--$('#projectImg').attr('src', '/resources/images/no_photo.jpg');--%>
         <%--}--%>
         <%--$('#title').attr('placeholder', projectData.title);--%>
         <%--$('#projectType').attr('placeholder', projectData.type);--%>

         <%--//----------------------  HTML EDITOR-------------------------------------//--%>


         <%--//---------------------- END  HTML EDITOR-------------------------------------//--%>
         <%--},--%>
         <%--statusCode: {--%>
         <%--404: function () {--%>
         <%--alert('Такого проекта нет');--%>
         <%--window.location.href = "/project/list";--%>
         <%--}--%>
         <%--}--%>
         <%--});--%>
         <%--*/--%>

        <%--$.ajax({--%>
            <%--type: "GET",--%>
            <%--url: "/api/rest/projectsAndInvestmentsService/project/id/" + getUrlParam('id') + "/read",--%>
            <%--success: function (projectData) {--%>
                <%--projectId = projectData.id;--%>
                <%--if (projectData.imagesIds !== null && projectData.imagesIds != '') {--%>
                    <%--picMapObj = projectData.imagesIds;--%>
                    <%--for (var id in picMapObj) {--%>
                        <%--picArrIn[id] = picMapObj[id];--%>
                        <%--$('.imgBlock').append('<ul id="' + id + '" style="display: inline-table; list-style-type: none"' +--%>
                                <%--' <li style="background-color: white">' +--%>
                                <%--'<a rel="example_group"> ' +--%>
                                <%--'<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + id + '"' + 'width="150" height="150"> ' +--%>
                                <%--'</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');--%>
                    <%--}--%>
                <%--}--%>
                <%--$('#title').attr('placeholder', projectData.title);--%>
                <%--$('#projectType').attr('placeholder', projectData.type);--%>

                <%--//----------------------  HTML EDITOR-------------------------------------//--%>
                <%--tinymce.init({--%>
                    <%--selector: 'textarea',--%>
                    <%--height: 300,--%>
                    <%--theme: 'modern',--%>
                    <%--plugins: [--%>
                        <%--'advlist autolink lists link image charmap print preview hr anchor pagebreak',--%>
                        <%--'searchreplace wordcount visualblocks visualchars code fullscreen',--%>
                        <%--'insertdatetime media nonbreaking save table contextmenu directionality',--%>
                        <%--'emoticons template paste textcolor colorpicker textpattern imagetools'--%>
                    <%--],--%>
                    <%--toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',--%>
                    <%--toolbar2: 'print preview media | forecolor backcolor emoticons',--%>
                    <%--image_advtab: true,--%>
                    <%--templates: [--%>
                        <%--{title: 'Test template 1', content: 'Test 1'},--%>
                        <%--{title: 'Test template 2', content: 'Test 2'}--%>
                    <%--],--%>
                    <%--content_css: [--%>
                        <%--'//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',--%>
                        <%--'//www.tinymce.com/css/codepen.min.css'--%>
                    <%--],--%>
                    <%--init_instance_callback: function (editor) {--%>
                        <%--editor.setContent(projectData.description);--%>
                    <%--}--%>
                <%--});--%>

                <%--//---------------------- END  HTML EDITOR-------------------------------------//--%>
            <%--},--%>
            <%--statusCode: {--%>
                <%--404: function () {--%>
                    <%--alert('Такого проекта нет');--%>
                    <%--window.location.href = "/project/list";--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>

        <%--// Setup the dnd listeners.--%>
        <%--var dropZone = document.getElementById('drop_zone');--%>
        <%--dropZone.addEventListener('dragover', handleDragOver, false);--%>
        <%--dropZone.addEventListener('drop', handleFileSelect, false);--%>

        <%--function handleFileSelect(evt) {--%>
            <%--evt.stopPropagation();--%>
            <%--evt.preventDefault();--%>

            <%--var files = evt.dataTransfer.files; // FileList object.--%>

            <%--// files is a FileList of File objects. List some properties.--%>
            <%--for (var i = 0, f; f = files[i]; i++) {--%>
                <%--var fd = new FormData();--%>
                <%--fd.append('file', f);--%>
                <%--$.ajax({--%>
                    <%--type: "POST",--%>
                    <%--url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",--%>
                    <%--data: fd,--%>
                    <%--async: false,--%>
                    <%--cache: false,--%>
                    <%--contentType: false,--%>
                    <%--processData: false,--%>

                    <%--success: function (data, textStatus, request) {--%>
                        <%--var id = data.id;--%>
                        <%--var isImage = f.type.substring(0, 5) === 'image';--%>
                        <%--if (isImage) {--%>
                            <%--picArrIn[id] = "image";--%>
                            <%--$('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none"' +--%>
                                    <%--' <li style="background-color: white">' +--%>
                                    <%--'<a rel="example_group"> ' +--%>
                                    <%--'<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +--%>
                                    <%--'</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>


            <%--}--%>
        <%--}--%>

        <%--function handleDragOver(evt) {--%>
            <%--evt.stopPropagation();--%>
            <%--evt.preventDefault();--%>
            <%--evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.--%>
        <%--}--%>

        <%--$('#addImg').click(function(){--%>
            <%--$('#uploadProfilePhotoInput').trigger('click');--%>
        <%--});--%>

        <%--$('#uploadProfilePhotoInput').change(function (event) {--%>
            <%--event.preventDefault();--%>

            <%--var files = event.currentTarget.files;--%>
            <%--for (var i = 0, f; f = files[i]; i++) {--%>
                <%--var fd = new FormData();--%>
                <%--fd.append('file', f);--%>
                <%--$.ajax({--%>
                    <%--type: "POST",--%>
                    <%--url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",--%>
                    <%--data: fd,--%>
                    <%--async: false,--%>
                    <%--cache: false,--%>
                    <%--contentType: false,--%>
                    <%--processData: false,--%>

                    <%--success: function (data, textStatus, request) {--%>
                        <%--var id = data.id;--%>
                        <%--var isImage = f.type.substring(0, 5) === 'image';--%>
                        <%--if (isImage) {--%>
                            <%--picArrIn[id] = "image";--%>
                            <%--$('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none">' +--%>
                                    <%--' <li style="background-color: white">' +--%>
                                    <%--'<a rel="example_group"> ' +--%>
                                    <%--'<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +--%>
                                    <%--'</a> <div onclick=\"deleteImgFromPage(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
            <%--event.currentTarget.form.reset();--%>
        <%--});--%>
    <%--});--%>

    <%--function deleteImgFromDB(picId) {--%>
        <%--$.ajax({--%>
            <%--url: '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/delete/id/' + picId,--%>
            <%--method: 'POST',--%>
            <%--success: function (response) {--%>
            <%--},--%>
            <%--error: function (response) {--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    <%--// delete pictures only from page--%>
    <%--function deleteImgFromPage(idImg) {--%>
        <%--$('#' + idImg).remove();--%>
        <%--picArrDel.push(idImg);--%>
    <%--}--%>
    <%--/*--%>
     <%--$(document).on('change', '#photoFile', function (e) {--%>

     <%--var formImg = new FormData($('#photoInput')[0]);--%>

     <%--if (imgId !== '') {--%>
     <%--deleteImgFromDB(imgId);--%>
     <%--}--%>

     <%--$.ajax({--%>
     <%--type: "POST",--%>
     <%--url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",--%>
     <%--data: formImg,--%>
     <%--async: false,--%>
     <%--cache: false,--%>
     <%--contentType: false,--%>
     <%--processData: false,--%>
     <%--success: function (data, textStatus, request) {--%>
     <%--imgId = data.id;--%>
     <%--$('#projectImg').attr("src", "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/" + imgId);--%>
     <%--}--%>
     <%--});--%>
     <%--});--%>
     <%--*/--%>
    <%--$(document).on('click', '#editProject', function (event) {--%>

        <%--for(var key in picArrIn) {--%>
            <%--if(picArrDel.indexOf(key) === -1) picArrNew.push(key);--%>
        <%--}--%>

        <%--for (var i = 0; i < picArrNew.length; i++) {--%>
            <%--imgsArrResult[picArrNew[i]] = picArrIn[picArrNew[i]];--%>
        <%--}--%>

        <%--for(var key in imgsArrResult) {--%>
            <%--imgId = key;--%>
            <%--break;--%>
        <%--}--%>

        <%--for(var i = 0; i < picArrDel.length; i++) {--%>
            <%--deleteImgFromDB(picArrDel[i]);--%>
        <%--}--%>

        <%--changedProject.id = projectId;--%>

        <%--if ($('#projectType').val() != "") {--%>
            <%--changedProject.type = $('#projectType').val();--%>
        <%--}--%>

        <%--if ($('#title').val() != "") {--%>
            <%--changedProject.title = $('#title').val();--%>
        <%--}--%>

        <%--if (imgId != "") {--%>
            <%--imgsArrResult[imgId] = "pic1";--%>
            <%--changedProject.imagesIds =  imgsArrResult;--%>
        <%--}--%>

        <%--changedProject.description = tinymce.activeEditor.getContent({format: 'raw'});--%>

        <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--url: "/api/rest/projectsAndInvestmentsService/project/edit",--%>
            <%--contentType: "application/json; charset=utf-8",--%>
            <%--dataType: "json",--%>
            <%--data: JSON.stringify(changedProject),--%>
            <%--statusCode: {--%>
                <%--200: function () {--%>
                    <%--window.location.href = '/project/id/' + projectId;--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
</body>
</html>

