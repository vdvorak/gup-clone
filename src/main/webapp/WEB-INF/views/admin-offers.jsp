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

  <title>Панель управления</title>

  <!-- Bootstrap Core CSS -->
  <link href="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

  <!-- DataTables CSS -->
  <link href="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
        rel="stylesheet">

  <!-- DataTables Responsive CSS -->
  <link href="/resources/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

  <!-- DataTables Select CSS -->
  <link href="https://cdn.datatables.net/select/1.0.1/css/select.dataTables.min.css" rel="stylesheet">
  <link href="https://cdn.datatables.net/buttons/1.0.3/css/buttons.dataTables.min.css" rel="stylesheet">


  <!-- Timeline CSS -->
  <link href="/resources/dist/css/timeline.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

  <!-- Morris Charts CSS -->
  <link href="/resources/bower_components/morrisjs/morris.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>

<div id="wrapper">

  <!-- Navigation -->
  <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <!-- Header-bar -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value="/admin" />">Панель управления</a>
    </div>
    <!-- Header-bar -->

    <!-- Header dropdown menu -->
    <ul class="nav navbar-top-links navbar-right">
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
          <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
          <li><a href="<c:url value="/profileEditor" />"><i class="fa fa-user fa-fw"></i> Профиль</a>
          </li>
          <li class="divider"></li>
          <li><a href="<c:url value="/logout" />"><i class="fa fa-sign-out fa-fw"></i> Выход</a>
          </li>
        </ul>
      </li>
    </ul>
    <!-- Header dropdown menu -->

    <!-- Left sidebar menu -->
    <div class="navbar-default sidebar" role="navigation">
      <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
          <li class="sidebar-search">
            <div class="input-group custom-search-form">
              <input type="text" class="form-control" placeholder="Поиск...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                  <i class="fa fa-search"></i>
                                </button>
                            </span>
            </div>
          </li>

          <li>
            <a href="<c:url value="/admin-user" />"><i class="fa fa-dashboard fa-fw"></i>Пользователи</a>
          </li>
          <li>
            <a href="<c:url value="/admin-tenders" />"><i class="fa fa-share fa-fw"></i>Тендеры</a>
          </li>

          <li>
            <a href="<c:url value="/admin-projects" />"><i class="fa fa-edit fa-fw"></i>Проекты</a>
          </li>
          <li>
            <a href="<c:url value="/admin-admins" />"><i class="fa fa-table fa-fw"></i>Админы</a>
          </li>
          <li>
            <a href="<c:url value="/admin-offers" />"><i class="fa fa-credit-card fa-fw"></i>Объявления</a>
          </li>
          <%--<li>--%>
          <%--<a href="<c:url value="/accountant/internal" />"><i class="fa fa-share fa-fw"></i> Внутренние транзакции</a>--%>
          <%--</li>--%>
        </ul>
      </div>
    </div>
    <!-- Left sidebar menu -->
  </nav>
  <!-- Navigation -->



  <!-- #page-wrapper -->
  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Транзакции на рассмотрении</h1>
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
                  <h3 class="panel-title">Отменить заявку на корректировку</h3>
                  <tr>
                    <td>ID внутренней транзакции:</td>
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


    $.ajax({
      type: "POST",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      url: "/accountant/ajaxAllAccountantCorrection",
      success: function (response) {
        data = response;
        for (var i = 0; i < data.length; i++) {
          data[i].dateTime = new Date(parseInt(data[i].dateTime));
          data[i].dateTime = moment(data[i].dateTime).locale("ru").format('LLL');
        }
        var table = $('#accountant').DataTable({
          select: {
            style: 'single'
          },
          data: data,
          "columns": [
            {"data": "id"},
            {"data": "code"},
            {"data": "type"},
            {"data": "sender"},
            {"data": "recipient"},
            {"data": "dateTime"},
            {"data": "amount"},
            {"data": "status"}
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