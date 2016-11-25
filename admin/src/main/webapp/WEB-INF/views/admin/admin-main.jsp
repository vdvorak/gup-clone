<%--<%@ page contentType="text/html; charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="ru">--%>

<%--<head>--%>

    <%--<meta charset="utf-8">--%>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--<meta name="description" content="">--%>
    <%--<meta name="author" content="">--%>

    <%--<title>Главная страница | Панель управления</title>--%>

    <%--<!-- Top Links -->--%>
    <%--<jsp:include page="/WEB-INF/templates/admin-panel/admin-top-links.jsp"/>--%>
    <%--<!-- Top Links -->--%>

<%--</head>--%>

<%--<body>--%>

<%--<div id="wrapper">--%>

    <%--<!-- Navigation -->--%>
    <%--<jsp:include page="/WEB-INF/templates/admin-panel/admin-left-bar.jsp"/>--%>
    <%--<!-- Navigation -->--%>

    <%--<div id="page-wrapper">--%>
        <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
                <%--<h1 class="page-header">Главная страница административной панели</h1>--%>
            <%--</div>--%>
            <%--<!-- /.col-lg-12 -->--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
                <%--<div class="col-lg-8">--%>
                    <%--<div class="span9">--%>
                        <%--<div id="content">--%>
                            <%--<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">--%>
                                <%--<li><a href="#offers" data-toggle="tab">Объявления</a></li>--%>
                                <%--<li><a href="#tenders" data-toggle="tab">Тендеры</a></li>--%>
                                <%--<li><a href="#projects" data-toggle="tab">Проекты</a></li>--%>
                            <%--</ul>--%>
                            <%--<div id="my-tab-content" class="tab-content">--%>
                                <%--<div class="tab-pane active" id="offers">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-lg-12">--%>
                                            <%--<div class="panel panel-default">--%>
                                                <%--<!-- /.panel-heading -->--%>
                                                <%--<div class="panel-body">--%>
                                                    <%--<div class="dataTable_wrapper">--%>
                                                        <%--<table id="AllOffers"--%>
                                                               <%--class="table table-striped table-bordered table-hover"--%>
                                                               <%--cellspacing="0" width="100%">--%>
                                                            <%--<thead>--%>
                                                            <%--<tr>--%>
                                                                <%--<th>Id</th>--%>
                                                                <%--<th>Фото</th>--%>
                                                                <%--<th>Заголовок</th>--%>
                                                                <%--<th>Дата</th>--%>
                                                            <%--</tr>--%>
                                                            <%--</thead>--%>
                                                        <%--</table>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="tab-pane" id="projects">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-lg-12">--%>
                                            <%--<div class="panel panel-default">--%>
                                                <%--<!-- /.panel-heading -->--%>
                                                <%--<div class="panel-body">--%>
                                                    <%--<div class="dataTable_wrapper">--%>
                                                        <%--<table id="AllTenders"--%>
                                                               <%--class="table table-striped table-bordered table-hover"--%>
                                                               <%--cellspacing="0" width="100%">--%>
                                                            <%--<thead>--%>
                                                            <%--<tr>--%>
                                                                <%--<th>Id</th>--%>
                                                                <%--<th>Фото</th>--%>
                                                                <%--<th>Заголовок</th>--%>
                                                                <%--<th>Дата</th>--%>
                                                            <%--</tr>--%>
                                                            <%--</thead>--%>
                                                        <%--</table>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="tab-pane" id="tenders">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-lg-12">--%>
                                            <%--<div class="panel panel-default">--%>
                                                <%--<!-- /.panel-heading -->--%>
                                                <%--<div class="panel-body">--%>
                                                    <%--<div class="dataTable_wrapper">--%>

                                                        <%--<table id="AllProjects"--%>
                                                               <%--class="table table-striped table-bordered table-hover"--%>
                                                               <%--cellspacing="0" width="100%">--%>
                                                            <%--<thead>--%>
                                                            <%--<tr>--%>
                                                                <%--<th>Id</th>--%>
                                                                <%--<th>Фото</th>--%>
                                                                <%--<th>Заголовок</th>--%>
                                                                <%--<th>Дата</th>--%>
                                                            <%--</tr>--%>
                                                            <%--</thead>--%>
                                                        <%--</table>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<!-- /.panel -->--%>
