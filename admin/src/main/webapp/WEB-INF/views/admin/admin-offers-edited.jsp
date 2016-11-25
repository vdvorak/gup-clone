<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Модерация отредактированных объявлений объявлений | Панель управления</title>

    <!-- Links -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-top-links.jsp"/>
    <!-- Links -->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-left-bar.jsp"/>
    <!-- Navigation -->

    <!-- #page-wrapper -->
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Отредактированные объявления</h1>
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
                            <table id="offersTable" class="table table-striped table-bordered table-hover"
                                   cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Фото</th>
                                    <th>Заголовок</th>
                                    <th>Изменения</th>
                                </tr>
                                </thead>
                            </table>

                                <table class="table table-user-information">
                                    <tbody>
                                    <h3 class="panel-title">Редактировать объявление</h3>
                                    <tr>
                                        <td>ID объявления:</td>
                                        <td><input id="inpId" class="form-control" name="offerId" readonly required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>URL объявления:</td>
                                        <td><input id="inpUrl" class="form-control" name="offerUrl" readonly required>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                                <a id="offerIdhref" href="">
                                    <button id="editOfferButton" class="btn btn-primary disabled">
                                        Редактировать
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
<jsp:include page="/WEB-INF/templates/admin-panel/admin-bottom-links.jsp"/>
<!-- Bottom Links -->

<script src="/resources/js/admin/admin-offers-edited.js"></script>

</body>
</html>