<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 28.12.2015
  Time: 15:59
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

    <title>Редактирование пользователей | Панель управления</title>
    <!-- Top Links -->
    <jsp:include page="/WEB-INF/templates/admin-top-links.jsp"/>
    <!-- Top Links -->
</head>
<body>
<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/templates/admin-left-bar.jsp"/>
    <!-- Navigation -->

    <!-- #page-wrapper -->
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Пользователи</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table id="users" class="table table-striped table-bordered table-hover"
                                   cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Фото</th>
                                    <th>ФИО/организация</th>
                                    <th>email</th>
                                    <th>Статус</th>
                                </tr>
                                </thead>
                            </table>

                            <form method="POST" action="/accountant/accountantCancelRequest">
                                <table class="table table-user-information">
                                    <tbody>
                                    <h3 class="panel-title">Редактировать профиль</h3>
                                    <tr>
                                        <td>ID пользователя:</td>
                                        <td><input id="inp" class="form-control" name="transactionId" readonly required>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <button id="cancelBtn" type="submit" class="btn btn-primary disabled">Редактировать
                                </button>
                            </form>
                        </div>
                        <!-- /.table -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /.panel -->
<!-- Bottom Links -->
<jsp:include page="/WEB-INF/templates/admin-bottom-links.jsp"/>
<!-- Bottom Links -->
<script>
    var idCorrect = [];

    $(document).ready(function () {
        var data;
        var filterOptions = new Object();
        filterOptions.skip = 0;
        filterOptions.limit = 10;
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/profilesService/profile/read/all",
            data: JSON.stringify(filterOptions),
            success: function (response) {
                data = response.entities;

                for (var i = 0; i < data.length; i++) {
                    if (data[i].contact !== null) {
                        if (data[i].contact.pic.length > 2){
                            data[i].contact.pic = '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + data[i].contact.pic + '" width="100" height="100">';
                        }
                       else{
                            data[i].contact.pic = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                        }
                    }
                    else {
                        data[i].contact = new Object();
                        data[i].contact.pic = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                    }
                }

                var table = $('#users').DataTable({
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

    function submitChanges() {
        $.ajax({
            type: "POST",
            url: "/admin/ajaxAdminConfirm",
            data: {"idCorrect": JSON.stringify(idCorrect)},
            success: function (response) {
                window.location.href = '/admin';
            }
        });
    }
</script>
</body>
</html>