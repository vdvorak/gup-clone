<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 21.10.2015
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Редактирование профилей и ролей | Панель управления</title>

    <!-- Links -->
    <jsp:include page="/WEB-INF/templates/admin-top-links.jsp"/>
    <!-- Links -->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/templates/admin-left-bar.jsp"/>
    <!-- Navigation -->

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Информация</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>


        <div class="row">
            <div class="col-lg-12">

                <div class="col-lg-8">
                    <div class="span9">

                        <div id="content">
                            <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                <li><a href="#adminsTable" data-toggle="tab">Админы</a></li>
                                <li><a href="#moderatorsTable" data-toggle="tab">Модераторы</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="adminsTable">

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel panel-default">
                                                <!-- /.panel-heading -->
                                                <div class="panel-body">
                                                    <div class="dataTable_wrapper">

                                                        <table id="admins"
                                                               class="table table-striped table-bordered table-hover"
                                                               cellspacing="0" width="100%">
                                                            <thead>
                                                            <tr>
                                                                <th>Id</th>
                                                                <th>Фото</th>
                                                                <th>Логин</th>
                                                                <th>Дата</th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="tab-pane" id="moderatorsTable">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel panel-default">
                                                <!-- /.panel-heading -->
                                                <div class="panel-body">
                                                    <div class="dataTable_wrapper">

                                                        <table id="moderators"
                                                               class="table table-striped table-bordered table-hover"
                                                               cellspacing="0" width="100%">
                                                            <thead>
                                                            <tr>
                                                                <th>Id</th>
                                                                <th>Фото</th>
                                                                <th>Логин</th>
                                                                <th>Дата</th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.panel -->

<!-- Bottom Links -->
<jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>
<!-- Bottom Links -->

<script>

    $(document).ready(function () {
        var data;
        var filterOptions = new Object();
        filterOptions.skip = 0;
        filterOptions.limit = 1000000;
        filterOptions.userRoles = ['ROLE_ADMIN'];
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/profilesService/profile/read/all",
            data: JSON.stringify(filterOptions),
            success: function (response) {
                data = response.entities;

                for (var i = 0; i < data.length; i++) {
                    if (data[i].contact !== null) {
                        if (data[i].contact.pic.length > 2) {
                            data[i].contact.pic = '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + data[i].contact.pic + '" width="100" height="100">';
                        }
                        else {
                            data[i].contact.pic = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                        }
                    }
                    else {
                        data[i].contact = new Object();
                        data[i].contact.pic = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                    }
                }

                var table = $('#admins').DataTable({
                    select: {
                        style: 'single'
                    },
                    data: data,
                    "columns": [
                        {"data": "contact.pic"},
                        {"data": "username"},
                        {"data": "email"},
                        {"data": "confirmModerator"}
                    ],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
                    }
                });

                table
                        .on('select', function (e, dt, type, indexes) {
                            var rowData = table.rows(indexes).data().toArray();
                            $("input[name='transactionId']").attr("value", rowData[0].id);
                            $('#inp').removeAttr("readonly");
                            $('#cancelBtn').attr("class", "btn btn-danger");
                        })
                        .on('deselect', function (e, dt, type, indexes) {
                            $("input[name='transactionId']").attr("value", "");
                            $('#inp').attr("readonly", "readonly");
                            $('#cancelBtn').attr("class", "btn btn-danger disabled");
                        });
            }
        });
    });
</script>
</body>
</html>

