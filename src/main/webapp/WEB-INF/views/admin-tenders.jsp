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
              <table id="accountant" class="table table-striped table-bordered table-hover"
                     cellspacing="0" width="100%">
                <thead>
                <tr>
                  <th>Фото</th>
                  <th>ID</th>
                  <th>тип</th>
                  <th>отправитель</th>
                  <th>получатель</th>
                  <th>время</th>
                  <th>сумма</th>
                  <th>статус</th>
                </tr>
                </thead>
              </table>

              <form method="POST" action="/accountant/accountantCancelRequest">
                <table class="table table-user-information">
                  <tbody>
                  <h3 class="panel-title">Редактировать тендер</h3>
                  <tr>
                    <td>ID тендера:</td>
                    <td><input id="inp" class="form-control" name="transactionId" readonly required></td>
                  </tr>
                  </tbody>
                </table>
                <button id="cancelBtn" type="submit" class="btn btn-primary disabled">Редактировать</button>
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

</div>
<!-- /.col-lg-8 -->

</div>
<!-- /.row -->
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/resources/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
<script src="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<script src="https://cdn.datatables.net/select/1.0.1/js/dataTables.select.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.0.3/js/dataTables.buttons.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/resources/dist/js/sb-admin-2.js"></script>

<!-- Moment library for humanlike date format -->
<script src="/resources/js/moment-with-locales.js"></script>

<script>
  $(document).ready(function () {
    var data;
    var tenderFilterOptions = new Object();
    tenderFilterOptions.skip = 0;
    tenderFilterOptions.limit = 10;

    $.ajax({
      type: "POST",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      data: {"skip" : 0, "limit" : 10},
      url: "/users/getallTender",
      success: function (response) {
        data = response;
        alert(data);
        alert(JSON.stringify(data));


//        for (var i = 0; i < data.length; i++) {
//          data[i].dateTime = new Date(parseInt(data[i].dateTime));
//          data[i].dateTime = moment(data[i].dateTime).locale("ru").format('LLL');
//        }
//        var table = $('#accountant').DataTable({
//          select: {
//            style: 'single'
//          },
//          data: data,
//          "columns": [
//            {"data": "id"},
//            {"data": "code"},
//            {"data": "type"},
//            {"data": "sender"},
//            {"data": "recipient"},
//            {"data": "dateTime"},
//            {"data": "amount"},
//            {"data": "status"}
//          ],
//          "language": {
//            "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
//          }
//        });
//
//        table
//                .on('select', function (e, dt, type, indexes) {
//                  var rowData = table.rows(indexes).data().toArray();
//                  $("input[name='transactionId']").attr("value", rowData[0].id);
//                  $('#inp').removeAttr("readonly");
//                  $('#cancelBtn').attr("class", "btn btn-danger");
//                })
//                .on('deselect', function (e, dt, type, indexes) {
//                  $("input[name='transactionId']").attr("value", "");
//                  $('#inp').attr("readonly", "readonly");
//                  $('#cancelBtn').attr("class", "btn btn-danger disabled");
//                });



      }
    });
  });
</script>

</body>

</html>