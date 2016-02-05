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

    <title>Редактирование тендеров | Панель управления</title>
    <!-- Links -->
    <jsp:include page="/WEB-INF/templates/admin-top-links.jsp"/>
    <!-- Links -->
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
                <h1 class="page-header">Тендеры</h1>
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
                            <table id="tenders" class="table table-striped table-bordered table-hover"
                                   cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Фото</th>
                                    <th>Название</th>
                                    <th>Тип</th>
                                </tr>
                                </thead>
                            </table>
                            <table class="table table-user-information">
                                <tbody>
                                <h3 class="panel-title">Редактировать тендер</h3>
                                <tr>
                                    <td>ID тендера:</td>
                                    <td><input id="inp" class="form-control" name="transactionId" readonly required>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <a id="tenderIdhref" href="">
                                <button id="tenderIdBtn" type="submit" class="btn btn-primary disabled">Редактировать
                                </button>
                            </a>
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

    function findFirstImg(arr) {
        var url = '/resources/images/no_photo.jpg';
        var imgId = '';
        for (var i in arr) {
            if (arr[i] === 'pic1') {
                imgId = i;
                url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
                break;
            }
        }
        return url;
    }


    $(document).ready(function () {
        var data;
        var tenderFilterOptions = {};
        tenderFilterOptions.skip = 0;
        tenderFilterOptions.limit = 10;
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/tenderService/tender/read/all/",
            data: JSON.stringify(tenderFilterOptions),
            success: function (response) {
                data = response.entities;

                for (var i = 0; i < data.length; i++) {
                    if (data[i].uploadFilesIds !== null) {
                        data[i].uploadFilesIds = '<img src="'+ findFirstImg(data[i].uploadFilesIds) + '" width="100" height="100">';
                    }
                    else {
                        data[i].uploadFilesIds = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                    }
                }

                var table = $('#tenders').DataTable({
                    select: {
                        style: 'single'
                    },
                    data: data,
                    "columns": [
                        {"data": "uploadFilesIds"},
                        {"data": "title"},
                        {"data": "type"}
                    ],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
                    }
                });

                table
                        .on('select', function (e, dt, type, indexes) {
                            var rowData = table.rows(indexes).data().toArray();
                            $("input[name='transactionId']").attr("value", rowData[0].id);
                            $('#tenderIdhref').attr("href", "/tender/id/" + rowData[0].id + "/update");
                            $('#inp').removeAttr("readonly");
                            $('#tenderIdBtn').attr("class", "btn btn-danger");
                        })
                        .on('deselect', function (e, dt, type, indexes) {
                            $("input[name='transactionId']").attr("value", "");
                            $('#inp').attr("readonly", "readonly");
                            $('#tenderIdBtn').attr("class", "btn btn-danger disabled");
                        });
            }
        });
    });
</script>
</body>
</html>