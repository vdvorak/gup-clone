<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Bootstrap Core CSS -->
<link href="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

<!-- AdminMenu CSS -->
<link href="/resources/css/admin_style.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
      rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="/resources/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

<!-- DataTables Select CSS -->
<link href="/resources/css/select.dataTables.min.css" rel="stylesheet">
<link href="/resources/css/buttons.dataTables.min.css" rel="stylesheet">

<!-- Timeline CSS -->
<link href="/resources/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="/resources/js/html5shiv.js"></script>
<script src="/resources/js/respond.min.js"></script>
<![endif]-->

<!-- Full Calendar 3.0.1 -->
<link rel='stylesheet' href='../../../resources/fullcalendar/lib/cupertino/jquery-ui.min.css'/>
<link href='../../../resources/fullcalendar/fullcalendar.css' rel='stylesheet'/>
<link href='../../../resources/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print'/>
<script src='../../../resources/fullcalendar/lib/moment.min.js'></script>
<script src='../../../resources/fullcalendar/lib/jquery.min.js'></script>
<script src='../../../resources/fullcalendar/lib/jquery-ui.min.js'></script>
<script src='../../../resources/fullcalendar/fullcalendar.min.js'></script>
<script src='../../../resources/fullcalendar/gcal.js'></script>
<script src='../../../resources/fullcalendar/locale/ru.js'></script>
<script src='../../../resources/fullcalendar/locale/uk.js'></script>


<!-- Here we ara getting service url from properties files and then put them into global variables. -->
<script>
    <%@ page language="java" import="java.util.ResourceBundle" %>
    <% ResourceBundle resource = ResourceBundle.getBundle("model-host");
      String apiUi=resource.getString("apiUi");
      String apiOauth=resource.getString("apiOauth");
      String host=resource.getString("host");
      %>

    let apiOauth = '<%=apiOauth %>';
    let apiUi = '<%=apiUi%>';
    let host = '<%=host%>';
</script>