<%--<!-- Bottom Links -->--%>
<%--<jsp:include page="/WEB-INF/templates/admin-panel/admin-bottom-links.jsp"/>--%>
<%--<!-- Bottom Links -->--%>

<%--<script>--%>
    <%--var idCorrect = [];--%>

    <%--$(document).ready(function () {--%>
        <%--var deleteIndex;--%>
        <%--var events = $('#events');--%>
        <%--var data;--%>
        <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--contentType: "application/json; charset=utf-8",--%>
            <%--dataType: "json",--%>
            <%--url: "/admin/ajaxAllCorrection",--%>
            <%--success: function (response) {--%>
                <%--data = response;--%>
                <%--for (var i = 0; i < data.length; i++){--%>
                    <%--data[i].dateTime = new Date(parseInt(data[i].dateTime));--%>
                    <%--data[i].dateTime = moment(data[i].dateTime).locale("ru").format('LLL');--%>
                <%--}--%>

                <%--var table = $('#correction').DataTable({--%>
                    <%--select: {--%>
                        <%--style: 'single'--%>
                    <%--},--%>
                    <%--data: data,--%>
                    <%--"columns": [--%>
                        <%--{"data": "id"},--%>
                        <%--{"data": "code"},--%>
                        <%--{"data": "type"},--%>
                        <%--{"data": "senderId"},--%>
                        <%--{"data": "recipient"},--%>
                        <%--{"data": "dateTime"},--%>
                        <%--{"data": "amount"},--%>
                        <%--{"data": "status"}--%>
                    <%--],--%>
                    <%--"language": {--%>
                        <%--"url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"--%>
                    <%--}--%>
                <%--});--%>

                <%--table--%>
                        <%--.on('select', function (e, dt, type, indexes) {--%>
                            <%--var rowData = table.rows(indexes).data().toArray();--%>
                            <%--idCorrect.push(rowData[0].id);--%>
                            <%--$("input[name='transactionId']").attr("value", rowData[0].id);--%>
                            <%--$('#confirmBtn').attr("class", "btn btn-success");--%>
                            <%--$('#rejectBtn').attr("class", "btn btn-danger");--%>
                            <%--$('#commentInp').removeAttr("readonly");--%>
                        <%--})--%>
                        <%--.on('deselect', function (e, dt, type, indexes) {--%>
                            <%--$("input[name='transactionId']").attr("value", "");--%>
                            <%--$('#confirmBtn').attr("class", "btn btn-success disabled");--%>
                            <%--$('#rejectBtn').attr("class", "btn btn-danger disabled");--%>
                            <%--$('#commentInp').attr("readonly","readonly");--%>
                            <%--var rowData = table.rows(indexes).data().toArray();--%>
                            <%--deleteIndex = idCorrect.indexOf(rowData[0].id);--%>

                            <%--if (deleteIndex > -1) {--%>
                                <%--idCorrect.splice(deleteIndex, 1);--%>
                            <%--}--%>
                        <%--});--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>

    <%--function submitChanges() {--%>
        <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--url: "/admin/ajaxAdminConfirm",--%>
            <%--data: {"idCorrect": JSON.stringify(idCorrect)},--%>
            <%--success: function (response) {--%>
                <%--window.location.href = '/admin';--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
<%--</script>--%>

<%--<!-- Morris Charts JavaScript -->--%>
<%--<script src="/resources/bower_components/raphael/raphael-min.js"></script>--%>
<%--<script src="/resources/bower_components/morrisjs/morris.min.js"></script>--%>
<%--<script src="/resources/js/morris-data.js"></script>--%>
<%--</body>--%>
<%--</html>--%>

